package de.cau.cs.kieler.rail.editor;


import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.model.graphiti.ui.GraphitiNewWizard;
import de.cau.cs.kieler.rail.Topologie.Model;
import de.cau.cs.kieler.rail.Topologie.TopologieFactory;

public class KrailNewWizard extends GraphitiNewWizard {

    public KrailNewWizard(){
    	super("Rail","krail","topologie","Rail",DiagramEditor.DIAGRAM_EDITOR_ID);
    }
    /**
     * {@inheritDoc}
     */
	@Override
	protected EObject createModel(String name) {
		Model model = TopologieFactory.eINSTANCE.createModel();
		return model;
	}

}
