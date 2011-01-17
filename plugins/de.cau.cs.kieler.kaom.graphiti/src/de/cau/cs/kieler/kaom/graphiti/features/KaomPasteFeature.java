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
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.graphiti.diagram.SemanticProvider;

/**
 * @author soh
 */
public class KaomPasteFeature extends AbstractPasteFeature {

    /**
     * the semantic provider used to fetch the top-level element of the current
     * diagram.
     */
    private SemanticProvider semanticProvider;

    /**
     *
     */
    private KaomCopyPasteUtil util;

    /**
     * The Constructor.
     * 
     * @param fp
     *            the feature provider
     * @param sp
     *            the semantic provider
     */
    public KaomPasteFeature(final IFeatureProvider fp, final SemanticProvider sp) {
        super(fp);
        this.semanticProvider = sp;
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

        case UNDEF:
        default:
            throw new RuntimeException();
        }
    }

    /**
     * @param parentEntity
     * @param clipBoard
     */
    private void pasteIntoEntity(final Entity parentEntity,
            final List<EObject> clipBoard) {
        ContainerShape container = (ContainerShape) util.geteObjectTopictMap()
                .get(parentEntity);
        for (EObject object : clipBoard) {
            EObject newObject = EcoreUtil.copy(object);
            if (object instanceof Entity) {
                parentEntity.getChildEntities().add((Entity) newObject);
            } else if (object instanceof Relation) {
                parentEntity.getChildRelations().add((Relation) newObject);
            } else if (object instanceof Port) {
                parentEntity.getChildPorts().add((Port) newObject);
            }

            AddContext ac = new AddContext();
            ac.setLocation(container.getGraphicsAlgorithm().getWidth() / 2,
                    container.getGraphicsAlgorithm().getHeight() / 2);
            ac.setTargetContainer(container);
            addGraphicalRepresentation(ac, newObject);
        }

    }

    /**
     * @author soh
     */
    private static enum Situation {
        ON_SINGLE_ENTITY, UNDEF;
    }

    /**
     * @author soh
     */
    private static enum PartialSituation {
        ONE_ENTITY, ONE_PORT, ONE_LINK, ONE_RELATION, ONLY_ENTITIES, ONLY_RELATIONS, ONLY_PORTS, ONLY_LINKS, UNDEF;
    }

    /**
     * @param selection
     * @param clipBoard
     * @return
     */
    private Situation analyze(final List<EObject> selection,
            final List<EObject> clipBoard) {
        Situation result = Situation.UNDEF;
        PartialSituation selSit = doPartialAnalysis(selection);
        PartialSituation clipSit = doPartialAnalysis(clipBoard);
        switch (selSit) {
        case ONE_ENTITY:
            if (clipSit == PartialSituation.ONE_ENTITY
                    || clipSit == PartialSituation.ONE_PORT
                    || clipSit == PartialSituation.ONE_RELATION
                    || clipSit == PartialSituation.ONLY_ENTITIES
                    || clipSit == PartialSituation.ONLY_PORTS
                    || clipSit == PartialSituation.ONLY_RELATIONS) {
                return Situation.ON_SINGLE_ENTITY;
            }
            return Situation.UNDEF;
        case ONE_LINK:
            break;
        case ONE_PORT:
            break;
        case ONE_RELATION:
            break;
        case ONLY_ENTITIES:
            break;
        case UNDEF:
            break;
        case ONLY_RELATIONS:
            break;
        case ONLY_LINKS:
            break;
        case ONLY_PORTS:
            break;

        }

        return result;
    }

    private PartialSituation doPartialAnalysis(final List<EObject> list) {
        PartialSituation selSit = PartialSituation.UNDEF;
        if (list.size() == 1) {
            EObject selected = list.get(0);
            if (selected instanceof Entity) {
                return PartialSituation.ONE_ENTITY;
            } else if (selected instanceof Port) {
                return PartialSituation.ONE_PORT;
            } else if (selected instanceof Link) {
                return PartialSituation.ONE_LINK;
            } else if (selected instanceof Relation) {
                return PartialSituation.ONE_RELATION;
            }
        }
        boolean onlyEntities = true;
        for (EObject eObj : list) {
            if (!(eObj instanceof Entity)) {
                onlyEntities = false;
            }
        }
        if (onlyEntities) {
            selSit = PartialSituation.ONLY_ENTITIES;
        }
        return selSit;
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
            if (!(o instanceof Entity || o instanceof Link || o instanceof Port || o instanceof Relation)) {
                return false;
            }
        }
        return true;
    }

}
