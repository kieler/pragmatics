/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kivik.viewer.content.part;

import java.util.List;

import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Control;

/**
 * Represents a tab that will be placed in a {@link ModelContentMergeTabFolder}.
 * <p/>
 * Initial implementation by <a href="mailto:laurent.goubet@obeo.fr">Laurent
 * Goubet</a>, small adjustments to handle any kind of objects by Arne Schipper.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @author ars
 */
public interface IModelContentMergeViewerTab {
	/**
	 * Registers a selection listener on the tab's Control.
	 * 
	 * @param listener
	 *            Listener which to register on the Control.
	 */
	void addSelectionChangedListener(ISelectionChangedListener listener);

	/**
	 * Disposes of all resources allocated this tab.
	 */
	void dispose();

	/**
	 * Returns the Control displayed by the tab.
	 * 
	 * @return The tab's Control.
	 */
	Control getControl();

	/**
	 * Returns the tab's selection as a list of Objects.
	 * 
	 * @return The tab's selection as a list of Objects.
	 */
	List<? extends Object> getSelectedElements();

	/**
	 * This will be used when drawing the center part's marquees.
	 * 
	 * @param data
	 *            The data for which we need UI variables for.
	 * @return List of items corresponding to the given data, wrapped along with
	 *         UI information.
	 */
	ModelContentMergeTabObject getUIElement(EObject data);

	/**
	 * Returns the tab's visible elements as a list of Objects.
	 * <p>
	 * Elements are deemed visible if they are currently in the client area of
	 * the tab's Control.
	 * </p>
	 * 
	 * @return List of the tab's visible elements.
	 */
	List<ModelContentMergeTabObject> getVisibleElements();

	/**
	 * Redraws the Control displayed by the tab.
	 */
	void redraw();

	/**
	 * Sets the input of the tab.
	 * <p>
	 * This is often implemented by redirecting to the tab's "setInput(Object)"
	 * method.
	 * </p>
	 * 
	 * @param input
	 *            New input of the tab's viewer.
	 */
	void setReflectiveInput(Object input);

	/**
	 * Ensures the given List of Objects is made visible in the tab's Control
	 * client area.
	 * 
	 * @param elements
	 *            List of the Objects to make visible.
	 */
	void showElements(List<DiffElement> elements);
}
