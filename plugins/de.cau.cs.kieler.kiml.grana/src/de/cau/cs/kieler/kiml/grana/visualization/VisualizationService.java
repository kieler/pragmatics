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

import de.cau.cs.kieler.kiml.grana.GranaPlugin;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * Singleton class for global access to the KIML GRANA result visualization.
 * 
 * @author mri
 */
public final class VisualizationService {

    /** identifier of the extension point for result visualizers. */
    private static final String EXTP_ID_RESULT_VISUALIZERS
            = "de.cau.cs.kieler.kiml.grana.resultVisualizers";
    /** name of the 'type' element. */
    private static final String ELEMENT_TYPE = "type";
    /** name of the 'visualizer' element. */
    private static final String ELEMENT_VISUALIZER = "visualizer";
    /** name of the 'visualizationMethod' element. */
    private static final String ELEMENT_VISUALIZATION_METHOD = "visualizationMethod";
    /** name of the 'default' attribute in the extension points. */
    private static final String ATTRIBUTE_DEFAULT = "default";
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
    private static VisualizationService instance = new VisualizationService();
    /** the visualization types mapped on the appropriate visualizers. */
    private Map<String, LinkedList<IVisualizer<Object, Object>>> typeVisualizersMapping
            = new HashMap<String, LinkedList<IVisualizer<Object, Object>>>();
    /** the visualizers mapped on their priority. */
    private Map<IVisualizer<Object, Object>, Integer> visualizerPriorityMapping
            = new HashMap<IVisualizer<Object, Object>, Integer>();
    /** the visualization methods. */
    private List<VisualizationMethodData> visualizationMethods
            = new LinkedList<VisualizationMethodData>();

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton instance
     */
    public static VisualizationService getInstance() {
        return instance;
    }

    /**
     * Prevents this class to be instantiated from outside.
     */
    private VisualizationService() {
    }

    /**
     * Creates the singleton and initializes it with the data from the extension point.
     */
    static {
        instance = new VisualizationService();
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
            final IConfigurationElement element, final String attribute, final Exception exception) {
        String message = "Extension point " + extensionPoint + ": Invalid entry in attribute '"
                + attribute + "' of element " + element.getName() + ", contributed by "
                + element.getContributor().getName();
        IStatus status = new Status(IStatus.WARNING, GranaPlugin.PLUGIN_ID, 0, message, exception);
        StatusManager.getManager().handle(status);
    }

    /**
     * Loads all visualizing types, visualizers and visualization methods from the extension point.
     */
    private void loadResultVisualizersExtension() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_RESULT_VISUALIZERS);
        List<String> visualizationTypes = new LinkedList<String>();
        // iterate through all extension elements
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_TYPE.equals(element.getName())) {
                String name = element.getAttribute(ATTRIBUTE_NAME);
                if (name == null || name.length() == 0) {
                    reportError(EXTP_ID_RESULT_VISUALIZERS, element, ATTRIBUTE_NAME, null);
                } else {
                    visualizationTypes.add(name);
                    LinkedList<IVisualizer<Object, Object>> visualizers = typeVisualizersMapping
                            .get(name);
                    if (visualizers == null) {
                        visualizers = new LinkedList<IVisualizer<Object, Object>>();
                        typeVisualizersMapping.put(name, visualizers);
                    }
                }
            } else if (ELEMENT_VISUALIZER.equals(element.getName())) {
                try {
                    @SuppressWarnings("unchecked")
                    IVisualizer<Object, Object> visualizer = (IVisualizer<Object, Object>) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (visualizer != null) {
                        String type = element.getAttribute(ATTRIBUTE_TYPE);
                        String priorityString = element.getAttribute(ATTRIBUTE_PRIORITY);
                        int priority;
                        try {
                            priority = Integer.parseInt(priorityString);
                        } catch (NumberFormatException exception) {
                            priority = 0;
                        }
                        LinkedList<IVisualizer<Object, Object>> visualizers = typeVisualizersMapping
                                .get(type);
                        if (visualizers == null) {
                            visualizers = new LinkedList<IVisualizer<Object, Object>>();
                            typeVisualizersMapping.put(type, visualizers);
                        }
                        visualizers.add(visualizer);
                        visualizerPriorityMapping.put(visualizer, priority);
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception, GranaPlugin.PLUGIN_ID);
                }
            } else if (ELEMENT_VISUALIZATION_METHOD.equals(element.getName())) {
                try {
                    IVisualizationMethod visualizationMethod = (IVisualizationMethod) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (visualizationMethod != null) {
                        String type = element.getAttribute(ATTRIBUTE_TYPE);
                        if (type == null || type.length() == 0) {
                            reportError(EXTP_ID_RESULT_VISUALIZERS, element, ATTRIBUTE_TYPE, null);
                        } else {
                            boolean silent = Boolean.parseBoolean(element
                                    .getAttribute(ATTRIBUTE_SILENT));
                            boolean active = true;
                            String activeString = element.getAttribute(ATTRIBUTE_DEFAULT);
                            if (activeString != null && activeString.length() > 0) {
                                active = Boolean.parseBoolean(activeString);
                            }
                            VisualizationMethodData infoVisualizationMethod
                                    = new VisualizationMethodData(visualizationMethod, silent, active,
                                            type);
                            visualizationMethods.add(infoVisualizationMethod);
                        }
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception, GranaPlugin.PLUGIN_ID);
                }
            }
        }
        // resolve visualization method types
        List<VisualizationMethodData> invalidVisualizationMethods
                = new LinkedList<VisualizationMethodData>();
        for (VisualizationMethodData method : visualizationMethods) {
            if (!visualizationTypes.contains(method.type)) {
                invalidVisualizationMethods.add(method);
            }
        }
        visualizationMethods.removeAll(invalidVisualizationMethods);
        // sort visualizers by their priority
        for (List<IVisualizer<Object, Object>> visualizers : typeVisualizersMapping.values()) {
            Collections.sort(visualizers, new VisualizerComparator());
        }
        // sort the visualization methods so silent ones come first
        Collections.sort(visualizationMethods, new VisualizationMethodComparator());
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
    public Visualization getVisualization(final String visualizationType, final Object object) {
        List<IVisualizer<Object, Object>> visualizers = typeVisualizersMapping
                .get(visualizationType);
        if (visualizers == null) {
            return null;
        }
        for (IVisualizer<Object, Object> visualizer : visualizers) {
            if (visualizer.canVisualize(object)) {
                return new Visualization(visualizer);
            }
        }
        return null;
    }
    
    /**
     * Activates or deactivates a given visualization method.
     * 
     * @param methodClass
     *            the class that represents the registered visualization method
     * @param active
     *            whether the method shall be activated or deactivated
     */
    public void setActive(final Class<? extends IVisualizationMethod> methodClass,
            final boolean active) {
        for (VisualizationMethodData methodData : visualizationMethods) {
            if (methodClass.isInstance(methodData.method)) {
                methodData.active = active;
            }
        }
    }
    
    /**
     * Determine whether there is an active visualization method that would process analysis results.
     * 
     * @param silent
     *            true if only silent visualization methods should be used
     * @return {@code true} if there is an active visualization method
     */
    public boolean findActiveMethod(final boolean silent) {
        for (VisualizationMethodData methodData : visualizationMethods) {
            if (methodData.active && (!silent || methodData.silent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Visualize the analyses results using all registered visualization methods.
     * 
     * @param analyses
     *            the analyses
     * @param results
     *            the results
     * @param silent
     *            true if only silent visualization methods should be used
     */
    public void visualize(final List<AnalysisData> analyses, final Map<String, Object> results,
            final boolean silent) {
        Map<String, List<BoundVisualization>> typeBoundVisualizationsMap
                = new HashMap<String, List<BoundVisualization>>();
        for (VisualizationMethodData methodData : visualizationMethods) {
            if (methodData.active && (!silent || methodData.silent)) {
                List<BoundVisualization> boundVisualizations = typeBoundVisualizationsMap
                        .get(methodData.type);
                if (boundVisualizations == null) {
                    boundVisualizations = new LinkedList<BoundVisualization>();
                    typeBoundVisualizationsMap.put(methodData.type, boundVisualizations);
                    for (AnalysisData analysis : analyses) {
                        Object result = results.get(analysis.getId());
                        Visualization visualization = getVisualization(methodData.type, result);
                        if (visualization != null) {
                            boundVisualizations.add(new BoundVisualization(analysis, result,
                                    visualization));
                        }
                    }
                }

                methodData.method.visualize(methodData.type, boundVisualizations);
            }
        }
    }

    /**
     * Wrapper class for visualization methods.
     */
    private static class VisualizationMethodData {

        /** the visualization type. */
        private String type;
        /** the wrapped visualization method. */
        private IVisualizationMethod method;
        /** is the visualization method silent? */
        private boolean silent;
        /** is the visualization method active? */
        private boolean active;

        /**
         * Constructs an VisualizationMethodData.
         * 
         * @param theMethod
         *            the visualization method
         * @param isSilent
         *            whether the visualization method is silent
         * @param isActive
         *            whether the visualization method is initially active
         * @param theType
         *            the visualization type
         */
        public VisualizationMethodData(final IVisualizationMethod theMethod, final boolean isSilent,
                final boolean isActive, final String theType) {
            this.method = theMethod;
            this.silent = isSilent;
            this.type = theType;
            this.active = isActive;
        }

    }

    /**
     * Helper class for comparing visualizers.
     */
    private class VisualizerComparator implements Comparator<IVisualizer<Object, Object>> {

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
    private static class VisualizationMethodComparator implements Comparator<VisualizationMethodData> {

        /**
         * {@inheritDoc}
         */
        public int compare(final VisualizationMethodData visualizationMethod1,
                final VisualizationMethodData visualizationMethod2) {
            return (visualizationMethod2.silent ? 1 : 0)
                    - (visualizationMethod1.silent ? 1 : 0);
        }

    }
}
