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
package de.cau.cs.kieler.kaom.text.scoping;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.scoping.impl.SimpleScope;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;

/**
 * This class contains custom scoping description.
 * 
 * See http://www.eclipse.org/Xtext/documentation/latest/xtext.html#scoping
 * 
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class KaomScopeProvider extends AbstractDeclarativeScopeProvider {
    
    /**
     * Return the scope for source references.
     * 
     * @param link a link
     * @param reference the source reference
     * @return the scope containing all visible linkables
     */
    IScope scope_Link_source(final Link link, final EReference reference) {
        return getScope(link);
    }

    /**
     * Return the scope for target references.
     * 
     * @param link a link
     * @param reference the target reference
     * @return the scope containing all visible linkables
     */
    IScope scope_Link_target(final Link link, final EReference reference) {
        return getScope(link);
    }
    
    /**
     * Return the scope with visible linkables for the given link.
     * 
     * @param link a link
     * @return the scope containing all visible linkables
     */
    private IScope getScope(final Link link) {
        List<IEObjectDescription> objects = new LinkedList<IEObjectDescription>();
        EObject container = link;
        while (container.eContainer() instanceof Entity) {
            container = container.eContainer();
        }
        if (container instanceof Entity) {
            addLinkables((Entity) container, objects);
        }
        return new SimpleScope(objects);
    }
    
    /**
     * Add all linkables that are contained in the given entity to the list of objects.
     * 
     * @param parent the parent entity
     * @param objects a list of object descriptions
     */
    private void addLinkables(final Entity parent, final List<IEObjectDescription> objects) {
        objects.add(EObjectDescription.create(parent.getId(), parent));
        for (Port port : parent.getChildPorts()) {
            objects.add(EObjectDescription.create(port.getId(), port));
        }
        for (Relation relation : parent.getChildRelations()) {
            objects.add(EObjectDescription.create(relation.getId(), relation));
        }
        for (Entity child : parent.getChildEntities()) {
            addLinkables(child, objects);
        }
    }

}
