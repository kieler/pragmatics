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
package de.cau.cs.kieler.kiml.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.kiml.service.formats.AbstractEmfHandler;
import de.cau.cs.kieler.kiml.service.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;

/**
 * Service class for graph transformations.
 *
 * @author msp
 */
public abstract class TransformationService {
    
    /** identifier of the extension point for layout info. */
    protected static final String EXTP_ID_GRAPH_TRANS
            = "de.cau.cs.kieler.kiml.service.graphTransformers";
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
    private static TransformationService instance;
    
    /**
     * Returns the singleton instance of the transformation service.
     * 
     * @return the singleton instance
     */
    public static TransformationService getInstance() {
        return instance;
    }

    /**
     * Protected constructor to enforce instantiation in subclasses.
     */
    protected TransformationService() {
        // the transformation service is supposed to exist exactly once
        instance = this;
    }
    
    /** mapping of graph format identifiers to their meta-data instances. */
    private Map<String, GraphFormatData> graphFormatMap
            = new LinkedHashMap<String, GraphFormatData>();
    /** additional map of graph format suffixes to data instances. */
    private Map<String, GraphFormatData> formatSuffixMap
            = new HashMap<String, GraphFormatData>();

    /**
     * Report an error that occurred while reading extensions.
     * 
     * @param extensionPoint the identifier of the extension point
     * @param element the configuration element
     * @param attribute the attribute that contains an invalid entry
     * @param exception an optional exception that was caused by the invalid entry
     */
    protected abstract void reportError(String extensionPoint, IConfigurationElement element,
            String attribute, Throwable exception);

    /**
     * Report an error that occurred while reading extensions.
     * 
     * @param exception a core exception holding a status with further information
     */
    protected abstract void reportError(CoreException exception);
    
    /**
     * Loads and registers all graph transformer extensions from the extension point.
     */
    protected final void loadGraphTransExtensions() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_GRAPH_TRANS);

        for (IConfigurationElement element : extensions) {
            if (ELEMENT_HANDLER.equals(element.getName())) {
                // register a transformation handler from the extension
                try {
                    ITransformationHandler<?> handler = (ITransformationHandler<?>)
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
                            StringTokenizer tokenizer = new StringTokenizer(extElem, ",");
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
    public GraphFormatData getFormatData(final String id) {
        return graphFormatMap.get(id);
    }
    
    /**
     * Returns all registered graph format data. 
     * 
     * @return a collection of graph format data
     */
    public Collection<GraphFormatData> getFormatData() {
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

}
