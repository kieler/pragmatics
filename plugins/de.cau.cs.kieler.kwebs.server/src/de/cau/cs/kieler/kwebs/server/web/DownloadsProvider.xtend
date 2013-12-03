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
import de.cau.cs.kieler.kwebs.server.configuration.Configuration
import de.cau.cs.kieler.kwebs.server.logging.Logger
import de.cau.cs.kieler.kwebs.server.util.Resources
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Array
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Enumeration
import java.util.Map
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import org.eclipse.core.runtime.Platform
import org.eclipse.xtext.xbase.lib.Pair
import org.osgi.framework.Bundle

/**
 * This class implements a web content provider for displaying the service meta data in HTML format.
 *
 * @author  swe
 */
class DownloadsProvider 
    extends AbstractProvider
{
    
    /**
     * 
     */
    override CharSequence getHeaders(
        ResourceProcessingExchange processingExchange
    ) 
    {
        return 
            '''
            <script type='text/javascript'>
                function toggle(toggleId, textId) {
                    var e = document.getElementById(toggleId);
                    if (e != null) {
                        var visible = (e.style.visibility == "visible"); 
                        if (visible) {
                            e.style.visibility = "hidden";
                            e.style.display    = "none";
                        } else {
                            e.style.visibility = "visible";
                            e.style.display    = "block";
                        }
                        var t = document.getElementById(textId);
                        if (t != null) {
                            if (visible) {
                                t.innerHTML = "to view a description of how to set up and use a secured"
                                            + " service in the KIELER modeling environment."
                           } else {
                                t.innerHTML = "to close the description."
                            }
                        }
                    }
                }
            </script>
            <style type='text/css'>
                table.multifigure td {
                    padding : 5px 5px 5px 5px;
                }
                .figuretitle {
                    font-size         : 10pt;
                    font-style      : italic;
                    font-family     : Consolas, Menlo, Monaco, Lucida Console, Liberation Mono, DejaVu Sans Mono, Bitstream Vera Sans Mono, Courier New, monospace, serif;
                    text-decoration : none;
                }
                .figuretitleindex {
                    font-weight     : bold;
                }
            </style>
            '''
    }
    
    /**  */
    private static String TRUSTSTORE_SUFFIX
        = "security/client.jks"
        
    /**
     * 
     */
    override CharSequence getBody(
        ResourceProcessingExchange processingExchange
    ) 
    {
        val Configuration config = Configuration::INSTANCE
        val String        urlTS  = config.getConfigProperty(Configuration::SUPPORTINGSERVER_PUBLIC_ADDRESS) + "/" + TRUSTSTORE_SUFFIX
        return 
            '''
            <div class="col-md-8 col-md-offset-2">
            <h2>
                Downloads
            </h2>
            <p>
                This page provides some useful downloads regarding service based layout with KWebs.
            </p>
            «if (config.getConfigPropertyAsBoolean(Configuration::FRONTEND_HTTPS_EXPOSE_TRUSTSTORE, false)) {
                '''
                <h3>Secure Access</h3>
                <p>
                    In order to use the layout service secured by HTTPS, you need to download the <a href='«urlTS»'>trust store</a>.
                    Furthermore, you will need the password to access the key that is stored in it and that is necessary
                    for opening a secure connection. The policy for getting this password depends on your service host.
                </p>
                <p>
                    Click <a href='javascript:toggle("confdescr", "conftext");'>here</a> <span id='conftext'>to view a description of how to set up and use a secured
                    service in the KIELER modeling environment.</span>
                </p>
                <div id='confdescr' style='display:none; visibility:hidden;'>
                    <h4>How to set up and use a secured service</h4>
                    <p>
                        The following illustration demonstrates how you can configure an HTTPS based service in the KIELER modeling
                        environment. After you downloaded the trust store, e.g. to <i>C:\kwebs\</i>, you create a server configuration that
                        points to the appropriate service endpoint, in this case <i>https://layout.rtsys.informatik.uni-kiel.de:9443/layout</i>.
                        Then you configure the trust store and enter the password that was given to you by your host.
                    </p>
                    <p>
                        <div align='center'>
                            <img src='images/ts_conf.png'/>
                            <div class='figuretitle'>
                                <span class='figuretitleindex'>Figure 1:</span> How to create a secured server configuration
                            </div>
                        </div>
                    </p>
                    <p>
                        Then you may want to click the <i>Check Connection</i> button to test whether you have configured the service correctly
                        before you store the server configuration permanently by using the <i>OK</i> button. When you have stored your server
                        configuration make sure it is selected for doing remote layout:
                    </p>
                    <p>
                        <div align='center'>
                            <img src='images/rm_select.png'/>
                            <div class='figuretitle'>
                                <span class='figuretitleindex'>Figure 2:</span> How to select a server configuration
                            </div>
                        </div>
                    </p>
                    <p>
                        All you have to do from now on is to make sure you have remote layout enabled in your KIELER modeling environment. You can
                        verify this by looking at the the status bar. If it displays a litte earth symbol, like figure 3 (a) shows, you work on remote
                        layout. If you see a little house symbol, like figure 3 (b) shows, you are on local layout. You can switch between these modes
                        in the context menu that appears when you do a click on it with the right mouse button, as can be seen in figure 3 (c).
                    </p>
                    <p>
                        <div align='center'>
                            <table class='multifigure'>
                                <th><td></td><td></td><td></td></th>
                                <tr>
                                    <td><img src='images/sym_rl.png'/></td>
                                    <td><img src='images/sym_ll.png'/></td>
                                    <td><img src='images/sym_ctx.png'/></td>
                                </tr>
                                <tr>
                                    <td><div class='figuretitle'>(a)</div></td>
                                    <td><div class='figuretitle'>(b)</div></td>
                                    <td><div class='figuretitle'>(c)</div></td>
                                </tr>
                                <tfoot><td></td><td></td><td></td></tfoot>
                            </table>
                            <div class='figuretitle'>
                                <span class='figuretitleindex'>Figure 3:</span> The symbols for local and remote layout and the context menu
                            </div>
                        </div>
                    </p>
                </div>
                '''    
            }»
            <h3>Layout on the Command Line</h3>
            <p>
                There is also a Java-based command line tool that can do layout and format translations.
                You can download it from <a href='http://www.informatik.uni-kiel.de/rtsys/kieler/downloads/tools-libraries/'>the KIELER downloads page</a>.
            </p>
            </div>
            '''
    }
    
    /** Constant for query parameter 'algorithm'. */
    private static String PARAM_FEATURE_DOWNLOAD = "feature"
    
    /** */
    private static Map<String, String> VALID_FEATURES = <String, String>newHashMap(
        new Pair<String, String>("ccl", "de.cau.cs.kieler.kwebs.tools")
    )
    
    /**
     * 
     */
    override boolean providerOverride(
        ResourceProcessingExchange processingExchange
    ) 
    {
        val Map<String, String> params = processingExchange.getParams()
        if (params.containsKey(PARAM_FEATURE_DOWNLOAD)) {
            if (!VALID_FEATURES.containsKey(params.get(PARAM_FEATURE_DOWNLOAD))) {
                Logger::log(
                    "Invalid feature request: " + processingExchange.getExchange().getRequestURI()
                )
                throw new IllegalArgumentException(
                    "Invalid feature request: " + processingExchange.getExchange().getRequestURI()
                )
            }
            doFeatureDownload(processingExchange)
            return true
        } 
        return false
    }
    
    /** */
    private SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MM yyyy HH:mm:ss")
    
    /**
     * 
     */
    def private void doFeatureDownload(
        ResourceProcessingExchange processingExchange
    ) 
    {        
        val Map<String, String> params  = processingExchange.getParams()
        val String              feature = VALID_FEATURES.get(params.get(PARAM_FEATURE_DOWNLOAD)) 
        try {
            
            val byte[] data = pluginAsByteArray(
                feature, "/", "*", true
            )
            
            val ResourceInformation resourceInformation
                = processingExchange.getResourceInformation()
            
            val String  now     = formatter.format(new Date())
            val Headers headers = resourceInformation.getAdditionalHeaders()
            
            headers.add("Expires:",                                                  now + " GMT")
            headers.add("Last-Modified:",                                            now + " GMT")
            headers.add("Cache-Control:",                   "no-store, no-cache, must-revalidate")
            headers.add("Cache-Control:",                             "post-check=0, pre-check=0")
            headers.add("Pragma:",                                                     "no-cache")
            
            resourceInformation.setName(feature + "_" + Resources::getPluginVersion(feature) + ".jar\"")
            resourceInformation.setContent(data)
            resourceInformation.setMimetype("application/octet-stream")
            
        } catch (Exception e) {
            Logger::log(
                "Could not create response for requested feature: " + feature
            )
            e.printStackTrace
        }
    }
    
    /**
     * 
     */
    def private byte[] pluginAsByteArray(
        String  pluginId, 
        String  root,
        String  filter, 
        boolean recurse
    ) 
    throws IOException
    {
        val Bundle                   bundle  = Platform::getBundle(pluginId)
        if (bundle == null) {
            throw new IOException("No such bundle: " + pluginId)
        }
        val ByteArrayOutputStream bytes   = new ByteArrayOutputStream()
        val ZipOutputStream        jar     = new ZipOutputStream(bytes)
        jar.setLevel(9)
        val Enumeration<URL>      entries = bundle.findEntries(root, filter, recurse)
        val byte[]                buffer  = Array::newInstance(typeof(byte), 4096) as byte[]
        var int                   read    = -1
        while (entries.hasMoreElements()) {
            val URL              entry = entries.nextElement()
            var String           path  = entry.getPath()
            if (!path.endsWith("/")) {
                if (path.startsWith("/")) {
                    path = path.substring(1)
                }
                try {
                    val InputStream   in    = Resources::getResourceStream(pluginId, path)
                    jar.putNextEntry(new ZipEntry(path))
                    while ((read = in.read(buffer)) > 0) {
                        jar.write(buffer, 0, read)
                    }
                    jar.closeEntry()
                } catch (Exception e) {
                    Logger::log(
                        Logger::severity_WARNING, "Could not add entry: " + entry, e
                    )
                }
            }
        }
        jar.flush()
        jar.close()
        return bytes.toByteArray();
    }
    
}
