/*
 * generated by Xtext 2.9.2
 */
package de.cau.cs.kieler.kgraph.text.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public abstract class AbstractKGraphValidator extends AbstractDeclarativeValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://elk.eclipse.org/KGraph"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://elk.eclipse.org/KLayoutData"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://kieler.cs.cau.de/KRendering"));
		return result;
	}
	
}