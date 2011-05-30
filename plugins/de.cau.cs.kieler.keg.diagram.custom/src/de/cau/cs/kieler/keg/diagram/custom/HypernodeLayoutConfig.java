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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.SemanticLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A semantic layout configuration that checks whether a given node is a hypernode.
 *
 * @author msp
 */
public class HypernodeLayoutConfig extends SemanticLayoutConfig {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<LayoutOptionData<?>> getOptionData(final EObject semanticElem) {
        if (semanticElem instanceof Node) {
            List<LayoutOptionData<?>> data = new ArrayList<LayoutOptionData<?>>(1);
            data.add(LayoutDataService.getInstance().getOptionData(LayoutOptions.HYPERNODE_ID));
            return data;
        }
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object getSemanticProperty(final EObject semanticElem,
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
    protected void setSemanticProperty(final EObject semanticElem,
            final LayoutOptionData<?> layoutOption, final Object value) {
        // not supported by this layout configuration
    }

}
