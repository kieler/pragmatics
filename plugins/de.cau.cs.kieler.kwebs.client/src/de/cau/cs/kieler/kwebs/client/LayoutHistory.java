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

package de.cau.cs.kieler.kwebs.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.cau.cs.kieler.kwebs.Statistics;

/**
 * Container class for holding statistical data about layout operations.
 *
 * @author swe
 */
public class LayoutHistory {
    
    /** Singleton instance. */
    private static final LayoutHistory INSTANCE 
        = new LayoutHistory();
    
    /** The list of statistics ordered by time. */
    private List<Statistics> statistics
        = new ArrayList<Statistics>();
    
    /** How many statistics to remember. */
    private int history
        = 20;
    
    /**
     * Get the singleton instance.
     * 
     * @return the singleton instance
     */
    public static LayoutHistory getInstance() {
        return INSTANCE;
    }
    
    /**
     * 
     * @return
     */
    public List<Statistics> getStatistics() {
        return Collections.unmodifiableList(statistics);
    }
    
    /**
     * 
     * @param statistic
     */
    public synchronized void addStatistic(final Statistics statistic) { 
        if (statistic == null) {
            throw new IllegalArgumentException("Statistics can not be null");
        }
        statistics.add(statistic);
        if (statistics.size() > history) {
            statistics.remove(0);
        }
    }
    
    /**
     * Private constructor.
     */
    private LayoutHistory() {        
    }
    
}
