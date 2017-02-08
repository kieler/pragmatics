/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.kiml.formats.IGraphFormatHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;
import de.cau.cs.kieler.kiml.formats.TransformationException;

/**
 * A handler for matrices as defined by Matrix Market.
 * See {@link http://math.nist.gov/MatrixMarket/}.
 * <p>
 * The handler supports three format types:
 * <ul>
 *   <li><b>Coordinate format:</b> Each edge is given explicitly with source and target node index.</li>
 *   <li><b>Array format:</b> The full adjacency matrix is written column-wise.</li>
 *   <li><b>Layout format:</b> Does not specify the graph structure, but only its layout.
 *     The first part contains coordinates for all nodes sorted by their index, while the
 *     second part contains bend points for all edges.</li>
 * </ul>
 * The coordinate and array formats are supported for import and export, while the layout format
 * is only supported for export. The default format for graph serialization is the layout format.
 * This can be overridden using the {@link de.cau.cs.kieler.kiml.options.LayoutOptions#NO_LAYOUT}
 * option: if defined, the output format is either coordinate or array, depending on the number
 * of edges in the graph.
 * </p>
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class MatrixFormatHandler implements IGraphFormatHandler<Matrix> {
    
    // CHECKSTYLEOFF MagicNumber

    /**
     * {@inheritDoc}
     */
    public void deserialize(final String serializedGraph,
            final TransformationData<Matrix, ElkNode> transData) {
        int linenr = 0;
        try {
            BufferedReader reader = new BufferedReader(new StringReader(serializedGraph));
            // ignore initial comment lines
            String line = null;
            do {
                line = reader.readLine();
                linenr++;
            } while (line != null && (line.startsWith("%") || line.length() == 0));
            
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
                        } while (line != null && (line.startsWith("%") || line.length() == 0));
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
                
                transData.setSourceGraph(matrix);
            } else {
                transData.setSourceGraph(new Matrix(0, 0));
            }
        } catch (NumberFormatException exception) {
            throw new TransformationException(
                    "Parse error while reading number on input line " + linenr, exception);
        } catch (IOException exception) {
            // this should not happen
            throw new TransformationException(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String serialize(final TransformationData<ElkNode, Matrix> transData) {
        StringBuilder builder = new StringBuilder();
        for (Matrix matrix : transData.getTargetGraphs()) {
            if (matrix.getLayout() == null) {
                if (matrix.getList() != null) {
                    // serialize the graph in coordinate format
                    builder.append("%%MatrixMarket matrix coordinate pattern general\n");
                    builder.append(matrix.getRows()).append(' ').append(matrix.getColumns());
                    builder.append(' ').append(matrix.getList().size()).append('\n');
                    for (Matrix.Entry entry : matrix.getList()) {
                        builder.append(entry.i + 1).append(' ').append(entry.j + 1).append('\n');
                    }
                } else if (matrix.getMatrix() != null) {
                    // serialize the graph in array format
                    builder.append("%%MatrixMarket matrix array integer general\n");
                    builder.append(matrix.getRows()).append(' ').append(matrix.getColumns());
                    builder.append('\n');
                    int[][] m = matrix.getMatrix();
                    for (int j = 0; j < matrix.getColumns(); j++) {
                        for (int i = 0; i < matrix.getRows(); i++) {
                            builder.append(m[i][j]).append('\n');
                        }
                    }
                }
            } else {
                // serialize the layout of the graph
                builder.append("%%KIELER matrix layout\n");
                for (KVectorChain chain : matrix.getLayout()) {
                    for (KVector vector : chain) {
                        builder.append(toString(vector.x)).append(' ');
                        builder.append(toString(vector.y)).append(' ');
                    }
                    builder.append('\n');
                }
            }
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
            return s.substring(0, dotIndex);
        }
        return s;
    }

    private MatrixImporter importer = new MatrixImporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<Matrix, ElkNode> getImporter() {
        return importer;
    }
    
    private MatrixExporter exporter = new MatrixExporter();

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<ElkNode, Matrix> getExporter() {
        return exporter;
    }

}
