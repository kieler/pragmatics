package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;

import de.cau.cs.kieler.rail.Topologie.Model;
import de.cau.cs.kieler.rail.Topologie.Basegraph.BasegraphFactory;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Edge;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Vertex;
import de.cau.cs.kieler.rail.editor.KrailDiagramEditor;

public class CreateEdgeFeature extends
       AbstractCreateConnectionFeature {
 
    public CreateEdgeFeature (IFeatureProvider fp) {
        // provide name and description for the UI, e.g. the palette
        super(fp, "Verbindung", "Verbindung erstellen");
    }
 
    public boolean canCreate(ICreateConnectionContext context) {
        // return true if both anchors belong to an EClass
        // and those EClasses are not identical
        //TODO Vertex or Object???
    	Vertex source = null;//getEClass(context.getSourceAnchor());
        Vertex target = null;//getEClass(context.getTargetAnchor());
        Anchor sourceAnchor = context.getSourceAnchor();
        if (sourceAnchor != null){
        	source = (Vertex) getBusinessObjectForPictogramElement(sourceAnchor.getParent());
        }
        Anchor targetAnchor = context.getTargetAnchor();
        if (targetAnchor != null) {
            target = (Vertex) getBusinessObjectForPictogramElement(targetAnchor.getParent());
        }
        
        //TODO What instead of Linkable
        return (sourceAnchor == null || source instanceof Vertex)
        	&& (targetAnchor == null || target instanceof Vertex);
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
            target = (Vertex) getBusinessObjectForPictogramElement(context.getTargetAnchor());
        } else if (targetAnchor != null) {
            target = (Vertex) getBusinessObjectForPictogramElement(context.getTargetAnchor().getParent());
        }
        
        
        if (source instanceof Vertex && target instanceof Vertex) {
        	Edge link = BasegraphFactory.eINSTANCE.createEdge();
        	//Port link = BasegraphFactory.eINSTANCE.createPort();
        	Port sourcePort =  BasegraphFactory.eINSTANCE.createPort();
        	sourcePort.setVertex((Vertex) source);
        	
        	Port targetPort =  BasegraphFactory.eINSTANCE.createPort();
        	targetPort.setVertex((Vertex) target);
        	
        	
            link.setBegin(sourcePort);
            link.setEnd(targetPort);
            targetPort.setEdge(link);
            sourcePort.setEdge(link);
            
            Model topModel = ((KrailDiagramEditor) getDiagramEditor()).fetchModel(getDiagram());
            
            //TODO Make the linkt
            topModel.getEdges().add(link);
            
            getFeatureProvider().getDirectEditingInfo().setActive(true);
            AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(),
                    context.getTargetAnchor());
            addContext.setNewObject(link);
            return (Connection) getFeatureProvider().addIfPossible(addContext);
        }
        
        
        /*
        if (source != null && target != null) {
            // create new business object
            EReference eReference = createEReference(source, target);
            // add connection for business object
            AddConnectionContext addContext =
                new AddConnectionContext(context.getSourceAnchor(), context
                    .getTargetAnchor());
            addContext.setNewObject(eReference);
            newConnection =
                (Connection) getFeatureProvider().addIfPossible(addContext);
        }
       
        return newConnection;*/
        return null;
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
}
