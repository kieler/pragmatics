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
package de.cau.cs.kieler.kiml.ui.views;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetEntry;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * A property descriptor for layout options.
 *
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayoutPropertyDescriptor implements IPropertyDescriptor {

    /** label provider used for layout options. */
    private class LayoutOptionLabelProvider extends LabelProvider {

        /**
         * {@inheritDoc}
         */
        @Override
        public Image getImage(final Object element) {
            KimlUiPlugin.Images images = KimlUiPlugin.getDefault().getImages();
            switch (optionData.getType()) {
            case OBJECT:
            case STRING:
                return images.getPropText();
            case BOOLEAN:
                if ((Integer) element == 1) {
                    return images.getPropTrue();
                } else {
                    return images.getPropFalse();
                }
            case REMOTE_ENUM:
            case ENUM:
                return images.getPropChoice();
            case INT:
                return images.getPropInt();
            case FLOAT:
                return images.getPropFloat();
            default:
                return null;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getText(final Object element) {
            switch (optionData.getType()) {
            case STRING:
                LayoutDataService layoutServices = LayoutDataService.getInstance();
                if (LayoutOptions.ALGORITHM_ID.equals(optionData.getId())) {
                    String layoutHint = (String) element;
                    LayoutTypeData layoutType = layoutServices.getTypeData(layoutHint);
                    if (layoutType != null) {
                        return layoutType.toString();
                    }
                    LayoutAlgorithmData layouterData = layoutServices.getAlgorithmData(layoutHint);
                    if (layouterData != null) {
                        return layouterData.toString();
                    }
                    return Messages.getString("kiml.ui.8");
                } else {
                    return (String) element;
                }
            case BOOLEAN:
            case REMOTE_ENUM:
                return optionData.getChoices()[(Integer) element];
            case ENUM:
                return optionData.getChoices()[(Integer) element];
            default:
                return element.toString();
            }
        }
        
    }
    
    /** the layout option data associated with this property descriptor. */
    private LayoutOptionData<?> optionData;
    /** the label provider for this property descriptor. */
    private LayoutOptionLabelProvider labelProvider;
    
    /**
     * Creates a layout property descriptor based on a specific layout option.
     * 
     * @param theoptionData the layout option data
     */
    public LayoutPropertyDescriptor(final LayoutOptionData<?> theoptionData) {
        this.optionData = theoptionData;
    }
    
    /**
     * {@inheritDoc}
     */
    public CellEditor createPropertyEditor(final Composite parent) {
        switch (optionData.getType()) {
        case STRING:
            if (LayoutOptions.ALGORITHM_ID.equals(optionData.getId())) {
                return new LayouterHintCellEditor(parent);
            } else {
                return new TextCellEditor(parent);
            }
        case INT:
            CellEditor intEditor = new TextCellEditor(parent);
            intEditor.setValidator(new ICellEditorValidator() {
                public String isValid(final Object value) {
                    try {
                        Integer.parseInt((String) value);
                        return null;
                    } catch (NumberFormatException exception) {
                        return Messages.getString("kiml.ui.6");
                    }
                }
            });
            return intEditor;
        case FLOAT:
            CellEditor floatEditor = new TextCellEditor(parent);
            floatEditor.setValidator(new ICellEditorValidator() {
                public String isValid(final Object value) {
                    try {
                        Float.parseFloat((String) value);
                        return null;
                    } catch (NumberFormatException exception) {
                        return Messages.getString("kiml.ui.7");
                    }
                }
            });
            return floatEditor;
        case BOOLEAN:
        case REMOTE_ENUM:
        case ENUM:
            return new ComboBoxCellEditor(parent, optionData.getChoices(), SWT.READ_ONLY);
        case OBJECT:
            return new TextCellEditor(parent);
        default:
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getCategory() {
        return optionData.getTargetsDescription();
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return optionData.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    public String getDisplayName() {
        return optionData.getName();
    }

    /**
     * {@inheritDoc}
     */
    public String[] getFilterFlags() {
        if (optionData.isAdvanced()) {
            return new String[] { IPropertySheetEntry.FILTER_ID_EXPERT };
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Object getHelpContextIds() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Object getId() {
        return optionData.getId();
    }

    /**
     * {@inheritDoc}
     */
    public ILabelProvider getLabelProvider() {
        if (labelProvider == null) {
            labelProvider = new LayoutOptionLabelProvider();
        }
        return labelProvider;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isCompatibleWith(final IPropertyDescriptor anotherProperty) {
        return anotherProperty instanceof LayoutPropertyDescriptor
                && this.optionData.getId().equals(anotherProperty.getId());
    }

}
