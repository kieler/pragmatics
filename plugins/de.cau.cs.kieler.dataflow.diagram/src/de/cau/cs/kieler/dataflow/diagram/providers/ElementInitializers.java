package de.cau.cs.kieler.dataflow.diagram.providers;

import de.cau.cs.kieler.dataflow.Box;
import de.cau.cs.kieler.dataflow.DataflowPackage;
import de.cau.cs.kieler.dataflow.diagram.expressions.DataflowOCLFactory;
import de.cau.cs.kieler.dataflow.diagram.part.DataflowDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {
    /**
     * @generated
     */
    public static void init_Box_2001(Box instance) {
        try {
            Object value_0 = DataflowOCLFactory.getExpression("\'Box\'",
                    DataflowPackage.eINSTANCE.getBox()).evaluate(instance);
            instance.setName((String) value_0);
        }
        catch (RuntimeException e) {
            DataflowDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public static void init_Box_3003(Box instance) {
        try {
            Object value_0 = DataflowOCLFactory.getExpression("\'Box\'",
                    DataflowPackage.eINSTANCE.getBox()).evaluate(instance);
            instance.setName((String) value_0);
        }
        catch (RuntimeException e) {
            DataflowDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
        }
    }

}
