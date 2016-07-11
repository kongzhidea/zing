package com.kk.code.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kk.code.model.TwoCodeException;
import com.kk.code.service.TwoDicCodeService;

public class Test {
	public static void main(String[] args) throws TwoCodeException {
//		getQRCodeURL();
		getQRCodeURL2();
//		getVQRCodeURL();
	}

	private static void getVQRCodeURL() throws TwoCodeException {
		String url = "http://www.baidu.com";
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-core.xml");
		TwoDicCodeService codeService = context
				.getBean(TwoDicCodeService.class);

	}

	private static void getQRCodeURL() throws TwoCodeException {
		String url = "http://open.renren.com";
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-core.xml");
		TwoDicCodeService codeService = context
				.getBean(TwoDicCodeService.class);

		System.out.println(codeService.getQRCodeURL(url));
	}

	private static void getQRCodeURL2() throws TwoCodeException {
		String url = "http://dwz.cn/1i9IDO";
		String headUrl = "http://ebydata.oss-cn-beijing.aliyuncs.com/data/images/act/2015-08-14/elogo.png";
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-core.xml");
		TwoDicCodeService codeService = context
				.getBean(TwoDicCodeService.class);

		System.out.println(codeService.getQRCodeURL(url, headUrl));
	}
}
