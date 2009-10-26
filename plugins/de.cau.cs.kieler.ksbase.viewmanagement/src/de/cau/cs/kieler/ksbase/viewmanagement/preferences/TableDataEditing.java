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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;

import de.cau.cs.kieler.ksbase.viewmanagement.combinations.KSBasECombination;

/**
 * The class TableDataEditing implements the editing support for DataTable
 * entries of the table. Specifically it provides a check box CellEditor for the
 * first (present) column and String CellEditors for the second (key) and the
 * third (value) column. <BR>
 * <BR>
 * It also sets a flag that the table is currently edited to prevent disturbing
 * updates during an ongoing editing action. Therefore it needs access to the
 * DataTableViewer.
 * 
 * @author Christian Motika - cmot AT informatik.uni-kiel.de changed Michael
 *         Matzen - mim AT informatik.uni-kiel.de
 */
public class TableDataEditing extends EditingSupport {

    /** The editor of this specific column. */
    private CellEditor editor;

    /** The column index. */
    private int columnIndex;

    /** The DataTableViewer to set the currently editing flag. */
    DataTableViewer viewer;

    // -------------------------------------------------------------------------

    /**
     * Instantiates a new table data editing.
     * 
     * @param viewer
     *            the viewer
     * @param columnIndex
     *            the column index
     */
    public TableDataEditing(DataTableViewer viewer, int columnIndex) {
	super(viewer);

	this.viewer = viewer;

	// Create the correct editor based on the column index
	switch (columnIndex) {
	case 0:
	    editor = new CheckboxCellEditor(null, SWT.CHECK);
	    break;
	case 2:
	    editor = new TextCellEditor(((DataTableViewer) viewer).getTree());
	    break;
	default:
	    break;
	}
	this.columnIndex = columnIndex;
    }

    // -------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(Object element) {
	return true;
    }

    // -------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(Object element) {
	return editor;
    }

    // -------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected Object getValue(Object element) {
	TableData tableData = (TableData) element;

	switch (this.columnIndex) {
	case 0:
	    if (tableData.isEffectActive()) {
		return true;
	    } else
		return false;

	case 1:
	    break;
	case 2:
	    if (tableData.isEffectActive()) {
		return "";
	    } else {
		return String.valueOf(tableData.getPriority());
	    }
	default:
	    break;
	}
	return null;
    }

    // -------------------------------------------------------------------------

    protected void setValue(Object element, Object value) {
	switch (this.columnIndex) {
	case 0:
	    if (value.toString().equals("true")) {
		if (element instanceof TableData) {
		    TableData data = (TableData) element;
		    KSBasECombination.addEffect(data.getEffectName(), data
			    .getPriority());
		    data.setEffectActive(true);
		}
	    } else {
		if (element instanceof TableData) {
		    TableData data = (TableData) element;
		    KSBasECombination.removeEffect(data.getEffectName(), data
			    .getPriority());
		    data.setEffectActive(false);
		}
	    }
	    break;
	case 1:
	    break;
	case 2:
	    try {
		int newPrio = Integer.valueOf((String) value);
		TableData data = (TableData) element;
		if (data.isEffectActive()) {
		    KSBasECombination.changePriority(data.getEffectName(), data
			    .getPriority(), newPrio);
		}
		data.setPriority(newPrio);
	    } catch (NumberFormatException e) {
		break;
	    } catch (ClassCastException e) {
		break;
	    }
	default:
	    break;
	}

	// updates the table
	getViewer().update(element, null);
    }

}
