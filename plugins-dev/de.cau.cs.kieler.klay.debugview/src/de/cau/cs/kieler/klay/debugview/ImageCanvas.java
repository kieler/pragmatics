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
package de.cau.cs.kieler.klay.debugview;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;

/**
 * A canvas with integrated image loading, panning and zooming capabilities.
 * 
 * @author cds
 */
public class ImageCanvas extends Canvas {

    // CONSTANTS
    
    /**
     * The smallest supported zoom level.
     */
    public static final float MIN_ZOOM = 0.05f;
    
    /**
     * The highest supported zoom level.
     */
    public static final float MAX_ZOOM = 2.0f;
    
    /**
     * Increment level for scroll bars.
     */
    private static final int SCROLL_BAR_INCREMENT = 10;
    
    
    // VARIABLES
    
    /**
     * The currently displayed image.
     */
    private Image currentImage = null;
    
    /**
     * The zoom factor.
     */
    private float zoomFactor = 1.0f;
    
    /**
     * Size of the image with zoom factor applied, as displayed by this control.
     */
    private Point zoomedImgSize = new Point(0, 0);
    
    /**
     * The point in the zoomed image that is displayed in the top left corner of this control. If
     * the width or height of the zoomed image fits entirely into the canvas, the corresponding
     * offset coordinate is 0.
     */
    private Point zoomedImgOffset = new Point(0, 0);
    
    
    /**
     * Creates a new instance within the given parent.
     * 
     * @param parent the parent composite.
     */
    public ImageCanvas(final Composite parent) {
        super(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.NO_BACKGROUND);
        
        // Setup scroll bars
        this.getHorizontalBar().setEnabled(false);
        this.getVerticalBar().setEnabled(false);
        
        // Add required event listeners
        this.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(final PaintEvent e) {
                paint(e.gc);
            }
        });
        
        this.addControlListener(new ControlAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void controlResized(final ControlEvent e) {
                syncScrollBars();
            }
        });
        
        this.getHorizontalBar().addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                zoomedImgOffset.x = getHorizontalBar().getSelection();
                redraw();
            }
        });
        
        this.getVerticalBar().addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                zoomedImgOffset.y = getVerticalBar().getSelection();
                redraw();
            }
        });
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Public Interface
    
    /**
     * Loads the given image file and displays it. If the file could not be loaded,
     * no image is displayed.
     * 
     * @param fileName the file to load.
     */
    public void loadImage(final String fileName) {
        if (fileName == null) {
            clear();
        } else {
            loadImage(new File(fileName));
        }
    }
    
    /**
     * Loads the given image file and displays it. If the file could not be loaded,
     * no image is displayed.
     * 
     * @param file the file to load.
     */
    public void loadImage(final File file) {
        if (file == null || !file.exists() || !file.isFile() || !file.canRead()) {
            clear();
            return;
        }
        
        // Dispose of the old image, if any
        if (currentImage != null) {
            currentImage.dispose();
        }
        
        // Load the new image
        currentImage = new Image(getDisplay(), file.getAbsolutePath());
        
        // Calculate the size of the zoomed image and center it
        Rectangle imgBounds = currentImage.getBounds();
        zoomedImgSize = new Point(
                (int) (imgBounds.width * zoomFactor),
                (int) (imgBounds.height * zoomFactor));
        centerImage();
        
        syncScrollBars();
        redraw();
    }
    
    /**
     * Clears the image.
     */
    public void clear() {
        // Dispose of the old image, if any
        if (currentImage != null) {
            currentImage.dispose();
            currentImage = null;
        }
        
        // Reset the geometry
        zoomedImgSize = new Point(0, 0);
        zoomedImgOffset = new Point(0, 0);
        
        syncScrollBars();
        redraw();
    }
    
    /**
     * Checks if the control is currently displaying an image.
     * 
     * @return {@code true} if an image is currently being displayed.
     */
    public boolean isImageLoaded() {
        return currentImage != null;
    }
    
    /**
     * Sets the zoom level.
     * 
     * @param zoom the new zoom level. {@code 1.0} displays the image in its
     *             original size. Must be at least {@link #MIN_ZOOM} and at
     *             most {@link #MAX_ZOOM}.
     * @throws IllegalArgumentException if the zoom level is outside the allowed boundaries.
     */
    public void setZoom(final float zoom) {
        if (zoom < MIN_ZOOM || zoom > MAX_ZOOM) {
            throw new IllegalArgumentException("The zoom factor must be between " + MIN_ZOOM
                    + " and " + MAX_ZOOM + ".");
        }
        
        zoomFactor = zoom;
        
        if (isImageLoaded()) {
            Rectangle imageBounds = currentImage.getBounds();
            zoomedImgSize.x = (int) (imageBounds.width * zoomFactor);
            zoomedImgSize.y = (int) (imageBounds.height * zoomFactor);
        }
        
        syncScrollBars();
        redraw();
    }
    
    /**
     * Sets the zoom level such that the image fits into the canvas.
     */
    public void zoomToFit() {
        if (!isImageLoaded()) {
            return;
        }
        
        Rectangle imageBounds = currentImage.getBounds();
        Rectangle clientArea = this.getClientArea();
        
        // The smallest zoom factor is the one that makes the longer side of the image
        // fit into the canvas
        float zoomToFitLevel = Math.min(
                ((float) clientArea.width) / ((float) imageBounds.width),
                ((float) clientArea.height) / ((float) imageBounds.height));
        
        // Make sure we don't exceed the allowed zoom boundaries
        setZoom(Math.max(MIN_ZOOM, Math.min(MAX_ZOOM, zoomToFitLevel)));
    }
    
    /**
     * Returns the zoom level.
     * 
     * @return the zoom level.
     */
    public float getZoom() {
        return zoomFactor;
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Utility Methods
    
    /**
     * Synchronizes the scroll bars with the current zoom and pan settings.
     */
    private void syncScrollBars() {
        Rectangle clientArea = this.getClientArea();
        ScrollBar hScrollBar = this.getHorizontalBar();
        ScrollBar vScrollBar = this.getVerticalBar();
        
        // Make sure the zoomed image offset is sensible
        zoomedImgOffset.x = Math.max(0,
                Math.min(zoomedImgOffset.x, zoomedImgSize.x - clientArea.width));
        zoomedImgOffset.y = Math.max(0,
                Math.min(zoomedImgOffset.y, zoomedImgSize.y - clientArea.height));
        
        // Horizontal scroll bar
        if (clientArea.width >= zoomedImgSize.x) {
            hScrollBar.setEnabled(false);
        } else {
            hScrollBar.setEnabled(true);
            
            hScrollBar.setMinimum(0);
            hScrollBar.setMaximum(zoomedImgSize.x);
            hScrollBar.setIncrement(SCROLL_BAR_INCREMENT);
            hScrollBar.setPageIncrement(clientArea.width);
            hScrollBar.setThumb(clientArea.width);
            
            hScrollBar.setSelection(zoomedImgOffset.x);
        }
        
        // Vertical scroll bar
        if (clientArea.height >= zoomedImgSize.y) {
            vScrollBar.setEnabled(false);
        } else {
            vScrollBar.setEnabled(true);
            
            vScrollBar.setMinimum(0);
            vScrollBar.setMaximum(zoomedImgSize.y);
            vScrollBar.setIncrement(SCROLL_BAR_INCREMENT);
            vScrollBar.setPageIncrement(clientArea.height);
            vScrollBar.setThumb(clientArea.height);
            
            vScrollBar.setSelection(zoomedImgOffset.y);
        }
    }
    
    /**
     * Calculates the image offset such that the image will be centered.
     */
    private void centerImage() {
        Rectangle clientArea = this.getClientArea();
        
        if (clientArea.width >= zoomedImgSize.x) {
            zoomedImgOffset.x = 0;
        } else {
            zoomedImgOffset.x = (zoomedImgSize.x - clientArea.width) / 2;
        }
        
        if (clientArea.height >= zoomedImgSize.y) {
            zoomedImgOffset.y = 0;
        } else {
            zoomedImgOffset.y = (zoomedImgSize.y - clientArea.height) / 2;
        }
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Event Handling
    
    /**
     * Paints the picture!
     * 
     * @param gc context to paint on.
     */
    private void paint(final GC gc) {
        Rectangle clientArea = this.getClientArea();
        
        // First, fill the background with white. This is very lazy in that we also fill
        // areas that get covered later by the image anyway.
        gc.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_WHITE));
        gc.setClipping(clientArea);
        gc.fillRectangle(clientArea);
        
        if (isImageLoaded()) {
            Rectangle imageBounds = currentImage.getBounds();
            
            // The destination rectangle
            Rectangle dstRect = new Rectangle(
                    zoomedImgOffset.x,
                    zoomedImgOffset.y,
                    Math.min(zoomedImgSize.x, clientArea.width),
                    Math.min(zoomedImgSize.y, clientArea.height));
            
            // Find the source rectangle in the original picture
            Rectangle srcRect = new Rectangle(
                    (int) (dstRect.x / zoomFactor),
                    (int) (dstRect.y / zoomFactor),
                    (int) (dstRect.width / zoomFactor),
                    (int) (dstRect.height / zoomFactor));
            srcRect = srcRect.intersection(imageBounds);
            
            // If the destination rectangle is smaller than the client area,
            // center it accordingly
            dstRect.x = Math.max(0, (clientArea.width - dstRect.width) / 2);
            dstRect.y = Math.max(0, (clientArea.height - dstRect.height) / 2);
            
            // Draw the image
            gc.drawImage(currentImage,
                    srcRect.x, srcRect.y, srcRect.width, srcRect.height,
                    dstRect.x, dstRect.y, dstRect.width, dstRect.height);
        }
    }
}
