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
package de.cau.cs.kieler.kiml;

import java.util.List;

import de.cau.cs.kieler.core.properties.IPropertyHolder;

/**
 * Layout option configuration interface for default values and configuration of specific
 * diagram elements.
 * {@link IPropertyHolder#getProperty(de.cau.cs.kieler.core.properties.IProperty) getProperty()}
 * is used to get the stored or default value for a layout option, while
 * {@link IPropertyHolder#setProperty(IProperty, Object) setProperty()}
 * is used to change the stored option value.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public interface ILayoutConfig extends IPropertyHolder {
    
    /**
     * Set the focus of this layout configuration on the given element.
     * 
     * @param element a diagram element for which layout options shall be analyzed
     */
    void setFocus(Object element);
    
    /**
     * Returns true if the given option has its default value.
     * 
     * @param optionData a layout option data
     * @return whether the option has a default value
     */
    boolean isDefault(LayoutOptionData<?> optionData);
    
    /**
     * Returns a list of available layout option descriptors.
     * 
     * @return list of available layout options
     */
    List<LayoutOptionData<?>> getOptionData();
    
    /**
     * Returns the layout provider descriptor for the content of the associated element.
     * 
     * @return the layout provider data for the element's content
     */
    LayoutProviderData getContentLayouterData();

    /**
     * Returns the layout provider descriptor for the container of the associated element.
     * 
     * @return the layout provider data for the element's container
     */
    LayoutProviderData getContainerLayouterData();
    
    /**
     * Remove all stored layout options.
     */
    void clearProperties();

    /**
     * Sets the given option as default value for all elements of the associated diagram.
     * 
     * @param optionData layout option data
     * @param value new default value
     */
    void setDiagramDefault(LayoutOptionData<?> optionData, Object value);
    
    /**
     * Returns the most appropriate layout provider for the given layout hint and diagram type.
     * 
     * @param layoutHint identifier of either a layout provider or a layout type
     * @param diagramType identifier of a diagram type
     * @return the most appropriate layout provider, or {@code null}
     */
    LayoutProviderData getLayouterData(final String layoutHint, final String diagramType);

}
