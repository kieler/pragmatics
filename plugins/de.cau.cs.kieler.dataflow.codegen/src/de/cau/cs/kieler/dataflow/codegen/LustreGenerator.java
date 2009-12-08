///*
// * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
// *
// * http://www.informatik.uni-kiel.de/rtsys/kieler/
// * 
// * Copyright 2009 by
// * + Christian-Albrechts-University of Kiel
// *   + Department of Computer Science
// *     + Real-Time and Embedded Systems Group
// * 
// * This code is provided under the terms of the Eclipse Public License (EPL).
// * See the file epl-v10.html for the license text.
// */
package de.cau.cs.kieler.dataflow.codegen;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.WorkflowContextDefaultImpl;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.issues.MWEDiagnostic;
import org.eclipse.emf.mwe.core.monitor.NullProgressMonitor;
import org.eclipse.emf.mwe.internal.core.Workflow;
import org.eclipse.emf.mwe.utils.Reader;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xpand2.Generator;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xtend.typesystem.emf.EmfMetaModel;

import de.cau.cs.kieler.dataflow.DataflowPackage;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Generate Lustre code from Dataflow diagram.
 * 
 * @author ctr
 * 
 */
public class LustreGenerator extends AbstractHandler implements IHandler {

    private EObject myModel = null;
    private String outPath = null;
    private String uriString = null;
    private IEditorPart editor = null;
    private URI uri = null;

    private String part2Location(final IEditorPart ed) {
        String out = null;

        FileEditorInput u = (FileEditorInput) ed.getEditorInput();
        String outName = u.getName();
        out = u.getURI().getRawPath().replace(outName, "");

        return out;
    }

    private void initialize() {
        TypeHelper.reset();
        Helper.reset();
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage();

        editor = activePage.getActiveEditor();
        outPath = part2Location(editor);
        uriString = null;
        if (editor instanceof DiagramEditor) {
            DiagramEditor diagramEditor = (DiagramEditor) editor;
            View notationElement = ((View) diagramEditor.getDiagramEditPart().getModel());
            myModel = (EObject) notationElement.getElement();
            uri = myModel.eResource().getURI();
            uriString = uri.toString();
        }

    }

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        generateLus();
        return null;
    }

    /**
     * Transforms the Dataflow model into a Lustre description. Each box that contains sub boxes is
     * becomes one node. Types are inferred by assuming that standard Lustre identifiers like + are
     * the corresponding basic nodes in the dataflow diagram. On the highest level, each unconnected
     * port is considered to be a global input or output.
     * 
     * @return a Lustre drescription of the dataflow model.
     * 
     */
    public String generateLus() {

        initialize();
        // EMF reader
        final Reader emfReader = new Reader();
        emfReader.setUri(uriString);
        emfReader.setModelSlot("model");

        // Meta model
        final EmfMetaModel metaModel = new EmfMetaModel(DataflowPackage.eINSTANCE);

        // Outlet
        final Outlet outlet = new Outlet();
        outlet.setPath(outPath);

        // Generator
        final Generator generator = new Generator();
        generator.addMetaModel(metaModel);
        generator.addOutlet(outlet);

        generator.setExpand("template::Lustre::main FOR model");

        final WorkflowContext wfx = new WorkflowContextDefaultImpl();
        final Issues issues = new org.eclipse.emf.mwe.core.issues.IssuesImpl();
        final NullProgressMonitor monitor = new NullProgressMonitor();

        final Workflow workflow = new Workflow();
        workflow.addComponent(emfReader);
        workflow.addComponent(generator);
        workflow.invoke(wfx, monitor, issues);

        final StringBuffer issue = new StringBuffer(generator.getLogMessage() + "\n");
        for (final MWEDiagnostic s : issues.getIssues()) {
            issue.append(s + "\n");
        }
        for (final MWEDiagnostic s : issues.getErrors()) {
            issue.append(s + "\n");
        }
        for (final MWEDiagnostic s : issues.getWarnings()) {
            issue.append(s + "\n");
        }
        for (final MWEDiagnostic s : issues.getInfos()) {
            issue.append(s + "\n");
        }
        StatusManager.getManager().handle(
                new Status(IStatus.WARNING, Activator.PLUGIN_ID, issue.toString(), null),
                StatusManager.LOG);

        return outPath;
    }
}
