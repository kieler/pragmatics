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
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import de.cau.cs.kieler.core.annotations.NamedObject;

/**
 * Filter for showing the property view of the selected element.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class KaomPropertyFilter extends AbstractPropertySectionFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean accept(final PictogramElement pictogramElement) {
        EObject eObject;
        if (pictogramElement instanceof ConnectionDecorator) {
            eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(
                    ((ConnectionDecorator) pictogramElement).getConnection());
        } else {
            eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(
                    pictogramElement);
        }

        return eObject instanceof NamedObject;
    }

}
