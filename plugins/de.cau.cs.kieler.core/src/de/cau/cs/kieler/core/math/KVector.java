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
package de.cau.cs.kieler.core.math;

/**
 * A simple 2D vector class which supports translation, scaling, normalization etc.
 * 
 * @author uru
 * @author owo
 */
public class KVector {

    /** angle for a half rotation. */
    private static final double HALF_ROTATION = 180.0;

    // CHECKSTYLEOFF VisibilityModifier
    /** x coordinate. */
    public double x;
    /** y coordinate. */
    public double y;
    // CHECKSTYLEON VisibilityModifier
    
    /**
     * Create vector with default coordinates (0,0).
     */
    public KVector() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Constructs a new vector from given values.
     * 
     * @param thex x value
     * @param they y value
     */
    public KVector(final double thex, final double they) {
        this.x = thex;
        this.y = they;
    }

    /**
     * Creates an exact copy of a given vector v.
     * 
     * @param v existing vector
     */
    public KVector(final KVector v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * returns an exact copy of this vector.
     * 
     * @return identical vector
     */
    @Override
    public KVector clone() {
        return new KVector(this.x, this.y);
    }
    
    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object obj) {
        if (obj instanceof KVector) {
            KVector other = (KVector) obj;
            return this.x == other.x && this.y == other.y;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return Double.valueOf(x).hashCode() + Double.valueOf(y).hashCode();
    }

    /**
     * Compute euclidean norm (a.k.a length).
     * FIXME why define two names for the same function? (msp)
     * 
     * @return length of this vector
     */
    public final double getNorm() {
        return getLength();
    }

    /**
     * returns this vector's length.
     * 
     * @return Math.sqrt(x*x + y*y)
     */
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * returns square length of this vector.
     * 
     * @return x*x + y*y
     */
    public double getSquareLength() {
        return x * x + y * y;
    }

    /**
     * Set vector to (0,0).
     * 
     * @return <code>this</code>
     */
    public final KVector reset() {
        this.x = 0.0;
        this.y = 0.0;
        return this;
    }

    /**
     * Vector addition.
     * 
     * @param v
     *            vector to add
     * @return <code>this + v</code>
     */
    public final KVector add(final KVector v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    /**
     * Returns the sum of two given vectors as a new vector instance.
     * 
     * @param v1 first vector
     * @param v2 second vector
     * @return new vector first + second
     */
    public static KVector add(final KVector v1, final KVector v2) {
        return new KVector(v1.x + v2.x, v1.y + v2.y);
    }

    /**
     * Vector subtraction.
     * 
     * @param v vector to subtract
     * @return <code>this</code>
     */
    public final KVector sub(final KVector v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    /**
     * Returns the subtraction of the two given vectors as a new vector instance.
     * 
     * @param v1 first vector
     * @param v2 second vector
     * @return new vector first - second
     */
    public static KVector sub(final KVector v1, final KVector v2) {
        return new KVector(v1.x - v2.x, v1.y - v2.y);
    }

    /**
     * Scale the vector.
     * 
     * @param scale scaling factor
     * @return <code>this</code>
     */
    public final KVector scale(final double scale) {
        this.x *= scale;
        this.y *= scale;
        return this;
    }

    /**
     * Normalize the vector.
     * 
     * @return <code>this</code>
     */
    public KVector normalize() {
        double length = this.getLength();
        this.x = this.x / length;
        this.y = this.y / length;
        return this;
    }

    /**
     * Negate the vector.
     * 
     * @return <code>this</code>
     */
    public KVector negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    /**
     * Add some "noise" to this vector.
     */
    public final void wiggle() {
        final double theWiggle = 0.5;
        this.x += (Math.random() - theWiggle);
        this.y += (Math.random() - theWiggle);
    }

    /**
     * Return x-coordinate.
     * FIXME remove this
     * 
     * @return x-coordinate
     * @deprecated
     */
    public final double getXpos() {
        return this.x;
    }

    /**
     * Return y-coordinate.
     * FIXME remove this
     * 
     * @return y-coordinate
     * @deprecated
     */
    public final double getYpos() {
        return this.y;
    }

    /**
     * Set new x-coordinate.
     * FIXME remove this
     * 
     * @param newxpos
     *            new x-coordinate
     * @deprecated
     */
    public final void setXpos(final double newxpos) {
        this.x = newxpos;
    }

    /**
     * Set new y-coordinate.
     * FIXME remove this
     * 
     * @param newypos
     *            new y-coordinate
     * @deprecated
     */
    public final void setYpos(final double newypos) {
        this.y = newypos;
    }

    /**
     * Create a scaled version of this vector.
     * 
     * @param lambda scaling factor
     * @return new vector which is <code>this</code> scaled by <code>lambda</code>
     */
    public final KVector scaledCreate(final double lambda) {
        return new KVector(this).scale(lambda);
    }

    /**
     * Create a normalized version of this vector.
     * 
     * @return normalized copy of <code>this</code>
     */
    public final KVector normalizedCreate() {
        return new KVector(this).normalize();
    }

    /**
     * Create a sum from this vector and another vector.
     * 
     * @param v second addend
     * @return new vector which is the sum of <code>this</code> and <code>v</code>
     */
    public final KVector sumCreate(final KVector v) {
        return new KVector(this).add(v);
    }

    /**
     * Create a sum from this vector and another vector.
     * 
     * @param v subtrahend
     * @return new vector which is the difference between <code>this</code> and <code>v</code>
     */
    public final KVector differenceCreate(final KVector v) {
        return new KVector(this).sub(v);
    }

    /**
     * Returns the distance between two vectors.
     * 
     * @param v2 second vector
     * @return distance between this and second vector
     */
    public double distance(final KVector v2) {
        double dx = this.x - v2.x;
        double dy = this.y - v2.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Returns the distance between two vectors.
     * 
     * @param v1 first vector
     * @param v2 second vector
     * @return distance between first and second
     */
    public static double distance(final KVector v1, final KVector v2) {
        double dx = v1.x - v2.x;
        double dy = v1.y - v2.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Returns the dot product of the two given vectors.
     * 
     * @param v2 second vector
     * @return (this.x * this.x) + (v1.y * v2.y)
     */
    public double productDot(final KVector v2) {
        return ((this.x * v2.x) + (this.y * v2.y));
    }

    /**
     * Returns the dot product of the two given vectors.
     * 
     * @param v1 first vector
     * @param v2 second vector
     * @return (this.x * this.x) + (v1.y * v2.y)
     */
    public static double productDot(final KVector v1, final KVector v2) {
        return ((v1.x * v2.x) + (v1.y * v2.y));
    }

    /**
     * converts a given angle in degree to an equivalent vector.
     * 
     * @param angle
     *            angle out of [0,360[
     * @return normalized vector representing angle
     */
    public static KVector angleVector(final double angle) {
        double radians = (angle * Math.PI) / HALF_ROTATION;
        // vector automatically is normalized
        return new KVector(Math.sin(radians), Math.cos(radians));
    }

}
