package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.graphiti.dt.*;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;


public class DiagramTypeProvider extends AbstractDiagramTypeProvider {

    private  IToolBehaviorProvider[] toolBehaviorProviders;
    
    public DiagramTypeProvider() {
        super();
        setFeatureProvider(new FeatureProvider(this));
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
