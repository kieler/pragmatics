package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
import de.cau.cs.kieler.kiml.ogdf.options.AttractionFormula;
import de.cau.cs.kieler.kiml.ogdf.options.OgdfMetaDataProvider;
import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.GraphFeature;
import org.eclipse.elk.core.options.SizeConstraint;
import org.eclipse.elk.core.options.SizeOptions;
import org.eclipse.elk.core.util.AlgorithmFactory;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class GemOptions implements ILayoutMetaDataProvider {
  /**
   * Property constant to access Border Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_BORDER = CoreOptions.SPACING_BORDER;
  
  /**
   * Property constant to access Label Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> LABEL_EDGE_DISTANCE = OgdfMetaDataProvider.LABEL_EDGE_DISTANCE;
  
  /**
   * Property constant to access Label Distance from within the layout algorithm code.
   */
  public final static IProperty<Float> LABEL_MARGIN_DISTANCE = OgdfMetaDataProvider.LABEL_MARGIN_DISTANCE;
  
  /**
   * Default value for {@link #ASPECT_RATIO} with algorithm "GEM".
   */
  private final static float ASPECT_RATIO_DEFAULT = 1.3f;
  
  /**
   * Property constant to access Aspect Ratio from within the layout algorithm code.
   */
  public final static IProperty<Float> ASPECT_RATIO = new Property<Float>(
                                CoreOptions.ASPECT_RATIO,
                                ASPECT_RATIO_DEFAULT);
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "GEM".
   */
  private final static float SPACING_NODE_DEFAULT = 30;
  
  /**
   * Property constant to access Node Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_NODE = new Property<Float>(
                                CoreOptions.SPACING_NODE,
                                SPACING_NODE_DEFAULT);
  
  /**
   * Property constant to access Number Of Rounds from within the layout algorithm code.
   */
  public final static IProperty<Integer> NUMBER_OF_ROUNDS = OgdfMetaDataProvider.NUMBER_OF_ROUNDS;
  
  /**
   * Property constant to access Minimal Temperature from within the layout algorithm code.
   */
  public final static IProperty<Float> MINIMAL_TEMPERATURE = OgdfMetaDataProvider.MINIMAL_TEMPERATURE;
  
  /**
   * Property constant to access Initial Temperature from within the layout algorithm code.
   */
  public final static IProperty<Float> INITIAL_TEMPERATURE = OgdfMetaDataProvider.INITIAL_TEMPERATURE;
  
  /**
   * Property constant to access Gravitational Constant from within the layout algorithm code.
   */
  public final static IProperty<Float> GRAVITATIONAL_CONSTANT = OgdfMetaDataProvider.GRAVITATIONAL_CONSTANT;
  
  /**
   * Property constant to access Maximal Disturbance from within the layout algorithm code.
   */
  public final static IProperty<Float> MAXIMAL_DISTURBANCE = OgdfMetaDataProvider.MAXIMAL_DISTURBANCE;
  
  /**
   * Property constant to access Rotation Angle from within the layout algorithm code.
   */
  public final static IProperty<Float> ROTATION_ANGLE = OgdfMetaDataProvider.ROTATION_ANGLE;
  
  /**
   * Property constant to access Oscillation Angle from within the layout algorithm code.
   */
  public final static IProperty<Float> OSCILLATION_ANGLE = OgdfMetaDataProvider.OSCILLATION_ANGLE;
  
  /**
   * Property constant to access Rotation Sensitivity from within the layout algorithm code.
   */
  public final static IProperty<Float> ROTATION_SENSITIVITY = OgdfMetaDataProvider.ROTATION_SENSITIVITY;
  
  /**
   * Property constant to access Oscillation Sensitivity from within the layout algorithm code.
   */
  public final static IProperty<Float> OSCILLATION_SENSITIVITY = OgdfMetaDataProvider.OSCILLATION_SENSITIVITY;
  
  /**
   * Property constant to access Attraction Formula from within the layout algorithm code.
   */
  public final static IProperty<AttractionFormula> ATTRACTION_FORMULA = OgdfMetaDataProvider.ATTRACTION_FORMULA;
  
  /**
   * Property constant to access Component Spacing Factor from within the layout algorithm code.
   */
  public final static IProperty<Float> MIN_DIST_C_C = OgdfMetaDataProvider.MIN_DIST_C_C;
  
  /**
   * Property constant to access Node Size Constraints from within the layout algorithm code.
   */
  public final static IProperty<EnumSet<SizeConstraint>> NODE_SIZE_CONSTRAINTS = CoreOptions.NODE_SIZE_CONSTRAINTS;
  
  /**
   * Property constant to access Node Size Options from within the layout algorithm code.
   */
  public final static IProperty<EnumSet<SizeOptions>> NODE_SIZE_OPTIONS = CoreOptions.NODE_SIZE_OPTIONS;
  
  /**
   * Property constant to access Place Labels from within the layout algorithm code.
   */
  public final static IProperty<Boolean> PLACE_LABELS = OgdfMetaDataProvider.PLACE_LABELS;
  
  /**
   * Property constant to access Adapt Port Positions from within the layout algorithm code.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = OgdfMetaDataProvider.ADAPT_PORT_POSITIONS;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "GEM",
        "Energy-based &quot;Graph Embedder&quot; algorithm after Frick, Ludwig, and Mehldau 1995. The method is based on the spring-embedder paradigm and contains several heuristics to improve the convergence, including local temperatures, gravitational forces, and the detection of rotations and oscillations.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "GEM"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/gem.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "org.eclipse.elk.spacing.border",
        SPACING_BORDER.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        LABEL_EDGE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        LABEL_MARGIN_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "org.eclipse.elk.aspectRatio",
        ASPECT_RATIO_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "org.eclipse.elk.spacing.node",
        SPACING_NODE_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.numberOfRounds",
        NUMBER_OF_ROUNDS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.minimalTemperature",
        MINIMAL_TEMPERATURE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.initialTemperature",
        INITIAL_TEMPERATURE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.gravitationalConstant",
        GRAVITATIONAL_CONSTANT.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.maximalDisturbance",
        MAXIMAL_DISTURBANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.rotationAngle",
        ROTATION_ANGLE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.oscillationAngle",
        OSCILLATION_ANGLE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.rotationSensitivity",
        ROTATION_SENSITIVITY.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.oscillationSensitivity",
        OSCILLATION_SENSITIVITY.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.attractionFormula",
        ATTRACTION_FORMULA.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.minDistCC",
        MIN_DIST_C_C.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "org.eclipse.elk.nodeSize.constraints",
        NODE_SIZE_CONSTRAINTS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "org.eclipse.elk.nodeSize.options",
        NODE_SIZE_OPTIONS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        PLACE_LABELS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.gem",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
  }
}
