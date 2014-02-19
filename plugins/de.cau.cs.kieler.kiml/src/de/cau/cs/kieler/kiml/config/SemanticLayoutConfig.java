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
package de.cau.cs.kieler.kiml.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutOptionData;

/**
 * An abstract layout configuration that is able to consider semantic model properties.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating yellow 2013-07-01 review KI-38 by cds, uru
 */
public abstract class SemanticLayoutConfig extends AbstractMutableLayoutConfig {
    
    /** the default priority for semantic layout configurations. */
    public static final int PRIORITY = 5;
    
    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }
    
    /**
     * Returns the options that are affected by this layout configuration.
     * 
     * @param semanticElem a semantic model element
     * @return the affected options, or {@code null} if there are none
     */
    protected abstract IProperty<?>[] getAffectedOptions(Object semanticElem);
    
    /**
     * Determine the value of the given layout option from the semantic element.
     * 
     * @param semanticElem a semantic model element
     * @param layoutOption a layout option
     * @return the corresponding value, or {@code null} if no specific value is determined
     */
    protected abstract Object getSemanticValue(Object semanticElem,
            LayoutOptionData layoutOption);
    
    /**
     * Set a layout option value for the semantic element. This feature is optional, so
     * subclasses may leave the implementation empty.
     * 
     * @param semanticElem a semantic model element
     * @param layoutOption a layout option
     * @param value a value for the layout option, or {@code null} if the currently set
     *     value shall be deleted
     */
    protected abstract void setSemanticValue(Object semanticElem,
            LayoutOptionData layoutOption, Object value);

    /**
     * {@inheritDoc}
     */
    public final Object getOptionValue(final LayoutOptionData optionData, final LayoutContext context) {
        Object element = context.getProperty(LayoutContext.DOMAIN_MODEL);
        if (element != null) {
            return getSemanticValue(element, optionData);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public final void setOptionValue(final LayoutOptionData optionData, final LayoutContext context,
            final Object value) {
        Object element = context.getProperty(LayoutContext.DOMAIN_MODEL);
        if (element != null) {
            setSemanticValue(element, optionData, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final Collection<IProperty<?>> getAffectedOptions(final LayoutContext context) {
        Object element = context.getProperty(LayoutContext.DOMAIN_MODEL);
        if (element != null) {
            IProperty<?>[] affectedOptions = getAffectedOptions(element);
            if (affectedOptions != null) {
                return Arrays.asList(affectedOptions);
            }
        }
        return Collections.emptyList();
    }

}
