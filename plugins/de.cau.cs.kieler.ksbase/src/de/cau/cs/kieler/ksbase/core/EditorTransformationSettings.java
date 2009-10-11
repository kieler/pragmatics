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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

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
    private HashMap<String, String[]> transformationParameters; //List of parameters for all defined methods
    private HashMap<String,String[]> xtendDiagramParmeterMapping; //Mapping of Xtend parameters to GMF Diagram Objects
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
        this.transformationParameters = new HashMap<String, String[]>();
        this.xtendDiagramParmeterMapping = new HashMap<String, String[]>();
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
        parseTransformations();
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
     * Parses the Xtend file to read transformation names & parameters
     */
    public void parseTransformations() {
        if (extFile != null && extFile.length() > 0) {
        	//Let's find all in-place m2m transformations, defined in this file
        	//They are defined by :
        	//Starting with 'void '
        	//End with ':'
        	String[] methods = extFile.split(";");
        	for (String m : methods) {
        		try {
        		String method = m.toLowerCase(); //just to be sure
        		if (!method.contains("void")) { //we only want void methods
        			continue;
        		}
        		//We have to eliminate random occurrences of the 'void' 
        		//keyword, e.g. in comments
        		String[] voidMethod = method.split(" ");
        		if (voidMethod.length == 0 || !voidMethod[0].trim().equals("void"))
        			continue;
        		
        		method = method.trim().replaceAll("//.*\n", "");
        		//method = method.replaceAll("/\\*.*\\", "");
        		String[] tokens = method.split("void ");
        		String[] head = tokens[1].split(":");
        		String[] parts = head[0].split("\\(");
        		String name = parts[0];
        		String param = parts[1].replace(")", "");
        		String[] parameterAndNames = param.split(",");
        		String[] parameters = new String[parameterAndNames.length];
        		for ( int i = 0; i < parameterAndNames.length; ++i) {
        			parameters[i] = parameterAndNames[i].split(" ")[1];
        		}
        		transformationParameters.put(name, parameters);
        		}
        		catch (NullPointerException exp)
        		{
        			System.err.println("invalid xtend file");
        		}
        	}
        }
    }
}
