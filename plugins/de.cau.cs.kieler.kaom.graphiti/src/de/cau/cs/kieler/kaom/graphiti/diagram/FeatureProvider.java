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

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICopyFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveAnchorFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.core.model.graphiti.features.DefaultKielerDeleteFeature;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.graphiti.features.AddEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.AddLinkFeature;
import de.cau.cs.kieler.kaom.graphiti.features.AddPortFeature;
import de.cau.cs.kieler.kaom.graphiti.features.AddRelationFeature;
import de.cau.cs.kieler.kaom.graphiti.features.CreateEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.CreateLinkFeature;
import de.cau.cs.kieler.kaom.graphiti.features.CreatePortFeature;
import de.cau.cs.kieler.kaom.graphiti.features.CreateRelationFeature;
import de.cau.cs.kieler.kaom.graphiti.features.DirectEditEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.DirectEditLinkFeature;
import de.cau.cs.kieler.kaom.graphiti.features.KaomCopyFeature;
import de.cau.cs.kieler.kaom.graphiti.features.KaomPasteFeature;
import de.cau.cs.kieler.kaom.graphiti.features.LayoutEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.LayoutPortFeature;
import de.cau.cs.kieler.kaom.graphiti.features.MoveEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.MovePortFeature;
import de.cau.cs.kieler.kaom.graphiti.features.MoveRelationFeature;
import de.cau.cs.kieler.kaom.graphiti.features.UpdateEntityFeature;
import de.cau.cs.kieler.kaom.graphiti.features.UpdateLinkFeature;
import de.cau.cs.kieler.kaom.graphiti.features.UpdateRelationFeature;

/**
 * Provides and initializes all features for the Graphiti KAOM editor.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class FeatureProvider extends DefaultFeatureProvider {

    /** the style provider that is used by add-features. */
    private IStyleProvider styleProvider;
    /** the semantic provider that is used by create-features. */
    private SemanticProvider semanticProvider;

    /**
     * Constructor.
     * 
     * @param dtp
     *            the diagram type provider
     */
    public FeatureProvider(final IDiagramTypeProvider dtp) {
        super(dtp);
        styleProvider = new StyleProvider(dtp);
        semanticProvider = new SemanticProvider(dtp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICreateFeature[] getCreateFeatures() {
        return new ICreateFeature[] {
                new CreateEntityFeature(this, semanticProvider),
                new CreatePortFeature(this),
                new CreateRelationFeature(this, semanticProvider) };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IAddFeature getAddFeature(final IAddContext context) {
        if (context.getNewObject() instanceof Entity) {
            return new AddEntityFeature(this, styleProvider);
        } else if (context.getNewObject() instanceof Link) {
            return new AddLinkFeature(this, styleProvider);
        } else if (context.getNewObject() instanceof Port) {
            return new AddPortFeature(this, styleProvider);
        } else if (context.getNewObject() instanceof Relation) {
            return new AddRelationFeature(this, styleProvider);
        }
        return super.getAddFeature(context);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public IUpdateFeature getUpdateFeature(final IUpdateContext context) {
        if (context.getPictogramElement() instanceof ContainerShape) {
            Object obj =
                    getBusinessObjectForPictogramElement(context
                            .getPictogramElement());
            if (obj instanceof Entity) {
                return new UpdateEntityFeature(this);
            } else if (obj instanceof Relation) {
                return new UpdateRelationFeature(this);
            }
        } else if (context.getPictogramElement() instanceof Connection) {
            Object obj =
                    getBusinessObjectForPictogramElement(context
                            .getPictogramElement());
            if (obj instanceof Link) {
                return new UpdateLinkFeature(this);
            }
        }

        return super.getUpdateFeature(context);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public IMoveShapeFeature
            getMoveShapeFeature(final IMoveShapeContext context) {
        Shape shape = context.getShape();
        Object ob = getBusinessObjectForPictogramElement(shape);
        if (ob instanceof Entity) {
            return new MoveEntityFeature(this, semanticProvider);
        } else if (ob instanceof Relation) {
            return new MoveRelationFeature(this, semanticProvider);
        }
        return super.getMoveShapeFeature(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IMoveAnchorFeature getMoveAnchorFeature(
            final IMoveAnchorContext context) {
        if (getBusinessObjectForPictogramElement(context.getAnchor()) instanceof Port) {
            return new MovePortFeature(this);
        }
        return super.getMoveAnchorFeature(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResizeShapeFeature getResizeShapeFeature(
            final IResizeShapeContext context) {
        Shape shape = context.getShape();
        Object ob = getBusinessObjectForPictogramElement(shape);
        if (ob instanceof Entity) {
            return new DefaultResizeShapeFeature(this);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILayoutFeature getLayoutFeature(final ILayoutContext context) {
        PictogramElement pe = context.getPictogramElement();
        Object obj = getBusinessObjectForPictogramElement(pe);
        if (obj instanceof Entity) {
            return new LayoutEntityFeature(this);
        } else if (obj instanceof Port) {
            return new LayoutPortFeature(this);
        }
        return super.getLayoutFeature(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDirectEditingFeature getDirectEditingFeature(
            final IDirectEditingContext context) {
        PictogramElement pe = context.getPictogramElement();
        Object obj;
        if (pe instanceof ConnectionDecorator) {
            obj =
                    getBusinessObjectForPictogramElement(((ConnectionDecorator) pe)
                            .getConnection());
        } else {
            obj = getBusinessObjectForPictogramElement(pe);
        }

        if (obj instanceof Entity) {
            return new DirectEditEntityFeature(this);
        } else if (obj instanceof Link) {
            return new DirectEditLinkFeature(this);
        }
        return super.getDirectEditingFeature(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICopyFeature getCopyFeature(final ICopyContext context) {
        return new KaomCopyFeature(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPasteFeature getPasteFeature(final IPasteContext context) {
        return new KaomPasteFeature(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICreateConnectionFeature[] getCreateConnectionFeatures() {
        return new ICreateConnectionFeature[] { new CreateLinkFeature(this,
                semanticProvider) };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDeleteFeature getDeleteFeature(final IDeleteContext context) {
        return new DefaultKielerDeleteFeature(this);
    }

}
