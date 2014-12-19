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
package de.cau.cs.kieler.kiml.formats.gml

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.StreamTokenizer
import java.util.Stack

/**
 * Parses the GML files in one top-down pass using a {@link StreamTokenizer}. 
 * For more information on the Graph Modelling Language see 
 * http://www.fim.uni-passau.de/en/fim/faculty/chairs/theoretische-informatik/projects.html.
 * 
 * Note that the StreamTokenizer assumes all numbers to be doubles. To
 * add support for integers we interpret a numbers {@code n} as integer
 * if {@code n % 1 == 0}. For instance, both {@code 1} and {@code 1.0} 
 * are converted to integers.
 * 
 * @author uru
 * @see GMLModel
 * @see GMLSerializer
 */
final class GMLParser {

    static val char SBO = '['
    static val char SBC = ']'
    static val char TT_QUOTED = '"'

    static val KEY_OR_CLOSE = 0
    static val VAL_OR_COL = 1

    static def parse(InputStream is) throws IOException {

        val r = new BufferedReader(new InputStreamReader(is))
        val st = new StreamTokenizer(r)
        st.slashSlashComments(true)
        st.slashStarComments(true)
        
        var expecting = KEY_OR_CLOSE;

        var Element currentElement
        var currentKey = "";
        
        val elementStack = new Stack<CollectionElement>
        // we always have a root element that collects top level
        // elements in the GML file
        elementStack.push(new CollectionElement(null, "root"))
        
        var eof = false;
        do {
            val token = st.nextToken();

            // expecting key values or a closing bracket
            if (expecting == KEY_OR_CLOSE) {
                switch (token) {
                    case StreamTokenizer.TT_EOF: {
                        eof = true;
                    }
                    case StreamTokenizer.TT_EOL: {}
                    case StreamTokenizer.TT_WORD: {
                        currentKey = st.sval
                        expecting = VAL_OR_COL
                    }
                    default: {
                        if (token as char == SBC) {
                            if (elementStack.size == 1) {
                                throw new IllegalArgumentException("Too many closing ']'.")
                            }
                            currentElement = elementStack.pop()
                            expecting = KEY_OR_CLOSE;
                        } else {
                            throw new IllegalArgumentException("Expected key or ']' but read '" 
                                + token as char + "' at line " + st.lineno + ".")
                        }
                    }
                }

            // expecting a value or an opening bracket, i.e.
            // the start of a collection of elements
            } else if (expecting == VAL_OR_COL) {

                // initially we push the root element on the stack and afterwards
                // assure that always at least the root element is on the stack
                // when reading a ']' (and popping an element).
                val container = elementStack.peek 
                var Element newElement = null
                
                switch (token) {
                    case StreamTokenizer.TT_EOF: {
                        eof = true;
                    }
                    case StreamTokenizer.TT_EOL: {}
                    case StreamTokenizer.TT_WORD,
                    case TT_QUOTED: {
                        newElement = new StringElement(container, currentKey, st.sval)
                        expecting = KEY_OR_CLOSE
                    }
                    case StreamTokenizer.TT_NUMBER: {
                        // special treatment of numbers, see class's javadoc
                        val Number n = if ((st.nval % 1) == 0.0) st.nval.intValue as Integer 
                                       else st.nval as Double
                        newElement = new NumberElement(container, currentKey, n)
                        expecting = KEY_OR_CLOSE
                    }
                    default: {
                        if (token as char == SBO) {
                            newElement = new CollectionElement(container, currentKey)
                            expecting = KEY_OR_CLOSE
                        } else {
                            throw new IllegalArgumentException("Expected value or but read " 
                                + token as char + "' at line " + st.lineno + ".")
                        }
                    }
                }

                // handle newly created elements
                if (newElement != null) {
                    container.elements += newElement

                    // push collection element to the stack
                    if (newElement instanceof CollectionElement) {
                        elementStack.push(newElement as CollectionElement)
                    }

                    // mark as last created element
                    currentElement = newElement
                }
            }

        } while (!eof);

        if (elementStack.size < 1) {
            throw new IllegalArgumentException("Too many closing ']'.")
        } else if (elementStack.size > 1) {
            throw new IllegalArgumentException("Too many opening '['.")
        }
        
        // pop the root element
        val root = elementStack.pop
        
        // copy to eobject surrogate
        val model = new GMLModel
        model.elements.addAll(root.elements)
        
        return model
    }

}
