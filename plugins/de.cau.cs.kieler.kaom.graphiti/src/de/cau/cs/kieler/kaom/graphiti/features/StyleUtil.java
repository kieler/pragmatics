package de.cau.cs.kieler.kaom.graphiti.features;

import java.util.Collection;

import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Style;
import org.eclipse.graphiti.mm.pictograms.StyleContainer;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

public class StyleUtil {

    private static final IColorConstant E_CLASS_TEXT_FOREGROUND = new ColorConstant(100, 100, 100);

    private static final IColorConstant E_CLASS_FOREGROUND = new ColorConstant(40, 55, 180);

    private static final IColorConstant E_CLASS_BACKGROUND = new ColorConstant(255, 255, 150);

   // private static final IColorConstant E_CLASS_BACKGROUND = new ColorConstant(226, 233, 255);
 
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

 

    public static Style getStyleForEClassText(Diagram diagram) {

        final String styleId = "E-CLASS-TEXT";
        // this is a child style of the e-class-style
        Style parentStyle = getStyleForEClass(diagram);
        Style style = findStyle(parentStyle, styleId);

        if (style == null) { // style not found - create new style
            IGaService gaService = Graphiti.getGaService();
            style = gaService.createStyle(diagram, styleId);
            // "overwrites" values from parent style
            style.setForeground(gaService.manageColor(diagram,E_CLASS_TEXT_FOREGROUND));

        }

        return style;

    }

     // find the style with a given id in the style-container, can return null

    private static Style findStyle(StyleContainer styleContainer, String id) {

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

 