/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.gml.formatting;

import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;

import de.cau.cs.kieler.kiml.formats.gml.services.GMLGrammarAccess;

/**
 * This class contains custom formatting description.
 * 
 * @author msp
 */
public class GMLFormatter extends AbstractDeclarativeFormatter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureFormatting(final FormattingConfig c) {
        GMLGrammarAccess f = (GMLGrammarAccess) getGrammarAccess();
        
        for (Pair<Keyword, Keyword> pair : f.findKeywordPairs("[", "]")) {
            c.setIndentation(pair.getFirst(), pair.getSecond());
            c.setLinewrap(1).after(pair.getFirst());
            c.setLinewrap(1).before(pair.getSecond());
            c.setLinewrap(1).after(pair.getSecond());
        }
        
        c.setLinewrap(1).after(f.getValueRule());
        // CHECKSTYLEOFF MagicNumber
        c.setAutoLinewrap(255);
    }
    
}
