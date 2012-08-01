/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.intermediate;

import de.cau.cs.kieler.klay.planar.graph.PNode;

/**
 * Represents the pgraph elements as a grid. The entries here are the graph nodes which are
 * connected by the graph edges.
 * 
 * @author pkl
 */
public class GridRepresentation {

    private PNode[][] grid;

    private final int height;

    private final int width;

    /**
     * Generates a new grid with given width and height.
     * 
     * @param width
     *            of the grid.
     * @param height
     *            of the grid.
     */
    public GridRepresentation(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.grid = new PNode[width][height];
    }

    /**
     * Removes a node of the grid.
     * 
     * @param node
     *            , the node to remove from grid.
     */
    public void remove(final PNode node) {
        int[] coordinates = search(node);
        grid[coordinates[0]][coordinates[1]] = null;
    }

    /**
     * Gives the entry of the grid for a position.
     * 
     * @param x
     *            position of the entry in the grid.
     * @param y
     *            position of the entry in the grid.
     * @return the found PNode.
     */
    public PNode get(final int x, final int y) {
        return this.grid[x][y];
    }

    /**
     * Sets a node at the x and y position of the grid.
     * 
     * @param x
     *            coordinate of the position.
     * @param y
     *            coordinate of the position.
     * @param node
     *            {@link PNode}
     */
    public void set(final int x, final int y, final PNode node) {
        this.grid[x][y] = node;
    }

    /**
     * Searches in the grid for the given node and gives its position in the grid!
     * 
     * @throws IllegalArgumentException
     *             if no entry is found.
     * @param node
     *            , the search element.
     * @return the grid position of the node, first element is the x coordinate, second is the y
     *         coordinate.
     */
    public int[] search(final PNode node) {
        int[] result = { 0, 0 };
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (grid[x][y] == node) {
                    result[0] = x;
                    result[1] = y;
                    return result;
                }
            }
        }
        throw new IllegalArgumentException("GridRepresentation: the searched node does not exist!");
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

}
