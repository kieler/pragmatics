package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;

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

}


