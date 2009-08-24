/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

/**
 * Stores the KSBasE settings for one specific editor Class is serializable so
 * it can be stored in an external File
 * 
 * @author Michael Matzen
 * 
 */
public class EditorTransformationSettings implements Serializable {

    private static final long serialVersionUID = -7706521956908478893L;
    private String modelPackageClass; // The model package class
    private String menuName; // Name of the menu
    private String menuLocation; // Location of the menu
    private String toolbarLocation; // Location of the toolbar
    private int visibilityFlags; // Visiblity flags (see KSBasePlugin.java)
    private boolean performAutoLayout; // Run auto-layout after transformation
    private URI defaultIconURI; // Default icon for
                                // menu/toolbar/balloon/contextmenu entries
    private String editor; // Editor to which this setting is assigned
    private String extFile; // Xtend file in which the transformations are
                            // defined
    private LinkedList<Transformation> transformations; // The current List of
                                                        // Transformations

    /**
     * Creates a new transformation setting with the given editor
     * 
     * @param editor
     */
    public EditorTransformationSettings(String editor) {
        this.editor = editor;
        this.visibilityFlags = KSBasEPlugin.SHOW_MENU
                | KSBasEPlugin.SHOW_CONTEXT | KSBasEPlugin.SHOW_TOOLBAR
                | KSBasEPlugin.SHOW_BALLOON;
        this.modelPackageClass = ""; //$NON-NLS-1$
        this.menuName = Messages.EditorTransformationSettings_Default_Menu_Name;
        this.menuLocation = Messages.EditorTransformationSettings_Default_Menu_Location;
        this.toolbarLocation = Messages.EditorTransformationSettings_Default_Toolbar_Location;
        this.defaultIconURI = URI.create(""); //$NON-NLS-1$
        this.extFile = ""; //$NON-NLS-1$
        this.transformations = new LinkedList<Transformation>();
        this.performAutoLayout = true;
    }

    /**
     * Set the editor class
     * 
     * @param editor
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * Gets the assigned editor
     * 
     * @return
     */
    public String getEditor() {
        return editor;
    }

    /**
     * Gets the model URI
     * 
     * @return A string representation of the model Package
     */
    public String getModelPackageClass() {

        if (modelPackageClass == null)
            return ""; //$NON-NLS-1$
        else
            return modelPackageClass;
    }

    /**
     * Sets a model URI
     * 
     * @param modelURI
     */
    public void setModelPackageClass(String modelPackageClass) {
        this.modelPackageClass = modelPackageClass;
    }

    /**
     * Gets the menu name
     * 
     * @return Name of the menu
     */
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getVisibility() {
        return visibilityFlags;
    }

    public void setVisibilityFlags(int flags) {
        this.visibilityFlags = flags;
    }

    public boolean isShownInMenu() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_MENU) == KSBasEPlugin.SHOW_MENU;
    }

    public boolean isShownIToolbar() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_TOOLBAR) == KSBasEPlugin.SHOW_TOOLBAR;
    }

    public boolean isShownInContext() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_CONTEXT) == KSBasEPlugin.SHOW_CONTEXT;
    }

    public boolean isShownInBalloon() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_BALLOON) == KSBasEPlugin.SHOW_BALLOON;
    }

    public URI getDefaultIconURI() {
        return defaultIconURI;
    }

    public void setDefaultIconURI(URI defaultIconURI) {
        this.defaultIconURI = defaultIconURI;
    }

    public LinkedList<Transformation> getTransformations() {
        return transformations;
    }

    public void setTransformations(LinkedList<Transformation> transformations) {
        this.transformations = transformations;
    }

    public void addTransformation(Transformation t) {
        this.transformations.add(t);
    }

    public void removeTransformation(int index) {
        this.transformations.remove(index);
    }

    public void modifyTransformation(Transformation oldVal,
            Transformation newVal) {
        if (this.transformations.contains(oldVal))
            transformations.remove(oldVal);
        transformations.add(newVal);
    }

    public String getMenuLocation() {
        return menuLocation;
    }

    public void setMenuLocation(String menuLocation) {
        this.menuLocation = menuLocation;
    }

    public String getToolbarLocation() {
        return toolbarLocation;
    }

    public void setToolbarLocation(String toolbarLocation) {
        this.toolbarLocation = toolbarLocation;
    }

    public boolean isPerformAutoLayout() {
        return performAutoLayout;
    }

    public void setPerformAutoLayout(boolean performAutoLayout) {
        this.performAutoLayout = performAutoLayout;
    }

    public String getExtFile() {
        return extFile;
    }

    public void setExtFile(String extFile, boolean parseFile) {
        this.extFile = extFile;
        if (parseFile)
            parseTransformationsFromFile();
    }

    /**
     * Parses the given Xtend file and stores the in-place m2m transformations.
     * This parsing is based on the Xtend function names only! So renaming a
     * function in Xtend will reset the KSbasE transformation
     */
    public void parseTransformationsFromFile() {
        // extFile should not be null, only if we read an old config file
        if (extFile != null && extFile.length() > 0) {
            // Create path from fileName
            IPath path = new Path(extFile);
            // Create IFile from path
            IFile file = ResourcesPlugin.getWorkspace().getRoot()
                    .getFileForLocation(path);
            // If the file does not exist, we create an empty list
            if (file == null || !file.exists()) {
                this.transformations = new LinkedList<Transformation>();
                return;
            }
            try { // read Xtend functions
                LinkedList<Transformation> newTrans = new LinkedList<Transformation>();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(file.getContents()));
                while (reader.ready()) {
                    // Read one line ... TODO: Check if functions can be defined
                    // over multiple lines...
                    String in = reader.readLine();
                    // Ugly isn't it? Parsing xtend by hand, assuming we get a
                    // correct file
                    // Split only twice ! so we only split return types and
                    // function
                    // declarations but not the parameters
                    String[] token = in.split(" ", 2); //$NON-NLS-1$
                    if (token != null && token.length > 0) {
                        if (token[0].equalsIgnoreCase("import")) { //$NON-NLS-1$
                            // we don't need the import statement, cause it's
                            // Xtend only
                        } else if (token[0].equalsIgnoreCase("void")) { //$NON-NLS-1$
                            // Only parse 'Void' functions
                            String methodName = token[1].substring(0, token[1]
                                    .indexOf('('));
                            String[] params = token[1].substring(
                                    token[1].indexOf('(') + 1,
                                    token[1].indexOf(')')).split(","); //$NON-NLS-1$
                            Transformation tNew = new Transformation(
                                    methodName, methodName);
                            tNew.setNumSelections(params.length);
                            // If the parsed function is not contained in the
                            // list of existing transformations, we add it now
                            if (this.transformations.contains(tNew)) {
                                newTrans
                                        .add(this.transformations
                                                .get(this.transformations
                                                        .indexOf(tNew)));
                            } else {
                                // else we simply add a new default
                                // transformation
                                newTrans.add(tNew);
                            }
                        }
                    }
                }
                this.transformations = newTrans;
            } catch (CoreException e) {
                // ignore
            } catch (IOException e) {
                // ignore
            } catch (IndexOutOfBoundsException e) {
                // ignore
            }
        }
    }
}
