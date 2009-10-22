/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 ******************************************************************************/

package de.cau.cs.kieler.ksbase.viewmanagement.preferences;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The class TableDataLabelProvider provides the labels and images
 * for the content of an TableData entry within the table ViewPart.
 * 
 * @author Christian Motika - cmot AT informatik.uni-kiel.de
 * 
 * @changed Michael Matzen - mim AT informatik.uni-kiel.de
 */
public class TableDataLabelProvider implements ITableLabelProvider {

	// define icons
	/** The Constant CHECKED. */
	private static final Image CHECKED = AbstractUIPlugin
			.imageDescriptorFromPlugin("de.cau.cs.kieler.ksbase.viewmanagement",
					"icons/checked.png").createImage();
	
	/** The Constant UNCHECKED. */
	private static final Image UNCHECKED = AbstractUIPlugin
			.imageDescriptorFromPlugin("de.cau.cs.kieler.ksbase.viewmanagement",
					"icons/unchecked.png").createImage();

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		TableData tableData = (TableData) element;
		if (columnIndex == 0) {
				if (tableData.isEffectActive())
					return CHECKED;
		
				else 
					return UNCHECKED;
		}
		return null;
	}

	//-------------------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		TableData tableData = (TableData) element;
		switch (columnIndex) {
		case 0 :  // NOT_VISIBLE_COLUMN
			return ""; 
		case 1 :  // PRESENT_COLUMN
			return tableData.getEffectName();
		case 2 : 
			if (tableData.isEffectActive()) {
				return String.valueOf(tableData.getPriority());
			}
			else {
				return "";
			}
		default :
			throw new RuntimeException("columnIndex out of bounds (3)");
		}
	}

	//-------------------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		//noop
	}
	
	//-------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		//noop
	}

	//-------------------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	//-------------------------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		//noop
	}

}
