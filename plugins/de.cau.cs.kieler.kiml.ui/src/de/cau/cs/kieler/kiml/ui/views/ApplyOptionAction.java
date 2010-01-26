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

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySheetEntry;

import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.KimlUiUtil;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsFactory;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage;

/**
 * An action that applies the selected layout option as default for the whole diagram.
 *
 * @kieler.rating 2010-01-26 proposed yellow msp
 * @author msp
 */
public class ApplyOptionAction extends Action {
    
    /** an identifier for the action. */
    public static final String ACTION_ID = "kieler.apply.option";
    
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
    public ApplyOptionAction(final LayoutViewPart thelayoutView, final String text) {
        super(text, icon);
        this.layoutView = thelayoutView;
    }
    
    /**
     * {@inheritDoc}
     */
    public void run() {
        IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage().getActiveEditor();
        if (editorPart instanceof DiagramEditor) {
            DiagramEditor diagramEditor = (DiagramEditor) editorPart;
            for (IPropertySheetEntry entry : layoutView.getSelection()) {
                applyOption(diagramEditor, entry);
            }
        }
    }
    
    /**
     * Sets the layout option of the given property sheet entry as default for the whole
     * diagram.
     * 
     * @param diagramEditor the currently active diagram editor
     * @param entry a property sheet entry
     */
    private void applyOption(final DiagramEditor diagramEditor, final IPropertySheetEntry entry) {
        LayoutOptionData theOptionData = null;
        for (LayoutOptionData data : LayoutServices.getInstance().getLayoutOptionData()) {
            if (data.getName().equals(entry.getDisplayName())) {
                theOptionData = data;
                break;
            }
        }
        final Object value = theOptionData.parseValue(entry.getValueAsString());
        if (theOptionData != null && value != null) {
            final IGraphicalEditPart diagramEditPart = (IGraphicalEditPart) diagramEditor
                    .getDiagramGraphicalViewer().getContents();
            final LayoutOptionData optionData = theOptionData;
            Runnable modelChange = new Runnable() {
                @SuppressWarnings("unchecked")
                public void run() {
                    View notationView = diagramEditPart.getNotationView();
                    
                    // get the layout option style
                    LayoutOptionStyle optionStyle = (LayoutOptionStyle) notationView
                            .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
                    if (optionStyle == null) {
                        optionStyle = LayoutOptionsFactory.eINSTANCE.createLayoutOptionStyle();
                        notationView.getStyles().add(optionStyle);
                    }
                    
                    // get the layout option
                    KOption koption = null;
                    for (KOption opt : optionStyle.getOptions()) {
                        if (opt.getKey().equals(optionData.getId())) {
                            koption = opt;
                            break;
                        }
                    }
                    if (koption == null) {
                        koption = KimlUiUtil.addKOption(optionStyle, optionData);
                    }
                    
                    // set the new option value
                    if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
                        KimlLayoutUtil.setValue(koption, optionData,
                                GmfLayoutPropertySource.getLayoutHint((String) value));
                    } else {
                        KimlLayoutUtil.setValue(koption, optionData, value);
                    }
                    koption.setDefault(true);
                    
                    // remove the option from all children
                    removeChildOptions(notationView, optionData.getId());
                }
            };
            KimlUiUtil.runModelChange(modelChange, diagramEditPart.getEditingDomain(),
                    Messages.getString("kiml.ui.13"));
        }
    }
    
    /**
     * Removes all options with given id from the children of the notation view recursively.
     * 
     * @param notationView a notation view
     * @param optionId an option identifier
     */
    private void removeChildOptions(final View notationView, final String optionId) {
        for (Object child : notationView.getPersistedChildren()) {
            View node = (View) child;
            LayoutOptionStyle optionStyle = (LayoutOptionStyle) node.getStyle(
                    LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            if (optionStyle != null) {
                KimlUiUtil.removeKOption(optionStyle, optionId);
            }
            for (Object edgeObj : node.getTargetEdges()) {
                View edge = (View) edgeObj;
                optionStyle = (LayoutOptionStyle) edge.getStyle(
                        LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
                if (optionStyle != null) {
                    KimlUiUtil.removeKOption(optionStyle, optionId);
                }
                removeChildOptions(edge, optionId);
            }
            removeChildOptions(node, optionId);
        }
    }

}
