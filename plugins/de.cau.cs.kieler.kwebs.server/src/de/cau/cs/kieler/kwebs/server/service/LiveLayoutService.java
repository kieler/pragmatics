/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.service;

import java.util.List;

import de.cau.cs.kieler.kwebs.server.layout.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutMetaDataService;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.servicedata.ServiceData;

/**
 * Dummy implementation that just forwards the layout request to the {@link AbstractService} class.
 * 
 * @author uru
 */
public class LiveLayoutService extends AbstractService {

    
    /**
     * @see AbstractService#layout(String, String, String, List)
     * 
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
     */
    public String doLayout(final String serializedGraph, final String informat,
            final String outformat, final List<GraphLayoutOption> options) {
        // perform the actual layout
        String result = layout(serializedGraph, informat, outformat, options);
        
        return result;
    }
    
    /**
     * Returns the layout services meta data as XMI.
     *  
     * @return the layout services meta data as XMI.
     */
    public ServiceData getServiceData() {
        return ServerLayoutMetaDataService.getInstance().getServiceDataModel();
    }
    
    /**
     * Returns the preview image associated with a remotely available layout
     * algorithm.
     *  
     * @param previewImage
     *            the identifier of the preview image as defined in the servers meta data
     * @return the preview image as byte array
     */
    public byte[] getPreviewImage(final String previewImage) {
        Logger.log(Severity.DEBUG, "Handling preview image request");
        try {
            byte[] result = ServerLayoutMetaDataService.getInstance().getPreviewImage(previewImage);
            Logger.log(Severity.DEBUG, "Handling preview image request succeeded");
            return result;
        } catch (Exception e) {
            Logger.log(Severity.WARNING, 
                "Handling preview image request failed: " + e.getMessage(), e
            );
        }
        return null;
    }
};
