package de.cau.cs.kieler.ksbase.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.ui.TransformationUIManager;

public class TransformationCommandHandler extends AbstractHandler implements
        IHandler {

    private EditorTransformationSettings editor;
    private Transformation transformation;

    public TransformationCommandHandler(EditorTransformationSettings editor,
            Transformation transformation) {
        this.editor = editor;
        this.transformation = transformation;
    }

    public Object execute(ExecutionEvent event) throws ExecutionException {
        TransformationUIManager.instance
                .createAndExecuteTransformationCommand(event, editor,
                        transformation);
        return null;
    }
}
