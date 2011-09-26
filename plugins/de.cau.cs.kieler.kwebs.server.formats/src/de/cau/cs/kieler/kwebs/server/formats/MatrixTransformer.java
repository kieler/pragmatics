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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kwebs.transformation.IGraphTransformer;
import de.cau.cs.kieler.kwebs.transformation.TransformationData;
import de.cau.cs.kieler.kwebs.transformation.TransformationException;

/**
 * A transformer for matrices as defined by Matrix Market.
 * See {@link http://math.nist.gov/MatrixMarket/}.
 *
 * @author msp
 */
public class MatrixTransformer implements IGraphTransformer<Matrix> {

    /** the nodes that are created by this transformer, in order as given by the matrix. */
    private static final IProperty<KNode[]> NODES = new Property<KNode[]>("nodes");
    /** the edges that are created by this transformer. */
    private static final IProperty<KEdge[]> EDGES = new Property<KEdge[]>("edges");
    
    // CHECKSTYLEOFF MagicNumber

    /**
     * {@inheritDoc}
     */
    public Matrix deserialize(final String serializedGraph) {
        int linenr = 0;
        try {
            BufferedReader reader = new BufferedReader(new StringReader(serializedGraph));
            // ignore initial comment lines
            String line = null;
            do {
                line = reader.readLine();
                linenr++;
            } while (line != null && line.startsWith("%"));
            
            if (line != null) {
                // read size of the matrix: [rows  columns  non-zero-entries]
                StringTokenizer tokenizer = new StringTokenizer(line, " \t");
                int tokenc = tokenizer.countTokens();
                if (tokenc < 2 || tokenc > 3) {
                    throw new TransformationException(
                            "Unexpected number of tokens on input line " + linenr);
                }
                int rows = Integer.parseInt(tokenizer.nextToken());
                int columns = Integer.parseInt(tokenizer.nextToken());
                Matrix matrix = new Matrix(rows, columns);
                
                if (tokenc == 3) {
                    // matrix in coordinate format: [i  j  value]
                    int nonzeros = Integer.parseInt(tokenizer.nextToken());
                    List<Matrix.Entry> adjlist = matrix.createList(nonzeros);
                    do {
                        do {
                            line = reader.readLine();
                            linenr++;
                        } while (line != null && line.startsWith("%"));
                        if (line != null) {
                            tokenizer = new StringTokenizer(line, " \t");
                            tokenc = tokenizer.countTokens();
                            if (tokenc < 2 || tokenc > 3) {
                                throw new TransformationException(
                                        "Unexpected number of tokens on input line " + linenr);
                            }
                            int i = Integer.parseInt(tokenizer.nextToken());
                            int j = Integer.parseInt(tokenizer.nextToken());
                            int value = 1;
                            if (tokenc == 3) {
                                double dval = Double.parseDouble(tokenizer.nextToken());
                                value = Double.compare(dval, 0);
                            }
                            if (value != 0) {
                                adjlist.add(new Matrix.Entry(i - 1, j - 1, value));
                            }
                        }
                    } while (line != null);
                    
                } else {
                    // matrix in array format (column-oriented)
                    int[][] m = matrix.createMatrix();
                    mat_loop: for (int j = 0; j < columns; j++) {
                        for (int i = 0; i < rows; i++) {
                            line = reader.readLine();
                            linenr++;
                            if (line == null) {
                                break mat_loop;
                            }
                            double dval = Double.parseDouble(line.trim());
                            m[i][j] = Double.compare(dval, 0);
                        }
                    }
                }
                
                return matrix;
            }
            return new Matrix(0, 0);
        } catch (NumberFormatException exception) {
            throw new TransformationException(
                    "Parse error while reading number on input line" + linenr, exception);
        } catch (IOException exception) {
            // this should not happen
            throw new TransformationException(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(final Matrix graph) {
        StringBuilder builder = new StringBuilder();
        for (KVectorChain chain : graph.getLayout()) {
            for (KVector vector : chain) {
                builder.append(toString(vector.x));
                builder.append(' ');
                builder.append(toString(vector.y));
                builder.append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }
    
    /**
     * Creates a string representation of the given double value. The resulting
     * string will omit the period character if is is followed only by zeros, so
     * that integer values are really shown as such.
     * 
     * @param d a double value
     * @return a string representation that writes integers without period
     */
    private static String toString(final double d) {
        String s = Double.toString(d);
        int dotIndex = s.indexOf('.');
        if (dotIndex > 0) {
            for (int i = dotIndex + 1; i < s.length(); i++) {
                if (s.charAt(i) != '0') {
                    return s;
                }
            }
            return s.substring(0, dotIndex - 1);
        }
        return s;
    }
    
    /**
     * {@inheritDoc}
     */
    public void deriveLayout(final TransformationData<Matrix> transData) {
        Matrix matrix = transData.getSourceGraph();
        KNode parent = KimlUtil.createInitializedNode();
        int nodec = Math.max(matrix.getRows(), matrix.getColumns());
        KNode[] nodes = new KNode[nodec];
        for (int i = 0; i < nodec; i++) {
            nodes[i] = KimlUtil.createInitializedNode();
            nodes[i].setParent(parent);
            nodes[i].getLabel().setText(Integer.toString(i + 1));
        }
        List<KEdge> edgeList = new LinkedList<KEdge>();
        
        // transform matrix form
        int[][] m = matrix.getMatrix();
        if (m != null) {
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getColumns(); j++) {
                    if (m[i][j] != 0) {
                        KEdge kedge = KimlUtil.createInitializedEdge();
                        edgeList.add(kedge);
                        if (m[i][j] > 0) {
                            kedge.setSource(nodes[i]);
                            kedge.setTarget(nodes[j]);
                        } else if (m[i][j] < 0) {
                            kedge.setSource(nodes[j]);
                            kedge.setTarget(nodes[i]);
                        }
                    }
                }
            }
        }
        
        // transform coordinates form
        List<Matrix.Entry> list = matrix.getList();
        for (Matrix.Entry entry : list) {
            if (entry.i < nodec && entry.j < nodec && entry.value != 0) {
                KEdge kedge = KimlUtil.createInitializedEdge();
                edgeList.add(kedge);
                if (entry.value > 0) {
                    kedge.setSource(nodes[entry.i]);
                    kedge.setTarget(nodes[entry.j]);
                } else {
                    kedge.setSource(nodes[entry.j]);
                    kedge.setTarget(nodes[entry.i]);
                }
            }
        }

        transData.getLayoutGraphs().add(parent);
        transData.setProperty(NODES, nodes);
        transData.setProperty(EDGES, edgeList.toArray(new KEdge[edgeList.size()]));
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final TransformationData<Matrix> transData) {
        List<KVectorChain> layout = transData.getSourceGraph().getLayout();
        // first lines: coordinates of node positions
        for (KNode node : transData.getProperty(NODES)) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            KVectorChain chain = new KVectorChain();
            chain.add(nodeLayout.getXpos() + nodeLayout.getWidth() / 2,
                    nodeLayout.getYpos() + nodeLayout.getHeight() / 2);
            layout.add(chain);
        }
        // remaining lines: coordinates of edge bend points
        for (KEdge edge : transData.getProperty(EDGES)) {
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            if (!edgeLayout.getBendPoints().isEmpty()) {
                try {
                    KVectorChain vectorChain = new KVectorChain();
                    // the first pair of numbers indicates the source and target node
                    vectorChain.add(Double.valueOf(edge.getSource().getLabel().getText()),
                            Double.valueOf(edge.getTarget().getLabel().getText()));
                    // the remaining numbers are bend point coordinates
                    for (KPoint bendPoint : edgeLayout.getBendPoints()) {
                        vectorChain.add(bendPoint.getX(), bendPoint.getY());
                    }
                    layout.add(vectorChain);
                } catch (NumberFormatException exception) {
                    // ignore exception
                }
            }
        }
    }

}
