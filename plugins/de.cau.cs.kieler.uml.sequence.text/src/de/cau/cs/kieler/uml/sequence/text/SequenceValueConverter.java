/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.uml.sequence.text;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.util.Strings;

/**
 * @author dja
 */
public class SequenceValueConverter extends DefaultTerminalConverters {

    /**
     * A value converter that will convert the keyword "all" to an integer.
     * 
     * @return The new custom value converter.
     */
    @ValueConverter(rule = "INT_GREATER_ZERO")
    public IValueConverter<Integer> INT_GREATER_ZERO() {
        return new IntGreaterZeroValueConverter();
    }

    /**
     * @author dja
     */
    private static class IntGreaterZeroValueConverter implements IValueConverter<Integer> {
        /**
         * {@inheritDoc}
         */
        public Integer toValue(String string, INode node) {
            if (Strings.isEmpty(string)) {
                throw new ValueConverterException("Couldn't convert empty string to int", node,
                        null);
            } else if ("all".equals(string.trim())) {
                return -1;
            }
            try {
                return Integer.parseInt(string);
            } catch (NumberFormatException e) {
                throw new ValueConverterException("Couldn't convert '" + string + "' to int", node,
                        e);
            }
        }

        /**
         * {@inheritDoc}
         */
        public String toString(Integer value) {
            return ((value == -1) ? "all" : Integer.toString(value));
        }
    };

}
