/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.xtext.triggers;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextModelListener;
import org.eclipse.xtext.ui.util.ResourceUtil;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.kivi.AbstractTrigger;
import de.cau.cs.kieler.core.kivi.AbstractTriggerState;
import de.cau.cs.kieler.core.kivi.ITrigger;
import de.cau.cs.kieler.core.kivi.ITriggerState;
import de.cau.cs.kieler.core.kivi.listeners.GlobalPartAdapter;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.klighd.xtext.triggers.XtextBasedEditorActivationChangeTrigger.XtextModelChangeState.EventType;

// SUPPRESS CHECKSTYLE PREVIOUS 10 LineLength

/**
 * Implementation of {@link ITrigger} dedicated to Xtext-based editors. Reacts on the following
 * events: opening, receiving the focus, modifying of their content, and closing.
 * 
 * @author chsch
 */
public class XtextBasedEditorActivationChangeTrigger extends AbstractTrigger implements ITrigger,
        IXtextModelListener, IPartListener {

    private static final String KIELER_XTEXT_ERROR_MARKER_ID
            = "de.cau.cs.kieler.core.model.xtext.errorMarker";
    
    /**
     * Return the last active editor. Returns the active editor of the current page if it is not
     * null. This might happen when you maximize a view and minimize it again. Returns the first
     * editor of any open editors if the active editor is null.
     * 
     * @author haf
     * 
     * @return the last open active editor, which may be {@code null} if there is no open editor
     * @deprecated 
     */
    private static IEditorPart getLastActiveEditor() {
        final Maybe<IEditorPart> editor = Maybe.create();
        Runnable runnable = new Runnable() {
            public void run() {
                IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (window == null) {
                    return;
                }
                IWorkbenchPage page = window.getActivePage();
                if (page == null) {
                    return;
                }
                editor.set(page.getActiveEditor());
                if (editor.get() == null) {
                    IEditorReference[] editors = page.getEditorReferences();
                    if (editors.length > 0) {
                        editor.set(editors[0].getEditor(true));
                    }
                }
            }
        };
        
        Display display = Display.getCurrent();
        if (display == null) {
            display = PlatformUI.getWorkbench().getDisplay();
            display.syncExec(runnable);
        } else {
            // we are currently in the UI thread, so just execute the code
            runnable.run();
        }
        
        return editor.get();
    }
    
    /** Listens to all parts within the workbench. */
    private GlobalPartAdapter partListener;
    
    /**
     * Default constructor, needed by KIVi.
     */
    public XtextBasedEditorActivationChangeTrigger() {
    }

    /**
     * The Xtext-based editor the trigger is attached to.
     */
    private XtextEditor currentEditor = null;


    /**
     * {@inheritDoc}
     */
    @Override
    public void register() {
        partListener = new GlobalPartAdapter(this);
        this.partActivated(getLastActiveEditor());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unregister() {
        partListener.unregister();
    }

    private abstract class KsbaseAdapter extends AdapterImpl {
        abstract XtextEditor getEditor();
    }
    
    private void attachToXtextEditor(final XtextEditor editor, final boolean opened) {
        this.currentEditor = editor;

        editor.getDocument().addModelListener(this);
        editor.getDocument().modify(new IUnitOfWork.Void<XtextResource>() {

            @Override
            public void process(final XtextResource resource) throws Exception {
                resource.eAdapters().add(new KsbaseAdapter() {
                        private XtextEditor editor = currentEditor;

                        public XtextEditor getEditor() {
                            return editor;
                        }
                        
                        
                    });
                if (checkAndIndicateErrors(resource)) {
                    XtextBasedEditorActivationChangeTrigger.this.trigger(new XtextModelChangeState(
                            editor, (opened ? EventType.OPENED : EventType.FOCUSED), resource));
                }
            }
        });
    }

    private void detachFromCurrentXtextEditor() {
        if (currentEditor != null) {
            currentEditor.getDocument().removeModelListener(this);
        }
        this.currentEditor = null;

    }

    private void xtextEditorClosed(final XtextEditor editor) {
        this.trigger(new XtextModelChangeState(editor, EventType.CLOSED));
    }

    private boolean checkAndIndicateErrors(final XtextResource resource) {
        final String msg = "Model contains syntactic errors, so KIVi is not triggered.";
        IFile underlyingFile = ResourceUtil.getUnderlyingFile(resource);

        if (underlyingFile == null) {
            // this happens in case models being part of installed bundles (e.g. Xtend files)
            //  are opened; it doesn't make sense to attach any markers to them
            return true;
        }
        
        IMarker marker = null;
        try {
            /* examine the files error markers, whether one of is created by this mechanisms */
            for (IMarker m : underlyingFile.findMarkers(IMarker.PROBLEM, false, 1)) {
                if (m.getAttribute(KIELER_XTEXT_ERROR_MARKER_ID, false)) {
                    marker = m;
                    break;
                }
            }

            List<IMarker> currentMarkers = Arrays.asList(
                    underlyingFile.findMarkers(IMarker.PROBLEM, false, IResource.DEPTH_INFINITE));
            if (marker != null) {
                currentMarkers = Lists.newArrayList(currentMarkers);
                currentMarkers.remove(marker);
            }
            
            /* if model is correct... */
            if (resource.getErrors().isEmpty() && currentMarkers.isEmpty()) {
                /* ...and a marker exists remove it and finish! */
                if (marker != null) {
                    marker.delete();
                }
                return true;

            } else {
                /* Otherwise create a marker if no one already exists. */
                if (marker == null) {
                    marker = underlyingFile.createMarker(IMarker.PROBLEM);
                    marker.setAttribute(KIELER_XTEXT_ERROR_MARKER_ID, true);
                    marker.setAttribute(IMarker.MESSAGE, msg);
                    marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
                    marker.setAttribute(IMarker.LINE_NUMBER, 1);
                }
                // old version:
                //  StatusManager.getManager().handle(
                //    new Status(IStatus.INFO, ModelXtextPlugin.PLUGIN_ID, resource.getURI()
                //       .lastSegment() + ": " + msg));
            }
            return false;
        } catch (CoreException e) {
            /* in this case something went heavily wrong */
            throw new WrappedException(e);
        }
    }

    /*
     * ------------------------------------------------------
     *  the IXtextModelListener parts
     * ------------------------------------------------------
     */

    /**
     * {@inheritDoc}
     */
    public void modelChanged(final XtextResource resource) {
        if (checkAndIndicateErrors(resource)) {
            XtextEditor editor =this.currentEditor;
            if (this.currentEditor == null) {
                KsbaseAdapter adapter = Iterables.getFirst(Iterables.filter(resource.eAdapters(), KsbaseAdapter.class), null);
                if (adapter != null) {
                    editor = adapter.getEditor();
                }
            }
            // System.out.println(Calendar.getInstance().get(Calendar.MINUTE) + " TRIGGER");
            this.trigger(new XtextModelChangeState(editor, EventType.MODIFIED, resource));
        }
    }

    /*
     * ------------------------------------------------------
     *  the IPartListener parts
     * ------------------------------------------------------
     */

    private boolean isSupported(final IWorkbenchPart part) {
        return part instanceof XtextEditor
                && !part.equals(this.currentEditor)
                && !((XtextEditor) part).getLanguageName().equals("org.eclipse.xtend.core.Xtend");
    }

    /**
     * {@inheritDoc}
     */
    public void partOpened(final IWorkbenchPart part) {
        if (isSupported(part)) {
            this.detachFromCurrentXtextEditor();
            this.attachToXtextEditor((XtextEditor) part, true);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partBroughtToTop(final IWorkbenchPart part) {
        if (isSupported(part)) {
            this.detachFromCurrentXtextEditor();
            this.attachToXtextEditor((XtextEditor) part, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partActivated(final IWorkbenchPart part) {
        if (isSupported(part)) {
            this.detachFromCurrentXtextEditor();
            this.attachToXtextEditor((XtextEditor) part, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partDeactivated(final IWorkbenchPart part) {
        if (part.equals(this.currentEditor)) {
            this.currentEditor = null;
            // this.detachFromCurrentXtextEditor();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partClosed(final IWorkbenchPart part) {
        if (part.equals(this.currentEditor)) {
            this.currentEditor = null;
            this.detachFromCurrentXtextEditor();
        }
        if (isSupported(part)) {
            this.xtextEditorClosed((XtextEditor) part);
        }
    }

    /*
     * ------------------------------------------------------
     *  the ITriggerState implementation
     * ------------------------------------------------------
     */

    /**
     * An {@link ITriggerState} indicating opening, modifying, or closing events of Xtext-based
     * editors.
     * 
     * @author chsch
     */
    public static class XtextModelChangeState extends AbstractTriggerState implements ITriggerState {

        /**
         * Type of event to be denoted.
         */
        public enum EventType {
            /** */
            OPENED,
            /** */
            FOCUSED,
            /** */
            MODIFIED,
            /** */
            CLOSED
        }

        private XtextEditor editor = null;
        private EventType eventType = EventType.OPENED;
        private XtextResource resource = null;

        /**
         * Default constructor.
         */
        public XtextModelChangeState() {
        }

        /**
         * Convenience constructor.
         * 
         * @param theEditor
         *            the editor the event has taken place of.
         * @param theEventType
         *            the event type description.
         */
        public XtextModelChangeState(final XtextEditor theEditor, final EventType theEventType) {
            this(theEditor, theEventType, null);
        }

        /**
         * Complete constructor.
         * 
         * @param theEditor
         *            the editor the event has taken place of.
         * @param theEventType
         *            the event type description.
         * @param theResource
         *            resource that is maintained by the editor. This parameter can be supplied in
         *            addition to the editor in order to avoid the costly grabbing via the
         *            transaction mechanism.
         */
        public XtextModelChangeState(final XtextEditor theEditor, final EventType theEventType,
                final XtextResource theResource) {
            this.editor = theEditor;
            this.eventType = theEventType;
            this.resource = theResource;
        }

        /**
         * {@inheritDoc}
         */
        public Class<? extends ITrigger> getTriggerClass() {
            return XtextBasedEditorActivationChangeTrigger.class;
        }

        /**
         * Getter for the editor field.
         * 
         * @return the value of the editor field.
         */
        public XtextEditor getEditor() {
            return this.editor;
        }

        /**
         * Reveals the path of the denoted editor's {@link org.eclipse.ui.IEditorInput}. That
         * {@link org.eclipse.ui.IEditorInput} is assumed to be a
         * {@link org.eclipse.ui.part.FileEditorInput}.
         * 
         * @return the {@link org.eclipse.core.runtime.IPath} of the denoted editor's
         *         {@link org.eclipse.ui.IEditorInput}.
         */
        public IPath getEditorInputPath() {
            if (this.editor != null && this.editor.getEditorInput() != null
                    && this.editor.getEditorInput().getClass().equals(FileEditorInput.class)
                    && ((FileEditorInput) this.editor.getEditorInput()).getFile() != null
                    && ((FileEditorInput) this.editor.getEditorInput()).getFile()
                            .getLocationURI() != null) {
                return ((FileEditorInput) this.editor.getEditorInput()).getPath();
            } else {
                return null;
            }
        }

        /**
         * Getter for the eventType field.
         * 
         * @return the value of the eventType field.
         */
        public EventType getEventType() {
            return this.eventType;
        }

        /**
         * Getter for the resource field.
         * 
         * @return the value of the resource field.
         */
        public XtextResource getResource() {
            return this.resource;
        }

        /**
         * Setter for the editor field.
         * 
         * @param theEditor
         *            the new value for the editor field.
         * @return the currently manipulated {@link ITriggerState} object.
         */
        public ITriggerState setEditor(final XtextEditor theEditor) {
            this.editor = theEditor;
            return this;
        }

        /**
         * Setter for the eventType field.
         * 
         * @param theEventType
         *            the new value for the eventType field.
         * @return the currently manipulated {@link ITriggerState} object.
         */
        public ITriggerState setEventType(final EventType theEventType) {
            this.eventType = theEventType;
            return this;
        }

        /**
         * Setter for the resource field.
         * 
         * @param theResource
         *            the new value for the resource field.
         * @return the currently manipulated {@link ITriggerState} object.
         */
        public ITriggerState setResource(final XtextResource theResource) {
            this.resource = theResource;
            return this;
        }
    }
}
