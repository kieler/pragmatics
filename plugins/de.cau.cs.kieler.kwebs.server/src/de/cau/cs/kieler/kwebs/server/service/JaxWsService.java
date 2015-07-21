/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.service;

import java.util.List;





//import javax.jws.HandlerChain;
import javax.jws.WebService;

import de.cau.cs.kieler.kwebs.server.jaxws.LayoutServicePort;
import de.cau.cs.kieler.kwebs.server.jaxws.ServiceFault;
import de.cau.cs.kieler.kwebs.server.jaxws.ServiceFault_Exception;
import de.cau.cs.kieler.kwebs.server.layout.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutMetaDataService;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.statistics.KIELERStatistics.Granularity;

/**
 * Service endpoint to be published as JAX-WS web service.
 * 
 * @kieler.design 2011-08-25 reviewed by ckru, msp, mri
 * @author swe
 */
// The attributes are necessary because otherwise JAX-WS would use package and class name for
// the definition of the service and port name and the namespace.
@WebService(
    endpointInterface = "de.cau.cs.kieler.kwebs.server.jaxws.LayoutServicePort",
    name = "LayoutServicePort",
    portName = "LayoutServicePort",
    serviceName = "LayoutService",
    targetNamespace = "http://layout.rtsys.informatik.uni-kiel.de/layout",
    wsdlLocation = "server/kwebs/wsdl/layoutService.wsdl"
)  
//@HandlerChain(file = "handlerchain/handlerchain.xml")
public final class JaxWsService extends AbstractService implements LayoutServicePort {

    private static final String STATS_JAXWS_TRY = "kwebs.jaxws.try";
    private static final String STATS_JAXWS_SUCC = "kwebs.jaxws.success";
    
    /**
     * Creates a new instance of the JAX-WS based layout service.
     */
    public JaxWsService() {
        super();
    }

    /**
     * Does remote layout on a given graph in serialized form.
     *  
     * @param serializedGraph
     *            the serialized graph model
     * @param informat
     *            identifier for the input graphs meta model and form of serialization {@see Formats}
     * @param outformat
     *            optional identifier for the output graphs meta model and form of serialization
     * @param options
     *            an optional list of layout options
     * @return the graph which was layout done on in the same serialization as the given graph
     * @throws ServiceFault_Exception
     *             if an error occurs
     */
    public String graphLayout(final String serializedGraph, final String informat,
            final String outformat, final List<GraphLayoutOption> options)
            throws ServiceFault_Exception {
        Logger.log(Severity.DEBUG, "Handling layout request");
        Logger.INSTANCE.getUsageStats().incCounter(Logger.STATS_KWEBS, STATS_JAXWS_TRY, 
                Granularity.DAY | Granularity.MONTH);
        try {
            String result = layout(serializedGraph, informat, outformat, options);
            Logger.log(Severity.DEBUG, "Handling layout request succeeded");
            Logger.INSTANCE.getUsageStats().incCounter(Logger.STATS_KWEBS, STATS_JAXWS_SUCC, 
                    Granularity.DAY | Granularity.MONTH);
            return result;
        } catch (Exception e) {
            Logger.log(Severity.WARNING, 
                "Handling layout request failed: " + e.getMessage(), e
            );
            throw createException(0, e);
        }
    }

    /**
     * Returns the layout services meta data as XMI.
     *  
     * @return the layout services meta data as XMI.
     * @throws ServiceFault_Exception
     *             if an error occurs
     */
    public String getServiceData() throws ServiceFault_Exception {
        try {
            return ServerLayoutMetaDataService.getInstance().getServiceData();
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
     * @throws ServiceFault_Exception
     *             if an error occurs
     */
    public byte[] getPreviewImage(final String previewImage) throws ServiceFault_Exception {
        Logger.log(Severity.DEBUG, "Handling preview image request");
        try {
            byte[] result = ServerLayoutMetaDataService.getInstance().getPreviewImage(previewImage);
            Logger.log(Severity.DEBUG, "Handling preview image request succeeded");
            return result;
        } catch (Exception e) {
            Logger.log(Severity.WARNING, 
                "Handling preview image request failed: " + e.getMessage(), e
            );
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
        StringBuilder message = new StringBuilder();
        Throwable t = throwable;
        String lastm = null;
        while (t != null) {
            String m = t.getMessage();
            if (m != null && !(lastm != null && lastm.endsWith(m))) {
                if (message.length() > 0) {
                    message.append('\n');
                }
                message.append(m);
                lastm = m;
            }
            if (t.getCause() == t) {
                t = null;
            } else {
                t = t.getCause();
            }
        }
        if (message.length() == 0) {
            message.append("Unknown cause");
        }
        ServiceFault fault = new ServiceFault();
        fault.setCode(code);
        fault.setMessage(message.toString());
        return new ServiceFault_Exception(message.toString(), fault);
    }

}
