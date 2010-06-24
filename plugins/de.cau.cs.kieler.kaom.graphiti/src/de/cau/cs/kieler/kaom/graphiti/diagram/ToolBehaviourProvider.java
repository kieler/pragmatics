package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;

import de.cau.cs.kieler.kaom.Entity;

public class ToolBehaviourProvider extends DefaultToolBehaviorProvider {

    public ToolBehaviourProvider(IDiagramTypeProvider diagramTypeProvider) {
        super(diagramTypeProvider);
        // TODO Auto-generated constructor stub
    }
    
   @Override
   protected boolean isContextMenuApplicable(IFeature feature) {
       boolean ret = (feature instanceof ICustomFeature);
       return ret;

        }

   public GraphicsAlgorithm[] getSelectionArea(PictogramElement pe){
     
       IFeatureProvider featureProvider = getFeatureProvider();
       Object obj=featureProvider.getBusinessObjectForPictogramElement(pe);
       if(obj instanceof Entity)
       {
           GraphicsAlgorithm invisible=pe.getGraphicsAlgorithm();
           GraphicsAlgorithm rectangle=invisible.getGraphicsAlgorithmChildren().get(0);
           return new GraphicsAlgorithm[]{rectangle};
       }
     return super.getSelectionArea(pe);
   }
   
   @Override
   public GraphicsAlgorithm getSelectionGraphicsAlgorithm(PictogramElement pe)
   {
       IFeatureProvider featureProvider=getFeatureProvider();
       Object obj=featureProvider.getBusinessObjectForPictogramElement(pe);
       
       if(obj instanceof Entity)
       {
           GraphicsAlgorithm invisible=pe.getGraphicsAlgorithm();
           EList<GraphicsAlgorithm> graphicsAlgorithmChildren=invisible.getGraphicsAlgorithmChildren();
           
           if(!graphicsAlgorithmChildren.isEmpty())
           {
               return graphicsAlgorithmChildren.get(0);
            }
       }
     
       return super.getSelectionGraphicsAlgorithm(pe);
               
   }
   
}


