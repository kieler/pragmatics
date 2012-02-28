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
 * @author swe
 * @author msp
 */
public class ServicedataProvider implements IDynamicWebContentProvider {

    // In this case readability of the code is worse if lines are limited to 100 characters.
    // CHECKSTYLEOFF LineLength
    
    /** Prefix of the HTML content. */
    private static final String HTML_PREFIX
        = "<html>\n"
          + "<head>\n"
          + "<style type='text/css'>\n"
          + "<!--\n"
          + "body { font-family: Verdana, Arial; font-size: 8pt; text-align: center; }\n"
          + ".lightgrey { background-color : #efefef; }"
          + ".title { font-size: 10pt; font-weight: bold; }\n"
          + "p { font-family: Verdana, Arial; font-size: 8pt; margin: 10pt; }\n"
          + "table { border-width : 2px; border-style : ridge; border-color: #000000; table-layout: fixed; }\n"
          + "td { font-family: Verdana, Arial; font-size: 8pt; border-tyle: none; text-align: left; }\n"
          + "th { font-family: Verdana, Arial; font-size: 8pt; font-weight: bold; text-align: left; border-bottom: 1px solid; }\n"
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
          + "<body>\n"
          + "<table style=\"border-style: none; margin-left: auto; margin-right: auto; background-color: #feffe6;\">\n"
          + "<tr><td>\n";
    
    /** Postfix of the HTML content. */
    private static final String HTML_POSTFIX = "</td></tr>\n</table>\n</body>\n</html>";

    /** Constant for query parameter 'algorithm'. */
    private static final String PARAM_ALGORITHM = "algorithm";

    /** Constant for query parameter 'option'. */
    private static final String PARAM_OPTION = "option";

    /** Constant for query parameter 'format'. */
    private static final String PARAM_FORMAT = "format";

    /** Constant for query parameter 'previewimage'. */
    private static final String PARAM_PREVIEWIMAGE = "previewimage";

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
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(HTML_PREFIX);
        
        sb.append("<p class='title'>Algorithm Details</p>\n");

        sb.append("<p>\nName: ").append(algorithm.getName()).append("<br/>\n</p>\n");
        sb.append("<p>\nIdentifier: ").append(algorithm.getId()).append("<br/>\n</p>\n");

        if (algorithm.getDescription() != null) {
            sb.append("<p>\n");
            generateHypertext(sb, algorithm.getDescription());
            sb.append("\n</p>\n");
        }

        sb.append("<p class='title'>A Quick Preview</p>\n");
        
        sb.append("<p>\n<img src=\"/ServiceData.html?previewimage=").append(algorithm.getPreviewImagePath()).append("\"/>\n</p>\n");

        sb.append("<p class='title'>Supported Layout Options</p>\n");
        
        sb.append("<p>\n<table cellspacing='0' cellpadding='5'>\n");        
        sb.append("<thead><tr><th>Name</th></tr></thead>\n<tbody>\n");
        for (KnownOption knownOption :  algorithm.getKnownOptions()) {
            LayoutOption option = knownOption.getOption();
            sb.append("<tr").append(scriptIndex % 2 == 0 ? " class='lightgrey'" : "");
            sb.append(" id='").append(scriptIndex).append("short' onmouseover='doHover(\""); 
            sb.append(scriptIndex).append("short\");' onmouseout='doUnhover(\""); 
            sb.append(scriptIndex).append("short\");' onclick='document.location.href=\"ServiceData.html?option=");
            sb.append(option.getId()).append("\";'><td>").append(option.getName()).append("</td></tr>\n");
            scriptIndex++;
        }        
        sb.append("</tbody>\n</table>\n</p>\n");
        
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
        final String algorithmId, final StringBuilder appendTo, final boolean rawAppend) {
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
        
        StringBuilder sb = new StringBuilder();
        
        if (!rawAppend) {
            
            sb.append(HTML_PREFIX);
        
            sb.append("<p class='title'>Layout Option Details</p>\n");
            
        }
        
        sb.append("<p>\nName: ").append(option.getName()).append("<br/>\n</p>\n");

        sb.append("<p>\nIdentifier: ").append(option.getId()).append("<br/>\n</p>\n");

        String type = option.getType();
        
        sb.append("<p>\n");
        sb.append("Type: ").append((type.equals(LayoutOptionData.REMOTEENUM_LITERAL) ? "enumeration" : type)).append("<br/>\n</p>\n");
        
        if (type.equals(LayoutOptionData.REMOTEENUM_LITERAL)) {
            sb.append("<p>\nPossible Values: ");
            for (String v : option.getRemoteEnum().getValues()) {
                sb.append(v).append(", ");
            }
            sb.append("<br/>\n</p>\n");            
        }
        
        String defaultValue = option.getDefault();
        if (defaultValue == null) {
            defaultValue = "<NONE>";
        }
        sb.append("<p>\nDefault Value: ").append(defaultValue).append("<br/>\n</p>\n");

        sb.append("<p class='title'>Description</p>\n");
        
        if (option.getDescription() != null) {
            sb.append("<p>\n");
            generateHypertext(sb, option.getDescription());
            sb.append("\n</p>\n");
        }

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
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(HTML_PREFIX);
        
        sb.append("<p class='title'>Format Details</p>\n");
        
        sb.append("<p>\nName: ").append(format.getName()).append("<br/>\n</p>\n");

        sb.append("<p>\nIdentifier: ").append(format.getId()).append("<br/>\n</p>\n");

        sb.append("<p class='title'>Description</p>\n");
        
        if (format.getDescription() != null) {
            sb.append("<p>\n");
            generateHypertext(sb, format.getDescription());
            sb.append("\n</p>\n");
        }

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
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(HTML_PREFIX);
        
        sb.append("<p class='title'>Service Details</p>\n");
        
        sb.append("<p>\nVersion: ").append(serviceData.getVersion()).append("<br/>\n</p>\n");

        sb.append("<p class='title'>Selection of Layout Algorithm</p>\n<p>\n");
        sb.append("In order to select a specific layout algorithm when doing layout with the provided service, you can use the following layout option:\n");
        sb.append("</p>\n");
        
        sb.append("<p>\n<table class='lightgrey' border='1px;' style='border-style:solid;'>\n");
        sb.append("<tr>\n<td>\n");
        generateForOption(requestData, LayoutOptions.ALGORITHM.getId(), sb, true);
        sb.append("</td>\n</tr>\n");
        sb.append("</table>\n</p>\n");
        
        sb.append("<p class='title'>Supported Algorithms</p>\n");

        sb.append("<p>\n<table cellspacing='0' cellpadding='5'>\n");        
        sb.append("<thead><tr><th>Name</th><th>Category</th><th>Type</th><th>Version</th></tr></thead>\n<tbody>\n");
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
            sb.append("<tr").append(scriptIndex % 2 == 0 ? " class='lightgrey'" : "").append(" id='");
            sb.append(scriptIndex).append("short' onmouseover='doHover(\""); 
            sb.append(scriptIndex).append("short\");' onmouseout='doUnhover(\""); 
            sb.append(scriptIndex).append("short\");' onclick='document.location.href=\"ServiceData.html?algorithm=");
            sb.append(algorithm.getId()).append("\";'><td>").append(algorithm.getName()).append("</td>");
            sb.append("<td>").append(category).append("</td>").append("<td>").append(type).append("</td>");
            sb.append("<td>").append(version).append("</td></tr>\n");
            scriptIndex++;
        }
        sb.append("</tbody>\n</table>\n</p>\n");

        sb.append("<p class='title'>Supported Formats</p>\n");

        sb.append("<p>\n<table cellspacing='0' cellpadding='5'>\n");        
        sb.append("<thead><tr><th>Name</th><th>Identifier</th></tr></thead>\n<tbody>\n");
        
        scriptIndex *= 2; // Needs to be even on the beginning of a table

        for (SupportedFormat format : serviceData.getSupportedFormats()) {
            sb.append("<tr");
            sb.append(scriptIndex % 2 == 0 ? " class='lightgrey'" : "").append(" id='"); 
            sb.append(scriptIndex).append("short' onmouseover='doHover(\""); 
            sb.append(scriptIndex).append("short\");' onmouseout='doUnhover(\""); 
            sb.append(scriptIndex).append("short\");' onclick='document.location.href=\"ServiceData.html?format=");
            sb.append(format.getId()).append("\";'>");
            sb.append("<td>").append(format.getName()).append("</td>"); 
            sb.append("<td>").append(format.getId()).append("</td></tr>\n");
            scriptIndex++;
        }

        sb.append("</tbody>\n</table>\n</p>\n");
        
        sb.append(HTML_POSTFIX);

        requestData.setContent(sb.toString().getBytes());
        
    }

    /** Name of the referer entry in the headers of a HTTP request. */
    private static final String HEADER_REFERER = "Referer";
    
    /**
     * Inserts a "Back" button in the response data buffer.
     * 
     * @param requestData
     *            the request data
     * @param buffer
     *            the response buffer to append to
     */
    private static void generateBackButton(final RequestData requestData, final StringBuilder buffer) {
        Headers headers = requestData.getExchange().getRequestHeaders();

        buffer.append("<p>\n<form action=\"");
        
        if (headers.containsKey(HEADER_REFERER)) {
            buffer.append(headers.getFirst(HEADER_REFERER));
        } else {
            buffer.append("javascript:history.back();");
        }
        
        buffer.append("\" method=\"POST\"><input type=\"submit\" value=\"Back\"/></form>\n</p>\n");
        
    }
    
    /**
     * Generates hypertext from a description text. URLs are converted to hyperlinks.
     * 
     * @param sb
     *            the response buffer to append to
     * @param text
     *            description text
     */
    private static void generateHypertext(final StringBuilder sb, final String text) {
        int p = 0;
        while (p < text.length()) {
            int s = text.indexOf("http", p);
            if (s >= 0) {
                if (s > p) {
                    sb.append(text.substring(p, s));
                }
                int e = s;
                char c = 0;
                do {
                    e++;
                    if (e < text.length()) {
                        c = text.charAt(e);
                    }
                } while (e < text.length() && c != ' ' && c != '\t' && c != '\r' && c != '\n');
                String url = text.substring(s, e);
                sb.append("<a href=\"").append(url).append("\">").append(url).append("</a>");
                p = e;
            } else {
                sb.append(text.substring(p));
                p = text.length();
            }
        }
    }
    
}
