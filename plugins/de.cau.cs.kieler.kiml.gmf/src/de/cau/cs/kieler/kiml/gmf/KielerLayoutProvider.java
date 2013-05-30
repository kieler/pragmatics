/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.gmf;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.AbstractImageEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeProvider;
import org.eclipse.jface.preference.IPreferenceStore;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutHandler;

/**
 * GMF layout provider that executes KIELER layout. This enables the execution of KIELER layout
 * using the default "Arrange All" button. However, layout of selection is not possible with
 * this provider.
 *
 * @author msp
 * @author chsch
 */
public class KielerLayoutProvider extends AbstractProvider implements ILayoutNodeProvider {

    /**
     * {@inheritDoc}
     */
    public boolean provides(final IOperation operation) {
        return operation instanceof ILayoutNodeOperation;
    }

    /**
     * {@inheritDoc}<br>
     * <br>
     * This method returns <code>true</code> only for {@link IGraphicalEditPart IGraphicalEditParts}
     * whose figures are visible and that contain at least a {@link ShapeNodeEditPart}, which is not
     * an {@link AbstractBorderItemEditPart} or an {@link AbstractImageEditPart}.<br>
     * <br>
     * In short, returns only true if the provided edit part contains children that can be layouted.
     */
    @SuppressWarnings("rawtypes")
    public boolean canLayoutNodes(final List layoutNodes, final boolean shouldOffsetFromBoundingBox,
            final IAdaptable layoutHint) {
        Object o = layoutHint.getAdapter(IGraphicalEditPart.class);

        if (!(o instanceof IGraphicalEditPart)) {
            return false;
        }
        
        IGraphicalEditPart gep = (IGraphicalEditPart) o;
        boolean result = true;
        
        // check visibility of the child
        IFigure figure = gep.getFigure();
        result &= (figure != null && figure.isVisible());

        // check the availability of children that can be arranged
        //  computing layout on the element wouldn't make sense otherwise
        result &= Iterables.any((List<?>) gep.getChildren(), new Predicate<Object>() {
            public boolean apply(final Object o) {
                boolean result = o instanceof ShapeNodeEditPart;
                result &= !(o instanceof AbstractBorderItemEditPart);
                result &= !(o instanceof AbstractImageEditPart);
                return result;
            }
        });

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    public Runnable layoutLayoutNodes(final List layoutNodes, final boolean offsetFromBoundingBox,
            final IAdaptable layoutHint) {
        // fetch general settings from preferences
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        final boolean animation = preferenceStore.getBoolean(LayoutHandler.PREF_ANIMATION);
        final boolean zoomToFit = preferenceStore.getBoolean(LayoutHandler.PREF_ZOOM);
        final boolean progressDialog = preferenceStore.getBoolean(LayoutHandler.PREF_PROGRESS);
        
        // return a runnable that does nothing
        return new Runnable() {
            public void run() {
                Object editPart = layoutHint.getAdapter(IGraphicalEditPart.class);
                if (editPart instanceof IGraphicalEditPart) {
                    DiagramLayoutEngine.INSTANCE.layout(null, editPart, animation, progressDialog,
                            false, zoomToFit);
                }
            }
        };
    }
}
