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
package de.cau.cs.kieler.kiml.ui.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * Provider class for the options tables. Contains a label provider and a content provider.
 *
 * @kieler.rating 2010-01-26 proposed yellow msp
 * @author msp
 */
public class OptionsTableProvider extends LabelProvider implements ITableLabelProvider,
        IStructuredContentProvider {

    /** data type for row entries in the table. */
    public static class DataEntry {
        /** name of the associated diagram type or element type. */
        private String associatedTypeName;
        /** identifier of the associated diagram type or element type. */
        private String associatedTypeId;
        /** description of the type of element (diagram type / model element / edit part). */
        private String typeDesc;
        /** layout option data. */
        private LayoutOptionData<?> optionData;
        /** the current value. */
        private Object value;
        
        /**
         * Creates a data entry.
         * 
         * @param thetypeName name of the associated diagram type or element type
         * @param thetypeId identifier of the associated diagram type or element type
         * @param thetypeDesc description of the type of element
         *         (diagram type / model element / edit part)
         * @param theoptionData layout option data
         * @param thevalue the current value
         */
        public DataEntry(final String thetypeName, final String thetypeId, final String thetypeDesc,
                final LayoutOptionData<?> theoptionData, final Object thevalue) {
            this.associatedTypeName = thetypeName;
            this.associatedTypeId = thetypeId;
            this.typeDesc = thetypeDesc;
            this.optionData = theoptionData;
            this.value = thevalue;
        }

        /**
         * Returns the associated type name.
         *
         * @return the associated type name
         */
        public String getAssociatedTypeName() {
            return associatedTypeName;
        }
        
        /**
         * Returns the associated type identifier.
         * 
         * @return the associated type identifier
         */
        public String getAssociatedTypeId() {
            return associatedTypeId;
        }

        /**
         * Returns the option data.
         *
         * @return the option data
         */
        public LayoutOptionData<?> getOptionData() {
            return optionData;
        }

        /**
         * Returns the value.
         *
         * @return the value
         */
        public Object getValue() {
            return value;
        }
        
        /**
         * Sets the value.
         * 
         * @param thevalue the new value
         */
        public void setValue(final Object thevalue) {
            this.value = thevalue;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(final Object object) {
            if (object instanceof DataEntry) {
                DataEntry other = (DataEntry) object;
                return this.associatedTypeId.equals(other.associatedTypeId)
                        && this.optionData.equals(other.optionData);
            } else {
                return false;
            }
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return associatedTypeId.hashCode() + optionData.hashCode();
        }
    }
    
    /** the "Element" column. */
    private static final int COL_ELEMENT = 0;
    /** the "Type" column. */
    private static final int COL_TYPE = 1;
    /** the "Option" column. */
    private static final int COL_OPTION = 2;
    /** the "Value" column. */
    private static final int COL_VALUE = 3;
    
    /**
     * {@inheritDoc}
     */
    public Image getColumnImage(final Object element, final int columnIndex) {
        if (element instanceof DataEntry && columnIndex == COL_VALUE) {
            DataEntry entry = (DataEntry) element;
            KimlUiPlugin.Images images = KimlUiPlugin.getDefault().getImages();
            switch (entry.optionData.getType()) {
            case STRING:
                return images.getPropText();
            case BOOLEAN:
                if ((Boolean) entry.value) {
                    return images.getPropTrue();
                } else {
                    return images.getPropFalse();
                }
            case ENUM:
                return images.getPropChoice();
            case INT:
                return images.getPropInt();
            case FLOAT:
                return images.getPropFloat();
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getColumnText(final Object element, final int columnIndex) {
        if (element instanceof DataEntry) {
            DataEntry entry = (DataEntry) element;
            switch (columnIndex) {
            case COL_ELEMENT:
                return entry.associatedTypeName;
            case COL_TYPE:
                return entry.typeDesc;
            case COL_OPTION:
                return entry.optionData.getName();
            case COL_VALUE:
                if (entry.optionData.getType() == LayoutOptionData.Type.ENUM
                        && entry.value instanceof Integer) {
                    return entry.optionData.getEnumValue((Integer) entry.value).toString();
                } else {
                    return entry.value.toString();
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof List<?>) {
            @SuppressWarnings("unchecked")
            List<DataEntry> list = new ArrayList<DataEntry>((List<DataEntry>) inputElement);
            ListIterator<DataEntry> listIter = list.listIterator();
            while (listIter.hasNext()) {
                DataEntry next = listIter.next();
                if (next.value == null) {
                    listIter.remove();
                }
            }
            return list.toArray();
        } else if (inputElement instanceof Object[]) {
            return (Object[]) inputElement;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

}
