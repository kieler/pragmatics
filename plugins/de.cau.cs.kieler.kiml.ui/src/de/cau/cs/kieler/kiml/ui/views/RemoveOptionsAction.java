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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.service.LayoutManagersService;
import de.cau.cs.kieler.kiml.service.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * An action that removes all layout options from the current diagram.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class RemoveOptionsAction extends Action {
    
    /** the layout view that created this action. */
    private LayoutViewPart layoutView;

    /**
     * Creates an apply option action.
     * 
     * @param thelayoutView the layout view that created this action
     * @param text user friendly text
     */
    public RemoveOptionsAction(final LayoutViewPart thelayoutView, final String text) {
        super(text);
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
            IMutableLayoutConfig layoutConfig = manager.getDiagramConfig();
            if (layoutConfig != null) {
                // build a layout context for setting the option
                LayoutContext context = new LayoutContext();
                context.setProperty(EclipseLayoutConfig.WORKBENCH_PART, workbenchPart);
                
                Object diagramPart = layoutConfig.getContextValue(LayoutContext.DIAGRAM_PART, context);
                if (diagramPart == null) {
                    diagramPart = layoutView.getCurrentDiagramPart();
                }
                if (diagramPart != null) {
                    context.setProperty(LayoutContext.DIAGRAM_PART, diagramPart);
                    context.setProperty(LayoutContext.GLOBAL, true);
                    DiagramLayoutEngine.INSTANCE.getOptionManager().enrich(context, layoutConfig,
                            false);

                    EditingDomain editingDomain = (EditingDomain) layoutConfig.getContextValue(
                            EclipseLayoutConfig.EDITING_DOMAIN, context);
                    removeOptions(workbenchPart.getTitle(), layoutConfig, context, editingDomain);
                }
            }
        }
    }
    
    /**
     * Remove all layout options from the given context.
     * 
     * @param diagramName name of the open diagram
     * @param layoutConfig a layout configuration for options removal
     * @param context a layout context
     * @param editingDomain an editing domain to apply the changes, or {@code null}
     */
    private void removeOptions(final String diagramName, final IMutableLayoutConfig layoutConfig,
            final LayoutContext context, final EditingDomain editingDomain) {
        // show a dialog to confirm the removal of all layout options
        boolean userResponse = MessageDialog.openQuestion(layoutView.getSite().getShell(),
                Messages.getString("kiml.ui.31"), Messages.getString("kiml.ui.32")
                + " " + diagramName + "?");
        if (userResponse) {
            Runnable runnable = new Runnable() {
                public void run() {
                    layoutConfig.clearOptionValues(context);
                }
            };
            KimlUiUtil.runModelChange(runnable, editingDomain, Messages.getString("kiml.ui.30"));
            
            // refresh the layout view after these changes
            LayoutViewPart viewPart = LayoutViewPart.findView();
            if (viewPart != null) {
                viewPart.refresh();
            }
        }
    }
    
}
