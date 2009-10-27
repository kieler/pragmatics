/*
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
 */
package de.cau.cs.kieler.kiml.ui.preferences;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableItem;

import de.cau.cs.kieler.kiml.layout.services.LayoutProviderData;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * Provider class for the layouter priorities table. Includes a content provider, a
 * label provider, and a cell modifier.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class PriorityTableProvider extends LabelProvider
        implements IStructuredContentProvider, ITableLabelProvider, ICellModifier {

    /** property name of the layouters column */
    public final static String LAYOUTERS_PROPERTY = "layouters";
    
    /** path to the icon for active layouters */
    private final static String ACTIVE_IMAGE_PATH = "icons/obj16/active.gif";
    
    /** table viewer that makes use of this provider */
    private TableViewer priorityTableViewer;
    /** priority data matrix */
    private int[][] data;
    /** array of user friendly layouter names */
    private String[] layouterNames;
    /** array of indices of the layouters with maximal priority */
    private int[] maxIndices;
    /** image used for active layouters */
    private Image activeImage;

    /** data type for row entries in the table */
    public static class DataEntry {
        /** index of the layouter */
        int layouterIndex;
        /** array of priorities for the layout provider */
        int[] priorities;
    }

    /**
     * Creates a table provider instance.
     */
    public PriorityTableProvider(TableViewer tableViewer, int[][] data,
            String[] layouterNames) {
        this.priorityTableViewer = tableViewer;
        this.data = data;
        this.layouterNames = layouterNames;
        this.maxIndices = new int[data[0].length];
        this.activeImage = KimlUiPlugin.getImageDescriptor(ACTIVE_IMAGE_PATH).createImage();
        refresh();
    }
    
    /**
     * Determines the index of the layouter with highest priority for the
     * given column.
     * 
     * @param col column of the diagram type for which the highest priority shall
     *     be determined
     */
    private void calcMaxIndex(int col) {
        int max = LayoutProviderData.MIN_PRIORITY;
        int index = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i][col] > max) {
                max = data[i][col];
                index = i;
            }
        }
        maxIndices[col] = index;
    }
    
    /**
     * Refreshes internally cached data for display in the table.
     */
    public void refresh() {
        for (int j = 0; j < data[0].length; j++)
            calcMaxIndex(j);
        priorityTableViewer.refresh();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
     */
    public void dispose() {
        activeImage.dispose();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof DataEntry[])
            return (DataEntry[])inputElement;
        else
            return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
        if (element instanceof DataEntry) {
            DataEntry entry = (DataEntry)element;
            if (columnIndex > 0 && maxIndices[columnIndex - 1] == entry.layouterIndex)
                return activeImage;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     */
    public String getColumnText(Object element, int columnIndex) {
        if (element instanceof DataEntry) {
            DataEntry entry = (DataEntry)element;
            if (columnIndex == 0)
                return layouterNames[entry.layouterIndex];
            int prio = entry.priorities[columnIndex - 1];
            return prio <= LayoutProviderData.MIN_PRIORITY
                    ? null : Integer.toString(prio);
        }
        else return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element, String property) {
        return !property.equals(LAYOUTERS_PROPERTY);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
     */
    public Object getValue(Object element, String property) {
        DataEntry entry = (DataEntry)element;
        try {
            int typeIndex = Integer.parseInt(property);
            int prio = entry.priorities[typeIndex];
            return prio <= LayoutProviderData.MIN_PRIORITY
                    ? "0" : Integer.toString(prio);
        }
        catch (NumberFormatException exception) {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
     */
    public void modify(Object element, String property, Object value) {
        DataEntry entry = (DataEntry)((TableItem)element).getData();
        int typeIndex = Integer.parseInt(property);
        try {
            entry.priorities[typeIndex] = Integer.parseInt((String)value);
        }
        catch (NumberFormatException exception) {
            entry.priorities[typeIndex] = LayoutProviderData.MIN_PRIORITY;
        }
        calcMaxIndex(typeIndex);
        priorityTableViewer.refresh();
    }

}
