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
package de.cau.cs.kieler.keg.importer.wizards;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorUtil;
import de.cau.cs.kieler.keg.importer.AbstractImporter;
import de.cau.cs.kieler.keg.importer.ImportManager;
import de.cau.cs.kieler.keg.importer.ImportUtil;
import de.cau.cs.kieler.keg.importer.KEGImporterPlugin;

/**
 * A wizard for importing graphs from various file formats into the KEG format.
 * 
 * @author mri
 */
public class ImportGraphWizard extends Wizard implements IImportWizard {

    // /** the message for telling the user that the specified file was not
    // valid. */
    // private static final String MESSAGE_INVALID_FILE =
    // "The specified file isn't a valid graph file.";

    /** the selection the import wizard was called on. */
    private IStructuredSelection selection;
    /** the wizard page from which to select the import file. */
    private ImportGraphWizardPage importPage;
    /** the wizard page from which to select the new file. */
    private ImportGraphNewFilePage newFilePage;
    /** the preselected workspace file. */
    private String workspaceFile = null;

    /**
     * Constructs a graph import wizard.
     */
    public ImportGraphWizard() {
        super();
        setWindowTitle("Import");
    }

    /**
     * Constructs a graph import wizard with a preselected workspace file.
     * 
     * @param theWorkspaceFile
     *            the workspace file
     */
    public ImportGraphWizard(final String theWorkspaceFile) {
        super();
        setWindowTitle("Import");
        workspaceFile = theWorkspaceFile;
    }

    /**
     * {@inheritDoc}
     */
    public void addPages() {
        importPage = new ImportGraphWizardPage(workspaceFile);
        newFilePage = new ImportGraphNewFilePage(selection, importPage);
        addPage(importPage);
        addPage(newFilePage);
    }

    /**
     * {@inheritDoc}
     */
    public boolean performFinish() {
        importPage.savePreferences();
        try {
            // get the selected configuration
            AbstractImporter importer = importPage.getImporter();
            String filePath = importPage.getImportFile();
            boolean isWorkspacePath = importPage.isImportWorkspacePath();
            MapPropertyHolder options = importPage.getOptions();
            // open the import file
            InputStream stream = ImportUtil.createInputStream(filePath, isWorkspacePath);
            // perform the import
            IKielerProgressMonitor monitor = new KielerProgressMonitor(new NullProgressMonitor());
            Node graph = importer.doImport(stream, options, monitor);
            stream.close();
            // serialize KEG graph
            serializeKEGGraph(graph);
            // post process the model file
            importer.doModelPostProcess(newFilePage.createNewFile().getFullPath(), options);
            // create diagram file
            IPath diagramFile =
                    newFilePage.createNewFile().getFullPath().removeFileExtension()
                            .addFileExtension("kegdi");
            createKEGDiagram(newFilePage.createNewFile().getFullPath(), diagramFile);
            // post process the diagram file
            options.setProperty(ImportManager.OPTION_OPEN_DIAGRAM, true);
            importer.doDiagramPostProcess(diagramFile, options);
        } catch (Throwable throwable) {
            IStatus status = new Status(IStatus.ERROR, KEGImporterPlugin.PLUGIN_ID,
                    "Failed importing model file", throwable);
            StatusManager.getManager().handle(status, StatusManager.SHOW);
        }

        return true;
    }

    private void serializeKEGGraph(final Node graph) throws IOException {
        IFile file = newFilePage.createNewFile();
        URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toOSString(), true);
        URIConverter uriConverter = new ExtensibleURIConverterImpl();
        OutputStream outputStream = uriConverter.createOutputStream(fileURI);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.createResource(URI.createURI("http:///My.keg"));
        resource.getContents().add(graph);
        Map<String, Object> resourceOptions = new HashMap<String, Object>();
        resourceOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
        resourceOptions.put(XMLResource.OPTION_FORMATTED, true);
        // write to the stream
        resource.save(outputStream, resourceOptions);
        outputStream.close();
    }

    private void createKEGDiagram(final IPath modelFile, final IPath diagramFile)
            throws IOException {
        closeDiagramEditor(diagramFile);
        // load the model
        ResourceSet modelResourceSet = new ResourceSetImpl();
        URI modelFileURI = URI.createPlatformResourceURI(modelFile.toOSString(), true);
        Resource modelResource = modelResourceSet.getResource(modelFileURI, true);
        // create the diagram resource
        ResourceSet diagramResourceSet = new ResourceSetImpl();
        URI diagramFileURI = URI.createPlatformResourceURI(diagramFile.toOSString(), true);
        Resource diagramResource = diagramResourceSet.createResource(diagramFileURI);
        // create the diagram model and serialize it to the resource
        EObject model = (EObject) modelResource.getContents().get(0);
        Diagram diagram =
                ViewService.createDiagram(model, NodeEditPart.MODEL_ID,
                        GraphsDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        diagramResource.getContents().add(diagram);
        diagramResource.save(GraphsDiagramEditorUtil.getSaveOptions());
    }

    private void closeDiagramEditor(final IPath diagramPath) {
        final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
        if (diagramFile.exists()) {
            // close all editors which have the diagram file opened
            MonitoredOperation.runInUI(new Runnable() {
                public void run() {
                    IWorkbenchPage page =
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    IEditorInput input = new FileEditorInput(diagramFile);
                    IEditorPart editorPart;
                    while ((editorPart = page.findEditor(input)) != null) {
                        page.closeEditor(editorPart, false);
                    }
                }
            }, true);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench, final IStructuredSelection theselection) {
        selection = theselection;
    }
}
