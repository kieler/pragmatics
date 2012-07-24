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
package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.model.graphiti.ui.GraphitiNewWizard;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;

/**
 * New-wizard for Graphiti KAOM diagrams.
 * 
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class KaomNewWizard extends GraphitiNewWizard {

    /**
     * Creates a new-wizard for Graphiti KAOM diagrams.
     */
    public KaomNewWizard() {
        super("KAOM", KaomDiagramEditor.DIAGRAM_FILE_EXTENSION, KaomDiagramEditor.MODEL_FILE_EXTENSION,
                KaomDiagramEditor.DIAGRAM_TYPE, KaomDiagramEditor.EDITOR_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EObject createModel(final String name) {
        Entity entity = KaomFactory.eINSTANCE.createEntity();
        entity.setName(name);
        return entity;
    }

}
