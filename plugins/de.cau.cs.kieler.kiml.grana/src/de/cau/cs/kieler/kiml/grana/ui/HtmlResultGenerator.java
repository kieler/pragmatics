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
import java.util.Map;

import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisServices;

/**
 * A utility class for generating html from a set of analysis results.
 * 
 * @author mri
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
     * @param analyses
     *            the analyses
     * @param results
     *            the results for the analyses
     * @return the generated html, or null if no analysis could be visualized
     */
    public static String generate(final List<AbstractInfoAnalysis> analyses,
            final Map<String, Object> results) {

        // build the html content
        boolean empty = true;
        String html =
                "<HTML><HEAD></HEAD><BODY><TABLE border=0 cellpadding='10'>";
        for (AbstractInfoAnalysis analysis : analyses) {
            Object obj = results.get(analysis.getID());
            String analysisHtml =
                    AnalysisServices.getInstance().visualizeResult(obj);
            if (analysisHtml != null) {
                empty = false;
                html += "<TR><TD><b>";
                html += analysis.getName();
                html += "</b></TD><TD>";
                html += analysisHtml;
                html += "</TD></TR>";
            }
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
