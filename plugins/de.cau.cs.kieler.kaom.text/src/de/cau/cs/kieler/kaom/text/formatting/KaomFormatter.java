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
package de.cau.cs.kieler.kaom.text.formatting;

import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Pair;

import de.cau.cs.kieler.core.annotations.text.formatting.AnnotationsFormatter;
import de.cau.cs.kieler.kaom.text.services.KaomGrammarAccess;

/**
 * This class contains custom formatting description for textual KAOM specifications.
 * 
 * @author chsch
 */
public class KaomFormatter extends AnnotationsFormatter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureFormatting(final FormattingConfig c) {
        KaomGrammarAccess f = (KaomGrammarAccess) getGrammarAccess();
        customConfigureFormatting(c, f);
    }

    /**
     * Method contains actual formatting instructions while GrammarAccess class maybe parameterized
     * allowing the reuse within other Formatters.
     * 
     * @param c
     *            FormattingConfig provided by caller
     * @param f
     *            GrammarAccess provided by caller
     */
    protected void customConfigureFormatting(final FormattingConfig c, final KaomGrammarAccess f) {
        super.customConfigureFormatting(c, f.getAnnotationsGrammarAccess());

        for (Pair<Keyword, Keyword> pair : f.findKeywordPairs("{", "}")) {
            c.setIndentation(pair.getFirst(), pair.getSecond());
            c.setLinewrap(1).after(pair.getFirst());
            c.setLinewrap(1).before(pair.getSecond());
            c.setLinewrap(1).after(pair.getSecond());
        }

        for (Keyword semicolon : f.findKeywords(";")) {
            c.setNoLinewrap().before(semicolon);
            c.setNoSpace().before(semicolon);
            c.setLinewrap().after(semicolon);
        }
        
        // This is a hack since line 61 is wrongly effectless 
        for (Keyword at : f.getAnnotationsGrammarAccess().findKeywords("@")) {
            c.setLinewrap().before(at);
        }
    }
}
