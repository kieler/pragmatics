package de.cau.cs.kieler.rail.editor.features;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.rail.Topologie.Model;

import de.cau.cs.kieler.rail.Topologie.Basegraph.BasegraphFactory;
import de.cau.cs.kieler.rail.Topologie.Basegraph.EPort;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Vertex;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.SpecializedVerticesFactory;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;
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
    	
    	//Ports will be create.
    	Port abzweig = BasegraphFactory.eINSTANCE.createPort();
    	abzweig.setName(EPort.ABZWEIG);
    	Port stamm = BasegraphFactory.eINSTANCE.createPort();
    	stamm.setName(EPort.STAMM);
    	Port spitze = BasegraphFactory.eINSTANCE.createPort();
    	spitze.setName(EPort.SPITZE);
    	
    	vertex.getPorts().add(abzweig);
    	vertex.getPorts().add(stamm);
    	vertex.getPorts().add(spitze);
    	
    	//PictogramElement v = addGraphicalRepresentation(context, vertex);
    	
    	
    	
    	
        KrailDiagramEditor kde = ((KrailDiagramEditor) getDiagramEditor());
        ContainerShape tc = context.getTargetContainer();
        Model model = kde.fetchModel(tc);
        
        //Model model = ((KrailDiagramEditor) getDiagramEditor()).fetchModel(context.getTargetContainer());
        
        model.getVertices().add(vertex);  
        
        // do the add
        
        PictogramElement v = addGraphicalRepresentation(context, vertex);
 
        PictogramElement ab = addGraphicalRepresentation(context, abzweig);
    	//not call with context instated something else    nicht mit context, sondern mit was anderem Aufrufen
        
        addGraphicalRepresentation(context, stamm);
    	addGraphicalRepresentation(context, spitze);
        
        // return newly created business object(s)
        return new Object[] { vertex };
    }
    /*
    public Object[] create(final ICreateContext context) {
        Vertex vertex = (Vertex) getBusinessObjectForPictogramElement(context.getTargetContainer());
        Port port = BasegraphFactory.eINSTANCE.createPort();
        //TODO allow only 2 or 3.
        vertex.getPorts().add(port);

        addGraphicalRepresentation(context, port);
        return new Object[] { port };
    }*/
    
    
    private Vertex getVertex()
    {
    	Vertex vertex;
		switch(type){
    	case BREANCH:
        	return SpecializedVerticesFactory.eINSTANCE.createEinbruchsknoten();
    	case DEADENDVERTEX:
    		return SpecializedVerticesFactory.eINSTANCE.createStumpfgleisknoten();
    	//TODO Make for both cases possible.
    	case SWITCHVERTEX_LEFT:
    		vertex = SpecializedVerticesFactory.eINSTANCE.createWeichenknoten();
    		((Weichenknoten)vertex).setAbzweigendeLage(EOrientation.LINKS);
    		return vertex;
    	case SWITCHVERTEX_RIGHT:
    		vertex = SpecializedVerticesFactory.eINSTANCE.createWeichenknoten();
    		((Weichenknoten)vertex).setAbzweigendeLage(EOrientation.RECHTS);
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
