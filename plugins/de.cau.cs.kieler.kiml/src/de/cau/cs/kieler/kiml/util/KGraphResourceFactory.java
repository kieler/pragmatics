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
package de.cau.cs.kieler.kiml.util;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * A custom resource factory that returns resources for KGraph xmi models, e.g. 
 * .kgt or .kgraph files. The dedicated resource takes care of loading and storing
 * custom layout properties within the model itself.
 * 
 * @see KimlUtil#loadDataElements(KNode)
 * @see KimlUtil#persistDataElements(KNode)
 * 
 * @author uru
 */
public class KGraphResourceFactory implements Factory {

    /**
     * {@inheritDoc}
     */
    public Resource createResource(final URI uri) {
        return new KGraphResource(uri);
    }

    /**
     * See the above class comment for further information. 
     */
    public static class KGraphResource extends XMIResourceImpl {

        /**
         * @param uri
         *            the desired uri.
         */
        public KGraphResource(final URI uri) {
            super(uri);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void load(final Map<?, ?> options) throws IOException {
            super.load(options);
            if (!this.getContents().isEmpty()) {
                EObject o = this.getContents().get(0);
                if (o instanceof KNode) {
                    // parse persisted key-value pairs using KIML's layout data service
                    KimlUtil.loadDataElements((KNode) o);
                    // validate layout data and references and fill in missing data
                    KimlUtil.validate((KNode) o);
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void save(final Map<?, ?> options) throws IOException {
            if (!this.getContents().isEmpty()) {
                EObject o = this.getContents().get(0);
                if (o instanceof KNode) {
                    KimlUtil.persistDataElements((KNode) o);
                }
            }

            super.save(options);
        }

    }

}
