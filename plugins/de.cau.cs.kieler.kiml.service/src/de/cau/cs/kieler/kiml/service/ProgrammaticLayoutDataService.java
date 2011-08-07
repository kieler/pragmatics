/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.service;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.EdgeType;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.Shape;

/**
 * A LAyout data service that knows about the options defined vie extension and programmatic options.
 *
 * @kieler.rating 2011-05-04 red
 *
 * @author swe
 */
public abstract class ProgrammaticLayoutDataService extends ExtensionLayoutDataService {

    /**
     * Register the programmatic options to the layout data services option data.
     */
    protected void registerProgrammaticOptions() {
        addProgramaticOption(LayoutOptions.COMMENT_BOX, Boolean.class);
        addProgramaticOption(LayoutOptions.DIAGRAM_TYPE, String.class);
        addProgramaticOption(LayoutOptions.EDGE_LABEL_PLACEMENT, EdgeLabelPlacement.class);
        addProgramaticOption(LayoutOptions.EDGE_TYPE, EdgeType.class);
        addProgramaticOption(LayoutOptions.FONT_NAME, String.class);
        addProgramaticOption(LayoutOptions.FONT_SIZE, Integer.class);
        addProgramaticOption(LayoutOptions.HYPERNODE, Boolean.class);
        addProgramaticOption(LayoutOptions.MIN_HEIGHT, Float.class);
        addProgramaticOption(LayoutOptions.MIN_WIDTH, Float.class);
        addProgramaticOption(LayoutOptions.NO_LAYOUT, Boolean.class);
        addProgramaticOption(LayoutOptions.OFFSET, Float.class);
        addProgramaticOption(LayoutOptions.PORT_SIDE, PortSide.class);
        //addProgramaticOption(LayoutOptions.PORT_RANK, Integer.class);
        addProgramaticOption(LayoutOptions.SHAPE, Shape.class);
    }

    /**
     * Creates an appropriate {@link LayoutOptionData} instance from a programmatic option
     * and registers it to the layout data services option data.
     * 
     * @param property
     *            the property to build a {@code LayoutOptionData} instance from
     * @param optionClass
     *            the class of the property
     */
    private void addProgramaticOption(final IProperty<?> property, final Class<?> optionClass) {
        LayoutOptionData<Object> optionData = new LayoutOptionData<Object>();
        String id = property.getId();
        String type = null;
        if (optionClass.equals(Boolean.class)) {
            type = LayoutOptionData.BOOLEAN_LITERAL;
        } else if (optionClass.equals(Integer.class)) {
            type = LayoutOptionData.INT_LITERAL;
        } else if (optionClass.equals(String.class)) {
            type = LayoutOptionData.STRING_LITERAL;
        } else if (optionClass.equals(Float.class)) {
            type = LayoutOptionData.FLOAT_LITERAL;
        } else if (Enum.class.isAssignableFrom(optionClass)) {
            type = LayoutOptionData.ENUM_LITERAL;
        }
        if (type == null) {            
            return;
        }
        optionData.setId(id);
        optionData.setType(type);
        if (type == LayoutOptionData.ENUM_LITERAL || type == LayoutOptionData.OBJECT_LITERAL) {
            optionData.setOptionClass(optionClass);
        }
        optionData.setDefault(property.getDefault());
        getRegistry().addLayoutOption(optionData);
    }

}
