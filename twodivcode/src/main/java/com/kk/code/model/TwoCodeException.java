package com.kk.code.model;

public class TwoCodeException extends Exception {
	private int code;

	public TwoCodeException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
