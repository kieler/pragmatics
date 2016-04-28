package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
import de.cau.cs.kieler.kiml.ogdf.options.OgdfMetaDataProvider;
import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.options.GraphFeature;
import org.eclipse.elk.core.util.AlgorithmFactory;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class StressMajorizationOptions implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #ITERATIONS} with algorithm "Stress Majorization".
   */
  private final static int ITERATIONS_DEFAULT = 300;
  
  /**
   * Property constant to access Iterations from within the layout algorithm code.
   */
  public final static IProperty<Integer> ITERATIONS = new Property<Integer>(
                                OgdfMetaDataProvider.ITERATIONS,
                                ITERATIONS_DEFAULT);
  
  /**
   * Property constant to access Stop Tolerance from within the layout algorithm code.
   */
  public final static IProperty<Float> STOP_TOLERANCE = OgdfMetaDataProvider.STOP_TOLERANCE;
  
  /**
   * Property constant to access Upward from within the layout algorithm code.
   */
  public final static IProperty<Boolean> UPWARD = OgdfMetaDataProvider.UPWARD;
  
  /**
   * Property constant to access Radial from within the layout algorithm code.
   */
  public final static IProperty<Boolean> RADIAL = OgdfMetaDataProvider.RADIAL;
  
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
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "Stress Majorization",
        "Stress majorization layout that allows radial constraints based on shortest path distances. The implementation is based on Brandes and Pich 2009. The input graph must be connected.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "STRESS_MAJORIZATION"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/stress_majorization.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.iterations",
        ITERATIONS_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.stopTolerance",
        STOP_TOLERANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.upward",
        UPWARD.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.radial",
        RADIAL.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        PLACE_LABELS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        LABEL_EDGE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        LABEL_MARGIN_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.stressMajorization",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
  }
}
