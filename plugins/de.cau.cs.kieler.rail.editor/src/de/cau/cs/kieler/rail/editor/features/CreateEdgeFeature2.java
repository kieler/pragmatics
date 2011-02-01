package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;

import de.cau.cs.kieler.rail.Topologie.Model;
import de.cau.cs.kieler.rail.Topologie.Basegraph.BasegraphFactory;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Edge;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Vertex;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;
import de.cau.cs.kieler.rail.editor.KrailDiagramEditor;


/**
 * 1.   canStartConnection
 * 1.2  create
 * 2.   canCreate
 * 2.1  create
 * @author hdw
 *
 */

public class CreateEdgeFeature2 extends
       AbstractCreateConnectionFeature {
 
    public CreateEdgeFeature2 (IFeatureProvider fp) {
        // provide name and description for the UI, e.g. the palette
        super(fp, "Verbindung 2", "Verbindung 2 erstellen");
    }
 
    public boolean canCreate(ICreateConnectionContext context) {
       System.out.println("#canCreate#");
       Vertex source = getVertex(context.getSourceAnchor());
       Vertex target = getVertex(context.getTargetAnchor());
       if(source != null && target != null && source != target){
    	   System.out.println("#canCreate#  true");
    	   return true;
       }
       return false;
    }
 
    public boolean canStartConnection(ICreateConnectionContext context) {
    	System.out.println("##canStartConnection##");
    	if(getVertex(context.getSourceAnchor())!=null){
    		return true;
    	}
    	return false;
    }
 
    public Connection create(ICreateConnectionContext context) {
    	Connection newConnection = null;
    	
    	// get Vertex which sould be connected.
    	Vertex source = getVertex(context.getSourceAnchor());
    	Vertex target = getVertex(context.getTargetAnchor());
    	
    	if((source != null)&&(target != null)){
    		// create new business object
    		Edge link = BasegraphFactory.eINSTANCE.createEdge();
    		
    		AddConnectionContext addContext = 
    			new AddConnectionContext(context.getSourceAnchor(),context.getTargetAnchor());
    		
    		addContext.setNewObject(link);
    		
    		System.out.println("create X " + addContext.getX());
    		int x=0;
    		x++;
    		newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
    		
    		System.out.println("create X " + addContext.getX());
    	}
    	return newConnection;
    }
 
    /**
     * Returns the EClass belonging to the anchor, or null if not available.
     */
    private EClass getEClass(Anchor anchor) {
        if (anchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(anchor.getParent());
            if (object instanceof EClass) {
                return (EClass) object;
            }
        }
        return null;
    }
 
    /**
    * Creates a EReference between two EClasses.
    */
    private EReference createEReference(EClass source, EClass target) {
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eReference.setName("new EReference");
        eReference.setEType(target);
        eReference.setLowerBound(0);
        eReference.setUpperBound(1);
        source.getEStructuralFeatures().add(eReference);
        return eReference;
   }
    private Vertex getVertex(Anchor anchor){
    	if(anchor != null){
    		Object object = getBusinessObjectForPictogramElement(anchor.getParent());
    		if(object instanceof Vertex){
    			return (Vertex) object;
    		}
    	}
    	return null;
    }
}
