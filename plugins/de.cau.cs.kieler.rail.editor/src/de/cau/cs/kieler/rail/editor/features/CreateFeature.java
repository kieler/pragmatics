package de.cau.cs.kieler.rail.editor.features;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.cau.cs.kieler.rail.Topologie.Model;

import de.cau.cs.kieler.rail.Topologie.Basegraph.Vertex;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.SpecializedVerticesFactory;
import de.cau.cs.kieler.rail.editor.KrailDiagramEditor;


public class CreateFeature extends AbstractCreateFeature  {
    private static final String TITLE = "Create class";
    
    private static final String USER_QUESTION = "Enter new class name";

	private TypeFeatures type;
 
    public CreateFeature(IFeatureProvider fp,TypeFeatures type) {
        // set name and description of the creation feature
    	super(fp, getName(type), getName(type) + " erstellen");
    	this.type = type;
    }
 
    public boolean canCreate(ICreateContext context) {
        return context.getTargetContainer() instanceof Diagram;
    }
 
    public Object[] create(ICreateContext context) {
        // ask user for Einbruchsknoten name
        //Einbruchsknoten vertex = SpecializedVerticesFactory.eINSTANCE.createEinbruchsknoten();
    	Vertex vertex = getVertex();
        
        KrailDiagramEditor kde = ((KrailDiagramEditor) getDiagramEditor());
        ContainerShape tc = context.getTargetContainer();
        Model model = kde.fetchModel(tc);
        
        //Model model = ((KrailDiagramEditor) getDiagramEditor()).fetchModel(context.getTargetContainer());
        
        
        model.getVertices().add(vertex);    
        
        // do the add
        addGraphicalRepresentation(context, vertex);
 
        // return newly created business object(s)
        return new Object[] { vertex };
    }
    
    
    private Vertex getVertex()
    {
		switch(type){
    	case BREANCH:
        	return SpecializedVerticesFactory.eINSTANCE.createEinbruchsknoten();
    	case DEADENDVERTEX:
    		return SpecializedVerticesFactory.eINSTANCE.createStumpfgleisknoten();
    	//TODO Make for both cases possible.
    	case SWITCHVERTEX_LEFT:
    	case SWITCHVERTEX_RIGHT:
    		Vertex vertex = SpecializedVerticesFactory.eINSTANCE.createWeichenknoten();
    		return vertex;
		}
    	return null;
    }
    
    private static String getName(TypeFeatures type){
    	switch(type){
    	case BREANCH:
        	return "Einbruchstelle";
    	case DEADENDVERTEX:
    		return "Stumpfgleisknoten";
    	case SWITCHVERTEX_LEFT:
    		return "Weiche links";
    	case SWITCHVERTEX_RIGHT:
    		return "Weiche rechts";
    	}
    	return "";
    }
    
}
