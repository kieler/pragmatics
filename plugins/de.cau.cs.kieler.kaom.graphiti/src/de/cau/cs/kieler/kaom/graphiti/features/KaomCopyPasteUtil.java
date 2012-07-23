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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.context.IPictogramElementsContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;

/**
 * @author soh
 * @kieler.ignore (excluded from review process)
 */
public class KaomCopyPasteUtil {

    /**
     *
     */
    private Map<PictogramElement, EObject> pictToEObjectMap;
    /**
     *
     */
    private Map<EObject, PictogramElement> eObjectTopictMap;

    /**
     * Creates a new KaomCopyPasteUtil.java.
     * 
     */
    public KaomCopyPasteUtil() {
        pictToEObjectMap = new HashMap<PictogramElement, EObject>();
        eObjectTopictMap = new HashMap<EObject, PictogramElement>();
    }

    /**
     * @return a map
     */
    public Map<PictogramElement, EObject> getPictToEObjectMap() {
        return pictToEObjectMap;
    }

    /**
     * @return a map
     */
    public Map<EObject, PictogramElement> geteObjectTopictMap() {
        return eObjectTopictMap;
    }

    /**
     * Get the useful top-level elements of the selection.
     * 
     * @param context
     *            the context
     * @return the selected elements
     */
    public List<EObject> getTopLevelElements(
            final IPictogramElementsContext context) {
        PictogramElement[] pes = context.getPictogramElements();
        List<EObject> modelElems = new LinkedList<EObject>();
        List<Port> ports = new LinkedList<Port>();
        List<Link> links = new LinkedList<Link>();
        List<Entity> entities = new LinkedList<Entity>();
        List<Relation> relations = new LinkedList<Relation>();
        for (PictogramElement pe : pes) {
            PictogramLink link = pe.getLink();
            if (link == null) {
                continue;
            }
            EList<EObject> list = link.getBusinessObjects();
            if (list.isEmpty()) {
                continue;
            }

            EObject elem = list.get(0);
            modelElems.add(elem);
            pictToEObjectMap.put(pe, elem);
            eObjectTopictMap.put(elem, pe);

            if (elem instanceof Entity) {
                entities.add((Entity) elem);
            } else if (elem instanceof Port) {
                ports.add((Port) elem);
            } else if (elem instanceof Link) {
                links.add((Link) elem);
            } else if (elem instanceof Relation) {
                relations.add((Relation) elem);
            }
        }

        Iterator<Relation> relIter = relations.iterator();
        while (relIter.hasNext()) {
            Relation rel = relIter.next();
            if (entities.contains(rel.eContainer())) {
                relIter.remove();
                for (Link l : rel.getIncomingLinks()) {
                    links.remove(l);
                }
                for (Link l : rel.getOutgoingLinks()) {
                    links.remove(l);
                }
            }
        }

        Iterator<Entity> entIter = entities.iterator();
        while (entIter.hasNext()) {
            Entity e = entIter.next();
            if (entities.contains(e.eContainer())) {
                entIter.remove();
                for (Link l : e.getChildLinks()) {
                    links.remove(l);
                }
                for (Port p : e.getChildPorts()) {
                    ports.remove(p);
                    for (Link l : p.getIncomingLinks()) {
                        links.remove(l);
                    }
                    for (Link l : p.getOutgoingLinks()) {
                        links.remove(l);
                    }
                }
            }
        }

        Iterator<Port> portIter = ports.iterator();
        while (portIter.hasNext()) {
            Port p = portIter.next();
            if (entities.contains(p.eContainer())) {
                portIter.remove();
            }
        }

        Iterator<EObject> iter = modelElems.iterator();
        // remove discarded elements from the list to keep the order
        // of the original selection intact
        while (iter.hasNext()) {
            EObject next = iter.next();
            if (!(entities.contains(next) || relations.contains(next)
                    || links.contains(next) || ports.contains(next))) {
                iter.remove();
            }
        }
        return modelElems;
    }
}
