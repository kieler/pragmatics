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

package de.cau.cs.kieler.kwebs.client.ui

import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm
import de.cau.cs.kieler.kwebs.servicedata.ServiceData
import de.cau.cs.kieler.kwebs.servicedata.SupportedFormat
import org.eclipse.emf.common.util.EList
import de.cau.cs.kieler.kwebs.client.util.Html

/**
 * Utility class for generating HTML content for the server details dialog.
 *
 * @kieler.rating 2011-05-14
 *
 * @author swe
 */
class ServerDetailsPage {

    /**
     *
     * @param serviceData
     * @return
     */
    def static String generateHtml(ServiceData serviceData, ILayoutServiceClient client) {

        val EList<LayoutAlgorithm> algorithms = serviceData.layoutAlgorithms
        val EList<SupportedFormat> formats    = serviceData.supportedFormats

        return
			'''
			<html>
			    <head>
			        <style type='text/css'>
			            <!--
			                body {
			                    font-family      : Verdana, Arial;
			                    font-size        : 8pt;
			                }
			                .lightgrey {
			                    background-color : #efefef;
			                }
			                p {
			                    font-family      : Verdana, Arial;
			                    font-size        : 8pt;
			                    margin           : 10pt;
			                }
			                table {
			                    border-width     : 2px;
			                    border-style     : ridge;
			                    border-color     : #000000;
			                    table-layout     : fixed;
			                }
			                td {
			                    font-family      : Verdana, Arial;
			                    font-size        : 8pt;
			                    border-tyle      : none;
			                    text-align       : left;
			                }
			                th {
			                    font-family      : Verdana, Arial;
			                    font-size        : 8pt;
			                    font-weight      : bold;
			                    text-align       : left;
			                    border-bottom    : 1px solid;
			                }
			                thead:first-child tr {
			                    border           : 1px solid;
			                }
			                .title {
			                    font-size        : 10pt;
			                    font-weight      : bold;
			                }
			            //-->
			        </style>
			        <script type='text/javascript'>
			            <!--
			                function doHover(id) {
			                    doMouse(id, "#a5F3a5", "pointer");
			                }
			                function doUnhover(id) {
			                    var index = parseInt(id);
			                    var color = (index % 2 == 0 ? "#efefef" : "#ffffff");
			                    doMouse(id, color, "default");
			                }
			                function doMouse(id, color, mouse) {
			                    if (id == null || color == null || mouse == null) {
			                        return;
			                    }
			                    var element = document.getElementById(id);
			                    if (element == null) {
			                        return;
			                    }
			                    element.style.backgroundColor = color;
			                    document.body.style.cursor    = mouse;
			                }
			                function doSwitch(id) {
			                    if (id == null) {
			                        return;
			                    }
			                    var element = document.getElementById(id);
			                    if (element == null) {
			                        return;
			                    }
			                    if (element.style.visibility == "visible" || element.style.visibility == "") {
			                        element.style.visibility = "hidden";
			                        element.style.display    = "none";
			                    } else {
			                        element.style.visibility = "visible";
			                        element.style.display    = "block";
			                    }
			                }
			            //-->
			        </script>
			    </head>
			    <body>
			        <p class='title'>Service Details</p>
			        <p>
			            Name: «client.serverConfig.name»<br/>
			            Address: «client.serverConfig.address»<br/>
			            Version: «serviceData.version»<br/>
			        </p>
			        <p class='title'>Supported Algorithms</p>
			        <p>
			            <table cellspacing='0' cellpadding='5'>
			                <thead><tr><th>Name</th><th>Category</th><th>Type</th><th>Version</th></th></thead>
			                <tbody>
			                    «algorithms.map(a | {
			
			                        var String category = a.category?.name
			                        var String type     = a.type?.name
			                        var String version  = a.version
			
			                        if (category == null || category.length == 0) {
			                            category = "&nbsp;"
			                        }
			                        if (type == null || type.length() == 0) {
			                            type = "&nbsp;"
			                        }
			                        if (version == null || version.length() == 0) {
			                            version = "&nbsp;"
			                        }
			
			                        val Integer index = algorithms.indexOf(a)
			
			                        '''
			                        <tr «
			                            if (index % 2 == 0) ''' class='lightgrey' '''
			                        » id='«index»short' onmouseover='doHover("«index»short");' onmouseout='doUnhover("«index»short");' onclick='doSwitch("«index»detail");'>
			                            <td>«a.name»</td>
			                            <td>«category»</td>
			                            <td>«type»</td>
			                            <td>«version»</td>
			                        </tr>
			                        <tr «
			                            if (index % 2 == 0) ''' class='lightgrey' '''
			                        » id='«index»detail' style='visibility:hidden;display:none;'>
			                            <td colspan='4' style='text-align:justify;'>
			                                «a.description»
			                            </td>
			                        </tr>
			                        '''
			
			                    }).join»
			                </tbody>
			            </table>
			        </p>
			        <p class='title'>Supported Formats</p>
			        <p>
			            <table cellspacing='0' cellpadding='5'>
			                <thead><tr><th>Name</th><th>Identifier</th></tr></thead>
			                <tbody>
			                    «formats.map(f | {
			
			                        // Needs to be even on the beginning of a table and the index must not
			                        // interfere with the indexes of the algorithms table
			                        val Integer index = algorithms.size * 2 + formats.indexOf(f)
			
			                        '''
			                        <tr «
			                            if (index % 2 == 0) ''' class='lightgrey' '''
			                        » id='«index»short' onmouseover='doHover("«index»short");' onmouseout='doUnhover("«index»short");' onclick='doSwitch("«index»detail");'>
			                            <td>«f.name»</td>
			                            <td>«f.id»</td>
			                        </tr>
			                        <tr «
			                            if (index % 2 == 0) ''' class='lightgrey' '''
			                        » id='«index»detail' style='visibility:hidden;display:none;'>
			                            <td colspan='2' style='text-align:justify;'>
			                                «Html::escapeUrls(f.description)»
			                            </td>
			                        </tr>
			                        '''
			
			                    }).join»
			                </tbody>
			            </table>
			        </p>
			    </body>
			</html>
			'''.toString

    }

    /**
     * Private constructor.
     */
    private new() {
    }

}
