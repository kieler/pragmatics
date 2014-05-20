/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.nodes;

import java.awt.Shape;
import java.io.InputStream;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.NodeDisposeListener.IResourceEmployer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A special {@link PNode} for integrating images in KLighD diagrams. The implementation is inspired
 * by that of {@link edu.umd.cs.piccolox.swt.PSWTImage}, some major differences wrt. design
 * requirements led to this new implementation. These differences involve the non-dependency to any
 * specific canvas implementation as well as to {@link org.eclipse.swt.widgets.Display Display}
 * being a specific {@link org.eclipse.swt.graphics.Device Device}.<br>
 * <br>
 * In contrast to {@link edu.umd.cs.piccolox.swt.PSWTImage} the bounds of nodes of this type are not
 * set while setting the image object to be displayed. Doing so results in flickering, at least
 * while drawing diagrams without animation. Instead the bounds are set top down by KlighD, see
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.KGERenderingControllerHelper#createImage(
 * de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController,
 * de.cau.cs.kieler.core.krendering.KImage, java.util.List, PNode,
 * de.cau.cs.kieler.klighd.microlayout.Bounds) KGERenderingControllerHelper#createImage(...)}.<br>
 * <br>
 * If the amount of instances of {@link Image} created while drawing diagrams, e.g. such with lots
 * of copies of the same icon will lead to performance/memory issues, one might introduce a further
 * caching mechanism beyond that in
 * {@link de.cau.cs.kieler.klighd.piccolo.internal.controller.KGERenderingControllerHelper#IMAGE_BUFFER
 * KGERenderingControllerHelper#IMAGE_BUFFER}. This could be done like for colors and fonts.
 * However, keeping every image in a lookup for the whole JVM life cycle sounds problematic...
 * 
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class KlighdImage extends PNode implements IResourceEmployer {

    private static final long serialVersionUID = 7201328608113593385L;
    
    // These two fields are to be kept consistent,
    //  i.e. both shall denote the same image.
    // This is useful for efficient drawing on SWT and non-SWT-based canvases.
    private transient Image image;
    private transient ImageData imageData;
    
    /**
     * The shape defining the clip area to be applied to this image.
     */
    private Shape clip;
    
    /**
     * Common private constructor.
     */
    private KlighdImage() {
        this.addPropertyChangeListener(NodeDisposeListener.DISPOSE, new NodeDisposeListener(this));
    }

    /**
     * Constructor.
     * @param image
     *            image to be displayed by this {@link KlighdImage}
     */
    public KlighdImage(final Image image) {
        this();
        setImage(image);
    }

    /**
     * Constructor.
     * @param image
     *            image to be displayed by this {@link KlighdImage}
     */
    public KlighdImage(final ImageData image) {
        this();
        setImage(image);
    }

    /**
     * Constructor.
     * @param input
     *            stream providing the image, will be read and converted to an Image internally
     */
    public KlighdImage(final InputStream input) {
        this();
        setImage(input);
    }

    /**
     * {@inheritDoc}
     */
    public void disposeSWTResource() {
        if (image != null) {
            image.dispose();
            image = null;
        }
        // Do not release 'imageData' here as a new Image may has to be
        //  created based on them later on 
    }
    
    /**
     * Getter.
     * 
     * @return the image that is displayed by this node, may be null
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image to be displayed by this node by delegating to
     * {@link ImageData#ImageData(InputStream)} and {@link #setImage(ImageData)}.
     * 
     * @param input
     *            stream providing the image data
     */
    public void setImage(final InputStream input) {
        setImage(new ImageData(input));
    }

    /**
     * Set the clip shape to be applied to this image, removes the existing clip
     * if <code>clip</code> is <code>null</code>.
     * 
     * @param clip the clip to set, may be <code>null</code>
     */
    public void setClip(final Shape clip) {
        this.clip = clip;
    }

    /**
     * Sets the image to be displayed by this node.
     *  
     * @param newImageData the image to be displayed, may be null
     */
    public void setImage(final ImageData newImageData) {
        final Image old = this.image;
        this.image = null;
        this.imageData = newImageData;

        if (old != null) {
            old.dispose();
        }

        firePropertyChange(PImage.PROPERTY_CODE_IMAGE, PImage.PROPERTY_IMAGE, old, imageData);
    }

    /**
     * Sets the image to be displayed by this node.
     *  
     * @param newImage the image to be displayed, may be null
     */
    public void setImage(final Image newImage) {
        final Image old = this.image;
        this.image = newImage;
        this.imageData = newImage.getImageData();

        if (old != null) {
            old.dispose();
        }

        firePropertyChange(PImage.PROPERTY_CODE_IMAGE, PImage.PROPERTY_IMAGE, old, image);
    }

    /**
     * {@inheritDoc}
     */
    protected void paint(final PPaintContext paintContext) {
        final KlighdSWTGraphics graphics = (KlighdSWTGraphics) paintContext.getGraphics();
        final PBounds b = getBoundsReference();

        if (graphics.getDevice() != null) {
            // within an SWT environment
            if (this.image == null && this.imageData != null) {
                this.image = new Image(graphics.getDevice(), this.imageData);
            }
            if (image != null) {
                final boolean setClip = clip != null;
                final Shape prevClip = graphics.getClip();
                
                if (setClip) {
                    graphics.clip(clip);
                }

                graphics.drawImage(image, b.width, b.height);

                if (setClip) {
                    graphics.setClip(prevClip);
                }
            }
        } else {
            // without any device we have to draw the raw image data
            if (image != null && imageData == null) {
                this.imageData = image.getImageData();
            }
            if (imageData != null) {
                final boolean setClip = clip != null;
                final Shape prevClip = graphics.getClip();
                
                if (setClip) {
                    graphics.clip(clip);
                }

                graphics.drawImage(imageData, b.width, b.height);

                if (setClip) {
                    graphics.setClip(prevClip);
                }
            }
        }
    }
}
