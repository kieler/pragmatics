/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.SemanticLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A semantic layout configuration for the diagram type of entities.
 *
 * @author ckru
 */
public class TypeLayoutConfig extends SemanticLayoutConfig {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<LayoutOptionData<?>> getOptionData(final EObject semanticElem) {
        if (semanticElem instanceof Entity) {
            LayoutOptionData<?> optionData = LayoutDataService.getInstance()
            .getOptionData(LayoutOptions.DIAGRAM_TYPE_ID);
            List<LayoutOptionData<?>> list = new ArrayList<LayoutOptionData<?>>(1);
            list.add(optionData);
            return list;
        }
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object getSemanticProperty(final EObject semanticElem,
            final LayoutOptionData<?> layoutOption) {
        if (semanticElem instanceof Entity && layoutOption.getId()
                .equals(LayoutOptions.DIAGRAM_TYPE_ID)) {
            Entity entity = (Entity) semanticElem;
            Annotation annotation = entity.getAnnotation("DiagramType");
            if (annotation instanceof StringAnnotation) {
                StringAnnotation typeAnn = (StringAnnotation) annotation; 
                if (typeAnn.getValue().equals("StateMachine")) {
                    return "de.cau.cs.kieler.layout.diagrams.stateMachine";
                }
            }
            return "de.cau.cs.kieler.layout.diagrams.dataFlow";
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setSemanticProperty(final EObject semanticElem,
            final LayoutOptionData<?> layoutOption, final Object value) {
        // not supported by this layout configuration
    }
    
}
