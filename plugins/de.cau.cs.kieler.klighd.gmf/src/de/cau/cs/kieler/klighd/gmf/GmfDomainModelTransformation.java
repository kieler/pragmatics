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
package de.cau.cs.kieler.klighd.gmf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;

import de.cau.cs.kieler.klighd.TransformationContext;
import de.cau.cs.kieler.klighd.transformations.AbstractTransformation;

/**
 * A transformation from an EMF domain model to a compatible GMF notation model.
 * 
 * @author mri
 */
public class GmfDomainModelTransformation extends AbstractTransformation<EObject, Diagram> {

    /** the last domain model a diagram was requested for. */
    private EObject lastDomainModel = null;
    /** the last diagram model requested for a domain model. */
    private Diagram lastDiagramModel = null;

    /**
     * {@inheritDoc}
     */
    public Diagram transform(final EObject model,
            final TransformationContext<EObject, Diagram> transformationContext) {
        return createDiagram(model);
    }
    
    /**
     * {@inheritDoc}
     */
    public Object getSourceElement(final Object object) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public Object getTargetElement(final Object object) {
        return null;
    }

    private Diagram createDiagram(final EObject model) {
        if (model == lastDomainModel) {
            return lastDiagramModel;
        }
        // FIXME not functional yet
        Diagram diagram = ViewService.createDiagram(model, null, PreferencesHint.USE_DEFAULTS);
        if (diagram != null) {
            lastDomainModel = model;
            lastDiagramModel = diagram;
        }
        return diagram;
    }
    
}
