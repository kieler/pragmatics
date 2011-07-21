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
     * @param graph the root element of the graph to persist elements of.
     */
    private void persistDataElements(final KNode graph) {
        if (graph != null) {
            TreeIterator<EObject> teo = graph.eAllContents();
            EObject eObject = null;
            while (teo.hasNext()) {
                eObject = teo.next();
                if (eObject instanceof KGraphData) {
                    ((KGraphData) eObject).makePersistent();
                }
            }
        }
    }

    /**
     * Unpersists all KGraphData elements of a KNode graph.
     *
     * @param graph the root element of the graph to unpersist elements of.
     */
    private void unpersistDataElements(final KNode graph) {
        if (graph != null) {
            TreeIterator<EObject> teo = graph.eAllContents();
            EObject eObject  = null;
            EList<PersistentEntry> pEntries = null;
            LayoutDataService services = LayoutDataService.getInstance();
            KGraphData kgd = null;
            while (teo.hasNext()) {
                eObject = teo.next();
                if (eObject instanceof KGraphData) {
                    kgd = ((KGraphData) eObject);
                    pEntries = kgd.getPersistentEntries();
                    for (PersistentEntry pe : pEntries) {
                        LayoutOptionData<?> lod
                            = services.getOptionData(pe.getKey());
                        if (lod != null) {
                            Object value = lod.parseValue(pe.getValue());
                            if (value != null) {
                                kgd.setProperty(lod, value);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates a resource set ready to be used with the KGraph and KLayoutData meta model
     * and XMI de-/serialization.
     *
     * @return A resource set ready to be used with the KGraph and KLayoutData meta model
     *         and XMI de-/serialization
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
