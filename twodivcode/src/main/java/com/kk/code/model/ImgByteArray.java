package com.kk.code.model;

//--------------------- Change Logs----------------------
// <p>@author peng.jiang1 Initial Created at 2013-12-23<p>
//-------------------------------------------------------
public class ImgByteArray {

	private byte[] bytes;

	private String imgFmt;

	private String originalFilename;

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes
	 *            the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the imgFmt
	 */
	public String getImgFmt() {
		return imgFmt;
	}

	/**
	 * @param imgFmt
	 *            the imgFmt to set
	 */
	public void setImgFmt(String imgFmt) {
		this.imgFmt = imgFmt;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
}
