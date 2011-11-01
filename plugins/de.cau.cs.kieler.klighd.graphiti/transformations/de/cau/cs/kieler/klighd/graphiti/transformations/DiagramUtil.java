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

    
    /**
     * Utility method calculating the width of a given label text rendered with a given font.
     * 
     * @param text the label text
     * @param font chosen font
     * @return the estimated width of the label
     */
    public static int calculatetextWidth(final String text, final Font font) {
       return GraphitiUi.getUiLayoutService().calculateTextSize(text, font).getWidth();
    }
    
    
    /**
     * Utility method calculating the height of a given label text rendered with a given font.
     * 
     * @param text the label text
     * @param font chosen font
     * @return the estimated height of the label
     */
    public static int calculatetextHeight(final String text, final Font font) {
        return GraphitiUi.getUiLayoutService().calculateTextSize(text, font).getHeight();
     }
    
    
    ////////////////////////
    //
    //  auxiliary stuff   //
    //                    //
    ////////////////////////
    
    /**
     * Generic debugging function for Xtend based transformations.
     * 
     * @param obj an {@link java.lang.Object} to be shown.
     */
    public static void debug(final java.lang.Object obj) {
        System.out.println(obj);
    }
    
    
    /**
     * Generic debugging function for Xtend based transformations.
     * 
     * @param list a list of Objects to be shown.
     */
    public static void debug(final java.util.List<?> list) {
        for (Object o : list) {
            debug(o);
        }
    }
    
    
}
