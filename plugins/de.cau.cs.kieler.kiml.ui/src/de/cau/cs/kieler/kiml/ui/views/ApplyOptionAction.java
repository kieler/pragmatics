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

import java.util.ListIterator;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
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
import de.cau.cs.kieler.kiml.ui.layout.KimlUiUtil;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage;

/**
 * An action that applies the selected layout option as default for the whole diagram.
 *
 * @author msp
 */
public class ApplyOptionAction extends Action {
    
    /** the property sheet page. */
    private PropertySheetPage page;
    
    /**
     * Creates an apply option action.
     * 
     * @param thepage property sheet page for which the action is created
     * @param text user friendly text
     */
    public ApplyOptionAction(final PropertySheetPage thepage, final String text) {
        super(text);
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
        LayoutOptionData optionData = null;
        for (LayoutOptionData data : LayoutServices.getInstance().getLayoutOptionData()) {
            if (data.getName().equals(entry.getDisplayName())) {
                optionData = data;
                break;
            }
        }
        // get the diagram edit part of the active editor
        IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage().getActiveEditor();
        if (optionData != null && editorPart instanceof DiagramEditor) {            
            IGraphicalEditPart diagramEditPart = (IGraphicalEditPart) ((DiagramEditor) editorPart)
                    .getDiagramGraphicalViewer().getContents();
            entry.getValueAsString();
            
            // get the layout option style
            LayoutOptionStyle optionStyle = (LayoutOptionStyle)diagramEditPart.getNotationView()
                    .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            if (optionStyle == null) {
                optionStyle = KimlUiUtil.addLayoutOptionStyle(diagramEditPart.getNotationView(),
                        diagramEditPart.getEditingDomain());
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
                koption = KimlUiUtil.addKOption(optionStyle, optionData,
                        diagramEditPart.getEditingDomain());
            }
            
            // set the new option value
            Object value = optionData.parseValue(entry.getValueAsString());
            if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
                value = GmfLayoutPropertySource.getLayoutHint((String) value);
            }
            KimlUiUtil.setValue(koption, optionData, value, diagramEditPart.getEditingDomain());
            KimlUiUtil.setDefault(koption, true, diagramEditPart.getEditingDomain());
            
            // remove the option from all children
            removeChildOptions(diagramEditPart.getNotationView(), diagramEditPart.getEditingDomain(),
                    optionData.getId());
        }
    }
    
    /**
     * Removes all options with given id from the children of the notation view using
     * a transactional command.
     * 
     * @param notationView a notation view
     * @param editingDomain the editing domain for the corresponding edit part
     * @param optionId  an option identifier
     */
    private void removeChildOptions(final View notationView,
            final TransactionalEditingDomain editingDomain, final String optionId) {
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            protected void doExecute() {
                removeChildOptions(notationView, optionId);
            }
         });
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
            removeOption(node, optionId);
            for (Object edgeObj : node.getTargetEdges()) {
                View edge = (View) edgeObj;
                removeOption(edge, optionId);
                removeChildOptions(edge, optionId);
            }
            removeChildOptions(node, optionId);
        }
    }
    
    /**
     * Removes the option with given id from the notation view.
     * 
     * @param notationView a notation view
     * @param optionId an option identifier
     */
    private void removeOption(final View notationView, final String optionId) {
        LayoutOptionStyle optionStyle = (LayoutOptionStyle)notationView
                .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (optionStyle != null) {
            ListIterator<KOption> optionIter = optionStyle.getOptions().listIterator();
            while (optionIter.hasNext()) {
                if (optionIter.next().getKey().equals(optionId)) {
                    optionIter.remove();
                    break;
                }
            }
        }
    }

}
