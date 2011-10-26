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

package de.cau.cs.kieler.kwebs.server.web;

import java.util.Map;

import com.sun.net.httpserver.Headers;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService;
import de.cau.cs.kieler.kwebs.servicedata.KnownOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.servicedata.LayoutOption;
import de.cau.cs.kieler.kwebs.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.servicedata.SupportedFormat;
import de.cau.cs.kieler.kwebs.util.Resources;

/**
 * This class implements a web content provider for displaying the service meta data in HTML format.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class ServicedataProvider implements IDynamicWebContentProvider {

    //CHECKSTYLEOFF LineLength
    
    /** Prefix of the HTML content. */
    private static final String HTML_PREFIX
        = "<html>\n"
          + "<head>\n"
          + "<style type='text/css'>\n"
          + "<!--\n"
          + "body { font-family : Verdana, Arial; font-size : 8pt; }\n"
          + ".lightgrey { background-color : #efefef; }"
          + ".title { font-size : 10pt; font-weight : bold; }\n"
          + "p { font-family : Verdana, Arial; font-size : 8pt; margin : 10pt; }\n"
          + "table { border-width : 2px; border-style : ridge; border-color : #000000; table-layout : fixed; }\n"
          + "td { font-family : Verdana, Arial; font-size : 8pt; border-tyle : none; text-align : left; }\n"
          + "th { font-family : Verdana, Arial; font-size : 8pt; font-weight : bold; text-align : left; border-bottom: 1px solid; }\n"
          + "thead:first-child tr { border: 1px solid; }\n"
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
          + "//-->\n"
          + "</script>\n"
          + "</head>\n"
          + "<body>\n";
    
    /** Postfix of the HTML content. */
    private static final String HTML_POSTFIX
        = "</body>\n"
          + "</html>";

    //CHECKSTYLEON LineLength

    /** Constant for query parameter 'algorithm'. */
    private static final String PARAM_ALGORITHM 
        = "algorithm";

    /** Constant for query parameter 'option'. */
    private static final String PARAM_OPTION
        = "option";

    /** Constant for query parameter 'format'. */
    private static final String PARAM_FORMAT 
        = "format";

    /** Constant for query parameter 'previewimage'. */
    private static final String PARAM_PREVIEWIMAGE 
        = "previewimage";

     /** Caching the service data model. */
     private ServiceData serviceData 
         = ServerLayoutDataService.getInstance().getServiceDataModel();
     
    /**
     * Handles a HTTP request related to the layout services meta data.
     * 
     * @param requestData
     *            the data of the request
     */
    public void handleRequest(final RequestData requestData) {
        Map<String, String> params = requestData.getParams();
        if (params.containsKey(PARAM_ALGORITHM)) {
            generateForAlgorithm(requestData);
        } else if (params.containsKey(PARAM_OPTION)) {
            generateForOption(requestData, null, null, false);
        } else if (params.containsKey(PARAM_FORMAT)) {
            generateForFormat(requestData);
        } else if (params.containsKey(PARAM_PREVIEWIMAGE)) {
            generateForPreviewImage(requestData);
        } else {
            generateOverview(requestData);
        }        
    }

    //CHECKSTYLEOFF LineLength
    
    /**
     * Generates web page content describing a layout algorithm.
     * 
     * @param requestData
     *            the data of the request
     */
    private void generateForAlgorithm(final RequestData requestData) {
        Map<String, String> params = requestData.getParams();
        String id = params.get(PARAM_ALGORITHM);
        if (id == null) {
            return;
        }
        LayoutAlgorithm algorithm = null;
        for (LayoutAlgorithm a : serviceData.getLayoutAlgorithms()) {
            if (a.getId().equals(id)) {
                algorithm = a;
                break;
            }
        }
        if (algorithm == null) {
            return;
        }

        int scriptIndex = 0;
        
        StringBuffer sb = new StringBuffer();
        
        sb.append(HTML_PREFIX);
        
        sb.append("<p class='title'>Algorithm Details</p>\n");
        
        sb.append("<p>\n");
        sb.append("Name: " + algorithm.getName() + "<br/>\n");
        sb.append("</p>\n");

        sb.append("<p>\n");
        sb.append("Identifier: " + algorithm.getId() + "<br/>\n");
        sb.append("</p>\n");

        sb.append("<p>\n");
        sb.append(algorithm.getDescription() + "\n");
        sb.append("</p>\n");

        sb.append("<p class='title'>A Quick Preview</p>\n");
        
        sb.append("<p>\n");
        sb.append("<img src=\"/ServiceData.html?previewimage=" + algorithm.getPreviewImagePath() + "\"/>\n");
        sb.append("</p>\n");

        sb.append("<p class='title'>Supported Layout Options</p>\n");
        
        sb.append("<p>\n");        
        sb.append("<table cellspacing='0' cellpadding='5'>\n");        
        sb.append("<thead><tr><th>Name</th></tr></thead>\n");
        sb.append("<tbody>\n");        
        for (KnownOption knownOption :  algorithm.getKnownOptions()) {
            LayoutOption option = knownOption.getOption();
            sb.append(
                "<tr"
                + (scriptIndex % 2 == 0 ? " class='lightgrey'" : "")
                + " id='" 
                + scriptIndex + "short' onmouseover='doHover(\"" 
                + scriptIndex + "short\");' onmouseout='doUnhover(\"" 
                + scriptIndex + "short\");' onclick='document.location.href=\"ServiceData.html?option="
                + option.getId()
                + "\";'>"
                + "<td>" + option.getName() + "</td>" 
                + "</tr>\n"
            );
            scriptIndex++;
        }        
        sb.append("</tbody>\n");
        sb.append("</table>\n");
        sb.append("</p>\n");
        
        generateBackButton(requestData, sb);
        
        sb.append(HTML_POSTFIX);
        
        requestData.setContent(sb.toString().getBytes());
        
    }

    /**
     * Generates web page content describing a layout option.
     * 
     * @param requestData
     *            the data of the request
     * @param algorithmId
     *            the id of the algorithm or code {@null}
     * @param appendTo
     *            the buffer to append the generated HTML to if in raw mode
     * @param rawAppend           
     *            create full page or simply add the raw algorithm description
     */
    private void generateForOption(final RequestData requestData, 
        final String algorithmId, final StringBuffer appendTo, final boolean rawAppend) {
        Map<String, String> params = requestData.getParams();
        String id = params.get(PARAM_OPTION);
        if (id == null) {
            if (algorithmId != null) {
                id = algorithmId;
            } else {
                return;
            }
        }
        LayoutOption option = null;
        for (LayoutOption o : serviceData.getLayoutOptions()) {
            if (o.getId().equals(id)) {
                option = o;
                break;
            }
        }
        if (option == null) {
            return;
        }
        
        StringBuffer sb = new StringBuffer();
        
        if (!rawAppend) {
            
            sb.append(HTML_PREFIX);
        
            sb.append("<p class='title'>Layout Option Details</p>\n");
            
        }
        
        sb.append("<p>\n");
        sb.append("Name: " + option.getName() + "<br/>\n");
        sb.append("</p>\n");

        sb.append("<p>\n");
        sb.append("Identifier: " + option.getId() + "<br/>\n");
        sb.append("</p>\n");

        String type = option.getType();
        
        sb.append("<p>\n");
        sb.append(
            "Type: " + (type.equals(LayoutOptionData.REMOTEENUM_LITERAL) ? "enumeration" : type) + "<br/>\n"
        );
        sb.append("</p>\n");
        
        if (type.equals(LayoutOptionData.REMOTEENUM_LITERAL)) {
            sb.append("<p>\n");
            sb.append("Possible Values: ");
            for (String v : option.getRemoteEnum().getValues()) {
                sb.append(v + ", ");
            }
            sb.append("<br/>\n");
            sb.append("</p>\n");            
        }
        
        String defaultValue = option.getDefault();
        if (defaultValue == null) {
            defaultValue = "<NONE>";
        }
        sb.append("<p>\n");
        sb.append("Default Value: " + defaultValue + "<br/>\n");
        sb.append("</p>\n");

        sb.append("<p class='title'>Description</p>\n");
        
        sb.append("<p>\n");
        sb.append(option.getDescription() + "\n");
        sb.append("</p>\n");

        if (rawAppend) {
            
            appendTo.append(sb);
            
        } else {
         
            generateBackButton(requestData, sb);
        
            sb.append(HTML_POSTFIX);

            requestData.setContent(sb.toString().getBytes());
            
        }
        
    }
    
    /**
     * Generates web page content describing a format.
     * 
     * @param requestData
     *            the data of the request
     */
    private void generateForFormat(final RequestData requestData) {
        Map<String, String> params = requestData.getParams();
        String id = params.get(PARAM_FORMAT);
        if (id == null) {
            return;
        }
        SupportedFormat format = null;
        for (SupportedFormat f : serviceData.getSupportedFormats()) {
            if (f.getId().equals(id)) {
                format = f;
                break;
            }
        }
        if (format == null) {
            return;
        }
        
        StringBuffer sb = new StringBuffer();
        
        sb.append(HTML_PREFIX);
        
        sb.append("<p class='title'>Format Details</p>\n");
        
        sb.append("<p>\n");
        sb.append("Name: " + format.getName() + "<br/>\n");
        sb.append("</p>\n");

        sb.append("<p>\n");
        sb.append("Identifier: " + format.getId() + "<br/>\n");
        sb.append("</p>\n");

        sb.append("<p class='title'>Description</p>\n");
        
        sb.append("<p>\n");
        sb.append(format.getDescription() + "\n");
        sb.append("</p>\n");

        generateBackButton(requestData, sb);
        
        sb.append(HTML_POSTFIX);

        requestData.setContent(sb.toString().getBytes());
        
    }

    /** Path to the image which is shown when a preview image is not given by a plug in. */
    private static final String PREVIEWIMAGE_UNAVAILABLE
        = "server/kwebs/web/images/unavailable.png";
    
    /**
     * Generates a preview image.
     * @param requestData
     *            the data of the request
     */
    private void generateForPreviewImage(final RequestData requestData) {
        Map<String, String> params = requestData.getParams();
        String id = params.get(PARAM_PREVIEWIMAGE);
        if (id == null) {
            return;
        }
        byte[] data = ServerLayoutDataService.getInstance().getPreviewImage(id);
        if (data == null) {
            data = Resources.readFileOrPluginResourceAsByteArray(Application.PLUGIN_ID, PREVIEWIMAGE_UNAVAILABLE);
        }
        if (data != null) {
            requestData.setContent(data);
            requestData.setMimetype(
                WebContentHandler.guessMimeType(requestData.getResource())
            );
        }
    }

    /**
     * Generates web page content giving an overview of the meta data.
     * @param requestData
     *            the data of the request
     */
    private void generateOverview(final RequestData requestData) {

        int scriptIndex = 0;
        
        StringBuffer sb = new StringBuffer();
        
        sb.append(HTML_PREFIX);
        
        sb.append("<p class='title'>Service Details</p>\n");
        
        sb.append("<p>\n");
        sb.append("Version: " + serviceData.getVersion() + "<br/>\n");
        sb.append("</p>\n");

        sb.append("<p class='title'>How do you select an algorithm ?</p>\n");
        sb.append("<p>\n");
        sb.append("In order to select a concrete layout algorithm when doing layout with the provided service, you can");
        sb.append(" use the following layout option:\n");
        sb.append("</p>\n");
        
        sb.append("<p>\n");
        sb.append("<table class='lightgrey' border='1px;' style='border-style:solid;'>\n");
        sb.append("<tr>\n");
        sb.append("<td>\n");
        generateForOption(requestData, LayoutOptions.ALGORITHM_ID, sb, true);
        sb.append("</td>\n");
        sb.append("</tr>\n");
        sb.append("</table>\n");
        sb.append("</p>\n");
        
        sb.append("<p class='title'>Supported Algorithms</p>\n");

        sb.append("<p>\n");        
        sb.append("<table cellspacing='0' cellpadding='5'>\n");        
        sb.append("<thead><tr><th>Name</th><th>Category</th><th>Type</th><th>Version</th></tr></thead>\n");
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
                + (scriptIndex % 2 == 0 ? " class='lightgrey'" : "")
                + " id='" 
                + scriptIndex + "short' onmouseover='doHover(\"" 
                + scriptIndex + "short\");' onmouseout='doUnhover(\"" 
                + scriptIndex + "short\");' onclick='document.location.href=\"ServiceData.html?algorithm="
                + algorithm.getId()
                + "\";'>"
                + "<td>" + algorithm.getName() + "</td>" 
                + "<td>" + category + "</td>"
                + "<td>" + type + "</td>"
                + "<td>" + version + "</td>"
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
                + (scriptIndex % 2 == 0 ? " class='lightgrey'" : "")
                + " id='" 
                + scriptIndex + "short' onmouseover='doHover(\"" 
                + scriptIndex + "short\");' onmouseout='doUnhover(\"" 
                + scriptIndex + "short\");' onclick='document.location.href=\"ServiceData.html?format="
                + format.getId()
                + "\";'>"
                + "<td>" + format.getName()  + "</td>" 
                + "<td>" + format.getId()  + "</td>"
                + "</tr>\n"
            );
            scriptIndex++;
        }

        sb.append("</tbody>\n");
        sb.append("</table>\n");
        sb.append("</p>\n");
        
        sb.append(HTML_POSTFIX);

        requestData.setContent(sb.toString().getBytes());
        
    }

    /** Name of the referer entry in the headers of a HTTP request. */
    private static final String HEADER_REFERER
        = "Referer";
    
    /**
     * Inserts a "Back" button in the response data buffer.
     * 
     * @param requestData
     *            the request data
     * @param buffer
     *            the response buffer to append to
     */
    private void generateBackButton(final RequestData requestData, final StringBuffer buffer) {
        Headers headers = requestData.getExchange().getRequestHeaders();

        buffer.append("<p>\n");
        buffer.append("<form action=\"");
        
        if (headers.containsKey(HEADER_REFERER)) {
            buffer.append(headers.getFirst(HEADER_REFERER));
        } else {
            buffer.append("javascript:history.back();");
        }
        
        buffer.append("\" method=\"POST\"><input type=\"submit\" value=\"Back\"/></form>\n");
        buffer.append("</p>\n");
        
    }
    
    //CHECKSTYLEON LineLength
    
}
