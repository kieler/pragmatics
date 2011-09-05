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
package de.cau.cs.kieler.klighd.piccolo.activities;

import java.awt.Color;
import java.awt.Paint;

import de.cau.cs.kieler.klighd.piccolo.PSWTAdvancedPath;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.activities.PInterpolatingActivity;

/**
 * The Piccolo activity for highlighting a node by changing the line color and width.
 * 
 * @author mri
 */
public class HighlightActivity extends PInterpolatingActivity {

    /** the highlight node. */
    private PNode node;
    /** the node casted to a {@code PSWTAdvancedPath} if possible. */
    private PSWTAdvancedPath path = null;
    /** the source color for the highlight. */
    private Color sourceColor = null;
    /** the target color for the highlight. */
    private Color targetColor;
    /** the source line width. */
    private double sourceLineWidth;
    /** the target line width. */
    private double targetLineWidth;
    /** the target line width factor. */
    private double targetLineWidthFactor;

    /**
     * Constructs a highlight activity.
     * 
     * @param node
     *            the highlight node
     * @param color
     *            the color for the highlight
     * @param lineWidthFactor
     *            the line width factor for the highlight if the node type supports it (i.e.
     *            PSWTAdvancedPath)
     * @param duration
     *            the duration over which the highlight is performed
     */
    public HighlightActivity(final PNode node, final Color color, final double lineWidthFactor,
            final long duration) {
        super(duration);
        setMode(PInterpolatingActivity.SOURCE_TO_DESTINATION_TO_SOURCE);
        this.node = node;
        targetColor = color;
        targetLineWidthFactor = lineWidthFactor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void activityStarted() {
        if (getFirstLoop()) {
            // get the source color if available
            Paint paint = node.getPaint();
            if (paint instanceof Color) {
                sourceColor = (Color) paint;
            }
            // get the line width if possible
            if (node instanceof PSWTAdvancedPath) {
                path = (PSWTAdvancedPath) node;
                sourceLineWidth = path.getLineWidth();
                targetLineWidth = targetLineWidthFactor * sourceLineWidth;
            }
        }
        super.activityStarted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {
        // set color if possible
        if (sourceColor != null) {
            float red =
                    sourceColor.getRed() + zeroToOne
                            * (targetColor.getRed() - sourceColor.getRed());
            float green =
                    sourceColor.getGreen() + zeroToOne
                            * (targetColor.getGreen() - sourceColor.getGreen());
            float blue =
                    sourceColor.getBlue() + zeroToOne
                            * (targetColor.getBlue() - sourceColor.getBlue());
            float alpha =
                    sourceColor.getAlpha() + zeroToOne
                            * (targetColor.getAlpha() - sourceColor.getAlpha());
            Color color = new Color((int) red, (int) green, (int) blue, (int) alpha);
            if (path != null) {
                path.setStrokeColor(color);
            } else {
                node.setPaint(color);
            }
        }
        // set line width if possible
        if (path != null) {
            path.setLineWidth(sourceLineWidth + zeroToOne * (targetLineWidth - sourceLineWidth));
        }
    }

}
