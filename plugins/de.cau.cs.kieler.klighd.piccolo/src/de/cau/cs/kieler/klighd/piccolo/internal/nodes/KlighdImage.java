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

import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.InputStream;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;

import de.cau.cs.kieler.klighd.piccolo.KlighdSWTGraphics;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * A special {@link PNode} for integrating images in KLighD diagrams. The implementation is inspired
 * by that of {@link edu.umd.cs.piccolox.swt.PSWTImage}, some major differences wrt to design
 * requirements led to this new implementation. These differences involve the non-dependency to any
 * specific canvas implementation as well as to {@link org.eclipse.swt.widgets.Display Display}
 * being a specific {@link Device}.
 * 
 * @author chsch
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public class KlighdImage extends PNode {

    private static final long serialVersionUID = 7201328608113593385L;
    private transient Image image;
    private transient ImageData imageData;

    /**
     * Constructor.
     * @param image
     *            image to be displayed by this {@link KlighdImage}
     */
    public KlighdImage(final Image image) {
        setImage(image);
        addDisposeListener();
    }

    /**
     * Constructor.
     * @param image
     *            image to be displayed by this {@link KlighdImage}
     */
    public KlighdImage(final ImageData image) {
        setImage(image);
        addDisposeListener();
    }

    /**
     * Constructor.
     * @param input
     *            stream providing the image, will be read and converted to an Image internally
     */
    public KlighdImage(final InputStream input) {
        setImage(input);
        addDisposeListener();
    }
    
    /**
     * Adds a lister to this node being in charge of disposing {@link #image} in case this node
     * (or one of its parents) is removed from its parent, which basically means that this node
     * has been removed.
     */
    private void addDisposeListener() {
        this.addPropertyChangeListener(PNode.PROPERTY_PARENT, new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent event) {
                if (event.getNewValue() == null && image != null) {
                    image.dispose();
                }
            }
        });
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

        if (this.imageData != null) {
            setBounds(0, 0, this.imageData.width, this.imageData.height);
            invalidatePaint();
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
        this.imageData = null;

        if (old != null) {
            old.dispose();
        }

        if (this.image != null) {
            final Rectangle bounds = getImage().getBounds();
            setBounds(0, 0, bounds.width, bounds.height);
            invalidatePaint();
        }
        
        firePropertyChange(PImage.PROPERTY_CODE_IMAGE, PImage.PROPERTY_IMAGE, old, image);
    }

    /**
     * {@inheritDoc}
     */
    protected void paint(final PPaintContext paintContext) {
        if (this.imageData != null) {
            final Device device = ((KlighdSWTGraphics) paintContext.getGraphics()).getDevice();
            this.image = new Image(device, this.imageData);
            this.imageData = null;
        }

        if (image != null) {
            final Rectangle r = image.getBounds();
            final PBounds b = getBoundsReference();
            final KlighdSWTGraphics graphics = (KlighdSWTGraphics) paintContext.getGraphics();

            if (b.x == 0 && b.y == 0 && b.width == r.width && b.height == r.height) {
                graphics.drawImage(image, 0, 0);
            } else {
                final AffineTransform original = graphics.getTransform();
                final AffineTransform copy = graphics.getTransform();
                copy.translate(b.x, b.y);
                copy.scale(b.width / r.width, b.height / r.height);
                graphics.setTransform(copy);
                graphics.drawImage(image, 0, 0);
                graphics.setTransform(original);
            }
        }
    }
}