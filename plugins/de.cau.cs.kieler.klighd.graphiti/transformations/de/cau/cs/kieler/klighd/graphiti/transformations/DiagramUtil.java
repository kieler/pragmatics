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
package de.cau.cs.kieler.klighd.graphiti.transformations;

import org.eclipse.graphiti.mm.algorithms.styles.Font; 
import org.eclipse.graphiti.ui.services.GraphitiUi;

/**
 * Utility class for diagram synthesis using Xtend based transformations.
 * This class is not intended to be instantiated.
 * 
 * @author chsch
 */
public final class DiagramUtil {

    /**
     * This class is not intended to be instantiated.
     */
    private DiagramUtil() {
    }

    
    public static int calculatetextWidth(String text, Font font) {
       return GraphitiUi.getUiLayoutService().calculateTextSize(text, font).getWidth();	
    }
    
    public static int calculatetextHeight(String text, Font font) {
        return GraphitiUi.getUiLayoutService().calculateTextSize(text, font).getHeight();	
     }
    
    
    /**
     * Generic debugging function for Xtend based transformations.
     * 
     * @param list a list of Objects to be shown.
     */
    public static void debug(final java.util.List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
    
    
}
