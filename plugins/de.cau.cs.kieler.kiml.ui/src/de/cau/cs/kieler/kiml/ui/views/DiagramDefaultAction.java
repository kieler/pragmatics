/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.views;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.views.properties.IPropertySheetEntry;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * An action that applies the selected layout option as default for the whole diagram.
 *
 * @kieler.rating 2010-01-26 proposed yellow msp
 * @author msp
 */
public class DiagramDefaultAction extends Action {
    
    /** an identifier for the action. */
    public static final String ACTION_ID = "kieler.diagram.default";
    
    /** the icon used for this action. */
    private static ImageDescriptor icon = KimlUiPlugin.getImageDescriptor(
            "icons/menu16/apply2diagram.gif");

    /** the layout view that created this action. */
    private LayoutViewPart layoutView;

    /**
     * Creates an apply option action.
     * 
     * @param thelayoutView the layout view that created this action
     * @param text user friendly text
     */
    public DiagramDefaultAction(final LayoutViewPart thelayoutView, final String text) {
        super(text, icon);
        this.layoutView = thelayoutView;
    }
    
    /**
     * {@inheritDoc}
     */
    public void run() {
        IEditorPart editorPart = layoutView.getCurrentEditor();
        if (editorPart instanceof GraphicalEditor) {
            GraphicalEditor graphEditor = (GraphicalEditor) editorPart;
            EditPart diagram = (EditPart) graphEditor.getAdapter(EditPart.class);
            DiagramLayoutManager manager = layoutView.getCurrentManager();
            if (manager != null) {
                ILayoutInspector inspector = manager.getInspector(diagram);
                for (IPropertySheetEntry entry : layoutView.getSelection()) {
                    applyOption(inspector, entry);
                }
            }
        }
    }
    
    /**
     * Sets the layout option of the given property sheet entry as default for the whole
     * diagram.
     * 
     * @param entry a property sheet entry
     */
    private void applyOption(final ILayoutInspector inspector, final IPropertySheetEntry entry) {
        LayoutOptionData<?> theOptionData = KimlUiUtil.getOptionData(
                layoutView.getCurrentProviderData(), entry.getDisplayName());
        if (theOptionData == null) {
            return;
        }

        final Object value = theOptionData.parseValue(entry.getValueAsString());
        if (value != null) {
            final LayoutOptionData<?> optionData = theOptionData;
            Runnable modelChange = new Runnable() {
                public void run() {
                    inspector.setDefault(optionData, value);
                }
            };
            KimlUiUtil.runModelChange(modelChange, inspector.getEditingDomain(),
                    Messages.getString("kiml.ui.13"));
        }
    }

}
