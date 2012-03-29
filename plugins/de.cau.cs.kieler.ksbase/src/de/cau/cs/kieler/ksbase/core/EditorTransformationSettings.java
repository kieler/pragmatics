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

import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.model.xtend.transformation.AbstractTransformation;
import de.cau.cs.kieler.core.model.xtend.transformation.ITransformationFramework;
import de.cau.cs.kieler.ksbase.KSBasEPlugin;

/**
 * Stores the KSBasE settings for one specific editor. This class is used by the
 * {@link TransformationManager} to store settings that have been defined using the KSBasE extension
 * point or the KSBasE preference pages.
 * 
 * 
 * This class may be serialized.
 * 
 * @author mim
 * 
 * @kieler.rating 2010-01-22 yellow review by msp, skn
 */
public class EditorTransformationSettings implements Serializable {

    /** Serialization Id. **/
    private static final long serialVersionUID = 836873211581178353L;
    /** The model package class. **/
    private List<String> modelPackages = new LinkedList<String>();
    /** Default icon for menu entries. **/
    private String defaultIcon;
    /** Editor ID to which this setting is assigned. **/
    private String editorId;
    /**
     * Transformation file in which the transformations are defined. This is a String representing
     * an absolute path.
     **/
    private String transformationFile;
    /** The context for the diagram editor, required for key bindings. **/
    private String context;
    /** The map of transformations, ordered by their transformation Id. **/
    private HashMap<String, KSBasETransformation> inplaceTransformations;
    /** The map of out-place transformations, those may only be used for validation. **/
    private HashMap<String, AbstractTransformation> outplaceTransformations;
    /** List of menu contributions. **/
    private LinkedList<KSBasEMenuContribution> menuContributions;
    /** Transformation Framework to use. **/
    private transient ITransformationFramework framework;
    /** Check visibility expressions? **/
    private boolean checkVisibility;
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
     * A list of transformation classes given by the extension point. This will likely be generated
     * xtend2 transformation classes.
     */
    private LinkedList<Object> transformationClasses = new LinkedList<Object>();

    /**
     * Creates a new transformation setting with the given fully qualified editor name.
     * 
     * @param editorClass
     *            The fqn of the diagram editor
     */
    public EditorTransformationSettings(final String editorClass) {
        this.editorId = editorClass;
        this.defaultIcon = ""; //$NON-NLS-1$
        this.transformationFile = ""; //$NON-NLS-1$
        this.context = ""; //$NON-NLS-1$
        this.commandHandler = ""; //$NON-NLS-1$
        this.inplaceTransformations = new HashMap<String, KSBasETransformation>();
        this.outplaceTransformations = new HashMap<String, AbstractTransformation>();
        this.menuContributions = new LinkedList<KSBasEMenuContribution>();
        this.contributor = null;
        this.checkVisibility = true;
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
    public final List<String> getModelPackages() {
        return modelPackages;
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
    public final Collection<KSBasETransformation> getTransformations() {
        return inplaceTransformations.values();
    }

    /**
     * Gets the list of defined out-place transformations.
     * 
     * @return A linkedList containing all transformations
     */
    public final Collection<AbstractTransformation> getOutPlaceTransformations() {
        return outplaceTransformations.values();
    }

    /**
     * Tries to find a transformation with a given name.
     * 
     * @param transformation
     *            The name to find
     * @return The first transformation found or null
     */
    public final KSBasETransformation getTransformationByName(final String transformation) {
        if (transformation != null) {
            for (KSBasETransformation t : inplaceTransformations.values()) {
                if (t.getTransformation().toLowerCase(Locale.getDefault())
                        .equals(transformation.toLowerCase(Locale.getDefault()))) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * Tries to find a transformation with a given name.
     * 
     * @param transformation
     *            The name to find
     * @return The first transformation found or null
     */
    public final AbstractTransformation getOutPlaceTransformationByName(final String transformation) {
        if (transformation != null) {
            for (AbstractTransformation t : outplaceTransformations.values()) {
                if (t.getTransformation().toLowerCase(Locale.getDefault())
                        .equals(transformation.toLowerCase(Locale.getDefault()))) {
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
    public final KSBasETransformation getTransformationById(final String tid) {
        return inplaceTransformations.get(tid);
    }

    /**
     * Tries to find a transformation by its identity string.
     * 
     * @param tid
     *            The id to find
     * @return The first transformation with the given id or null if no transformation has been
     *         found
     */
    public final AbstractTransformation getOutPlaceTransformationById(final String tid) {
        return outplaceTransformations.get(tid);
    }

    /**
     * Adds a single transformation to the transformations list.
     * 
     * @param t
     *            a transformation definition
     */
    public final void addTransformation(final KSBasETransformation t) {
        if (t != null) {
            inplaceTransformations.put(t.getTransformationId(), t);
        }
    }

    /**
     * Adds a single transformation to the transformations list.
     * 
     * @param t
     *            a transformation definition
     */
    public final void addOutPlaceTransformation(final KSBasETransformation t) {
        if (t != null) {
            outplaceTransformations.put(t.getTransformationId(), t);
        }
    }

    /**
     * Returns the absolute path to the transformation file.
     * 
     * @return An abolute path.
     */
    public final String getTransformationFile() {
        return transformationFile;
    }

    /**
     * Sets the path to the transformation file.
     * 
     * @param absolutePath
     *            An absolute path
     */
    public final void setTransformationFile(final String absolutePath) {
        this.transformationFile = absolutePath;
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
     *            been defined by the user or the plug-in project does not have a contributor.
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
     * 
     * @param handlerClass
     *            The name of the command handler class, must not be null
     */
    public void setCommandHandler(final String handlerClass) {
        assert (handlerClass != null);
        this.commandHandler = handlerClass;
    }

    /**
     * Gets the transformation framework for this editor.
     * 
     * @return an implementation of ITransformationFramework
     */
    public ITransformationFramework getFramework() {
        return framework;
    }

    /**
     * Sets the transformation framework for this editor.
     * 
     * @param theFramework
     *            an implementation of ITransformationFramework
     */
    public void setFramework(final ITransformationFramework theFramework) {
        this.framework = theFramework;
    }

    /**
     * Visibility checks enabled?
     * 
     * @return true if checks are enabled
     */
    public boolean isCheckVisibility() {
        return checkVisibility;
    }

    /**
     * Set visibility checks.
     * 
     * @param flag
     *            new option
     */
    public void setCheckVisibility(final boolean flag) {
        this.checkVisibility = flag;
    }

    /**
     * Parses the transformation file to read transformations and parameters.
     * 
     * @param createTransformations
     *            If this flag is set the transformations are created while parsing. If not, the
     *            parameters of the existing transformations are matched with the file.
     * @param fileURL
     *            a valid URL to an transformation file.
     */
    public final void parseTransformations(final boolean createTransformations, final URL fileURL) {
        if (framework != null) {
            List<AbstractTransformation> parseTransformations = null;
            if (fileURL != null) {
                // Parse transformations with the framework
                parseTransformations = framework.parseTransformations(fileURL, true);
                if (parseTransformations == null) {
                    KSBasEPlugin.getDefault().logError(
                            "Could not parse extensions for editor " + editorId
                                    + ". Developer hint: make sure that the .ext file's containing"
                                    + "folder is part of the binary build (build.properties)");
                    return;
                }
            } else {
                parseTransformations = new LinkedList<AbstractTransformation>();
            }
            // If we have any invalid transformations, i.e.
            // the transformation defined here has no transformation
            // match in the transformation file, we want to remove them.
            LinkedList<KSBasETransformation> cachedTransformations = new LinkedList<KSBasETransformation>();

            for (AbstractTransformation t : parseTransformations) {
                KSBasETransformation transformation = getTransformationByName(t.getTransformation());
                if (transformation != null) {
                    // Clone it, so we don't remove the transformation
                    // when clearing the transformations list
                    for (List<String> p : t.getParameterList()) {
                        transformation.addParameters(p);
                    }
                    cachedTransformations.add(transformation.clone());
                } else if (createTransformations) {
                    // Create new transformation:
                    transformation = new KSBasETransformation(t.getTransformation(),
                            t.getTransformation());
                    // set parameters
                    transformation.setParameters(t.getParameterList());
                    // Create a default transformation id
                    transformation.setTransformationId(editorId + "." + t.getTransformation());
                    cachedTransformations.add(transformation);
                }
            }

            for (Object transformationClass : this.transformationClasses) {
                for (KSBasETransformation t : inplaceTransformations.values()) {
                    Method[] methods = transformationClass.getClass().getMethods();
                    for (Method m : methods) {
                        String methodName = m.getName();
                        String transformationName = t.getTransformation();
                        if (methodName.equals(transformationName)) {
                            t.setTransformationClass(transformationClass);
                            cachedTransformations.add(t);
                        }
                    }
                }
            }

            for (KSBasETransformation t : inplaceTransformations.values()) {
                if (!cachedTransformations.contains(t)) {
                    Status myStatus = new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                            "The transformation" + t.getName() + "could not be parsed correctly",
                            null);
                    StatusManager.getManager().handle(myStatus, StatusManager.LOG);
                }
            }

            // Adding all transformations. By clearing the
            // transformations first, we ensure that no illegal
            // transformations are included.
            inplaceTransformations.clear();
            for (KSBasETransformation t : cachedTransformations) {
                inplaceTransformations.put(t.getTransformationId(), t);
            }

            // Parsing out-place transformations
            // We do not care if they already exist in the list, 'cause
            // they're used for validation only
            outplaceTransformations.clear();
            List<AbstractTransformation> outplace = framework.parseTransformations(fileURL, false);
            if (outplace != null) {
                for (AbstractTransformation oT : outplace) {
                    outplaceTransformations.put(oT.getTransformation(), oT);
                }

            }

        } else {
            KSBasEPlugin.getDefault().logError(
                    "Unable to parse transformation file: "
                            + "No TransformationFramework has been set."); //$NON-NLS-1$
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

    public void addTransformationClass(Object classObject) {

    }

    /**
     * 
     * @return A list of transformation classes. This will likely be generated xtend2 transformation
     *         classes.
     */
    public List<Object> getTransformationClasses() {
        return transformationClasses;
    }
}
