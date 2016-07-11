package com.kk.code.model;

public class TemplateInfo {
	private int animId;
	private String name;
	private String smallUrl;
	private String largeUrl;

	public int getAnimId() {
		return animId;
	}

	public void setAnimId(int animId) {
		this.animId = animId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	public String getLargeUrl() {
		return largeUrl;
	}

	public void setLargeUrl(String largeUrl) {
		this.largeUrl = largeUrl;
	}

	@Override
	public String toString() {
		return "TemplateInfo [animId=" + animId + ", name=" + name
				+ ", smallUrl=" + smallUrl + ", largeUrl=" + largeUrl + "]";
	}

}
