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
package de.cau.cs.kieler.kiml.kivi;

import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * An effect that sets layout options for a specific element.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class SetOptionsEffect extends AbstractEffect {

    /** the workbench part for which options are set. */
    private IWorkbenchPart workbenchPart;
    /** map of layout option identifiers to values. */
    private Map<String, Object> optionMap;
    /** the model element for which options are set. */
    private EObject modelElement;
    
    /**
     * Creates an effect to set a single option.
     * 
     * @param part the workbench part for which options are set
     * @param element the model element for which options shall be set
     * @param optionId layout option identifier
     * @param value the new value ({@code null} to remove the option value)
     */
    public SetOptionsEffect(final IWorkbenchPart part, final EObject element,
            final String optionId, final Object value) {
        this.workbenchPart = part;
        this.optionMap = Collections.singletonMap(optionId, value);
        this.modelElement = element;
    }
    
    /**
     * Creates an effect to set multiple options.
     * 
     * @param part the workbench part for which options are set
     * @param element the model element for which options shall be set
     * @param options map of layout option identifiers to values
     */
    public SetOptionsEffect(final IWorkbenchPart part, final EObject element,
            final Map<String, Object> options) {
        this.workbenchPart = part;
        this.optionMap = options;
        this.modelElement = element;
    }
    
    /**
     * {@inheritDoc}
     */
    public void execute() {
        Object diagramPart = LayoutEffect.findDiagramPart(workbenchPart, modelElement);
        if (diagramPart != null) {
            IDiagramLayoutManager<?> manager = EclipseLayoutInfoService.getInstance().getManager(
                    workbenchPart, diagramPart);
            if (manager != null) {
                final IMutableLayoutConfig layoutConfig = (IMutableLayoutConfig) manager.getAdapter(
                        null, ILayoutConfig.class);
                if (layoutConfig != null) {
                    // build a layout context for setting the option
                    final LayoutContext context = new LayoutContext();
                    context.setProperty(LayoutContext.DOMAIN_MODEL, modelElement);
                    context.setProperty(LayoutContext.DIAGRAM_PART, diagramPart);
                    layoutConfig.enrich(context);
                    
                    // get an editing domain and execute the command
                    EditingDomain editingDomain = (EditingDomain) manager.getAdapter(diagramPart,
                            EditingDomain.class);
                    KimlUiUtil.runModelChange(new Runnable() {
                        public void run() {
                            for (Map.Entry<String, Object> entry : optionMap.entrySet()) {
                                LayoutOptionData<?> optionData = LayoutDataService.getInstance()
                                        .getOptionData(entry.getKey());
                                if (optionData != null) {
                                    layoutConfig.setValue(optionData, context, entry.getValue());
                                }
                            }
                        }
                    }, editingDomain, Messages.getString("kiml.ui.40"));
                }
            }
        }
    }

}
