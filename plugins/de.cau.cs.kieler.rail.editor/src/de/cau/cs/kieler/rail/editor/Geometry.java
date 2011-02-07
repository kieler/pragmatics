package de.cau.cs.kieler.rail.editor;
/**
 * 
 * Responsible for some geometry methods.
 * @author hdw
 *
 */
public class Geometry {
	public static double degreeToRad(double angle){
		return angle * Math.PI / 180;
	}
	
	/**
	 * 
	 * @param relativStartY
	 * @param angle
	 * @param relativEndHeight
	 * @return
	 */
	public static double getRelativWeight(double relativStartY,
	double angle, double relativEndHeight){
		double m = Math.tan(angle);
		return ((relativEndHeight)/m);  //-relativStartY
	}
}
