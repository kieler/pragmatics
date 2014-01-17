/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.service;

import java.util.List;

import de.cau.cs.kieler.kwebs.server.layout.GraphLayoutOption;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.statistics.KIELERStatistics.Granularity;

/**
 * Dummy implementation that just forwards the layout request to the {@link AbstractService} class.
 * 
 * @author uru
 */
public class LiveLayoutService extends AbstractService {

    private static final String STATS_LIVE_LAYOUT_TRY = "kwebs.livelayout.try";
    private static final String STATS_LIVE_LAYOUT_SUCC = "kwebs.livelayout.success";
    
    /**
     * @see AbstractService#layout(String, String, String, List)
     * 
     * @param serializedGraph
     * @param informat
     * @param outformat
     * @param options
     * @return
     */
    public String doLayout(final String serializedGraph, final String informat,
            final String outformat, final List<GraphLayoutOption> options) {
        // log the layout attempt
        Logger.INSTANCE.getUsageStats().incCounter(Logger.STATS_KWEBS, STATS_LIVE_LAYOUT_TRY, 
                Granularity.DAY | Granularity.MONTH);
        // perform the actual layout
        String result =  layout(serializedGraph, informat, outformat, options);
        // log the success
        Logger.INSTANCE.getUsageStats().incCounter(Logger.STATS_KWEBS, STATS_LIVE_LAYOUT_SUCC, 
                Granularity.DAY | Granularity.MONTH);
        
        return result;
    }
};
