package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalStressLayoutProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;

@SuppressWarnings("all")
public class HierarchicalStressOptions implements ILayoutMetaDataProvider {
  /**
   * The id of the Stress Layouter algorithm.
   */
  public final static String ALGORITHM_ID = "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.stress";
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new org.eclipse.elk.core.data.LayoutAlgorithmData(
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.stress",
        "Stress Layouter",
        null,
        new org.eclipse.elk.core.util.AlgorithmFactory(HierarchicalStressLayoutProvider.class, ""),
        null,
        "Hierarchical Layout Algorithms",
        null,
        null
    ));
  }
}
