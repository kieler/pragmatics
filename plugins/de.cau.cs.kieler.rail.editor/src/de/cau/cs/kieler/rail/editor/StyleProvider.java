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
package de.cau.cs.kieler.rail.editor;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.StyleContainer;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.ColorConstant;

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;

/**
 * A provider for styles of diagram elements.
 * 
 * @author hdw
 */
public class StyleProvider implements IStyleProvider {

    /** the diagram type provider for which this style provider is operating. */
    private IDiagramTypeProvider diagramTypeProvider;

    /**
     * Creates a style provider for a given diagram type provider.
     * 
     * @param provider
     *            the diagram type provider
     */
    public StyleProvider(final IDiagramTypeProvider provider) {
        this.diagramTypeProvider = provider;
    }

    /**
     * Returns the default style.
     * 
     * @return the default style
     */
    public Style getStyle() {
        return getStyle(DEFAULT_STYLE);
    }

    /**
     * Fetch the style with given identifier.
     * 
     * @param id
     *            the style identifier
     * @return a style instance, or {@code null} if the id is unknown
     */
    public Style getStyle(final String id) {
        Diagram diagram = diagramTypeProvider.getDiagram();
        Style style = getStyle(diagram, id);
        if (style == null) {
            style = createStyle(diagram, id);
        }
        return style;
    }

    /**
     * Look recursively for an appropriate style.
     * 
     * @param container
     *            a style container
     * @param id
     *            the style identifier
     * @return a style instance, or {@code null} if the style could not be found
     */
    private Style getStyle(final StyleContainer container, final String id) {
        Style style = null;
        for (Style diagramStyle : container.getStyles()) {
            if (id.equals(diagramStyle.getId())) {
                style = diagramStyle;
                break;
            }
            style = getStyle(diagramStyle, id);
            if (style != null) {
                break;
            }
        }
        return style;
    }

    /** the default style id for KAOM diagrams. */
    public static final String DEFAULT_STYLE = "default";
    /** style id for items with solid color fill. */
    public static final String SOLID_STYLE = "solid";
    // TODO new one.
    /** style id for Breach (Einbruchsstelle) */
    public static final String BREACH = "breach";
    /** style id for Breach (Einbruchsstelle) */
    public static final String PORT = "port";
    public static final String PORT_END = "port end";

    /**
     * Create the style with given identifier.
     * 
     * @param diagram
     *            the diagram
     * @param id
     *            the style identifier
     * @return a new style instance, or {@code null} if the id is unknown
     */
    private Style createStyle(final Diagram diagram, final String id) {
        IGaService gaService = Graphiti.getGaService();
        if (DEFAULT_STYLE.equals(id)) {
            Style style = gaService.createStyle(diagram, id);
            style.setForeground(gaService.manageColor(diagram,
                    ColorConstant.BLACK));
            style.setBackground(gaService.manageColor(diagram,
                    ColorConstant.WHITE));
            return style;
        } else if (SOLID_STYLE.equals(id)) {
            Style defaultStyle = getStyle(DEFAULT_STYLE);
            Style style = gaService.createStyle(defaultStyle, id);
            style.setFilled(true);
            return style;
        } else if (BREACH.equals(id)) {
            Style style = gaService.createStyle(diagram, id);
            style.setForeground(gaService.manageColor(diagram,
                    ColorConstant.BLACK));
            style.setBackground(gaService.manageColor(diagram,
                    ColorConstant.GRAY));
            style.setFilled(true);
            return style;
        } else if (PORT.equals(id)) {
            Style style = gaService.createStyle(diagram, id);
            style.setForeground(gaService.manageColor(diagram,
                    ColorConstant.BLACK));
            style.setBackground(gaService.manageColor(diagram,
                    ColorConstant.LIGHT_GRAY));
            style.setFilled(false);
            style.setTransparency(1.0);
            return style;
        } else if (PORT_END.equals(id)) {
            Style style = gaService.createStyle(diagram, id);
            style.setForeground(gaService.manageColor(diagram,
                    ColorConstant.WHITE));
            style.setBackground(gaService.manageColor(diagram,
                    ColorConstant.WHITE));
            style.setFilled(false);
            style.setTransparency(1.0);
            return style;
        }
        return null;
    }

}
