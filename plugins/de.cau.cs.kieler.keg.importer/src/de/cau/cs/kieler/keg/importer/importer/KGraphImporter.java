/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
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

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.importer.AbstractImporter;
import de.cau.cs.kieler.keg.importer.ImportManager;
import de.cau.cs.kieler.keg.importer.ImportUtil;
import de.cau.cs.kieler.keg.importer.ImporterOption;

/**
 * A KEG importer for the KGraph.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class KGraphImporter extends AbstractImporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "kgraph" }; //$NON-NLS-1$
    /** the xtend transformation file. */
    private static final String XTEND_TRANSFORMATION_FILE = "kgraph2keg.ext"; //$NON-NLS-1$
    /** the xtend extension which is performing the transformation. */
    private static final String XTEND_TRANSFORMATION = "transform"; //$NON-NLS-1$

    /** the option for the transfer of layout information. */
    private static final ImporterOption<Boolean> OPTION_TRANSFER_LAYOUT =
            new ImporterOption<Boolean>("kgraph.transferLayout", //$NON-NLS-1$
                    Messages.KGraphImporter_transfer_layout_description, true);

    /**
     * The edge direction.
     */
    private enum EdgeDirection {
        DIRECTED, UNDIRECTED
    }

    /** the option for the edge direction. */
    private static final ImporterOption<EdgeDirection> OPTION_EDGE_DIRECTION =
            new ImporterOption<EdgeDirection>("kgraph.edgeDirection", //$NON-NLS-1$
                    Messages.KGraphImporter_edge_direction_description, //$NON-NLS-1$
                    EdgeDirection.DIRECTED);

    /**
     * Constructs a KGraphImporter.
     */
    public KGraphImporter() {
        addOption(OPTION_TRANSFER_LAYOUT);
        addOption(OPTION_EDGE_DIRECTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return Messages.KGraphImporter_kgraph_name;
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
            parameters.add(options.getProperty(OPTION_EDGE_DIRECTION) == EdgeDirection.DIRECTED);
            node =
                    ImportUtil.transformModel2KEGGraph(XTEND_TRANSFORMATION_FILE,
                            XTEND_TRANSFORMATION, parameters, inputStream, null, monitor,
                            "de.cau.cs.kieler.core.kgraph.KGraphPackage"); //$NON-NLS-1$
        } catch (IOException e) {
            throw new WrappedException(Messages.KGraphImporter_io_error, e);
        } catch (TransformException e) {
            throw new WrappedException(Messages.KGraphImporter_transformation_error, e);
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
