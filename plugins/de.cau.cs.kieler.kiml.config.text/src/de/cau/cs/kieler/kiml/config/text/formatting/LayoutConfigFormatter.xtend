/*
 * generated by Xtext
 */
package de.cau.cs.kieler.kiml.config.text.formatting

import com.google.inject.Inject
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter
import org.eclipse.xtext.formatting.impl.FormattingConfig
import de.cau.cs.kieler.kiml.config.text.services.LayoutConfigGrammarAccess

/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation.html#formatting
 * on how and when to use it 
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
public class LayoutConfigFormatter extends AbstractDeclarativeFormatter {
	
	@Inject extension LayoutConfigGrammarAccess
	
	override protected configureFormatting(FormattingConfig c) {
		for (pair: findKeywordPairs('{', '}')) {
			c.setIndentation(pair.first, pair.second)
			c.setLinewrap(1).after(pair.first)
			c.setLinewrap(1).before(pair.second)
			c.setLinewrap(1).after(pair.second)
		}
		for (comma: findKeywords(',')) {
			c.setNoLinewrap().before(comma)
			c.setNoSpace().before(comma)
			c.setLinewrap().after(comma)
		}
		for (colon : findKeywords(":")) {
            c.setNoLinewrap().before(colon);
            c.setNoSpace().before(colon);
        }
		c.setLinewrap(0, 1, 2).before(SL_COMMENTRule)
		c.setLinewrap(0, 1, 2).before(ML_COMMENTRule)
		c.setLinewrap(0, 1, 1).after(ML_COMMENTRule)
		
		c.setLinewrap.after(propertyRule)
	}
}
