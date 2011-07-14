/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/

package de.cau.cs.kieler.karma.util.expandcollapsebutton;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.internal.tools.CompartmentCollapseTracker;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 * This class is basically the same as the standard gmf CompartmentCollapseHandle.
 * In addition it provides the potential to exchange or influence the Collapse/Expand button. 
 * @author ckru
 *
 */
public class AdvancedRenderingCompartmentCollapseHandle extends AbstractHandle implements
        PropertyChangeListener, NotificationListener {

    private SwitchableCollapseFigure switchableCollapseFigure;

    /** handle figure dimension. */
    public static Dimension SIZE = new Dimension(11, 11);

    /**
     * Positions the supplied figure in its owner's top left corner offset by [1,1].
     */
    private class CollapseHandleLocator implements Locator {
        public void relocate(final IFigure target) {
            Rectangle theBounds = getOwnerFigure().getClientArea().getCopy();
            getOwnerFigure().translateToAbsolute(theBounds);
            target.translateToRelative(theBounds);
            target.setLocation(theBounds.getLocation());
        }
    }

    
    /**
     * @see org.eclipse.gmf.runtime.diagram.ui.handles.CompartmentCollapseHandle#CompartmentCollapseHandle(IGraphicalEditPart)
     */
    public AdvancedRenderingCompartmentCollapseHandle(final IGraphicalEditPart owner) {
        setOwner(owner);
        setLocator(new CollapseHandleLocator());
        setCursor(Cursors.ARROW);

        setSize(SIZE);
        setLayoutManager(new StackLayout());

        add(switchableCollapseFigure = new SwitchableCollapseFigure(this));

        View view = owner.getNotationView();
        if (view != null) {
            DrawerStyle style = (DrawerStyle) view.getStyle(NotationPackage.eINSTANCE
                    .getDrawerStyle());
            if (style != null) {
                switchableCollapseFigure.setCollapsed(style.isCollapsed());
                return;
            }
        }
        switchableCollapseFigure.setCollapsed(false);
    }

    
    /**
     * Constructs a new Handle with custom collapse/expand figures and locator.
     * @param owner
     * @param collapseFigure a custom figure to be display as collapse button.
     * @param expandFigure a custom figure to be display as expand button.
     * @param locator a custom locator to locate expand/collapse button
     */
    public AdvancedRenderingCompartmentCollapseHandle(final IGraphicalEditPart owner,
            final IFigure collapseFigure, final IFigure expandFigure, final Locator locator, final Dimension size) {
        setOwner(owner);
        if (locator != null) {
            setLocator(locator);
        } else {
            setLocator(new CollapseHandleLocator());
        }
        setCursor(Cursors.ARROW);
        if (size != null) {
            setSize(size);
        } else {
            setSize(SIZE);
        }
        setLayoutManager(new StackLayout());

        add(switchableCollapseFigure = new SwitchableCollapseFigure(this));
        if (collapseFigure != null) {
            switchableCollapseFigure.setCollapseFigure(collapseFigure);
        }
        if (expandFigure != null) {
            switchableCollapseFigure.setExpandFigure(expandFigure);
        }
        View view = owner.getNotationView();
        if (view != null) {
            DrawerStyle style = (DrawerStyle) view.getStyle(NotationPackage.eINSTANCE
                    .getDrawerStyle());
            if (style != null) {
                switchableCollapseFigure.setCollapsed(style.isCollapsed());
                return;
            }
        }
        switchableCollapseFigure.setCollapsed(false);
    }

    /**
     * @see org.eclipse.draw2d.IFigure#findFigureAt(int, int, TreeSearch)
     */
    public IFigure findFigureAt(final int x, final int y, final TreeSearch search) {
        IFigure found = super.findFigureAt(x, y, search);
        return (switchableCollapseFigure.equals(found)) ? this : found;
    }

    /**
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Properties.ID_COLLAPSED)) {
            switchableCollapseFigure.setCollapsed(((Boolean) evt.getNewValue()).booleanValue());
        }
    }

    /**
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void notifyChanged(final Notification notification) {
        if (NotationPackage.eINSTANCE.getDrawerStyle_Collapsed() == notification.getFeature()) {
            switchableCollapseFigure.setCollapsed(notification.getNewBooleanValue());
        }
    }

    /**
     * @see org.eclipse.gef.handles.AbstractHandle#createDragTracker()
     */
    protected DragTracker createDragTracker() {
        return new CompartmentCollapseTracker((IResizableCompartmentEditPart) getOwner());
    }

    /**
     * @see org.eclipse.draw2d.IFigure#addNotify()
     */
    public void addNotify() {
        super.addNotify();
        IGraphicalEditPart owner = (IGraphicalEditPart) getOwner();
        View view = owner.getNotationView();
        if (view != null) {
            getDiagramEventBroker().addNotificationListener(owner.getNotationView(),
                    AdvancedRenderingCompartmentCollapseHandle.this);
        }
    }

    /**
     * @see org.eclipse.draw2d.IFigure#removeNotify()
     */
    public void removeNotify() {
        IGraphicalEditPart owner = (IGraphicalEditPart) getOwner();
        getDiagramEventBroker().removeNotificationListener(owner.getNotationView(), this);
        super.removeNotify();
    }

    private DiagramEventBroker getDiagramEventBroker() {
        TransactionalEditingDomain theEditingDomain = ((IGraphicalEditPart) getOwner())
                .getEditingDomain();
        if (theEditingDomain != null) {
            return DiagramEventBroker.getInstance(theEditingDomain);
        }
        return null;
    }

}
