/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.nonosgi;

import org.eclipse.equinox.nonosgi.registry.RegistryFactoryHelper;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.DefaultFactory;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.formats.IGraphFormatHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;
import de.cau.cs.kieler.kiml.nonosgi.service.NonOsgiExtensionLayoutMetaDataService;

/**
 * This class provides an interface to KIELER layout with graph format support, such as JSON, in a
 * pure Java environment. In other words, no active OSGI or Eclipse environment is required. The
 * dependencies are resolved via jar depenencies.
 * 
 * See this <a href=
 * "https://angelozerr.wordpress.com/2010/09/14/eclipse-extension-points-and-extensions-without-osgi/"
 * >link</a> for further information on the non osgi execution and for the original sources.
 *
 * @author uru
 */
public final class KielerNonOsgiLayout {

    // Initialization
    static {

        // this basically initializes the non osgi registry
        RegistryFactoryHelper.getRegistry();

        // Following two lines are copied from
        // de.cau.cs.kieler.kiml.formats.service.KimlServicePlugin#start()

        // Replaced the default service by one that does not
        // require any eclipse platform or ui stuff

        // Initialize the layout meta data service (see LayoutDataService.getInstance())
        LayoutMetaDataService.setInstanceFactory(new DefaultFactory<LayoutMetaDataService>(
                NonOsgiExtensionLayoutMetaDataService.class));

        // Not required here because we don't have any editors or views that provide configurations

        // Initialize the layout configuration service (see LayoutConfigService.getInstance())
        // LayoutConfigService.setInstanceFactory(new DefaultFactory<LayoutConfigService>(
        // ExtensionLayoutConfigService.class));

    }

    private KielerNonOsgiLayout() {
    }

    /**
     * The layout engine we will use internally.
     */
    private static IGraphLayoutEngine layoutEngine = new RecursiveGraphLayoutEngine();

    /**
     * Performs layout
     * 
     * @param serializedGraph
     *            serialized version of the input graph in the desired format
     * @param handler
     *            an {@link IGraphFormatHandler} that is able to handle the format of the input
     *            graph
     * @param importer
     *            an {@link IGraphTransformer} that is able to transform (
     *            {@link IGraphTransformer#transform(TransformationData)}) the input graph and
     *            transfer the layout back (
     *            {@link IGraphTransformer#transferLayout(TransformationData)}).
     * @return A serialized version of the input graph with attached layout information.
     */
    public static <T> String performLayout(final String serializedGraph,
            IGraphFormatHandler<T> handler, IGraphTransformer<T, KNode> importer) {

        String input = serializedGraph;
        TransformationData<T, KNode> transformationData = new TransformationData<T, KNode>();

        handler.deserialize(input, transformationData);

        importer.transform(transformationData);

        for (KNode graph : transformationData.getTargetGraphs()) {
            layoutEngine.layout(graph, new BasicProgressMonitor());
        }

        // write the layout back to the json
        importer.transferLayout(transformationData);

        // Serialize the resulting graph
        TransformationData<KNode, T> outTransData = new TransformationData<KNode, T>();
        outTransData.getTargetGraphs().add(transformationData.getSourceGraph());

        return handler.serialize(outTransData);
    }

}
