/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.text.ui;

import org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;

import de.cau.cs.kieler.core.annotations.text.ui.AnnotationsAntlrTokenToAttributeIdMapper;

/**
 * Custom {@link AbstractAntlrTokenToAttributeIdMapper} contributing to the Kaom editor. Defines
 * custom mappings of keywords and terminals to highlighting configurations. The required method
 * {@link AbstractAntlrTokenToAttributeIdMapper#calculateId} will be provided by
 * {@link AnnotationsAntlrTokenToAttributeIdMapper} and can be specialized in order to add Types
 * specific highlight mappings.
 * 
 * @author chsch
 */
public class KaomAntlrTokenToAttributeIdMapper extends AnnotationsAntlrTokenToAttributeIdMapper {

//    protected String calculateId(String tokenName, int tokenType) {
//        return super.calculateId(tokenName, tokenType);
//    }

}
