/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.service;

import java.util.Hashtable;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.service.GraphLayouterOption;
import de.cau.cs.kieler.kwebs.transformation.IGraphTransformer;

/**
 * .
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public abstract class AbstractService {

    /** The layout engine used. */
    private RecursiveGraphLayoutEngine layoutEngine
        = new RecursiveGraphLayoutEngine(null);

    /** Mapping of format identifiers {@see Formats} to transformer instances. */
    private Hashtable<String, IGraphTransformer> transformers 
        = new Hashtable<String, IGraphTransformer>();

    /** */
    private static final String EXTENSIONPOINT_ID
        = "de.cau.cs.kieler.kwebs.server.configuration";

    /** */
    private static final String ELEMENT_TRANSFORMER
        = "transformer";

    /** */
    private static final String ATTRIBUTE_SUPPORTEDFORMAT
        = "supportedFormat";

    /** */
    private static final String ATTRIBUTE_IMPLEMENTATIONCLASS
        = "implementationClass";

    /**
     * 
     */
    protected AbstractService() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        for (IConfigurationElement element : registry.getConfigurationElementsFor(EXTENSIONPOINT_ID)) {
            if (element.getName().equals(ELEMENT_TRANSFORMER)) {
                String format = element.getAttribute(ATTRIBUTE_SUPPORTEDFORMAT);
                String implementation = element.getAttribute(ATTRIBUTE_IMPLEMENTATIONCLASS);
                if (Formats.isSupportedFormat(format)) {
                    if (!transformers.containsKey(format)) {
                        try {
                            Bundle contributor 
                                = Platform.getBundle(element.getContributor().getName());
                            IGraphTransformer transformer 
                                = (IGraphTransformer)
                                      (contributor.loadClass(implementation).newInstance());
                            transformers.put(format, transformer);                                
                        } catch (Exception e) {
                                //FIXME handle
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Base implementation of layout functionality.
     * 
     * @param serializedGraph
     *            the graph to do layout on in serial representation
     * @param format
     *            the format of the serial graph {@see Formats}
     * @param options
     *            the optional layout options
     * @return the graph on which the layout was done in the same format as used for the source graph 
     */
    protected final String layout(final String serializedGraph,
        final String format, final GraphLayouterOption[] options) {
        // parameter testing
        if (serializedGraph == null) {
            throw new IllegalArgumentException("No graph given");
        }
        if (!Formats.isSupportedFormat(format)) {
            throw new IllegalArgumentException("Format not supported");
        }
        // parse the transmitted layout options
        // currently only specifying a layout algorithm
        // is supported
        LayoutDataService lds = LayoutDataService.getInstance();
        LayoutAlgorithmData lad = null;
        if (options != null) {
            String id = null;
            String value = null;
            for (GraphLayouterOption option : options) {
                id = option.getId();
                value = option.getValue();
                if (id.equals(LayoutOptions.ALGORITHM_ID)) {
                    lad = lds.getAlgorithmData(value);
                }
            }
        }
        //FIXME monitor timeout
        IKielerProgressMonitor monitor = new BasicProgressMonitor();
        IGraphTransformer transformer = transformers.get(format);
        if (transformer == null) {
            throw new IllegalStateException("Transformer could not be acquired");
        }
        monitor.begin("", 1);
        KNode graph = transformer.deserialize(serializedGraph);
        // use client selected algorithm
        if (lad != null) {
            if (lad.getProviderPool() != null) {
                AbstractLayoutProvider provider = null;
                //CHECKSTYLEOFF EmptyBlock
                try {
                    provider = lad.getProviderPool().fetch();
                } catch (Exception e) {
                }
                //CHECKSTYLEON EmptyBlock
                if (provider == null) {
                    throw new IllegalStateException("Layout provider could not be acquired");
                }
                provider.doLayout(graph, monitor);
                lad.getProviderPool().release(provider);
            }
        // use default layout engine
        } else {
            layoutEngine.layout(graph, monitor);
        }
        String serializedResult = transformer.serialize(graph);
        monitor.done();
        return serializedResult;
    }

}
