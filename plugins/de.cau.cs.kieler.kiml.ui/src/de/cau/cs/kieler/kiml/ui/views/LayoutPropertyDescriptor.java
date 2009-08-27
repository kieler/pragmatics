/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import de.cau.cs.kieler.kiml.layout.services.LayoutOptionData;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * A property descriptor for layout options.
 *
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LayoutPropertyDescriptor implements IPropertyDescriptor {

    /** label provider used for layout options */
    private class LayoutOptionLabelProvider extends LabelProvider {

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
         */
        public Image getImage(Object element) {
            switch (optionData.type) {
            case STRING:
                return KimlUiPlugin.images.propText;
            case BOOLEAN:
                if (((Boolean)element).booleanValue())
                    return KimlUiPlugin.images.propTrue;
                else
                    return KimlUiPlugin.images.propFalse;
            case ENUM:
                return KimlUiPlugin.images.propChoice;
            case INT:
                return KimlUiPlugin.images.propInt;
            case FLOAT:
                return KimlUiPlugin.images.propFloat;
            default:
                return null;
            }
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
         */
        public String getText(Object element) {
            switch (optionData.type) {
            case ENUM:
                return optionData.getEnumValue(((Integer)element).intValue()).toString();
            default:
                return element.toString();
            }
        }
        
    }
    
    /** the layout option data associated with this property descriptor */
    private LayoutOptionData optionData;
    
    /**
     * Creates a layout property descriptor based on a specific layout option.
     * 
     * @param optionData the layout option data
     */
    public LayoutPropertyDescriptor(LayoutOptionData optionData) {
        this.optionData = optionData;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
     */
    public CellEditor createPropertyEditor(Composite parent) {
        switch (optionData.type) {
        case STRING:
            return new TextCellEditor(parent);
        case BOOLEAN:
            return new CheckboxCellEditor(parent);
        case INT:
            CellEditor intEditor = new TextCellEditor(parent);
            intEditor.setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    try {
                        Integer.parseInt((String)value);
                        return null;
                    }
                    catch (NumberFormatException exception) {
                        return Messages.getString("kiml.ui.6");
                    }
                }
            });
            return intEditor;
        case FLOAT:
            CellEditor floatEditor = new TextCellEditor(parent);
            floatEditor.setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    try {
                        Float.parseFloat((String)value);
                        return null;
                    }
                    catch (NumberFormatException exception) {
                        return Messages.getString("kiml.ui.7");
                    }
                }
            });
            return floatEditor;
        case ENUM:
            return new ComboBoxCellEditor(parent, optionData.getChoices());
        default:
            return null;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getCategory()
     */
    public String getCategory() {
        return optionData.getTargetsDescription();
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getDescription()
     */
    public String getDescription() {
        return optionData.description;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getDisplayName()
     */
    public String getDisplayName() {
        return optionData.name;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getFilterFlags()
     */
    public String[] getFilterFlags() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getHelpContextIds()
     */
    public Object getHelpContextIds() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getId()
     */
    public Object getId() {
        return optionData.id;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getLabelProvider()
     */
    public ILabelProvider getLabelProvider() {
        return new LayoutOptionLabelProvider();
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertyDescriptor#isCompatibleWith(org.eclipse.ui.views.properties.IPropertyDescriptor)
     */
    public boolean isCompatibleWith(IPropertyDescriptor anotherProperty) {
        return anotherProperty instanceof LayoutPropertyDescriptor
                && this.optionData.id.equals(anotherProperty.getId());
    }

}
