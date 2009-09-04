package de.itemis.gmf.runtime.combolabel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

public interface ISemanticRedirectingEditPart extends IGraphicalEditPart {

	List<? extends EObject> getCandidates();
	
	void setValue(EObject value);
	
	EObject getValue();
}
