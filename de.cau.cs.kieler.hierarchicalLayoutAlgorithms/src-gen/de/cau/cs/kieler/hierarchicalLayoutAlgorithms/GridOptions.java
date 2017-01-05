package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import de.cau.cs.kieler.hierarchicalLayoutAlgorithms.GridSnapLayoutProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;

@SuppressWarnings("all")
public class GridOptions implements ILayoutMetaDataProvider {
  /**
   * The id of the Grid Snap Layouter algorithm.
   */
  public final static String ALGORITHM_ID = "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.grid";
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new org.eclipse.elk.core.data.LayoutAlgorithmData(
        "de.cau.cs.kieler.hierarchicalLayoutAlgorithms.grid",
        "Grid Snap Layouter",
        "Layouter that uses a force based algorithm and places the nodes in a grid afterwards.",
        new org.eclipse.elk.core.util.AlgorithmFactory(GridSnapLayoutProvider.class, ""),
        null,
        "Hierarchical Layout Algoritms",
        null,
        null
    ));
  }
}
