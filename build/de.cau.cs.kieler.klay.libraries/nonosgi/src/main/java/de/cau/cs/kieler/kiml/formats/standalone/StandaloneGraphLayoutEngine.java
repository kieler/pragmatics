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
package de.cau.cs.kieler.kiml.formats.standalone;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.elk.core.IGraphLayoutEngine;
import org.eclipse.elk.core.RecursiveGraphLayoutEngine;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.graph.KNode;
import org.eclipse.equinox.nonosgi.registry.RegistryFactoryHelper;

import de.cau.cs.kieler.kiml.formats.IGraphFormatHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;

/**
 * This class provides an interface to ELK layout with KIELER's graph format support, such as JSON, in a
 * pure Java environment. In other words, no active OSGI or Eclipse environment is required. The
 * dependencies are resolved via jar dependencies.
 * 
 * See this <a href=
 * "https://angelozerr.wordpress.com/2010/09/14/eclipse-extension-points-and-extensions-without-osgi/"
 * >link</a> for further information on the non osgi execution and for the original sources.
 *
 * @author uru
 */
public final class StandaloneGraphLayoutEngine {

    // Initialization
    static {
        // this basically initializes the non osgi registry
        RegistryFactoryHelper.getRegistry();

        // Initialize the layout meta data service
        loadLayoutProviders();
    }
    
    /** identifier of the extension point for layout providers. */
    protected static final String EXTP_ID_LAYOUT_PROVIDERS = "org.eclipse.elk.core.layoutProviders";
    /** name of the 'provider' element in the 'layout providers' extension point. */
    protected static final String ELEMENT_PROVIDER = "provider";
    /** name of the 'class' attribute in the extension points. */
    protected static final String ATTRIBUTE_CLASS = "class";
    
    /**
     * Creates a new instance, loading the extension point information in the process.
     * 
     * Code copied from {@link org.eclipse.elk.core.service.ElkServicePlugin ElkServicePlugin}.
     */
    private static void loadLayoutProviders() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_PROVIDERS);
        LayoutMetaDataService service = LayoutMetaDataService.getInstance();
        
        for (IConfigurationElement element : extensions) {
            try {
                if (ELEMENT_PROVIDER.equals(element.getName())) {
                    ILayoutMetaDataProvider provider = (ILayoutMetaDataProvider)
                            element.createExecutableExtension(ATTRIBUTE_CLASS);
                    
                    if (provider != null) {
                        service.registerLayoutMetaDataProvider(provider);
                    }
                }
            } catch (CoreException exception) {
            	System.err.println(
                        "Error while parsing extension point:" 
                        + "\nElement: " + (element != null ? element.toString() : "<unknown>")
                        + "\n\n"
                    );
            }
        }
    }

    private StandaloneGraphLayoutEngine() { }

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
