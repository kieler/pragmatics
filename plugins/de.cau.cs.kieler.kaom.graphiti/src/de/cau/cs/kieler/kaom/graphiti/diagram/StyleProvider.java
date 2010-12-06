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

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.ColorConstant;

/**
 * A provider for styles of diagram elements.
 *
 * @author msp
 */
public class StyleProvider {

    /** the diagram type provider for which this style provider is operating. */
    private IDiagramTypeProvider diagramTypeProvider;
    
    /**
     * Creates a style provider for a given diagram type provider.
     * 
     * @param provider the diagram type provider
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
     * @param id the style identifier
     * @return a style instance, or {@code null} if the id is unknown
     */
    public Style getStyle(final String id) {
        Diagram diagram = diagramTypeProvider.getDiagram();
        Style style = null;
        for (Style diagramStyle : diagram.getStyles()) {
            if (id.equals(diagramStyle.getId())) {
                 style = diagramStyle;
                 break;
            }
        }
        if (style == null) {
            style = createStyle(diagram, id);
        }
        return style;
    }
    
    /** the default style id for KAOM diagrams. */
    public static final String DEFAULT_STYLE = "default";
    /** style id for items with solid color fill. */
    public static final String SOLID_STYLE = "solid";
    /** style id for edges (links). */
    public static final String EDGE_STYLE = "link";
    
    /**
     * Create the style with given identifier.
     * 
     * @param diagram the diagram
     * @param id the style identifier
     * @return a new style instance, or {@code null} if the id is unknown
     */
    private Style createStyle(final Diagram diagram, final String id) {
        IGaService gaService = Graphiti.getGaService();
        if (DEFAULT_STYLE.equals(id)) {
            Style style = gaService.createStyle(diagram, id);
            style.setForeground(gaService.manageColor(diagram, ColorConstant.BLACK));
            style.setBackground(gaService.manageColor(diagram, ColorConstant.WHITE));
            return style;
        } else if (SOLID_STYLE.equals(id)) {
            Style defaultStyle = getStyle(DEFAULT_STYLE);
            Style style = gaService.createStyle(defaultStyle, id);
            style.setFilled(true);
        } else if (EDGE_STYLE.equals(id)) {
            Style style = gaService.createStyle(diagram, id);
            style.setForeground(gaService.manageColor(diagram, ColorConstant.BLACK));
            style.setLineStyle(LineStyle.SOLID);
        }
        return null;
    }
    
}
