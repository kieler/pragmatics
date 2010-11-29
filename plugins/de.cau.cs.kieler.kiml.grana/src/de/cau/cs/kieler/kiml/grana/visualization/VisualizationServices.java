/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.visualization;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.util.Dependency;
import de.cau.cs.kieler.core.util.DependencyGraph;
import de.cau.cs.kieler.core.util.IDependencyGraph;
import de.cau.cs.kieler.core.util.IDepending;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.plugin.GranaPlugin;

/**
 * Singleton class for global access to the KIML GRANA result visualization.
 * 
 * @author mri
 */
public final class VisualizationServices {

    /** identifier of the extension point for result visualizers. */
    private static final String EXTP_ID_RESULT_VISUALIZERS =
            "de.cau.cs.kieler.kiml.grana.resultVisualizers";
    /** name of the 'type' element. */
    private static final String ELEMENT_TYPE = "type";
    /** name of the 'dependency' element. */
    private static final String ELEMENT_DEPENDENCY = "dependency";
    /** name of the 'visualizer' element. */
    private static final String ELEMENT_VISUALIZER = "visualizer";
    /** name of the 'visualizationMethod' element. */
    private static final String ELEMENT_VISUALIZATION_METHOD =
            "visualizationMethod";
    /** name of the 'name' attribute in the extension points. */
    private static final String ATTRIBUTE_NAME = "name";
    /** name of the 'type' attribute in the extension points. */
    private static final String ATTRIBUTE_TYPE = "type";
    /** name of the 'class' attribute in the extension points. */
    private static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'priority' attribute in the extension points. */
    private static final String ATTRIBUTE_PRIORITY = "priority";
    /** name of the 'silent' attribute in the extension points. */
    private static final String ATTRIBUTE_SILENT = "silent";

    /** the singleton instance. */
    private static VisualizationServices instance = new VisualizationServices();
    /** the dependency graph for visualization types. */
    private IDependencyGraph<String, VisualizationType> dependencyGraph =
            new DependencyGraph<String, VisualizationType>();
    /** the visualization type names mapped on the type. */
    private Map<String, VisualizationType> nameTypeMapping =
            new HashMap<String, VisualizationType>();
    /** the visualization types mapped on the appropriate visualizers. */
    private Map<String, LinkedList<IVisualizer<Object, Object>>> typeVisualizersMapping =
            new HashMap<String, LinkedList<IVisualizer<Object, Object>>>();
    /** the visualizers mapped on their priority. */
    private Map<IVisualizer<Object, Object>, Integer> visualizerPriorityMapping =
            new HashMap<IVisualizer<Object, Object>, Integer>();
    /** the visualization methods. */
    private List<InfoVisualizationMethod> visualizationMethods =
            new LinkedList<InfoVisualizationMethod>();

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton instance
     */
    public static VisualizationServices getInstance() {
        return instance;
    }

    /**
     * Prevents this class to be instantiated from outside.
     */
    private VisualizationServices() {
    }

    /**
     * Creates the singleton and initializes it with the data from the extension
     * point.
     */
    static {
        instance = new VisualizationServices();
        // load the data from the extension point
        instance.loadResultVisualizersExtension();
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
     * 
     * @author msp
     */
    private static void reportError(final String extensionPoint,
            final IConfigurationElement element, final String attribute,
            final Exception exception) {
        String message =
                "Extension point " + extensionPoint
                        + ": Invalid entry in attribute '" + attribute
                        + "' of element " + element.getName()
                        + ", contributed by "
                        + element.getContributor().getName();
        IStatus status =
                new Status(IStatus.WARNING, GranaPlugin.PLUGIN_ID, 0, message,
                        exception);
        StatusManager.getManager().handle(status);
    }

    /**
     * Loads all visualizing types, visualizers and visualization methods from
     * the extension point.
     */
    private void loadResultVisualizersExtension() {
        IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(
                        EXTP_ID_RESULT_VISUALIZERS);
        List<VisualizationType> visualizationTypes =
                new LinkedList<VisualizationType>();
        List<Pair<InfoVisualizationMethod, String>> visualizationMethodTypes =
                new LinkedList<Pair<InfoVisualizationMethod, String>>();
        // iterate through all extension elements
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_TYPE.equals(element.getName())) {
                String name = element.getAttribute(ATTRIBUTE_NAME);
                if (name == null || name.length() == 0) {
                    reportError(EXTP_ID_RESULT_VISUALIZERS, element,
                            ATTRIBUTE_NAME, null);
                } else {
                    VisualizationType type = new VisualizationType(name);
                    for (IConfigurationElement child : element.getChildren()) {
                        if (ELEMENT_DEPENDENCY.equals(child.getName())) {
                            String dependency =
                                    child.getAttribute(ATTRIBUTE_TYPE);
                            if (dependency == null || dependency.length() == 0) {
                                reportError(EXTP_ID_RESULT_VISUALIZERS, child,
                                        ATTRIBUTE_TYPE, null);
                            } else {
                                type.addTypeDependency(dependency);
                            }
                        }
                    }
                    visualizationTypes.add(type);
                    LinkedList<IVisualizer<Object, Object>> visualizers =
                            typeVisualizersMapping.get(type);
                    if (visualizers == null) {
                        visualizers =
                                new LinkedList<IVisualizer<Object, Object>>();
                        typeVisualizersMapping.put(name, visualizers);
                    }
                }
            } else if (ELEMENT_VISUALIZER.equals(element.getName())) {
                try {
                    @SuppressWarnings("unchecked")
                    IVisualizer<Object, Object> visualizer =
                            (IVisualizer<Object, Object>) element
                                    .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (visualizer != null) {
                        String type = element.getAttribute(ATTRIBUTE_TYPE);
                        String priorityString =
                                element.getAttribute(ATTRIBUTE_PRIORITY);
                        int priority;
                        try {
                            priority = Integer.parseInt(priorityString);
                        } catch (NumberFormatException exception) {
                            priority = 0;
                        }
                        LinkedList<IVisualizer<Object, Object>> visualizers =
                                typeVisualizersMapping.get(type);
                        if (visualizers == null) {
                            visualizers =
                                    new LinkedList<IVisualizer<Object, Object>>();
                            typeVisualizersMapping.put(type, visualizers);
                        }
                        visualizers.add(visualizer);
                        visualizerPriorityMapping.put(visualizer, priority);
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception,
                            GranaPlugin.PLUGIN_ID);
                }
            } else if (ELEMENT_VISUALIZATION_METHOD.equals(element.getName())) {
                try {
                    IVisualizationMethod visualizationMethod =
                            (IVisualizationMethod) element
                                    .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (visualizationMethod != null) {
                        String type = element.getAttribute(ATTRIBUTE_TYPE);
                        boolean silent =
                                Boolean.parseBoolean(element
                                        .getAttribute(ATTRIBUTE_SILENT));
                        if (type == null || type.length() == 0) {
                            reportError(EXTP_ID_RESULT_VISUALIZERS, element,
                                    ATTRIBUTE_TYPE, null);
                        } else {
                            InfoVisualizationMethod infoVisualizationMethod =
                                    new InfoVisualizationMethod(
                                            visualizationMethod, silent);
                            visualizationMethods.add(infoVisualizationMethod);
                            visualizationMethodTypes
                                    .add(new Pair<InfoVisualizationMethod, String>(
                                            infoVisualizationMethod, type));
                        }
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception,
                            GranaPlugin.PLUGIN_ID);
                }
            }
        }
        // add the types to the dependency graph and remove types which
        // had unresolved or cyclic dependencies
        List<VisualizationType> unresolvedTypes =
                dependencyGraph.addAll(visualizationTypes);
        visualizationTypes.removeAll(unresolvedTypes);
        for (VisualizationType type : unresolvedTypes) {
            typeVisualizersMapping.remove(type.getId());
            // display a warning
            String message =
                    "VisualizationType "
                            + type.getId()
                            + " is missing a dependency or is part of a dependency cycle.";
            IStatus status =
                    new Status(IStatus.WARNING, GranaPlugin.PLUGIN_ID, 0,
                            message, null);
            StatusManager.getManager().handle(status);
        }
        // create the visualization type mapping
        for (VisualizationType visualizationType : visualizationTypes) {
            nameTypeMapping.put(visualizationType.getId(), visualizationType);
        }
        // resolve visualization method types
        for (Pair<InfoVisualizationMethod, String> pair : visualizationMethodTypes) {
            VisualizationType type = nameTypeMapping.get(pair.getSecond());
            if (type != null) {
                pair.getFirst().setType(type);
            } else {
                visualizationMethods.remove(pair.getFirst());
            }
        }
        // sort visualizers by their priority
        for (List<IVisualizer<Object, Object>> visualizers : typeVisualizersMapping
                .values()) {
            Collections.sort(visualizers, new VisualizerComparator());
        }
        // sort the visualization methods so silent ones come first
        Collections.sort(visualizationMethods,
                new InfoVisualizationMethodComparator());
    }

    /**
     * Returns a visualization for the given type and object.
     * 
     * @param visualizationType
     *            the visualization type
     * @param object
     *            the object which has to be visualized
     * @return the visualization
     */
    public Visualization getVisualization(final String visualizationType,
            final Object object) {
        VisualizationType type = nameTypeMapping.get(visualizationType);
        if (type == null) {
            return null;
        }
        return dependencyGraph.deriveObject(type,
                new VisualizationDerivationDetail(object));
    }

    /**
     * Visualizes the analyses results using all registered visualization
     * methods.
     * 
     * @param analyses
     *            the analyses
     * @param results
     *            the results
     * @param silent
     *            true if only silent visualization methods should be used
     */
    public void visualize(final List<AbstractInfoAnalysis> analyses,
            final Map<String, Object> results, final boolean silent) {
        Map<VisualizationType, List<BoundVisualization>> typeBoundVisualizationsMap =
                new HashMap<VisualizationType, List<BoundVisualization>>();
        for (InfoVisualizationMethod method : visualizationMethods) {
            if (!silent || method.isSilent()) {
                List<BoundVisualization> boundVisualizations =
                        typeBoundVisualizationsMap.get(method.getType());
                if (boundVisualizations == null) {
                    boundVisualizations = new LinkedList<BoundVisualization>();
                    typeBoundVisualizationsMap.put(method.getType(),
                            boundVisualizations);
                    for (AbstractInfoAnalysis analysis : analyses) {
                        Object result = results.get(analysis.getId());
                        Visualization visualization =
                                dependencyGraph.deriveObject(method.getType(),
                                        new VisualizationDerivationDetail(
                                                result));
                        boundVisualizations.add(new BoundVisualization(
                                analysis, result, visualization));
                    }
                }

                method.visualize(method.getType().getId(), boundVisualizations);
            }
        }
    }

    /**
     * The class that represents a visualization type.
     */
    private static class VisualizationType implements IDepending<String> {

        /** the type name. */
        private String name;
        /** the dependencies. */
        private List<Dependency<String>> dependencies =
                new LinkedList<Dependency<String>>();

        /**
         * Constructs the VisualizationType.
         * 
         * @param theName
         *            the type name
         */
        public VisualizationType(final String theName) {
            name = theName;
        }

        /**
         * {@inheritDoc}
         */
        public String getId() {
            return name;
        }

        /**
         * {@inheritDoc}
         */
        public List<Dependency<String>> getDependencies() {
            return dependencies;
        }

        /**
         * Adds a type to the dependencies.
         * 
         * @param type
         *            the type dependency
         */
        public void addTypeDependency(final String type) {
            Dependency<String> dep = new Dependency<String>(type);
            dependencies.add(dep);
        }
    }

    /**
     * Wrapper class for visualization methods.
     */
    private static class InfoVisualizationMethod implements
            IVisualizationMethod {

        /** the visualization type. */
        private VisualizationType type;
        /** the wrapped visualization method. */
        private IVisualizationMethod method;
        /** is the visualization method silent? */
        private boolean silent;

        /**
         * Constructs an InfoVisualizationMethod.
         * 
         * @param theMethod
         *            the visualization method
         * @param isSilent
         *            whether the visualization method is silent
         */
        public InfoVisualizationMethod(final IVisualizationMethod theMethod,
                final boolean isSilent) {
            method = theMethod;
            silent = isSilent;
        }

        /**
         * Returns the visualization type.
         * 
         * @return the visualization type
         */
        public VisualizationType getType() {
            return type;
        }

        /**
         * Sets the visualization type.
         * 
         * @param theType
         *            the visualization type
         */
        public void setType(final VisualizationType theType) {
            type = theType;
        }

        /**
         * Returns whether the visualization method is silent.
         * 
         * @return true if the visualization method is silent, false else
         */
        public boolean isSilent() {
            return silent;
        }

        /**
         * {@inheritDoc}
         */
        public void visualize(final String theType,
                final List<BoundVisualization> visualizations) {
            method.visualize(theType, visualizations);
        }
    }

    /**
     * Helper class for comparing visualizers.
     */
    private class VisualizerComparator implements
            Comparator<IVisualizer<Object, Object>> {

        /**
         * {@inheritDoc}
         */
        public int compare(final IVisualizer<Object, Object> visualizer1,
                final IVisualizer<Object, Object> visualizer2) {
            return -visualizerPriorityMapping.get(visualizer1).compareTo(
                    visualizerPriorityMapping.get(visualizer2));
        }

    }

    /**
     * Helper class for comparing info visualization methods.
     */
    private class InfoVisualizationMethodComparator implements
            Comparator<InfoVisualizationMethod> {

        /**
         * {@inheritDoc}
         */
        public int compare(final InfoVisualizationMethod visualizationMethod1,
                final InfoVisualizationMethod visualizationMethod2) {
            return (visualizationMethod2.isSilent() ? 1 : 0)
                    - (visualizationMethod1.isSilent() ? 1 : 0);
        }

    }

    /**
     * The implementation of the derivation detail interface for deriving a
     * visualization from the dependency graph.
     * 
     * @author mri
     */
    private class VisualizationDerivationDetail implements
            IDependencyGraph.DerivationDetail<VisualizationType, Visualization> {

        /** the result that has to be visualized. */
        private Object result;

        /**
         * Constructs a VisualizationDerivationDetail.
         * 
         * @param theResult
         *            the result that has to be visualized
         */
        public VisualizationDerivationDetail(final Object theResult) {
            result = theResult;
        }

        /**
         * {@inheritDoc}
         */
        public Visualization derive(final VisualizationType object) {
            IVisualizer<Object, Object> visualizer = null;
            // search for an appropriate visualizer
            for (IVisualizer<Object, Object> candidate : typeVisualizersMapping
                    .get(object.getId())) {
                if (candidate.canVisualize(result)) {
                    visualizer = candidate;
                    break;
                }
            }
            // no visualizer found?
            if (visualizer == null) {
                return null;
            }
            // create the visualization object
            return new Visualization(visualizer);
        }

        /**
         * {@inheritDoc}
         */
        public void makeDependent(final Visualization object,
                final Visualization dependency,
                final VisualizationType dependencyObject) {
            object.addDependency(dependencyObject.getId(), dependency);
        }
    }
}
