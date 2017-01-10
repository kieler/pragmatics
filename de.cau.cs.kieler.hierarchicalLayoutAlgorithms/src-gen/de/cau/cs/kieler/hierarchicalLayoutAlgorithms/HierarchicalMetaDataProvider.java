package de.cau.cs.kieler.hierarchicalLayoutAlgorithms;

import org.eclipse.elk.core.data.ILayoutMetaDataProvider;

@SuppressWarnings("all")
public class HierarchicalMetaDataProvider implements ILayoutMetaDataProvider {
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    new de.cau.cs.kieler.hierarchicalLayoutAlgorithms.RadialOptions().apply(registry);
    new de.cau.cs.kieler.hierarchicalLayoutAlgorithms.GridOptions().apply(registry);
  }
}
