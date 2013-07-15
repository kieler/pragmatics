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
package de.cau.cs.kieler.keg.diagram.custom;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.SemanticLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A semantic layout configuration that checks whether a given node is a hypernode.
 *
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class HypernodeLayoutConfig extends SemanticLayoutConfig {

    /**
     * {@inheritDoc}
     */
    @Override
    protected IProperty<?>[] getAffectedOptions(final EObject semanticElem) {
        if (semanticElem instanceof Node) {
            return new IProperty<?>[] { LayoutOptions.HYPERNODE };
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object getSemanticValue(final EObject semanticElem,
            final LayoutOptionData<?> layoutOption) {
        if (semanticElem instanceof Node && layoutOption.equals(LayoutOptions.HYPERNODE)) {
            return ((Node) semanticElem).isHypernode();
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
