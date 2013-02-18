package de.cau.cs.kieler.kaom.karma.ptolemy.krendering;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import de.cau.cs.kieler.core.kivi.IEffect;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorPlugin;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorUtil;
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect;

public class OpenKaomKlighdViewHandler extends AbstractHandler implements IHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {
        String commandid = event.getCommand().getId();
        if (commandid.endsWith("openkaomklighd")) {
            ISelection selection = HandlerUtil.getCurrentSelection(event);
            if (selection instanceof IStructuredSelection) {
                Iterator<?> iter = ((IStructuredSelection) selection).iterator();
                while (iter.hasNext()) {
                    Object o = iter.next();
                    IFile file = null;
                    if (o instanceof IFile) {
                        file = (IFile) o;
                        EList<EObject> objects = loadModel(file);
                        EObject obj = objects.get(0);
                        if (obj instanceof Entity) {
                            IEffect effect = new KlighdUpdateDiagramEffect(file.getName(), obj);
                            effect.execute();
                        }
                    }
                }
            }
        } else if (commandid.endsWith("measureopentime")) {
            ISelection selection = HandlerUtil.getCurrentSelection(event);
            if (selection instanceof IStructuredSelection) {
                Iterator<?> iter = ((IStructuredSelection) selection).iterator();
                while (iter.hasNext()) {
                    Object o = iter.next();
                    IFolder folder = null;
                    if (o instanceof IFolder) {                        
                        folder = (IFolder) o;
                        List<IFile> files = getAllKaomFiles(folder, new LinkedList<IFile>());
                        
                        measureGmf(files);
                        measureKlighd(files);
                        
                        String csv = "file,gmf1diagram,gmf1open,gmf2diagram,gmf2open,gmf3diagram,gmf3open,gmf4diagram,gmf4open,gmf5diagram,gmf5open,klighd1,klighd2,klighd3,klighd4,klighd5" + "\n";
                        for (String entry: csvstrings.values()) {
                            csv += entry + "\n";
                        }
                        String filepath = folder.getLocation().append("measurements.csv").toString();
                        System.out.println(filepath);
                        //java.net.URI uri = java.net.URI.create(filepath);
                        File csvfile = new File(filepath);
                        try {
                            csvfile.createNewFile();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        FileWriter fstream;
                        try {
                            fstream = new FileWriter(csvfile);
                            BufferedWriter out = new BufferedWriter(fstream);
                            out.write(csv);
                            out.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(csv);
                    }
                    
                }
            }
            System.out.println("test");
        }
        return null;
    }
    
    private HashMap<String, String> csvstrings = new HashMap<String, String>();
    
    private void measureKlighd(List<IFile> files) {
        for (IFile file : files) {
            EList<EObject> objects = loadModel(file);
            EObject obj = objects.get(0);
            if (obj instanceof Entity) {
                
                IEffect effect = new KlighdUpdateDiagramEffect(file.getName(), obj, csvstrings, file);
                for (int i = 0; i < 5; i++) {
                    effect.execute();
                    System.gc();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                
                }
            }
        }
    }

    private void measureGmf(List<IFile> files) {
        //csvstrings.put("file", "file,gmf1,gmf2,gmf3,gmf4,gmf5,klighd1,klighd2,klighd3,klighd4,klighd5");
        
        for (IFile file : files) {
            try {
                String csventry = file.getFullPath().toString();
                IPath kaodPath = new Path(file.getFullPath().toString().replaceAll("kaom", "kaod"));
                for (int i = 0; i < 5; i++) { 
                    long start = System.nanoTime();
                    this.createDiagram(file.getFullPath(), kaodPath);
                    long created = System.nanoTime();
                    this.openDiagram(kaodPath);
                    long open = System.nanoTime();
                    this.closeDiagram(kaodPath);
                    csventry += "," + (open - start);
                    csventry += "," + (open - created);
                    //System.out.println("GMF// " + "opendiagram: " + (open - created) + "create+open" + (open - start));
                    System.gc();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                csvstrings.put(file.getFullPath().toString(), csventry);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        
    }
    
    private List<IFile> getAllKaomFiles(IFolder parent, List<IFile> files) {
        try {
            IResource[] children = parent.members();
            for (IResource r : children) {
                if (r instanceof IFile) {
                    if (((IFile) r).getName().endsWith(".kaom")) {
                        files.add((IFile) r);
                    }
                } else if (r instanceof IFolder) {
                    getAllKaomFiles((IFolder) parent, files);
                }
            }
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return files;
    }
    
    private EList<EObject> loadModel(IFile file) {
            try {
                ResourceSet resourceSet = new ResourceSetImpl();
                Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(
                                file.getFullPath().toString(), true), true);
                final Map<String, Boolean> options = new HashMap<String, Boolean>();
                options.put(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE);
                
                resource.load(options);
                
                
                
                EList<EObject> models = new BasicEList<EObject>(resource.getContents());
                //recursiveSetNamesIfUnset(models);
                return models;
        } catch (final Exception e) {
            return null;
        }
    }
    
    /**
     * Initializes a diagram from the given model file.
     * 
     * @param modelFile the model file to create a diagram for.
     * @param diagramFile path of the diagram file to create.
     * @throws IOException if anything goes wrong.
     */
    private void createDiagram(final IPath modelFile, final IPath diagramFile) throws IOException {
        //closeDiagram(diagramFile);
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
                ViewService.createDiagram(model, EntityEditPart.MODEL_ID,
                        KaomDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        diagramResource.getContents().add(diagram);
        diagramResource.save(KaomDiagramEditorUtil.getSaveOptions());
    }
    
    /**
     * Opens the given diagram file.
     * 
     * @param diagramPath path to the diagram file that is to be opened.
     */
    private void openDiagram(final IPath diagramPath) {
        final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page =
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                // open the diagram file in an editor
                IEditorDescriptor editorDescriptor = IDE.getDefaultEditor(diagramFile);
                if (editorDescriptor == null || editorDescriptor.isOpenExternal()) {
                    // if no editor to open the diagram is present ignore it
                    return;
                }
                try {
                    IDE.openEditor(page, diagramFile, editorDescriptor.getId(), true);
                } catch (PartInitException e) {
                    // opening the diagram failed but as it is optional it can be safely ignored
                }
            }
        });
    }
    
    /**
     * Closes the given diagram file if it is open in any editors.
     * 
     * @param diagramPath the diagram file to close.
     */
    private void closeDiagram(final IPath diagramPath) {
        final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
        if (diagramFile.exists()) {
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    // close all editors which have the diagram file opened
                    IWorkbenchPage page =
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    IEditorInput input = new FileEditorInput(diagramFile);
                    IEditorPart editorPart;
                    while ((editorPart = page.findEditor(input)) != null) {
                        page.closeEditor(editorPart, false);
                    }
                }
            });
        }
    }
    
}
