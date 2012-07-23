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

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.graphiti.diagram.StyleProvider;

/**
 * Adds a link between the source and the target elements.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class AddLinkFeature extends AbstractAddFeature {

    /** the style provider. */
    private IStyleProvider styleProvider;

    /**
     * The Constructor.
     * 
     * @param fp
     *            the feature provider
     * @param sp
     *            the style provider
     */
    public AddLinkFeature(final IFeatureProvider fp, final IStyleProvider sp) {
        super(fp);
        this.styleProvider = sp;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canAdd(final IAddContext context) {
        return (context instanceof IAddConnectionContext && context
                .getNewObject() instanceof Link);
    }

    /** relative location of link labels on the edge. */
    private static final double TEXT_LOCATION = 0.5;
    /** absolute offset of link labels from the edge. */
    private static final int TEXT_OFFSET = 10;
    /** length of link arrows. */
    private static final int ARROW_LENGTH = 8;
    /** width of link arrows. */
    private static final int ARROW_WIDTH = 8;

    /**
     * {@inheritDoc}
     */
    public PictogramElement add(final IAddContext context) {
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        Connection connection =
                peCreateService.createFreeFormConnection(getDiagram());
        IAddConnectionContext addConContext = (IAddConnectionContext) context;
        connection.setStart(addConContext.getSourceAnchor());
        connection.setEnd(addConContext.getTargetAnchor());
        IGaService gaService = Graphiti.getGaService();
        Polyline polyline = gaService.createPolyline(connection);
        polyline.setStyle(styleProvider.getStyle(StyleProvider.LINK_STYLE));
        polyline.setLineWidth(2);
        link(connection, context.getNewObject());

        ConnectionDecorator textDecorator =
                peCreateService.createConnectionDecorator(connection, true,
                        TEXT_LOCATION, true);
        Text text = gaService.createDefaultText(getDiagram(), textDecorator);
        text.setStyle(styleProvider.getStyle());
        gaService.setLocation(text, TEXT_OFFSET, 0);

        // add static graphical decorators (composition and navigable)
        ConnectionDecorator arrowDecorator =
                peCreateService.createConnectionDecorator(connection, false,
                        1.0, true);
        Polyline arrow =
                gaService.createPolygon(arrowDecorator, new int[] {
                        -ARROW_LENGTH, ARROW_WIDTH / 2, 0, 0, -ARROW_LENGTH,
                        -ARROW_WIDTH / 2 });
        arrow.setStyle(styleProvider.getStyle(StyleProvider.LINK_STYLE));

        // provide information to support direct-editing directly
        // after object creation (must be activated additionally)
        IDirectEditingInfo directEditingInfo =
                getFeatureProvider().getDirectEditingInfo();
        directEditingInfo.setMainPictogramElement(connection);
        // set shape and graphics algorithm where the editor for
        // direct editing shall be opened after object creation
        directEditingInfo.setPictogramElement(textDecorator);
        directEditingInfo.setGraphicsAlgorithm(text);
        return connection;
    }

}
