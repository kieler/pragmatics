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
package de.cau.cs.kieler.kaom.graphiti.util;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

/**
 * 
 * @author atr
 * Class to set the shape of Entity
 */
public class PropertyUtil {
   
    /*
     * The 
     */
    public static final String SHAPE_KEY = "shape-id";
 
    public static final String SHAPE_VALUE_E_CLASS = "e-class";
 
    /**
     * 
     * @param pe
     *Sets the shape of the Entity
     */
    public static final void setEClassShape(final PictogramElement pe) {
    Graphiti.getPeService().setPropertyValue(pe, SHAPE_KEY,
            SHAPE_VALUE_E_CLASS);

    }

    /**
     * 
     * @param pe .
     * @return
     * Gets the shape of the Entity
     */
    public static boolean isEClassShape(final PictogramElement pe) {
        return SHAPE_VALUE_E_CLASS.equals(Graphiti.getPeService()
           .getPropertyValue(pe, SHAPE_KEY));

    }

}
