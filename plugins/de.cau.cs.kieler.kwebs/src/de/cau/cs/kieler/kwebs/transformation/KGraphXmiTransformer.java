/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.transformation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.EdgeType;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.Shape;
import de.cau.cs.kieler.kwebs.formats.Formats;

/**
 * Transformer for the KGraph model and XMI serialization.
 *
 * @kieler.rating 2011-05-04 red
 *
 * @author swe
 */
public class KGraphXmiTransformer implements IGraphTransformer {

    /** 
     *  Map which indexes the programmatic layout options defined in {@link LayoutOptions}
     *  encapsulated in an appropriate {@link LayoutOptionData} by their identifiers.
     */
    private static Map<String, LayoutOptionData<?>> programaticOptions
        = new HashMap<String, LayoutOptionData<?>>();
    
    /**
     * Build the map
     */
    static {
        addProgramaticOption(LayoutOptions.COMMENT_BOX, Boolean.class);
        addProgramaticOption(LayoutOptions.DIAGRAM_TYPE, String.class);
        addProgramaticOption(LayoutOptions.EDGE_LABEL_PLACEMENT, EdgeLabelPlacement.class);
        addProgramaticOption(LayoutOptions.EDGE_TYPE, EdgeType.class);
        addProgramaticOption(LayoutOptions.FONT_NAME, String.class);
        addProgramaticOption(LayoutOptions.FONT_SIZE, Integer.class);
        addProgramaticOption(LayoutOptions.HYPERNODE, Boolean.class);
        addProgramaticOption(LayoutOptions.MIN_HEIGHT, Float.class);
        addProgramaticOption(LayoutOptions.MIN_WIDTH, Float.class);
        addProgramaticOption(LayoutOptions.NO_LAYOUT, Boolean.class);
        addProgramaticOption(LayoutOptions.OFFSET, Float.class);
        addProgramaticOption(LayoutOptions.PORT_SIDE, PortSide.class);
        addProgramaticOption(LayoutOptions.PORT_RANK, Integer.class);
        addProgramaticOption(LayoutOptions.SHAPE, Shape.class);
    }

    /**
     * Creates an appropriate {@link LayoutOptionData} instance from a programmatic property.
     * 
     * @param property
     *            the property to build a {@code LayoutOptionData} instance from
     * @param optionClass
     *            the class of the property
     */
    private static void addProgramaticOption(final IProperty<?> property, final Class<?> optionClass) {
        LayoutOptionData<Object> optionData = new LayoutOptionData<Object>();
        String id = property.getId();
        String type = null;
        if (optionClass.equals(Boolean.class)) {
            type = LayoutOptionData.BOOLEAN_LITERAL;
        } else if (optionClass.equals(Integer.class)) {
            type = LayoutOptionData.INT_LITERAL;
        } else if (optionClass.equals(String.class)) {
            type = LayoutOptionData.STRING_LITERAL;
        } else if (optionClass.equals(Float.class)) {
            type = LayoutOptionData.FLOAT_LITERAL;
        } else if (Enum.class.isAssignableFrom(optionClass)) {
            type = LayoutOptionData.ENUM_LITERAL;
        }
        if (type == null) {            
            return;
        }
        optionData.setId(id);
        optionData.setType(type);
        if (type == LayoutOptionData.ENUM_LITERAL || type == LayoutOptionData.OBJECT_LITERAL) {
            optionData.setOptionClass(optionClass);
        }
        optionData.setDefault(property.getDefault());
        programaticOptions.put(id, optionData);
    }

    // implementation of the interface {@link IGraphTransformer}

    /**
     * {@inheritDoc}
     */
    public final KNode deserialize(final String serializedGraph) {
        KNode graph = null;
        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream(serializedGraph.getBytes());
            URI uri = URI.createURI("inputstream://temp.kgraph");
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            EObject eObject = null;
            try {
                resource.load(inStream, Collections.EMPTY_MAP);
                eObject = resource.getContents().get(0);
                if (eObject instanceof KNode) {
                    graph = (KNode) eObject;
                    unpersistDataElements(graph);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            inStream.close();
        } catch (Exception e) {
            throw new TransformationException(e);
        }
        return graph;
    }

    /**
     * {@inheritDoc}
     */
    public final String serialize(final KNode graph) {
        String xmi = null;
        try {
            persistDataElements(graph);
            URI uri = URI.createURI("outputstream://temp.kgraph");
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            resource.unload();
            resource.getContents().add(graph);            
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            resource.save(outStream, Collections.EMPTY_MAP);
            outStream.flush();
            xmi = new String(outStream.toByteArray());
            outStream.close();
            unpersistDataElements(graph);
        } catch (Exception e) {
            throw new TransformationException(e);
        }
        return xmi;
    }

    /**
     * {@inheritDoc}
     */
    public final String getSupportedFormat() {
        return Formats.FORMAT_KGRAPH_XMI;
    }

    // Private utility methods

    /**
     * Persists all KGraphData elements of a KNode graph.
     *
     * @param graph
     *            the root element of the graph to persist elements of.
     */
    private void persistDataElements(final KNode graph) {
        if (graph != null) {
            TreeIterator<EObject> iterator = graph.eAllContents();
            EObject eObject = null;
            while (iterator.hasNext()) {
                eObject = iterator.next();
                if (eObject instanceof KGraphData) {
                    ((KGraphData) eObject).makePersistent();
                }
            }
        }
    }

    /**
     * Unpersists all KGraphData elements of a KNode graph.
     *
     * @param graph 
     *            the root element of the graph to unpersist elements of.
     */
    private void unpersistDataElements(final KNode graph) {
        if (graph != null) {
            TreeIterator<EObject> iterator = graph.eAllContents();
            EObject eObject  = null;
            EList<PersistentEntry> persistentEntries = null;
            LayoutDataService services = LayoutDataService.getInstance();
            KGraphData kgraphData = null;
            while (iterator.hasNext()) {
                eObject = iterator.next();
                if (eObject instanceof KGraphData) {
                    kgraphData = ((KGraphData) eObject);
                    persistentEntries = kgraphData.getPersistentEntries();
                    for (PersistentEntry persistentEntry : persistentEntries) {
                        String key = persistentEntry.getKey();
                        String value = persistentEntry.getValue();
                        if (key != null && value != null) {                            
                            LayoutOptionData<?> layoutOptionData = services.getOptionData(key);
                            if (layoutOptionData == null) {
                                layoutOptionData = programaticOptions.get(key);
                            }
                            if (layoutOptionData != null) {
                                Object layoutOptionValue = layoutOptionData.
                                    parseValue(persistentEntry.getValue());
                                if (layoutOptionValue != null) {
                                    kgraphData.setProperty(layoutOptionData, layoutOptionValue);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates a resource set ready to be used with the KGraph and KLayoutData meta model
     * for serialization and deserialization in XMI.
     *
     * @return A resource set ready to be used with the KGraph and KLayoutData meta model
     *         for serialization and deserialization in XMI
     */
    private ResourceSet createResourceSet() {
        ResourceSet resourceset = new ResourceSetImpl();
        resourceset.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
            Resource.Factory.Registry.DEFAULT_EXTENSION,
            new XMIResourceFactoryImpl()
        );
        resourceset.getPackageRegistry().put(
            KGraphPackage.eNS_URI,
            KGraphPackage.eINSTANCE
        );
        resourceset.getPackageRegistry().put(
            KLayoutDataPackage.eNS_URI,
            KLayoutDataPackage.eINSTANCE
        );
        return resourceset;
    }

}
