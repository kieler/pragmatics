/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.text;

import javax.inject.Inject;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.impl.AbstractDeclarativeValueConverterService;
import org.eclipse.xtext.conversion.impl.AbstractIDValueConverter;
import org.eclipse.xtext.conversion.impl.IDValueConverter;
import org.eclipse.xtext.conversion.impl.STRINGValueConverter;
import org.eclipse.xtext.nodemodel.INode;

/**
 * @author uru
 */
public class GranaValueConverters extends AbstractDeclarativeValueConverterService {

    @Inject
    private AbstractIDValueConverter idValueConverter;
    
    /**
     * Create a converter for the ID rule.
     * 
     * @return a value converter for ID
     */
    @ValueConverter(rule = "ID")
    public IValueConverter<String> ID() {
        return idValueConverter;
    }
    
    
    private QualifiedIDValueConverter qualifiedIdValueConverter = new QualifiedIDValueConverter();
    
    /**
     * Create a converter for the QualifiedID rule.
     * 
     * @return a value converter for QualifiedID
     */
    @ValueConverter(rule = "QualifiedID")
    public IValueConverter<String> QualifiedID() {
        return qualifiedIdValueConverter;
    }
    
    /**
     * Value converter for qualified identifiers.
     */
    private class QualifiedIDValueConverter extends IDValueConverter {

        @Override
        public String toString(final String s) {
            // Escape each ID with the converter for identifiers
            StringBuilder result = new StringBuilder();
            String[] idarray = s.split("\\.");
            for (int i = 0; i < idarray.length; i++) {
                if (i > 0) {
                    result.append('.');
                }
                result.append(idValueConverter.toString(idarray[i]));
            }
            return result.toString();
        }
        
        @Override
        public String toValue(final String string, final INode node) {
            if (string == null) {
                return null;
            }
            return string.replace("^", "");
        }
    }
    
    @Inject
    private STRINGValueConverter stringValueConverter;

    /**
     * Create a converter for the STRING rule.
     * 
     * @return a value converter for STRING
     */
    @ValueConverter(rule = "STRING")
    public IValueConverter<String> STRING() {
        return stringValueConverter;
    }
    
}
