package com.kk.code.service;

import java.awt.image.BufferedImage;

import com.kk.code.handle.TwoDivCodeGenHandler;
import com.kk.code.model.ImgByteArray;
import com.kk.code.model.TemplateInfo;
import com.kk.code.model.TwoCodeException;
import com.kk.code.upload.UploadService;

/**
 * 二维码服务
 * 
 * TODO 增加缓存,上传文件
 * 
 * @author Administrator
 * 
 */
public class TwoDicCodeService {

	/** 生成二维码 */
	private TwoDivCodeGenHandler twoDivCodeGenHandler;

	/**
	 * 获取普通二维码图片url
	 * 
	 * @param url
	 * @return
	 * @throws TwoCodeException
	 */
	public String getQRCodeURL(String url) throws TwoCodeException {

		BufferedImage bufferedImage = twoDivCodeGenHandler.genQRCode(url);

		ImgByteArray imageBytes = twoDivCodeGenHandler
				.toByteArray(bufferedImage);
		UploadService.write(imageBytes);
		return "";
	}

	/**
	 * 获取普通二维码图片url，中间有小头像
	 * 
	 * @param url
	 * @param headUrl
	 * @return
	 * @throws TwoCodeException
	 */
	public String getQRCodeURL(String url, String headUrl)
			throws TwoCodeException {

		BufferedImage bufferedImage = twoDivCodeGenHandler.genQRCode(url);

		bufferedImage = twoDivCodeGenHandler.drawIcon(bufferedImage, headUrl);

		ImgByteArray imageBytes = twoDivCodeGenHandler
				.toByteArray(bufferedImage);
		UploadService.write(imageBytes);
		return "";
	}


	public void setTwoDivCodeGenHandler(
			TwoDivCodeGenHandler twoDivCodeGenHandler) {
		this.twoDivCodeGenHandler = twoDivCodeGenHandler;
	}

}
