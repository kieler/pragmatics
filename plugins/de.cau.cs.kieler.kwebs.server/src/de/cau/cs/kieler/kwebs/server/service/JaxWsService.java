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
import de.cau.cs.kieler.kwebs.RemoteServiceException;
import de.cau.cs.kieler.kwebs.jaxws.LayoutServicePort;
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
     */
    public String graphLayout(final String serializedGraph, final String format, 
        final List<GraphLayoutOption> options) { 
        try {
            return layout(serializedGraph, format, options);
        } catch (Exception e) {         
            throw new RemoteServiceException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getServiceData() {
        try {
            return ServerLayoutDataService.getServiceData();
        } catch (Exception e) {
            throw new RemoteServiceException(e.getMessage());
        }
    }

}
