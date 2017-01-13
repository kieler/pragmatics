package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalMetaDataProvider;
import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.RadialLayoutProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.properties.IProperty;

@SuppressWarnings("all")
public class RadialOptions implements ILayoutMetaDataProvider {
  /**
   * The id of the Radial Layouter algorithm.
   */
  public final static String ALGORITHM_ID = "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial";
  
  /**
   * Property constant to access Border Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_BORDER = CoreOptions.SPACING_BORDER;
  
  /**
   * Property constant to access Node Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_NODE = CoreOptions.SPACING_NODE;
  
  /**
   * Property constant to access Hierarchical ID  from within the layout algorithm code.
   */
  public final static IProperty<Integer> HIERARCHICAL_I_D = HierarchicalMetaDataProvider.HIERARCHICAL_I_D;
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new org.eclipse.elk.core.data.LayoutAlgorithmData(
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial",
        "Radial Layouter",
        null,
        new org.eclipse.elk.core.util.AlgorithmFactory(RadialLayoutProvider.class, ""),
        null,
        "Hierarchical Layout Algorithms",
        null,
        null
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial",
        "org.eclipse.elk.spacing.border",
        SPACING_BORDER.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial",
        "org.eclipse.elk.spacing.node",
        SPACING_NODE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.radial",
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.hierarchicalID",
        HIERARCHICAL_I_D.getDefault()
    );
  }
}
