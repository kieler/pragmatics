package de.cau.cs.kieler.rail.editor;
/**
 * 
 * Responsible for some geometry methods.
 * @author hdw
 *
 */
public class Geometry {
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
}
