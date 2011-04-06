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
package de.cau.cs.kieler.core.annotations.formatting;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.parsetree.AbstractNode;
import org.eclipse.xtext.util.Strings;

/**
 * @author chsch
 * 
 */
public class AnnotationsValueConverter extends DefaultTerminalConverters {

    /**
     * Provides comment annotation converter dropping/attaching the leading/trailing characters.
     * @return dedicated value converter
     */
    @ValueConverter(rule = "COMMENT_ANNOTATION")
        public IValueConverter<String> CommentAnnotation() {
                return new IValueConverter<String>() {

                        public String toValue(String string, AbstractNode node) {
                                return string.replace("/**","").replace("*/", "").trim();
                        }

                        public String toString(String value) {
                                if (Strings.isEmpty(value)) {
                                        return null;
                                } else {
                                        return "/** " + value + " */";
                                }
                        }
                };
    }
    
    /**
     * Provides comment annotation converter dropping/attaching the leading/trailing characters.
     * @return dedicated value converter
     */
    @ValueConverter(rule = "TypeId")
        public IValueConverter<String> TypeId() {
                return new IValueConverter<String>() {

                        public String toValue(String string, AbstractNode node) {
                                return string.replace("[","").replace("]", "").trim();
                        }

                        public String toString(String value) {
                                if (Strings.isEmpty(value)) {
                                        return null;
                                } else {
                                        return "[" + value + "]";
                                }
                        }
                };
    }

    
    /**
     * Provides annotation key converter dropping/attaching the leading '@'.
     * @return dedicated value converter
     */
    @ValueConverter(rule = "ANNOTATION_KEY")
        public IValueConverter<String> AnnotationKey() {
                return new IValueConverter<String>() {

                        public String toValue(String string, AbstractNode node) {
                                return string.replace("@","");
                        }

                        public String toString(String value) {
                                if (Strings.isEmpty(value)) {
                                        return null;
                                } else {
                                        return "\"" + "@" + value + "\""; 
                                }
                        }
                };
    }
    

    /**
     * Provides EString converter dropping/attaching the leading/trailing character.
     * 
     * @return dedicated value converter
     */
    @ValueConverter(rule = "EString")
    public IValueConverter<String> EString() {
        return new IValueConverter<String>() {

            public String toValue(String string, AbstractNode node) {
                if (!Strings.isEmpty(string)) {
                    if (string.startsWith("\"") || string.startsWith("'")) {
                        return string.substring(1, string.length() - 1);
                    } else {
                        return string;
                    }
                }
                return "";
            }

            public String toString(String value) {
                String res = "\"\"";
                if (!Strings.isEmpty(value)) {
                    try {
                        res = AnnotationsValueConverter.this.getConverter("ID").toString(value);
                    } catch (ValueConverterException e) {
                        res = AnnotationsValueConverter.this.getConverter("STRING").toString(value);
                    }
                }
                return res;
            }
        };
    }
}
