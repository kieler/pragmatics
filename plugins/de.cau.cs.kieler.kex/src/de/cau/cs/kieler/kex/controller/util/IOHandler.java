package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.core.KielerException;

public class IOHandler {

	public static String PROJECT_FILE = ".project";
	public static String MANIFEST_MF = "MANIFEST.MF";
	public static final String PLUGIN_XML = "plugin.xml";

	public static void writeFile(File sourceFile, File destFile)
			throws IOException {
		// TODO is directory geht nur auf absolute pfade.
		if (!sourceFile.exists()) {
			throw new IOException("Source file for does not exist for path: "
					+ sourceFile.getPath());
		}
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
	 * 
	 * filters the plugin.xml of plugin project for given destination.<br>
	 * Searches first for a java project by checking parent dirs for containing
	 * ".project" file. <br>
	 * Searches than in found project for the "manifest.mf" file of an plugin
	 * project and if that found, finally searches the plugin.xml.
	 * 
	 * @param location
	 *            , folder in an plugin project.
	 * @return plugin.xml if found otherwise parent java project directory
	 * @throws KielerException
	 */
	public static File filterPluginXML(final File location)
			throws KielerException {

		File project = searchUP(location, IOHandler.PROJECT_FILE)
				.getParentFile();
		if (project == null) {
			throw new KielerException("Could not find any java project.");
		}
		if (getFile(project, IOHandler.MANIFEST_MF) != null) {
			File result = getFile(project, IOHandler.PLUGIN_XML);
			if (result != null)
				return result;
			else
				return project;
		} else {
			throw new KielerException(
					"The choosen destination contains no manifest.mf.");
		}

	}

	/**
	 * Searches for given fileName in given directory and subdirectories.
	 * 
	 * @param sourceDir
	 * @param fileName
	 * @return true, if exactly one file is found, otherwise false
	 * @throws KielerException
	 */
	private static File getFile(File sourceDir, final String fileName)
			throws KielerException {
		List<File> resultList = new ArrayList<File>();
		collectFiles(sourceDir, fileName, resultList);
		return filterFoundFile(resultList, fileName, sourceDir);

	}

	/**
	 * adds a file with machting filename to given result list.
	 * 
	 * @param sourceDir
	 * @param fileName
	 * @param resultList
	 * @throws KielerException
	 */
	private static void collectFiles(File sourceDir, final String fileName,
			List<File> resultList) throws KielerException {
		for (File file : sourceDir.listFiles()) {
			if (fileName.equals(file.getName())) {
				resultList.add(file);
			}
			if (file.isDirectory()) {
				collectFiles(file, fileName, resultList);
			}
		}
	}

	/**
	 * Searches for an file with matching fileName in parent folders of source
	 * directory.
	 * 
	 * @param sourceDir
	 * @param fileName
	 * @return File, if exactly one file is found otherwise null;
	 * @throws KielerException
	 */
	private static File searchUP(File sourceDir, final String fileName)
			throws KielerException {
		File parent = sourceDir;
		File[] foundFiles = null;
		while (parent != null && parent.exists() && parent.isDirectory()) {
			foundFiles = parent.listFiles(new FilenameFilter() {

				public boolean accept(File dir, String name) {
					return fileName.equals(name);
				}
			});
			if (foundFiles.length > 0)
				break;
			parent = parent.getParentFile();
		}
		return filterFoundFile(foundFiles, fileName, parent);

	}

	private static File filterFoundFile(List<File> foundFiles,
			String searchName, File source) throws KielerException {
		int fileCount = foundFiles.size();
		if (fileCount == 0)
			return null;
		if (fileCount > 1)
			throw new KielerException(new StringBuffer().append(
					"There are more than one file with name \"" + source
							+ "\" in").append(source.getPath()).toString());
		return foundFiles.get(0);
	}

	private static File filterFoundFile(File[] foundFiles, String searchName,
			File source) throws KielerException {
		int fileCount = foundFiles.length;
		if (fileCount == 0)
			return null;
		if (fileCount > 1)
			throw new KielerException(new StringBuffer().append(
					"There are more than one file with name \"" + source
							+ "\" in").append(source.getPath()).toString());
		return foundFiles[0];

	}

	/**
	 * 
	 * @param sourceUrl
	 *            , source URL
	 * @param destPath
	 *            , destination path as String
	 * @param overwrite
	 *            , boolean
	 * @throws KielerException
	 * @throws IOException
	 */
	// TODO in IOHandler auslagern...
	public static void writeFile(final URL sourceUrl, final String destPath,
			final boolean overwrite) throws IOException {
		File f2 = new File(destPath);

		// TODO immer auf override setzen, muss �ber methode geregelt werden
		// nicht �ber konstr. glaube ich.
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
	public static void createFolder(final String destFolder) {
		(new File(destFolder)).mkdir();
	}
}
