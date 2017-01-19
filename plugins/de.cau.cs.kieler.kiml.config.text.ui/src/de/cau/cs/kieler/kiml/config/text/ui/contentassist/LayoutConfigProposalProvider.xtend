/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kiml.config.text.ui.contentassist

import org.eclipse.elk.graph.ElkNode
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor

/**
 *
 */
class LayoutConfigProposalProvider extends AbstractLayoutConfigProposalProvider {
    
    override complete_PropertyKey(EObject model, RuleCall ruleCall, ContentAssistContext context,
        ICompletionProposalAcceptor acceptor) {
        switch model {
            ElkNode: proposeProperties(model, null /* any layout alg */, null /* any target */, context, acceptor)
        }
    }
}