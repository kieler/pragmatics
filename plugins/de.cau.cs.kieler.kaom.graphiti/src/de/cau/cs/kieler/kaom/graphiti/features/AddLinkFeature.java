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
package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.graphiti.diagram.StyleProvider;

/**
 * Adds a link between the source and the target elements.
 * 
 * @author atr 
 */
public class AddLinkFeature extends AbstractAddFeature {

    /** the style provider. */ 
    private StyleProvider styleProvider;
    
    /**
     * The Constructor.
     * 
     * @param fp the feature provider
     * @param thestyleProvider the style provider
     */
    public AddLinkFeature(final IFeatureProvider fp, final StyleProvider thestyleProvider) {
        super(fp);
        this.styleProvider = thestyleProvider;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canAdd(final IAddContext context) {
        return (context instanceof IAddConnectionContext && context.getNewObject() instanceof Link);
    }

    /** relative location of link labels on the edge. */
    private static final double TEXT_LOCATION = 0.5;
    /** absolute offset of link labels from the edge. */
    private static final int TEXT_OFFSET = 10;
    /** length of link arrows. */
    private static final int ARROW_LENGTH = 10;
    /** width of link arrows. */
    private static final int ARROW_WIDTH = 10;
    
    /**
     * {@inheritDoc}
     */
    public PictogramElement add(final IAddContext context) {
        IAddConnectionContext addConContext = (IAddConnectionContext) context;
        IPeCreateService peCreateService = Graphiti.getPeCreateService();

        Connection conn = peCreateService.createFreeFormConnection(getDiagram());
        conn.setStart(addConContext.getSourceAnchor());
        conn.setEnd(addConContext.getTargetAnchor());
        IGaService gaService = Graphiti.getGaService();
        Polyline polyline = gaService.createPolyline(conn);
        polyline.setStyle(styleProvider.getStyle(StyleProvider.EDGE_STYLE));
        link(conn, context.getNewObject());

        ConnectionDecorator textDecorator = peCreateService.createConnectionDecorator(
                conn, true, TEXT_LOCATION, true);
        Text text = gaService.createDefaultText(textDecorator);
        text.setStyle(styleProvider.getStyle());
        gaService.setLocation(text, TEXT_OFFSET, 0);

        // add static graphical decorators (composition and navigable)
        ConnectionDecorator arrowDecorator = peCreateService.createConnectionDecorator(
                conn, false, 1.0, true);
        Polyline arrow = gaService.createPolygon(arrowDecorator,
                new int[] { -ARROW_LENGTH, ARROW_WIDTH / 2, 0, 0, -ARROW_LENGTH, -ARROW_WIDTH / 2 });
        arrow.setStyle(styleProvider.getStyle(StyleProvider.SOLID_STYLE));

        // provide information to support direct-editing directly
        // after object creation (must be activated additionally)
        IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
        // set container shape for direct editing after object creation
        directEditingInfo.setMainPictogramElement(conn);
        // set shape and graphics algorithm where the editor for
        // direct editing shall be opened after object creation
        directEditingInfo.setPictogramElement(textDecorator);
        directEditingInfo.setGraphicsAlgorithm(text);
        return conn;
    }

}
