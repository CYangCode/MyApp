package com.tc.service.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {
	public static String readIntStream(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		try {
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] result = baos.toByteArray();
		String temp = new String(result);
		return temp;
	}
}
