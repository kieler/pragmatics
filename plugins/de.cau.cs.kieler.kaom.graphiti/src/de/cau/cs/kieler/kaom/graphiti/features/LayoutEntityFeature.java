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

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.graphiti.util.GraphitiUtil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * Feature used to layout an entity and adjust its components after resizing has occurred.
 * 
 * @author atr
 */
public class LayoutEntityFeature extends AbstractLayoutFeature {

    /** minimal width for entities. */
    public static final int MIN_WIDTH = 30;
    /** minimal width for the port container shapes. */
    public static final int MIN_CONTAINER_WIDTH = MIN_WIDTH + 2 * AddPortFeature.PORT_SIZE;
    /** minimal height for entities. */
    public static final int MIN_HEIGHT = 30;
    /** minimal height for the port container shapes. */
    public static final int MIN_CONTAINER_HEIGHT = MIN_HEIGHT + 2 * AddPortFeature.PORT_SIZE;

    /** the distance of the separator line from the top margin. */
    private static final int SEPARATOR_DIST = 20;
    
    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public LayoutEntityFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * {@inheritDoc}
     */
    public boolean canLayout(final ILayoutContext context) {
        PictogramElement pe = context.getPictogramElement();
        if (pe instanceof ContainerShape) {
            EList<EObject> ob = pe.getLink().getBusinessObjects();
            return ob.size() == 1 && (ob.get(0) instanceof Entity);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean layout(final ILayoutContext context) {
        boolean changed = false;
        ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
        GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
        
        if (containerGa.getHeight() < MIN_CONTAINER_HEIGHT) {
            containerGa.setHeight(MIN_CONTAINER_HEIGHT);
            changed = true;
        }
        if (containerGa.getWidth() < MIN_CONTAINER_WIDTH) {
            containerGa.setWidth(MIN_CONTAINER_WIDTH);
            changed = true;
        }

        // container width initially of the invisible rectangle
        // now adjusted to the width of the normal inner rectangle
        int entityWidth = containerGa.getWidth() - 2 * AddPortFeature.PORT_SIZE;
        int entityHeight = containerGa.getHeight() - 2 * AddPortFeature.PORT_SIZE;
        for (GraphicsAlgorithm child : containerGa.getGraphicsAlgorithmChildren()) {
            changed |= GraphitiUtil.setBounds(child, AddPortFeature.PORT_SIZE, AddPortFeature.PORT_SIZE,
                    entityWidth, entityHeight);
        }

        // position of each child shape of the entity adjusted
        for (Shape shape : containerShape.getChildren()) {
            GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
            if (ga instanceof Polyline) {
                int[] points = new int[] {
                        AddPortFeature.PORT_SIZE, AddPortFeature.PORT_SIZE + SEPARATOR_DIST,
                        AddPortFeature.PORT_SIZE + entityWidth,
                        AddPortFeature.PORT_SIZE + SEPARATOR_DIST
                };
                changed |= GraphitiUtil.setPoints((Polyline) ga, points);
            } else if (ga instanceof Text) {
                changed |= GraphitiUtil.setBounds(ga, AddPortFeature.PORT_SIZE,
                        AddPortFeature.PORT_SIZE, entityWidth, SEPARATOR_DIST); 
            }
        }
        
        return changed;
    }

}
