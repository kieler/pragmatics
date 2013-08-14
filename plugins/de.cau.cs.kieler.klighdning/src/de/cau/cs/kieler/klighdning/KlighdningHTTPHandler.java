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
package de.cau.cs.kieler.klighdning;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighdning.viewer.SVGBrowsingViewer;

/**
 * @author uru
 */
public class KlighdningHTTPHandler extends AbstractHandler {

    private File docRoot;

    private SVGBrowsingViewer getViewer;

    private HtmlGenerator gen = new HtmlGenerator();

    /**
     * @param docRoot
     *            the root of the models folders
     */
    public KlighdningHTTPHandler(final File docRoot) {
        this.docRoot = docRoot;
        this.getViewer = new SVGBrowsingViewer();
    }

    /**
     * {@inheritDoc}
     */
    public void handle(final String target, final Request baseRequest,
            final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {

        // decide depending on the http target
        if (target.startsWith("/content")) {
            /*----------------------------------------------------------------------------
             *  Return the a tree view of the root folder's content
             */
            String html = gen.toHtmlRoot(docRoot);

            response.setContentType("text/html;charset=utf8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            response.getWriter().println(html);

        } else if (target.startsWith("/resource")) {
            /*----------------------------------------------------------------------------
             *  Return a specific resource as SVG this is mainly called by perma links.
             */
            String path = target.replace("/resource", "");

            ResourceSet rs = new ResourceSetImpl();

            // MOML
            Map<String, Boolean> parserFeatures = Maps.newHashMap();
            parserFeatures.put("http://xml.org/sax/features/validation", Boolean.FALSE);
            parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
                    Boolean.FALSE);
            parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd",
                    Boolean.FALSE);

            rs.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
            rs.getLoadOptions().put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
            // rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
            // .put("xml", new MomlResourceFactoryImpl());

            System.out.println("Loading resource: " + path);
            final Resource r =
                    rs.getResource(URI.createFileURI(new File(docRoot, path).getAbsolutePath()),
                            true);

            try {
                // translate and set the model
                KNode model = LightDiagramServices.translateModel(r.getContents().get(0), null);
                getViewer.setModel(model, true);

                // retrieve the perma link infos
                String perma = request.getParameterMap().get("perma")[0];
                // transform
                String transform = "";
                String[] transformArr = request.getParameterMap().get("transform");
                if (transformArr != null && transformArr.length > 0) {
                    transform = transformArr[0];
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
            response.getWriter().println(gen.permaLinkPage(svg));

        } else if (target.startsWith("/refreshGit")) {
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
        }

    }
}
