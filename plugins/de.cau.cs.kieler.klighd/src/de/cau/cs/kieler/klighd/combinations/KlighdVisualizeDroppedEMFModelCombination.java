/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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
package de.cau.cs.kieler.klighd.combinations;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.klighd.effects.KlighdDiagramEffect;
import de.cau.cs.kieler.klighd.triggers.KlighdResourceDropTrigger.KlighdResourceDropState;

/**
 * A view management combination which loads EMF resources which have been dropped on KLighD views
 * and updates the view with the model loaded from the resource.
 * 
 * @author mri
 */
public class KlighdVisualizeDroppedEMFModelCombination extends AbstractCombination {

    /**
     * Loads EMF resources dropped on a KLighD view and schedules diagram effects to visualize
     * loaded EMF models in that view.
     * 
     * @param state
     *            the resource drop state
     */
    public void execute(final KlighdResourceDropState state) {
        IResource resource = state.getResource();
        if (resource instanceof IFile) {
            IFile file = (IFile) resource;
            Object model = loadModel(file);
            if (model != null) {
                // show the model in the view by scheduling the required effect
                schedule(new KlighdDiagramEffect(state.getViewId(), file.getName(), model));
            }
        }
    }

    /**
     * Loads an EMF model from the given file.
     * 
     * @param file
     *            the file
     * @return the model
     */
    private Object loadModel(final IFile file) {
        // TransactionalEditingDomain.Factory factory = TransactionalEditingDomain.Factory.INSTANCE;
//        TransactionalEditingDomain transactionalEditingDomain = DiagramEditingDomainFactory.INSTANCE
//                .createEditingDomain();
        // factory.createEditingDomain();
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.createResource(URI.createPlatformResourceURI(file
                .getFullPath().toOSString(), true));
        try {
            resource.load(null);
        } catch (IOException e) {
            return null;
        }
        if (resource.getContents().size() > 0) {
            return resource.getContents().get(0);
        } else {
            return null;
        }
    }

}
