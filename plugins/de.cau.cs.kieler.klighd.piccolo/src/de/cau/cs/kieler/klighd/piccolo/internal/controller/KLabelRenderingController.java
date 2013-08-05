/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.krendering.KForeground;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KLabelNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.internal.util.PiccoloPlacementUtil;
import edu.umd.cs.piccolo.PNode;

/**
 * @author mri, chsch
 */
public class KLabelRenderingController extends AbstractKGERenderingController<KLabel, KLabelNode> {

    /**
     * Constructs a rendering controller for a label.
     * 
     * @param label
     *            the Piccolo node representing a label
     */
    public KLabelRenderingController(final KLabelNode label) {
        super(label.getGraphElement(), label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PNode internalUpdateRendering() {
        PNode repNode = getRepresentation();

        // evaluate the rendering data
        KRendering currentRendering = getCurrentRendering();
        PNode renderingNode;
        if (currentRendering != null) {
            renderingNode = handleLabelRendering(currentRendering, (KLabelNode) repNode);
        } else {
            renderingNode =
                    handleLabelRendering(createDefaultLabelRendering(), (KLabelNode) repNode);
        }

        return renderingNode;
    }

    /**
     * Creates the Piccolo node for a rendering of a {@code KLabel} inside a parent Piccolo node.<br>
     * <br>
     * The rendering has to be a {@code KText} or the method fails.
     * 
     * @param rendering
     *            the rendering
     * @param parent
     *            the parent Piccolo label node
     * @return the Piccolo node representing the rendering
     */
    private PNode handleLabelRendering(final KRendering rendering, final KLabelNode parent) {
        // the rendering of a label has to be a text
        if (!(rendering instanceof KText)) {
            throw new RuntimeException("Non-text rendering attached to graph label: "
                    + getGraphElement());
        }

        // create the rendering
        @SuppressWarnings("unchecked")
        final PNodeController<KlighdStyledText> controller =
                (PNodeController<KlighdStyledText>) createRendering(rendering,
                        new ArrayList<KStyle>(0), parent, Bounds.of(parent.getBoundsReference()));
        controller.getNode().setText(parent.getText());

        // add a listener on the parent's bend points
        addListener(KLabelNode.PROPERTY_TEXT, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        controller.getNode().setText(parent.getText());
                        controller.getNode().repaint();
                    }
                });

        // add a listener on the parent's bounds
        addListener(PNode.PROPERTY_BOUNDS, parent, controller.getNode(),
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent e) {
                        // calculate the new bounds of the rendering
                        Bounds bounds =
                                PiccoloPlacementUtil.evaluateAreaPlacement(PlacementUtil
                                        .asAreaPlacementData(rendering.getPlacementData()),
                                        parent.getBoundsReference());
                        // use the controller to apply the new bounds
                        controller.setBounds(bounds);
                    }
                });

        return controller.getNode();
    }

    /**
     * Creates a default rendering for labels without attached rendering data.
     * 
     * @return the rendering
     */
    private static KRendering createDefaultLabelRendering() {
        // create the default rendering model
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KText text = factory.createKText();

        KForeground foreground = factory.createKForeground();
        foreground.setColor(factory.createKColor());
        
        text.getStyles().add(foreground);
        return text;
    }

}
