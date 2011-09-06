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
 * The Piccolo activity for highlighting a node by changing the color.
 * 
 * @author mri
 */
public class HighlightActivity extends PInterpolatingActivity {

    /** the highlight node. */
    private PNode node;
    /** the node casted to a {@code PSWTAdvancedPath} if possible. */
    private PSWTAdvancedPath path = null;
    /** the source color for the stroke highlight. */
    private Color sourceStrokeColor = null;
    /** the target color for the stroke highlight. */
    private Color targetStrokeColor;
    /** the source color for the highlight. */
    private Color sourceColor = null;
    /** the target color for the highlight. */
    private Color targetColor;

    /**
     * Constructs a highlight activity.
     * 
     * @param node
     *            the highlight node
     * @param strokeColor
     *            the stroke color for the highlight
     * @param color
     *            the color for the highlight
     * @param duration
     *            the duration over which the highlight is performed
     */
    public HighlightActivity(final PNode node, final Color strokeColor, final Color color,
            final long duration) {
        super(duration);
        setMode(PInterpolatingActivity.SOURCE_TO_DESTINATION_TO_SOURCE);
        this.node = node;
        targetStrokeColor = strokeColor;
        targetColor = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void activityStarted() {
        if (getFirstLoop()) {
            // get the source stroke color if possible
            if (node instanceof PSWTAdvancedPath) {
                path = (PSWTAdvancedPath) node;
                // get the source stroke color
                Paint paint = path.getStrokePaint();
                if (paint instanceof Color) {
                    sourceStrokeColor = (Color) paint;
                }
            }
            // get the source color
            Paint paint = node.getPaint();
            if (paint instanceof Color) {
                sourceColor = (Color) paint;
            }
        }
        super.activityStarted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelativeTargetValue(final float zeroToOne) {
        // set stroke color if possible
        if (sourceStrokeColor != null) {
            Color color = getColor(zeroToOne, sourceStrokeColor, targetStrokeColor);
            path.setStrokeColor(color);
        }
        // set color if possible
        if (sourceColor != null) {
            Color color = getColor(zeroToOne, sourceColor, targetColor);
            node.setPaint(color);
        }
    }

    private Color getColor(final float zeroToOne, final Color source, final Color target) {
        float red = source.getRed() + zeroToOne * (target.getRed() - source.getRed());
        float green = source.getGreen() + zeroToOne * (target.getGreen() - source.getGreen());
        float blue = source.getBlue() + zeroToOne * (target.getBlue() - source.getBlue());
        float alpha = source.getAlpha() + zeroToOne * (target.getAlpha() - source.getAlpha());
        Color color = new Color((int) red, (int) green, (int) blue, (int) alpha);
        return color;
    }

}
