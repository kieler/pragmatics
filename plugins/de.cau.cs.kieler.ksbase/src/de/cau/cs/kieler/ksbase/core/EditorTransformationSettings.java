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
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

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
    private boolean performAutoLayout; // Run auto-layout after transformation
    private String defaultIcon; // Default icon for menu/tool bar/balloon/popup menu entries
    private String editor; // Editor to which this setting is assigned
    private String extFile; // Xtend file in which the transformations are defined
    private String context; // The context for the diagram editor, required for key bindings
    private LinkedList<Transformation> transformations; // The current List of Transformations
    private LinkedList<KSBasEMenuContribution> menuContributions; //List of menu contributions
    
    //for internal uses
    private String contributor; //The contributor which contains the extension points
    /**
     * Creates a new transformation setting with the given editor diagram class
     * 
     * @param editor The fqn of the diagram editor 
     */
    public EditorTransformationSettings(String editor) {
        this.editor = editor;
        this.modelPackageClass = ""; //$NON-NLS-1$
        this.defaultIcon = "";
        this.extFile = ""; //$NON-NLS-1$
        this.context = "";
        this.transformations = new LinkedList<Transformation>();
        this.menuContributions = new LinkedList<KSBasEMenuContribution>();
        this.performAutoLayout = true;
        this.contributor = "";
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
        public LinkedList<KSBasEMenuContribution> getMenuContributions() {
        return menuContributions;
    }

    public void setMenuContributions(
            LinkedList<KSBasEMenuContribution> menuContributions) {
        this.menuContributions = menuContributions;
    }
    
    public void addMenuContribution(KSBasEMenuContribution contrib) {
        this.menuContributions.add(contrib);
    }

    /**
     * Returns the URI to the default icon
     * @return URI to the default icon, no checks are done if this is a valid URI
     */
    public String getDefaultIcon() {
        return defaultIcon;
    }

    /**
     * Sets the URI to the default icon
     * @param defaultIconURI URI to the default icon
     */
    public void setDefaultIcon(String icon) {
        this.defaultIcon= icon;
    }

    /**
     * Gets the list of defined transformations
     * @return A LinkedList containing all transformations 
     */
    public LinkedList<Transformation> getTransformations() {
        return transformations;
    }
    
    /**
     * Tries to find a transformation with a given name
     * @param transformation
     * @return
     */
    public Transformation getTransformationByName(String transformation) {
        for ( Transformation t : transformations) {
            if ( t.getTransformationName().equals(transformation))
                return t;
        }
        return null;
    }
    
    public Transformation getTransformationById(String tid) {
        for ( Transformation t : transformations) {
            if ( t.getTransformationID().equals(tid))
                return t;
        }
        return null;
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

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
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
            BufferedReader reader = null;
            try { // read Xtend functions
                this.extFile = ""; // clear old transformation file
                LinkedList<Transformation> newTrans = new LinkedList<Transformation>();
                 reader = new BufferedReader(
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
            finally {
            	try {
					if (reader != null )
						reader.close();
				} catch (IOException e) {
				}
            }
        }
    }
}
