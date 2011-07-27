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
package de.cau.cs.kieler.kiml.ui;

import org.eclipse.jface.dialogs.IInputValidator;

import de.cau.cs.kieler.kiml.LayoutOptionData;

/**
 * A validator for string input of layout option values by the user.
 *
 * @kieler.rating 2010-01-26 proposed yellow msp
 * @author msp
 */
public class LayoutOptionValidator implements IInputValidator {

    /** the layout option data to validate for. */
    private LayoutOptionData<?> optionData;
    
    /**
     * Creates a layout option validator for a layout option data.
     * 
     * @param theoptionData layout option data
     */
    public LayoutOptionValidator(final LayoutOptionData<?> theoptionData) {
        this.optionData = theoptionData;
    }
    
    /**
     * {@inheritDoc}
     */
    public String isValid(final String newText) {
        String trimmedText = newText.trim();
        switch (optionData.getType()) {
        case BOOLEAN:
        case REMOTE_ENUM:
        case ENUM:
            String[] choices = optionData.getChoices();
            for (int i = 0; i < choices.length; i++) {
                if (choices[i].equalsIgnoreCase(trimmedText)) {
                    return null;
                }
            }
            return getChoicesMessage(choices);
        case INT:
            try {
                Integer.parseInt(trimmedText);
                return null;
            } catch (NumberFormatException exception) {
                return Messages.getString("kiml.ui.26");
            }
        case FLOAT:
            try {
                Float.parseFloat(trimmedText);
                return null;
            } catch (NumberFormatException exception) {
                return Messages.getString("kiml.ui.27");
            }
        case OBJECT:
            if (optionData.parseValue(trimmedText) == null) {
                return Messages.getString("kiml.ui.39");
            } else {
                return null;
            }
        default:
            return null;
        }
    }
    
    /**
     * Creates an error message for available choices.
     * 
     * @param choices the available choices
     * @return an error message
     */
    private String getChoicesMessage(final String[] choices) {
        StringBuilder messageBuilder = new StringBuilder(Messages.getString("kiml.ui.25"));
        for (int i = 0; i < choices.length; i++) {
            if (i == 0) {
                messageBuilder.append(" " + choices[i]);
            } else {
                messageBuilder.append(", " + choices[i]);
            }
        }
        return messageBuilder.toString();
    }

}
