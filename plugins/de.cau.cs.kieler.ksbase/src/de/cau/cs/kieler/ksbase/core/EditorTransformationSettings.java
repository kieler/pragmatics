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
 * Stores the KSBasE settings for one specific editor. class is serializable so
 * it can be stored in an external File
 * 
 * @author Michael Matzen
 * 
 */
public class EditorTransformationSettings implements Serializable {

    private static final long serialVersionUID = -208150422554311871L;
    private String modelPackageClass; // The model package class
    private String menuName; // Name of the menu
    private String menuLocation; // Location of the menu
    private String toolbarLocation; // Location of the toolbar
    private int visibilityFlags; // Visiblity flags (see KSBasePlugin.java)
    private boolean performAutoLayout; // Run auto-layout after transformation
    private URI defaultIconURI; // Default icon for menu/toolbar/balloon/contextmenu entries
    private String editor; // Editor to which this setting is assigned
    private String extFile; // Xtend file in which the transformations are defined
    private String context; // The context for the diagram editor, required for key bindings
    private LinkedList<Transformation> transformations; // The current List of Transformations

    /**
     * Creates a new transformation setting with the given editor diagram class
     * 
     * @param editor The fqn of the diagram editor 
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
        this.context = "";
        this.transformations = new LinkedList<Transformation>();
        this.performAutoLayout = true;
    }

    /**
     * Set the editor class
     * 
     * @param editor The fqn of the diagram editor
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
     * Gets the model package class name
     * 
     * @return The fqn of the model package class
     */
    public String getModelPackageClass() {

        if (modelPackageClass == null)
            return ""; //$NON-NLS-1$
        else
            return modelPackageClass;
    }

    /**
     * Sets a model package class
     * 
     * @param modelClass
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

    /**
     * Sets the menu name
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * Returns the number representing the contributions visibility
     * @see KSBasEPlugin for visibility flags 
     * @return
     */
    public int getVisibility() {
        return visibilityFlags;
    }

    /**
     * Sets the visibility flags.
     * @see KSBasEPlugin  for valid flags 
     * @param flags
     */
    public void setVisibilityFlags(int flags) {
        this.visibilityFlags = flags;
    }

    /**
     * Menu visibility  
     * @return true if a menu should be visible 
     */
    public boolean isShownInMenu() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_MENU) == KSBasEPlugin.SHOW_MENU;
    }

    /**
     * Toolbar visibility 
     * @return true if a toolbar should be visible
     */
    public boolean isShownIToolbar() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_TOOLBAR) == KSBasEPlugin.SHOW_TOOLBAR;
    }

    /**
     * Context menu visibility
     * @return true if a context menu should be visible
     */
    public boolean isShownInContext() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_CONTEXT) == KSBasEPlugin.SHOW_CONTEXT;
    }

    /**
     * Balloon menu visibility
     * @return true if a balloon menu should be visible
     */
    public boolean isShownInBalloon() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_BALLOON) == KSBasEPlugin.SHOW_BALLOON;
    }

    /**
     * Returns the URI to the default icon
     * @return URI to the default icon, no checks are done if this is a valid URI
     */
    public URI getDefaultIconURI() {
        return defaultIconURI;
    }

    /**
     * Sets the URI to the default icon
     * @param defaultIconURI URI to the default icon
     */
    public void setDefaultIconURI(URI defaultIconURI) {
        this.defaultIconURI = defaultIconURI;
    }

    /**
     * Gets the list of defined transformations
     * @return A LinkedList containing all transformations 
     */
    public LinkedList<Transformation> getTransformations() {
        return transformations;
    }

    /**
     * Sets the transformation list
     * @param transformations A LinkedList containing the transformations
     */
    public void setTransformations(LinkedList<Transformation> transformations) {
        this.transformations = transformations;
    }

    /**
     * Adds a single transformation to the transformations list
     * @param t a transformation definition
     */
    public void addTransformation(Transformation t) {
        this.transformations.add(t);
    }

    /**
     * Removes a transformation
     * @param index the index of the element to remove
     */
    public void removeTransformation(int index) {
        this.transformations.remove(index);
    }

    /**
     * Replaces a transformation with a new one 
     * @param oldVal The transformation to replace
     * @param newVal The transformation to insert
     */
    public void modifyTransformation(Transformation oldVal,
            Transformation newVal) {
        if (this.transformations.contains(oldVal))
            transformations.remove(oldVal);
        transformations.add(newVal);
    }

    /**
     * Returns the locationURI of the menu 
     * @return a locationURI representing the position of the menu
     */
    public String getMenuLocation() {
        return menuLocation;
    }

    /**
     * Sets the locationURI of the menu
     * @param menuLocation a locationURI
     */
    public void setMenuLocation(String menuLocation) {
        this.menuLocation = menuLocation;
    }

    /**
     * Returns the locationURI of the toolbar
     * @return a locationURI representing the position of the toolbar
     */
    public String getToolbarLocation() {
        return toolbarLocation;
    }

    /**
     * Sets the locationURI of the toolbar
     * @param toolbarLocation a locationURI
     */
    public void setToolbarLocation(String toolbarLocation) {
        this.toolbarLocation = toolbarLocation;
    }

    /**
     * The auto layout setting
     * @return true if auto layout should be performed after execution
     */
    public boolean isPerformAutoLayout() {
        return performAutoLayout;
    }

    /**
     * Sets the auto layout setting
     * @param performAutoLayout
     */
    public void setPerformAutoLayout(boolean performAutoLayout) {
        this.performAutoLayout = performAutoLayout;
    }

    /**
     * Returns the text representation of the Xtend file
     * @return A .ext file in plain text
     */
    public String getExtFile() {
        return extFile;
    }

    /**
     * Sets the content of the Xtend file
     * @param file a .ext file in plain text
     */
    public void setExtFile(String file) {
        this.extFile = file;
    }

    /**
     * Returns the defined diagram context
     * @return A contextID used to bind keyboard shortcuts to commands 
     */
    public String getContext() {
        return context;
    }

    /**
     * Sets the diagram context
     * @param context the contextID to bind keyboard shortcuts
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Parses the given Xtend file and stores the in-place m2m transformations.
     * This parsing is based on the Xtend function names only! So renaming a
     * function in Xtend will reset the KSbasE transformation
     */
    public void parseTransformationsFromFile(String file) {
        // extFile should not be null, only if we read old settings
        if (file != null && file.length() > 0) {
            // Create path from fileName
            IPath path = new Path(file);
            // Create IFile from path
            IFile xtfile = ResourcesPlugin.getWorkspace().getRoot()
                    .getFileForLocation(path);
            // If the file does not exist, we create an empty list
            if (xtfile == null || !xtfile.exists()) {
                this.transformations = new LinkedList<Transformation>();
                return;
            }
            try { // read Xtend functions
                this.extFile = ""; // clear old transformation file
                LinkedList<Transformation> newTrans = new LinkedList<Transformation>();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(xtfile.getContents()));

                while (reader.ready()) {
                    // Read one line ... TODO: Check if functions can be defined
                    // over multiple lines...
                    String in = reader.readLine();
                    // Store in extFile as String, and append the deleted
                    // newline
                    // so the file remains human readable
                    extFile += in + "\r\n";

                    // Ugly isn't it? Parsing Xtend by hand, assuming we get a
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
                reader.close();
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
