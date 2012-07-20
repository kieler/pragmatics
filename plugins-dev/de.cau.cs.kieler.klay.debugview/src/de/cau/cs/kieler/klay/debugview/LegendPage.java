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
package de.cau.cs.kieler.klay.debugview;

import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;

/**
 * Generates an HTML page that explains the node labels and the color keys used for dummy nodes.
 * 
 * @author cds
 */
public final class LegendPage {
    /**
     * The initial capacity of the string builder used to build the HTML color key text.
     */
    private static final int STRING_BUILDER_CAPACITY = 5000;
    
    /**
     * The (lazily) generated HTML text.
     */
    private static String htmlText = null;
    
    
    /**
     * This class doesn't need to be instantiated.
     */
    private LegendPage() {
        // Private constructor.
    }
    
    
    /**
     * Returns an HTML text that explains the drawings.
     * 
     * @param control a control used to find system fonts.
     * @return the HTML text.
     */
    public static String getColorKeyText(final Control control) {
        if (htmlText == null) {
            htmlText = generateHtmlText(control);
        }
        
        return htmlText;
    }
    
    /**
     * Generates the HTML text.
     * 
     * @param control a control used to find system fonts.
     * @return a String containing the HTML text.
     */
    private static String generateHtmlText(final Control control) {
        StringBuilder sb = new StringBuilder(STRING_BUILDER_CAPACITY);
        
        // Find the system font name
        String defaultFont = control.getDisplay().getSystemFont().getFontData()[0].getName();
        
        // Header
        sb.append("<html><head><style type='text/css'><!-- "); //$NON-NLS-1$
        sb.append("body {font-family:" + defaultFont //$NON-NLS-1$
                + ",Verdana,Arial; font-size:10pt; margin:10px;} "); //$NON-NLS-1$
        sb.append("h1 {font-size:14pt; font-weight:bold;} "); //$NON-NLS-1$
        sb.append("table {font-size: 10pt;} "); //$NON-NLS-1$
        sb.append("th {font-weight:bold; text-align:left;} "); //$NON-NLS-1$
        sb.append("p {text-align:justify;} "); //$NON-NLS-1$
        sb.append(".bordered {border:1px solid #666666;} "); //$NON-NLS-1$
        sb.append(" --></style></head><body>"); //$NON-NLS-1$
        
        // LEGEND
        sb.append("<h1>"); //$NON-NLS-1$
        sb.append(Messages.LegendPage_NodeLabels_Heading);
        sb.append("</h1><p>"); //$NON-NLS-1$
        sb.append(Messages.LegendPage_NodeLabels_Text);
        sb.append("</p>"); //$NON-NLS-1$
        sb.append("<pre>"); //$NON-NLS-1$
        sb.append(Messages.LegendPage_NodeLabels_Pattern);
        sb.append("</pre>"); //$NON-NLS-1$
        
        // COLOR KEYS
        sb.append("<h1>"); //$NON-NLS-1$
        sb.append(Messages.ColorKeyPage_Heading);
        sb.append("</h1><p>"); //$NON-NLS-1$
        sb.append(Messages.ColorKeyPage_IntroText);
        sb.append("</p>"); //$NON-NLS-1$
        
        // Table
        sb.append("<p><table cellspacing='4' width='100%'><tr><th>"); //$NON-NLS-1$
        sb.append(Messages.ColorKeyPage_NodeType);
        sb.append("</th><th>"); //$NON-NLS-1$
        sb.append(Messages.ColorKeyPage_Color);
        sb.append("</th></tr>"); //$NON-NLS-1$
        
        for (NodeType type : LGraph.NODE_TYPE_COLORS.keySet()) {
            sb.append("<tr><td>"); //$NON-NLS-1$
            sb.append(type.toString());
            sb.append("</td><td class='bordered' style='background-color:" //$NON-NLS-1$
                    + LGraph.NODE_TYPE_COLORS.get(type) + ";'>"); //$NON-NLS-1$
            sb.append("&nbsp;</td></tr>"); //$NON-NLS-1$
        }
        
        sb.append("</table></p>"); //$NON-NLS-1$
        
        // End
        sb.append("</body></html>"); //$NON-NLS-1$
        
        return sb.toString();
    }
}
