/*
 * generated by Xtext
 */
package de.cau.cs.kieler.kiml.config.text.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;

public class AbstractLayoutConfigValidator extends org.eclipse.xtext.validation.AbstractDeclarativeValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://elk.eclipse.org/KGraph"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://elk.eclipse.org/KLayoutData"));
		return result;
	}
}
