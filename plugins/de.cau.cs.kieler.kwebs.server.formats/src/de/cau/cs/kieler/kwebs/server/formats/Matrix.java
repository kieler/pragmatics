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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.math.KVectorChain;

/**
 * Internal data structure for matrices.
 *
 * @author msp
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
    private List<KVectorChain> layout = new LinkedList<KVectorChain>();
    
    /**
     * Creates a matrix of given size.
     * 
     * @param rows the number of rows
     * @param columns the number of columns
     */
    public Matrix(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
    }
    
    /**
     * Create the adjacency list.
     * 
     * @param nonzeros the number of non-zero entries in the matrix
     * @return the new adjacency list
     */
    public List<Entry> createList(final int nonzeros) {
        list = new ArrayList<Entry>(nonzeros);
        return list;
    }
    
    /**
     * Create the adjacency matrix.
     * 
     * @return the new adjacency matrix
     */
    public int[][] createMatrix() {
        matrix = new int[rows][columns];
        return matrix;
    }
    
    /**
     * Return the adjacency list.
     * 
     * @return the adjacency list, or {@code null}
     */
    public List<Entry> getList() {
        return list;
    }
    
    /**
     * Return the adjacency matrix.
     * 
     * @return the adjacency matrix, or {@code null}
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
     * @return the layout specification
     */
    public List<KVectorChain> getLayout() {
        return layout;
    }

}
