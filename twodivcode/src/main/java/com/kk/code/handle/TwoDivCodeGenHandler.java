package com.kk.code.handle;

import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.kk.code.model.ImgByteArray;
import com.kk.code.model.TwoCodeException;
import com.kk.code.util.HttpUtil;

public class TwoDivCodeGenHandler {
	private static final Log logger = LogFactory
			.getLog(TwoDivCodeGenHandler.class);

	/**
	 * 生成QR码
	 * 
	 * @param content
	 *            编码内容
	 * @return
	 * @throws CommonException
	 */
	public BufferedImage genQRCode(String content) throws TwoCodeException {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// hints.put(EncodeHintType.MARGIN, 0);
		BufferedImage imageData = null;
		try {
			// 生成QR码内存图片
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
					BarcodeFormat.QR_CODE, QRCodeMeta.QR_SIZE,
					QRCodeMeta.QR_SIZE, hints);
			imageData = MatrixToImageWriter.toBufferedImage(bitMatrix);

			// 内存图片转换色彩模型
			ColorConvertOp cop = new ColorConvertOp(
					ColorSpace.getInstance(ColorSpace.CS_sRGB), null);
			BufferedImage qrImg = new BufferedImage(imageData.getWidth(),
					imageData.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
			cop.filter(imageData, qrImg);

			return qrImg;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new TwoCodeException(1, "生成二维码错误");
		}
	}

	/**
	 * 在二维码图片中央画图标
	 * 
	 * @param bkImg
	 * @param iconUrl
	 * @throws CommonException
	 */
	public BufferedImage drawIcon(BufferedImage bkImg, String iconUrl)
			throws TwoCodeException {

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
			logger.error(e.getMessage(), e);
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

	/**
	 * 将内存图片转换为字节数组
	 * 
	 * @param image
	 * @return
	 * @throws CommonException
	 */
	public ImgByteArray toByteArray(BufferedImage image)
			throws TwoCodeException {

		ImgByteArray byteArray = new ImgByteArray();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, QRCodeMeta.IMAGE_TYPE, outputStream);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new TwoCodeException(3, "转成字节数组失败");
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
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
