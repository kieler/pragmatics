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
 */
package de.cau.cs.kieler.ksbase.ui.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.model.transformation.ITransformationFramework;
import de.cau.cs.kieler.core.model.transformation.xtend.XtendTransformationFramework;
import de.cau.cs.kieler.core.model.util.ModelingUtil;
import de.cau.cs.kieler.core.ui.handler.ICutCopyPasteCommandFactory;
import de.cau.cs.kieler.ksbase.ui.TransformationUIManager;
import de.cau.cs.kieler.ksbase.ui.handler.TransformationCommand;
import de.cau.cs.kieler.ksbase.ui.listener.ITransformationEventListener;

/**
 * Creates the cut, copy and paste commands from ksbase.
 * 
 * @author soh
 * @kieler.rating 2010-06-15 yellow msp, cmot
 */
public abstract class AbstractCutCopyPasteCommandFactory implements
        ICutCopyPasteCommandFactory {

    /** The transformation FRAMEWORK. */
    private static final ITransformationFramework FRAMEWORK = new XtendTransformationFramework();

    /** The last selection. */
    private List<EObject> lastSelection;

    /** The instance of the job. */
    private static WorkerJob jobInstance = null;

    /**
     * Build a new copy command.
     * 
     * @param part
     *            the editor
     * @param selection
     *            the selection
     * @return the command
     */
    public ICommand buildCopyCommand(final IDiagramWorkbenchPart part,
            final List<EObject> selection) {
        return buildCommand(part, selection, "Copy");
    }

    /**
     * Build a new cut command.
     * 
     * @param part
     *            the editor
     * @param selection
     *            the selection
     * @return the command
     */
    public ICommand buildCutCommand(final IDiagramWorkbenchPart part,
            final List<EObject> selection) {
        return buildCommand(part, selection, "Cut");
    }

    /**
     * Build a new paste command.
     * 
     * @param part
     *            the editor
     * @param selection
     *            the selection
     * @return the command
     */
    public ICommand buildPasteCommand(final IDiagramWorkbenchPart part,
            final List<EObject> selection) {
        return buildCommand(part, selection, "Paste");
    }

    /**
     * Getter for the transformation file. e.g.: /transformations/feature.ext
     * 
     * @return the file
     */
    protected abstract String getFile();

    /**
     * Getter for the bundle of the plugin where the file is located.
     * 
     * @return the bundle
     */
    protected abstract Bundle getBundle();

    /**
     * Build a command.
     * 
     * @param part
     *            the editor
     * @param selection
     *            the selection
     * @param label
     *            the label and name of the transformation
     * @return the command
     */
    private ICommand buildCommand(final IDiagramWorkbenchPart part,
            final List<EObject> selection, final String label) {
        lastSelection = selection;

        /* The path of the transformation file. */
        String filePath = prepareFile();

        TransformationCommandWithAutoLayout result = null;
        if (part instanceof DiagramEditor && filePath != null) {
            if (jobInstance != null) {
                jobInstance.cancel();
            }
            DiagramEditor editor = (DiagramEditor) part;
            TransactionalEditingDomain transDomain = editor.getEditingDomain();

            result = new TransformationCommandWithAutoLayout(transDomain,
                    label, editor);

            mapParameters(selection, label, filePath, result, editor);
        }
        return result;
    }

    /**
     * @param selection
     * @param label
     * @param filePath
     * @param result
     * @param editor
     */
    private void mapParameters(final List<EObject> selection,
            final String label, final String filePath,
            final TransformationCommandWithAutoLayout result,
            final DiagramEditor editor) {
        if (selection.size() > 1) {

            Class<?>[] types = getTypes();
            List<Boolean> hasListType = new LinkedList<Boolean>();
            for (int i = 0; i < types.length; i++) {
                hasListType.add(false);
            }
            List<String> pureMapping = new LinkedList<String>();

            for (int i = 0; i < selection.size(); i++) {
                EObject obj = selection.get(i);
                for (int j = 0; j < types.length; j++) {
                    if (types[j].isAssignableFrom(obj.getClass())) {
                        pureMapping.add(types[j].getSimpleName());
                        hasListType.set(j, true);
                    }
                }
            }

            List<String[]> possibleMappings = new LinkedList<String[]>();
            for (int i = 0; i < types.length; i++) {
                if (hasListType.get(i)) {
                    String[] array = { "List[" + types[i].getSimpleName() + "]" };
                    possibleMappings.add(array);
                }
            }

            if (label.equals("Paste")) {
                possibleMappings.add(pureMapping.toArray(new String[pureMapping
                        .size()]));
            } else {
                String[] array = { "List[Object]" };
                possibleMappings.add(array);
            }

            for (String[] s : possibleMappings) {
                List<Object> mappedSelection = FRAMEWORK
                        .createParameterMapping(selection, s);
                if (mappedSelection != null
                        && result.initialize(editor, mappedSelection,
                                label.toLowerCase(), filePath, getModel(),
                                FRAMEWORK)) {
                    break;
                }
            }
        } else {
            List<Object> list = new LinkedList<Object>();
            list.add(selection.get(0));
            result.initialize(editor, list, label.toLowerCase(), filePath,
                    getModel(), FRAMEWORK);
        }
    }

    /**
     * @param filePath
     * @return
     */
    private String prepareFile() {
        String filePath = null;
        Bundle bundle = getBundle();
        InputStream inStream = null;
        StringBuffer contentBuffer = new StringBuffer();
        String transformationFile = getFile();
        try {
            if (bundle != null) {
                URL urlPath = bundle.getEntry(transformationFile);
                // Parse transformation file to read transformations and
                // parameters now:
                if (urlPath != null) {
                    inStream = urlPath.openStream();
                    while (inStream.available() > 0) {
                        contentBuffer.append((char) inStream.read());

                    }
                }
            }
            // Write transformation file to .metadata
            IPath path = ResourcesPlugin.getPlugin().getStateLocation();

            String[] segments = transformationFile.split("/");
            // Transformation file name:
            path = path.append(segments[segments.length - 1]);

            File file = new File(path.toOSString());
            if (file != null) {
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    if (out != null) {
                        if (!file.exists()) {
                            if (!file.createNewFile()) {
                                System.out.println("Can't create file.");
                            }
                        }

                        out.write(contentBuffer.toString().getBytes());
                        out.flush();
                        out.close();
                    }
                } catch (FileNotFoundException fne) {
                    // do nothing
                } catch (SecurityException sece) {
                    // do nothing
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
                // Set delete on exit flag, so the files will be cleaned when
                // exiting eclipse
                filePath = file.getAbsolutePath();
                file.deleteOnExit();
            }
        } catch (IOException e0) {
            e0.printStackTrace();
        }
        return filePath;
    }

    /**
     * Get a list of all types that can be copied or pasted.
     * 
     * @return the list of types
     */
    protected abstract Class<?>[] getTypes();

    /**
     * Get the path to the model package. e.g.:
     * de.cau.cs.kieler.synccharts.SyncchartsPackage
     * 
     * @return the model package
     */
    protected abstract List<String> getModel();

    /**
     * Perform actions before the operation has started.
     * 
     * @param monitor
     *            a progress monitor
     */
    protected void performPreOperationActions(final IProgressMonitor monitor) {

    }

    /**
     * Perform actions after the operation has finished.
     * 
     * @param monitor
     *            a progress monitor
     */
    protected void performPostOperationActions(final IProgressMonitor monitor) {

    }

    /**
     * This transformation command performs an auto layout some time after the
     * last transformation.
     * 
     * @author soh
     */
    private class TransformationCommandWithAutoLayout extends
            TransformationCommand {

        /** The label. */
        private final String label;

        /** The delay. */
        private static final int DELAY = 500;

        private DiagramEditor editor;

        /**
         * Creates a new Transformation command.
         * 
         * @param domain
         * @param labelParam
         * @param editorParam
         */
        public TransformationCommandWithAutoLayout(
                final TransactionalEditingDomain domain,
                final String labelParam, final DiagramEditor editorParam) {
            super(domain, labelParam, null);
            this.label = labelParam;
            this.editor = editorParam;
        }

        @Override
        protected CommandResult doExecuteWithResult(
                final IProgressMonitor monitor, final IAdaptable info)
                throws ExecutionException {
            performPreOperationActions(monitor);

            // We need the view management
            // FIXME: adapt to new kivi
            // RunLogic.getInstance().registerListeners();

            // Notify event listeners:
            for (ITransformationEventListener te : TransformationUIManager.INSTANCE
                    .getTransformationEventListeners()) {
                te.transformationAboutToExecute(new Object[] {});
            }

            CommandResult res = super.doExecuteWithResult(monitor, info);
            if (label.equalsIgnoreCase("paste")) {
                WorkerJob job = new WorkerJob(editor);
                job.schedule(DELAY);

            }
            return res;
        }
    }

    /**
     * Thread for triggering an autolayout after some time.
     * 
     * @author soh
     */
    private class WorkerJob extends Job {

        private DiagramEditor editor;

        /**
         * Creates a new AbstractCutCopyPasteCommandFactory.java.
         * 
         * @param editorParam
         * @param name
         */
        public WorkerJob(final DiagramEditor editorParam) {
            super("Applying post-'paste' viewmanagement effects.");
            jobInstance = this;
            this.editor = editorParam;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected IStatus run(final IProgressMonitor monitor) {
            Throwable myException = null;
            EObject obj = ((View) editor.getDiagramEditPart().getModel())
                    .getElement();
            // haf: small try-catch blocks to avoid that the control flow gets
            // interrupted
            try {
                List<?> editPolicies = CanonicalEditPolicy
                        .getRegisteredEditPolicies(obj);
                for (Iterator<?> it = editPolicies.iterator(); it.hasNext();) {

                    CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it
                            .next();

                    nextEditPolicy.refresh();
                }
            } catch (Exception e) {
                myException = e;
            }

            try {
                // haf: this has to be done in UI thread, because the Trigger
                // Listener might
                // call UI methods. This would yield an invalid thread access
                PlatformUI.getWorkbench().getDisplay()
                        .asyncExec(new Runnable() {
                            public void run() {
                                IDiagramGraphicalViewer graphViewer = ((IDiagramWorkbenchPart) editor)
                                        .getDiagramGraphicalViewer();
                                graphViewer.flush();

                            }
                        });
            } catch (Exception e) {
                myException = e;
            }

            // Notify event listeners:
            for (ITransformationEventListener te : TransformationUIManager.INSTANCE
                    .getTransformationEventListeners()) {
                te.transformationExecuted(new Object[] { obj, editor });
            }

            performPostOperationActions(monitor);

            if (myException == null) {
                return new Status(IStatus.OK, "de.cau.cs.kieler.ksbase",
                        "Layout done");
            }
            String message = "Error during paste. " + myException.getMessage();
            return new Status(IStatus.ERROR, "de.cau.cs.kieler.ksbase",
                    message, myException);
        }
    }

    /**
     * Refresh the edit policies.
     * 
     * @param editorPart
     *            the editor
     */
    protected void refreshEditPolicies(final IEditorPart editorPart) {
        if (editorPart instanceof IDiagramWorkbenchPart) {
            IDiagramWorkbenchPart part = (IDiagramWorkbenchPart) editorPart;
            if (lastSelection != null) {
                for (EObject sel : lastSelection) {
                    refreshPolicy(sel);
                }
                // commit changes to view
                IDiagramGraphicalViewer graphViewer = part
                        .getDiagramGraphicalViewer();
                graphViewer.flush();
            }
        }
        lastSelection = null;
    }

    /**
     * Refresh the edit policy on the given element.
     * 
     * @param sel
     *            the element
     */
    private void refreshPolicy(final EObject sel) {
        EditPart editPart = ModelingUtil.getEditPart(sel);
        // get all registered edit parts in order to get transitions as well
        Collection<?> parts = editPart.getViewer().getEditPartRegistry()
                .values();

        // results list
        List<CanonicalEditPolicy> policies = new LinkedList<CanonicalEditPolicy>();
        // iterate over all parts
        for (Iterator<?> iter = parts.iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (obj instanceof EditPart) {
                Object model = ((EditPart) obj).getModel();
                if (model instanceof View) {
                    EObject eObject = ((View) model).getElement();

                    // get policy for the semantic element
                    List<?> editPolicies = CanonicalEditPolicy
                            .getRegisteredEditPolicies(eObject);
                    for (Iterator<?> it = editPolicies.iterator(); it.hasNext();) {
                        CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it
                                .next();
                        policies.add(nextEditPolicy);

                    }
                }
            }
        }

        // refresh all policies at once to avoid concurrent modification
        // exception
        for (CanonicalEditPolicy policy : policies) {
            policy.refresh();
        }
    }
}
