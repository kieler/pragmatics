/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighdning.handler;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighdning.viewer.SVGBrowsingViewer;
import de.cau.cs.kieler.klighdning.viewer.SVGLayoutProvider;

/**
 * @author uru
 */
public class KlighdningHTTPHandler extends AbstractHandler {

    private File docRoot;

    private SVGBrowsingViewer getViewer;

    private HtmlGenerator gen = new HtmlGenerator();
    
    private static final String TARGET_TREE_NAVIGATION = "/json/content";
    private static final String TARGET_TEXT_FORMATS = "/textualFormats";
    private static final String TARGET_RESOURCE = "/resource";
    private static final String TARGET_GIT_REFRESH = "/refreshGit";

    /**
     * @param docRoot
     *            the root of the models folders
     */
    public KlighdningHTTPHandler(final File docRoot) {
        this.docRoot = docRoot;
        this.getViewer = new SVGBrowsingViewer();
        gen.setRoot(docRoot);
    }

    /**
     * {@inheritDoc}
     */
    public void handle(final String target, final Request baseRequest,
            final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {

        // decide depending on the http target
        if (target.startsWith(TARGET_TREE_NAVIGATION)) {
            /*----------------------------------------------------------------------------
             *  Return the navigation for tree based browsing.
             */
            String json = "";
            
            if (target.equals("/json/content/")) {
                json =  gen.toJson(docRoot);
            } else {
                String path = target.replace("/json/content/", "");
                json = gen.toJson(new File(docRoot, path));
            }
            
            response.setContentType("application/json;charset=utf8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            response.getWriter().println(json);
            
        } else if (target.startsWith(TARGET_RESOURCE)) {
            /*----------------------------------------------------------------------------
             *  Return a specific resource as SVG this is mainly called by perma links.
             */
            String path = target.replace("/resource", "");

            ResourceSet rs = new ResourceSetImpl();

            Map<String, Boolean> parserFeatures = Maps.newHashMap();
            parserFeatures.put("http://xml.org/sax/features/validation", Boolean.FALSE);
            parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
                    Boolean.FALSE);
            parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd",
                    Boolean.FALSE);

            rs.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
            rs.getLoadOptions().put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);

            File file = new File(docRoot, path);
            System.out.println("Loading resource: " + path);
            final Resource r =
                    rs.getResource(URI.createFileURI(file.getAbsolutePath()),
                            true);

            boolean hasChanged = false;
            try {
                // translate and set the model
                KNode model = LightDiagramServices.translateModel(r.getContents().get(0), null);
                getViewer.setModel(model, true);

                // retrieve the perma link infos
                // perma is the information about expanded elements
                String perma = request.getParameterMap().get("perma")[0];
                // transform
                String transform = "";
                String[] transformArr = request.getParameterMap().get("transform");
                if (transformArr != null && transformArr.length > 0) {
                    transform = transformArr[0];
                }
                
                // get timestamp
                String cs = request.getParameterMap().get("cs")[0];
                Long hash = Hashing.adler32().hashBytes(Files.toByteArray(file)).asLong();
                if (Long.valueOf(cs) != hash) {
                    hasChanged = true;
                }
                
                // apply perma link
                getViewer.applyPermalink(perma, transform);

            } catch (Exception e) {
                // catch any error
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                try {
                    response.getWriter().println("ERROR: Unable to handle the selected model.");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            // answer the request
            response.setContentType("text/html;charset=utf8");
            response.setCharacterEncoding("utf8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);

            // pass the svg
            String svg = SVGLayoutProvider.getInstance().layout(getViewer, false);
            // assemble parameter string for switch to interactive mode
            String params = "?path=" + path;
            for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                if (entry.getValue() != null && entry.getValue().length > 0) {
                    params +=
                            "&" + entry.getKey() + "="
                                    + URLEncoder.encode(entry.getValue()[0], "utf8");
                }
            }
            response.getWriter().println(gen.permaLinkPage(svg, hasChanged, params));

        } else if (target.startsWith(TARGET_GIT_REFRESH)) {
            /*----------------------------------------------------------------------------
             *  Tries to refresh the root directory if it is a git repository.
             */

            // create a process that executes git pull
            // the git bin has to be within the system path
            ProcessBuilder pb = new ProcessBuilder("git", "pull");
            pb.directory(docRoot);

            Process run = pb.start();
            try {
                // wait for the git process to finish
                run.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // answer the request
            String success = CharStreams.toString(new InputStreamReader(run.getInputStream()));
            if (!Strings.isNullOrEmpty(success)) {
                // send the error
                response.getWriter().println(success);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                String error = CharStreams.toString(new InputStreamReader(run.getErrorStream()));
                response.getWriter().println(error);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            // general info
            response.setContentType("text/html;charset=utf8");
            response.setCharacterEncoding("utf8");
            baseRequest.setHandled(true);
            
        } else if (target.startsWith(TARGET_TEXT_FORMATS)) {
            
            // get the registered formats
            Registry r = Resource.Factory.Registry.INSTANCE;
            Set<String> formats = r.getExtensionToFactoryMap().keySet();
           
            String json = "[\"" + Joiner.on("\", \"").join(formats) + "\"]";
            
            response.getWriter().println(json);
            response.setContentType("application/json;charset=utf8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
        }
    }
}
