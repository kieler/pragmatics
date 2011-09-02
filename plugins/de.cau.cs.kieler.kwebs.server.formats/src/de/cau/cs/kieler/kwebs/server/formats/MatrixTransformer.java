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

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.transformation.IGraphTransformer;
import de.cau.cs.kieler.kwebs.transformation.TransformationException;

/**
 * A transformer for matrices as defined by Matrix Market.
 * See {@link http://math.nist.gov/MatrixMarket/}.
 *
 * @author msp
 */
public class MatrixTransformer implements IGraphTransformer<Matrix> {

    /**
     * {@inheritDoc}
     */
    public String getSupportedFormat() {
        return Formats.FORMAT_MATRIX;
    }
    
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
                                adjlist.add(new Matrix.Entry(i, j, value));
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
                builder.append(vector.x);
                builder.append(' ');
                builder.append(vector.y);
                builder.append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }
    
    /** the node array in order of increasing index. */
    private KNode[] nodes;
    /** the edge array in column-major i,j order (array form) or in order as received (coord. form). */
    private KEdge[] edges;

    /**
     * {@inheritDoc}
     */
    public List<KNode> deriveLayout(final Matrix graph) {
        KNode parent = KimlUtil.createInitializedNode();
        int nodec = Math.max(graph.getRows(), graph.getColumns());
        nodes = new KNode[nodec];
        for (int i = 0; i < nodec; i++) {
            nodes[i] = KimlUtil.createInitializedNode();
            nodes[i].setParent(parent);
        }
        List<KEdge> edgeList = new LinkedList<KEdge>();
        
        // transform matrix form
        int[][] m = graph.getMatrix();
        if (m != null) {
            for (int i = 0; i < graph.getRows(); i++) {
                for (int j = 0; j < graph.getColumns(); j++) {
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
        List<Matrix.Entry> list = graph.getList();
        for (Matrix.Entry entry : list) {
            if (entry.i <= nodec && entry.j <= nodec && entry.value != 0) {
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

        edges = edgeList.toArray(new KEdge[edgeList.size()]);
        return Lists.newArrayList(parent);
    }

    /**
     * {@inheritDoc}
     */
    public void applyLayout(final Matrix graph, final List<KNode> layoutGraphs) {
        List<KVectorChain> layout = graph.getLayout();
        for (KNode node : nodes) {
            KVectorChain chain = new KVectorChain();
            chain.add(node.getData(KShapeLayout.class).createVector());
            layout.add(chain);
        }
        for (KEdge edge : edges) {
            layout.add(edge.getData(KEdgeLayout.class).createVectorChain());
        }
    }

}
