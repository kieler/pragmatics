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

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

/**
 * This class contains custom formatting description.
 * 
 * @author msp
 */
public class GMLFormatter extends AbstractDeclarativeFormatter {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void configureFormatting(final FormattingConfig c) {
        // It's usually a good idea to activate the following three statements.
        // They will add and preserve newlines around comments
        // c.setLinewrap(0, 1, 2).before(getGrammarAccess().getSL_COMMENTRule());
        // c.setLinewrap(0, 1, 2).before(getGrammarAccess().getML_COMMENTRule());
        // c.setLinewrap(0, 1, 1).after(getGrammarAccess().getML_COMMENTRule());
    }
}
