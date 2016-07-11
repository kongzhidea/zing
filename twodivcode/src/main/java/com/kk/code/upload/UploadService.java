package com.kk.code.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kk.code.model.ImgByteArray;

public class UploadService {

	public static void write(ImgByteArray imageBytes) {
		File file = new File("d:/data/output/" + imageBytes.getOriginalFilename());
		OutputStream os;
		try {
			os = new FileOutputStream(file);
			os.write(imageBytes.getBytes());
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
