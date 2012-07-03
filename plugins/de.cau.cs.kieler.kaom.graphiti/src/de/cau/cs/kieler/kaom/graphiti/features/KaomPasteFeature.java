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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.graphiti.diagram.ReInitKaomDiagramCommand;

/**
 * @author soh
 */
public class KaomPasteFeature extends AbstractPasteFeature {

    /**
     *
     */
    private KaomCopyPasteUtil util;

    /**
     * The Constructor.
     * 
     * @param fp
     *            the feature provider
     */
    public KaomPasteFeature(final IFeatureProvider fp) {
        super(fp);
        util = new KaomCopyPasteUtil();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void paste(final IPasteContext context) {
        List<EObject> selection = util.getTopLevelElements(context);
        Object[] obj = super.getFromClipboard();
        List<EObject> clipBoard = new LinkedList<EObject>();
        for (Object o : obj) {
            clipBoard.add((EObject) o);
        }
        Situation s = analyze(selection, clipBoard);
        switch (s) {
        case ON_SINGLE_ENTITY:
            pasteIntoEntity((Entity) selection.get(0), clipBoard);
            break;
        case LINKS_BETWEEN_ITEMS:
            List<Link> links = new LinkedList<Link>();
            for (EObject o : clipBoard) {
                links.add((Link) o);
            }
            pasteLinksBetweenItems((Linkable) selection.get(0),
                    (Linkable) selection.get(1), links);
            break;

        case UNDEF:
        default:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Paste one or more links between two items that can be linked.
     * 
     * @param source
     *            source item
     * @param target
     *            target item
     * @param links
     *            the list of links
     */
    private void pasteLinksBetweenItems(final Linkable source,
            final Linkable target, final List<Link> links) {
        EObject container = source;
        PictogramElement pe = util.geteObjectTopictMap().get(source);
        if (source instanceof Port) {
            Port port = (Port) source;
            container = port.eContainer();
            pe = (PictogramElement) pe.eContainer();
        }
        Entity parent = (Entity) container.eContainer();
        ContainerShape cs = (ContainerShape) pe.eContainer();

        Anchor srcAnchor = getAnchor(cs, source);
        Anchor targetAnchor = getAnchor(cs, target);

        for (Link link : links) {
            Link copy = EcoreUtil.copy(link);
            parent.getChildLinks().add(copy);

            copy.setSource(source);
            source.getOutgoingLinks().add(copy);

            copy.setTarget(target);
            target.getIncomingLinks().add(copy);

            addLinkToView(cs, srcAnchor, targetAnchor, copy);
        }
    }

    private void addLinkToView(final ContainerShape cs, final Link link) {
        Anchor srcAnchor = getAnchor(cs, link.getSource());
        Anchor targetAnchor = getAnchor(cs, link.getTarget());
        addLinkToView(cs, srcAnchor, targetAnchor, link);
    }

    private void addLinkToView(final ContainerShape cs, final Anchor srcAnchor,
            final Anchor targetAnchor, final Link link) {
        AddConnectionContext acc =
                new AddConnectionContext(srcAnchor, targetAnchor);
        acc.setNewObject(link);
        acc.setTargetContainer(cs);
        PictogramElement pe = getFeatureProvider().addIfPossible(acc);
        if (pe != null) {
            UpdateContext up = new UpdateContext(pe);
            getFeatureProvider().updateIfPossibleAndNeeded(up);
        }

    }

    /**
     * Get the anchor of the given model element.
     * 
     * @param eObject
     *            the model element
     * @return the anchor
     */
    private Anchor getAnchor(final Shape cs, final EObject eObject) {
        boolean findChopBoxAnchor = false;
        if (cs.getLink().getBusinessObjects().get(0) == eObject) {
            findChopBoxAnchor = true;
        }
        for (Anchor a : cs.getAnchors()) {
            if (findChopBoxAnchor) {
                if (a instanceof ChopboxAnchor) {
                    return a;
                }
            } else {
                PictogramLink link = a.getLink();
                if (link != null) {
                    if (eObject == link.getBusinessObjects().get(0)) {
                        return a;
                    }
                }
            }
        }
        if (cs instanceof ContainerShape) {
            for (Shape child : ((ContainerShape) cs).getChildren()) {
                Anchor a = getAnchor(child, eObject);
                if (a != null) {
                    return a;
                }
            }
        }
        return null;
    }

    private void pasteIntoEntity(final Entity parentEntity,
            final List<EObject> clipBoard) {
        ContainerShape container =
                (ContainerShape) util.geteObjectTopictMap().get(parentEntity);
        for (EObject object : clipBoard) {
            EObject newObject = EcoreUtil.copy(object);
            if (object instanceof Entity) {
                parentEntity.getChildEntities().add((Entity) newObject);
            } else if (object instanceof Relation) {
                parentEntity.getChildRelations().add((Relation) newObject);
            } else if (object instanceof Port) {
                parentEntity.getChildPorts().add((Port) newObject);
            }

            PictogramElement pe = addToView(container, newObject);
            if (newObject instanceof Entity) {
                recursiveAdd((ContainerShape) pe, (Entity) newObject);
            }
        }

    }

    private void recursiveAdd(final ContainerShape cs, final Entity entity) {
        List<ContainerShape> children = new LinkedList<ContainerShape>();
        for (Entity ent : entity.getChildEntities()) {
            PictogramElement pe = addToView(cs, ent);
            children.add((ContainerShape) pe);
            recursiveAdd((ContainerShape) pe, ent);
        }
        for (Relation rel : entity.getChildRelations()) {
            addToView(cs, rel);
        }
        for (Port port : entity.getChildPorts()) {
            addToView(cs, port);
        }
        for (Link link : entity.getChildLinks()) {
            addLinkToView(cs, link);
        }
        for (ContainerShape child : children) {
            new ReInitKaomDiagramCommand().alignBoxRelativeAnchors(child);
        }
    }

    private PictogramElement addToView(final ContainerShape cs,
            final EObject eObj) {
        AddContext ac = new AddContext();
        ac.setTargetContainer(cs);
        ac.setNewObject(eObj);
        ac.setLocation(cs.getGraphicsAlgorithm().getWidth() / 2, cs
                .getGraphicsAlgorithm().getHeight() / 2);
        return addGraphicalRepresentation(ac, eObj);
    }

    /**
     * blah.
     */
    private static enum Situation {
        ON_SINGLE_ENTITY, LINKS_BETWEEN_ITEMS, UNDEF;
    }

    /**
     * blah.
     */
    private static enum PartialSituation {
        ONE_ENTITY, TWO_ENTITIES, ONE_PORT, TWO_PORTS, ONE_LINK, TWO_LINKS,
        ONE_RELATION, TWO_RELATIONS, MANY_ENTITIES, MANY_RELATIONS, MANY_PORTS,
        MANY_LINKS, UNDEF;

        public static PartialSituation one(final EObject eObject) {
            if (eObject instanceof Entity) {
                return ONE_ENTITY;
            } else if (eObject instanceof Relation) {
                return ONE_RELATION;
            } else if (eObject instanceof Link) {
                return ONE_LINK;
            } else if (eObject instanceof Port) {
                return ONE_PORT;
            } else {
                return UNDEF;
            }
        }

        public static PartialSituation two(final EObject eObject) {
            if (eObject instanceof Entity) {
                return TWO_ENTITIES;
            } else if (eObject instanceof Relation) {
                return TWO_RELATIONS;
            } else if (eObject instanceof Link) {
                return TWO_LINKS;
            } else if (eObject instanceof Port) {
                return TWO_PORTS;
            } else {
                return UNDEF;
            }
        }

        public static PartialSituation many(final EObject eObject) {
            if (eObject instanceof Entity) {
                return MANY_ENTITIES;
            } else if (eObject instanceof Relation) {
                return MANY_RELATIONS;
            } else if (eObject instanceof Link) {
                return MANY_LINKS;
            } else if (eObject instanceof Port) {
                return MANY_PORTS;
            } else {
                return UNDEF;
            }
        }

        public static boolean links(final PartialSituation situation) {
            return situation == ONE_LINK || situation == TWO_LINKS
                    || situation == MANY_LINKS;
        }

        public static boolean isOne(final PartialSituation situation) {
            return situation == ONE_ENTITY || situation == ONE_LINK
                    || situation == ONE_PORT || situation == ONE_RELATION;
        }

        public static boolean isTwo(final PartialSituation situation) {
            return situation == TWO_ENTITIES || situation == TWO_LINKS
                    || situation == TWO_PORTS || situation == TWO_RELATIONS;
        }

        public static boolean noLinks(final List<PartialSituation> list) {
            for (PartialSituation p : list) {
                if (links(p)) {
                    return false;
                }
            }
            return true;
        }

        public static boolean onlyLinks(final List<PartialSituation> list) {
            for (PartialSituation p : list) {
                if (!links(p)) {
                    return false;
                }
            }
            return true;
        }
    }

    private Situation analyze(final List<EObject> selection,
            final List<EObject> clipBoard) {
        List<PartialSituation> selectionSituation =
                doPartialAnalysis(selection);
        List<PartialSituation> clipboardSituation =
                doPartialAnalysis(clipBoard);
        if (selectionSituation.isEmpty()
                || selectionSituation.contains(PartialSituation.UNDEF)
                || clipboardSituation.isEmpty()
                || clipboardSituation.contains(PartialSituation.UNDEF)) {
            return Situation.UNDEF;
        }

        if (selectionSituation.size() == 1) {
            PartialSituation selSit = selectionSituation.get(0);
            if (selSit == PartialSituation.ONE_ENTITY
                    && PartialSituation.noLinks(clipboardSituation)) {
                return Situation.ON_SINGLE_ENTITY;
            } else if (PartialSituation.isTwo(selSit)
                    && !PartialSituation.links(selSit)
                    && PartialSituation.onlyLinks(clipboardSituation)) {
                return Situation.LINKS_BETWEEN_ITEMS;
            }
        } else if (selectionSituation.size() == 2) {
            PartialSituation first = selectionSituation.get(0);
            PartialSituation second = selectionSituation.get(1);
            if (PartialSituation.isOne(first) && PartialSituation.isOne(second)
                    && !PartialSituation.links(first)
                    && !PartialSituation.links(second)
                    && PartialSituation.onlyLinks(clipboardSituation)) {
                return Situation.LINKS_BETWEEN_ITEMS;
            }
        }
        return Situation.UNDEF;
    }

    private List<PartialSituation> doPartialAnalysis(final List<EObject> list) {
        List<PartialSituation> result = new LinkedList<PartialSituation>();
        for (EObject eObj : list) {
            if (result.contains(PartialSituation.one(eObj))) {
                result.remove(PartialSituation.one(eObj));
                result.add(PartialSituation.two(eObj));
            } else if (result.contains(PartialSituation.two(eObj))) {
                result.remove(PartialSituation.two(eObj));
                result.add(PartialSituation.many(eObj));
            } else if (!result.contains(PartialSituation.many(eObj))) {
                PartialSituation part = PartialSituation.one(eObj);
                if (part != PartialSituation.UNDEF) {
                    result.add(part);
                }
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canPaste(final IPasteContext context) {
        List<EObject> objects = util.getTopLevelElements(context);
        Object[] obj = super.getFromClipboard();
        if (objects.size() == 0) {
            return false;
        }
        for (Object o : obj) {
            if (!(o instanceof Entity || o instanceof Link || o instanceof Port
                    || o instanceof Relation)) {
                return false;
            }
        }
        return true;
    }

}
