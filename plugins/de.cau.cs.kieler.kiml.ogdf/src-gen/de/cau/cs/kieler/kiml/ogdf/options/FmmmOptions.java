package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.ogdf.OgdfLayoutProvider;
import de.cau.cs.kieler.kiml.ogdf.options.OgdfMetaDataProvider;
import de.cau.cs.kieler.kiml.ogdf.options.QualityVsSpeed;
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
public class FmmmOptions implements ILayoutMetaDataProvider {
  /**
   * Property constant to access Quality vs Speed from within the layout algorithm code.
   */
  public final static IProperty<QualityVsSpeed> QUALITY_VS_SPEED = OgdfMetaDataProvider.QUALITY_VS_SPEED;
  
  /**
   * Property constant to access New Initial Placement from within the layout algorithm code.
   */
  public final static IProperty<Boolean> NEW_INITIAL_PLACEMENT = OgdfMetaDataProvider.NEW_INITIAL_PLACEMENT;
  
  /**
   * Default value for {@link #SPACING_BORDER} with algorithm "FMMM".
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
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "FMMM",
        "The fast multipole multilevel layout algorithm, after Hachul and Juenger 2004. This force-directed drawing method is suited also for very large graphs. It is based on a combination of an efficient multilevel scheme and a strategy for approximating the repusive forces in the system by rapidly evaluating potential fields.",
        new AlgorithmFactory(OgdfLayoutProvider.class, "FMMM"),
        "org.eclipse.elk.force",
        "OGDF",
        "images/fmmm.png",
        EnumSet.of(GraphFeature.MULTI_EDGES, GraphFeature.EDGE_LABELS, GraphFeature.DISCONNECTED)
    ));
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.qualityVsSpeed",
        QUALITY_VS_SPEED.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.newInitialPlacement",
        NEW_INITIAL_PLACEMENT.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "org.eclipse.elk.spacing.border",
        SPACING_BORDER_DEFAULT
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.labelEdgeDistance",
        LABEL_EDGE_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.labelMarginDistance",
        LABEL_MARGIN_DISTANCE.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "org.eclipse.elk.nodeSize.constraints",
        NODE_SIZE_CONSTRAINTS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "org.eclipse.elk.nodeSize.options",
        NODE_SIZE_OPTIONS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.placeLabels",
        PLACE_LABELS.getDefault()
    );
    registry.addOptionSupport(
        "de.cau.cs.kieler.kiml.ogdf.fmmm",
        "de.cau.cs.kieler.kiml.ogdf.adaptPortPositions",
        ADAPT_PORT_POSITIONS.getDefault()
    );
  }
}
