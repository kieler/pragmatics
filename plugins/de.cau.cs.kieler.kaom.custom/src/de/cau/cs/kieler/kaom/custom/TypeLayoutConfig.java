package de.cau.cs.kieler.kaom.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.SemanticLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

public class TypeLayoutConfig extends SemanticLayoutConfig{

    @Override
    protected List<LayoutOptionData<?>> getOptionData(final EObject semanticElem) {
        if (semanticElem instanceof Entity) {
            LayoutOptionData<?> optionData = LayoutServices.getInstance()
            .getOptionData(LayoutOptions.DIAGRAM_TYPE_ID);
            List<LayoutOptionData<?>> list = new ArrayList<LayoutOptionData<?>>(1);
            list.add(optionData);
            return list;
        }
        return Collections.emptyList();
    }

    @Override
    protected Object getSemanticProperty(final EObject semanticElem, final LayoutOptionData<?> layoutOption) {
        if (semanticElem instanceof Entity && layoutOption.getId()
                .equals(LayoutOptions.DIAGRAM_TYPE_ID)) {
            Entity entity = (Entity) semanticElem;
            StringAnnotation typeAnn = (StringAnnotation) entity.getAnnotation("DiagramType"); 
            if ((typeAnn == null) || (typeAnn.getValue().equals("DataFlow"))) {
                return "de.cau.cs.kieler.layout.diagrams.dataFlow";
            } else if (typeAnn.getValue().equals("StateMachine")) {
                return "de.cau.cs.kieler.layout.diagrams.stateMachine";
            } else {
                return "de.cau.cs.kieler.layout.diagrams.dataFlow";
            }
        }
        return null;
    }

    @Override
    protected void setSemanticProperty(final EObject semanticElem, final LayoutOptionData<?> layoutOption,
            final Object value) {
        // TODO Auto-generated method stub
        
    }

    
}
