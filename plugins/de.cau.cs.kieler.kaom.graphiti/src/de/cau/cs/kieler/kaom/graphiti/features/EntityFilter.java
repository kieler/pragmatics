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
package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;

/**
 * @author atr
 * This class is used in order to show the property view of the selected element.
 */
public class EntityFilter extends AbstractPropertySectionFilter {

    /**
     * used to filter out the elements whose property view has to be enabled.
     * {@inheritDoc}
     */
    @Override
    protected boolean accept(final PictogramElement pictogramElement) {

        EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(
                pictogramElement);

        if (eObject instanceof Entity) {
            return true;
        } else if (eObject instanceof Link) {
            return true;
        } else if (eObject instanceof Port) {
            return true;
        } else if (eObject instanceof Relation) {
            return true;
        }
        return false;
    }

}
