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
package de.cau.cs.kieler.keg.importer.importer;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.WrappedException;

import net.ogdf.ogml.util.OgmlResourceFactoryImpl;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.importer.AbstractImporter;
import de.cau.cs.kieler.keg.importer.ImportManager;
import de.cau.cs.kieler.keg.importer.ImportUtil;
import de.cau.cs.kieler.keg.importer.ImporterOption;

/**
 * A KEG importer for the OGML file format.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class OGMLImporter extends AbstractImporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "ogml" }; //$NON-NLS-1$
    /** the xtend transformation file. */
    private static final String XTEND_TRANSFORMATION_FILE = "ogml2keg.ext"; //$NON-NLS-1$
    /** the xtend extension which is performing the transformation. */
    private static final String XTEND_TRANSFORMATION = "transform"; //$NON-NLS-1$

    /** the option for the transfer of layout information. */
    private static final ImporterOption<Boolean> OPTION_TRANSFER_LAYOUT =
            new ImporterOption<Boolean>("ogml.transferLayout", //$NON-NLS-1$
                    Messages.OGMLImporter_transfer_layout_description, true);

    /**
     * Constructs a KGraphImporter.
     */
    public OGMLImporter() {
        addOption(OPTION_TRANSFER_LAYOUT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return Messages.OGMLImporter_ogml_name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultExtension() {
        return SUPPORTED_FILE_EXTENSIONS[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getExtensions() {
        return SUPPORTED_FILE_EXTENSIONS;
    }

    /**
     * {@inheritDoc}
     */
    public Node doImport(final InputStream inputStream, final IPropertyHolder options,
            final IKielerProgressMonitor monitor) {
        Node node = null;
        try {
            List<Object> parameters = new LinkedList<Object>();
            node =
                    ImportUtil.transformModel2KEGGraph(XTEND_TRANSFORMATION_FILE,
                            XTEND_TRANSFORMATION, parameters, inputStream,
                            new OgmlResourceFactoryImpl(), monitor,
                            "net.ogdf.ogml.OgmlPackage", //$NON-NLS-1$
                            "de.cau.cs.kieler.core.kgraph.KGraphPackage", //$NON-NLS-1$
                            "de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage"); //$NON-NLS-1$
        } catch (IOException e) {
            throw new WrappedException(Messages.OGMLImporter_io_error, e);
        } catch (TransformException e) {
            throw new WrappedException(Messages.OGMLImporter_transformation_error, e);
        }
        return node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doDiagramPostProcess(final IPath diagramPath, final IPropertyHolder options) {
        if (options.getProperty(OPTION_TRANSFER_LAYOUT)) {
            ImportUtil.applyContainedLayout(diagramPath,
                    options.getProperty(ImportManager.OPTION_OPEN_DIAGRAM));
        }
    }

}
