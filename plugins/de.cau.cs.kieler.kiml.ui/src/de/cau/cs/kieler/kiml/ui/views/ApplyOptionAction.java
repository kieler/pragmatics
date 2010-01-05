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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetPage;

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
 * @author msp
 */
public class ApplyOptionAction extends Action {
    
    /** the property sheet page. */
    private PropertySheetPage page;
    /** the icon used for this action. */
    private static ImageDescriptor icon = KimlUiPlugin.getImageDescriptor(
            "icons/menu16/apply2diagram.gif");
    
    /**
     * Creates an apply option action.
     * 
     * @param thepage property sheet page for which the action is created
     * @param text user friendly text
     */
    public ApplyOptionAction(final PropertySheetPage thepage, final String text) {
        super(text, icon);
        this.page = thepage;
    }
    
    /**
     * {@inheritDoc}
     */
    public void run() {
        TreeItem[] treeItems = ((Tree)page.getControl()).getSelection();
        for (TreeItem item : treeItems) {
            Object data = item.getData();
            if (data instanceof IPropertySheetEntry) {
                applyOption((IPropertySheetEntry) data);
            } else {
                // a category was selected, apply options for all children
                for (TreeItem childItem : item.getItems()) {
                    data = childItem.getData();
                    if (data instanceof IPropertySheetEntry) {
                        applyOption((IPropertySheetEntry) data);
                    }
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
    private void applyOption(final IPropertySheetEntry entry) {
        LayoutOptionData theOptionData = null;
        for (LayoutOptionData data : LayoutServices.getInstance().getLayoutOptionData()) {
            if (data.getName().equals(entry.getDisplayName())) {
                theOptionData = data;
                break;
            }
        }
        // get the diagram edit part of the active editor
        IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage().getActiveEditor();
        if (theOptionData != null && editorPart instanceof DiagramEditor) {
            final IGraphicalEditPart diagramEditPart = (IGraphicalEditPart) ((DiagramEditor) editorPart)
                    .getDiagramGraphicalViewer().getContents();
            final LayoutOptionData optionData = theOptionData;
            Runnable modelChange = new Runnable() {
                @SuppressWarnings("unchecked")
                public void run() {
                    View notationView = diagramEditPart.getNotationView();
                    
                    // get the layout option style
                    LayoutOptionStyle optionStyle = (LayoutOptionStyle)notationView
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
                    Object value = optionData.parseValue(entry.getValueAsString());
                    if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
                        value = GmfLayoutPropertySource.getLayoutHint((String) value);
                    }
                    KimlLayoutUtil.setValue(koption, optionData, value);
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
            LayoutOptionStyle optionStyle = (LayoutOptionStyle)node
                    .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            if (optionStyle != null) {
                KimlUiUtil.removeKOption(optionStyle, optionId);
            }
            for (Object edgeObj : node.getTargetEdges()) {
                View edge = (View) edgeObj;
                optionStyle = (LayoutOptionStyle)edge
                        .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
                if (optionStyle != null) {
                    KimlUiUtil.removeKOption(optionStyle, optionId);
                }
                removeChildOptions(edge, optionId);
            }
            removeChildOptions(node, optionId);
        }
    }

}
