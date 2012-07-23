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
package de.cau.cs.kieler.keg.ksbase.copypaste;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.keg.Edge;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.ksbase.KEGKsbasePlugin;
import de.cau.cs.kieler.ksbase.ui.utils.AbstractCutCopyPasteCommandFactory;

/**
 * Creates the cut, copy and paste commands from ksbase.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class KEGCutCopyPasteCommandFactory extends
        AbstractCutCopyPasteCommandFactory {

    /** the transformation file. */
    private static final String TRANSFORMATION_FILE =
            "/transformations/copyPaste.ext";

    /** the base packages of the underlying meta model. */
    private static final String[] MODEL = {
            "de.cau.cs.kieler.keg.KEGPackage",
            "de.cau.cs.kieler.core.kgraph.KGraphPackage" };

    /** the supported types. */
    private static final Class<?>[] TYPES = { Node.class, Edge.class };

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFile() {
        return TRANSFORMATION_FILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Bundle getBundle() {
        return KEGKsbasePlugin.getDefault().getBundle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<?>[] getTypes() {
        return TYPES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> getModel() {
        List<String> modelsList = new ArrayList<String>(MODEL.length);
        for (String s : MODEL) {
            modelsList.add(s);
        }
        return modelsList;
    }

    @Override
    public ICommand buildCopyCommand(final IDiagramWorkbenchPart part,
            final List<EObject> selection) {
        return super.buildCopyCommand(part, selection);
    }
}
