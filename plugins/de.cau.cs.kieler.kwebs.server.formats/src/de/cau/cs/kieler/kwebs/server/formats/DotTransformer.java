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
package de.cau.cs.kieler.kwebs.server.formats;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.common.collect.Maps;
import com.google.inject.Injector;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.graphviz.dot.GraphvizDotStandaloneSetup;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Attribute;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.AttributeStatement;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.EdgeStatement;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.EdgeTarget;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Graph;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.GraphvizModel;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Node;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.NodeStatement;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Statement;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Subgraph;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.util.DotSwitch;
import de.cau.cs.kieler.kiml.graphviz.dot.transformations.Attributes;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.transformation.AbstractEmfTransformer;
import de.cau.cs.kieler.kwebs.transformation.TransformationData;

/**
 * A transformer for Graphviz Dot.
 *
 * @author msp
 */
public class DotTransformer extends AbstractEmfTransformer<GraphvizModel> {
    
    /** map of Graphviz node identifiers to their KNode instances. */
    private static final IProperty<Map<String, KNode>> NODE_ID_MAP
            = new Property<Map<String, KNode>>("nodeIdMap");
    /** map of Graphviz port identifiers to their KPort instances. */
    private static final IProperty<Map<Pair<KNode, String>, KPort>> PORT_ID_MAP
            = new Property<Map<Pair<KNode, String>, KPort>>("portIdMap");
    
    /** the injector for creation of resources. */
    private static Injector injector;

    /**
     * {@inheritDoc}
     */
    public String getSupportedFormat() {
        return Formats.FORMAT_DOT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFileExtension() {
        return "graphviz_dot";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResourceSet createResourceSet() {
        if (injector == null) {
            injector = new GraphvizDotStandaloneSetup().createInjectorAndDoEMFRegistration();
        }
        return injector.getInstance(XtextResourceSet.class);
    }

    /**
     * {@inheritDoc}
     */
    public void deriveLayout(final TransformationData<GraphvizModel> transData) {
        for (Graph graph : transData.getSourceGraph().getGraphs()) {
            KNode parent = KimlUtil.createInitializedNode();
            Map<String, KNode> nodeIdMap = Maps.newHashMap();
            transData.setProperty(NODE_ID_MAP, nodeIdMap);
            Map<Pair<KNode, String>, KPort> portIdMap = Maps.newHashMap();
            transData.setProperty(PORT_ID_MAP, portIdMap);
            transform(graph.getStatements(), parent, transData);
            transData.getLayoutGraphs().add(parent);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final TransformationData<GraphvizModel> transData) {
        // TODO Auto-generated method stub
        
    }
    
    /*---------- Transformation Dot to KGraph ----------*/
    
    /**
     * Transform a Dot graph to a KNode.
     * 
     * @param statements a list of Dot statements
     * @param parent a KNode
     */
    private void transform(final List<Statement> statements, final KNode parent,
            final TransformationData<GraphvizModel> transData) {
        DotSwitch<Object> statementSwitch = new DotSwitch<Object>() {
            
            @Override
            public Object caseNodeStatement(final NodeStatement statement) {
                KNode knode = transformNode(statement.getNode().getName(), parent, transData);
                for (Attribute attr : statement.getAttributes()) {
                    if (Attributes.LABEL.equals(attr.getName())) {
                        knode.getLabel().setText(attr.getValue());
                    }
                }
                return null;
            }
            
            @Override
            public Object caseEdgeStatement(final EdgeStatement statement) {
                transformEdge(statement, parent, transData);
                return null;
            }

            @Override
            public Object caseSubgraph(final Subgraph subgraph) {
                KNode subKNode = transformNode(subgraph.getName(), parent, transData);
                subKNode.setParent(parent);
                transform(subgraph.getStatements(), subKNode, transData);
                return null;
            }

            @Override
            public Object caseAttributeStatement(final AttributeStatement statement) {
                // TODO evaluate global node and edge attributes
                return null;
            }

            @Override
            public Object caseAttribute(final Attribute attribute) {
                // TODO evaluate graph attributes
                return null;
            }

        };
        for (Statement statement : statements) {
            statementSwitch.doSwitch(statement);
        }
    }
    
    /**
     * Transforms a single node, if not already done before.
     * 
     * @param nodeId a node identifier
     * @param parent the parent where the new KNode is stored
     * @param transData the transformation data instance
     * @return a KNode instance
     */
    private KNode transformNode(final String nodeId, final KNode parent,
            final TransformationData<GraphvizModel> transData) {
        Map<String, KNode> nodeIdMap = transData.getProperty(NODE_ID_MAP);
        KNode knode = nodeIdMap.get(nodeId);
        if (knode == null) {
            knode = KimlUtil.createInitializedNode();
            knode.setParent(parent);
            if (nodeId != null) {
                nodeIdMap.put(nodeId, knode);
            }
        }
        return knode;
    }
    
    /**
     * Transforms a single port, if not already done before.
     * 
     * @param portId a port identifier
     * @param node the node to which the new KPort belongs
     * @param transData the transformation data instance
     * @return a KPort instance
     */
    private KPort transformPort(final String portId, final KNode node,
            final TransformationData<GraphvizModel> transData) {
        Map<Pair<KNode, String>, KPort> portIdMap = transData.getProperty(PORT_ID_MAP);
        Pair<KNode, String> key = new Pair<KNode, String>(node, portId);
        KPort kport = portIdMap.get(key);
        if (kport == null) {
            kport = KimlUtil.createInitializedPort();
            kport.setNode(node);
            if (portId != null) {
                portIdMap.put(key, kport);
            }
        }
        return kport;
    }
    
    /**
     * Transforms an edge.
     * 
     * @param statement an edge statement
     * @param parent the parent node
     */
    private void transformEdge(final EdgeStatement statement, final KNode parent,
            final TransformationData<GraphvizModel> transData) {
        KNode source = transformNode(statement.getSourceNode().getName(), parent, transData);
        KPort sourcePort = null;
        if (statement.getSourceNode().getPort() != null) {
            sourcePort = transformPort(statement.getSourceNode().getPort()
                    .getCompass_pt().getName(), source, transData);
        }
        for (EdgeTarget edgeTarget : statement.getEdgeTargets()) {
            KEdge kedge = KimlUtil.createInitializedEdge();
            kedge.setSource(source);
            kedge.setSourcePort(sourcePort);
            KNode target;
            Node edgeTargetNode = edgeTarget.getTargetnode();
            if (edgeTargetNode == null) {
                target = transformNode(edgeTarget.getTargetSubgraph().getName(), parent, transData);
            } else {
                target = transformNode(edgeTargetNode.getName(), parent, transData);
                if (edgeTargetNode.getPort() != null) {
                    kedge.setTargetPort(transformPort(edgeTargetNode.getPort()
                            .getCompass_pt().getName(), target, transData));
                }
            }
            kedge.setTarget(target);
            
            // add edge labels
            for (Attribute attr : statement.getAttributes()) {
                if (Attributes.LABEL.equals(attr.getName())) {
                    KLabel label = KimlUtil.createInitializedLabel(kedge);
                    label.setText(attr.getValue());
                    label.getData(KShapeLayout.class).setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT,
                            EdgeLabelPlacement.CENTER);
                    kedge.getLabels().add(label);
                } else if (Attributes.HEADLABEL.equals(attr.getName())) {
                    KLabel label = KimlUtil.createInitializedLabel(kedge);
                    label.setText(attr.getValue());
                    label.getData(KShapeLayout.class).setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT,
                            EdgeLabelPlacement.HEAD);
                    kedge.getLabels().add(label);
                } else if (Attributes.TAILLABEL.equals(attr.getName())) {
                    KLabel label = KimlUtil.createInitializedLabel(kedge);
                    label.setText(attr.getValue());
                    label.getData(KShapeLayout.class).setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT,
                            EdgeLabelPlacement.TAIL);
                    kedge.getLabels().add(label);
                }
            }
        }

    }
    
}
