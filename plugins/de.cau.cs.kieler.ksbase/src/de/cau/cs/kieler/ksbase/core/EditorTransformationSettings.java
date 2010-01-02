/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.runtime.IContributor;
import org.eclipse.internal.xtend.expression.ast.DeclaredParameter;
import org.eclipse.internal.xtend.xtend.XtendFile;
import org.eclipse.internal.xtend.xtend.ast.Extension;
import org.eclipse.xtend.XtendResourceParser;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

/**
 * Stores the KSBasE settings for one specific editor. This class is used by the
 * {@link TransformationManager} to store settings that have been defined using the KSBasE extension
 * point or the KSBasE preference pages.
 * 
 * This class is capable of parsing an Xtend file and reading all in-place extensions and
 * parameters.
 * 
 * This class may be serialized.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 * @kieler.rating 2009-11-19 proposed yellow -review by msp,skn: class ok
 */
public class EditorTransformationSettings implements Serializable {

    /** Serialization Id. **/
    private static final long serialVersionUID = 836873211581178353L;
    /** The model package class. **/
    private String modelPackageClass;
    /** Default icon for menu entries. **/
    private String defaultIcon;
    /** Editor ID to which this setting is assigned. **/
    private String editorId;
    /** Xtend file in which the transformations are defined. **/
    private String extFile;
    /** The context for the diagram editor, required for key bindings. **/
    private String context;
    /** The map of transformations, ordered by their transformation Id. **/
    private HashMap<String, Transformation> transformations;
    /** List of menu contributions. **/
    private LinkedList<KSBasEMenuContribution> menuContributions;
    /**
     * Command handler to be used by this editor. If this is empty, the default handler will be
     * used.
     **/
    private String commandHandler;
    /**
     * The contributor which contains the extension points. When the settings have been defined
     * using the preference pages this will be null. This attribute will not be serialized.
     **/
    private transient IContributor contributor;

    /**
     * Creates a new transformation setting with the given fully qualified editor name.
     * 
     * @param editorClass
     *            The fqn of the diagram editor
     */
    public EditorTransformationSettings(final String editorClass) {
        this.editorId = editorClass;
        this.modelPackageClass = ""; //$NON-NLS-1$
        this.defaultIcon = ""; //$NON-NLS-1$
        this.extFile = ""; //$NON-NLS-1$
        this.context = ""; //$NON-NLS-1$
        this.commandHandler = ""; //$NON-NLS-1$
        this.transformations = new HashMap<String, Transformation>();
        this.menuContributions = new LinkedList<KSBasEMenuContribution>();
        this.contributor = null;
    }

    /**
     * Sets the editor class.
     * 
     * @param editorid
     *            The id of the diagram editor
     */
    public final void setEditor(final String editorid) {
        if (editorid != null && editorid.length() > 0) {
            this.editorId = editorid;
        }
    }

    /**
     * Gets the ID of the assigned editor.
     * 
     * @return The ID of the editor
     */
    public final String getEditorId() {
        return editorId;
    }

    /**
     * Gets the model package class name.
     * 
     * @return The fqn of the model package class
     */
    public final String getModelPackageClass() {

        if (modelPackageClass == null) {
            return ""; //$NON-NLS-1$
        } else {
            return modelPackageClass;
        }
    }

    /**
     * Sets a model package class.
     * 
     * @param modelPackage
     *            The package class
     */
    public final void setModelPackageClass(final String modelPackage) {
        this.modelPackageClass = modelPackage;
    }

    /**
     * Gets the list of existing menu contributions.
     * 
     * @return The list of menu contributions for this editor
     */
    public final LinkedList<KSBasEMenuContribution> getMenuContributions() {
        return menuContributions;
    }

    /**
     * Sets the menu contributions for this editor and removes any existing contributions.
     * 
     * @param contributionList
     *            The list of menu contributions to use
     */
    public final void setMenuContributions(final List<KSBasEMenuContribution> contributionList) {
        if (contributionList != null && contributionList.size() > 0) {
            this.menuContributions.clear();
            this.menuContributions.addAll(contributionList);
        }
    }

    /**
     * Adds a menu contribution to this editor.
     * 
     * @param contribution
     *            The contribution to append to the list of menu contributions.
     */
    public final void addMenuContribution(final KSBasEMenuContribution contribution) {
        if (contribution != null) {
            this.menuContributions.add(contribution);
        }
    }

    /**
     * Returns the path to the default icon.
     * 
     * @return Path to the default icon
     */
    public final String getDefaultIcon() {
        return defaultIcon;
    }

    /**
     * Sets the path to the default icon.
     * 
     * @param icon
     *            Icon path
     */
    public final void setDefaultIcon(final String icon) {
        this.defaultIcon = icon;
    }

    /**
     * Gets the list of defined transformations.
     * 
     * @return A LinkedList containing all transformations
     */
    public final Collection<Transformation> getTransformations() {
        return transformations.values();
    }

    /**
     * Tries to find a transformation with a given name.
     * 
     * @param transformation
     *            The name to find
     * @return The first transformation found or null
     */
    public final Transformation getTransformationByName(final String transformation) {
        if (transformation != null) {
            for (Transformation t : transformations.values()) {
                if (t.getExtension().toLowerCase(Locale.getDefault()).equals(
                        transformation.toLowerCase(Locale.getDefault()))) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * Tries to find a transformation by its identity string.
     * 
     * @param tid
     *            The id to find
     * @return The first transformation with the given id or null if no transformation has been
     *         found
     */
    public final Transformation getTransformationById(final String tid) {
        return transformations.get(tid);
    }

    /**
     * Adds a single transformation to the transformations list.
     * 
     * @param t
     *            a transformation definition
     */
    public final void addTransformation(final Transformation t) {
        if (t != null) {
            transformations.put(t.getTransformationId(), t);
        }
    }

    /**
     * Returns the text representation of the Xtend file.
     * 
     * @return An Xtend file in plain text
     */
    public final String getExtFile() {
        return extFile;
    }

    /**
     * Sets the content of the Xtend file.
     * 
     * @param file
     *            An Xtend file in plain text
     */
    public final void setExtFile(final String file) {
        this.extFile = file;
    }

    /**
     * Gets the contributor. May return null if the editor has been added via the preference pages.
     * 
     * @return The editors contributor project.
     */
    public final IContributor getContributor() {
        return contributor;
    }

    /**
     * Sets the editors contributor project.
     * 
     * @param contrib
     *            The contribution that is assigned with this editor. May be null if the editor has
     *            been defined by the user.
     */
    public final void setContributor(final IContributor contrib) {
        this.contributor = contrib;
    }

    /**
     * Returns the defined diagram context.
     * 
     * @return A contextID used to bind keyboard shortcuts to commands
     */
    public final String getContext() {
        return context;
    }

    /**
     * Sets the diagram context.
     * 
     * @param contxt
     *            the contextID to bind keyboard shortcuts
     */
    public final void setContext(final String contxt) {
        this.context = contxt;
    }

    /**
     * Returns the command handler, may return an empty string but never null.
     * 
     * @return The string containing the class name of the command handler.
     */
    public String getCommandHandler() {
        return commandHandler;
    }

    /**
     * Sets the command handler for this editor.
     * @param handlerClass The name of the command handler class
     */
    public void setCommandHandler(final String handlerClass) {
        this.commandHandler = handlerClass;
    }

    /**
     * Parses the Xtend file to read transformations and parameters.
     * 
     * @param createTransformations
     *            If this flag is set the transformations are created while parsing. If not, the
     *            parameters of the existing transformations are matched with the file.
     * @param fileName
     *            a valid URL to an Xtend file.
     */
    public final void parseTransformations(final boolean createTransformations, final URL fileName) {
        if (fileName != null) {
            try {
                // Using the XtendResourceParser to read transformations
                XtendResourceParser parser = new XtendResourceParser();
                Reader reader = new InputStreamReader(fileName.openStream());
                Object o = parser.parse(reader, "features.ext"); //$NON-NLS-1$
                if (o != null) {
                    // If we have any invalid transformations, i.e.
                    // the transformation defined here has no transformation
                    // match in the xtend file, we want to remove them.
                    LinkedList<Transformation> cachedTransformations = new LinkedList<Transformation>();

                    XtendFile xtFile = (XtendFile) o;
                    for (Extension ext : xtFile.getExtensions()) {
                        // Only read in-place methods
                        if (!ext.getReturnTypeIdentifier().getValue().equals("Void")) { //$NON-NLS-1$
                            continue;
                        }

                        Transformation transformation = getTransformationByName(ext.getName());
                        // Read parameters:
                        LinkedList<String> parameters = new LinkedList<String>();
                        for (DeclaredParameter param : ext.getFormalParameters()) {
                            parameters.add(param.getType().getValue());
                        }

                        if (transformation != null) {
                            // set parameters
                            transformation.setParameters(parameters.toArray(new String[parameters
                                    .size()]));
                            // Clone it, so we don't remove the transformation
                            // when
                            // clearing the transformations list
                            cachedTransformations.add(transformation.clone());
                        } else if (createTransformations) {
                            // Create new transformation
                            transformation = new Transformation(ext.getName(), ext.getName());
                            // set parameters
                            transformation.setParameters(parameters.toArray(new String[parameters
                                    .size()]));
                            // Create a default transformation id
                            transformation.setTransformationId(editorId + "." + ext.getName());
                            cachedTransformations.add(transformation);
                        }
                    }
                    // Adding all transformations. By clearing the
                    // transformations first, we ensure that no illegal
                    // transformations are included.
                    transformations.clear();
                    for (Transformation t : cachedTransformations) {
                        transformations.put(t.getTransformationId(), t);
                    }
                }
            } catch (SecurityException sec) {
                KSBasEPlugin.getDefault().logError(
                        "Unable to parse Xtend file: Not allowed to open file."); //$NON-NLS-1$
            } catch (IOException e) {
                KSBasEPlugin.getDefault().logError(
                        "Unable to parse Xtend file: Error while reading file."); //$NON-NLS-1$
            }
        }
    }

    /**
     * Two editor settings are the same if they have the same target editor and the same source
     * contributor. So we will have an implicit distinction between extension point and user defined
     * settings, because user defined settings will have 'null' as contributor.
     * 
     * @param obj
     *            The Object to compare
     * @return True if the given Object is an EditorTransformationSetting and has the same
     *         contributor
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof EditorTransformationSettings) {
            EditorTransformationSettings o = (EditorTransformationSettings) obj;
            // we are using the '==' operator for the contributor
            // because it may be possible that the contributor is 'null'
            // and the equals method will throw an exception
            return (o.getEditorId().equals(editorId) && o.getContributor() == contributor);
        }
        return false;
    }

    /**
     * The hashcode is calculated from the editors hash and the hashCode of the contributor, if
     * existing.
     * 
     * @return The hashCode
     */
    @Override
    public int hashCode() {
        int hash = editorId.hashCode();
        if (contributor != null) {
            hash += contributor.hashCode();
        }
        return hash;
    }
}
