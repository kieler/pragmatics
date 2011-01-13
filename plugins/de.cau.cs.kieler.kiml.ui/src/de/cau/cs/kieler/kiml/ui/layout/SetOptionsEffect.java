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
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.model.util.ModelingUtil;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * An effect that sets layout options for a specific element.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public class SetOptionsEffect extends AbstractEffect {

    /** map of layout option identifiers to values. */
    private Map<String, Object> optionMap;
    /** the model element for which options are set. */
    private EObject modelElement;
    
    /**
     * Creates an effect to set a single option.
     * 
     * @param element the model element for which options shall be set
     * @param optionId layout option identifier
     * @param value the new value ({@code null} to remove the option value)
     */
    public SetOptionsEffect(final EObject element, final String optionId, final Object value) {
        optionMap = Collections.singletonMap(optionId, value);
        this.modelElement = element;
    }
    
    /**
     * Creates an effect to set multiple options.
     * 
     * @param element the model element for which options shall be set
     * @param options map of layout option identifiers to values
     */
    public SetOptionsEffect(final EObject element, final Map<String, Object> options) {
        this.optionMap = options;
        this.modelElement = element;
    }
    
    /**
     * {@inheritDoc}
     */
    public void execute() {
        final Maybe<EditPart> editPart = new Maybe<EditPart>();
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                editPart.set(ModelingUtil.getEditPart(modelElement));
            }
        });
        final EclipseLayoutServices layoutServices = EclipseLayoutServices.getInstance();
        DiagramLayoutManager manager = layoutServices.getManager(null, editPart.get());
        if (manager != null) {
            final ILayoutConfig layoutConfig = manager.getLayoutConfig(editPart.get());
            if (layoutConfig != null) {
                EditingDomain editingDomain = manager.getBridge().getEditingDomain(editPart.get());
                KimlUiUtil.runModelChange(new Runnable() {
                    public void run() {
                        for (Map.Entry<String, Object> entry : optionMap.entrySet()) {
                            LayoutOptionData<?> optionData = layoutServices
                                    .getLayoutOptionData(entry.getKey());
                            layoutConfig.setProperty(optionData, entry.getValue());
                        }
                    }
                } , (TransactionalEditingDomain) editingDomain, Messages.getString("kiml.ui.40"));
            }
        }
    }

}
