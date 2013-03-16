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

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;

import de.cau.cs.kieler.kiml.grana.visualization.BoundVisualization;
import de.cau.cs.kieler.kiml.service.AnalysisService;

/**
 * A utility class for generating html from a set of analysis results.
 * 
 * @author mri
 * @author cds
 * @kieler.ignore (excluded from review process)
 */
public final class HtmlResultGenerator {

    /**
     * Private constructor to make this class a static utility class.
     */
    private HtmlResultGenerator() {
        // nothing
    }

    /**
     * Generates html for the given analyses and results using the registered visualizers.
     * 
     * @param boundVisualizations
     *            the visualizations, sorted by category
     * @return the generated html, or null if no analysis could be visualized
     */
    public static String generate(final List<BoundVisualization> boundVisualizations) {
        String currentCategory = "";

        // build the html content
        boolean empty = true;
        boolean first = true;
        int rowIndex = 0;
        StringBuilder html = new StringBuilder("<HTML><HEAD>")
            .append(generateCSS())
            .append("</HEAD><BODY>");
        for (BoundVisualization visualization : boundVisualizations) {
            // Check if this visualization starts a new category
            if (!visualization.getAnalysis().getCategory().equals(currentCategory)) {
                currentCategory = visualization.getAnalysis().getCategory();

                if (first) {
                    first = false;
                } else {
                    html.append("</TABLE>");
                }

                html.append("<H1>")
                        .append(AnalysisService.getInstance().getCategory(currentCategory).getName())
                        .append("</H1>")
                        .append("<TABLE cellspacing='0' cellpadding='6'>")
                        .append("<TR CLASS='header'><TH>Analysis</TH><TH>Result</TH></TR>");
                
                rowIndex = 0;
            }

            empty = false;
            html.append("<TR class='" + (rowIndex % 2 == 0 ? "even" : "odd") + "'>")
                    .append("<TD VALIGN='TOP' CLASS='analysisName'>")
                    .append(visualization.getAnalysis().getName())
                    .append("</TD><TD VALIGN='TOP'>")
                    .append(visualization.get())
                    .append("</TD></TR>");
            
            rowIndex++;
        }
        html.append("</TABLE></BODY></HTML>");

        if (empty) {
            return null;
        } else {
            return html.toString();
        }
    }

    // TODO this should be customizable through the preference pages
    
    /**
     * Generates the CSS code necessary for the HTML output to look good.
     * 
     * @return CSS code.
     */
    private static String generateCSS() {
        // Get the standard dialog font and the font used for headings
        FontData headerFont = JFaceResources.getHeaderFont().getFontData()[0];
        boolean boldHeaderFont = (headerFont.getStyle() & SWT.BOLD) != 0;
        FontData dialogFont = JFaceResources.getDialogFont().getFontData()[0];
        
        StringBuilder css = new StringBuilder("<style TYPE='text/css'><!--")
            .append("body, table { font-family: \"" + dialogFont.getName() + "\";")
            .append("              font-size: " + dialogFont.getHeight() + "pt; }")
            .append("h1 { font-family: \"" + headerFont.getName() + "\";")
            .append("     font-size: " + headerFont.getHeight() + "pt;")
            .append("     font-weight: " + (boldHeaderFont ? "bold" : "normal") + "; }")
            .append("table { border-bottom: 1pt solid #aaaaaa;")
            .append("        margin-left: 20pt; margin-bottom: 20pt; }")
            .append("th { background: #fafafa;")
            .append("     border-bottom: 2pt solid #aaaaaa; border-top: 2pt solid #aaaaaa;")
            .append("     font-weight: bold; text-align: left; }")
            .append("td { border-bottom: 1pt solid #aaaaaa; }")
            .append(".analysisName { font-weight: normal; }")
            .append(".even { background: white; }")
            .append(".odd { background: white; }")
            .append("--></style>");
        
        return css.toString();
    }
}
