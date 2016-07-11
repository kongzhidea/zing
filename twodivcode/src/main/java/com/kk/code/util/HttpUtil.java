package com.kk.code.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpUtil {
	public static Log logger = LogFactory.getLog(HttpUtil.class);

	public static byte[] getContent(String url) {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);

		int timeout = 4000;
		httpClient.getParams().setParameter("http.connection.timeout", timeout);
		httpClient.getParams().setParameter("http.socket.timeout", timeout);

		getMethod
				.setRequestHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 5.1; rv:22.0) Gecko/20100101 Firefox/22.0");
		getMethod.setRequestHeader("Cache-Control", "max-age=0");
		getMethod
				.setRequestHeader("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		getMethod.setRequestHeader("Accept-Language",
				"zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		getMethod.setRequestHeader("Accept-Charset",
				"zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(getMethod);
		} catch (HttpException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
		// 301或者302
		byte[] responseBody = null;
		if (statusCode == HttpStatus.SC_OK) {
			// 从头中取出转向的地址
			try {
				responseBody = getMethod.getResponseBody();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			} finally {
				// 释放连接
				getMethod.releaseConnection();
			}
		}
		return responseBody;
	}
}
