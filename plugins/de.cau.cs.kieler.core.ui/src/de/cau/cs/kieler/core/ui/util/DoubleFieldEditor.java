/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.ui.util;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.cau.cs.kieler.core.ui.Messages;

/**
 * Field editor for input of double values.
 * 
 * @kieler.design proposed 2012-11-02 cds
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author ???
 */
public class DoubleFieldEditor extends StringFieldEditor {

    /** lower bound for double values. */
    private double lowerBound = Double.MIN_VALUE;
    /** upper bound for double values. */
    private double upperBound = Double.MAX_VALUE;

    /**
     * Creates a new Double Field Editor.
     * 
     * @param name
     *            the name of the preference this field editor works on
     * @param labelText
     *            the label text of the field editor
     * @param parent
     *            the parent of the field editor's control
     */
    public DoubleFieldEditor(final String name, final String labelText,
            final Composite parent) {
        super(name, labelText, parent);
    }

    /**
     * Sets the range of valid values for this field.
     * 
     * @param thelowerBound
     *            the minimum allowed value (inclusive)
     * @param theupperBound
     *            the maximum allowed value (inclusive)
     */
    public void setValidRange(final double thelowerBound,
            final double theupperBound) {
        this.lowerBound = thelowerBound;
        this.upperBound = theupperBound;
        setErrorMessage(Messages.DoubleFieldEditor_doubleBounds
                + thelowerBound + "," + theupperBound + "]"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkState() {

        Text text = getTextControl();

        if (text == null) {
            return false;
        }

        String numberString = text.getText();
        try {
            double number = Double.valueOf(numberString).doubleValue();
            if (number >= lowerBound && number <= upperBound) {
                clearErrorMessage();
                return true;
            }

            showErrorMessage();
            return false;

        } catch (NumberFormatException e1) {
            showErrorMessage();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doLoad() {
        Text text = getTextControl();
        if (text != null) {
            double value = getPreferenceStore().getDouble(getPreferenceName());
            text.setText("" + value); //$NON-NLS-1$
            oldValue = "" + value; //$NON-NLS-1$
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doLoadDefault() {
        Text text = getTextControl();
        if (text != null) {
            double value = getPreferenceStore().getDefaultDouble(
                    getPreferenceName());
            text.setText("" + value); //$NON-NLS-1$
        }
        valueChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doStore() {
        Text text = getTextControl();
        if (text != null) {
            Double d = new Double(text.getText());
            getPreferenceStore().setValue(getPreferenceName(), d.doubleValue());
        }
    }

    /**
     * Returns this field editor's current value as an integer.
     * 
     * @return the value
     */
    public double getDoubleValue() {
        return new Double(getStringValue()).doubleValue();
    }

}
