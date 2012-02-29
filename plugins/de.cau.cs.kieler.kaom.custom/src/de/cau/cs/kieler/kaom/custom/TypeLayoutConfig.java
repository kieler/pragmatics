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

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.SemanticLayoutConfig;
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
    protected IProperty<?>[] getAffectedOptions(final EObject semanticElem) {
        if (semanticElem instanceof Entity) {
            return new IProperty<?>[] { LayoutOptions.DIAGRAM_TYPE };
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object getSemanticValue(final EObject semanticElem,
            final LayoutOptionData<?> layoutOption) {
        if (semanticElem instanceof Entity && layoutOption.getId()
                .equals(LayoutOptions.DIAGRAM_TYPE.getId())) {
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
    protected void setSemanticValue(final EObject semanticElem,
            final LayoutOptionData<?> layoutOption, final Object value) {
        // not supported by this layout configuration
    }
    
}
