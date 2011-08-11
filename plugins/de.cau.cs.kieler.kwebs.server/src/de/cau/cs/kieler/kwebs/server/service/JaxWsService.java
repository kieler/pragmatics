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

import javax.jws.HandlerChain;
import javax.jws.WebService;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.RemoteServiceException;
import de.cau.cs.kieler.kwebs.jaxws.LayoutServicePort;
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService;
import de.cau.cs.kieler.kwebs.util.Resources;

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
//@HandlerChain(file = "handlerchain/handlerchain.xml")  
public final class JaxWsService extends AbstractService implements LayoutServicePort {
private int sCount = 0;
private int fCount = 0;
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
            String result = layout(serializedGraph, format, options);
            handle(serializedGraph, "succeeded", sCount++);
            return result;
            //return layout(serializedGraph, format, options);
        } catch (Exception e) {
            handle(serializedGraph, "failed", fCount++);
            throw new RemoteServiceException(e.getMessage());
        }
    }

    /** */
    private static final String ROOT
        = "/home/layout/kwebs/testdata";

    /**
     * 
     * @param  
     * @param 
     * @param
     */
    private void handle(final String serializedGraph, final String prefix, final int index) {
        try {
            Resources.writeFile(ROOT + "/" + prefix + "_" + index + ".xmi", serializedGraph);
        } catch (Exception e) {
            // Ignore
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
