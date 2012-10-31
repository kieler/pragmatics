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
package de.cau.cs.kieler.klay.planar;

/**
 * Contains constants which could be used in all areas of klay planar.
 * 
 * @author pkl
 */
public final class PConstants {

    // ---------------- high-degree node ---------------------------

    /** The high degree position index for the start position of the x-coordinate. */
    public static final int X_COR = 0;

    /** The high degree position index for the start position of the y-coordinate. */
    public static final int Y_COR = 1;

    /** The high degree position index for the node width. */
    public static final int WIDTH_POS = 2;

    /** The high degree position index for the node height. */
    public static final int HEIGHT_POS = 3;

    private PConstants() {
    }

}
