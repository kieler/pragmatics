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

import de.cau.cs.kieler.kiml.LayoutOptionData
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kwebs.server.Application
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService
import de.cau.cs.kieler.kwebs.server.logging.Logger
import de.cau.cs.kieler.kwebs.servicedata.KnownOption
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm
import de.cau.cs.kieler.kwebs.servicedata.LayoutOption
import de.cau.cs.kieler.kwebs.servicedata.ServiceData
import de.cau.cs.kieler.kwebs.servicedata.SupportedFormat
import de.cau.cs.kieler.kwebs.util.Resources
import java.util.Map
import java.util.List

/**
 * This class implements a web content provider for displaying the service meta data in HTML format.
 *
 * @author swe
 * @author msp
 */
class ProvidedlayoutProvider 
    extends AbstractProvider
{
    
    /** The default layout algorithm identifier. */
    private static String DEFAULT_ALGORITHM_ID = "de.cau.cs.kieler.klay.layered"

    /** Constant for query parameter 'algorithm'. */
    private static String PARAM_ALGORITHM = "algorithm"

    /** Constant for query parameter 'option'. */
    private static String PARAM_OPTION = "option"

    /** Constant for query parameter 'format'. */
    private static String PARAM_FORMAT = "format"

    /** Constant for query parameter 'previewimage'. */
    private static String PARAM_PREVIEWIMAGE = "previewimage"

    /** Caching the service data model. */
    private ServiceData serviceData 
        = ServerLayoutDataService::getInstance().getServiceDataModel()
     
    /**
     * 
     */
    override CharSequence getHeaders(
        ResourceProcessingExchange processingExchange
    ) 
    {
        '''
        <style type='text/css'>
            <!--
                .even {
                    background-color : #efefef;
                }
                .odd  {
                    background-color : #ffffff;
                }
                .even:hover, .odd:hover {
                    background-color : #a5F3a5;
                    cursor           : pointer;
                }
                table.listing {
                    border-width      : 2px;
                    border-style      : ridge;
                    border-color     : #000000;
                    table-layout     : fixed;
                }
                table.listing thead tr > th, table.listing tbody tr > td {
                    font-family      : Verdana, Arial; 
                    font-size         : 8pt; 
                    border-style     : none;
                    text-align         : left;
                    padding          : 10px;
                }
                table.listing thead tr > th {
                    font-weight         : bold;
                    border-bottom     : 1px solid;
                }
                table.advertisement {
                    background-color : #a5F3a5;
                    border           : 1px solid;
                    padding          : 10px;
                }
                table.advertisement tr td > p{
                    font-family      : Verdana, Arial; 
                    font-size         : 8pt;
                    border-style     : none;
                    text-align         : left;
                }
            //-->
        </style>
        '''
    }
    
    /**
     * 
     */
    override CharSequence getBody(
        ResourceProcessingExchange processingExchange
    ) 
    {        
        val Map<String, String> params = processingExchange.getParams()
        if (params.containsKey(PARAM_ALGORITHM)) {
            return generateForAlgorithm(processingExchange)
        } else if (params.containsKey(PARAM_OPTION)) {
            return generateForOption(processingExchange, null, false)
        } else if (params.containsKey(PARAM_FORMAT)) {
            return generateForFormat(processingExchange)
        } else if (params.containsKey(PARAM_PREVIEWIMAGE)) {
            Logger::log(//FIXME Xtend2 can not handle static enum members                
                "Preview image handled without override."
            )
            return ''''''
        } 
        return generateOverview(processingExchange)            
    }
    
    /**
     * 
     */
    override boolean providerOverride(
        ResourceProcessingExchange processingExchange
    ) 
    {
        val Map<String, String> params = processingExchange.getParams()
        if (params.containsKey(PARAM_PREVIEWIMAGE)) {
            generateForPreviewImage(processingExchange)
            return true
        } 
        return false
    }
    
    /**
     * Generates web page content describing a layout algorithm.
     * 
     * @param requestData
     *            the data of the request
     */
    def private CharSequence generateForAlgorithm(
        ResourceProcessingExchange processingExchange
    ) 
    {
        val Map<String, String> params = processingExchange.getParams()
        val String                 id        = params.get(PARAM_ALGORITHM)
        if (id == null) {
            return ''''''
        }
        val LayoutAlgorithm algorithm = serviceData.layoutAlgorithms.filter(algorithm | algorithm.id.equals(id)).head
        if (algorithm == null) {
            return ''''''
        }
        val List<KnownOption> options = algorithm.knownOptions.sortBy[it.option.name]
        
        '''
        <div class="col-md-8 col-md-offset-2">
        <h3>«algorithm.category?.name» - «algorithm.name»</h3>
        <p>Type: «algorithm.type?.name»<br/></p>
        <p>Identifier: «algorithm.id»<br/></p>
        «if (algorithm.description != null) {
            '''<p>«generateHypertext(algorithm.description)»</p>'''
        }»
        <p>
            <div align='center'>
                <img src='/ProvidedLayout.html?previewimage=«algorithm.previewImagePath»'/>
            </div>
        </p>
        <h3>Supported Layout Options</h3>
        <p>
            <div align='center'>
                <table cellspacing='0' cellpadding='5' class='listing'>
                    <thead><tr><th>Name</th><th>Type</th><th>Identifier</th><th>Default Value</th></tr></thead>
                    <tbody>
                        «options.map(option | {
                            '''
                            <tr class='«
                                if (options.indexOf(option) % 2 == 0) "even" else "odd"
                            »' onclick='document.location.href="Providedlayout.html?option=«
                                option.option.id
                            »";'>
                                <td>«option.option.name»</td>
                                <td>«
                                    if (option.option.type.equals(LayoutOptionData::REMOTEENUM_LITERAL))
                                        LayoutOptionData::ENUM_LITERAL
                                    else if (option.option.type.equals(LayoutOptionData::REMOTEENUMSET_LITERAL))
                                        LayoutOptionData::ENUMSET_LITERAL
                                    else option.option.type
                                »</td>
                                <td>«option.option.id»</td>
                                <td>«if (option.^default == null) {
                                    option.option.^default
                                } else {
                                    option.^default
                                }»</td>
                            </tr>
                            '''
                        }).join»
                    </tbody>
                </table>
            </div>
        </p>
        «generateBackButton(processingExchange)»
        </div>
        '''
    }

    /**
     * Generates web page content giving an overview of the meta data.
     * @param requestData
     *            the data of the request
     */
    def private CharSequence generateOverview(
        ResourceProcessingExchange processingExchange
    ) 
    {
        val List<LayoutAlgorithm> algorithmns = serviceData.layoutAlgorithms.sortBy[
            '''«it.category?.name»/«it.type?.name»/«it.name»'''.toString
        ]
        val List<SupportedFormat> formats     = serviceData.supportedFormats
        
        '''
        <div class="col-md-8 col-md-offset-2">
        <h2>Provided Layout</h2>
        <p>
            This page offers details on the configuration options of the layout service.
            The most important option is the choice which layout algorithm to execute on the input graph.
            Each algorithm supports a different set of options for fine-tuning the layout.
            Furthermore, the service supports different graph formats to exchange graphs in serialized form
            between your application and the server.
            You can either send and receive the graph in the same format, or use different formats
            for input and output.
        </p>
        <h3>Service Details</h3>
        <p>Currently running version: «serviceData.version»<br/></p>
        <h3>Supported Algorithms</h3>
        <a id="algorithms"></a>
        <p>
            The following option can be used to select a specific layout algorithm:
        </p>
        <div class="alert alert-info">
            «generateForOption(processingExchange, LayoutOptions::ALGORITHM.id, true)»
        </div>
        <p>
            The following layout algorithms are currently supported by this service. Click any 
            algorithm to receive further information on it's supported layout options.
        </p>
        <p>
            <div align='center'>
                <table cellspacing='0' cellpadding='5' class='listing'>
                    <thead><tr><th>Name</th><th>Category</th><th>Type</th><th>Identifier</th><th>Version</th></tr></thead>
                    <tbody>
                        «algorithmns.map(algorithm | {    
                            var String category = algorithm.category?.name
                            var String type     = algorithm.type?.name
                            var String version  = algorithm.version
                            if (category == null || category.length== 0) {
                                category = "&nbsp;"
                            }
                            if (type == null || type.length== 0) {
                                type = "&nbsp;"
                            }
                            if (version == null || version.length== 0) {
                                version = "&nbsp;"
                            }
                            '''
                            <tr class='«
                                if (algorithmns.indexOf(algorithm) % 2 == 0) "even" else "odd"
                            »' onclick='document.location.href="Providedlayout.html?algorithm=«
                                algorithm.id
                            »";'>
                                <td>«algorithm.name»</td>
                                <td>«category»</td>
                                <td>«type»</td>
                                <td>«algorithm.id»</td>
                                <td>«version»</td>
                            </tr>'''
                        }).join»
                    </tbody>
                </table>
            </div>
        </p>    
        <h3>Supported Formats</h3>
        <a id="formats"></a>
        <p>
            The following formats can be used to transfer graphs to the layout service:
        </p>
        <p>
            <div align='center'>
                <table cellspacing='0' cellpadding='5' class='listing'>
                    <thead><tr><th>Name</th><th>Identifier</th></tr></thead>
                    <tbody>
                        «formats.map(format | {
                            '''
                            <tr class='«
                                if (formats.indexOf(format) % 2 == 0) "even" else "odd"
                            »' onclick='document.location.href="Providedlayout.html?format=«
                                format.id
                            »";'>
                                <td>«format.name»</td> 
                                <td>«format.id»</td>
                            </tr>'''
                        }).join»
                    </tbody>
                </table>
            </div>
        </p>
        </div>'''            
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
    def private CharSequence generateForOption(
        ResourceProcessingExchange processingExchange,
        String                     algorithmId,
        boolean                    rawAppend
    ) 
    {
        val Map<String, String> params = processingExchange.getParams()
        val String              id     = if (params.get(PARAM_OPTION) != null) params.get(PARAM_OPTION) else algorithmId
        if (id == null) {
            return ''''''
        }
        val LayoutOption option = serviceData.layoutOptions.filter(option | option.id.equals(id)).head 
        if (option == null) {
            return ''''''
        }
        val String type         = option.type
        val String defaultValue =
            if (id.equals(LayoutOptions::ALGORITHM.getId())) DEFAULT_ALGORITHM_ID
            else if (option.^default != null && option.^default.trim.length > 0) option.^default.trim
            else "&lt;NONE&gt;"
 
        '''
        
            «if (!rawAppend) '''<div class="col-md-8 col-md-offset-2"><h3>Layout Option Details</h3>'''»
            <dl class="dl-horizontal">
                <dt>Name:</dt><dd>«option.name»</dd>
                <dt>Identifier:</dt><dd>«option.id»</dd>
                <dt>Type:</dt><dd>
                «if (type.equals(LayoutOptionData::REMOTEENUM_LITERAL))
                    "enumeration"
                else if (type.equals(LayoutOptionData::REMOTEENUMSET_LITERAL))
                    "enumeration set"
                else type»</dd>
                «if (type.equals(LayoutOptionData::REMOTEENUM_LITERAL)
                    || type.equals(LayoutOptionData::REMOTEENUMSET_LITERAL)) {
                    '''
                    <dt>Possible Values:</dt><dd>«option.remoteEnum.values.join(", ")»</dd>
                    '''
                }»
                <dt>Default Value:</dt><dd>«defaultValue»</dd>
                «if (option.appliesTo != null) {
                    '''<dt>Applies To:</dt><dd>«option.appliesTo»</dd>'''
            }»
            </dl>
            «if (option.description != null) {
            '''
            <h4>Description</h4>
            <p>
                «generateHypertext(option.description)»
            </p>
                '''
            }»
            «if (type.equals(LayoutOptionData::REMOTEENUMSET_LITERAL)) 
            '''
            <div class="alert alert-info">
             To textually specify enumsets pass a string with the desired values separated by a whitespace.  
             <code> de.cau.cs.kieler.enumProp: "VAL_A VAL_B VAL_C"</code>
            </div>
            '''»
            ««««if (!rawAppend) generateBackButton(processingExchange)»
        «if (!rawAppend)'''</div>''' »
        '''
    }
    
    /**
     * Generates web page content describing a format.
     * 
     * @param requestData
     *            the data of the request
     */
    def private generateForFormat(
        ResourceProcessingExchange processingExchange
    ) 
    {
        val Map<String, String> params = processingExchange.getParams()
        val String                 id     = params.get(PARAM_FORMAT)
        if (id == null) {
            return ''''''
        }
        val SupportedFormat format = serviceData.supportedFormats.filter(format | format.id.equals(id)).head
        if (format == null) {
            return ''''''
        }
        '''
        <div class="col-md-8 col-md-offset-2">
        <h3>Format Details</h3>
        <p>Name: «format.name»<br/></p>
        <p>Identifier: «format.id»<br/></p>
        «if (format.description!= null) {
            '''
            <h3>Description</h3>
            <p>
                «generateHypertext(format.description)»
            </p>'''
        }»
        «generateBackButton(processingExchange)»
        </div>
        '''
    }
    
    /** Path to the image which is shown when a preview image is not given by a plug in. */
    private static String PREVIEWIMAGE_UNAVAILABLE
        = "server/kwebs/web/images/unavailable.png"
    
    /**
     * Generates a preview image.
     * @param requestData
     *            the data of the request
     */
    def private generateForPreviewImage(
        ResourceProcessingExchange processingExchange
    ) 
    {
        val Map<String, String> params = processingExchange.getParams()
        val String id = params.get(PARAM_PREVIEWIMAGE)
        if (id == null) {
            return ''''''
        }
        var byte[] data = ServerLayoutDataService::getInstance().getPreviewImage(id)
        if (data == null) {
            data = Resources::readFileOrPluginResourceAsByteArray(Application::PLUGIN_ID, PREVIEWIMAGE_UNAVAILABLE)
        }
        if (data != null) {
            processingExchange.getResourceInformation().setContent(data)
            processingExchange.getResourceInformation().setMimetype(
                WebContentHandler::guessMimeType(processingExchange.getResource())
            )
        }
    }
    
    /**
     * Inserts a "Back" button in the response data buffer.
     * 
     * @param requestData
     *            the request data
     * @param buffer
     *            the response buffer to append to
     */
    def private CharSequence generateBackButton(
        ResourceProcessingExchange processingExchange
    ) 
    {
        '''
        <p>
            <div align='center'>
                <form action="javascript:history.back();" method="POST">
                    <input type="submit" value="Back"/>
                </form>
            </div>
        </p>'''
    }
    
    /**
     * Generates hypertext from a description text. URLs are converted to hyperlinks.
     * 
     * @param sb
     *            the response buffer to append to
     * @param text
     *            description text
     */
    def private CharSequence generateHypertext(
        String text
    ) 
    {
        val StringBuilder builder = new StringBuilder
        var int p = 0
        while (p < text.length) {
            val int s = text.indexOf("http", p)
            if (s >= 0) {
                if (s > p) {
                    builder.append(text.substring(p, s))
                }
                var int  e = s
                var char c = 0 as char
                do {
                    e = e + 1
                    if (e < text.length) {
                        c = text.charAt(e)
                    }
                } while (e < text.length && c != ' ' && c != '\t' && c != '\r' && c != '\n')
                val String url = text.substring(s, e)
                builder.append("<a href=\"").append(url).append("\">").append(url).append("</a>")
                p = e
            } else {
                builder.append(text.substring(p))
                p = text.length()
            }
        }
        return builder
    }
    
}
