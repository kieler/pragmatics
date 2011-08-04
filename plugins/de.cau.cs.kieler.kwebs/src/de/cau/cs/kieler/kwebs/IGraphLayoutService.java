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

package de.cau.cs.kieler.kwebs;

import javax.jws.WebService;

/**
 * This is the definition of the interface published by the layout service.
 * By implementing its methods the server provides the layout functionality.
 * The client can use this interface for dynamic stub generation.
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *     
 * @author swe
 */
@WebService(
    //endpointInterface = "de.cau.cs.kieler.kwebs.IGraphLayoutService",
    name = "LayoutServicePort",
    portName = "LayoutServicePort",
    serviceName = "LayoutService",
    targetNamespace = "http://rtsys.informatik.uni-kiel.de/layout"
) 
public interface IGraphLayoutService {

    /**
     * This is the main method published by the web service.
     *
     * @param serializedGraph the graph in serialized form to do layout on
     * @param format the model and the form of serialization used {@see Formats}
     * @param options optional layout options like algorithm to be used
     *
     * @return String containing the graph on which layout was done in the
     *         same serialized form as was given by the client
     */
    String graphLayout(String serializedGraph, String format, GraphLayoutOption[] options);

    /**
     * Returns the meta data of the layout service, e.g. what layout
     * algorithms and options are supported.
     *
     * @return String containing a XMI representation of the meta data
     */
    String getServiceData();

}
