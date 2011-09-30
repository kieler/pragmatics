/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A graph structure to express the relations for instances of {@code IModelTransformation} and
 * {@code IViewerProvider}.
 * 
 * @author mri
 */
public class TransformationGraph {

    /** the mapping of model classes on model class nodes. */
    private Map<Class<?>, ClassNode> classNodeMapping = new HashMap<Class<?>, ClassNode>();

    /** the mapping of transformation id's on transformation edges. */
    private Map<String, TransformationEdge> idTransformationMapping =
            new HashMap<String, TransformationEdge>();
    /** the transformations in this graph. */
    private Set<IModelTransformation<?, ?>> transformations =
            new HashSet<IModelTransformation<?, ?>>();

    /** the mapping of viewer provider id's on viewer provider data. */
    private Map<String, ViewerProviderData> idViewerProviderMapping =
            new HashMap<String, ViewerProviderData>();
    /** the viewer providers in this graph. */
    private Set<IViewerProvider> viewerProviders = new HashSet<IViewerProvider>();

    /**
     * Adds a model transformation under a given identifier to the graph.
     * 
     * @param id
     *            the identifier for the model transformation
     * @param transformation
     *            the model transformation
     */
    public void addModelTransformation(final String id,
            final IModelTransformation<?, ?> transformation) {
        // only add the transformation if it is not already present
        if (id.length() > 0 && !transformations.contains(transformation)
                && !idTransformationMapping.containsKey(id)) {
            Class<?> sourceClass = transformation.getSourceClass();
            Class<?> targetClass = transformation.getTargetClass();
            // only add the transformation if both source and target class can be determined
            if (sourceClass != null && targetClass != null) {
                transformations.add(transformation);
                // insert the classes into the graph if they are not present already
                ClassNode source = createClassNode(sourceClass);
                ClassNode target = createClassNode(targetClass);
                // insert the transformation
                TransformationEdge transformationEdge = new TransformationEdge();
                transformationEdge.transformation = transformation;
                transformationEdge.source = source;
                transformationEdge.target = target;
                source.outgoingTransformations.put(id, transformationEdge);
                target.incomingTransformations.put(id, transformationEdge);
                idTransformationMapping.put(id, transformationEdge);
            }
        }
    }

    /**
     * Adds a viewer provider under the given identifier to the graph.
     * 
     * @param id
     *            the identifier for the viewer provider
     * @param viewerProvider
     *            the viewer provider
     */
    public void addViewerProvider(final String id, final IViewerProvider viewerProvider) {
        // only add the viewer provider if it is not already present
        if (id.length() > 0 && !viewerProviders.contains(viewerProvider)
                && !idViewerProviderMapping.containsKey(id)) {
            // only add the viewer provider if the model class can be determined
            Class<?> modelClass = viewerProvider.getClass();
            if (modelClass != null) {
                viewerProviders.add(viewerProvider);
                // insert the model class into the graph if it is not present already
                ClassNode classNode = createClassNode(modelClass);
                // insert the viewer provider
                ViewerProviderData viewerProviderData = new ViewerProviderData();
                viewerProviderData.viewerProvider = viewerProvider;
                viewerProviderData.classNode = classNode;
                classNode.viewerProviders.put(id, viewerProviderData);
                idViewerProviderMapping.put(id, viewerProviderData);
            }
        }
    }

    /**
     * Creates a view context for the given model.
     * 
     * @param model
     *            the model
     * @return the view context
     */
    public ViewContext createViewContext(final Object model) {
        return null;
    }

    /**
     * Creates a view context for the given model using the specified model transformation if
     * possible.
     * 
     * @param model
     *            the model
     * @param modelTransformation
     *            the model transformation
     * @return the view context
     */
    public ViewContext createViewContext(final Object model,
            final IModelTransformation<?, ?> modelTransformation) {
        return null;
    }

    /**
     * Creates a view context for the given model using the specified viewer provider if possible.
     * 
     * @param model
     *            the model
     * @param viewerProvider
     *            the viewer provider
     * @return the view context
     */
    public ViewContext createViewContext(final Object model, final IViewerProvider viewerProvider) {
        return null;
    }

    /**
     * Creates a view context for the given model using the specified model transformation and
     * viewer if possible.
     * 
     * @param model
     *            the model
     * @param modelTransformation
     *            the model transformation
     * @param viewerProvider
     *            the viewer provider
     * @return the view context
     */
    public ViewContext createViewContext(final Object model,
            final IModelTransformation<?, ?> modelTransformation,
            final IViewerProvider viewerProvider) {
        return null;
    }

    /**
     * Computes a list of transformation edges which form the shortest path from the start to the
     * target class node.
     * 
     * @param start
     *            the start node
     * @param target
     *            the target node
     * @return the list of edges
     */
    private List<TransformationEdge> findShortestPath(final ClassNode start, final ClassNode target) {
        List<TransformationEdge> pathEdges = new LinkedList<TransformationEdge>();
        return pathEdges;
    }

    /**
     * Returns the class node representing the given class. Creates a new class node if it does not
     * exist.
     * 
     * @param clazz
     *            the class
     * @return the class node
     */
    private ClassNode createClassNode(final Class<?> clazz) {
        ClassNode node = classNodeMapping.get(clazz);
        if (node == null) {
            node = new ClassNode();
            node.clazz = clazz;
            classNodeMapping.put(clazz, node);
        }
        return node;
    }

    /**
     * The class for nodes in this graph representing model classes.
     */
    private class ClassNode {

        /** the model class. */
        private Class<?> clazz;
        /** the transformations which take instances of the model class as source. */
        private Map<String, TransformationEdge> outgoingTransformations =
                new LinkedHashMap<String, TransformationEdge>();
        /** the transformations which take instances of the model class as target. */
        private Map<String, TransformationEdge> incomingTransformations =
                new LinkedHashMap<String, TransformationEdge>();
        /** the viewer providers which support model instances of the model class. */
        private Map<String, ViewerProviderData> viewerProviders =
                new LinkedHashMap<String, ViewerProviderData>();
        
        private int attributes = 0;

    }

    /**
     * The class representing viewer providers in this graph.
     */
    private class ViewerProviderData {

        /** the viewer provider. */
        private IViewerProvider viewerProvider;
        /** the class node representing the model class supported by the viewer provider. */
        private ClassNode classNode;

    }

    /**
     * The class for edges in this graph representing model transformations.
     */
    private class TransformationEdge {

        /** the model transformation. */
        private IModelTransformation<?, ?> transformation;
        /** the class node representing the source model class for the transformation. */
        private ClassNode source;
        /** the class node representing the target model class for the transformation. */
        private ClassNode target;

    }

}
