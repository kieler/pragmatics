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

import de.cau.cs.kieler.kaom.graphiti.KaomGraphitiPlugin;

/**
 * Provides all the images required in the graphical editor.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class ImageProvider extends AbstractImageProvider {
   
    /** References to image Entity. */
    public static final String IMAGE_ENTITY = KaomGraphitiPlugin.PLUGIN_ID + ".Entity";
   
    /** References to image Port. */
    public static final String IMAGE_PORT = KaomGraphitiPlugin.PLUGIN_ID + ".Port";
   
    /** References to image Link. */
    public static final String IMAGE_LINK = KaomGraphitiPlugin.PLUGIN_ID + ".Link";
    
    /** References to image Relation. */
    public static final String IMAGE_RELATION = KaomGraphitiPlugin.PLUGIN_ID + ".Relation";

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void addAvailableImages() {
        addImageFilePath(IMAGE_ENTITY, "/icons/Entity.gif");
        addImageFilePath(IMAGE_PORT, "/icons/Port.gif");
        addImageFilePath(IMAGE_RELATION, "/icons/Relation.gif");
        addImageFilePath(IMAGE_LINK, "/icons/Link.gif");
    }

}
