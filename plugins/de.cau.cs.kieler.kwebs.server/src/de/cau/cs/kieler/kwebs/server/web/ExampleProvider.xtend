/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.web

import com.google.common.base.Charsets
import com.google.common.io.Resources

/**
 * @author uru
 */
class ExampleProvider extends AbstractProvider {
     
    override getHeaders(ResourceProcessingExchange processingExchange) {
        '''
        <link href="styles/bootstrap-3.0.2.min.css" rel="stylesheet">
        <link href="styles/prettify.css" type="text/css" rel="stylesheet" />
        
        <script src="scripts/jquery-1.10.2.min.js"></script>
        <script src="scripts/bootstrap-3.0.2.min.js"></script>
        <script src="scripts/prettify.js"></script>
        '''
    }
    
    override getBody(ResourceProcessingExchange processingExchange) {
        
        //val text = ""
        '''
          <div class="col-md-8 col-md-offset-2">
            <h1>The JSON Graph Format</h1>
            The json graph format comprises of four basic elements - Nodes, Ports, Labels, and Edges. 
            <ul>
              <li>Each element has an 'id' that identifies it uniquely.</li> 
              <li>The first three elements can hold a position and dimension.</li> 
              <li>Edges on the contrary can hold bend points specifying where the edge changes direction.</li> 
              <li>Nodes can contain child nodes and hold ports that specify attachment points of edges.</li> 
              <li>Multiple edges can be attached to the same port, the port can be attached to the node itself.</li> 
              <li>All elements can hold labels (despite the label itself).</li> 
              <li>All elements can hold properties which represent additional information to the layout algorithm.</li> 
            </ul>
            
            <h2>Examples</h2>
            Below are some example graphs in the JSON graph format. You can hop to the <a href="Live.html">Live</a> 
            section and try them! If you use SVG as output format the graph will be rendered as an SVG image.
             
            <h3>Small graph with one edge</h3>
            <pre class="prettyprint lang-javascript">
                «loadExample("g1.json")»
            </pre>
            
            <h3>Small graph with a port and hierarchy</h3>
            <pre class="prettyprint lang-javascript">
                «loadExample("g2.json")»
            </pre>
          </div>
        '''
    }
    
    override providerOverride(ResourceProcessingExchange processingExchange) {
        false
    }
    
    def loadExample(String fileName) {
        try {
            val url = Resources.getResource(this.getClass, "/de/cau/cs/kieler/kwebs/server/web/examples/" + fileName)
            val text = Resources.toString(url, Charsets.UTF_8)
            return text
        } catch (Exception e) {
           // silent 
           return ""
        }
    }
    
}