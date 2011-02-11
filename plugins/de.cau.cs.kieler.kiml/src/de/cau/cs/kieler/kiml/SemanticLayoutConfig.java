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
package de.cau.cs.kieler.kiml;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;

/**
 * An abstract layout configuration that is able to consider semantic model properties.
 *
 * @author msp
 */
public abstract class SemanticLayoutConfig implements ILayoutConfig {

    /** the semantic model element in focus. */
    private EObject element;
    
    /**
     * Returns a list of options that are affected by this layout configuration.
     * 
     * @param semanticElem a semantic model element
     * @return the affected options
     */
    public abstract List<LayoutOptionData<?>> getOptionData(EObject semanticElem);
    
    /**
     * Determine the value of the given layout option from the semantic element.
     * 
     * @param semanticElem a semantic model element
     * @param layoutOption a layout option
     * @return the corresponding value, or {@code null} if no specific value is determined
     */
    protected abstract Object getSemanticProperty(EObject semanticElem,
            LayoutOptionData<?> layoutOption);
    
    /**
     * Set a layout option value for the semantic element. This feature is optional, so
     * subclasses may leave the implementation empty.
     * 
     * @param semanticElem a semantic model element
     * @param layoutOption a layout option
     * @param value a value for the layout option, or {@code null} if the currently set
     *     value shall be deleted
     */
    protected abstract void setSemanticProperty(EObject semanticElem,
            LayoutOptionData<?> layoutOption, Object value);
    
    /**
     * {@inheritDoc}
     */
    public final List<LayoutOptionData<?>> getOptionData() {
        if (element != null) {
            return getOptionData(element);
        }
        return Collections.emptyList();
    }


    /**
     * {@inheritDoc}
     */
    public Map<IProperty<?>, Object> getAllProperties() {
        Map<IProperty<?>, Object> properties = new HashMap<IProperty<?>, Object>();
        if (element != null) {
            for (LayoutOptionData<?> option : getOptionData(element)) {
                Object value = getSemanticProperty(element, option);
                if (value != null) {
                    properties.put(option, value);
                }
            }
        }
        return properties;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public final <T> T getProperty(final IProperty<T> property) {
        if (property instanceof LayoutOptionData<?> && element != null) {
            return (T) getSemanticProperty(element, (LayoutOptionData<?>) property);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public final void setProperty(final IProperty<?> property, final Object value) {
        if (property instanceof LayoutOptionData<?> && element != null) {
            setSemanticProperty(element, (LayoutOptionData<?>) property, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void setFocus(final Object obj) {
        if (obj instanceof EObject) {
            this.element = (EObject) obj;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDefault(final LayoutOptionData<?> optionData) {
        return getProperty(optionData) == null;
    }

    /**
     * {@inheritDoc}
     */
    public void clearProperties() {
        if (element != null) {
            for (LayoutOptionData<?> option : getOptionData(element)) {
                setSemanticProperty(element, option, null);
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void copyProperties(final IPropertyHolder holder) {
        for (Map.Entry<IProperty<?>, Object> entry : holder.getAllProperties().entrySet()) {
            setProperty(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Returns {@code null}, since this layout configuration is not connected to a layout algorithm.
     * 
     * @return {@code null}
     */
    public LayoutAlgorithmData getContentLayouterData() {
        return null;
    }

    /**
     * Returns {@code null}, since this layout configuration is not connected to a layout algorithm.
     * 
     * @return {@code null}
     */
    public LayoutAlgorithmData getContainerLayouterData() {
        return null;
    }

    /**
     * Throws an {@link UnsupportedOperationException}, since this configuration cannot be
     * used to change diagram options.
     * 
     * @param optionData a layout option
     * @param value an option value
     */
    public void setDiagramDefault(final LayoutOptionData<?> optionData, final Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws an {@link UnsupportedOperationException}, since this configuration cannot be
     * used to handle layout providers.
     * 
     * @param layoutHint a layout hint
     * @param diagramType a diagram type
     * @return nothing
     */
    public LayoutAlgorithmData getLayouterData(final String layoutHint, final String diagramType) {
        throw new UnsupportedOperationException();
    }

}
