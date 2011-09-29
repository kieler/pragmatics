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

package de.cau.cs.kieler.kwebs.client.kiml;

import java.text.NumberFormat;

import de.cau.cs.kieler.kwebs.Statistics;

/**
 * Utility class for generating HTML representation of the collected statistics.
 *
 * @kieler.rating 2011-05-14
 * 
 * @author swe
 */
public final class LayoutHistoryPage {
    
    /** */
    private static final String HTML_PREFIX
        = "<html>"
        + "<head>"
        + "<style type='text/css'>"
        + "<!--"
        + "body { margin : 20px; }"
        + "table.chart { border-style : solid; border-width : 0px 0px 1px 1px; border-color : #000000; }"
        + "td.bar { width : 40px;}"
        + "td.spacer { width : 20px; }"
        + ".centeredtext { font-family : Sans-Serif; font-size : 7pt; text-align : center; padding-bottom : 5px;}"
        + ".lefttext { font-family : Sans-Serif; font-size : 7pt; text-align : left; padding-bottom : 5px;}"
        + ".red { background-color : #f00000; }"
        + ".green { background-color : #00f000; }"
        + ".blue { background-color : #0000f0; }"
        + ".yellow { background-color : #f0f000; }"
        + ".cyan { background-color : #00f0f0; }"
        + ".magenta { background-color : #f000f0; }"
        + "//-->"
        + "</style>"
        + "</head>"
        + "<body>"
        + "<table class='chart' cellspacing='0' cellpadding='0' height='230px'>";

    /** */
    private static final String HTML_POSTFIX
        = "</table>"
        + "<p class='lefttext'>"
        + "All layout durations are normalized to 100%. The colored parts represent the percentage of the layout time the according component of the layout process did take."
        + "</p>"
        + "<p>"
        + "<table cellspacing='0' cellpadding='0'>"
        + "<td class='green' width='50px'></td><td class='lefttext'>&nbsp;&nbsp;&nbsp;Layout&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
        + "<tr>"
        + "</tr>"
        + "<td class='blue' width='50px'></td><td class='lefttext'>&nbsp;&nbsp;&nbsp;Network transfer</td>"
        + "</tr>"
        + "<tr>"
        + "<td class='red' width='50px'></td><td class='lefttext'>&nbsp;&nbsp;&nbsp;Remote supplementary operations&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
        + "<tr>"
        + "</tr>"
        + "<tr>"
        + "<td class='yellow' width='50px'></td><td class='lefttext'>&nbsp;&nbsp;&nbsp;Local supplementary operations&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
        + "<tr>"
        + "</tr>"
        + "</table>"
        + "</p>"
        + "</body>"
        + "</html>";
    
    /**
     * 
     * @return
     */
    public static String generateHtml() {
        StringBuffer html = new StringBuffer();
        StringBuffer time = new StringBuffer();
        StringBuffer elements = new StringBuffer();
        StringBuffer optional = new StringBuffer();
        StringBuffer bars = new StringBuffer();
        
        html.append(HTML_PREFIX);
        
        for (Statistics statistics : LayoutHistory.getInstance().getStatistics()) {
            createTimeEntry(statistics, time);
            createElementEntry(statistics, elements);
            createOptionalEntry(statistics, optional);
            createBarEntry(statistics, bars);
        }
        
        html.append("<tr height='10px'>");
        html.append("<td class='text'></td>");
        html.append(time);
        html.append("<td class='text'></td>");
        html.append("</tr>");

        html.append("<tr height='10px'>");
        html.append("<td class='text'></td>");
        html.append(elements);
        html.append("<td class='text'></td>");
        html.append("</tr>");

        html.append("<tr height='10px'>");
        html.append("<td class='text'></td>");
        html.append(optional);
        html.append("<td class='text'></td>");
        html.append("</tr>");
        
        html.append("<tr height='150px'>");
        html.append("<td class='spacer'></td>");
        html.append(bars);
        html.append("<td class='spacer'></td>");
        html.append("</tr>");
        
        html.append(HTML_POSTFIX);
        
        return html.toString();
    }

    /** Constant for calculating seconds out of nano seconds. */
    private static final double FACTOR_NANOTOSECONDS 
        = 1e-9;

    /** Formatter for double values. */
    private static final NumberFormat numberFormat
        = NumberFormat.getInstance();
    
    /**
     * 
     * @param statistics
     * @param time
     */
    private static void createTimeEntry(final Statistics statistics, final StringBuffer time) {
        time.append(
            "<td class='centeredtext' colspan='3' align='middle'>"
            + numberFormat.format(statistics.getTimeTotal() * FACTOR_NANOTOSECONDS) + " sec"
            + "</td>"
        );
    }

    /**
     * 
     * @param statistics
     * @param elements
     */
    private static void createElementEntry(final Statistics statistics, final StringBuffer elements) {
        elements.append(
            "<td class='centeredtext' colspan='3' align='middle'>"
            + statistics.getElementCount() + " elements"
            + "</td>"
        );        
    }

    /**
     * 
     * @param statistics
     * @param optional
     */
    private static void createOptionalEntry(final Statistics statistics, final StringBuffer optional) {
        optional.append(
            "<td class='centeredtext' colspan='3' align='middle'>"
            + statistics.getBytes() + " bytes"
            + (statistics.isCompression() ? " (C)" : "")
            + "</td>"
        );        
    }

    /**
     * 
     * @param statistics
     * @param bars
     */
    private static void createBarEntry(final Statistics statistics, final StringBuffer bars) {
        double localPart = statistics.getLocalSupplementalPart();
        double remotePart = statistics.getRemoteSupplementalPart();
        double layoutPart = statistics.getLayoutPart();
        double networkPart = statistics.getNetworkPart();
        
        bars.append(
            "<td class='spacer'></td>"
            + "<td style='vertical-align:bottom;'>"
            + "<table cellspacing='0' cellpadding='0' height='100%'>"
            + "<tr class='green' style='height:" + layoutPart + "%;'><td class='bar'></td></tr>"
            + "<tr class='blue' style='height:" + networkPart + "%;'><td class='bar'></td></tr>"
            + "<tr class='red' style='height:" + remotePart + "%;'><td class='bar'></td></tr>"
            + "<tr class='yellow' style='height:" + localPart + "%;'><td class='bar'></td></tr>"
            + "</table>"
            + "</td>"
            + "<td class='spacer'></td>"
        );
    }

    /**
     * Private constructor.
     */
    private LayoutHistoryPage() {        
    }
    
}
