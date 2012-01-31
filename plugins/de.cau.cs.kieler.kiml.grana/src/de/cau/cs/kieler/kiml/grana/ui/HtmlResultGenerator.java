/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.grana.ui;

import java.util.List;

import de.cau.cs.kieler.kiml.grana.visualization.BoundVisualization;
import de.cau.cs.kieler.kiml.service.AnalysisService;


/**
 * A utility class for generating html from a set of analysis results.
 * 
 * @author mri
 * @author cds
 */
public final class HtmlResultGenerator {

    /**
     * Private constructor to make this class a static utility class.
     */
    private HtmlResultGenerator() {
        // nothing
    }

    /**
     * Generates html for the given analyses and results using the registered
     * visualizers.
     * 
     * @param boundVisualizations
     *            the visualizations, sorted by category
     * @return the generated html, or null if no analysis could be visualized
     */
    public static String generate(
            final List<BoundVisualization> boundVisualizations) {

        String currentCategory = "";
        
        // build the html content
        boolean empty = true;
        boolean first = true;
        String html =
                "<HTML><HEAD></HEAD><BODY>";
        for (BoundVisualization visualization : boundVisualizations) {
            // Check if this visualization starts a new category
            if (!visualization.getAnalysis().getCategory().equals(currentCategory)) {
                currentCategory = visualization.getAnalysis().getCategory();
                
                if (first) {
                    first = false;
                } else {
                    html += "</TABLE>";
                }
                
                html += "<H2>"
                    + AnalysisService.getInstance().getCategoryById(currentCategory).getName()
                    + "</H2>";
                html += "<TABLE border=0 cellpadding='10'>";
            }
            
            empty = false;
            html += "<TR><TD VALIGN='TOP'><b>";
            html += visualization.getAnalysis().getName();
            html += "</b></TD><TD VALIGN='TOP'>";
            html += visualization.get();
            html += "</TD></TR>";
        }
        html += "</TABLE></BODY></HTML>";

        if (empty) {
            return null;
        } else {
            return html;
        }
    }

    // TODO this should be customizable through the preference pages
}
