package com.kk.barcode;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

/**
 * 条形码
 * 
 * @author zhihui.kong
 * 
 */
public class BarcodeUtil {

	public static File saveToFile(String code) {
		try {
			JBarcode localJBarcode = new JBarcode(Code128Encoder.getInstance(),
					WidthCodedPainter.getInstance(),
					BaseLineTextPainter.getInstance());
			BufferedImage localBufferedImage = localJBarcode
					.createBarcode(code);

			return saveToFile(localBufferedImage, code + ".gif", "gif");
		} catch (InvalidAtributeException ignored) {
			return null;
		}
	}

	private static File saveToFile(BufferedImage paramBufferedImage,
			String fileName, String fileExt) {
		try {
			String path = "/tmp/barcode";
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				path = "d:\\kk\\barcode";
			}
			File parentFile = new File(path);
			if (!parentFile.exists()) {
				parentFile.mkdir();
			}
			File file = new File(path + File.separator + fileName);
			if (file.exists()) {
				return file;
			} else {
				file.createNewFile();
			}
			FileOutputStream localFileOutputStream = new FileOutputStream(file);
			ImageUtil.encodeAndWrite(paramBufferedImage, fileExt,
					localFileOutputStream, 80, 40);
			localFileOutputStream.close();
			return file;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		File file = saveToFile("aeebcde");
	}
}
