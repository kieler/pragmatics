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
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.StreamTokenizer
import java.util.Stack
import java.io.IOException

/**
 * TODO
 * numbers are interpreted as int if %1 == 0
 * 
 * @author uru
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
        val elementStack = new Stack<CollectionElement>
        elementStack.push(new CollectionElement(null, "root"))

        var currentKey = "";
        
        var eof = false;
        do {

            val token = st.nextToken();

            if (expecting == KEY_OR_CLOSE) {

                switch (token) {
                    case StreamTokenizer.TT_EOF: {
                        eof = true;
                    }
                    case StreamTokenizer.TT_WORD: {
                        currentKey = st.sval
                        expecting = VAL_OR_COL
                    }
                    default: {
                        if (token as char == SBC) {
                            currentElement = elementStack.pop()
                            expecting = KEY_OR_CLOSE;
                        }
                    }
                }

            } else if (expecting == VAL_OR_COL) {

                var Element newElement = null
                val container = (if(!elementStack.isEmpty) elementStack.peek else null) as CollectionElement

                switch (token) {
                    case StreamTokenizer.TT_EOF: {
                        eof = true;
                    }
                    case StreamTokenizer.TT_EOL: {
                    }
                    case StreamTokenizer.TT_WORD,
                    case TT_QUOTED: {

                        newElement = new StringElement(container, currentKey, st.sval)
                        expecting = KEY_OR_CLOSE
                    }
                    case StreamTokenizer.TT_NUMBER: {
                        
                        val Number n = if ((st.nval % 1) == 0.0) st.nval.intValue as Integer 
                                       else st.nval as Double
                       
                        newElement = new NumberElement(container, currentKey, n)
                        expecting = KEY_OR_CLOSE
                    }
                    default: {
                        if (token as char == SBO) {
                            newElement = new CollectionElement(container, currentKey)
                            expecting = KEY_OR_CLOSE
                        }
                    }
                }

                if (newElement != null) {

                    // if a parent element exists, add the new element to it
                    if (container != null) {
                        container.elements += newElement
                    }

                    // push collection element to the stack
                    if (newElement instanceof CollectionElement) {
                        elementStack.push(newElement as CollectionElement)
                    }

                    // mark as last created element
                    currentElement = newElement
                }
            }

        } while (!eof);

        // pop the root element
        val root = elementStack.pop
        // copy to eobject surrogate
        val model = new GMLModel
        model.elements.addAll(root.elements)
        
        return model

    }

    def static void main(String[] args) {

        val test2 = '''
            cluster [
               id 1
            ] 
            cluster [
               id 2
            ]
        '''

        val fi = new FileInputStream(new File("D:/till_clusters.gml"));

        val e =  GMLParser.parse(new ByteArrayInputStream(test2.bytes))

        //        val large = new GMLParser().parse(fi)
        println(e)
        //        Files.write(large.toString.bytes, new File("D:/tmp.gml"))
       // println(GMLSerializer.serialize(e))

    } 

}
