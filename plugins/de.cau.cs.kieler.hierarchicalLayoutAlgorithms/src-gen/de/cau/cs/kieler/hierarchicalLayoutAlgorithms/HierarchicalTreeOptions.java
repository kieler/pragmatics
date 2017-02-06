package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.HierarchicalTreeLayoutProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;

@SuppressWarnings("all")
public class HierarchicalTreeOptions implements ILayoutMetaDataProvider {
  /**
   * The id of the Tree Layouter algorithm.
   */
  public final static String ALGORITHM_ID = "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.tree";
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new org.eclipse.elk.core.data.LayoutAlgorithmData(
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.tree",
        "Tree Layouter",
        null,
        new org.eclipse.elk.core.util.AlgorithmFactory(HierarchicalTreeLayoutProvider.class, ""),
        null,
        "Hierarchical Layout Algorithms",
        null,
        null
    ));
  }
}
