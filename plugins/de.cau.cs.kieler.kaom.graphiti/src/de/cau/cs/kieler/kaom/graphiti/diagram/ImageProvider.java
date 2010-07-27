/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

/**
 * 
 * @author atr
 * Provides all the images required in the graphical editor
 */
public class ImageProvider extends AbstractImageProvider {

    /**
     * Prefix gives the directory where the image is located.
     */
    protected static final String PREFIX = "de.cau.cs.kieler.kaom.graphiti";
   // protected static final String PREF = "de.cau.cs.kieler.kaom.edit";
    /**
     * Prefix gives the reference to the image.
     */
    public static final String IMAGE_EREFERENCE = PREFIX + "ereference";
    
    public static final String IMAGE_ENTITY = PREFIX + "Entity";
    public static final String IMAGE_PORT = PREFIX + "Port";
    public static final String IMAGE_LINK = PREFIX + "Link";
    public static final String IMAGE_RELATION = PREFIX + "Relation";
    
    @Override
    protected void addAvailableImages() {
        // TODO Auto-generated method stub
        addImageFilePath(IMAGE_EREFERENCE, "/icons/ereference.gif");
        addImageFilePath(IMAGE_ENTITY, "/icons/Entity.gif");
        addImageFilePath(IMAGE_PORT, "/icons/Port.gif");
        addImageFilePath(IMAGE_RELATION, "/icons/Relation.gif");
        addImageFilePath(IMAGE_LINK, "/icons/Link.gif");
    }

}
