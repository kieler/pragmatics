package de.cau.cs.kieler.ksbase.ui.test;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;

public class ModelObjectTester extends PropertyTester {

	public ModelObjectTester() {
		// TODO Auto-generated constructor stub
	}

	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		assert(args.length == 2);
		assert(args[0] instanceof String);
		assert(args[1] instanceof String);
		
		Transformation t = TransformationManager.instance.getEditorByName((String)args[0]).getTransformationById((String)args[1]);
		List<String> match = t.getParameterList();
		
		ISelection sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getSelectionService().getSelection();
		if (sel instanceof StructuredSelection) {
			Iterator<?> it = ((StructuredSelection) sel).iterator();
			while (it.hasNext()) {
				Object testingObject = it.next();
				if (testingObject instanceof EditPart) {
					Object model = ((EditPart) testingObject).getModel();
					if (model instanceof View) {
						View vep = (View) model;
						int idx = match.indexOf(vep.getElement().eClass().getName().toLowerCase());
						if ( idx > -1)
							match.remove(idx);
					}
				}
			}
		}
		return match.isEmpty();
	}

}
