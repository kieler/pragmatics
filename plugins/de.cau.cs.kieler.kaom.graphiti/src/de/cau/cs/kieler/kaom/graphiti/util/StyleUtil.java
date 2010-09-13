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

import java.util.Collection;

import org.eclipse.graphiti.mm.StyleContainer;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * Utility class used to set a common style for all elements.
 * 
 * @author atr
 */
public final class StyleUtil {
    
    /** Hidden constructor. */
    private StyleUtil() {
    }

    private static final IColorConstant E_CLASS_TEXT_FOREGROUND = new ColorConstant(100, 100, 100);

    private static final IColorConstant E_CLASS_FOREGROUND = new ColorConstant(40, 55, 180);

    private static final IColorConstant E_CLASS_BACKGROUND = new ColorConstant(255, 255, 150);

   // private static final IColorConstant E_CLASS_BACKGROUND = new ColorConstant(226, 233, 255);
 
   
    /**
     * @return Style
     * @param diagram
     * returns style for Entity or else returns null.
     */
    public static Style getStyleForEClass(final Diagram diagram) {

        final String styleId = "E-CLASS";

        Style style = findStyle(diagram, styleId);

         if (style == null) { // style not found - create new style

            IGaService gaService = Graphiti.getGaService();
            style = gaService.createStyle(diagram, styleId);
            style.setForeground(gaService.manageColor(diagram, E_CLASS_FOREGROUND));
            style.setBackground(gaService.manageColor(diagram, E_CLASS_BACKGROUND));
            style.setLineWidth(2);

        }

        return style;

    }

 
    /**
     * 
     * @param diagram .
     * @return
     * returns style of Entity or else null
     */
    public static Style getStyleForEClassText(final Diagram diagram) {

        final String styleId = "E-CLASS-TEXT";
        // this is a child style of the e-class-style
        Style parentStyle = getStyleForEClass(diagram);
        Style style = findStyle(parentStyle, styleId);

        if (style == null) { // style not found - create new style
            IGaService gaService = Graphiti.getGaService();
            style = gaService.createStyle(diagram, styleId);
            // "overwrites" values from parent style
            style.setForeground(gaService.manageColor(diagram, E_CLASS_TEXT_FOREGROUND));

        }

        return style;

    }

     // find the style with a given id in the style-container, can return null

    /**
     * @param id
     * @param styleContainer
     */
    private static Style findStyle(final StyleContainer styleContainer, final String id) {

        // find and return style
        Collection<Style> styles = styleContainer.getStyles();

        if (styles != null) {
            for (Style style : styles) {
                if (id.equals(style.getId())) {
                    return style;
                }
            }
        }

        return null;
    }

}
