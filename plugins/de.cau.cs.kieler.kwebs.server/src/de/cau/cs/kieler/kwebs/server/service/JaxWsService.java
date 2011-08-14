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

package de.cau.cs.kieler.kwebs.server.service;

import java.util.List;

import javax.jws.WebService;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.jaxws.LayoutServicePort;
import de.cau.cs.kieler.kwebs.jaxws.ServiceFault;
import de.cau.cs.kieler.kwebs.jaxws.ServiceFault_Exception;
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService;

/**
 * Main service class to be published as JAX-WS web service.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
// The attributes are necessary because otherwise JAX-WS would use package and class name for
// the definition of the service and port name and the namespace.
@WebService(
    endpointInterface = "de.cau.cs.kieler.kwebs.jaxws.LayoutServicePort",
    name = "LayoutServicePort",
    portName = "LayoutServicePort",
    serviceName = "LayoutService",
    targetNamespace = "http://rtsys.informatik.uni-kiel.de/layout",
    wsdlLocation = "server/kwebs/wsdl/layoutService.wsdl"
)  
public final class JaxWsService extends AbstractService implements LayoutServicePort {

    /**
     * Creates a new instance of the JAX-WS based layout service.
     */
    public JaxWsService() {
        ServerLayoutDataService.create();
    }

    /**
     * {@inheritDoc}
     * @throws ServiceFault_Exception 
     */
    public String graphLayout(final String serializedGraph, final String format, 
        final List<GraphLayoutOption> options) throws ServiceFault_Exception { 
        try {
            return layout(serializedGraph, format, options);
        } catch (Exception e) {  
            throw createException(0, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getServiceData() throws ServiceFault_Exception {
        try {
            return ServerLayoutDataService.getServiceData();
        } catch (Exception e) {
            throw createException(0, e);
        }
    }

    /**
     * Returns the preview image associated with a remotely available layout
     * algorithm.
     *  
     * @param previewImage
     *            the identifier of the preview image as defined in the servers meta data
     * @return the preview image as byte array
     */
    public byte[] getPreviewImage(final String previewImage) throws ServiceFault_Exception {
        try {
            return ServerLayoutDataService.getPreviewImage(previewImage);
        } catch (Exception e) {
            throw createException(0, e);
        }
    }
    
    /**
     * Creates a {@code ServiceFault_Exception} from an exception thrown while serving a service
     * request with the according error code.
     * 
     * @param code
     *            the error code
     * @param throwable
     *            the exception occurred
     * @return the {@code ServiceFault_Exception}
     */
    private ServiceFault_Exception createException(final int code, final Throwable throwable) {        
        ServiceFault fault = new ServiceFault();
        fault.setCode(code);
        String message = throwable.getMessage();
        if (message == null) {
            message = "Unknown cause";
        }
        fault.setMessage(message);
        return new ServiceFault_Exception(message, fault);
    }

}
