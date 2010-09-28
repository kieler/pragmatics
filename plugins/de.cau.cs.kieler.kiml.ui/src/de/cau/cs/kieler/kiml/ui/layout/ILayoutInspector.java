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

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutProviderData;

/**
 * Interface for edit part inspectors for handling of layout options. A layout inspector is always
 * associated with a specific edit part instance.
 * 
 * @author msp
 */
public interface ILayoutInspector {

    /**
     * Initialize available layout options for the associated edit part. Some methods require this
     * to be called before they are used.
     */
    void initOptions();

    /**
     * Returns a list of layout option descriptors that are available for the associated edit part.
     * {@link #initOptions()} must be called before this method is used.
     * 
     * @return list of available layout options
     */
    List<LayoutOptionData<?>> getOptionData();

    /**
     * Returns the currently stored option value for the given layout option.
     * {@link #initOptions()} must be called before this method is used.
     * 
     * @param optionData layout option data
     * @return the current value, or {@code null} if there is no such value
     */
    Object getOption(LayoutOptionData<?> optionData);
    
    /**
     * Sets the given value for a layout option. {@link #initOptions()} must be
     * called before this method is used. Giving {@code null} as option value
     * is equivalent to calling {@link #removeOption(LayoutOptionData)}.
     * 
     * @param optionData layout option data
     * @param value the new value for the layout option
     */
    void setOption(LayoutOptionData<?> optionData, Object value);

    /**
     * Removes any stored value for the given layout option. {@link #initOptions()} must be called
     * before this method is used.
     * 
     * @param optionData layout option data
     */
    void removeOption(LayoutOptionData<?> optionData);

    /**
     * Returns the layout provider descriptor for the associated edit part. {@link #initOptions()}
     * must be called before this method is used.
     * 
     * @return the associated layout provider data
     */
    LayoutProviderData getFocusLayouterData();

    /**
     * Returns the edit part that contains the associated edit part. {@link #initOptions()}
     * must be called before this method is used.
     * 
     * @return the container edit part
     */
    EditPart getContainerPart();

    /**
     * Returns the layout provider descriptor for the container of the associated edit
     * part. {@link #initOptions()} must be called before this method is used.
     * 
     * @return the layout provider data for the container edit part
     */
    LayoutProviderData getContainerLayouterData();
    
    /**
     * Returns {@code true} if the associated edit part has children that can be layouted.
     * 
     * @return whether the edit part has children
     */
    boolean hasChildren();

    /**
     * Removes all stored layout options from the associated edit part and its children.
     */
    void removeAllOptions();

    /**
     * Returns the default value for the associated diagram.
     * 
     * @param optionData layout option data
     * @return default value of the layout option, or {@code null} if no default value is set for
     *         the diagram
     */
    Object getDefault(LayoutOptionData<?> optionData);

    /**
     * Sets the given option as default value for all elements of the associated diagram.
     * 
     * @param optionData layout option data
     * @param value new default value
     */
    void setDefault(LayoutOptionData<?> optionData, Object value);

    /**
     * Returns the edit part that is associated with this layout inspector.
     * 
     * @return the associated edit part
     */
    EditPart getFocusPart();

    /**
     * Returns the domain model element that is associated with this inspector.
     * 
     * @return the associated domain model element
     */
    EObject getFocusModel();

    /**
     * Returns a transactional editing domain in which to perform model changes.
     * 
     * @return an editing domain for model changes
     */
    TransactionalEditingDomain getEditingDomain();

    /**
     * Returns a layer figure for the associated diagram that can be used to draw
     * additional information.
     * 
     * @return a drawing layer
     */
    IFigure getDrawingLayer();
    
    /**
     * Returns a zoom manager for the associated diagram.
     * 
     * @return a zoom manager
     */
    ZoomManager getZoomManager();

}
