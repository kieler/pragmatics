package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.cau.cs.kieler.core.KielerException;

public class IOHandler {

	public static void writeFile(File sourceFile, File destFile)
			throws IOException {
		// TODO is directory geht nur auf absolute pfade.
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

	/**
	 * searches for a file namend
	 * 
	 * @param location
	 * @return
	 * @throws KielerException
	 */
	public static File filterFile(final File location, final String searchObject)
			throws KielerException {
		// TODO check if File parent = new File(location.getPath) besser ist...
		File parent = location;
		File[] listFiles = null;
		while (parent != null && parent.exists() && parent.isDirectory()) {
			listFiles = parent.listFiles(new FileFilter() {

				public boolean accept(File pathname) {
					if (searchObject.equals(pathname.getName()))
						return true;
					return false;
				}
			});
			if (listFiles.length > 0)
				break;
			parent = parent.getParentFile();
		}
		if (listFiles.length == 0)
			throw new KielerException(
					"The given destination location is not an plugin project and not a containing folder.");
		if (listFiles.length > 1)
			throw new KielerException(new StringBuffer()
					.append("There are more than one " + searchObject
							+ "  files in ").append(parent.getPath())
					.toString());
		return listFiles[0];
	}

}
