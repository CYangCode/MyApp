package com.tc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FileTool {
	/**
	 * 将查询的结果导入到指定目录下的文件中
	 * 
	 * @param result
	 * @param path
	 */
	public static void convertResultToFile(ArrayList<HashMap<String, String>> result,
			String path) throws IOException {
		File file = new File(path);
		System.out.println(file.getPath());
		if (!file.exists())
			file.createNewFile();
		FileOutputStream out = null;
		out = new FileOutputStream(file, false);
		StringBuffer sb = new StringBuffer();
		for (HashMap<String, String> map : result) {
			Iterator<Entry<String, String>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> entry = (Entry<String, String>) iter
						.next();
				String val = entry.getValue();
				sb.append(val + "\t");
			}
			sb.append("\n");
		}
		out.write(sb.toString().getBytes("utf-8"));
		out.close();
		System.out.println("file finished!");
	}
}
