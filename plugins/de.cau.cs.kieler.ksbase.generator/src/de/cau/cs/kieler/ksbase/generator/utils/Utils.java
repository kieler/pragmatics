package de.cau.cs.kieler.ksbase.generator.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.eclipse.core.resources.ResourcesPlugin;

import de.cau.cs.kieler.ksbase.featureDefinition.FeatureType;

public class Utils {

    public static String projectName;
    public static String targetProjectName;
    public static String sourceFilePath;

    public static String getTargetPackageName() {
        System.out.println("TargetPackageName: " + targetProjectName);
        return targetProjectName;
    }

    public static String getTargetPackageFile() {
        System.out.println("TargetPackageFile: "
                + targetProjectName.replace(".", "/"));
        return targetProjectName.replace(".", "/");
    }

    public static final void GetNumParams(FeatureType type) {
        if (type.getNumParameter().size() == 0)
            type.getNumParameter().add(type.getParameter().size());
    }

    public static void copyFeatureFiles(String source, String target) {
        if (source != null && target != null) {
            // TODO: Create folder only once !
            String outFolder = ResourcesPlugin.getWorkspace().getRoot()
                    .getLocation().toOSString();
            outFolder += "/" + Utils.targetProjectName + "/src/"
                    + targetProjectName.replace(".", "/") + "/transformations";

            File targetFolder = new File(outFolder);
            String tmp = outFolder.substring(0,outFolder.lastIndexOf("/"));
            File tmpFolder = new File(tmp);
            System.out.println( tmp + " => " + tmpFolder.exists() );
            if (!targetFolder.exists()) {
                if (!(targetFolder.mkdir())) {
                    System.out.println("failed to create ouput folder ("+outFolder+")");
                }
            }

            String inFolder = ResourcesPlugin.getWorkspace().getRoot()
                    .getLocation().toOSString();
            String sourceProject = Utils.sourceFilePath.substring(0,
                    Utils.sourceFilePath.lastIndexOf("/"));
            inFolder += sourceProject;
            File inputFile = new File(inFolder + "/" + source);
            File outputFile = new File(outFolder + "/" + source);
            if (!outputFile.exists()) {
                try {
                    FileReader in = new FileReader(inputFile);
                    FileWriter out = new FileWriter(outputFile);
                    int c;

                    while ((c = in.read()) != -1)
                        out.write(c);

                    in.close();
                    out.close();
                } catch (Exception e) {

                    System.out.println("Copy failed:" + e.getMessage());
                }
            }
        }
    }

    public static void setTargetPackageName(String name) {
        // Utils.targetProjectName = name;
    }

    public static String getCurrentProjectName() {
        System.out.println("Project-Name : " + Utils.projectName);
        return Utils.projectName;
    }

    public static String getTargetProjectName() {
        System.out.println("Target Project-Name");
        return Utils.targetProjectName;
    }
}
