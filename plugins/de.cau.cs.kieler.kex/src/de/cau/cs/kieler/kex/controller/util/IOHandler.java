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
import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.kex.controller.ErrorMessage;

/**
 * This class provides all methods to access I/O.
 * 
 * @author pkl
 * 
 */
public class IOHandler {

    public static final String PROJECT_FILE = ".project";
    public static final String MANIFEST_MF = "MANIFEST.MF";
    public static final String PLUGIN_XML = "plugin.xml";
    public static final int BUFFER_SIZE = 1024;

    public static void writeResource(final File sourceFile, final File destFile) throws IOException {
        if (!sourceFile.exists()) {
            // FIXME Problem, if user imports a project from another locations
            // as it's workspace.
            // the sourcepath is generated from workspace location and local
            // path. BANG!
            throw new IOException(ErrorMessage.NO_SOURCE_FILE + sourceFile.getPath());
        }

        // is directory works only on absolute paths.
        if (sourceFile.isDirectory()) {
            destFile.mkdir();
        } else {
            InputStream is = new FileInputStream(sourceFile);
            OutputStream os = new FileOutputStream(destFile);
            byte[] buf = new byte[IOHandler.BUFFER_SIZE];
            int len;
            while ((len = is.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
            is.close();
            os.close();
        }
    }

    /**
     * deletes a file or a directory. If a directory is choosen, all subdirectories and files will
     * be deleted.
     * 
     * @param deletable
     *            , File
     * @return true, if deleting success, otherwise false.
     */
    public static boolean deleteFile(File deletable) {
        if (!deletable.exists()) {
            return true;
        }
        if (deletable.isDirectory()) {
            String[] entries = deletable.list();
            for (int i = 0; i < entries.length; i++) {
                deleteFile(new File(deletable.getPath(), entries[i]));
            }
        }
        return deletable.delete();
    }

    /**
     * 
     * filters the plugin.xml of plugin project for given destination.<br>
     * Searches first for a java project by checking parent dirs for containing ".project" file. <br>
     * Searches than in found project for "manifest.mf" file of an plugin project and if that found,
     * finally searches the plugin.xml.
     * 
     * @param location
     *            , folder in an plugin project.
     * @return plugin.xml if found otherwise parent java project directory
     * @throws KielerException
     */
    public static File filterPluginXML(final File location) throws KielerException {

        // TODO hier ansetzen project rausholen lokale rootresource filtern und
        // bei example einsetzen sowie beim plugin schreiben berücksichtigen,
        // bei resources und bei example attribute rootresource
        File childDir = searchUP(location, IOHandler.PROJECT_FILE);
        if (childDir == null) {
            throw new KielerException("Could not find any java project.");
        }

        File project = childDir.getParentFile();
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
            throw new KielerException("The choosen destination contains no manifest.mf.");
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
    private static File getFile(File sourceDir, final String fileName) throws KielerException {
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
    private static void collectFiles(File sourceDir, final String fileName, List<File> resultList)
            throws KielerException {
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
     * Searches for an file with matching fileName in parent folders of source directory.
     * 
     * @param sourceDir
     * @param fileName
     * @return File, if exactly one file is found otherwise null;
     * @throws KielerException
     */
    public static File searchUP(File sourceDir, final String fileName) throws KielerException {
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

    private static File filterFoundFile(List<File> foundFiles, String searchName, File source)
            throws KielerException {
        int fileCount = foundFiles.size();
        if (fileCount == 0)
            return null;
        if (fileCount > 1)
            throw new KielerException(new StringBuffer()
                    .append("There are more than one file with name \"" + source + "\" in")
                    .append(source.getPath()).toString());
        return foundFiles.get(0);
    }

    private static File filterFoundFile(File[] foundFiles, String searchName, File source)
            throws KielerException {
        int fileCount = foundFiles.length;
        if (fileCount == 0)
            return null;
        if (fileCount > 1)
            throw new KielerException(new StringBuffer()
                    .append("There are more than one file with name \"" + source + "\" in")
                    .append(source.getPath()).toString());
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
     * @throws KielerModelException
     */
    public static void writeFile(final URL sourceUrl, final String destPath,
            final boolean checkDuplicate) throws IOException, KielerModelException {
        File target = new File(destPath);
        if (checkDuplicate && target.exists()) {
            throw new KielerModelException(ErrorMessage.DUPLICATE_EXAMPLE, target.getName());
        }
        InputStream is = sourceUrl.openStream();
        OutputStream os = new FileOutputStream(target, false);
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
        new File(destFolder).mkdir();
    }
}
