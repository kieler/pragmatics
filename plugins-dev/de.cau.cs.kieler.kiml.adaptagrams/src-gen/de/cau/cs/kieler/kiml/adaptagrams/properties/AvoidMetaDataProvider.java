package de.cau.cs.kieler.kiml.adaptagrams.properties;

import org.eclipse.elk.core.data.ILayoutMetaDataProvider;

@SuppressWarnings("all")
public class AvoidMetaDataProvider implements ILayoutMetaDataProvider {
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    new de.cau.cs.kieler.kiml.adaptagrams.properties.AvoidOptions().apply(registry);
  }
}
