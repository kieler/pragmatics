package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOHandler {

	public static void writeFile(File sourceFile, File destFile)
			throws IOException {
		if (sourceFile.isDirectory()) {
			destFile.mkdir();
		} else {
			InputStream is = new FileInputStream(sourceFile);
			OutputStream os = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int len;
			while ((len = is.read(buf)) > 0) {
				os.write(buf, 0, len);
			}
			is.close();
			os.close();
		}
	}

	public static void deleteFile(File deletable) {
		deletable.delete();
	}

}
