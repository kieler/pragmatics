package de.cau.cs.kieler.ksbase.validation;

import org.eclipse.xtext.validation.Check;

import de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitions;
import de.cau.cs.kieler.ksbase.featureDefinition.FeatureType;


public class FeatureDefinitionJavaValidator extends AbstractFeatureDefinitionJavaValidator {

    boolean hasGlobalFile = false;

    @Check
    public void checkFeatureDefinition(FeatureDefinitions defs) {
        checkFile(defs.getFeatureFile());
        hasGlobalFile = true;
    }

    @Check
    public void checkFeatureType(FeatureType type) {
        if (hasGlobalFile) {
            checkFile(type.getFileName());
            warning(
                    "Global extension file has been defined",
                    de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage.FEATURE_TYPE__FILE_NAME);
        }
    }

    private void checkFile(String filename) {
        if (!filename.endsWith(".ext")) {
            error(
                    "Filename has to end with .ext",
                    de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage.FEATURE_TYPE__FILE_NAME);
        }
        //A File.exists would be nice, but does not seem to be possible from here
    }
}
