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


import java.util.EnumSet;
import java.util.Set;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetEntry;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.LayoutOptionLabelProvider;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * A property descriptor for layout options.
 * 
 * <p>Here's a small peculiarity concerning the descriptions of layout options: calling
 * {@link #getDescription()} only returns the first sentence of an option's description,
 * to be displayed in the status bar. To retrieve the full description, call
 * {@link #getLongDescription()}.</p>
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating yellow 2012-10-26 review KI-29 by cmot, sgu
 */
public class LayoutPropertyDescriptor implements IPropertyDescriptor {

    /** the layout option data associated with this property descriptor. */
    private LayoutOptionData optionData;
    /** option targets applicable to the currently selected diagram element. */
    private Set<LayoutOptionData.Target> elementTargets;
    /** the label provider for this property descriptor. */
    private LayoutOptionLabelProvider labelProvider;
    
    /**
     * Creates a layout property descriptor based on a specific layout option.
     * 
     * @param theoptionData the layout option data
     * @param theelementTargets option targets applicable to the currently selected diagram element
     */
    public LayoutPropertyDescriptor(final LayoutOptionData theoptionData,
            final Set<LayoutOptionData.Target> theelementTargets) {
        this.optionData = theoptionData;
        this.elementTargets = theelementTargets;
    }
    
    /**
     * {@inheritDoc}
     */
    public CellEditor createPropertyEditor(final Composite parent) {
        switch (optionData.getType()) {
        case STRING:
            if (LayoutOptions.ALGORITHM.equals(optionData)) {
                return new AlgorithmCellEditor(parent);
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
        case ENUM:
            return new ComboBoxCellEditor(parent, optionData.getChoices(), SWT.READ_ONLY);
        case ENUMSET:
            return new MultipleOptionsCellEditor(parent, optionData.getChoices());
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
        // Compute the intersection of the targets registered for the layout option
        // and those determined for the graph element currently in focus.
        Set<LayoutOptionData.Target> targets = EnumSet.copyOf(optionData.getTargets());
        if (elementTargets != null) {
            targets.retainAll(elementTargets);
        }
        // In normal cases, the intersection should contain exactly one element.
        if (targets.size() == 1) {
            switch (targets.iterator().next()) {
            case PARENTS:
                if (elementTargets != null && elementTargets.contains(LayoutOptionData.Target.NODES)) {
                    return Messages.getString("kiml.ui.74");
                }
                return Messages.getString("kiml.ui.73");
            case NODES:
                return Messages.getString("kiml.ui.75");
            case EDGES:
                return Messages.getString("kiml.ui.76");
            case PORTS:
                return Messages.getString("kiml.ui.77");
            case LABELS:
                return Messages.getString("kiml.ui.78");
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        // We only return the first sentence of the description. The longer description
        // can be retrieved by calling getLongDescription.
        String description = optionData.getDescription();
        int firstPeriodIndex = description.indexOf('.');
        
        if (firstPeriodIndex > 0) {
            return description.substring(0, firstPeriodIndex + 1);
        } else {
            return description;
        }
    }
    
    /**
     * Returns the option's full description. This is in contrast to {@link #getDescription()},
     * which only returns the description's first sentence.
     * 
     * @return the full description.
     */
    public String getLongDescription() {
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
            labelProvider = new LayoutOptionLabelProvider(optionData);
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
