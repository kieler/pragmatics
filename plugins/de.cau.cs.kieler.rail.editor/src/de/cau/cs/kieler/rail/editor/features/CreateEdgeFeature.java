package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;

import de.menges.topologie.Topologie.Model;
import de.menges.topologie.Topologie.Basegraph.BasegraphFactory;
import de.menges.topologie.Topologie.Basegraph.Edge;
import de.menges.topologie.Topologie.Basegraph.Port;
import de.menges.topologie.Topologie.Basegraph.Vertex;
import de.menges.topologie.Topologie.SpecializedVertices.*;
import de.cau.cs.kieler.rail.editor.KrailDiagramEditor;


/**
 * 1.   canStartConnection
 * 1.2  create
 * 2.   canCreate
 * 2.1  create
 * @author hdw
 *
 */

public class CreateEdgeFeature extends
       AbstractCreateConnectionFeature {
 
    public CreateEdgeFeature (IFeatureProvider fp) {
        // provide name and description for the UI, e.g. the palette
        super(fp, "Verbindung", "Verbindung erstellen");
    }
 
    public boolean canCreate(ICreateConnectionContext context) {
        // return true if both anchors belong to an EClass
        // and those EClasses are not identical


    	Object source = null;//getEClass(context.getSourceAnchor());
        Object target = null;//getEClass(context.getTargetAnchor());
        Anchor sourceAnchor = context.getSourceAnchor();
        if (sourceAnchor != null){
        	source = getBusinessObjectForPictogramElement(sourceAnchor.getParent());
        }
        Anchor targetAnchor = context.getTargetAnchor();
        if (targetAnchor != null) {
            target =  getBusinessObjectForPictogramElement(targetAnchor.getParent());
        }
        
        if(source != null && target != null){
        	source=source;
        }
        
        return (sourceAnchor != null || source instanceof Vertex)
        	&& (targetAnchor != null || target instanceof Vertex);
    }
 
    public boolean canStartConnection(ICreateConnectionContext context) {
    	return (context.getSourceAnchor() != null
                && getBusinessObjectForPictogramElement(
                context.getSourceAnchor().getParent()) != null);
    }
 
    public Connection create(ICreateConnectionContext context) {
    	Anchor sourceAnchor = context.getSourceAnchor();
 
        // get Vertex which should be connected
        Object source = null;
        Object target=null;
        
        if (sourceAnchor instanceof BoxRelativeAnchor) {
            source =  getBusinessObjectForPictogramElement(context.getSourceAnchor());
        } else if (sourceAnchor != null) {
            source = getBusinessObjectForPictogramElement(context.getSourceAnchor().getParent());

        }
        
        Anchor targetAnchor = context.getTargetAnchor();
        if (targetAnchor instanceof BoxRelativeAnchor) {
            target = getBusinessObjectForPictogramElement(context.getTargetAnchor());
            
        } else if (targetAnchor != null) {
            target = getBusinessObjectForPictogramElement(context.getTargetAnchor().getParent());
            
        }
        
        
        if (source instanceof Port && target instanceof Port) {
        	Edge link = BasegraphFactory.eINSTANCE.createEdge();
        	link.setFrom((Port) source);
        	link.setDestination((Port) target);
            
            Model topModel = ((KrailDiagramEditor) getDiagramEditor()).fetchModel(getDiagram());
            
            //TODO Make the link I thing it is not necessary
            //topModel.getEdges().add(link);
            
            
            getFeatureProvider().getDirectEditingInfo().setActive(true);
            AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(),
                    context.getTargetAnchor());
            addContext.setNewObject(link);
            return (Connection) getFeatureProvider().addIfPossible(addContext);
        }
        return null;
    }
}
