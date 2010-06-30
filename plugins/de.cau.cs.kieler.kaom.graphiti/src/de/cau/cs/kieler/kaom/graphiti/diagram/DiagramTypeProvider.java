package de.cau.cs.kieler.kaom.graphiti.diagram;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.dt.*;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;


public class DiagramTypeProvider extends AbstractDiagramTypeProvider {

    private  IToolBehaviorProvider[] toolBehaviorProviders;
    public static Entity topEntity;
    
    public DiagramTypeProvider() {
        super();
        setFeatureProvider(new FeatureProvider(this));
      
       if (getDiagram() == null) {
            System.out.println("This is not possible");
            }
    /*    List<EObject> contents = getDiagram().eResource().getContents();
        if(contents==null || contents.size()==0) {
            topEntity = KaomFactory.eINSTANCE.createEntity();
            contents.add(topEntity);
        }
    }*/
    }
    
    @Override
    public boolean isAutoUpdateAtRuntime() {
        return true;
    }

    @Override
    public boolean isAutoUpdateAtStartup() {
        return true;
    }
    
    
    @Override
    public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
        if (toolBehaviorProviders == null) {
            toolBehaviorProviders = new IToolBehaviorProvider[] { new ToolBehaviourProvider(
                    this) };
        }
        return toolBehaviorProviders;

    }
    
            

}
