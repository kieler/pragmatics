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
package de.cau.cs.kieler.kiml.util;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Interface for debug canvas.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public interface IDebugCanvas {

    /** color definitions. */
    public enum Color {
        /** the color black. */
        BLACK,
        /** the color blue. */
        BLUE,
        /** the color cyan. */
        CYAN,
        /** the color gray. */
        GRAY,
        /** the color green. */
        GREEN,
        /** the color orange. */
        ORANGE,
        /** the color red. */
        RED,
        /** the color white. */
        WHITE,
        /** the color yellow. */
        YELLOW
    }
    
    /**
     * Sets an offset for this canvas.
     * 
     * @param parentNode the parent node that defines the current offset
     * @param addx additional horizontal offset
     * @param addy additional vertical offset
     */
    void setOffset(KNode parentNode, float addx, float addy);

    /**
     * Draws a filled rectangle on the canvas.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     * @param color the color
     */
    void drawFilledRectangle(float x, float y, float w, float h, Color color);

    /**
     * 
     * Draws a non-filled rectangle one the canvas.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     * @param color the color
     */
    void drawRectangle(float x, float y, float w, float h, Color color);

    /**
     * Draws a filled ellipse on the canvas.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     * @param color the color
     */
    void drawFilledEllipse(float x, float y, float w, float h, Color color);

    /**
     * Draws a non-filled ellipse on the canvas.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param w the width
     * @param h the height
     * @param color the color
     */
    void drawEllipse(float x, float y, float w, float h, Color color);

    /**
     * Draws a filled circle on the canvas.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param d the diameter
     * @param color the color
     */
    void drawFilledCircle(float x, float y, float d, Color color);

    /**
     * Draws a non-filled circle on the canvas.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param d the diameter
     * @param color the color
     */
    void drawCircle(float x, float y, float d, Color color);

    /**
     * Draws a line on the canvas.
     * 
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     * @param color the color
     */
    void drawLine(float x1, float y1, float x2, float y2, Color color);

    /**
     * Draws a string on the canvas.
     * 
     * @param string the string
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param color the color
     */
    void drawString(String string, float x, float y, Color color);

    /**
     * Clears the canvas and the figure buffer.
     */
    void clear();
    
    /**
     * Sets the canvas to buffered mode or non-buffered mode.
     * 
     * @param buffered whether buffered mode shall be active
     */
    void setBuffered(boolean buffered);

    /**
     * Draws the buffered figures and clears the buffer. This has only an effect if the
     * canvas is set to buffered.
     */
    void drawBuffer();

}
