/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.gml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.xtext.resource.IResourceFactory;

/**
 * A dedicated resource to load {@link GMLModel}s in the context of 
 * EMF. This is for instance handy when opening {@link GMLModel}s 
 * with the KLighD editor, as it resolves file resources 
 * based on the file extension and uses EMF's infrastructure
 * to actually load the model.
 * 
 * @author uru
 */
public class GmlResource extends ResourceImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doLoad(final InputStream inputStream, final Map<?, ?> options)
            throws IOException {

        try {
            getContents().clear();
            GMLModel model = GMLParser.parse(inputStream);
            if (model != null) {
                getContents().add(model);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doSave(final OutputStream outputStream, final Map<?, ?> options)
            throws IOException {
        if (!getContents().isEmpty()) {
            GMLModel model = (GMLModel) getContents().get(0);
            String serialized = GMLSerializer.serialize(model);
            try {
                outputStream.write(serialized.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@link IResourceFactory} for {@link GmlResource}s to be registered 
     * via EMF's 'extension_parser' extension point.
     */
    public static class GmlResourceFactory implements IResourceFactory {

        /**
         * {@inheritDoc}
         */
        public Resource createResource(final URI uri) {
            GmlResource gr = new GmlResource();
            gr.setURI(uri);
            return gr;
        }
    }
}
