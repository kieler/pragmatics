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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kwebs.formats.Formats;

/**
 * Transformer for the KGraph model and XMI serialization.
 *
 * @kieler.rating 2011-05-04 red
 *
 * @author swe
 */
public class KGraphXmiTransformer implements IGraphTransformer {

    // implementation of the interface {@link IGraphTransformer}

    /**
     * {@inheritDoc}
     */
    public final Object deserialize(final String serializedGraph) {
        KNode graph = null;
        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream(
                serializedGraph.getBytes("UTF-8")
                //serializedGraph.getBytes()
            );
            URI uri = URI.createURI("inputstream://temp.kgraph");
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            EObject eObject = null;
            try {
                Map<String, String> options = new HashMap<String, String>();
                options.put(XMLResource.OPTION_ENCODING, "UTF-8");
                //resource.load(inStream, Collections.EMPTY_MAP);
                resource.load(inStream, options);
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
    public final String serialize(final Object graph) {
        String xmi = null;
        if (!(graph instanceof KNode)) {
            throw new TransformationException("Given graph object is not a KGraph instance");
        }
        try {
            EcoreUtil.resolveAll((KNode) graph);
            persistDataElements((KNode) graph);            
            URI uri = URI.createURI("outputstream://temp.kgraph");
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            resource.unload();
            resource.getContents().add((KNode) graph);            
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            //resource.save(outStream, Collections.EMPTY_MAP);
            resource.save(outStream, options);
            outStream.flush();
            xmi = new String(outStream.toByteArray(), "UTF-8");
            outStream.close();
            unpersistDataElements((KNode) graph);
        } catch (Exception e) {
            throw new TransformationException(e);
        }
        return xmi;
    }

    /**
     * {@inheritDoc}
     */
    public KNode deriveLayout(final Object graph) {
        return (KNode) graph;
    }
    
    /**
     * {@inheritDoc}
     */
    public void applyLayout(final Object graph, final KNode layout) {
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
/*            
            if (services == null) {
                throw new IllegalStateException("No service data instance registered");
            }
*/            
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
                            if (services != null) { // For use in KIELER
                                LayoutOptionData<?> layoutOptionData = services.getOptionData(key);
                                if (layoutOptionData != null) {
                                    Object layoutOptionValue = layoutOptionData.
                                        parseValue(persistentEntry.getValue());
                                    if (layoutOptionValue != null) {
                                        kgraphData.setProperty(layoutOptionData, layoutOptionValue);
                                    }
                                }
                            } else { // For use outside of KIELER
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
