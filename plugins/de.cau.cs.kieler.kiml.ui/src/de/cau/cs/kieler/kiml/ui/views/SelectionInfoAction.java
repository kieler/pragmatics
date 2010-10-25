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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.views.properties.IPropertySheetEntry;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * An action that displays useful information about the current selection.
 *
 * @author msp
 */
public class SelectionInfoAction extends Action {

    /** the icon used for this action. */
    private static ImageDescriptor icon = KimlUiPlugin.getImageDescriptor("icons/menu16/info.gif");
    
    /** the layout view that created this action. */
    private LayoutViewPart layoutView;

    /**
     * Creates a selection info action.
     * 
     * @param thelayoutView the layout view that created this action
     * @param text user friendly text
     */
    public SelectionInfoAction(final LayoutViewPart thelayoutView, final String text) {
        super(text, icon);
        this.layoutView = thelayoutView;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        MessageDialog.open(MessageDialog.INFORMATION, layoutView.getViewSite().getShell(),
                Messages.getString("kiml.ui.38"), createInfo(), SWT.SHEET);
    }
    
    /**
     * Create an information string to display to the user.
     * 
     * @return an info string
     */
    private String createInfo() {
        LayoutServices layoutServices = LayoutServices.getInstance();
        DiagramLayoutManager manager = layoutView.getCurrentManager();
        StringBuilder builder = new StringBuilder();
        EditPart editPart = layoutView.getCurrentEditPart();
        if (editPart != null) {
            builder.append("Edit part class:\n\t"
                    + editPart.getClass().getName() + "\n");
            ILayoutInspector inspector = manager.getInspector(editPart);
            EObject model = inspector.getFocusModel();
            if (model != null) {
                builder.append("Domain model class:\n\t"
                        + model.eClass().getInstanceTypeName() + "\n");
            }
        }
        
        LayoutProviderData[] providerData = layoutView.getCurrentProviderData();
        if (providerData != null && providerData.length > 0) {
            builder.append("Involved layout providers:\n");
            for (LayoutProviderData data : providerData) {
                if (data != null) {
                    builder.append("\t" + data.getName());
                    String category = layoutServices.getCategoryName(data.getCategory());
                    if (category != null) {
                        builder.append(" (" + category + ")");
                    }
                    builder.append(" - " + data.getId() + "\n");
                }
            }
        }
        
        List<IPropertySheetEntry> selectedOptions = layoutView.getSelection();
        if (!selectedOptions.isEmpty()) {
            builder.append("Selected options:\n");
            for (IPropertySheetEntry entry : selectedOptions) {
                final LayoutOptionData<?> optionData = KimlUiUtil.getOptionData(providerData,
                        entry.getDisplayName());
                if (optionData != null) {
                    builder.append("\t" + optionData.getName() + " (" + optionData.getType().literal()
                            + ") - " + optionData.getId() + "\n");
                }
            }
        }
        
        if (builder.length() == 0) {
            builder.append("No information available.");
        }
        return builder.toString();
    }

}
