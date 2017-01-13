package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.CustomLayoutProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;

@SuppressWarnings("all")
public class CustomOptions implements ILayoutMetaDataProvider {
  /**
   * The id of the Custom Layouter algorithm.
   */
  public final static String ALGORITHM_ID = "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.custom";
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new org.eclipse.elk.core.data.LayoutAlgorithmData(
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.custom",
        "Custom Layouter",
        null,
        new org.eclipse.elk.core.util.AlgorithmFactory(CustomLayoutProvider.class, ""),
        null,
        "Hierarchical Layout Algorithms",
        null,
        null
    ));
  }
}
