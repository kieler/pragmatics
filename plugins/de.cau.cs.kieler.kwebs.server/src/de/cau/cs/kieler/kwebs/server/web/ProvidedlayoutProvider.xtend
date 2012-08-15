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

import com.sun.net.httpserver.Headers
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
import org.eclipse.emf.common.util.EList

/**
 * This class implements a web content provider for displaying the service meta data in HTML format.
 *
 * @author swe
 * @author msp
 */
class ProvidedlayoutProvider 
	extends AbstractProvider
{

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
		RequestData requestData
	) 
	{
		return 
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
						border-width 	 : 2px;
						border-style 	 : ridge;
						border-color	 : #000000;
						table-layout	 : fixed;
					}
					table.listing thead tr > th, table.listing tbody tr > td {
						font-family 	 : Verdana, Arial; 
						font-size		 : 8pt; 
						border-style	 : none;
						text-align	     : left;
						padding          : 10px;
					}
					table.listing thead tr > th {
						font-weight		 : bold;
						border-bottom	 : 1px solid;
					}
					table.advertisement {
						background-color : #a5F3a5;
						border           : 1px solid;
						padding          : 10px;
					}
					table.advertisement tr td > p{
						font-family 	 : Verdana, Arial; 
						font-size		 : 8pt;
						border-style	 : none;
						text-align	     : left;
					}
				//-->
			</style>
			'''
	}
    
    /**
     * 
     */
	override CharSequence getBody(
		RequestData requestData
	) 
	{		
        val Map<String, String> params = requestData.params
        if (params.containsKey(PARAM_ALGORITHM)) {
            return generateForAlgorithm(requestData)
        } else if (params.containsKey(PARAM_OPTION)) {
            return generateForOption(requestData, null, false)
        } else if (params.containsKey(PARAM_FORMAT)) {
            return generateForFormat(requestData)
        } else if (params.containsKey(PARAM_PREVIEWIMAGE)) {
            Logger::log(//FIXME Xtend2 can not handle static enum members            	
            	"Preview image handled without override."
            )
            return ''''''
        } 
        return generateOverview(requestData)            
	}
    
    /**
     * 
     */
    override boolean providerOverride(
		RequestData requestData
	) 
	{
        val Map<String, String> params = requestData.params
		if (params.containsKey(PARAM_PREVIEWIMAGE)) {
            generateForPreviewImage(requestData)
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
		RequestData requestData
	) 
	{
        val Map<String, String> params = requestData.params
        val String 				id 	   = params.get(PARAM_ALGORITHM)
        if (id == null) {
            return ''''''
        }
        val LayoutAlgorithm algorithm = serviceData.layoutAlgorithms.filter(algorithm | algorithm.id.equals(id)).head
        if (algorithm == null) {
            return ''''''
        }
        val EList<KnownOption> options = algorithm.knownOptions
        return 
        	'''
			<p class='title'>Algorithm Details</p>
			<p>Name: «algorithm.name»<br/></p>
			<p>Identifier: «algorithm.id»<br/></p>
			«if (algorithm.description != null) {
				'''<p>«generateHypertext(algorithm.description)»</p>'''
			}»
			<p class='title'>A Quick Preview</p>
			<p>
				<div align='center'>
					<img src='/ProvidedLayout.html?previewimage=«algorithm.previewImagePath»'/>
				</div>
			</p>
			<p class='title'>Supported Layout Options</p>
			<p>
				<div align='center'>
					<table cellspacing='0' cellpadding='5' class='listing'>
						<thead><tr><th>Name</th></tr></thead>
						<tbody>
							«options.map(option | {			
					            return 
						            '''
						            <tr class='«
						            	if (options.indexOf(option) % 2 == 0) "even" else "odd"
						            »' onclick='document.location.href="Providedlayout.html?option=«
						            	option.option.id
						            »";'>
						            	<td align='left'>«option.option.name»</td>
						            </tr>
						            '''
						    }).join»
						</tbody>
					</table>
				</div>
			</p>
			«generateBackButton(requestData)»
        	'''
    }

    /**
     * Generates web page content giving an overview of the meta data.
     * @param requestData
     *            the data of the request
     */
    def private CharSequence generateOverview(
		RequestData requestData
	) 
	{
		val EList<LayoutAlgorithm> algorithmns = serviceData.layoutAlgorithms
		val EList<SupportedFormat> formats     = serviceData.supportedFormats
        return
        	'''
			<p class='title'>Provided Layout</p>
			<p>
				On this page you can inform yourself about the layout the service provides. You can take a look at the <i>algorithms</i> it exposes and see how
				you can configure them to your specific needs by setting their <i>options</i> accordingly. Furthermore you can take a look at the <i>formats</i>
				the service understands. The formats are used to exchange graphs in serialized form between your application and the service and we try to provide
				a useful set of them to make the service as easily consumeable for you as possible. If you would like to use a format not currently supported please
				feel free to <a href='Contact.html'>contact</a> us.
			</p>
			<p class='title'>Service Details</p>
			<p>Currently executed version: «serviceData.version»<br/></p>
			<p class='title'>How to select a Layout Algorithm &#63;</p>
			<p>
				In order to select a specific layout algorithm when doing layout with the provided service, you can use the following layout option:
			</p>
			<p>
				<div align='center'>
					<table class='advertisement'>
						<tr>
							<td align='left'>
								«generateForOption(requestData, LayoutOptions::ALGORITHM.id, true)»
							</td>
						</tr>
					</table>
				</div>
			</p>
			<p class='title'>Supported Algorithms</p>
			<p>
				The following list shows you the list of layout algorithms currently supported by this service.
			</p>
			<p>
				<div align='center'>
					<table cellspacing='0' cellpadding='5' class='listing'>
						<thead><tr><th>Name</th><th>Category</th><th>Type</th><th>Version</th></tr></thead>
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
					            return 
									'''
									<tr class='«
										if (algorithmns.indexOf(algorithm) % 2 == 0) "even" else "odd"
									»' onclick='document.location.href="Providedlayout.html?algorithm=«
										algorithm.id
									»";'>
										<td>«algorithm.name»</td>
										<td>«category»</td>
										<td>«type»</td>
										<td>«version»</td>
									</tr>'''
					        }).join»
						</tbody>
					</table>
				</div>
			</p>	
			<p class='title'>Supported Formats</p>
			<p>
				The following list shows you the list of formats you can use to transfer the graphs you wish to have calculated a layout on to this service.
			</p>
			<p>
				<div align='center'>
					<table cellspacing='0' cellpadding='5' class='listing'>
						<thead><tr><th>Name</th><th>Identifier</th></tr></thead>
						<tbody>
					        «formats.map(format | {
					        	return 
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
			</p>'''	        
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
		RequestData requestData, 
        String      algorithmId,
        boolean     rawAppend
	) 
	{
        val Map<String, String> params = requestData.params
        val String              id     = if (params.get(PARAM_OPTION) != null) params.get(PARAM_OPTION) else algorithmId
        if (id == null) {
            return ''''''
        }
        val LayoutOption option = serviceData.layoutOptions.filter(option | option.id.equals(id)).head 
        if (option == null) {
            return ''''''
        }
	    val String type         = option.type
	    val String defaultValue = if (option.^default != null && option.^default.trim.length > 0) option.^default.trim else "&lt;NONE&gt;"
        return 
        	'''
			«if (!rawAppend) '''<p class='title'>Layout Option Details</p>'''»
			<p>Name: «option.name»<br/></p>
			<p>Identifier: «option.id»<br/></p>
			<p>Type: «if (type.equals(LayoutOptionData::REMOTEENUM_LITERAL)) "enumeration" else type»<br/></p>
			«if (type.equals(LayoutOptionData::REMOTEENUM_LITERAL)) {
			    '''
			    <p>
			    	Possible Values: «option.remoteEnum.values.map(value | {
			            '''«value», '''
			        }).join»
			    	<br/>
			    </p>
			    '''
			}»
			<p>Default Value: «defaultValue»<br/></p>
			<p class='title'>Description</p>
			«if (option.description != null) {
			    '''
			    <p>
			    	«generateHypertext(option.description)»
			    </p>
			    '''
			}»
			«if (!rawAppend) generateBackButton(requestData)»'''
    }
    
    /**
     * Generates web page content describing a format.
     * 
     * @param requestData
     *            the data of the request
     */
    def private generateForFormat(
		RequestData requestData
	) 
	{
        val Map<String, String> params = requestData.params
        val String 				id     = params.get(PARAM_FORMAT)
        if (id == null) {
            return ''''''
        }
        val SupportedFormat format = serviceData.supportedFormats.filter(format | format.id.equals(id)).head
        if (format == null) {
            return ''''''
        }
        return
        	'''
			<p class='title'>Format Details</p>
			<p>Name: «format.name»<br/></p>
			<p>Identifier: «format.id»<br/></p>
			«if (format.description!= null) {
			    '''
				<p class='title'>Description</p>
				<p>
					«generateHypertext(format.description)»
				</p>'''
	        }»
	        «generateBackButton(requestData)»'''
    }
    
    /** Path to the image which is shown when a preview image is not given by a plug in. */
    private static String PREVIEWIMAGE_UNAVAILABLE
        = "server/kwebs/web/images/unavailable.png"
    
    /**
     * Generates a preview image.
     * @param requestData
     *            the data of the request
     */
    def private CharSequence generateForPreviewImage(
		RequestData requestData
	) 
	{
        val Map<String, String> params = requestData.params
        val String 			    id     = params.get(PARAM_PREVIEWIMAGE)
        if (id == null) {
            return ''''''
        }
        var byte[] data = ServerLayoutDataService::getInstance().getPreviewImage(id)
        if (data == null) {
            data = Resources::readFileOrPluginResourceAsByteArray(Application::PLUGIN_ID, PREVIEWIMAGE_UNAVAILABLE)
        }
        if (data != null) {
            requestData.setContent(data)
            requestData.setMimetype(
                WebContentHandler::guessMimeType(requestData.getResource())
            )
        }
    }
    
    /** Name of the referer entry in the headers of a HTTP request. */
//    private static String HEADER_REFERER = "Referer"
    
    /**
     * Inserts a "Back" button in the response data buffer.
     * 
     * @param requestData
     *            the request data
     * @param buffer
     *            the response buffer to append to
     */
    def private CharSequence generateBackButton(
		RequestData requestData
	) 
	{
//        val Headers headers = requestData.exchange.requestHeaders
		return
			'''
			<p>
				<div align='center'>
«««					<form action="«        
«««				        if (headers.containsKey(HEADER_REFERER)) {
«««				            '''«headers.getFirst(HEADER_REFERER)»'''
«««				        } else {
«««				            '''javascript:history.back();'''
«««				        }»" method="POST">
«««				        <input type="submit" value="Back"/>
«««				    </form>
					<form action="javascript:history.back();" method="POST">
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
    	val StringBuilder builder = new StringBuilder()
        var int 		  p       = 0
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
        return '''«builder.toString»'''
    }
    
}
