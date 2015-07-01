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

import com.google.common.base.Strings
import de.cau.cs.kieler.kiml.LayoutAlgorithmData
import de.cau.cs.kieler.kiml.LayoutMetaDataService
import de.cau.cs.kieler.kiml.LayoutOptionData
import de.cau.cs.kieler.kiml.options.GraphFeature
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kwebs.server.Application
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutMetaDataService
import de.cau.cs.kieler.kwebs.server.logging.Logger
import de.cau.cs.kieler.kwebs.server.servicedata.KnownOption
import de.cau.cs.kieler.kwebs.server.servicedata.LayoutAlgorithm
import de.cau.cs.kieler.kwebs.server.servicedata.LayoutOption
import de.cau.cs.kieler.kwebs.server.servicedata.ServiceData
import de.cau.cs.kieler.kwebs.server.servicedata.SupportedFormat
import de.cau.cs.kieler.kwebs.server.util.Resources
import java.util.List
import java.util.Map

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
        = ServerLayoutMetaDataService::getInstance().getServiceDataModel()
     
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
                .link:hover {
                    cursor: pointer;
                }
                .imgPlaceholder {
                    width: 20px;
                    height: 20px;
                }
                .center {
                    display: inline-block;
                    float: none;
                    margin-left: auto;
                    margin-right: auto;
                }
                th.rotate {
                  /* Something you can count on */
                  height: 140px;
                  white-space: nowrap;
                }
                th.rotate > div {
                  transform: 
                    /* Magic Numbers */
                    translate(-5px, -2px)
                    /* 45 is really 360 - 45 */
                    rotate(315deg);
                  width: 30px;
                }
                th.rotate > div > span {
                  border-bottom: 1px solid #ccc;
                  padding: 5px 10px;
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
        
        val List<GraphFeature> features = GraphFeature.values().sortBy[it.name]
        
        val LayoutAlgorithmData algorithmData =
                LayoutMetaDataService.instance.getAlgorithmData(algorithm.id)
        
        '''
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
        <ul class="nav nav-tabs nav-justified">
            <li class="active"><a data-toggle="tab" href="#options">Supported Layout Options</a></li>
            <li><a data-toggle="tab" href="#features">Supported Graph Features</a></li>
        </ul>
        <div class="tab-content">
            <div id="options" class="tab-pane active">
                <table class='table table-striped table-hover table-responsive'>
                    <thead><tr><th>Name</th><th>Type</th><th>Identifier</th><th>Default Value</th></tr></thead>
                    <tbody>
                        «options.map(option | {
                            '''
                            <tr>
                                <td class="link" onclick='document.location.href="Providedlayout.html?option=«
                                option.option.id
                                »";'>«option.option.name»</td>
                                <td>«option.option.type»</td>
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
            <div id="features" class="tab-pane">
                <table class='table table-striped table-hover table-responsive'>
                    <thead><tr><th>Name</th><th>Description</th><th>Degree of Support</th></tr></thead>
                    <tbody>
                        «features.map(feature | {
                            if (algorithmData.supportsFeature(feature)) {
                            '''
                            <tr>
                                <td>«feature.name»</td>
                                <td>«feature.description»</td>
                                <td>«algorithmData.getSupportedFeatureDescription(feature)»</td>
                            </tr>
                            '''
                            } else {
                            ''''''
                            }
                        }).join»
                    </tbody>
                </table>
            </div>
        </div>
        «generateBackButton(processingExchange)»
        '''
    }
    
    /** Path to the image which is shown when a preview image is not given by a plug in. */
    private static String IMAGE_CHECK
        = "/images/check.png"

    /**
     * Generates web page content giving an overview of the meta data.
     * @param requestData
     *            the data of the request
     */
    def private CharSequence generateOverview(
        ResourceProcessingExchange processingExchange
    ) 
    {
        val List<LayoutAlgorithm> algorithms = serviceData.layoutAlgorithms.sortBy[
            '''«it.category?.name»/«it.type?.name»/«it.name»'''.toString
        ]
        val List<SupportedFormat> formats     = serviceData.supportedFormats
        val List<GraphFeature> features = GraphFeature.values().sortBy[it.name]
        
        '''
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
        <ul class="nav nav-tabs nav-justified">
            <li class="active"><a data-toggle="tab" href="#algorithms">Supported Algorithms</a></li>
            <li><a data-toggle="tab" href="#formats">Supported Formats</a></li>
            <li><a data-toggle="tab" href="#features">Supported Features</a></li>
        </ul>
        <div class="tab-content">
            <div id="algorithms" class="tab-pane active">
                <p>
                    The following option can be used to select a specific layout algorithm:
                </p>
                <div style="text-align: center;">
                <div class="alert alert-info center" style="text-align: left;">
                    «generateForOption(processingExchange, LayoutOptions::ALGORITHM.id, true)»
                </div>
                </div>
                <p>
                    The following layout algorithms are currently supported by this service. Click any 
                    algorithm to receive further information on it's supported layout options.
                </p>
                <table class='table table-striped table-hover table-responsive'>
                    <thead><tr><th>Name</th><th>Category</th><th>Type</th><th>Identifier</th><th>Version</th></tr></thead>
                    <tbody>
                        «algorithms.map(algorithm | {    
                            var String category = algorithm.category?.name
                            var String type     = algorithm.type?.name
                            var String version  = algorithm.version
                            if (Strings.isNullOrEmpty(category)) {
                                category = "&nbsp;"
                            }
                            if (Strings.isNullOrEmpty(type)) {
                                type = "&nbsp;"
                            }
                            if (Strings.isNullOrEmpty(version)) {
                                version = "&nbsp;"
                            }
                            '''
                            <tr>
                                <td class="link" onclick='document.location.href="Providedlayout.html?algorithm=«
                                algorithm.id
                                »";'>«algorithm.name»</td>
                                <td>«category»</td>
                                <td>«type»</td>
                                <td>«algorithm.id»</td>
                                <td>«version»</td>
                            </tr>'''
                        }).join»
                    </tbody>
                </table>
            </div>
            <div id="formats" class="tab-pane">
                <p>
                    The following formats can be used to transfer graphs to the layout service:
                </p>
                <table class='table table-striped table-hover table-responsive'>
                    <thead><tr><th>Name</th><th>Identifier</th></tr></thead>
                    <tbody>
                        «formats.map(format | {
                            '''
                            <tr>
                                <td class="link" onclick='document.location.href="Providedlayout.html?format=«
                                format.id
                                »";'>«format.name»</td> 
                                <td>«format.id»</td>
                            </tr>'''
                        }).join»
                    </tbody>
                </table>
            </div>
            <div id="features" class="tab-pane">
                <p>
                    The following features could be supported by the layout algorithms:
                </p>
                <table class='table table-striped table-hover table-responsive rotate-headers'>
                    <thead><tr><th>Algorithm</th>
                        «features.map(feature | {
                            '''<th class="rotate"><div><span data-toggle="tooltip" title="«feature.description»">
                                «feature.name»</span></div></th>'''
                        }).join»</tr></thead>
                    <tbody>
                        «algorithms.map(algorithm | {
                            val LayoutAlgorithmData algorithmData =
                                    LayoutMetaDataService.instance.getAlgorithmData(algorithm.id)
                            '''
                            <tr>
                                <td class="link" onclick='document.location.href="Providedlayout.html?algorithm=«
                                algorithm.id
                                »";'>«algorithm.name»</td>
                                «features.map(feature | {
                                    '''<td>
                                    «IF (algorithmData.supportsFeature(feature))»
                                        <img height="20" src="«IMAGE_CHECK»"
                                        «IF !Strings.isNullOrEmpty(algorithmData.getSupportedFeatureDescription(feature))»
                                            data-toggle="tooltip" title="«algorithmData.getSupportedFeatureDescription(feature)»"
                                        «ENDIF»
                                        />
                                    «ELSE»
                                        <div class="imgPlaceholder"></div>
                                    «ENDIF»
                                    </td>'''
                                }).join»
                            </tr>'''
                        }).join»
                    </tbody>
                </table>
            </div>
        </div>
        <script>
            $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
                if ($(e.target).attr("href") == "#features") {
                    var width = $("th.rotate:last-child>div>span").first().width()
                    $("th.rotate:last-child").first().width(width)
                    $(".rotate-headers>tbody>tr>td:last-child").width(width)
                }
            })
        </script>'''
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
        
            «if (!rawAppend) '''
            <h3>Layout Option Details</h3>'''»
            <dl class="dl-horizontal">
                <dt>Name:</dt><dd>«option.name»</dd>
                <dt>Identifier:</dt><dd>«option.id»</dd>
                <dt>Type:</dt><dd>«type»</dd>
                «if (type.equals(LayoutOptionData::ENUM_LITERAL)
                    || type.equals(LayoutOptionData::ENUMSET_LITERAL)) {
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
            «if (type.equals(LayoutOptionData::ENUMSET_LITERAL)) 
            '''
            <div class="alert alert-info">
             To textually specify enumsets pass a string with the desired values separated by a whitespace.  
             <code> de.cau.cs.kieler.enumProp: "VAL_A VAL_B VAL_C"</code>
            </div>
            '''»
            «if (!rawAppend) generateBackButton(processingExchange)»
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
        var byte[] data = ServerLayoutMetaDataService::getInstance().getPreviewImage(id)
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
                    <input class="btn btn-default" type="submit" value="Back"/>
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
                var String c = ""
                do {
                    e = e + 1
                    if (e < text.length) {
                        c = text.charAt(e) + ""
                    }
                } while (e < text.length && !c.equals(" ") && !c.equals("\t")
                    && !c.equals("\r") && !c.equals("\n"))
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
