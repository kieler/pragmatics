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
package de.cau.cs.kieler.formats.matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.math.KVectorChain;

import de.cau.cs.kieler.formats.TransformationException;

/**
 * Internal data structure for matrices.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class Matrix {

    /** Entry class for coordinate format. */
    public static class Entry {
        // CHECKSTYLEOFF VisibilityModifier
        /** row number. */
        public int i;
        /** column number. */
        public int j;
        /** the value (1 for edge i&rarr;j, -1 for edge j&rarr;i). */
        public int value;
        // CHECKSTYLEON VisibilityModifier
        
        /**
         * Create a new entry.
         * 
         * @param i row number (0-based)
         * @param j column number (0-based)
         * @param value the value (1 for edge i&rarr;j, -1 for edge j&rarr;i)
         */
        public Entry(final int i, final int j, final int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }
    
    /** the number of rows. */
    private int rows;
    /** the number of columns. */
    private int columns;
    /** the adjacency matrix, or {@code null}. */
    private int[][] matrix;
    /** the adjacency list, or {@code null}. */
    private List<Entry> list;
    /** the layout specification. */
    private List<KVectorChain> layout;
    
    /**
     * Creates a matrix of given size.
     * 
     * @param rows the number of rows
     * @param columns the number of columns
     */
    public Matrix(final int rows, final int columns) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("The number of rows and columns must not be negative.");
        }
        this.rows = rows;
        this.columns = columns;
    }
    
    /** the maximal number of entries in an adjacency list. */
    public static final int MAX_LIST_SIZE = 6553600;
    
    /**
     * Create the adjacency list.
     * 
     * @param nonzeros the number of non-zero entries in the matrix
     * @return the new adjacency list
     */
    public List<Entry> createList(final int nonzeros) {
        if (nonzeros > MAX_LIST_SIZE) {
            throw new TransformationException("The matrix exceeds the maximal number of"
                    + " non-zero entries (max. " + MAX_LIST_SIZE + ", here " + nonzeros + ").");
        }
        list = new ArrayList<Entry>(nonzeros);
        return list;
    }
    
    /** the maximal number of elements in an adjacency matrix. */
    public static final long MAX_MATRIX_SIZE = 26214400L;
    
    /**
     * Create the adjacency matrix.
     * 
     * @return the new adjacency matrix
     */
    public int[][] createMatrix() {
        if ((long) rows * columns > MAX_MATRIX_SIZE) {
            throw new TransformationException("The matrix exceeds the maximal number of elements (max. "
                    + MAX_MATRIX_SIZE + ", here " + ((long) rows * columns) + ").");
        }
        matrix = new int[rows][columns];
        return matrix;
    }
    
    /**
     * Create the layout specification list.
     * 
     * @return the layout specification list
     */
    public List<KVectorChain> createLayout() {
        layout = new LinkedList<KVectorChain>();
        return layout;
    }
    
    /**
     * Return the adjacency list.
     * 
     * @return the adjacency list, or {@code null} if the matrix is not stored in coordinate format
     */
    public List<Entry> getList() {
        return list;
    }
    
    /**
     * Return the adjacency matrix.
     * 
     * @return the adjacency matrix, or {@code null} if the matrix is not stored in array format
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Return the number of rows.
     * 
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Return the number of columns.
     * 
     * @return the number of columns
     */
    public int getColumns() {
        return columns;
    }
    
    /**
     * Return the layout specification, that is a list of vector chains. The first elements
     * contain only one vector for the node positions, and the last elements contain the
     * coordinates for edge line segments.
     * 
     * @return the layout specification, or {@code null} if no layout was set for the matrix.
     */
    public List<KVectorChain> getLayout() {
        return layout;
    }

}
