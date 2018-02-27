/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.kivi.effects;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.graphics.Color;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.klighd.kgraph.KEdge;
import de.cau.cs.kieler.klighd.kgraph.KGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KLabel;
import de.cau.cs.kieler.klighd.kgraph.KLabeledGraphElement;
import de.cau.cs.kieler.klighd.kgraph.KPort;
import de.cau.cs.kieler.klighd.krendering.KBackground;
import de.cau.cs.kieler.klighd.krendering.KColor;
import de.cau.cs.kieler.klighd.krendering.KForeground;
import de.cau.cs.kieler.klighd.krendering.KLineStyle;
import de.cau.cs.kieler.klighd.krendering.KLineWidth;
import de.cau.cs.kieler.klighd.krendering.KRendering;
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory;
import de.cau.cs.kieler.klighd.krendering.KStyle;

/**
 * Effect highlight a given {@link KGraphElement} as well as optionally its {@link KLabel} and ports
 * in case of a {@link KEdge}.
 * 
 * @author ckru
 * @author chsch
 */
public class KlighdHighlightEffect extends AbstractEffect {

    /** KRenderingFactory to generate KRenderings. */
    private KRenderingFactory factory = KRenderingFactory.eINSTANCE;

    /** the element to be highlighted. */
    private KGraphElement targetNode;

    /** Remember newly set styles to remove them on undo. */
    private List<KStyle> styles = new LinkedList<KStyle>();

    /** the new foreground highlight color. */
    private Color foregroundColor = null;
    /** the new background highlight color. */
    private Color backgroundColor = null;
    /** the new line width. */
    private float lineWidth = 0;
    /** the new line style. */
    private KLineStyle lineStyle = null;

    /** optional background color for labels. */
    private Color labelBackgroundColor = null;
    /** optional background color for labels. */
    private Color labelForegroundColor = null;

    /** optional background color for labels. */
    private Color portForegroundColor = null;
    /** optional background color for labels. */
    private Color portBackgroundColor = null;

    /**
     * Constructor of an effect that highlights the given element.
     * 
     * @param target
     *            the element to be highlighted.
     * @param color
     *            the foreground color to highlight with. May be null for no foreground
     *            highlighting.
     * @param background
     *            the background color to highlight with. May be null for no background
     *            highlighting.
     */
    public KlighdHighlightEffect(final KGraphElement target, final Color color,
            final Color background) {
        this.targetNode = target;
        this.foregroundColor = color;
        this.backgroundColor = background;
    }

    /**
     * Constructor of an effect that highlights the given element.
     * 
     * @param target
     *            the element to be highlighted.
     * @param color
     *            the foreground color to highlight with. May be null for no foreground
     *            highlighting.
     * @param background
     *            the background color to highlight with. May be null for no background
     *            highlighting.
     * 
     * @param lineWidth
     *            set a line width for the highlighted element. Set to 0 to keep old line width.
     */
    public KlighdHighlightEffect(final KGraphElement target, final Color color,
            final Color background, final float lineWidth) {
        this(target, color, background);
        this.lineWidth = lineWidth;
    }

    /**
     * Constructor of an effect that highlights the given element.
     * 
     * @param target
     *            the element to be highlighted.
     * @param color
     *            the foreground color to highlight with. May be null for no foreground
     *            highlighting.
     * @param background
     *            the background color to highlight with. May be null for no background
     *            highlighting.
     * @param lineStyle
     *            define a line style for the highlighted element. May be null to keep old
     *            linestyle.
     */
    public KlighdHighlightEffect(final KGraphElement target, final Color color,
            final Color background, final KLineStyle lineStyle) {
        this(target, color, background);
        this.lineStyle = lineStyle;
    }

    /**
     * Constructor of an effect that highlights the given element.
     * 
     * @param target
     *            the element to be highlighted.
     * @param color
     *            the foreground color to highlight with. May be null for no foreground
     *            highlighting.
     * @param background
     *            the background color to highlight with. May be null for no background
     *            highlighting.
     * 
     * @param lineWidth
     *            set a line width for the highlighted element. Set to 0 to keep old line width.
     * @param lineStyle
     *            define a line style for the highlighted element. May be null to keep old line
     *            style.
     */
    public KlighdHighlightEffect(final KGraphElement target, final Color color,
            final Color background, final float lineWidth, final KLineStyle lineStyle) {
        this(target, color, background);
        this.lineWidth = lineWidth;
        this.lineStyle = lineStyle;
    }

    /**
     * Constructor of an effect that highlights the given element.
     * 
     * @param target
     *            the element to be highlighted.
     * @param color
     *            the foreground color to highlight with. May be null for no foreground
     *            highlighting.
     * @param background
     *            the background color to highlight with. May be null for no background
     *            highlighting.
     * 
     * @param lineWidth
     *            set a line width for the highlighted element. Set to 0 to keep old line width.
     * @param lineStyle
     *            define a line style for the highlighted element. May be null to keep old line
     *            style.
     * @param labelForegroundColor
     *            foreground color to highlight the target elements labels with. Set to null to not
     *            highlight.
     * @param labelBackgroundColor
     *            background color to highlight the target elements labels with. Set to null to not
     *            highlight.
     */
    public KlighdHighlightEffect(final KGraphElement target, final Color color,
            final Color background, final float lineWidth, final KLineStyle lineStyle,
            final Color labelForegroundColor, final Color labelBackgroundColor) {
        this(target, color, background, lineWidth, lineStyle);
        this.labelBackgroundColor = labelBackgroundColor;
        this.labelForegroundColor = labelForegroundColor;
    }

    /**
     * Constructor of an effect that highlights the given element.
     * 
     * @param target
     *            the element to be highlighted.
     * @param color
     *            the foreground color to highlight with. May be null for no foreground
     *            highlighting.
     * @param background
     *            the background color to highlight with. May be null for no background
     *            highlighting.
     * 
     * @param lineWidth
     *            set a line width for the highlighted element. Set to 0 to keep old line width.
     * @param lineStyle
     *            define a line style for the highlighted element. May be null to keep old line
     *            style.
     * @param labelForegroundColor
     *            foreground color to highlight the target elements labels with. Set to null to not
     *            highlight.
     * @param labelBackgroundColor
     *            background color to highlight the target elements labels with. Set to null to not
     *            highlight.
     * @param portForegroundColor
     *            foreground color to highlight the target edges ports with. Set to null to not
     *            highlight.
     * @param portBackgroundColor
     *            background color to highlight the target edges ports with. Set to null to not
     *            highlight.
     */
    // SUPPRESS CHECKSTYLE NEXT Parameter
    public KlighdHighlightEffect(final KEdge target, final Color color, final Color background,
            final float lineWidth, final KLineStyle lineStyle, final Color labelForegroundColor,
            final Color labelBackgroundColor, final Color portForegroundColor,
            final Color portBackgroundColor) {
        this(target, color, background, lineWidth, lineStyle, labelForegroundColor,
                labelBackgroundColor);
        this.portBackgroundColor = portBackgroundColor;
        this.portForegroundColor = portForegroundColor;
    }
    
    /**
     * Reveals the {@link KRendering KRenderings} to equipped with highlighting styles for
     * highlighting {@link KGraphElement KGraphElements}.<br>
     * <br>
     * This method is intended to be overridden by subclasses!
     * 
     * @return an {@link Iterable} containing the {@link KRendering} data elements of the
     *         {@link KGraphElement} to be highlighted
     */
    protected Iterable<KRendering> getKRenderingsToBeHightlighted() {
        // since KNodes may contain KRendering for both the collapsed figure and the expanded figure
        //  return all of the attached KRenderings
        return Iterables.filter(targetNode.getData(), KRendering.class);
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        for (KRendering rendering : getKRenderingsToBeHightlighted()) {
            // set foreground color of target element
            if (foregroundColor != null) {
                KStyle fg = this.getKForeground(foregroundColor);
                rendering.getStyles().add(fg);
                this.styles.add(fg);
            }
    
            // set line style of target element
            if (lineStyle != null) {
                rendering.getStyles().add(lineStyle);
                this.styles.add(lineStyle);
            }
    
            // set background color of target element
            if (backgroundColor != null) {
                KStyle bg = this.getKBackground(backgroundColor);
                rendering.getStyles().add(bg);
                this.styles.add(bg);
            }
    
            // set line width of target element
            if (lineWidth != 0) {
                KStyle lw = this.getLineWidth(lineWidth);
                rendering.getStyles().add(lw);
                this.styles.add(lw);
            }
    
            // highlight labels
            if (targetNode instanceof KLabeledGraphElement) {
                for (KLabel l : ((KLabeledGraphElement) targetNode).getLabels()) {
                    KRendering labelRendering = l.getData(KRendering.class);
                    // set foreground color of target elements label
                    if (labelForegroundColor != null) {
                        KStyle lfg = this.getKForeground(labelForegroundColor);
                        labelRendering.getStyles().add(lfg);
                        this.styles.add(lfg);
                    }
    
                    // set background color of target elements label
                    if (labelBackgroundColor != null) {
                        KStyle lbg = this.getKBackground(labelBackgroundColor);
                        labelRendering.getStyles().add(lbg);
                        this.styles.add(lbg);
                    }
                }
            }
    
            // highlight ports
            if (targetNode instanceof KEdge) {
                KPort sourcePort = ((KEdge) targetNode).getSourcePort();
                KPort targetPort = ((KEdge) targetNode).getTargetPort();
                // set foreground color of target elements ports
                if (portForegroundColor != null) {
                    // highlight source port
                    if (sourcePort != null) {
                        KRendering portRendering = sourcePort.getData(KRendering.class);
                        KStyle pfg = this.getKForeground(portForegroundColor);
                        portRendering.getStyles().add(pfg);
                        this.styles.add(pfg);
                    }
                    // highlight target port
                    if (targetPort != null) {
                        KRendering portRendering = targetPort.getData(KRendering.class);
                        KStyle pfg = this.getKForeground(portForegroundColor);
                        portRendering.getStyles().add(pfg);
                        this.styles.add(pfg);
                    }
                }
    
                // set background color of target elements ports
                if (portBackgroundColor != null) {
                    // highlight source port
                    if (sourcePort != null) {
                        KRendering portRendering = sourcePort.getData(KRendering.class);
                        KStyle pfg = this.getKBackground(portBackgroundColor);
                        portRendering.getStyles().add(pfg);
                        this.styles.add(pfg);
                    }
                    // highlight target port
                    if (targetPort != null) {
                        KRendering portRendering = targetPort.getData(KRendering.class);
                        KStyle pfg = this.getKBackground(portBackgroundColor);
                        portRendering.getStyles().add(pfg);
                        this.styles.add(pfg);
                    }
                }
            }
        }

    }

    @Override
    public void undo() {
        for (KStyle s : this.styles) {
            KRendering rendering = (KRendering) s.eContainer();
            rendering.getStyles().remove(s);
        }
        this.styles.clear();
    }

    /**
     * Create a KLineWidth with given width.
     * @param width the line width
     * @return a new KLineWidth with the given width
     */
    private KLineWidth getLineWidth(final float width) {
        KLineWidth lw = factory.createKLineWidth();
        lw.setLineWidth(width);
        return lw;
    }

    /**
     * Create a KForeground from the given Color.
     * @param color the desired foreground color
     * @return KForeground with the given color
     */
    private KForeground getKForeground(final Color color) {
        KForeground fg = factory.createKForeground();
        fg.setColor(this.kcolorFromSwtcolor(color));
        return fg;
    }

    /**
     * Create a KBackground from the given Color.
     * @param color the desired background color
     * @return KBackground with the given color
     */
    private KBackground getKBackground(final Color color) {
        KBackground bg = factory.createKBackground();
        bg.setColor(this.kcolorFromSwtcolor(color));
        return bg;
    }

    /**
     * Create a KColor from the given swt Color.
     * @param color the desired color
     * @return KColor with the same rgb values as given swt color
     */
    private KColor kcolorFromSwtcolor(final Color color) {
        KColor c = factory.createKColor();
        c.setBlue(color.getBlue());
        c.setGreen(color.getGreen());
        c.setRed(color.getRed());
        return c;
    }

}