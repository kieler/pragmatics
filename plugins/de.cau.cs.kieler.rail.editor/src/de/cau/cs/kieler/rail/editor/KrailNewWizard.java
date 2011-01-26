package de.cau.cs.kieler.rail.editor;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Diagram;

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
    protected void configureDiagram(final Diagram diagram) {
		Property p = MmFactory.eINSTANCE.createProperty();
		p.setKey("layout:de.cau.cs.kieler.layout.options.layoutHint");
		p.setValue("de.cau.cs.kieler.klay.rail");
		diagram.getProperties().add(p);
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
