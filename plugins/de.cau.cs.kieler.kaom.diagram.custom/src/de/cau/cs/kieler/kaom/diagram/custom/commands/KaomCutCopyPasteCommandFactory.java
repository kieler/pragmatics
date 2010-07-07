/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.diagram.custom.commands;

import java.util.Arrays;
import java.util.List;

import org.osgi.framework.Bundle;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.diagram.custom.KaomDiagramCustomPlugin;
import de.cau.cs.kieler.ksbase.ui.utils.AbstractCutCopyPasteCommandFactory;

/**
 * Creates the cut, copy and paste commands from ksbase.
 * 
 * @author soh
 */
public class KaomCutCopyPasteCommandFactory extends
        AbstractCutCopyPasteCommandFactory {

    /** The transformation file. */
    private static final String FILE = "/transformations/copyAndPaste.ext";

    /** The base package of the underlying meta model. */
    private static final String[] MODEL = { "de.cau.cs.kieler.kaom.KaomPackage" };

    /** The types touched by cut copy and paste. */
    private static final Class<?>[] TYPES = { Entity.class, Relation.class,
            Link.class, Port.class };

    /**
     * {@inheritDoc}
     */
    @Override
    protected Bundle getBundle() {
        return KaomDiagramCustomPlugin.getDefault().getBundle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFile() {
        return FILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> getModel() {
        return Arrays.asList(MODEL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<?>[] getTypes() {
        return TYPES;
    }
}
