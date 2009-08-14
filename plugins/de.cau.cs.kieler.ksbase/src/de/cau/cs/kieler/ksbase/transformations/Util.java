package de.cau.cs.kieler.ksbase.transformations;

import org.eclipse.emf.ecore.EObject;

public class Util {

	public static void Select(EObject list) {
		System.out.println(list.eClass());
	}
}
