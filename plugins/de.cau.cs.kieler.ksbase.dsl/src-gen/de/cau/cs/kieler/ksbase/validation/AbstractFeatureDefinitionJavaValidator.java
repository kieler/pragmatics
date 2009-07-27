package de.cau.cs.kieler.ksbase.validation;
 
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.ComposedChecks;

@ComposedChecks(validators= {org.eclipse.xtext.validation.ImportUriValidator.class})
public class AbstractFeatureDefinitionJavaValidator extends AbstractDeclarativeValidator {

    @Override
    protected List<? extends EPackage> getEPackages() {
        List<EPackage> result = new ArrayList<EPackage>();
        result.add(de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage.eINSTANCE);
        return result;
    }

}
