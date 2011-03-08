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
	public static double degreeToRad(double angle){
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
		return ((relativEndHeight)/m);  //-relativStartY
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
}
