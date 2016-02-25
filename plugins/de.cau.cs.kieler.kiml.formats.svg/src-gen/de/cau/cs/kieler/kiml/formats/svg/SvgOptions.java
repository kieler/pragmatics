/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.svg;

import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

@SuppressWarnings("all")
public class SvgOptions implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #SCALE}.
   */
  private final static float SCALE_DEFAULT = 1.0f;
  
  /**
   * Property for output scaling, to be put in parent node's shape layout.
   */
  public final static IProperty<Float> SCALE = new Property<Float>(
            "de.cau.cs.kieler.kiml.formats.svg.scale",
            SCALE_DEFAULT);
  
  /**
   * Default value for {@link #USE_CSS}.
   */
  private final static boolean USE_CSS_DEFAULT = false;
  
  /**
   * Whether to use CSS style properties in SVG output, as opposed to plain attributes.
   */
  public final static IProperty<Boolean> USE_CSS = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.formats.svg.useCss",
            USE_CSS_DEFAULT);
  
  public void apply(final ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.formats.svg.scale",
        "",
        "SVG Rendering Scale",
        "Property for output scaling, to be put in parent node\'s shape layout.",
        SCALE_DEFAULT,
        LayoutOptionData.Type.FLOAT,
        float.class,
        EnumSet.of(LayoutOptionData.Target.PARENTS),
        LayoutOptionData.Visibility.VISIBLE
        , "de.cau.cs.kieler.svg.scale"
    ));
    registry.register(new LayoutOptionData(
        "de.cau.cs.kieler.kiml.formats.svg.useCss",
        "",
        "Use CSS in SVG output",
        "Whether to use CSS style properties in SVG output, as opposed to plain attributes.",
        USE_CSS_DEFAULT,
        LayoutOptionData.Type.BOOLEAN,
        boolean.class,
        null,
        LayoutOptionData.Visibility.VISIBLE
        , "de.cau.cs.kieler.svg.css"
    ));
  }
}
