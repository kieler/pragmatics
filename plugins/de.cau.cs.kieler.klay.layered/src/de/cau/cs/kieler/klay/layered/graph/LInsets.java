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
package de.cau.cs.kieler.klay.layered.graph;

/**
 * Stores the insets of an object. This is only the abstract superclass. The actual storage
 * representation of the insets is left to subclasses.
 * 
 * @author cds
 * @see LInsets.Double
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public class LInsets {
    // Allow public fields in this utility classes.
    // CHECKSTYLEOFF VisibilityModifier
    
    /**
     * Specifies {@code KInsets} in {@code double} coordinates.
     * 
     * @author cds
     */
    public static class Double extends LInsets {
        /**
         * The inset from the top.
         */
        public double top = 0.0;
        
        /**
         * The inset from the bottom.
         */
        public double bottom = 0.0;
        
        /**
         * The inset from the left.
         */
        public double left = 0.0;
        
        /**
         * The inset from the right.
         */
        public double right = 0.0;
        
        
        /**
         * Creates a new instance with all fields set to {@code 0.0}.
         */
        public Double() {
            
        }
        
        /**
         * Creates a new instance initialized with the given values.
         * 
         * @param top the inset from the top.
         * @param left the inset from the left.
         * @param bottom the inset from the bottom.
         * @param right the inset from the right.
         */
        public Double(final double top, final double left, final double bottom, final double right) {
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
        }
        
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof LInsets.Double) {
                LInsets.Double other = (LInsets.Double) obj;
                
                return this.top == other.top
                    && this.bottom == other.bottom
                    && this.left == other.left
                    && this.right == other.right;
            } else {
                return false;
            }
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            // I don't care very much to define explicit constants for  this calculation.
            // CHECKSTYLEOFF MagicNumber
            
            int code1 = java.lang.Double.valueOf(left).hashCode() << 16;
            code1 |= java.lang.Double.valueOf(bottom).hashCode() & 0xffff;
            
            int code2 = java.lang.Double.valueOf(right).hashCode() << 16;
            code2 |= java.lang.Double.valueOf(top).hashCode() & 0xffff;
            
            return code1 ^ code2;
            
            // CHECKSTYLEON MagicNumber
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return getClass().getName()
                + "[top="  + top
                + ",left=" + left
                + ",bottom=" + bottom
                + ",right=" + right + "]";
        }
        
        /**
         * Copy insets values from another double valued insets.
         * 
         * @param other another insets
         */
        public void copy(final Double other) {
            this.left = other.left;
            this.right = other.right;
            this.top = other.top;
            this.bottom = other.bottom;
        }
        
    }
    
}
