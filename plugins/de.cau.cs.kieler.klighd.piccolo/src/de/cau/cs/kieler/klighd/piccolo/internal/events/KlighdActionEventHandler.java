/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.piccolo.internal.events;

import java.util.List;

import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.krendering.KAction;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.klighd.IAction;
import de.cau.cs.kieler.klighd.IAction.ActionContext;
import de.cau.cs.kieler.klighd.IAction.ActionResult;
import de.cau.cs.kieler.klighd.KlighdDataManager;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.ZoomStyle;
import de.cau.cs.kieler.klighd.internal.IKlighdTrigger;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.AbstractKGERenderingController;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdMouseEventListener.KlighdMouseEvent;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import edu.umd.cs.piccolo.event.PInputEvent;
import edu.umd.cs.piccolo.event.PInputEventListener;

/**
 * Initial draft of an event handler that invokes actions associated with KRenderings.
 * 
 * @author chsch
 */
public class KlighdActionEventHandler implements PInputEventListener {

    private PiccoloViewer viewer = null;
    
    /**
     * Constructor.
     * 
     * @param theViewer
     *            the {@link PiccoloViewer} it is attached to
     */
    public KlighdActionEventHandler(final PiccoloViewer theViewer) {
        this.viewer = theViewer;
    }
    
    /**
     * The well-formedness criterion of {@link KAction KActions} that is used to filter
     * the actions to be examined in {@link #processEvent(PInputEvent, int)}.  
     */
    private static final Predicate<KAction> WELLFORMED = new Predicate<KAction>() {
        public boolean apply(final KAction action) {
            return action.getTrigger() != null && !Strings.isNullOrEmpty(action.getActionId());
        }
    };
    

    /**
     * {@inheritDoc}
     */
    public void processEvent(final PInputEvent inputEvent, final int eventType) {
        // don't modify the evaluation of the 'handled' flag in an ad-hoc way,
        //  first make sure that the scenario described below is not enabled again.
        if (inputEvent.isHandled()) {
            return;
        }
        
        if (!(inputEvent.getSourceSwingEvent() instanceof KlighdMouseEvent)) {
            return;
        }

        final KlighdMouseEvent me = (KlighdMouseEvent) inputEvent.getSourceSwingEvent();
        
        final KRendering rendering = (KRendering) inputEvent.getPickedNode().getAttribute(
                AbstractKGERenderingController.ATTR_KRENDERING);
        
        if (rendering == null) {
            return;
        }
        
        ActionContext context = null; // construct the context lazily when it is required
        ActionResult result = null;
        
        // this flag is used to track the successful execution of actions
        //  in order to enable animated diagram changes, the viewer must be informed to
        //  record view model changes, which is done once an action is actually executed
        boolean anyActionPerformed = false;
        
        for (KAction action : Iterables.filter(rendering.getActions(), WELLFORMED)) {
            if (!action.getTrigger().equals(me.getTrigger()) || !guardsMatch(action, me)) {
                continue;
            }
            
            final IAction actionImpl =
                    KlighdDataManager.getInstance().getActionById(action.getActionId());
            if (actionImpl == null) {
                continue;
            }
            
            if (context == null) {
                context = new ActionContext(this.viewer, action.getTrigger(), null, rendering);
            }
            
            if (!anyActionPerformed) {
                viewer.startRecording();
                // the related 'stopRecording(...)' will be performed after the layout application
            }
            result = actionImpl.execute(context);

            if (result == null) {
                viewer.stopRecording(ZoomStyle.NONE, 0);
                final String msg = "KLighD action event handler: Execution of "
                        + actionImpl.getClass()
                        + " returned 'null', expected an IAction.ActionResult.";
                throw new IllegalResultException(msg);
            }

            anyActionPerformed = result.getActionPerformed();
        }
        
        if (!anyActionPerformed) {
            // if no action has been performed, skip the layout update and return
            return;
        }
        
        // don't modify the evaluation of the 'handled' flag in an ad-hoc way,
        //  first make sure that the scenario described below is not enabled again.
        inputEvent.setHandled(true);
        
        final boolean zoomToFit = result.getZoomToFit() != null
                ? result.getZoomToFit() : context.getViewContext().isZoomToFit();
        final boolean zoomToFocus = result.getZoomToFocus() != null
                ? result.getZoomToFocus()
                        : context.getViewContext().getZoomStyle() == ZoomStyle.ZOOM_TO_FOCUS;

        // remember the desired zoom style in the view context
        final ViewContext vc = viewer.getViewContext();
        vc.setZoomStyle(ZoomStyle.create(zoomToFit, zoomToFocus));

        final boolean animate = result.getAnimateLayout();
        final List<ILayoutConfig> layoutConfigs = result.getLayoutConfigs();

        // Execute the layout asynchronously in order to let the KLighdInputManager
        //  finish the processing of 'inputEvent' quickly.
        // Otherwise if the diagram layout engine interrupts its work by calling
        //  Display.readAndDispatch() and, with that, the control flow executing this method
        //  the processing of 'inputEvent' by the input manager might get triggered a
        //  second time by some timer event causing a kind of nested/recursive (!) evaluation
        //  of 'inputEvent' and, thereby, this method.
        // In addition, this scenario is tried to avoid by setting & evaluating the 'handled'.
        //  flag of 'inputEvent' properly.
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                LightDiagramServices.layoutDiagram(vc, animate, zoomToFit, layoutConfigs);
            }
        });
        
        KlighdPlugin.getTrigger().triggerStatus(IKlighdTrigger.Status.UPDATE, viewer.getViewContext());
    }
    
    private boolean guardsMatch(final KAction action, final KlighdMouseEvent event) {
        return (!action.isAltPressed() || event.isAltDown())
                && (!action.isCtrlCmdPressed() || event.isControlDown())
                && (!action.isShiftPressed() || event.isShiftDown());
    }


    /**
     * A dedicated exception indicating an illegal result of a method.<br>
     * It is currently thrown if implementations of {@link IAction#execute(ActionContext)} returns
     * <code>null</code>.
     * 
     * @author chsch
     */
    public class IllegalResultException extends RuntimeException {

        private static final long serialVersionUID = -5838587904577606037L;

        /**
         * Constructor.
         * 
         * @param msg
         *            the detail message. The detail message is saved for later retrieval by the
         *            {@link #getMessage()} method.
         */
        public IllegalResultException(final String msg) {
            super(msg);
        }
    }
}
