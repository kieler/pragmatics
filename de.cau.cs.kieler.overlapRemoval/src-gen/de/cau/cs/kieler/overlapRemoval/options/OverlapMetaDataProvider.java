package de.cau.cs.kieler.overlapRemoval.options;

import org.eclipse.elk.core.data.ILayoutMetaDataProvider;

/**
 * Declarations for the ELK Layered layout algorithm.
 */
@SuppressWarnings("all")
public class OverlapMetaDataProvider implements ILayoutMetaDataProvider {
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    new de.cau.cs.kieler.overlapRemoval.options.OverlapRemovalOptions().apply(registry);
  }
}
