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
package de.cau.cs.kieler.kiml.graphviz.dot.serializer;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;

/**
 * Customized syntactic sequencer to enforce serialization of some optional keywords.
 * 
 * @author msp
 */
@SuppressWarnings("restriction")
public class GraphvizDotSyntacticSequencer extends AbstractGraphvizDotSyntacticSequencer {
    
    // CHECKSTYLEOFF MethodName
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void emit_AttributeStatement_CommaKeyword_2_1_0_q(final EObject semanticObject,
            final ISynNavigable transition, final List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getAttributeStatementAccess().getCommaKeyword_2_1_0(),
                ",", null);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void emit_EdgeStatement_CommaKeyword_2_1_1_0_q(final EObject semanticObject,
            final ISynNavigable transition, final List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getEdgeStatementAccess().getCommaKeyword_2_1_1_0(),
                ",", null);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void emit_NodeStatement_CommaKeyword_1_1_1_0_q(final EObject semanticObject,
            final ISynNavigable transition, final List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getNodeStatementAccess().getCommaKeyword_1_1_1_0(),
                ",", null);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void emit_Statement_SemicolonKeyword_1_q(final EObject semanticObject,
            final ISynNavigable transition, final List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getStatementAccess().getSemicolonKeyword_1(),
                ";", null);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void emit_Subgraph_SubgraphKeyword_1_0_q(final EObject semanticObject,
            final ISynNavigable transition, final List<INode> nodes) {
        acceptUnassignedKeyword(grammarAccess.getSubgraphAccess().getSubgraphKeyword_1_0(),
                "subgraph", null);
    }
    
}
