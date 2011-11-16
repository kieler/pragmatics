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
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;

import com.google.inject.Inject;

import de.cau.cs.kieler.kiml.graphviz.dot.services.GraphvizDotGrammarAccess;

/**
 * Customized syntactic sequencer to enforce serialization of some optional keywords.
 * 
 * CHECKSTYLEOFF MethodName
 *
 * @author msp
 */
@SuppressWarnings("restriction")
public class GraphvizDotSyntacticSequencer extends AbstractGraphvizDotSyntacticSequencer {
    
    /**
     * The generated code falsely creates TokenAlias matchers with many=true and optional=false,
     * but it should be many=false and optional=true.
     * 
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=362581
     * 
     * FIXME remove this after the bug in the code generator was fixed
     */
    @Inject
    protected void init(IGrammarAccess access) {
        grammarAccess = (GraphvizDotGrammarAccess) access;
        match_AttributeStatement_CommaKeyword_2_1_0_q = new TokenAlias(false, true, grammarAccess.getAttributeStatementAccess().getCommaKeyword_2_1_0());
        match_EdgeStatement_CommaKeyword_2_1_1_0_q = new TokenAlias(false, true, grammarAccess.getEdgeStatementAccess().getCommaKeyword_2_1_1_0());
        match_EdgeStatement___LeftSquareBracketKeyword_2_0_RightSquareBracketKeyword_2_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getEdgeStatementAccess().getLeftSquareBracketKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getEdgeStatementAccess().getRightSquareBracketKeyword_2_2()));
        match_NodeStatement_CommaKeyword_1_1_1_0_q = new TokenAlias(false, true, grammarAccess.getNodeStatementAccess().getCommaKeyword_1_1_1_0());
        match_NodeStatement___LeftSquareBracketKeyword_1_0_RightSquareBracketKeyword_1_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getNodeStatementAccess().getLeftSquareBracketKeyword_1_0()), new TokenAlias(false, false, grammarAccess.getNodeStatementAccess().getRightSquareBracketKeyword_1_2()));
        match_Statement_SemicolonKeyword_1_q = new TokenAlias(false, true, grammarAccess.getStatementAccess().getSemicolonKeyword_1());
        match_Subgraph_SubgraphKeyword_1_0_q = new TokenAlias(false, true, grammarAccess.getSubgraphAccess().getSubgraphKeyword_1_0());
    }
    
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
