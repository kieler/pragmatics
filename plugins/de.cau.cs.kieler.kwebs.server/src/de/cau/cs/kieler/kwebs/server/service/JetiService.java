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

package de.cau.cs.kieler.kwebs.server.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.RemoteServiceException;
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService;
import de.cau.cs.kieler.kwebs.util.Resources;
import de.unido.ls5.eti.toolserver.InputFileReference;
import de.unido.ls5.eti.toolserver.OutputFileReference;

/**
 * Implementation of the jETI based layout service.
 * 
 * @kieler.rating  2011-08-25 proposed yellow
 *      reviewed by ckru, msp, mri
 *      
 * @author  swe
 */
public class JetiService extends AbstractService {

    /**
     * Creates an instance of the jETI based layout service. This class is initialized
     * from the jETI tool server every time a layout request is being received.
     */
    public JetiService() {
        super();
    }

    /**
     * Does layout on a given graph.
     * 
     * @param inRef
     *            reference to the input file in which the graph to do layout on is stored
     * @param outRef
     *            reference to the output file in which the graph which was layout done 
     *            on is to be stored
     * @param format
     *            the serial format of the graph {@see Formats}
     * @throws Exception
     *             if an error occurs
     */
    public final void graphLayout(final InputFileReference inRef, final OutputFileReference outRef, 
        final String format) throws Exception {
        graphLayout(inRef, outRef, format, null);
    }

    /**
     * Does layout on a given graph.
     * 
     * @param inRef
     *            reference to the input file in which the graph to do layout on is stored
     * @param outRef
     *            reference to the output file in which the graph which was layout done 
     *            on is to be stored
     * @param format
     *            the serial format of the graph {@see Formats}
     * @param serializedOptions
     *            optional layout options in their serialized form {@see GraphLayouterOption}
     *            if an exception occurs while processing the request
     * @throws Exception
     *             if an error occurs
     */
    public final void graphLayout(final InputFileReference inRef, final OutputFileReference outRef,
        final String format, final String serializedOptions) throws Exception {
        try {
            byte[] data = Resources.readFileAsByteArray(inRef.toString());
            String serializedGraph = new String(data, "UTF-8");
            List<GraphLayoutOption> options = null;
            if (serializedOptions != null) {
                options = GraphLayoutOption.stringToList(serializedOptions);
            }
            String serializedResult = layout(serializedGraph, format, options);
            Resources.writeFile(outRef.toString(), serializedResult);
        } catch (Exception e) {
            throw new RemoteServiceException(e.getMessage());
        }
    }

    /**
     * Stores the meta data of the layout server in a file. 
     * 
     * @param outRef
     *            reference to the output file
     * @throws Exception
     *            if an exception occurs while processing the request
     */
    public final void getServiceData(final OutputFileReference outRef)
        throws Exception {
        try {
            Resources.writeFile(
                outRef.toString(),
                ServerLayoutDataService.getInstance().getServiceData()
            );
        } catch (Exception e) {
            throw new RemoteServiceException(e.getMessage());
        }
    }

    /**
     * Stores the requested preview image in a file. 
     * 
     * @param previewImage
     *            the identifier of the preview image
     * @param outRef
     *            reference to the preview image file
     * @throws Exception
     *            if an exception occurs while processing the request
     */
    public final void getPreviewImage(final String previewImage, final OutputFileReference outRef)
        throws Exception {
        if (previewImage == null) {
            throw new RemoteServiceException("No preview image identifier");
        }
        byte[] data = ServerLayoutDataService.getInstance().getPreviewImage(previewImage);
        if (data == null) {
            throw new RemoteServiceException("Preview image identifier not valid");
        }
        try {
            Resources.writeFile(
                outRef.toString(),
                new ByteArrayInputStream(data)
            );
        } catch (Exception e) {
            throw new RemoteServiceException(e.getMessage());
        }
    }

}
