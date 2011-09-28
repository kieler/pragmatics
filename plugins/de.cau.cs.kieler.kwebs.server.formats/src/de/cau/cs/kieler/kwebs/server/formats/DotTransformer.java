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
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.common.collect.Maps;
import com.google.inject.Injector;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.graphviz.dot.GraphvizDotStandaloneSetup;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.Attribute;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.AttributeStatement;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.AttributeType;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.DotFactory;
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
import de.cau.cs.kieler.kiml.graphviz.dot.transformations.KGraphDotTransformation;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
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
    /** original Graphviz statements attached to graph elements. */
    private static final IProperty<Statement> PROP_STATEMENT
            = new Property<Statement>("dotTransformer.statement");
    /** original Graphviz identifiers attached to graph elements. */
    private static final IProperty<String> PROP_ID = new Property<String>("dotTransformer.name");
    /** original Graphviz graph attached to parent nodes. */
    private static final IProperty<Graph> PROP_GRAPH = new Property<Graph>("dotTransformer.graph");
    
    /** the injector for creation of resources. */
    private static Injector injector;

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
            parent.getData(KShapeLayout.class).setProperty(PROP_GRAPH, graph);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final TransformationData<GraphvizModel> transData) {
        for (KNode layoutNode : transData.getLayoutGraphs()) {
            applyLayout(layoutNode, new KVector(),
                    layoutNode.getData(KShapeLayout.class).getProperty(PROP_GRAPH));
        }
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
            
            public Object caseNodeStatement(final NodeStatement statement) {
                transformNode(statement, parent, transData);
                return null;
            }
            
            public Object caseEdgeStatement(final EdgeStatement statement) {
                transformEdge(statement, parent, transData);
                return null;
            }

            public Object caseSubgraph(final Subgraph subgraph) {
                KNode subKNode = transformNode(subgraph.getName(), parent, transData);
                KShapeLayout nodeLayout = subKNode.getData(KShapeLayout.class);
                if (nodeLayout.getProperty(PROP_STATEMENT) != null) {
                    Logger.log("Discarding subgraph \"" + subgraph.getName()
                            + "\" since its id is already used.");
                } else {
                    nodeLayout.setProperty(PROP_STATEMENT, subgraph);
                    transform(subgraph.getStatements(), subKNode, transData);
                }
                return null;
            }

            public Object caseAttributeStatement(final AttributeStatement statement) {
                // TODO evaluate global node and edge attributes
                return null;
            }

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
     * Transforms a node.
     * 
     * @param statement a node statement
     * @param parent the parent node
     * @param transData the transformation data instance
     */
    private void transformNode(final NodeStatement statement, final KNode parent,
            final TransformationData<GraphvizModel> transData) {
        KNode knode = transformNode(statement.getNode().getName(), parent, transData);
        KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
        if (nodeLayout.getProperty(PROP_STATEMENT) != null) {
            Logger.log("Discarding node \"" + statement.getNode().getName()
                    + "\" since its id is already used.");
        } else {
            nodeLayout.setProperty(PROP_STATEMENT, statement);
            
            // evaluate attributes for the new node
            for (Attribute attr : statement.getAttributes()) {
                try {
                    if (Attributes.LABEL.equals(attr.getName())) {
                        knode.getLabel().setText(attr.getValue());
                    } else if (Attributes.POS.equals(attr.getName())) {
                        KVector pos = new KVector();
                        pos.parse(attr.getValue());
                        pos.scale(KGraphDotTransformation.DPI);
                        nodeLayout.applyVector(pos);
                    } else if (Attributes.WIDTH.equals(attr.getName())) {
                        nodeLayout.setWidth(Float.parseFloat(attr.getValue())
                                * KGraphDotTransformation.DPI);
                    } else if (Attributes.HEIGHT.equals(attr.getName())) {
                        nodeLayout.setHeight(Float.parseFloat(attr.getValue())
                                * KGraphDotTransformation.DPI);
                    }
                } catch (NumberFormatException exception) {
                    Logger.log("Discarding attribute \"" + attr.getName()
                            + "\" for node \"" + statement.getNode().getName()
                            + "\" since its value could not be parsed correctly.");
                } catch (IllegalArgumentException exception) {
                    Logger.log("Discarding attribute \"" + attr.getName()
                            + "\" for node \"" + statement.getNode().getName()
                            + "\" since its value could not be parsed correctly.");
                }
            }
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
                knode.getData(KShapeLayout.class).setProperty(PROP_ID, nodeId);
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
     * @param transData the transformation data instance
     */
    private void transformEdge(final EdgeStatement statement, final KNode parent,
            final TransformationData<GraphvizModel> transData) {
        String sourceName = statement.getSourceNode().getName();
        KNode source = transformNode(sourceName, parent, transData);
        KPort sourcePort = null;
        if (statement.getSourceNode().getPort() != null) {
            sourcePort = transformPort(statement.getSourceNode().getPort()
                    .getCompass_pt().getName(), source, transData);
        }
        ListIterator<EdgeTarget> targetIter = statement.getEdgeTargets().listIterator();
        while (targetIter.hasNext()) {
            EdgeTarget edgeTarget = targetIter.next();
            KEdge kedge = KimlUtil.createInitializedEdge();
            kedge.setSource(source);
            kedge.setSourcePort(sourcePort);
            KNode target;
            KPort targetPort = null;
            Node edgeTargetNode = edgeTarget.getTargetnode();
            String targetName;
            if (edgeTargetNode == null) {
                targetName = edgeTarget.getTargetSubgraph().getName();
                target = transformNode(targetName, parent, transData);
            } else {
                targetName = edgeTargetNode.getName();
                target = transformNode(targetName, parent, transData);
                if (edgeTargetNode.getPort() != null) {
                    targetPort = transformPort(edgeTargetNode.getPort()
                            .getCompass_pt().getName(), target, transData);
                }
            }
            kedge.setTarget(target);
            kedge.setTargetPort(targetPort);
            
            KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
            if (targetIter.previousIndex() == 0) {
                // this is the first target - just store the edge statement
                edgeLayout.setProperty(PROP_STATEMENT, statement);
            } else {
                // the edge statement has more that one target - create a copy
                EdgeStatement newStatement = DotFactory.eINSTANCE.createEdgeStatement();
                Node sourceNode = DotFactory.eINSTANCE.createNode();
                sourceNode.setName(sourceName);
                newStatement.setSourceNode(sourceNode);
                targetIter.remove();
                newStatement.getEdgeTargets().add(edgeTarget);
                for (Attribute attr : statement.getAttributes()) {
                    newStatement.getAttributes().add(EcoreUtil.copy(attr));
                }
                edgeLayout.setProperty(PROP_STATEMENT, newStatement);
            }
            
            // evaluate attributes for the new edge
            for (Attribute attr : statement.getAttributes()) {
                if (Attributes.LABEL.equals(attr.getName())) {
                    KLabel label = KimlUtil.createInitializedLabel();
                    label.setText(attr.getValue());
                    label.getData(KShapeLayout.class).setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT,
                            EdgeLabelPlacement.CENTER);
                    kedge.getLabels().add(label);
                } else if (Attributes.HEADLABEL.equals(attr.getName())) {
                    KLabel label = KimlUtil.createInitializedLabel();
                    label.setText(attr.getValue());
                    label.getData(KShapeLayout.class).setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT,
                            EdgeLabelPlacement.HEAD);
                    kedge.getLabels().add(label);
                } else if (Attributes.TAILLABEL.equals(attr.getName())) {
                    KLabel label = KimlUtil.createInitializedLabel();
                    label.setText(attr.getValue());
                    label.getData(KShapeLayout.class).setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT,
                            EdgeLabelPlacement.TAIL);
                    kedge.getLabels().add(label);
                }
            }
            
            // the edge target is the source for the next edge target
            source = target;
            sourceName = targetName;
            sourcePort = targetPort;
        }
    }
    

    /*---------- Layout Transfer KGraph to Dot ----------*/

    /**
     * Apply layout to a parent node and its children.
     * 
     * @param parent a parent node
     * @param offset the node's offset in the graph
     * @param graph the Graphviz graph
     */
    private void applyLayout(final KNode parent, final KVector offset, final Graph graph) {
        for (KNode knode : parent.getChildren()) {
            KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
            Statement statement = nodeLayout.getProperty(PROP_STATEMENT);
            if (statement == null) {
                // the node was only declared implicitly - create an explicit declaration
                NodeStatement stm = DotFactory.eINSTANCE.createNodeStatement();
                Node node = DotFactory.eINSTANCE.createNode();
                node.setName(nodeLayout.getProperty(PROP_ID));
                stm.setNode(node);
                graph.getStatements().add(stm);
                statement = stm;
            }
            if (statement instanceof NodeStatement) {
                List<Attribute> attributes = ((NodeStatement) statement).getAttributes();
                // transfer node position
                removeAttributes(attributes, Attributes.POS);
                double xpos = (nodeLayout.getXpos() + nodeLayout.getWidth() / 2 + offset.x)
                        / KGraphDotTransformation.DPI;
                double ypos = (nodeLayout.getYpos() + nodeLayout.getHeight() / 2 + offset.y)
                        / KGraphDotTransformation.DPI;
                String posString = "\"" + Double.toString(xpos) + "," + Double.toString(ypos) + "\"";
                attributes.add(KGraphDotTransformation.createAttribute(Attributes.POS, posString));
                // transfer node size
                removeAttributes(attributes, Attributes.WIDTH);
                attributes.add(KGraphDotTransformation.createAttribute(Attributes.WIDTH,
                        Float.toString(nodeLayout.getWidth() / KGraphDotTransformation.DPI)));
                removeAttributes(attributes, Attributes.HEIGHT);
                attributes.add(KGraphDotTransformation.createAttribute(Attributes.HEIGHT,
                        Float.toString(nodeLayout.getHeight() / KGraphDotTransformation.DPI)));
            } else if (statement instanceof Subgraph) {
                applyLayout(knode, new KVector(offset).translate(nodeLayout.getXpos(),
                        nodeLayout.getYpos()), graph);
            }
            
            for (KEdge kedge : knode.getOutgoingEdges()) {
                applyLayout(kedge, offset, graph);
            }
        }
        
        // transfer graph size to bounding box
        List<Statement> statements;
        KShapeLayout parentLayout = parent.getData(KShapeLayout.class);
        Statement graphStm = parentLayout.getProperty(PROP_STATEMENT);
        if (graphStm instanceof Subgraph) {
            statements = ((Subgraph) graphStm).getStatements();
        } else {
            statements = graph.getStatements();
        }
        removeGraphAttributes(statements, Attributes.BOUNDINGBOX);
        float width = parentLayout.getWidth() / KGraphDotTransformation.DPI;
        float height = parentLayout.getHeight() / KGraphDotTransformation.DPI;
        String bbString = "\"0,0," + Float.toString(width)
                + "," + Float.toString(height) + "\"";
        statements.add(KGraphDotTransformation.createAttribute(
                Attributes.BOUNDINGBOX, bbString));
    }
    
    /**
     * Apply layout to an edge and its labels.
     * 
     * @param edge an edge
     * @param offset its offset in the graph
     * @param graph the Graphviz graph
     */
    private void applyLayout(final KEdge edge, final KVector offset, final Graph graph) {
        KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
        EdgeStatement edgeStatement = (EdgeStatement) edgeLayout.getProperty(PROP_STATEMENT);
        if (edgeStatement.eContainer() == null) {
            // this can happen when an edge with multiple target declarations was found
            graph.getStatements().add(edgeStatement);
        }
        
        // transfer edge bend points and source / target points
        List<Attribute> attributes = edgeStatement.getAttributes();
        removeAttributes(attributes, Attributes.POS);
        StringBuilder bendpointString = new StringBuilder("\"");
        KVectorChain vectorChain = edgeLayout.createVectorChain();
        ListIterator<KVector> chainIter = vectorChain.listIterator();
        while (chainIter.hasNext()) {
            KVector point = chainIter.next().add(offset).scale(1.0 / KGraphDotTransformation.DPI);
            bendpointString.append(point.x);
            bendpointString.append(',');
            bendpointString.append(point.y);
            if (chainIter.hasNext()) {
                bendpointString.append(' ');
            }
        }
        bendpointString.append('\"');
        attributes.add(KGraphDotTransformation.createAttribute(Attributes.POS,
                bendpointString.toString()));
        
        // transfer label positions
        for (KLabel label : edge.getLabels()) {
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            if (!labelLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
                String attrKey = null;
                switch (labelLayout.getProperty(LayoutOptions.EDGE_LABEL_PLACEMENT)) {
                case CENTER:
                    attrKey = Attributes.LABELPOS;
                    break;
                case HEAD:
                    attrKey = Attributes.HEADLP;
                    break;
                case TAIL:
                    attrKey = Attributes.TAILLP;
                    break;
                }
                if (attrKey != null) {
                    removeAttributes(attributes, attrKey);
                    double xpos = (labelLayout.getXpos() + labelLayout.getWidth() / 2 + offset.x)
                            / KGraphDotTransformation.DPI;
                    double ypos = (labelLayout.getYpos() + labelLayout.getHeight() / 2 + offset.y)
                            / KGraphDotTransformation.DPI;
                    String posString = "\"" + Double.toString(xpos)
                            + "," + Double.toString(ypos) + "\"";
                    attributes.add(KGraphDotTransformation.createAttribute(attrKey, posString));
                }
            }
        }
    }
    
    /**
     * Removes all graph-related occurrences of the given key from a statement list.
     * 
     * @param statements a list of statements
     * @param key an attribute key
     */
    private void removeGraphAttributes(final List<Statement> statements, final String key) {
        ListIterator<Statement> stmIter = statements.listIterator();
        while (stmIter.hasNext()) {
            Statement stm = stmIter.next();
            if (stm instanceof Attribute) {
                if (key.equals(((Attribute) stm).getName())) {
                    stmIter.remove();
                }
            } else if (stm instanceof AttributeStatement) {
                AttributeStatement attrStatement = (AttributeStatement) stm;
                if (attrStatement.getType() == AttributeType.GRAPH) {
                    removeAttributes(attrStatement.getAttributes(), key);
                    if (attrStatement.getAttributes().isEmpty()) {
                        stmIter.remove();
                    }
                }
            }
        }
    }
    
    /**
     * Removes all occurrences of the given key from an attributes list.
     * 
     * @param attributes a list of attributes
     * @param key an attribute key
     */
    private void removeAttributes(final List<Attribute> attributes, final String key) {
        ListIterator<Attribute> attrIter = attributes.listIterator();
        while (attrIter.hasNext()) {
            Attribute attr = attrIter.next();
            if (key.equals(attr.getName())) {
                attrIter.remove();
            }
        }
    }
    
}
