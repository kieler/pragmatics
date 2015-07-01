/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi.triggers;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.kivi.AbstractTrigger;
import de.cau.cs.kieler.core.kivi.AbstractTriggerState;
import de.cau.cs.kieler.core.kivi.ITrigger;
import de.cau.cs.kieler.core.kivi.listeners.GlobalPartAdapter;

/**
 * A part trigger that fires trigger states if the active part has been switched.
 * 
 * @author chsch
 */
public class PartTrigger extends AbstractTrigger implements IPartListener {

    private IEditorPart currentActiveEditor = null;
    private IWorkbenchPart currentActivePart = null;

    /** Listens to all parts within the workbench. */
    private GlobalPartAdapter partListener;

    /**
     * {@inheritDoc}
     */
    @Override
    public void register() {
        partListener = new GlobalPartAdapter(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unregister() {
        partListener.unregister();
    }

    /*****************************
     * * the IPartListener stuff * *
     *****************************/

    /**
     * {@inheritDoc}
     */
    public void partOpened(final IWorkbenchPart part) {
        updateActiveViewPartAndActiveEditorPart();

        // cmot: if a part is activated for the first time, Eclipse does not
        // call partActivated but partOpened instead. This trigger should be
        // executed in both cases.
        partActivated(part);
    }

    /**
     * {@inheritDoc}
     */
    public void partBroughtToTop(final IWorkbenchPart part) {
        // cmot: if one selects a different opened tab and bring
        // another part to the front, this method should also
        // delegate to partActivated
        partActivated(part);
    }

    /**
     * {@inheritDoc}
     */
    public void partActivated(final IWorkbenchPart part) {
        this.currentActivePart = part;
        boolean isEditorReference = false;
        EventType type = null;
        type = EventType.VIEW_ACTIVATED;

        if (part instanceof IEditorPart) {
            this.currentActiveEditor = (IEditorPart) part;
            // currentActiveEditor.setFocus();
            type = EventType.EDITOR_ACTIVATED;
            isEditorReference = true;
        }

        if (this.isActive()) {
            PartState state = new PartState(this.currentActivePart, this.currentActiveEditor, type);

            if (isEditorReference) {
                EditorState state2 = new EditorState(this.currentActivePart,
                        this.currentActiveEditor, type);
                
                this.trigger(state);
                this.trigger(state2);

            } else {
                this.trigger(state);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partDeactivated(final IWorkbenchPart part) {
        if (this.currentActivePart != null && this.currentActivePart.equals(part)) {
            this.currentActivePart = null;
            if (this.isActive()) {
                EventType type = EventType.VIEW_DEACTIVATED;
                if (part instanceof IEditorPart) {
                    type = EventType.ACTIVE_EDITOR_DEACTIVATED;
                    this.trigger(new EditorState(this.currentActivePart, this.currentActiveEditor,
                            type));
                }
                this.trigger(new PartState(this.currentActivePart, this.currentActiveEditor, type));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partClosed(final IWorkbenchPart part) {
        // so far i figured out workbench parts get closed after
        // getting deactivated and the potential successor getting activated
        EventType type = null;
        // IWorkbenchPart part = partRef.getPart(false);
        if (this.currentActivePart != null) {
            if (this.currentActivePart.equals(part)) {
                this.currentActivePart = null;
            }
            type = EventType.VIEW_CLOSED;
        }

        if (part instanceof IEditorPart) {
            if (this.currentActiveEditor != null && this.currentActiveEditor.equals(part)) {
                this.currentActiveEditor = null;
            }
            type = EventType.ACTIVE_EDITOR_CLOSED;
        }

        if (this.isActive()) {
            PartState state = new PartState(part, this.currentActiveEditor, type);

            if (part instanceof IEditorPart) {
                PartState state2 = new EditorState(part, this.currentActiveEditor, type);

                this.trigger(state2);
            }
            this.trigger(state);
        }
    }

    /*****************************
     * * the IPartListener2 stuff * *
     *****************************/
    // is not used right now.

    /**
     * {@inheritDoc}
     */
    public void partActivated(final IWorkbenchPartReference partRef) {
        updateActiveViewPartAndActiveEditorPart();

        boolean isEditorReference = false;
        EventType type = null;
        type = EventType.VIEW_ACTIVATED;

        if (partRef instanceof IEditorReference) {
            type = EventType.EDITOR_ACTIVATED;
            isEditorReference = true;
        }

        if (this.isActive()) {
            PartState state = new PartState(this.currentActivePart, this.currentActiveEditor, type);

            if (isEditorReference) {
                EditorState state2 = new EditorState(this.currentActivePart,
                        this.currentActiveEditor, type);

                this.trigger(state);
                this.trigger(state2);

            } else {
                this.trigger(state);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partBroughtToTop(final IWorkbenchPartReference partRef) {
    }

    /**
     * {@inheritDoc}
     */
    public void partClosed(final IWorkbenchPartReference partRef) {
        // so far i figured out workbench parts get closed after
        // getting deactivated and the potential successor getting activated
        EventType type = null;
        IWorkbenchPart part = partRef.getPart(false);
        if (this.currentActivePart != null) {
            if (this.currentActivePart.equals(part)) {
                this.currentActivePart = null;
            }
            type = EventType.VIEW_CLOSED;
        }

        if (partRef instanceof IEditorReference) {
            if (this.currentActiveEditor.equals(part)) {
                this.currentActiveEditor = null;
            }
            type = EventType.ACTIVE_EDITOR_CLOSED;
        }

        if (this.isActive()) {
            PartState state = new PartState(part, this.currentActiveEditor, type);

            if (partRef instanceof IEditorReference) {
                PartState state2 = new EditorState(part, this.currentActiveEditor, type);

                this.trigger(state2);
            }
            this.trigger(state);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partDeactivated(final IWorkbenchPartReference partRef) {
        if (this.currentActivePart != null && this.currentActivePart.equals(partRef.getPart(false))) {
            this.currentActivePart = null;
            if (this.isActive()) {
                EventType type = EventType.VIEW_DEACTIVATED;
                if (partRef instanceof IEditorReference) {
                    type = EventType.ACTIVE_EDITOR_DEACTIVATED;
                    this.trigger(new EditorState(this.currentActivePart, this.currentActiveEditor,
                            type));
                }
                this.trigger(new PartState(this.currentActivePart, this.currentActiveEditor, type));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partOpened(final IWorkbenchPartReference partRef) {
    }

    /**
     * {@inheritDoc}
     */
    public void partHidden(final IWorkbenchPartReference partRef) {
    }

    /**
     * {@inheritDoc}
     */
    public void partVisible(final IWorkbenchPartReference partRef) {
    }

    /**
     * {@inheritDoc}
     */
    public void partInputChanged(final IWorkbenchPartReference partRef) {
    }

    /**
     * Update active view part and active editor part.
     */
    private void updateActiveViewPartAndActiveEditorPart() {
        IWorkbench wb = PlatformUI.getWorkbench();
        if (wb != null) {
            IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
            if (win != null) {
                IWorkbenchPage page = win.getActivePage();
                if (page != null) {
                    // get the (last) active editor and view part
                    IEditorPart activeEditorPart = page.getActiveEditor();
                    IWorkbenchPart activePart = page.getActivePart();
                    currentActiveEditor = activeEditorPart;
                    currentActivePart = activePart;
                }
            }
        }
    }

    /*****************************
     * * the TriggerState * *
     *****************************/

    /**
     * Possible EventTypes PartStates are indicating.
     * 
     * @author chsch
     */
    public static enum EventType {

        /** A view is active, it got the focus. */
        VIEW_ACTIVATED,

        /** A view lost the focus and is inactive. */
        VIEW_DEACTIVATED,

        /** A view has been closed. */
        VIEW_CLOSED,

        /** An editor is active, it got the focus. */
        EDITOR_ACTIVATED,

        /** The active editor lost the focus but is kept in trigger state. */
        ACTIVE_EDITOR_DEACTIVATED,

        /**
         * The active editor has been hidden and is kept on. This might occur if a view is
         * maximized, which often leads to strange behavior if PlatformUI....getActiveEditor is
         * used.
         */
        ACTIVE_EDITOR_HIDDEN,

        /**
         * The active editor is visible again and is kept on. This might occur if a view maximized
         * view is re-normalized, which led to PlatformUI....getActiveEditor returns null in the
         * past.
         */
        ACTIVE_EDITOR_VISIBLE,

        /**
         * The active editor has been closed.
         */
        ACTIVE_EDITOR_CLOSED

    }

    /**
     * Trigger states containing information of the current active work bench part.
     * 
     * @author chsch
     */
    public static class PartState extends AbstractTriggerState {

        /** The currently active workbench part or null if no one exists. */
        private IWorkbenchPart part;

        /** The currently active editor part of null if none exists. */
        private IEditorPart editorPart;

        /** The event type the state is triggered for. */
        private EventType eventType;

        /**
         * Default constructor.
         */
        public PartState() {

        }

        /**
         * Constructor taking the part to be delivered to combination.
         * 
         * @param theCurrentActivePart
         *            the currently active workbench part.
         * @param theCurrentActiveEditorPart
         *            the currently active editor part.
         * @param theEventType
         *            indicates the kind of the occurred event.
         */
        public PartState(final IWorkbenchPart theCurrentActivePart,
                final IEditorPart theCurrentActiveEditorPart, final EventType theEventType) {
            this.part = theCurrentActivePart;
            this.editorPart = theCurrentActiveEditorPart;
            this.eventType = theEventType;
        }

        /**
         * @return the current active workbench part or null if no one exists. If a close event is
         *         indicated this method returns the part being closed.
         */
        public IWorkbenchPart getPart() {
            return this.part;
        }

        /**
         * @return the current active workbench part or null if no one exists.
         */
        public IEditorPart getEditorPart() {
            return this.editorPart;
        }

        /**
         * @return the the event type the state was triggered for.
         */
        public EventType getEventType() {
            return this.eventType;
        }

        /**
         * Convenience tester, whether current active editor is current actual active workbench
         * part.
         * 
         * @return true if the known editor is the actual active part, false otherwise.
         */
        public boolean editorIsActivePart() {
            return this.editorPart == this.part;
        }

        /**
         * {@inheritDoc}
         */
        public Class<? extends ITrigger> getTriggerClass() {
            return PartTrigger.class;
        }
    }

    /**
     * A specialized {@link PartState} allowing to restrict the triggering of combinations sensible
     * to workbench part events.
     * 
     * @author chsch
     */
    public static class EditorState extends PartState {

        /**
         * Default constructor.
         */
        public EditorState() {
        }

        /**
         * Constructor delegating to the super constructor.
         * 
         * @param theCurrentActivePart
         *            the currently active workbench part.
         * @param theCurrentActiveEditorPart
         *            the currently active editor part.
         * @param theEventType
         *            indicates the kind of the occurred event.
         */
        public EditorState(final IWorkbenchPart theCurrentActivePart,
                final IEditorPart theCurrentActiveEditorPart, final EventType theEventType) {
            super(theCurrentActivePart, theCurrentActiveEditorPart, theEventType);
        }
    }

}
