package de.cau.cs.kieler.kaom.text.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Use this class to register components to be used within the IDE.
 */
public class KaomUiModule extends de.cau.cs.kieler.kaom.text.ui.AbstractKaomUiModule {
    
    public KaomUiModule(AbstractUIPlugin plugin) {
        super(plugin);
    }

    // customizations by chsch:
    
    /* get rid of the dawn XtextNature question */
    public Class<? extends org.eclipse.xtext.ui.editor.IXtextEditorCallback> bindIXtextEditorCallback() {
        return org.eclipse.xtext.ui.editor.IXtextEditorCallback.NullImpl.class;
    }
    
    /* introduces new highlighting profiles (e.g. annotationKey) */
    public Class<? extends org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration> bindIHighlightingConfiguration() {
        return KaomHighlightingConfiguration.class;
    }

    /* provides token based highlighting configurations */
    public Class<? extends org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper> bindAbstractAntlrTokenToAttributeIdMapper() {
        return KaomAntlrTokenToAttributeIdMapper.class;
    }
    
    /* provides a few additional highlighting rules */
    public Class<? extends org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator> bindISemanticHighlightingCalculator() {
        return KaomSemanticHighlightingCalculator.class;
    }
}
