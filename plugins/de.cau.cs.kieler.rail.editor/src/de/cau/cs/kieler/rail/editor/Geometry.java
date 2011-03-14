package de.cau.cs.kieler.rail.editor;
/**
 * 
 * Responsible for some geometry methods.
 * @author hdw
 *
 */
public class Geometry {
    private static final int X1 = 0;
    private static final int Y1 = 1;
    private static final int X2 = 2;
    private static final int Y2 = 3;
	
	/**
	 * Degree to radiant
	 * @param angle the angle in degree
	 * @return calculate degree (°) to radiant
	 */
	public static double degreeToRad(final double angle){
		return angle * Math.PI / 180;
	}
	/**
	 * A standard constructor
	 */
	public Geometry() {

	}
	/**
	 * 
	 * @param relativStartY
	 * @param angle
	 * @param relativEndHeight
	 * @return get the relative weight
	 */
	public static double getRelativWeight(final double relativStartY,
	final double angle, final double relativEndHeight){
		double m = Math.tan(angle);
		return ((relativEndHeight) / m);  //-relativStartY
	}
	
	// TODO better comment
    /**
     * Calculate the Y pos for the straight line (port Stamm to port Ende) for a
     * x pos.
     * @param xy1Xy2
     *            the position array witch the line descrips (X1, Y1, X2, Y2)
     * @param x
     *            the x position
     * @return the y position
     */
    public static int getYFromArray(final int[] xy1Xy2, final int x) {
        double m =
                (xy1Xy2[Y2] - xy1Xy2[Y1])
                        / (xy1Xy2[X2] - xy1Xy2[X1]);
        double b = xy1Xy2[Y1] - m * xy1Xy2[X1];
        return (int) (m * x + b);
    }
    
    /**
     * calculate the Slope of a linear function.
     * @param xy1Xy2 this 2 Point are needet
     * @return the slope
     */
    public static double getSlopeLineaFunction(final int[] xy1Xy2) {
    	double m =
            (xy1Xy2[Y2] - xy1Xy2[Y1])
                    / (xy1Xy2[X2] - xy1Xy2[X1]);
    	return m;
    }
    /**
     * Calculate the offset of a linear function
     * @param m the slope of the function
     * @param x the x position
     * @param y the y position
     * @return the offset.
     */
    public static double getOffsetLineaFunction(final double m, final int x, final int y) {
    	return y - m * x;
    }
    
    /**
     * Calculate the offset of a linear function
     * @param m the slope of the function
     * @param x the x position
     * @param y the y position
     * @return the offset.
     */
    public static double getOffsetLineaFunction(final int[] xy, final double m) {
    	double b = xy[Y1] - m * xy[X1];
    	return b;
    }
    
    public static int[] getInterceptPoint (final double m1, final double b1, final int[] f2xy1xy2) {
    	double m2 = getSlopeLineaFunction(f2xy1xy2);
    	double b2 = getOffsetLineaFunction(f2xy1xy2, m2);
    	
    	double x = (b2-b1) / (m1-m2);
    	double y = m1*x + b1;
    	return new int[] {(int) x,(int) y};
    }
    /**
     * Get the orthogonality intercept point
     * @param x 
     * @param y
     * @param xy1Xy2
     * @return
     */
    public static int[] getOrthogonalityInterceptPoint(final int x, final int y, final int[] xy1Xy2) {
    	// Orthogonality mathematically f_1'(x) = -1/f_2'(x)
    	double m = -1/getSlopeLineaFunction(xy1Xy2);
    	double b = getOffsetLineaFunction(m,x,y);
    	return getInterceptPoint(m,b,xy1Xy2);
    }
}
