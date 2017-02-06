/**
 * Declarations for the ELK Layered layout algorithm.
 */
package de.cau.cs.kieler.overlapRemoval.options;

import de.cau.cs.kieler.overlapRemoval.OverlapRemovalLayoutProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;

@SuppressWarnings("all")
public class OverlapRemovalOptions implements ILayoutMetaDataProvider {
  /**
   * The id of the Overlap Removal algorithm.
   */
  public final static String ALGORITHM_ID = "de.cau.cs.kieler.overlapRemoval";
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new org.eclipse.elk.core.data.LayoutAlgorithmData(
        "de.cau.cs.kieler.overlapRemoval",
        "Overlap Removal",
        null,
        new org.eclipse.elk.core.util.AlgorithmFactory(OverlapRemovalLayoutProvider.class, ""),
        null,
        null,
        null,
        null
    ));
  }
}
