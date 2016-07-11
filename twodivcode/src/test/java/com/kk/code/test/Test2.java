package com.kk.code.test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

import com.kk.code.model.ImgByteArray;
import com.kk.code.model.TwoCodeException;
import com.kk.code.upload.UploadService;
import com.kk.code.util.HttpUtil;
import com.kk.code.handle.QRCodeMeta;

public class Test2 {

	public static void main(String[] args) throws TwoCodeException {

		String url = "http://ebydata.oss-cn-beijing.aliyuncs.com/data/images/act/2015-08-14/88.png";
		String headUrl = "http://ebydata.oss-cn-beijing.aliyuncs.com/data/images/act/2015-08-14/elogo.png";
		BufferedImage img = drawIcon(url, headUrl);
		ImgByteArray imageBytes = toByteArray(img);
		UploadService.write(imageBytes);
	}

	public static BufferedImage drawIcon(String url, String iconUrl)
			throws TwoCodeException {

		BufferedImage bkImg = null;
		try {
			byte[] cont = HttpUtil.getContent(url);
			InputStream in = new ByteArrayInputStream(cont);
			bkImg = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 如果iconUrl为空，直接返回原图
		if (StringUtils.isBlank(iconUrl)) {
			return bkImg;
		}

		// 读取图标
		BufferedImage icon = null;
		try {
			byte[] cont = HttpUtil.getContent(iconUrl);
			InputStream in = new ByteArrayInputStream(cont);
			icon = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TwoCodeException(2, "画图标失败");
		}

		Graphics g = bkImg.getGraphics();

		// 画icon周围的白色边框
		g.setColor(QRCodeMeta.PAD_COLOR);
		g.fillRect(QRCodeMeta.PAD_LEFT, QRCodeMeta.PAD_TOP,
				QRCodeMeta.PAD_WIDTH, QRCodeMeta.PAD_HIGHT);

		// 将图标画在背景图上
		g.drawImage(icon, QRCodeMeta.ICON_LEFT, QRCodeMeta.ICON_TOP,
				QRCodeMeta.ICON_HEIGHT, QRCodeMeta.ICON_WIDTH, null);

		return bkImg;
	}

	public static ImgByteArray toByteArray(BufferedImage image)
			throws TwoCodeException {

		ImgByteArray byteArray = new ImgByteArray();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, QRCodeMeta.IMAGE_TYPE, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw new TwoCodeException(3, "转成字节数组失败");
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		byte[] imageBytes = outputStream.toByteArray();
		byteArray.setBytes(imageBytes);
		byteArray.setImgFmt(QRCodeMeta.IMAGE_TYPE);
		byteArray.setOriginalFilename(System.currentTimeMillis() + "."
				+ QRCodeMeta.IMAGE_TYPE);
		return byteArray;
	}

}
