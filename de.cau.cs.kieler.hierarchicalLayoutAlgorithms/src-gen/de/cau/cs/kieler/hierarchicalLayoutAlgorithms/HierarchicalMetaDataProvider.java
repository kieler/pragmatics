package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class HierarchicalMetaDataProvider implements ILayoutMetaDataProvider {
  public final static IProperty<Integer> HIERARCHICAL_I_D = new Property<Integer>(
            "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.hierarchicalID");
  
  public final static IProperty<Integer> HIERARCHICAL_PARENT_I_D = new Property<Integer>(
            "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.hierarchicalParentID");
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new org.eclipse.elk.core.data.LayoutOptionData(
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.hierarchicalID",
        "",
        "Hierarchical ID",
        null,
        null,
        null,
        null,
        org.eclipse.elk.core.data.LayoutOptionData.Type.INT,
        Integer.class,
        EnumSet.of(org.eclipse.elk.core.data.LayoutOptionData.Target.PARENTS),
        org.eclipse.elk.core.data.LayoutOptionData.Visibility.HIDDEN
        , "de.cau.cs.kieler.hierarchicalID"
    ));
    registry.register(new org.eclipse.elk.core.data.LayoutOptionData(
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.hierarchicalParentID",
        "",
        "Hierarchical Parent ID",
        null,
        null,
        null,
        null,
        org.eclipse.elk.core.data.LayoutOptionData.Type.INT,
        Integer.class,
        EnumSet.of(org.eclipse.elk.core.data.LayoutOptionData.Target.PARENTS),
        org.eclipse.elk.core.data.LayoutOptionData.Visibility.HIDDEN
        , "de.cau.cs.kieler.hierarchicalParentID"
    ));
    new de.cau.cs.kieler.hierarchicalLayoutAlgorithms.RadialOptions().apply(registry);
    new de.cau.cs.kieler.hierarchicalLayoutAlgorithms.GridOptions().apply(registry);
    new de.cau.cs.kieler.hierarchicalLayoutAlgorithms.CustomOptions().apply(registry);
  }
}
