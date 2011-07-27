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

package de.cau.cs.kieler.kwebs.service;

import javax.jws.WebService;
import javax.jws.WebParam;


/**
 * This is the definition of the interface published by the layout service.
 * By implementing its methods the server provides the layout functionality.
 * The client can use this interface for dynamic stub generation.
 *
 * @kieler.rating 2011-05-04 red
 *
 * @author swe
 */
@WebService(
    //endpointInterface = "de.cau.cs.kieler.kwebs.service.IGraphLayouterService",
    name = "LayoutServicePort",
    portName = "LayoutServicePort",
    serviceName = "LayoutService",
    targetNamespace = "http://kieler.layout/"
)
public interface IGraphLayouterService {

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
    String graphLayout(
        @WebParam(name = "serializedGraph") final String serializedGraph,
        @WebParam(name = "format") final String format,
        @WebParam(name = "options") final GraphLayouterOption[] options
    );

    /**
     * Returns the meta data of the layout service, e.g. what layout
     * algorithms and options are supported.
     *
     * @return String containing a XML representation of the meta data
     */
    String getCapabilities();

    /**
     * The version of the layout service that is published.
     *
     * @return String the version
     */
    String getVersion();

    /**
     * Returns the preview image associated with a remotely available layout
     * algorithm.
     *  
     * @param previewImage
     *            the identifier of the preview image as defined in the servers meta data
     * @return the preview image as byte array
     */
    byte[] getPreviewImage(final String previewImage);
    
}
