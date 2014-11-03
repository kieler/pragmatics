/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.kiml.formats;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.DefaultFactory;
import de.cau.cs.kieler.core.alg.IFactory;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Service class for graph formats.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class GraphFormatsService {

    /** the plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kiml.formats";
    
    /** identifier of the extension point for layout info. */
    protected static final String EXTP_ID_GRAPH_TRANS
            = "de.cau.cs.kieler.kiml.formats.graphFormats";
    /** name of the 'handler' element in the 'graph transformer' extension point. */
    protected static final String ELEMENT_HANDLER = "handler";
    /** name of the 'class' attribute in the extension points. */
    protected static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'description' attribute in the extension points. */
    protected static final String ATTRIBUTE_DESCRIPTION = "description";
    /** name of the 'extensions' attribute in the extension points. */
    protected static final String ATTRIBUTE_EXTENSIONS = "extensions";
    /** name of the 'id' attribute in the extension points. */
    protected static final String ATTRIBUTE_ID = "id";
    /** name of the 'name' attribute in the extension points. */
    protected static final String ATTRIBUTE_NAME = "name";
    
    /** the singleton instance of the transformation service. */
    private static GraphFormatsService instance;
    /** the factory for creation of service instances. */
    private static IFactory<? extends GraphFormatsService> instanceFactory
            = new DefaultFactory<GraphFormatsService>(GraphFormatsService.class);
    
    /**
     * Returns the singleton instance of the transformation service.
     * 
     * @return the singleton instance
     */
    public static synchronized GraphFormatsService getInstance() {
        if (instance == null) {
            instance = instanceFactory.create();
        }
        return instance;
    }
    
    /**
     * Set the factory for creating instances. If an instance is already created, it is cleared
     * so the next call to {@link #getInstance()} uses the new factory.
     * 
     * @param factory an instance factory
     */
    public static void setInstanceFactory(final IFactory<? extends GraphFormatsService> factory) {
        instanceFactory = factory;
        instance = null;
    }
    

    /**
     * Load all registered extensions for the graph formats extension point.
     */
    public GraphFormatsService() {
        loadGraphTransExtensions();
    }
    
    /** mapping of graph format identifiers to their meta-data instances. */
    private final Map<String, GraphFormatData> graphFormatMap
            = new LinkedHashMap<String, GraphFormatData>();
    /** additional map of graph format suffixes to data instances. */
    private final Map<String, GraphFormatData> formatSuffixMap
            = new HashMap<String, GraphFormatData>();

    /**
     * Report an error that occurred while reading extensions. May be overridden by subclasses
     * in order to report errors in a different way.
     * 
     * @param extensionPoint the identifier of the extension point
     * @param element the configuration element
     * @param attribute the attribute that contains an invalid entry
     * @param exception an optional exception that was caused by the invalid entry
     */
    protected void reportError(final String extensionPoint, final IConfigurationElement element,
            final String attribute, final Throwable exception) {
        String message;
        if (element != null && attribute != null) {
            message = "Extension point " + extensionPoint + ": Invalid entry in attribute '"
                    + attribute + "' of element " + element.getName() + ", contributed by "
                    + element.getContributor().getName();
        } else {
            message = "Extension point " + extensionPoint
                    + ": An error occured while loading extensions.";
        }
        IStatus status = new Status(IStatus.WARNING, PLUGIN_ID, 0, message, exception);
        StatusManager.getManager().handle(status);
    }

    /**
     * Report an error that occurred while reading extensions. May be overridden by subclasses
     * in order to report errors in a different way.
     * 
     * @param exception a core exception holding a status with further information
     */
    protected void reportError(final CoreException exception) {
        StatusManager.getManager().handle(exception, PLUGIN_ID);
    }
    
    /**
     * Loads and registers all graph transformer extensions from the extension point.
     */
    private void loadGraphTransExtensions() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_GRAPH_TRANS);

        for (IConfigurationElement element : extensions) {
            if (ELEMENT_HANDLER.equals(element.getName())) {
                // register a transformation handler from the extension
                try {
                    IGraphFormatHandler<?> handler = (IGraphFormatHandler<?>)
                            element.createExecutableExtension(ATTRIBUTE_CLASS);
                    String id = element.getAttribute(ATTRIBUTE_ID);
                    String name = element.getAttribute(ATTRIBUTE_NAME);
                    if (handler == null) {
                        reportError(EXTP_ID_GRAPH_TRANS, element, ATTRIBUTE_CLASS, null);
                    } else if (id == null || id.length() == 0) {
                        reportError(EXTP_ID_GRAPH_TRANS, element, ATTRIBUTE_ID, null);
                    } else if (name == null || name.length() == 0) {
                        reportError(EXTP_ID_GRAPH_TRANS, element, ATTRIBUTE_NAME, null);
                    } else {
                        GraphFormatData formatData = new GraphFormatData();
                        formatData.setId(id);
                        formatData.setName(name);
                        formatData.setDescription(element.getAttribute(ATTRIBUTE_DESCRIPTION));
                        formatData.setHandler(handler);
                        String extElem = element.getAttribute(ATTRIBUTE_EXTENSIONS);
                        if (extElem != null) {
                            StringTokenizer tokenizer = new StringTokenizer(extElem, ", \t");
                            String[] extArray = new String[tokenizer.countTokens()];
                            for (int i = 0; i < extArray.length; i++) {
                                extArray[i] = tokenizer.nextToken();
                            }
                            formatData.setExtensions(extArray);
                        }
                        graphFormatMap.put(id, formatData);
                        if (handler instanceof AbstractEmfHandler<?>) {
                            if (formatData.getExtensions() == null
                                    || formatData.getExtensions().length == 0) {
                                reportError(EXTP_ID_GRAPH_TRANS, element, ATTRIBUTE_EXTENSIONS, null);
                            } else {
                                String extension = formatData.getExtensions()[0];
                                ((AbstractEmfHandler<?>) handler).setFileExtension(extension);
                            }
                        }
                    }
                } catch (CoreException exception) {
                    reportError(exception);
                }
            }
        }
    }
    
    /**
     * Returns the graph format data for the given identifier.
     * 
     * @param id a graph format identifier
     * @return the corresponding format data, or {@code null} if there is none with the given id
     */
    public final GraphFormatData getFormatData(final String id) {
        return graphFormatMap.get(id);
    }
    
    /**
     * Returns all registered graph format data. 
     * 
     * @return a collection of graph format data
     */
    public final Collection<GraphFormatData> getFormatData() {
        return Collections.unmodifiableCollection(graphFormatMap.values());
    }
    
    /**
     * Returns a graph format data that has the given suffix in its identifier.
     * 
     * @param suffix
     *            a graph format identifier suffix
     * @return the first graph format data that has the given suffix
     */
    public final GraphFormatData getFormatDataBySuffix(final String suffix) {
        GraphFormatData data = graphFormatMap.get(suffix);
        if (data == null) {
            data = formatSuffixMap.get(suffix);
            if (data == null) {
                for (GraphFormatData d : graphFormatMap.values()) {
                    // check format identifiers
                    String id = d.getId();
                    if (id.endsWith(suffix) && (suffix.length() == id.length()
                            || id.charAt(id.length() - suffix.length() - 1) == '.')) {
                        formatSuffixMap.put(suffix, d);
                        return d;
                    }
                    // check file extensions
                    for (String ext : d.getExtensions()) {
                        if (ext.equals(suffix)) {
                            formatSuffixMap.put(suffix, d);
                            return d;
                        }
                    }
                }
            }
        }
        return data;
    }
    
    /**
     * Load the given file and transform it to the KGraph format.
     * 
     * @param file a file
     * @return the contained graphs as KGraph instances
     * @throws IOException if reading the file fails
     * @throws CoreException if accessing the file fails
     */
    public KNode[] loadKGraph(final IFile file) throws IOException, CoreException {
        return loadKGraph(file.getContents(), file.getFileExtension());
    }

    /**
     * Load the given file and transform it to the KGraph format.
     * 
     * @param inputStream an input stream containing the graph data
     * @param extension the file extension used to determine the graph format
     * @return the contained graphs as KGraph instances
     * @throws IOException if reading the file fails
     * @throws CoreException if accessing the file fails
     */
    public KNode[] loadKGraph(final InputStream inputStream, final String extension)
            throws IOException, CoreException {
        for (GraphFormatData formatData : graphFormatMap.values()) {
            for (String fe : formatData.getExtensions()) {
                if (fe.equalsIgnoreCase(extension)) {
                    // a matching extension was found -- try to load the file
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int c = inputStream.read();
                    while (c >= 0) {
                        baos.write(c);
                        c = inputStream.read();
                    }
                    inputStream.close();
                    try {
                        return loadKGraph(baos.toString(), formatData.getHandler());
                    } catch (TransformationException exception) {
                        if (exception.getCause() instanceof IOException) {
                            throw (IOException) exception.getCause();
                        } else if (exception.getCause() instanceof CoreException) {
                            throw (CoreException) exception.getCause();
                        } else {
                            throw new IOException(exception);
                        }
                    }
                }
            }
        }
        throw new IOException("The extension of the given file is unknown.");
    }
    
    /**
     * Load KGraph instances from the given serialized graph.
     * 
     * @param serializedGraph a serialized graph
     * @param handler the graph handler
     * @return an array of contained graphs
     */
    private static <T> KNode[] loadKGraph(final String serializedGraph,
            final IGraphFormatHandler<T> handler) {
        Map<String, Boolean> parserFeatures = Maps.newHashMap();
        parserFeatures.put(
                "http://xml.org/sax/features/validation", //$NON-NLS-1$
                Boolean.FALSE);
        parserFeatures.put(
                "http://apache.org/xml/features/nonvalidating/load-dtd-grammar", //$NON-NLS-1$
                Boolean.FALSE);
        parserFeatures.put(
                "http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
                Boolean.FALSE);
        Map<Object, Object> xmlOptions = Maps.newHashMap();
        xmlOptions.put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
        TransformationData<T, KNode> transData = new TransformationData<T, KNode>();
        transData.setProperty(AbstractEmfHandler.XML_OPTIONS, xmlOptions);
        
        handler.deserialize(serializedGraph, transData);
        handler.getImporter().transform(transData);
        return transData.getTargetGraphs().toArray(new KNode[transData.getTargetGraphs().size()]);
    }

}
