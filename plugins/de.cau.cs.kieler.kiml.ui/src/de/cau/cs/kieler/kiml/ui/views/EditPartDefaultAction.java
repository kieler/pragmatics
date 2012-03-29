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
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetEntry;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * An action that sets the selected layout option as default for all
 * instances of an edit part.
 *
 * @kieler.rating 2010-01-26 proposed yellow msp
 * @author msp
 */
public class EditPartDefaultAction extends Action {

    /** an identifier for the action without the domain model option. */
    public static final String EDIT_PART_ACTION_ID = "kieler.edit.part.default";
    /** an identifier for the action with the domain model option. */
    public static final String MODEL_ACTION_ID = "kieler.model.default";

    /** the icon used for this action. */
    private static ImageDescriptor icon = KimlUiPlugin.getImageDescriptor(
            "icons/menu16/apply2editPart.gif");

    /** the layout view that created this action. */
    private LayoutViewPart layoutView;
    /** indicates whether options are set for the domain element class. */
    private boolean forDomainModel;
    
    /**
     * Creates an edit part default action.
     * 
     * @param thelayoutView the layout view that created this action
     * @param text user friendly text
     * @param isforDomainModel if true, the action sets layout options for the domain
     *     element class, else for the edit part class
     */
    public EditPartDefaultAction(final LayoutViewPart thelayoutView, final String text,
            final boolean isforDomainModel) {
        super(text, icon);
        this.layoutView = thelayoutView;
        this.forDomainModel = isforDomainModel;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        Object diagramPart = layoutView.getCurrentEditPart();
        if (diagramPart instanceof EditPart) {
            for (IPropertySheetEntry entry : layoutView.getSelection()) {
                setDefault((EditPart) diagramPart, entry);
            }
        }
    }
    
    /**
     * Sets the layout option of the given property sheet entry as default for
     * the given edit part.
     * 
     * @param editPart an edit part
     * @param entry a property sheet entry
     */
    private void setDefault(final EditPart editPart, final IPropertySheetEntry entry) {
        LayoutOptionData<?> optionData = KimlUiUtil.getOptionData(
                layoutView.getCurrentLayouterData(), entry.getDisplayName());

        if (optionData != null) {
            String valueString = entry.getValueAsString();
            if (optionData.equals(LayoutOptions.ALGORITHM)) {
                valueString = LayoutPropertySource.getLayoutHint(valueString);
            }
            EclipseLayoutInfoService.getInstance().storeOption(editPart, optionData,
                    valueString, forDomainModel);
        }
    }
    
}
