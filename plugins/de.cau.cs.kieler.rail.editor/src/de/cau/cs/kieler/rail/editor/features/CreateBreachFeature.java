package de.cau.cs.kieler.rail.editor.features;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.cau.cs.kieler.rail.Topologie.Model;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Einbruchsknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.SpecializedVerticesFactory;
import de.cau.cs.kieler.rail.editor.KrailDiagramEditor;


public class CreateBreachFeature extends AbstractCreateFeature  {
    private static final String TITLE = "Create class";
    
    private static final String USER_QUESTION = "Enter new class name";
 
    public CreateBreachFeature(IFeatureProvider fp) {
        // set name and description of the creation feature
        super(fp, "Einbruchstelle", "Einbruchstelle erstellen");
    }
 
    public boolean canCreate(ICreateContext context) {
        return context.getTargetContainer() instanceof Diagram;
    }
 
    public Object[] create(ICreateContext context) {
        // ask user for EClass name
        Einbruchsknoten vertex = SpecializedVerticesFactory.eINSTANCE.createEinbruchsknoten();
        Model model = ((KrailDiagramEditor) getDiagramEditor()).fetchModel(context.getTargetContainer());
        
        model.getVertices().add(vertex);    
        
        // do the add
        addGraphicalRepresentation(context, vertex);
 
        // return newly created business object(s)
        return new Object[] { vertex };
    }
}
