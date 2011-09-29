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

package de.cau.cs.kieler.kwebs.client.kiml.ui;

import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.servicedata.SupportedFormat;

/**
 * Utility class for generating HTML content for the server details dialog.
 *
 * @kieler.rating 2011-05-14
 * 
 * @author swe
 */
public final class ServerDetailsPage {

    /** */
    private static final String HTML_PREFIX
        = "<html>\n"
          + "<head>\n"
          + "<style type='text/css'>\n"
          + "<!--\n"
          + "body { font-family : Verdana, Arial; font-size : 8pt; }\n"
          + ".lightgrey { background-color : #efefef; }"
          + "p { font-family : Verdana, Arial; font-size : 8pt; margin : 10pt; }\n"
          + "table { border-width : 2px; border-style : ridge; border-color : #000000; table-layout : fixed; }\n"
          + "td { font-family : Verdana, Arial; font-size : 8pt; border-tyle : none; text-align : left; }\n"
          + "th { font-family : Verdana, Arial; font-size : 8pt; font-weight : bold; text-align : left; border-bottom: 1px solid; }\n"
          + "thead:first-child tr { border: 1px solid; }\n"
          + ".title { font-size : 10pt; font-weight : bold; }\n"
          + "//-->\n"
          + "</style>\n"
          + "<script type='text/javascript'>\n"
          + "<!--\n"
          + "function doHover(id) { doMouse(id, \"#a5F3a5\", \"pointer\"); }\n"
          + "function doUnhover(id) { var index = parseInt(id); var color = (index % 2 == 0 ? \"#efefef\" : \"#ffffff\"); doMouse(id, color, \"default\"); }\n"
          + "function doMouse(id, color, mouse) {\n"
          + "if (id == null || color == null || mouse == null) {\n"
          + "return;\n"
          + "}\n"
          + "var element = document.getElementById(id);\n"
          + "if (element == null) {\n"
          + "return;\n"
          + "}\n"
          + "element.style.backgroundColor = color;\n"
          + "document.body.style.cursor = mouse;\n"
          + "}\n"
          + "function doSwitch(id) {\n"
          + "if (id == null) {\n"
          + "return;\n"
          + "}\n"
          + "var element = document.getElementById(id);\n"
          + "if (element == null) {\n"
          + "return;\n"
          + "}\n"
          + "if (element.style.visibility == \"visible\" || element.style.visibility == \"\") {\n"
          + "element.style.visibility = \"hidden\";\n"
          + "element.style.display = \"none\";\n"
          + "} else {\n"
          + "element.style.visibility = \"visible\";\n"
          + "element.style.display = \"block\";\n"
          + "}\n"
          + "}\n"
          + "//-->\n"
          + "</script>\n"
          + "</head>\n"
          + "<body>\n";
    
    private static final String HTML_POSTFIX
        = "</body>\n"
          + "</html>";
    
    /**
     * 
     * @param serviceData
     * @return
     */
    public static String generateHtml(final ServiceData serviceData, final ILayoutServiceClient client) {      
        int scriptIndex = 0;
        StringBuffer sb = new StringBuffer();
        
        sb.append(HTML_PREFIX);
        
        sb.append("<p class='title'>Service Details</p>\n");
        
        sb.append("<p>\n");
        sb.append("Name: " + client.getServerConfig().getName() + "<br/>\n");
        sb.append("Address: " + client.getServerConfig().getAddress() + "<br/>\n");
        sb.append("Version: " + serviceData.getVersion() + "<br/>\n");
        sb.append("</p>\n");
        
        sb.append("<p class='title'>Supported Algorithms</p>\n");

        sb.append("<p>\n");        
        sb.append("<table cellspacing='0' cellpadding='5'>\n");        
        sb.append("<thead><tr><th>Name</th><th>Category</th><th>Type</th><th>Version</th></th></thead>\n");
        sb.append("<tbody>\n");        
        for (LayoutAlgorithm algorithm : serviceData.getLayoutAlgorithms()) {
            String category = (algorithm.getCategory() != null ? algorithm.getCategory().getName() : null);
            String type = (algorithm.getType() != null ? algorithm.getType().getName() : null);
            String version = algorithm.getVersion();
            if (category == null || category.length() == 0) {
                category = "&nbsp;";
            }
            if (type == null || type.length() == 0) {
                type = "&nbsp;";
            }
            if (version == null || version.length() == 0) {
                version = "&nbsp;";
            }
            sb.append(
                "<tr"
                + (scriptIndex % 2 == 0 ? " class='lightgrey'" : "" )
                + " id='" 
                + scriptIndex + "short' onmouseover='doHover(\"" 
                + scriptIndex + "short\");' onmouseout='doUnhover(\"" 
                + scriptIndex + "short\");' onclick='doSwitch(\"" 
                + scriptIndex + "detail\");'>"
                + "<td>" + algorithm.getName() + "</td>" 
                + "<td>" + category + "</td>"
                + "<td>" + type + "</td>"
                + "<td>" + version + "</td>"
                + "</tr>\n"
                + "<tr"
                + (scriptIndex % 2 == 0 ? " class='lightgrey'" : "" )
                + " id='" + scriptIndex + "detail' style='visibility:hidden;display:none;'>\n"
                + "<td colspan='4' style='text-align:justify;'>\n"
                + algorithm.getDescription()
                + "</td>\n"
                + "</tr>\n"
            );
            scriptIndex++;
        }        
        sb.append("</tbody>\n");
        sb.append("</table>\n");
        sb.append("</p>\n");

        sb.append("<p class='title'>Supported Formats</p>\n");

        sb.append("<p>\n");        
        sb.append("<table cellspacing='0' cellpadding='5'>\n");        
        sb.append("<thead><tr><th>Name</th><th>Identifier</th></tr></thead>\n");
        sb.append("<tbody>\n");
        
        scriptIndex *= 2; // Needs to be even on the beginning of a table

        for (SupportedFormat format : serviceData.getSupportedFormats()) {
            sb.append(
                "<tr"
                + (scriptIndex % 2 == 0 ? " class='lightgrey'" : "" )
                + " id='" 
                + scriptIndex + "short' onmouseover='doHover(\"" 
                + scriptIndex + "short\");' onmouseout='doUnhover(\"" 
                + scriptIndex + "short\");' onclick='doSwitch(\"" 
                + scriptIndex + "detail\");'>"
                + "<td>" + format.getName()  + "</td>" 
                + "<td>" + format.getId()  + "</td>"
                + "</tr>\n"
                + "<tr"
                + (scriptIndex % 2 == 0 ? " class='lightgrey'" : "" )
                + " id='" + scriptIndex + "detail' style='visibility:hidden;display:none;'>\n"
                + "<td colspan='2' style='text-align:justify;'>\n"
                + format.getDescription()
                + "</td>\n"
                + "</tr>\n"
            );
            scriptIndex++;
        }

        sb.append("</tbody>\n");
        sb.append("</table>\n");
        sb.append("</p>\n");
        
        sb.append(HTML_POSTFIX);

        return sb.toString();
    }

}
