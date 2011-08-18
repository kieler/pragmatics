/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.klighd.transformations.XtendBasedTransformation;

/**
 * Singleton for accessing the light diagram services.
 * 
 * @author mri
 */
public final class LightDiagramServices {

    /** identifier of the extension point for viewer providers. */
    public static final String EXTP_ID_VIEWER_PROVIDERS = "de.cau.cs.kieler.klighd.viewerProviders";
    /** identifier of the extension point for model transformations. */
    public static final String EXTP_ID_MODEL_TRANSFORMATIONS =
            "de.cau.cs.kieler.klighd.modelTransformations";
    /** name of the 'viewer' element. */
    public static final String ELEMENT_VIEWER = "viewer";

    /** name of the 'transformation' element. */
    public static final String ELEMENT_TRANSFORMATION = "transformation";
    /** name of the 'xtendBasedTransformation' element. */
    public static final String ELEMENT_XTEND_BASED_TRANSFORMATION = "xtendBasedTransformation";

    /** name of the 'id' attribute in the extension points. */
    public static final String ATTRIBUTE_ID = "id";
    /** name of the 'class' attribute in the extension points. */
    public static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'extFile' attribute in the extension points. */
    public static final String ATTRIBUTE_EXTENSION_FILE = "extFile";
    /** name of the 'extension' attribute in the extension points. */
    public static final String ATTRIBUTE_EXTENSION = "extension";
    /** name of the 'EPackage' attribute in the extension points. */
    public static final String ATTRIBUTE_EPACKAGE = "EPackage";
    /** name of the 'EPackageClass' attribute in the extension points. */
    public static final String ATTRIBUTE_EPACKAGE_CLASS = "EPackageClass";
    /** name of the 'contributingBundle' attribute in the extension points. */
    public static final String ATTRIBUTE_CO_CONTRIBUTING_BUNDLE = "contributingBundle";

    /** the singleton instance. */
    private static LightDiagramServices instance;
    /** a mapping between viewer provider id's and the instances. */
    private Map<String, IViewerProvider> idViewerProviderMapping =
            new LinkedHashMap<String, IViewerProvider>();
    /** a mapping between transformation id's and the instances. */
    private Map<String, IModelTransformation<Object, ?>> idModelTransformationMapping =
            new LinkedHashMap<String, IModelTransformation<Object, ?>>();
    /**
     * a collection of classes of models that are definitely not supported by the available
     * viewers/transformations, maintained in order to improve the performance.
     */
    private Set<Class<?>> knownNotSupportedModels = new HashSet<Class<?>>();

    /**
     * A private constructor to prevent instantiation.
     */
    private LightDiagramServices() {
        // do nothing
    }

    /**
     * Creates the singleton and initializes it with the data from the extension point.
     */
    static {
        instance = new LightDiagramServices();
        // load the data from the extension points
        instance.loadViewerProviderExtension();
        instance.loadModelTransformationsExtension();
    }

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton
     */
    public static LightDiagramServices getInstance() {
        return instance;
    }

    /**
     * Reports an error that occurred while reading extensions.
     * 
     * @param extensionPoint
     *            the identifier of the extension point
     * @param element
     *            the configuration element
     * @param attribute
     *            the attribute that contains an invalid entry
     * @param exception
     *            an optional exception that was caused by the invalid entry
     */
    private static void reportError(final String extensionPoint,
            final IConfigurationElement element, final String attribute, final Exception exception) {
        String message = "Extension point " + extensionPoint + ": Invalid entry in attribute '"
                + attribute + "' of element " + element.getName() + ", contributed by "
                + element.getContributor().getName();
        IStatus status = new Status(IStatus.WARNING, KLighDPlugin.PLUGIN_ID, 0, message, exception);
        StatusManager.getManager().handle(status);
    }

    /**
     * Loads and registers all viewer provider from the extension point.
     */
    private void loadViewerProviderExtension() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_VIEWER_PROVIDERS);
        for (IConfigurationElement element : extensions) {
            try {
                if (ELEMENT_VIEWER.equals(element.getName())) {
                    // initialize viewer provider from the extension point
                    IViewerProvider viewerProvider = (IViewerProvider) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (viewerProvider != null) {
                        String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_VIEWER_PROVIDERS, element, ATTRIBUTE_ID, null);
                        } else {
                            idViewerProviderMapping.put(id, viewerProvider);
                        }
                    }
                }
            } catch (CoreException exception) {
                StatusManager.getManager().handle(exception, KLighDPlugin.PLUGIN_ID);
            }
        }
    }

    private HashMap<String, EPackage> ePackages = new HashMap<String, EPackage>();

    /**
     * Loads and registers all model transformations from the extension point.
     */
    private void loadModelTransformationsExtension() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_MODEL_TRANSFORMATIONS);
        for (IConfigurationElement element : extensions) {
            try {
                if (ELEMENT_TRANSFORMATION.equals(element.getName())) {
                    // initialize model transformation from the extension point
                    @SuppressWarnings("unchecked")
                    IModelTransformation<Object, ?> modelTransformation = 
                            (IModelTransformation<Object, ?>) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (modelTransformation != null) {
                        String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_MODEL_TRANSFORMATIONS, element, ATTRIBUTE_ID, null);
                        } else {
                            idModelTransformationMapping.put(id, modelTransformation);
                        }
                    }
                } else if (ELEMENT_XTEND_BASED_TRANSFORMATION.equals(element.getName())) {
                    //
                    // handle xtendBasedTransformation extensions
                    //
                    String id = element.getAttribute(ATTRIBUTE_ID);
                    String extFile = element.getAttribute(ATTRIBUTE_EXTENSION_FILE);
                    String extension = element.getAttribute(ATTRIBUTE_EXTENSION);
                    Bundle contributingBundle = Platform.getBundle(element.getContributor()
                            .getName());
                    String coContributingBundlesName = element
                            .getAttribute(ATTRIBUTE_CO_CONTRIBUTING_BUNDLE);

                    // "normalize" the Xtend file path
                    extFile = extFile.replaceAll("::", "/");
                    if (!extFile.endsWith(".ext")) {
                        extFile = extFile + ".ext";
                    }

                    URL extFileURL = null;
                    if (contributingBundle != null) {
                        // try to reveal the Xtend file in the bundle the extension is declared in
                        extFileURL = contributingBundle.getEntry(extFile);

                        if (extFileURL == null) {
                            extFileURL = contributingBundle.getEntry("src/" + extFile);
                        }

                        if (extFileURL == null) {
                            extFileURL = contributingBundle.getEntry("transformations/" + extFile);
                        }

                        // in case a the Xtend file is located in a bundle different from
                        //  'contributingBundle' try to reveal that bundle and the Xtend file by
                        //   means of the 'contributingBundle' entry in the extension (refered to as
                        //   coContributingBundlesName)
                        // this, however, should not be used extensively but is helpful during the
                        //   prototyping state
                        if (extFileURL == null && coContributingBundlesName != null
                                && !coContributingBundlesName.equals("")) {
                            Bundle coContributingBundle = Platform
                                    .getBundle(coContributingBundlesName);
                            extFileURL = coContributingBundle.getEntry(extFile);

                            if (extFileURL == null) {
                                extFileURL = coContributingBundle.getEntry("src/" + extFile);
                            }

                            if (extFileURL == null) {
                                extFileURL = coContributingBundle.getEntry("transformations/"
                                        + extFile);
                            }
                        }
                    }

                    if (extFileURL == null) {
                        continue;
                    }

                    List<EPackage> metamodels = new ArrayList<EPackage>();

                    for (IConfigurationElement epackageDecl : element
                            .getChildren(ATTRIBUTE_EPACKAGE)) {

                        String ePackageId = epackageDecl.getAttribute(ATTRIBUTE_EPACKAGE_CLASS);
                        EPackage ePackageInstance = ePackages.get(ePackageId);

                        if (ePackageInstance == null) {
                            try {

                                Class<?> ePackage = contributingBundle.loadClass(ePackageId);
                                ePackageInstance = (EPackage) ePackage.getField("eINSTANCE").get(
                                        null);
                                this.ePackages.put(ePackageId, ePackageInstance);
                            } catch (Exception e) {
                                String msg = "EPackage " + ePackageId + " could not be loaded";
                                StatusManager.getManager().addLoggedStatus(
                                        new Status(IStatus.ERROR, KLighDPlugin.PLUGIN_ID, msg, e));
                                continue;
                            }
                        }

                        if (ePackageInstance != null) {
                            metamodels.add(ePackageInstance);
                        }

                    }

                    IModelTransformation<Object, ?> modelTransformation = new XtendBasedTransformation(
                            extFileURL, extension, metamodels);
                    idModelTransformationMapping.put(id, modelTransformation);

                }
            } catch (CoreException exception) {
                StatusManager.getManager().handle(exception, KLighDPlugin.PLUGIN_ID);
            }
        }
    }

    /**
     * Returns a viewer provider which is supporting the given model.
     * 
     * @param model
     *            the model
     * @return the viewer provider or null if no viewer provider could be found
     */
    public IViewerProvider getViewerProviderForModel(final Object model) {
        for (IViewerProvider viewerProvider : idViewerProviderMapping.values()) {
            if (viewerProvider.supports(model)) {
                return viewerProvider;
            }
        }
        return null;
    }

    private static final int MAX_DEPTH = 10;
    private int currentDepth;

    /**
     * Tries to find a viewer for the given model.<br>
     * If no viewer provider is registered which directly supports the model, the registered
     * transformations are used to transform the model until a supporting viewer provider is
     * available. The finally transformed model and the viewer provider which supports that model
     * are retured in a {@code ViewContext} if available.
     * 
     * @param model
     *            the model
     * @return the view context or null if the model and all possible transformations are
     *         unsupported by all viewer providers
     */
    public ViewContext getValidViewContext(final Object model) {
        currentDepth = 0;
        ViewContext context = getValidViewContextRec(model,
                new LinkedList<IModelTransformation<?, ?>>());
        if (context == null) {
            synchronized (this.knownNotSupportedModels) {
                this.knownNotSupportedModels.add(model.getClass());
            }
        }
        return context;
    }

    private ViewContext getValidViewContextRec(final Object model,
            final List<IModelTransformation<?, ?>> transformations) {
        // enforce maximum recursion depth to prevent infinite recursion
        if (currentDepth++ > MAX_DEPTH) {
            return null;
        }
        IViewerProvider viewerProvider = getViewerProviderForModel(model);
        // if the model is supported by a viewer provider create a new view context and return it
        if (viewerProvider != null) {
            return new ViewContext(viewerProvider, model, transformations);
        }
        // transform the model and proceed recursively
        for (IModelTransformation<Object, ?> transformation : idModelTransformationMapping.values()) {
            if (transformation.supports(model)) {
                Object newModel = transformation.transform(model);
                transformations.add(transformation);
                ViewContext viewContext = getValidViewContextRec(newModel, transformations);
                if (viewContext != null) {
                    return viewContext;
                }
                transformations.remove(transformations.size() - 1);
            }
        }
        return null;
    }

    /**
     * Provides a cheap test whether a model may be visualized with the available
     * viewers/transformations.
     * 
     * @param model
     *            The model to be tested.
     * @return <code>false</code> if the model type is determined to be not supported by the
     *         available viewers/transformations, true otherwise.
     */
    public boolean maybeSupports(final Object model) {
        synchronized (this.knownNotSupportedModels) {
            return !this.knownNotSupportedModels.contains(model.getClass());
        }
    }

}
