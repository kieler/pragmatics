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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.tb.ContextMenuEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IContextMenuEntry;
import org.eclipse.graphiti.tb.IDecorator;

import de.cau.cs.kieler.kaom.Entity;

/**
 * Provides all the features to control the tool bar.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class ToolBehaviourProvider extends DefaultToolBehaviorProvider {

    /** the identifier of the property contibutor. */
    public static final String PROPERTY_CONTRIBUTOR_ID = "de.cau.cs.kieler.kaom.graphiti";

    /**
     * Constructor.
     * 
     * @param diagramTypeProvider
     *            the diagram type provider
     */
    public ToolBehaviourProvider(final IDiagramTypeProvider diagramTypeProvider) {
        super(diagramTypeProvider);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicsAlgorithm[] getClickArea(final PictogramElement pe) {
        // This method basically selects the inner pictogram element, i.e. the
        // rounded rectangle
        // inside the outside invisible rectangle
        IFeatureProvider featureProvider = getFeatureProvider();
        Object obj = featureProvider.getBusinessObjectForPictogramElement(pe);
        if (obj instanceof Entity) {
            GraphicsAlgorithm invisible = pe.getGraphicsAlgorithm();
            for (GraphicsAlgorithm algo : invisible
                    .getGraphicsAlgorithmChildren()) {
                if (algo instanceof Rectangle) {
                    return new GraphicsAlgorithm[] { algo };
                }
            }
        }
        return super.getClickArea(pe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicsAlgorithm getSelectionBorder(final PictogramElement pe) {
        // This method is used to select the Graphics Algorithm of the above
        // selected
        // inner Pictogram Element
        IFeatureProvider featureProvider = getFeatureProvider();
        Object obj = featureProvider.getBusinessObjectForPictogramElement(pe);

        if (obj instanceof Entity) {
            GraphicsAlgorithm invisible = pe.getGraphicsAlgorithm();
            for (GraphicsAlgorithm algo : invisible
                    .getGraphicsAlgorithmChildren()) {
                if (algo instanceof Rectangle) {
                    return algo;
                }
            }
        }

        return super.getSelectionBorder(pe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IContextButtonPadData getContextButtonPad(
            final IPictogramElementContext context) {
        // IContextButtonPadData data = super.getContextButtonPad(context);
        // PictogramElement pe = context.getPictogramElement();
        //
        // setGenericContextButtons(data, pe, CONTEXT_BUTTON_DELETE);

        // CreateConnectionContext ccc = new CreateConnectionContext();
        // ccc.setSourcePictogramElement(pe);
        // Anchor anchor = null;
        // if (pe instanceof Anchor) {
        // anchor = (Anchor) pe;
        // } else if (pe instanceof AnchorContainer) {
        // anchor = Graphiti.getPeService().getChopboxAnchor(
        // (AnchorContainer) pe);
        // }
        // ccc.setSourceAnchor(anchor);
        //
        // ContextButtonEntry button = new ContextButtonEntry(null, context);
        // button.setText("Create Connection");
        // button.setIconId(ImageProvider.IMAGE_LINK);
        // ICreateConnectionFeature[] features = getFeatureProvider()
        // .getCreateConnectionFeatures();
        // for (ICreateConnectionFeature feature : features) {
        // if (feature.isAvailable(ccc) && feature.canStartConnection(ccc)) {
        // button.addDragAndDropFeature(feature);
        // }
        // }
        //
        // if (button.getDragAndDropFeatures().size() > 0) {
        // data.getDomainSpecificContextButtons().add(button);
        // }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IContextMenuEntry[] getContextMenu(final ICustomContext context) {
        ContextMenuEntry subMenu = new ContextMenuEntry(null, context);
        subMenu.setText("Custom Features");
        subMenu.setDescription("Custom feature submenu");
        subMenu.setSubmenu(true);

        ICustomFeature[] customFeatures = getFeatureProvider()
                .getCustomFeatures(context);
        for (ICustomFeature customFeature : customFeatures) {
            if (customFeature.isAvailable(context)) {
                ContextMenuEntry menuEntry = new ContextMenuEntry(
                        customFeature, context);
                subMenu.add(menuEntry);
            }
        }

        IContextMenuEntry[] ret = new IContextMenuEntry[] { subMenu };
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPaletteCompartmentEntry[] getPalette() {
        List<IPaletteCompartmentEntry> ret = new ArrayList<IPaletteCompartmentEntry>();
        IPaletteCompartmentEntry[] superCompartments = super.getPalette();
        for (IPaletteCompartmentEntry superCompartment : superCompartments) {
            ret.add(superCompartment);
        }

        return ret.toArray(new IPaletteCompartmentEntry[ret.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDecorator[] getDecorators(final PictogramElement pe) {
        // TODO add validation mechanism here
        return super.getDecorators(pe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContributorId() {
        return PROPERTY_CONTRIBUTOR_ID;
    }

}
