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

package de.cau.cs.kieler.kwebs.client.layout;

import java.io.ByteArrayInputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.KwebsClientPlugin;

/**
 * An image descriptor for supporting lazy loading of the preview images via layout web service client.
 *
 * @kieler.rating  2011-08-14 red
 *     
 * @author  swe
 */
public class LayoutServiceImageDescriptor extends ImageDescriptor {

    /** The layout service client used to retrieve the preview image. */
    private ILayoutServiceClient client;
    
    /** The identifier of the preview image. */
    private String previewImage;
    
    /** The cached data of the image. */
    private ImageData imageData;
    
    /**
     * 
     * @param theclient
     *            the layout service client used to retrieve the preview image
     * @param thepreviewImage
     *            the identifier of the preview image
     */
    public LayoutServiceImageDescriptor(final ILayoutServiceClient theclient, 
        final String thepreviewImage) {
        client = theclient;
        previewImage = thepreviewImage;
    }

    /**
     * {@inheritDoc}
     */
    public ImageData getImageData() {
        if (imageData == null) {
            try {
                imageData = new ImageData(new ByteArrayInputStream(client.getPreviewImage(previewImage)));
            } catch (Exception e) {
                StatusManager.getManager().handle(
                    new Status(
                        IStatus.WARNING, 
                        KwebsClientPlugin.PLUGIN_ID, 
                        "Could not create descriptor for preview image: " + previewImage
                    )
                );
            }
        }
        return imageData;
    }

}
