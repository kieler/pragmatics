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
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.services.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.services.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.services.LayoutServices;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * A property descriptor for layout options.
 *
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
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
            switch (optionData.type) {
            case STRING:
                return images.propText;
            case BOOLEAN:
                if (((Boolean)element).booleanValue()) {
                    return images.propTrue;
                } else {
                    return images.propFalse;
                }
            case ENUM:
                return images.propChoice;
            case INT:
                return images.propInt;
            case FLOAT:
                return images.propFloat;
            default:
                return null;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getText(final Object element) {
            switch (optionData.type) {
            case STRING:
                LayoutServices layoutServices = LayoutServices.getInstance();
                if (LayoutOptions.LAYOUT_HINT.equals(optionData.id)) {
                    String layoutHint = layoutHintValues[((Integer)element).intValue()];
                    String layoutType = layoutServices.getLayoutTypeName(layoutHint);
                    if (layoutType != null) {
                        return layoutType + " " + Messages.getString("kiml.ui.9");
                    }
                    LayoutProviderData providerData = layoutServices.getLayoutProviderData(layoutHint);
                    if (providerData != null) {
                        String category = layoutServices.getCategoryName(providerData.category);
                        if (category == null) {
                            return providerData.name;
                        } else {
                            return providerData.name + " (" + category + ")";
                        }
                    }
                    return layoutHint;
                } else {
                    return (String)element;
                }
            case ENUM:
                return optionData.getEnumValue(((Integer)element).intValue()).toString();
            default:
                return element.toString();
            }
        }
        
    }
    
    /** the layout option data associated with this property descriptor. */
    private LayoutOptionData optionData;
    /** array of choices for the layout hint option. */
    private String[] layoutHintChoices;
    /** array of identifiers for the layout hint option. */
    private String[] layoutHintValues;
    /** the label provider for this property descriptor. */
    private LayoutOptionLabelProvider labelProvider;
    
    /**
     * Creates a layout property descriptor based on a specific layout option.
     * 
     * @param theoptionData the layout option data
     * @param thelayoutHintChoices the array of choices for the layout hint option
     * @param thelayoutHintValues the array of identifiers for the layout hint option
     */
    public LayoutPropertyDescriptor(final LayoutOptionData theoptionData,
            final String[] thelayoutHintChoices, final String[] thelayoutHintValues) {
        this.optionData = theoptionData;
        this.layoutHintChoices = thelayoutHintChoices;
        this.layoutHintValues = thelayoutHintValues;
    }
    
    /**
     * {@inheritDoc}
     */
    public CellEditor createPropertyEditor(final Composite parent) {
        switch (optionData.type) {
        case STRING:
            if (LayoutOptions.LAYOUT_HINT.equals(optionData.id)) {
                return new ComboBoxCellEditor(parent, layoutHintChoices, SWT.READ_ONLY);
            } else {
                return new TextCellEditor(parent);
            }
        case BOOLEAN:
            return new CheckboxCellEditor(parent);
        case INT:
            CellEditor intEditor = new TextCellEditor(parent);
            intEditor.setValidator(new ICellEditorValidator() {
                public String isValid(final Object value) {
                    try {
                        Integer.parseInt((String)value);
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
                        Float.parseFloat((String)value);
                        return null;
                    } catch (NumberFormatException exception) {
                        return Messages.getString("kiml.ui.7");
                    }
                }
            });
            return floatEditor;
        case ENUM:
            return new ComboBoxCellEditor(parent, optionData.getChoices(), SWT.READ_ONLY);
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
        return optionData.description;
    }

    /**
     * {@inheritDoc}
     */
    public String getDisplayName() {
        return optionData.name;
    }

    /**
     * {@inheritDoc}
     */
    public String[] getFilterFlags() {
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
        return optionData.id;
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
                && this.optionData.id.equals(anotherProperty.getId());
    }

}
