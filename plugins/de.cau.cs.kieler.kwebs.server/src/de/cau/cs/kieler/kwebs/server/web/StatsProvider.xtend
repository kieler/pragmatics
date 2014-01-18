/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.web

import de.cau.cs.kieler.kwebs.server.configuration.Configuration

/**
 * 
 */
class StatsProvider extends AbstractProvider {
    
    override getHeaders(ResourceProcessingExchange processingExchange) {
        
        var url = Configuration.INSTANCE.getConfigProperty(Configuration.STATS_SERVER_HTTP);
        if (!url.startsWith("http://")) {
            url = "http://" + url;
        }
        
        '''
        <!-- Stylesheets -->
        <link rel="stylesheet" href="scripts/styles/xcharts.min.css" />
        
        <!-- Js -->
        <script src="scripts/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="scripts/d3.v3.min.js" type="text/javascript"></script>
        <script src="scripts/xcharts.min.js" type="text/javascript"></script>
        <script src="scripts/kstats.js" type="text/javascript"></script>
        <script src="scripts/moment.min.js" type="text/javascript"></script>
        
        <style>
            #tt {
              background: none repeat scroll 0 0 #EEEEEE;
              border-collapse: separate;
              border-radius: 3px;
              box-shadow: 0 1px 3px #000000;
              display: none;
              padding: 5px;
              position: absolute;
            }
        </style>
        
        <script type="text/javascript">
            
            kstats.setUrl("«url»/stats");       
            
            kstats.createBarChart({
              collection: "de.cau.cs.kieler.kwebs",
              document: "kwebs.livelayout.try",
              unit: 'month',
              noOfUnits: 12,
              dateFormat: 'YYYY-MM',
              id: "liveLayoutMonthly"
            });
            
            kstats.createBarChart({
                collection: "de.cau.cs.kieler.kwebs",
                document: "kwebs.livelayout.try",
                unit: 'day',
                noOfUnits: 31,
                dateFormat: 'YYYY-MM-DD',
                id: "liveLayoutDaily"
            });
            
            kstats.createBarChart({
              collection: "de.cau.cs.kieler.kwebs",
              document: "kwebs.jaxws.try",
              unit: 'month',
              noOfUnits: 12,
              dateFormat: 'YYYY-MM',
              id: "jaxWSMonthly"
            });
            
            kstats.createBarChart({
                collection: "de.cau.cs.kieler.kwebs",
                document: "kwebs.jaxws.try",
                unit: 'day',
                noOfUnits: 31,
                dateFormat: 'YYYY-MM-DD',
                id: "jaxWSDaily"
            });
            
            kstats.createMultiBarChart({
                collection: "de.cau.cs.kieler.kwebs",
                document: "kwebs.layout.algorithm",
                key : "de.cau.cs.kieler.algorithm",
                unit: 'day',
                noOfUnits: 31,
                dateFormat: 'YYYY-MM-DD',
                id: "algorithmsDaily"
            });
        </script>
        '''
    }
    
    override getBody(ResourceProcessingExchange processingExchange) {
        '''
        <h1>LiveLayout - Monthly</h1>
        <figure style="width: 90%; height: 300px;" id="liveLayoutMonthly"></figure>
        <h1>LiveLayout - Daily</h1>
        <figure style="width: 90%; height: 300px;" id="liveLayoutDaily"></figure>
        <h1>JaxWS - Monthly</h1>
        <figure style="width: 90%; height: 300px;" id="jaxWSMonthly"></figure>
        <h1>JaxWS - Daily</h1>
        <figure style="width: 90%; height: 300px;" id="jaxWSDaily"></figure>
        <h1>Algorithms - Daily</h1>
        <figure style="width: 90%; height: 300px;" id="algorithmsDaily"></figure>
        <div id="tt"></div>
        '''
    }
    
    override providerOverride(ResourceProcessingExchange processingExchange) {
        return false
    }
       

}