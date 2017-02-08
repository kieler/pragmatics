/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
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
       
       c.setLinewrap().after(analysisRule)
       
       // RegularJob
       c.setLinewrap(1, 1, 2).after(regularJobAccess.nameAssignment_1)
       c.setLinewrap().after(regularJobAccess.layoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0)
       c.setLinewrap().after(regularJobAccess.measureExecutionTimeMeasureExecutionTimeKeyword_3_0)
       c.setLinewrap(1, 1, 2).after(regularJobAccess.resourcesKeyword_4)
       c.setLinewrap(1, 1, 2).after(regularJobAccess.layoutoptionsKeyword_6)
       c.setLinewrap(1, 1, 2).after(regularJobAccess.analysesKeyword_8)
       
       // RangeJob
       c.setLinewrap(1, 1, 2).after(rangeJobAccess.nameAssignment_1)
       c.setLinewrap(1, 1, 2).after(rangeJobAccess.resourcesKeyword_3)
       c.setLinewrap(1, 1, 2).after(rangeJobAccess.layoutoptionsKeyword_5)
       c.setLinewrap(1, 1, 2).after(rangeJobAccess.analysesKeyword_7)
       c.setLinewrap(1, 1, 2).after(rangeJobAccess.rangeoptionKeyword_9)
       c.setLinewrap(1, 1, 2).after(rangeJobAccess.rangeanalysisKeyword_12_0_0)
       c.setLinewrap(1, 1, 2).after(rangeJobAccess.rangeOptionAssignment_10)
       c.setLinewrap(1, 1, 2).after(rangeJobAccess.rangeAnalysisComponentSIGNED_INTTerminalRuleCall_12_0_2_1_0)
       c.setLinewrap(1, 1, 2).after(rangeAccess.rule)
       
       
       c.setLinewrap.after(granaAccess.globalResourcesKeyword_0_0)   
       c.setLinewrap.after(granaAccess.globalOutputsKeyword_1_0)
       
       c.setLinewrap.after(globalResourceRefRule)
       c.setLinewrap.after(globalOutputRefRule)
       
       c.setLinewrap(2, 2, 2).before(granaAccess.globalOutputsKeyword_1_0)
       c.setLinewrap(2, 2, 2).before(granaAccess.executeKeyword_2_0)
    
        // key value pairs
        c.setLinewrap.after(propertyAccess.valueAssignment_2_0)
        c.setLinewrap.after(propertyAccess.valueAssignment_2_1)
        c.setLinewrap.after(propertyAccess.valueAssignment_2_2)
        c.setLinewrap.after(propertyAccess.valueAssignment_2_3)
       
       // indentation increments 
       c.setIndentationIncrement.after(granaAccess.globalResourcesKeyword_0_0)
       c.setIndentationIncrement.after(granaAccess.globalOutputsKeyword_1_0)
       c.setIndentationIncrement.after(regularJobAccess.nameAssignment_1)
       c.setIndentationIncrement.after(regularJobAccess.resourcesKeyword_4)
       c.setIndentationIncrement.after(regularJobAccess.analysesKeyword_8)
       c.setIndentationIncrement.after(regularJobAccess.layoutoptionsKeyword_6)
       c.setIndentationIncrement.after(rangeJobAccess.nameAssignment_1)
       c.setIndentationIncrement.after(rangeJobAccess.resourcesKeyword_3)
       c.setIndentationIncrement.after(rangeJobAccess.analysesKeyword_7)
       c.setIndentationIncrement.after(rangeJobAccess.layoutoptionsKeyword_5)
       c.setIndentationIncrement.after(rangeJobAccess.rangeoptionKeyword_9)
       c.setIndentationIncrement.after(rangeJobAccess.rangeanalysisKeyword_12_0_0)
       
       // indentation decrements
       c.setIndentationDecrement.before(granaAccess.globalOutputsKeyword_1_0)
       c.setIndentationDecrement.before(granaAccess.executeKeyword_2_0)
       c.setIndentationDecrement.before(regularJobAccess.layoutoptionsKeyword_6)
       c.setIndentationDecrement.before(regularJobAccess.analysesKeyword_8)
       c.setIndentationDecrement.before(regularJobAccess.outputKeyword_10)
       c.setIndentationDecrement.before(rangeJobAccess.layoutoptionsKeyword_5)
       c.setIndentationDecrement.before(rangeJobAccess.analysesKeyword_7)
       c.setIndentationDecrement.before(rangeJobAccess.outputKeyword_13)
       c.setIndentationDecrement.before(rangeJobAccess.rangeoptionKeyword_9)
       c.setIndentationDecrement.before(rangeJobAccess.rangeanalysisKeyword_12_0_0)
       c.setIndentationDecrement.after(outputRule)
	}
}
