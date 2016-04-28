package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
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
public class UpwardPlanarizationOptions implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "Upward-Planarization".
   */
  private final static float SPACING_BORDER_DEFAULT = 15;
  
  /**
   * Property constant to access Border Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_BORDER = new Property<Float>(
                                CoreOptions.SPACING_BORDER,
                                SPACING_BORDER_DEFAULT);
  
  /**
   * Property constant to access Label Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> LABEL_EDGE_DISTANCE = OgdfMetaDataProvider.LABEL_EDGE_DISTANCE;
  
  /**
   * Property constant to access Label Distance from within the layout algorithm code.
   */
  public final static IProperty<Float> LABEL_MARGIN_DISTANCE = OgdfMetaDataProvider.LABEL_MARGIN_DISTANCE;
  
  /**
   * Default value for {@link #MIN_DIST_LEVEL} with algorithm "Upward-Planarization".
   */
  private final static float MIN_DIST_LEVEL_DEFAULT = 16;
  
  /**
   * Property constant to access Level Spacing Factor from within the layout algorithm code.
   */
  public final static IProperty<Float> MIN_DIST_LEVEL = new Property<Float>(
                                OgdfMetaDataProvider.MIN_DIST_LEVEL,
                                MIN_DIST_LEVEL_DEFAULT);
  
  /**
   * Default value for {@link #SPACING_NODE} with algorithm "Upward-Planarization".
   */
  private final static float SPACING_NODE_DEFAULT = 16;
  
  /**
   * Property constant to access Node Spacing from within the layout algorithm code.
   */
  public final static IProperty<Float> SPACING_NODE = new Property<Float>(
                                CoreOptions.SPACING_NODE,
                                SPACING_NODE_DEFAULT);
  
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
   * Property constant to access Runs from within the layout algorithm code.
   */
  public final static IProperty<Integer> RUNS = OgdfMetaDataProvider.RUNS;
  
  /**
   * Default value for {@link #RANDOM_SEED} with algorithm "Upward-Planarization".
   */
  private final static int RANDOM_SEED_DEFAULT = 1;
  
  /**
   * Property constant to access Randomization Seed from within the layout algorithm code.
   */
  public final static IProperty<Integer> RANDOM_SEED = new Property<Integer>(
                                CoreOptions.RANDOM_SEED,
                                RANDOM_SEED_DEFAULT);
  
  /**
   * Property constant to access Adapt Port Positions from within the layout algorithm code.
   */
  public final static IProperty<Boolean> ADAPT_PORT_POSITIONS = OgdfMetaDataProvider.ADAPT_PORT_POSITIONS;
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutAlgorithmData(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "Upward-Planarization",
        "Upward planarization layout by Chimani, Gutwenger, Mutzel and Wong \'10. While usual layer-based methods first determine a layering and then minimize crossings, this approach aims at directly computing an upward planar representation (UPR), where edge crossings are represented by dummy vertices. A layering is then derived from this UPR, which also induces a node order in each layer. This leads to significantly less edge crossings compared to former methods, while the overall edge direction is still preserved. The input graph must be connected.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "UPWARD_PLANARIZATION"),
        "org.eclipse.elk.layered",
        "OGDF",
        "images/upward.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "org.eclipse.elk.spacing.border",
        SPACING_BORDER_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        LABEL_EDGE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        LABEL_MARGIN_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.minDistLevel",
        MIN_DIST_LEVEL_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "org.eclipse.elk.spacing.node",
        SPACING_NODE_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "org.eclipse.elk.nodeSize.constraints",
        NODE_SIZE_CONSTRAINTS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "org.eclipse.elk.nodeSize.options",
        NODE_SIZE_OPTIONS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        PLACE_LABELS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.runs",
        RUNS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "org.eclipse.elk.randomSeed",
        RANDOM_SEED_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.upwardPlanarization",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
  }
}
