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
package de.cau.cs.kieler.config.text.ide.contentassist

import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.text.ide.contentassist.ElkGraphProposalProvider
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ide.editor.contentassist.IIdeContentProposalAcceptor

/**
 *
 */
class LayoutConfigProposalProvider extends ElkGraphProposalProvider {
    
    override protected completePropertyKey(ContentAssistContext context, IIdeContentProposalAcceptor acceptor) {
        val model = context.currentModel
        switch model {
            ElkNode: super.completePropertyKey(context, acceptor)
        } 
    }
    
}