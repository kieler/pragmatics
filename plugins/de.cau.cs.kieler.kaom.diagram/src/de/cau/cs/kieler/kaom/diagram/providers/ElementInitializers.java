package de.cau.cs.kieler.kaom.diagram.providers;

import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

    protected ElementInitializers() {
        // use #getInstance to access cached instance
    }

    /**
     * @generated
     */
    public static ElementInitializers getInstance() {
        ElementInitializers cached = KaomDiagramEditorPlugin.getInstance().getElementInitializers();
        if (cached == null) {
            KaomDiagramEditorPlugin.getInstance().setElementInitializers(
                    cached = new ElementInitializers());
        }
        return cached;
    }

}
