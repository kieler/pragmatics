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

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySheetEntry;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.LayoutManagersService;
import de.cau.cs.kieler.kiml.service.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * An action that applies the selected layout option as default for the whole diagram.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class DiagramDefaultAction extends Action {
    
    /** identifier of the diagram default action. */
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
    @Override
    public void run() {
        IWorkbenchPart workbenchPart = layoutView.getCurrentWorkbenchPart();
        IDiagramLayoutManager<?> manager = LayoutManagersService.getInstance()
                .getManager(workbenchPart, null);
        if (manager != null) {
            Object diagramPart = null;
            if (manager.getAdapterList().length > 0) {
                diagramPart = manager.getAdapter(workbenchPart, manager.getAdapterList()[0]);
            }
            if (diagramPart == null) {
                diagramPart = layoutView.getCurrentDiagramPart();
            }
            IMutableLayoutConfig layoutConfig = (IMutableLayoutConfig) manager.getAdapter(
                    workbenchPart, IMutableLayoutConfig.class);
            EditingDomain editingDomain = (EditingDomain) manager.getAdapter(workbenchPart,
                    EditingDomain.class);
            if (diagramPart != null && layoutConfig != null) {
                // build a layout context for setting the option
                final LayoutContext context = new LayoutContext();
                context.setProperty(LayoutContext.DIAGRAM_PART, diagramPart);
                context.setProperty(LayoutContext.GLOBAL, true);
                DiagramLayoutEngine.INSTANCE.getOptionManager().enrich(context, layoutConfig, false);
                
                for (IPropertySheetEntry entry : layoutView.getSelection()) {
                    applyOption(editingDomain, layoutConfig, context, entry);
                }
            }
        }
    }
    
    /**
     * Sets the layout option of the given property sheet entry as default for the whole
     * diagram.
     * 
     * @param editingDomain the editing domain, or {@code null}
     * @param config a layout configuration
     * @param context a layout context
     * @param entry a property sheet entry
     */
    private void applyOption(final EditingDomain editingDomain,
            final IMutableLayoutConfig config, final LayoutContext context,
            final IPropertySheetEntry entry) {
        final LayoutOptionData optionData = KimlUiUtil.getOptionData(
                layoutView.getCurrentLayouterData(), entry.getDisplayName());
        if (optionData == null) {
            return;
        }

        final Object value;
        if (optionData.equals(LayoutOptions.ALGORITHM)) {
            value = LayoutPropertySource.getLayoutHint((String) entry.getValueAsString());
        } else {
            value = optionData.parseValue(entry.getValueAsString());
        }
        if (value != null) {
            Runnable modelChange = new Runnable() {
                public void run() {
                    config.setOptionValue(optionData, context, value);
                }
            };
            KimlUiUtil.runModelChange(modelChange, editingDomain, Messages.getString("kiml.ui.13"));
        }
    }

}
