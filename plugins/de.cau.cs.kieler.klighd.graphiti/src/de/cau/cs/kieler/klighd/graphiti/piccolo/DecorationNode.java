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
package de.cau.cs.kieler.klighd.graphiti.piccolo;

import java.awt.geom.Point2D;

import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;

import de.cau.cs.kieler.klighd.piccolo.nodes.PEmptyNode;

/**
 * A Piccolo node representing a connection decorator.
 * 
 * @author mri
 */
public class DecorationNode extends PEmptyNode {

    private static final long serialVersionUID = 8658315866234781559L;

    /** the Pictogram decorator. */
    private ConnectionDecorator decorator;
    /** whether to rotate the decoration. */
    private boolean rotate;
    /** the last rotation applied to this node. */
    private float lastRotation = 0.0f;

    /**
     * Constructs a DecorationNode.
     * 
     * @param decorator
     *            the Pictogram decorator
     * @param rotate
     *            whether to rotate the decoration to the angle defined by the last polyline segment
     *            of the associated connection
     */
    public DecorationNode(final ConnectionDecorator decorator, final boolean rotate) {
        this.decorator = decorator;
        this.rotate = rotate;
    }

    /**
     * Updates the decoration according to the polyline specified by the given coordinates.
     * 
     * @param xps
     *            the x-coordinates
     * @param yps
     *            the y-coordinates
     */
    public void updateDecoration(final float[] xps, final float[] yps) {
        Point2D.Float point = new Point2D.Float();
        int i = getSegmentStartIndexAndPoint(point, xps, yps, (float) decorator.getLocation());
        setGlobalTranslation(point);
        if (rotate) {
            float angle = angle(xps[i], yps[i], xps[i + 1], yps[i + 1]);
            rotate(angle - lastRotation);
            lastRotation = angle;
        }
    }

    private int getSegmentStartIndexAndPoint(final Point2D.Float point, final float[] xps,
            final float[] yps, final float location) {
        // compute total polyline distance
        float totalDistance = 0;
        for (int i = 0; i < xps.length - 1; ++i) {
            totalDistance += distance(xps[i], yps[i], xps[i + 1], yps[i + 1]);
        }
        // find the segment and point for the location
        int k = -1;
        float searchDistance = location * totalDistance;
        float currentDistance = 0;
        for (int i = 0; i < xps.length - 1; ++i) {
            float d = distance(xps[i], yps[i], xps[i + 1], yps[i + 1]);
            if (d <= 0) {
                continue;
            }
            if (currentDistance + d >= searchDistance) {
                // memorize the start index of the segment
                k = i;
                // compute the actual point on the polyline
                float rD = searchDistance - currentDistance;
                point.x = xps[i] + rD * (xps[i + 1] - xps[i]) / d;
                point.y = yps[i] + rD * (yps[i + 1] - yps[i]) / d;
                break;
            } else {
                currentDistance += d;
            }
        }
        // take first or last segment by default
        if (k < 0) {
            // CHECKSTYLEOFF MagicNumber
            if (location < 0.5) {
                k = 0;
                point.x = xps[0];
                point.y = yps[0];
            } else {
                k = xps.length - 2;
                point.x = xps[k];
                point.y = yps[k];
            }
            // CHECKSTYLEON MagicNumber
        }
        return k;
    }

    private float distance(final float x0, final float y0, final float x1, final float y1) {
        float xD = x1 - x0;
        float yD = y1 - y0;
        return (float) Math.sqrt(xD * xD + yD * yD);
    }

    private float angle(final float x0, final float y0, final float x1, final float y1) {
        float xD = x1 - x0;
        float yD = y1 - y0;
        return (float) Math.atan2(yD, xD);
    }

}
