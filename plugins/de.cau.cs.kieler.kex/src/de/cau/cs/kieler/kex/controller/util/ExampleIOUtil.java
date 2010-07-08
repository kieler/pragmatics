package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import de.cau.cs.kieler.core.KielerException;

public class ExampleIOUtil {

	/**
	 * 
	 * @param sourceUrl
	 *            , source url
	 * @param destFile
	 *            , destination file
	 * @param overwrite
	 *            , boolean
	 * @throws KielerException
	 * @throws IOException
	 */
	public static void writeFile(URL sourceUrl, String destFile,
			boolean overwrite) throws IOException {
		File f2 = new File(destFile);
		if (f2.exists() && !overwrite) {
			return;
		}

		InputStream is = sourceUrl.openStream();
		OutputStream os = new FileOutputStream(f2, overwrite);
		byte[] buf = new byte[1024];
		int len;
		while ((len = is.read(buf)) > 0) {
			os.write(buf, 0, len);
		}

		is.close();
		os.close();
	}

	/**
	 * creates a folder with given parameter.
	 * 
	 * @param destFolder
	 *            , pathname of destination folder.
	 */
	public static void createFolder(String destFolder) {
		(new File(destFolder)).mkdir();
	}

	public static boolean isFolder(String path) {
		return (new File(path)).isDirectory();
	}

}
