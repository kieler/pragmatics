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

package de.cau.cs.kieler.kiml.service;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.formats.AbstractEmfHandler;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Transformer for the KGraph model and XMI serialization.
 *
 * @author swe
 */
public class KGraphHandler extends AbstractEmfHandler<KNode> {
    
    /** the KGraph format identifier. */
    public static final String FORMAT = "de.cau.cs.kieler.kgraph";

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize(final KNode graph) {
        KimlUtil.persistDataElements(graph);
        return super.serialize(graph);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deserialize(final String serializedGraph,
            final TransformationData<KNode, KNode> transData) {
        super.deserialize(serializedGraph, transData);
        unpersistDataElements(transData.getSourceGraph());
    }

    /**
     * {@inheritDoc}
     */
    protected ResourceSet createResourceSet() {
        Map<String, Object> extensionMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
        if (!extensionMap.containsKey("kgraph")) {
            extensionMap.put("kgraph", new XMIResourceFactoryImpl());
        }
        EPackage.Registry registry = EPackage.Registry.INSTANCE;
        if (!registry.containsKey(KGraphPackage.eNS_URI)) {
            registry.put(KGraphPackage.eNS_URI, KGraphPackage.eINSTANCE);
        }
        if (!registry.containsKey(KLayoutDataPackage.eNS_URI)) {
            registry.put(KLayoutDataPackage.eNS_URI, KLayoutDataPackage.eINSTANCE);
        }
        return new ResourceSetImpl();
    }
    
    /** the identity transformer for KGraph. */
    private static final IGraphTransformer<KNode, KNode> TRANSFORMER
            = new IGraphTransformer<KNode, KNode>() {
        
        public void transform(TransformationData<KNode, KNode> data) {
            KNode graph = data.getSourceGraph();        
            // Make sure all graph elements are configured according to specs
            validate(graph);
            // Forward the validated graph as layout graph
            data.getTargetGraphs().add(graph);
        }
        
        public void transferLayout(TransformationData<KNode, KNode> data) {
            // nothing to do
        }
    };

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, KNode> getImporter() {
        return TRANSFORMER;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, KNode> getExporter() {
        return TRANSFORMER;
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
            LayoutDataService dataService = LayoutDataService.getInstance();
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
                            LayoutOptionData<?> layoutOptionData = null;
                            // If we run inside of KIELER we try to get the layout option
                            // from the data service
                            if (dataService != null) { 
                                layoutOptionData = dataService.getOptionData(key);
                            }
                            // If we have a valid layout option, parse its value and
                            // annotate graph
                            if (layoutOptionData != null) {
                                Object layoutOptionValue = layoutOptionData
                                    .parseValue(persistentEntry.getValue());
                                if (layoutOptionValue != null) {
                                    kgraphData.setProperty(layoutOptionData, layoutOptionValue);
                                }
                            // Unknown options are wrapped by a dynamically instantiated one
                            } else {
                                IProperty<String> property = new Property<String>(key);
                                kgraphData.setProperty(property, value);
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Ensures that each element contained in a KGraph instance is attributed correctly for
     * usage in KIML.
     * 
     * @param graph
     *            the KGraph instance to validate the elements of 
     */
    private static void validate(final KNode graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph instance is null");
        }
        KLayoutDataFactory layoutFactory = KLayoutDataFactory.eINSTANCE;
        Iterator<EObject> contentIter = graph.eAllContents();
        while (contentIter.hasNext()) {
            EObject element = contentIter.next();
            // Make sure nodes are OK
            if (element instanceof KNode) {
                KNode node = (KNode) element;
                KShapeLayout sLayout = node.getData(KShapeLayout.class);
                if (sLayout == null) {
                    sLayout = layoutFactory.createKShapeLayout();                   
                    node.getData().add(sLayout);
                } 
                if (sLayout.getInsets() == null) {
                    sLayout.setInsets(layoutFactory.createKInsets());
                }
            // Make sure ports are OK           
            } else if (element instanceof KPort) {
                KPort port = (KPort) element;
                KShapeLayout sLayout = port.getData(KShapeLayout.class);
                if (sLayout == null) {
                    port.getData().add(layoutFactory.createKShapeLayout());
                }    
            // Make sure labels are OK
            } else if (element instanceof KLabel) {
                KLabel label = (KLabel) element;
                KShapeLayout sLayout = label.getData(KShapeLayout.class);
                if (sLayout == null) {
                    label.getData().add(layoutFactory.createKShapeLayout());
                }
                if (label.getText() == null) {
                    label.setText("");
                }
            // Make sure edges are OK
            } else if (element instanceof KEdge) {
                KEdge edge = (KEdge) element;
                KEdgeLayout eLayout = edge.getData(KEdgeLayout.class);
                if (eLayout == null) {
                    eLayout = layoutFactory.createKEdgeLayout();
                    eLayout.setSourcePoint(layoutFactory.createKPoint());
                    eLayout.setTargetPoint(layoutFactory.createKPoint());
                    edge.getData().add(eLayout);
                }
            }
        }
    }

}
