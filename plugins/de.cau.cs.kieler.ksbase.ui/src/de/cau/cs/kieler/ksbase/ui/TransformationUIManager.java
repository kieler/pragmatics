package de.cau.cs.kieler.ksbase.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.layout.AutoLayoutTrigger;
import de.cau.cs.kieler.ksbase.ui.handler.ExecuteTransformationRequest;
import de.cau.cs.kieler.viewmanagement.RunLogic;

public class TransformationUIManager {

    public static TransformationUIManager instance = new TransformationUIManager();
    
    /**
     * Creates and executes a transformation command by creating a request and
     * execute the resulting command on the diagram command stack
     * 
     * @param event
     *            Execution event for which this command should be created
     * @param editor
     *            The editor for which this transformation is
     * @param transformation
     *            The transformation that should be executed
     */
    public void createAndExecuteTransformationCommand(ExecutionEvent event,
            EditorTransformationSettings editor, Transformation transformation) {
        IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);

        ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
                .getSelectionService().getSelection();

        if (selection instanceof StructuredSelection && !selection.isEmpty()) {

            // First, we have to store the transformation file because Xtend
            // doesn't execute strings
            // We have to do this every time, because we can't be sure that the
            // file we wrote last time is still valid. We will write it
            // to the meta-inf folder:

            EditPart selectedElement = (EditPart) ((StructuredSelection) selection)
                    .getFirstElement();
            File file = null;
            try {
                IPath path = ResourcesPlugin.getPlugin().getStateLocation();
                file = File.createTempFile("extension", ".ext", new File(path
                        .toOSString()));
                FileOutputStream out = new FileOutputStream(file);
                if (!file.exists()) {
                    if (!file.createNewFile()) 
                        return;//FIXME: We were unable to create the file ! Add Error-log
                }
                out.write(editor.getExtFile().getBytes());
                out.flush();
                out.close();

                // Create request
                ExecuteTransformationRequest request = new ExecuteTransformationRequest(
                        activeEditor, transformation.getTransformationName(),
                        file.getAbsolutePath(), selection, transformation
                                .getNumSelections(), editor
                                .getModelPackageClass());
                Command transformationCommand = selectedElement
                        .getCommand(request);

                // gets a command stack to execute the command
                DiagramCommandStack commandStack = null;
                Object adapter = activeEditor.getAdapter(CommandStack.class);
                if (adapter instanceof DiagramCommandStack)
                    commandStack = (DiagramCommandStack) adapter;
                if (commandStack == null)
                    commandStack = new DiagramCommandStack(null);
                commandStack.execute(transformationCommand);
            } catch (FileNotFoundException e) {

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                // Remove temporary Xtend file
                if (file != null) {
                    if ( !file.delete() )
                        System.out.println("Warning, unable to delete temporary xtend file"); //maybe just ignore or add a warning 
                }
                // update edit policies, so GMF will generate diagram elements
                // for model elements which have been generated during the
                // transformation but
                // not translated to gmf now:
                if (activeEditor instanceof DiagramEditor) {
                    EObject obj = ((View) ((DiagramEditor) activeEditor)
                            .getDiagramEditPart().getModel()).getElement();
                    List<?> editPolicies = CanonicalEditPolicy
                            .getRegisteredEditPolicies(obj);
                    for (Iterator<?> it = editPolicies.iterator(); it.hasNext();) {
                        CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it
                                .next();
                        nextEditPolicy.refresh();
                    }
                    //IFigure figure = ((DiagramEditor)activeEditor).getDiagramEditPart().getFigure();
                    //figure.invalidateTree();
                    IDiagramGraphicalViewer graphViewer = ((DiagramEditor)activeEditor).getDiagramGraphicalViewer();
                    graphViewer.flush();
                                        
                    // If auto-layout is activated, execute now:
                    if (editor.isPerformAutoLayout()) {
                        EditPart e = ((DiagramEditor) activeEditor)
                                .getDiagramEditPart().getRoot().getContents();
                        while (!(e instanceof ShapeEditPart)) {
                            e = (EditPart) e.getChildren().get(0);
                        }
                        
                        ((AutoLayoutTrigger) RunLogic
                                .getTrigger("de.cau.cs.kieler.ksbase.layout.AutoLayoutTrigger"))
                                .triggerAutoLayout(e, activeEditor);

                    }
                }

            }
        }
    }
}
