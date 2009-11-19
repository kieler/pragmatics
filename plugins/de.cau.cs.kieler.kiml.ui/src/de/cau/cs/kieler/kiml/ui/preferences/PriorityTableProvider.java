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

import de.cau.cs.kieler.kiml.layout.LayoutProviderData;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * Provider class for the layouter priorities table. Includes a content provider, a
 * label provider, and a cell modifier.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class PriorityTableProvider extends LabelProvider
        implements IStructuredContentProvider, ITableLabelProvider, ICellModifier {

    /** property name of the layouters column. */
    public static final String LAYOUTERS_PROPERTY = "layouters";
    
    /** path to the icon for active layouters. */
    private static final String ACTIVE_IMAGE_PATH = "icons/obj16/active.gif";
    
    /** table viewer that makes use of this provider. */
    private TableViewer priorityTableViewer;
    /** priority data matrix. */
    private int[][] data;
    /** array of user friendly layouter names. */
    private String[] layouterNames;
    /** array of indices of the layouters with maximal priority. */
    private int[] maxIndices;
    /** image used for active layouters. */
    private Image activeImage;

    /** data type for row entries in the table. */
    public static class DataEntry {
        /** index of the layouter. */
        private int layouterIndex;
        /** array of priorities for the layout provider. */
        private int[] priorities;
        
        /**
         * Sets the layouter index.
         *
         * @param thelayouterIndex the layouter index to set
         */
        void setLayouterIndex(final int thelayouterIndex) {
            this.layouterIndex = thelayouterIndex;
        }
        
        /**
         * Returns the layouter index.
         *
         * @return the layouter index
         */
        int getLayouterIndex() {
            return layouterIndex;
        }
        
        /**
         * Sets the priorities.
         *
         * @param thepriorities the priorities to set
         */
        void setPriorities(final int[] thepriorities) {
            this.priorities = thepriorities;
        }
        
        /**
         * Returns the priorities.
         *
         * @return the priorities
         */
        int[] getPriorities() {
            return priorities;
        }
    }

    /**
     * Creates a table provider instance.
     * 
     * @param thetableViewer table viewer that makes use of this provider
     * @param thedata priority data matrix
     * @param thelayouterNames array of user friendly layouter names
     */
    public PriorityTableProvider(final TableViewer thetableViewer, final int[][] thedata,
            final String[] thelayouterNames) {
        this.priorityTableViewer = thetableViewer;
        this.data = thedata;
        this.layouterNames = thelayouterNames;
        this.maxIndices = new int[thedata[0].length];
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
    private void calcMaxIndex(final int col) {
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
        for (int j = 0; j < data[0].length; j++) {
            calcMaxIndex(j);
        }
        priorityTableViewer.refresh();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        activeImage.dispose();
    }
    
    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof DataEntry[]) {
            return (DataEntry[])inputElement;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

    /**
     * {@inheritDoc}
     */
    public Image getColumnImage(final Object element, final int columnIndex) {
        if (element instanceof DataEntry) {
            DataEntry entry = (DataEntry)element;
            if (columnIndex > 0 && maxIndices[columnIndex - 1] == entry.getLayouterIndex()) {
                return activeImage;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getColumnText(final Object element, final int columnIndex) {
        if (element instanceof DataEntry) {
            DataEntry entry = (DataEntry)element;
            if (columnIndex == 0) {
                return layouterNames[entry.getLayouterIndex()];
            }
            int prio = entry.getPriorities()[columnIndex - 1];
            return prio <= LayoutProviderData.MIN_PRIORITY
                    ? null : Integer.toString(prio);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean canModify(final Object element, final String property) {
        return !property.equals(LAYOUTERS_PROPERTY);
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final Object element, final String property) {
        DataEntry entry = (DataEntry)element;
        try {
            int typeIndex = Integer.parseInt(property);
            int prio = entry.getPriorities()[typeIndex];
            return prio <= LayoutProviderData.MIN_PRIORITY
                    ? "0" : Integer.toString(prio);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void modify(final Object element, final String property, final Object value) {
        DataEntry entry = (DataEntry)((TableItem)element).getData();
        int typeIndex = Integer.parseInt(property);
        try {
            entry.getPriorities()[typeIndex] = Integer.parseInt((String)value);
        } catch (NumberFormatException exception) {
            entry.getPriorities()[typeIndex] = LayoutProviderData.MIN_PRIORITY;
        }
        calcMaxIndex(typeIndex);
        priorityTableViewer.refresh();
    }

}
