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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.common.collect.Maps;
import com.google.inject.Injector;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
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
import de.cau.cs.kieler.kwebs.transformation.IGraphTransformer;
import de.cau.cs.kieler.kwebs.transformation.TransformationException;

/**
 * A transformer for Graphviz Dot.
 *
 * @author msp
 */
public class DotTransformer implements IGraphTransformer<GraphvizModel> {
    
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
    public GraphvizModel deserialize(final String serializedGraph) {
        GraphvizModel graph = null;
        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream(
                serializedGraph.getBytes("UTF-8")
            );
            URI uri = URI.createURI("inputstream://temp.graphviz_dot");
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            EObject eObject = null;
            Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.load(inStream, options);
            eObject = resource.getContents().get(0);
            if (eObject instanceof GraphvizModel) {
                graph = (GraphvizModel) eObject;
            }
            inStream.close();
        } catch (UnsupportedEncodingException e) {
            throw new TransformationException(e);
        } catch (IOException e) {
            throw new TransformationException(e);
        }
        return graph;
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(final GraphvizModel graph) {
        String xmi = null;
        try {
            EcoreUtil.resolveAll(graph);
            URI uri = URI.createURI("outputstream://temp.graphviz_dot");
            ResourceSet resourceSet = createResourceSet();
            Resource resource = resourceSet.createResource(uri);
            resource.unload();
            resource.getContents().add(graph);            
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.save(outStream, options);
            outStream.flush();
            xmi = new String(outStream.toByteArray(), "UTF-8");
            outStream.close();
        } catch (IOException e) {
            throw new TransformationException(e);
        }
        return xmi;
    }

    /**
     * Creates a resource set ready to be used with the GraphML meta model.
     *
     * @return a resource set
     */
    private ResourceSet createResourceSet() {
        if (injector == null) {
            injector = new GraphvizDotStandaloneSetup().createInjectorAndDoEMFRegistration();
        }
        return injector.getInstance(XtextResourceSet.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<KNode> deriveLayout(final GraphvizModel model) {
        List<KNode> result = new LinkedList<KNode>();
        for (Graph graph : model.getGraphs()) {
            KNode parent = KimlUtil.createInitializedNode();
            transform(graph.getStatements(), parent);
            result.add(parent);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final GraphvizModel graph, final List<KNode> layout) {
        // TODO Auto-generated method stub
        
    }
    
    /*---------- Transformation Dot to KGraph ----------*/
    
    /** map of Dot node identifiers to KNodes. */
    private Map<String, KNode> nodeIdMap = Maps.newHashMap();
    /** map of Dot port identifiers to KPorts. */
    private Map<Pair<KNode, String>, KPort> portIdMap = Maps.newHashMap();
    
    
    /**
     * Transform a Dot graph to a KNode.
     * 
     * @param statements a list of Dot statements
     * @param parent a KNode
     */
    private void transform(final List<Statement> statements, final KNode parent) {
        DotSwitch<Object> statementSwitch = new DotSwitch<Object>() {
            
            @Override
            public Object caseNodeStatement(final NodeStatement statement) {
                KNode knode = transformNode(statement.getNode().getName(), parent);
                for (Attribute attr : statement.getAttributes()) {
                    if (Attributes.LABEL.equals(attr.getName())) {
                        knode.getLabel().setText(attr.getValue());
                    }
                }
                return null;
            }
            
            @Override
            public Object caseEdgeStatement(final EdgeStatement statement) {
                transformEdge(statement, parent);
                return null;
            }

            @Override
            public Object caseSubgraph(final Subgraph subgraph) {
                KNode subKNode = transformNode(subgraph.getName(), parent);
                subKNode.setParent(parent);
                transform(subgraph.getStatements(), subKNode);
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
        
        nodeIdMap.clear();
        portIdMap.clear();
    }
    
    /**
     * Transforms a single node, if not already done before.
     * 
     * @param nodeId a node identifier
     * @param parent the parent where the new KNode is stored
     * @return a KNode instance
     */
    private KNode transformNode(final String nodeId, final KNode parent) {
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
     * @return a KPort instance
     */
    private KPort transformPort(final String portId, final KNode node) {
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
    private void transformEdge(final EdgeStatement statement, final KNode parent) {
        KNode source = transformNode(statement.getSourceNode().getName(), parent);
        KPort sourcePort = null;
        if (statement.getSourceNode().getPort() != null) {
            sourcePort = transformPort(statement.getSourceNode().getPort()
                    .getCompass_pt().getName(), source);
        }
        for (EdgeTarget edgeTarget : statement.getEdgeTargets()) {
            KEdge kedge = KimlUtil.createInitializedEdge();
            kedge.setSource(source);
            kedge.setSourcePort(sourcePort);
            KNode target;
            Node edgeTargetNode = edgeTarget.getTargetnode();
            if (edgeTargetNode == null) {
                target = transformNode(edgeTarget.getTargetSubgraph().getName(), parent);
            } else {
                target = transformNode(edgeTargetNode.getName(), parent);
                if (edgeTargetNode.getPort() != null) {
                    kedge.setTargetPort(transformPort(edgeTargetNode.getPort()
                            .getCompass_pt().getName(), target));
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
