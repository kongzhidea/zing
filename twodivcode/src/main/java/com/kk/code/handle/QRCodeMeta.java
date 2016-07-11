package com.kk.code.handle;

import java.awt.Color;

public interface QRCodeMeta {

	int QR_SIZE = 400;

	String IMAGE_TYPE = "jpg";
	String IMAGE_GIF = "gif";

	int ICON_WIDTH = 100;

	int ICON_HEIGHT = 100;

	int PAD_SIZE = 4;

	Color PAD_COLOR = Color.WHITE;

	// 衍生数据
	int ICON_LEFT = (QR_SIZE - ICON_WIDTH) / 2;

	int ICON_TOP = (QR_SIZE - ICON_HEIGHT) / 2;

	int PAD_LEFT = ICON_LEFT - PAD_SIZE;

	int PAD_TOP = ICON_TOP - PAD_SIZE;

	int PAD_WIDTH = ICON_WIDTH + 2 * PAD_SIZE;

	int PAD_HIGHT = ICON_HEIGHT + 2 * PAD_SIZE;

}