package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
import de.cau.cs.kieler.kiml.ogdf.options.OgdfMetaDataProvider;
import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.GraphFeature;
import org.eclipse.elk.core.util.AlgorithmFactory;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class KamadaKawaiOptions implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Kamada-Kawai".
   */
  private final static float SPACING_NODE_DEFAULT = 30;
  
  /**
   * Property constant to access Node Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_NODE = new Property<Float>(
                                CoreOptions.SPACING_NODE,
                                SPACING_NODE_DEFAULT);
  
  /**
   * Property constant to access Stop Tolerance from within the layout algorithm code.
   */
  public final static IProperty<Float> STOP_TOLERANCE = OgdfMetaDataProvider.STOP_TOLERANCE;
  
  /**
   * Property constant to access Place Labels from within the layout algorithm code.
   */
  public final static IProperty<Boolean> PLACE_LABELS = OgdfMetaDataProvider.PLACE_LABELS;
  
  /**
   * Property constant to access Label Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> LABEL_EDGE_DISTANCE = OgdfMetaDataProvider.LABEL_EDGE_DISTANCE;
  
  /**
   * Property constant to access Label Distance from within the layout algorithm code.
   */
  public final static IProperty<Float> LABEL_MARGIN_DISTANCE = OgdfMetaDataProvider.LABEL_MARGIN_DISTANCE;
  
  /**
   * Property constant to access Adapt Port Positions from within the layout algorithm code.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = OgdfMetaDataProvider.ADAPT_PORT_POSITIONS;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "Kamada-Kawai",
        "The spring-embedder layout algorithm after Kamada and Kawai 1989. The input graph must be connected.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "KAMADA_KAWAI"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/kamada_kawai.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "org.eclipse.elk.spacing.node",
        SPACING_NODE_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "de.cau.cs.kieler.kiml.ogdf.stopTolerance",
        STOP_TOLERANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        PLACE_LABELS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        LABEL_EDGE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        LABEL_MARGIN_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.kamadaKawai",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
  }
}
