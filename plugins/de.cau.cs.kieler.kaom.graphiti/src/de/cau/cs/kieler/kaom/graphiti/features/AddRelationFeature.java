package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Polygon;
import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Relation;

public class AddRelationFeature extends AbstractAddShapeFeature {

    public AddRelationFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    public PictogramElement add(IAddContext context) {
        // TODO Auto-generated method stub
     //   KaomFactory kaomFactory=KaomFactory.eINSTANCE;
       
     //   Relation relation=kaomFactory.createRelation();
       Relation relation=(Relation)context.getNewObject();
        IPeCreateService peCreateService=Graphiti.getPeCreateService();
        ContainerShape containerShape = (ContainerShape)context.getTargetContainer();
        Entity parentEntity = null;
        Shape shape=peCreateService.createShape(containerShape, true);
        IGaService gaService=Graphiti.getGaService();
       // RoundedRectangle polygon=gaService.createRoundedRectangle(containerShape, 5, 5);
        Polygon polygon= gaService.createPolygon(shape, new int[]{context.getX()-9,context.getY(),context.getX(),context.getY()+12,context.getX()+9,context.getY(),context.getX(),context.getY()-12});        
        polygon.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
        polygon.setBackground(manageColor(new ColorConstant(70,70,70)));
     //   gaService.setLocationAndSize(polygon,context.getX(),context.getY(),70,80);
        

       
        parentEntity=(Entity)getBusinessObjectForPictogramElement(context.getTargetContainer());
        parentEntity.getChildRelations().add(relation);
   
        peCreateService.createChopboxAnchor(shape);          
        
         link(shape, relation);
         
      //   layoutPictogramElement(containerShape);               
         
         return containerShape;
    }

    public boolean canAdd(IAddContext context) {
        // TODO Auto-generated method stub
        if (context.getNewObject() instanceof Relation)
        {
            
            if(context.getTargetContainer() instanceof ContainerShape )
                return true;
        }
        return false;
    }

}
