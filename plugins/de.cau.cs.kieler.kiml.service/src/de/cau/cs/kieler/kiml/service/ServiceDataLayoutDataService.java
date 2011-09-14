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

package de.cau.cs.kieler.kiml.service;

import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import de.cau.cs.kieler.kwebs.servicedata.Category;
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.servicedata.LayoutOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutType;
import de.cau.cs.kieler.kwebs.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.servicedata.transformation.ServiceDataXmiTransformer;

/**
 * .
 *
 * @kieler.rating 2011-05-17 red
 * @author swe
 */
public abstract class ServiceDataLayoutDataService extends /*ProgrammaticLayoutDataService*/ExtensionLayoutDataService {

    /** 
     *  The extension elements created from the layout meta data received
     *  from the layout server.
     */
    private IConfigurationElement[] extensions;
    
    /**
     * 
     */
    protected ServiceDataLayoutDataService() {
    }
    
    /**
     * Initializes the layout meta data from a string retrieved from the layout service.
     * The host part is needed for retrieving the preview images.
     * 
     * @param serializedData
     *            the data which contains layout meta data
     */
    protected void initializeFromString(final String serializedData) {        
        try {
            if (serializedData == null) {
                throw new IllegalArgumentException("Server data can not be null");
            }
            ServiceDataXmiTransformer transformer = new ServiceDataXmiTransformer();
            ServiceData serviceData = transformer.deserialize(serializedData);
            Vector<IConfigurationElement> vectorElements = new Vector<IConfigurationElement>();
            for (Category category : serviceData.getCategories()) {   
                vectorElements.add(ServiceDataConfigurationElement.getCategoryElementFromModel(category));
            }
            for (LayoutType type : serviceData.getLayoutTypes()) {                
                vectorElements.add(ServiceDataConfigurationElement.getLayoutTypeElementFromModel(type));
            }
            for (LayoutOption option : serviceData.getLayoutOptions()) {
                vectorElements.add(ServiceDataConfigurationElement.getLayoutOptionElementFromModel(option));
            }
            for (LayoutAlgorithm algorithm : serviceData.getLayoutAlgorithms()) {
                vectorElements.add(ServiceDataConfigurationElement.getLayoutAlgorithmElementFromModel(algorithm));
            }
            extensions = vectorElements.toArray(new IConfigurationElement[0]);
            loadLayoutProviderExtensions();
            //registerProgrammaticOptions();
        } catch (Exception e) {
            reportError("Error while parsing layout server meta data", e);
        }
    }
    
    /**
     * Returns the configuration elements created from the layout meta data from the server.
     * 
     *  @return the configuration elements created from the layout meta data from the server
     */
    @Override
    protected IConfigurationElement[] getProviderExtensions() {
        if (extensions == null) {
            throw new IllegalStateException("Layout data services have not been initialized");
        }
        return extensions;
    }
    
    /**
     * 
     */
    protected void reportError(final String extensionPoint, final IConfigurationElement element,
        final String attribute, final Throwable exception) {
        reportError(
            "Exception occured while parsing meta data:"
            + " EP=" + extensionPoint
            + ", EL=" + element.getName()
            + ", AT=" + attribute,
            exception
        );
    }

    /*
     * (non-Javadoc)
     * @see de.cau.cs.kieler.kiml.service.ExtensionLayoutDataService#reportError(org.eclipse.core.runtime.CoreException)
     */
    protected void reportError(final CoreException e) {
        reportError("Exception occured while parsing meta data", e);
    }

    /**
     * To be implemented by sub classes to handle an error.
     * 
     * @param message
     *            the error message
     */
    protected abstract void reportError(final String message);

    /**
     * To be implemented by sub classes to handle an error.
     * 
     * @param message
     *            the error message
     * @param exception
     *            the exception occurred
     */
    protected abstract void reportError(final String message, final Throwable throwable);

}
