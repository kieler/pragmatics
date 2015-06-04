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
package de.cau.cs.kieler.kiml.grana.text.formatting

import com.google.inject.Inject
import de.cau.cs.kieler.kiml.grana.text.services.GranaGrammarAccess
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter
import org.eclipse.xtext.formatting.impl.FormattingConfig

/**
 * Formatter for textually specified Grana analyses.
 * 
 * @author uru
 */
class GranaFormatter extends AbstractDeclarativeFormatter {

	@Inject extension GranaGrammarAccess
	
	override protected void configureFormatting(FormattingConfig c) {
	    
	    c.setAutoLinewrap(120);
	    
        // line wrapping for comments
		c.setLinewrap(0, 1, 2).before(SL_COMMENTRule)
		c.setLinewrap(0, 1, 2).before(ML_COMMENTRule)
		c.setLinewrap(0, 1, 1).after(ML_COMMENTRule)
		
	    // general formatting options relative to some delimiters
        for (pair : findKeywordPairs("{", "}")) {
            c.setIndentation(pair.getFirst(), pair.getSecond());
            c.setLinewrap(1).after(pair.getFirst());
            c.setLinewrap(1).before(pair.getSecond());
            c.setLinewrap(1).after(pair.getSecond());
        }
        for (pair : findKeywordPairs("[", "]")) {
            c.setNoSpace().after(pair.getFirst());
            c.setNoSpace().before(pair.getSecond()); 
        }
        for (pair : findKeywordPairs("(", ")")) {
            c.setNoSpace().after(pair.getFirst());
            c.setNoSpace().before(pair.getSecond());
        }
        for (Keyword comma : findKeywords(",")) {
            c.setNoLinewrap().before(comma);
            c.setNoSpace().before(comma);
        }
        for (Keyword colon : findKeywords(":")) {
            c.setNoLinewrap().before(colon);
            c.setNoSpace().before(colon);
        }
        for (Keyword star : findKeywords("*")) {
            c.setNoLinewrap().after(star);
            c.setNoSpace().after(star);
        }
	   
	   // larger separations
	   c.setLinewrap(2, 2, 2).before(jobRule)
       
       // single line breaks after rules
	   c.setLinewrap().after(resourceRule)
	   c.setLinewrap().after(localResourceRule)
	   c.setLinewrap().after(resourceReferenceRule)
	   c.setLinewrap().after(localOutputRule)
	   c.setLinewrap().after(outputReferenceRule)
	   
	   c.setLinewrap(1, 1, 2).after(jobAccess.nameAssignment_2)
	   c.setLinewrap().after(jobAccess.layoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0)
	   c.setLinewrap().after(jobAccess.measureExecutionTimeMeasureExecutionTimeKeyword_4_0)
	   c.setLinewrap(1, 1, 2).after(jobAccess.resourcesKeyword_5)
	   c.setLinewrap(1, 1, 2).after(jobAccess.layoutoptionsKeyword_7)
	   c.setLinewrap(1, 1, 2).after(jobAccess.analysesKeyword_9)
	   c.setLinewrap().after(analysisRule)
	   
	   c.setLinewrap().after(granaAccess.globalResourcesKeyword_0_0)   
	   c.setLinewrap().after(granaAccess.globalOutputsKeyword_1_0)
       
       c.setLinewrap(2,2,2).after(globalResourceRefRule)
       c.setLinewrap(2,2,2).after(globalOutputRefRule)

        // key value pairs
        c.setLinewrap.after(persistentEntryAccess.valueAssignment_2)
       
       // indentations
       c.setIndentationIncrement.after(granaAccess.globalResourcesKeyword_0_0)
       c.setIndentationDecrement.before(granaAccess.globalOutputsKeyword_1_0)
       c.setIndentationIncrement.after(granaAccess.globalOutputsKeyword_1_0)
       c.setIndentationDecrement.before(granaAccess.executeKeyword_2_0)
       
       c.setIndentationIncrement.after(jobAccess.nameAssignment_2)
       c.setIndentationIncrement.after(jobAccess.resourcesKeyword_5)
       c.setIndentationDecrement.before(jobAccess.layoutoptionsKeyword_7)
       c.setIndentationIncrement.after(jobAccess.analysesKeyword_9)
       c.setIndentationDecrement.before(jobAccess.outputKeyword_11)
       c.setIndentationDecrement.after(outputRule)
	}
}
