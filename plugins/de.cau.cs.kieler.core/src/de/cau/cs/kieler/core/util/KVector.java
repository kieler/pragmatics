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
package de.cau.cs.kieler.core.util;

import de.cau.cs.kieler.core.util.KielerMath.Point;

/**
 * A simple 2d-vector class which supports translation, scaling, normalization ...
 * 
 * Extends <code>KielerMath.Point</code>, because there are some calculations defined on Point.
 * 
 * @author uru
 * @author owo
 * 
 */
public class KVector extends Point {

    private static final double HALF_ROTATION = 180d;

    /**
     * Create vector with default coordinates (0,0).
     */
    public KVector() {
        super(0.0d, 0.0d);
    }

    /**
     * Constructs new Vector from given values.
     * 
     * @param x
     *            x value
     * @param y
     *            y value
     */
    public KVector(final double x, final double y) {
        super(x, y);
    }

    /**
     * Creates an exact copy of given vector v.
     * 
     * @param v
     *            vector
     */
    public KVector(final KVector v) {
        this(v.x, v.y);
    }

    /**
     * Compute euclidean norm (a.k.a length).
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
     * Returns sum of two given vectors.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            snd vector
     * @return new Vector first + snd
     */
    public static KVector add(final KVector v1, final KVector v2) {
        return new KVector(v1.x + v2.x, v1.y + v2.y);
    }

    /**
     * Vector subtraction.
     * 
     * @param v
     *            vector to subtract
     * @return <code>this</code>
     */
    public final KVector sub(final KVector v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    /**
     * Returns the subtraction of the two given vectors.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            snd vector
     * @return new Vector first - snd
     */
    public static KVector sub(final KVector v1, final KVector v2) {
        return new KVector(v1.x - v2.x, v1.y - v2.y);
    }

    /**
     * Scale the vector.
     * 
     * @param scale
     *            scaling factor
     * @return <code>this</code>
     */
    public final KVector scale(final double scale) {
        this.x *= scale;
        this.y *= scale;
        return this;
    }

    /**
     * normalizes this vector.
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
     * returns negated vector.
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
     * 
     * @return x-coordinate
     * @deprecated
     */
    public final double getXpos() {
        return this.x;
    }

    /**
     * Return y-coordinate.
     * 
     * @return y-coordinate
     * @deprecated
     */
    public final double getYpos() {
        return this.y;
    }

    /**
     * Set new x-coordinate.
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
     * @param lambda
     *            scaling factor
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
     * @param v
     *            second addend
     * @return new vector which is the sum of <code>this</code> and <code>v</code>
     */
    public final KVector sumCreate(final KVector v) {
        return new KVector(this).add(v);
    }

    /**
     * Create a sum from this vector and another vector.
     * 
     * @param v
     *            subtrahend
     * @return new vector which is the difference between <code>this</code> and <code>v</code>
     */
    public final KVector differenceCreate(final KVector v) {
        return new KVector(this).sub(v);
    }

    /**
     * returns angle in degrees represented by this vector.
     * 
     * @return angle out of [0,360[
     */
    public double toAngle() {
        KVector thisTemp = this.normalizedCreate();
        double radians = Math.atan(thisTemp.x / thisTemp.y);
        if (radians < 0) {
            radians = 2 * Math.PI - Math.abs(radians);
        }
        double degrees = Math.toDegrees(radians);
        return degrees;
    }

    /**
     * Returns the distance between two vectors.
     * 
     * @param v2
     *            snd vector
     * @return distance between this and snd vector
     */
    public double distance(final KVector v2) {
        double dx = this.x - v2.x;
        double dy = this.y - v2.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Returns the distance between two vectors.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            snd vector
     * @return distance between first and snd
     */
    public static double distance(final KVector v1, final KVector v2) {
        double dx = v1.x - v2.x;
        double dy = v1.y - v2.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Returns the dot product of the two given vectors.
     * 
     * @param v2
     *            snd vector
     * @return (this.x * this.x) + (v1.y * v2.y)
     */
    public double productDot(final KVector v2) {
        return ((this.x * v2.x) + (this.y * v2.y));
    }

    /**
     * Returns the dot product of the two given vectors.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            snd vector
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
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof KVector) {
            KVector v = (KVector) obj;
            if (v.x == this.x && v.y == this.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Double.valueOf(x).hashCode() + Double.valueOf(y).hashCode();
    }

}
