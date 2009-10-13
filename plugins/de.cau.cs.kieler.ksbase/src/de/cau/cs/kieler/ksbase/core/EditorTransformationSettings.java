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

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Stores the KSBasE settings for one specific editor. class is serializable so
 * it can be stored in an external File.
 * 
 * @author Michael Matzen
 * 
 */
public class EditorTransformationSettings implements Serializable {

	private static final long serialVersionUID = -208150422554311871L;
	private String modelPackageClass; // The model package class
	private boolean performAutoLayout; // Run auto-layout after transformation
	private String defaultIcon; // Default icon for menu/tool bar/balloon/popup
	// menu entries

	private String editor; // Editor to which this setting is assigned

	private String extFile; // Xtend file in which the transformations are
	// defined

	private String context; // The context for the diagram editor, required for
	// key bindings

	private LinkedList<Transformation> transformations; // The current List of
	// Transformations

	private LinkedList<KSBasEMenuContribution> menuContributions;
	// List of menu contributions
	private String contributor; // The contributor which contains

	// the extension points

	/**
	 * Creates a new transformation setting with the given editor diagram class.
	 * 
	 * @param editor
	 *            The fqn of the diagram editor
	 */
	public EditorTransformationSettings(final String editor) {
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
	 * Set the editor class.
	 * 
	 * @param editor
	 *            The fqn of the diagram editor
	 */
	final public void setEditor(final String editor) {
		this.editor = editor;
	}

	/**
	 * Gets the assigned editor.
	 * 
	 * @return The fully qualified editor name
	 */
	final public String getEditor() {
		return editor;
	}

	/**
	 * Gets the model package class name.
	 * 
	 * @return The fqn of the model package class
	 */
	final public String getModelPackageClass() {

		if (modelPackageClass == null) {
			return ""; //$NON-NLS-1$
		} else
			return modelPackageClass;
	}

	/**
	 * Sets a model package class.
	 * 
	 * @param modelPackageClass
	 *            The package class
	 */
	final public void setModelPackageClass(final String modelPackageClass) {
		this.modelPackageClass = modelPackageClass;
	}

	/**
	 * @return The list of menu contributions for this editor
	 */
	final public LinkedList<KSBasEMenuContribution> getMenuContributions() {
		return menuContributions;
	}

	/**
	 * Sets the menu contributions for this editor and removes any existing
	 * contributions.
	 * 
	 * @param menuContributions
	 */
	final public void setMenuContributions(
			final LinkedList<KSBasEMenuContribution> menuContributions) {
		this.menuContributions.clear();
		this.menuContributions.addAll(menuContributions);
	}

	/**
	 * Adds a menu contribution to this editor.
	 * 
	 * @param contrib
	 */
	final public void addMenuContribution(final KSBasEMenuContribution contrib) {
		this.menuContributions.add(contrib);
	}

	/**
	 * Returns the path to the default icon.
	 * 
	 * @return Path to the default icon
	 */
	final public String getDefaultIcon() {
		return defaultIcon;
	}

	/**
	 * Sets the path to the default icon.
	 * 
	 * @param icon
	 *            Icon path
	 */
	final public void setDefaultIcon(final String icon) {
		this.defaultIcon = icon;
	}

	/**
	 * Gets the list of defined transformations.
	 * 
	 * @return A LinkedList containing all transformations
	 */
	final public LinkedList<Transformation> getTransformations() {
		return transformations;
	}

	/**
	 * Tries to find a transformation with a given name.
	 * 
	 * @param transformation
	 *            The name to find
	 * @return The first transformation found or null
	 */
	final public Transformation getTransformationByName(
			final String transformation) {
		for (Transformation t : transformations) {
			if (t.getTransformationName().toLowerCase(Locale.getDefault())
					.equals(transformation.toLowerCase(Locale.getDefault()))) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Tries to find a transformation by its identity string.
	 * 
	 * @param tid
	 *            The id to find
	 * @return The first transformation with the given id or null if no
	 *         transformation has been found
	 */
	final public Transformation getTransformationById(final String tid) {
		for (Transformation t : transformations) {
			if (t.getTransformationId().equals(tid)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Sets the transformation list.
	 * 
	 * @param transformations
	 *            A LinkedList containing the transformations
	 */
	final public void setTransformations(
			final LinkedList<Transformation> transformations) {
		this.transformations.clear();
		this.transformations.addAll(transformations);
	}

	/**
	 * Adds a single transformation to the transformations list.
	 * 
	 * @param t
	 *            a transformation definition
	 */
	final public void addTransformation(final Transformation t) {
		this.transformations.add(t);
	}

	/**
	 * Removes a transformation.
	 * 
	 * @param index
	 *            the index of the element to remove
	 */
	final public void removeTransformation(final int index) {
		this.transformations.remove(index);
	}

	/**
	 * Replaces a transformation with a new one.
	 * 
	 * @param oldVal
	 *            The transformation to replace
	 * @param newVal
	 *            The transformation to insert
	 */
	final public void modifyTransformation(final Transformation oldVal,
			final Transformation newVal) {
		if (this.transformations.contains(oldVal)) {
			transformations.remove(oldVal);
		}
		transformations.add(newVal);
	}

	/**
	 * The auto layout setting.
	 * 
	 * @return true if auto layout should be performed after execution
	 */
	final public boolean isPerformAutoLayout() {
		return performAutoLayout;
	}

	/**
	 * Sets the auto layout setting.
	 * 
	 * @param performAutoLayout
	 */
	final public void setPerformAutoLayout(final boolean performAutoLayout) {
		this.performAutoLayout = performAutoLayout;
	}

	/**
	 * Returns the text representation of the Xtend file.
	 * 
	 * @return A .ext file in plain text
	 */
	final public String getExtFile() {
		return extFile;
	}

	/**
	 * Sets the content of the Xtend file.
	 * 
	 * @param file
	 *            a .ext file in plain text
	 */
	final public void setExtFile(final String file) {
		this.extFile = file;
		parseTransformations();
	}

	/**
	 * @return The editors contributor project.
	 */
	final public String getContributor() {
		return contributor;
	}

	/**
	 * Sets the editors contributor project.
	 * 
	 * @param contributor
	 */
	final public void setContributor(final String contributor) {
		this.contributor = contributor;
	}

	/**
	 * Returns the defined diagram context.
	 * 
	 * @return A contextID used to bind keyboard shortcuts to commands
	 */
	final public String getContext() {
		return context;
	}

	/**
	 * Sets the diagram context.
	 * 
	 * @param context
	 *            the contextID to bind keyboard shortcuts
	 */
	final public void setContext(final String context) {
		this.context = context;
	}

	/**
	 * Parses the Xtend file to read transformation names & parameters.
	 */
	final public void parseTransformations() {
		if (extFile != null && extFile.length() > 0) {
			// Let's find all in-place m2m transformations, defined in this file
			// They are defined by :
			// Starting with 'void '
			// End with ':'
			String[] methods = extFile.split(";");
			for (String m : methods) {
				try {
					m = m.trim().replaceAll("//.*\n", "");
					String method = m.toLowerCase(); // just to be sure
					if (!method.contains("void")) { // we only want void methods
						continue;
					}
					// We have to eliminate random occurrences of the 'void'
					// keyword, e.g. in comments
					String[] voidMethod = method.split(" ");
					if (voidMethod.length == 0
							|| !voidMethod[0].trim().equals("void")) {
						continue;
					}

					// method = method.replaceAll("/\\*.*\\", "");
					String[] tokens = method.split("void ");
					String[] head = tokens[1].split(":");
					String[] parts = head[0].split("\\(");
					String name = parts[0];
					String param = parts[1].replace(")", "");
					String[] parameterAndNames = param.split(",");
					String[] parameters = new String[parameterAndNames.length];
					for (int i = 0; i < parameterAndNames.length; ++i) {
						parameters[i] = parameterAndNames[i].trim().split(" ")[0];
					}
					Transformation t = getTransformationByName(name);
					if (t != null) {
						getTransformationByName(name).setParameter(parameters);
					}
				} catch (NullPointerException exp) {
					System.err.println("invalid xtend file");
				}
			}
		}
	}
}
