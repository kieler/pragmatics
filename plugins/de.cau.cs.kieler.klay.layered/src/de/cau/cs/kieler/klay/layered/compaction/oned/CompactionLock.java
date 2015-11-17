/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.compaction.oned;

import de.cau.cs.kieler.kiml.options.Direction;

/**
 * Internal class representing a 4 tuple that states for a {@link CNode} if the compaction should
 * be locked in a particular direction.
 * 
 * @author dag
 */
public final class CompactionLock {
    /** directional locks. */
    private boolean left, right, up, down;
    
    /**
     * The lock defaults to false.
     */
    public CompactionLock() {
        set(false, false, false, false);
    }
    
    /**
     * This constructor initializes the lock.
     * 
     * @param l left
     * @param r right
     * @param u up
     * @param d down
     */
    public CompactionLock(final boolean l, final boolean r, final boolean u, final boolean d) {
        set(l, r, u, d);
    }
    
    /**
     * Sets the lock.
     * 
     * @param l left
     * @param r right
     * @param u up
     * @param d down
     */
    public void set(final boolean l, final boolean r, final boolean u, final boolean d) {
        left = l;
        right = r;
        up = u;
        down = d;
    }
    
    /**
     * Sets the lock in a specific {@link Direction}.
     * 
     * @param value 
     *          the desired state
     * @param direction the {@link Direction} of compaction
     */
    public void set(final boolean value, final Direction direction) {
        switch (direction) {
        case LEFT:
            left = value;
            break;
            
        case RIGHT:
            right = value;
            break;
            
        case UP:
            up = value;
            break;
            
        case DOWN:
            down = value;
            break;

        default:
            break;
        }
    }
    
    /**
     * Returns the state for a {@link Direction}.
     * 
     * @param direction
     *          the {@link Direction}
     * @return the state
     */
    public boolean get(final Direction direction) {
        switch (direction) {
        case LEFT:
            return left;
            
        case RIGHT:
            return right;
            
        case UP:
            return up;
            
        case DOWN:
            return down;

        default:
            return false;
        }
    }
}
