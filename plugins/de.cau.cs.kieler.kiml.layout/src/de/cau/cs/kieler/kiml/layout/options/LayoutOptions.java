/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.layout.options;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KObjectOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption;

/**
 * Definition of layout options and utility methods to get and set these
 * options.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public final class LayoutOptions {

    /**
     * Hide constructor to avoid instantiation.
     */
    private LayoutOptions() {
    }

    /**
     * Resolves the class of an enumeration given by an identifier.
     * 
     * FIXME this implementation is restricted to internally defined enums,
     * should be extended for all enums
     * 
     * @param optionId identifier of a layout option that is represented by an
     *            enumeration
     * @return the corresponding enumeration class
     */
    public static Class<? extends Enum<?>> getEnumClass(final String optionId) {
        if (LAYOUT_DIRECTION.equals(optionId)) {
            return LayoutDirection.class;
        } else if (PORT_SIDE.equals(optionId)) {
            return PortSide.class;
        } else if (PORT_CONSTRAINTS.equals(optionId)) {
            return PortConstraints.class;
        } else if (EDGE_LABEL_PLACEMENT.equals(optionId)) {
            return EdgeLabelPlacement.class;
        } else if (SHAPE.equals(optionId)) {
            return Shape.class;
        } else {
            return null;
        }
    }

    /** layout option key: layout hint. */
    public static final String LAYOUT_HINT = "de.cau.cs.kieler.layout.options.layoutHint";

    /**
     * Returns the layout hint for a given layout data instance.
     * 
     * @param layoutData layout data for a parent node
     * @return the layout hint for the given layout data, or {@code null} if
     *         there is no such option
     */
    public static String getLayoutHint(final KLayoutData layoutData) {
        KStringOption hintOption = (KStringOption) layoutData.getOption(LAYOUT_HINT);
        if (hintOption == null) {
            return null;
        } else {
            return hintOption.getValue();
        }
    }

    /**
     * Sets the layout hint for the given layout data instance.
     * 
     * @param layoutData layout data for a parent node
     * @param layoutHint layout hint to set
     */
    public static void setLayoutHint(final KLayoutData layoutData, final String layoutHint) {
        KStringOption hintOption = (KStringOption) layoutData.getOption(LAYOUT_HINT);
        if (hintOption == null) {
            hintOption = KLayoutDataFactory.eINSTANCE.createKStringOption();
            hintOption.setKey(LAYOUT_HINT);
            layoutData.getOptions().add(hintOption);
        }
        hintOption.setValue(layoutHint);
    }

    /** layout option key: diagram type. */
    public static final String DIAGRAM_TYPE = "de.cau.cs.kieler.layout.options.diagramType";

    /**
     * Returns the diagram type for a given layout data instance.
     * 
     * @param layoutData layout data for a parent node
     * @return the diagram type for the given layout data, or {@code null} if
     *         there is no such option
     */
    public static String getDiagramType(final KLayoutData layoutData) {
        KStringOption typeOption = (KStringOption) layoutData.getOption(DIAGRAM_TYPE);
        if (typeOption == null) {
            return null;
        } else {
            return typeOption.getValue();
        }
    }

    /**
     * Sets the diagram type for the given layout data instance.
     * 
     * @param layoutData layout data for a parent node
     * @param diagramType diagram type to set
     */
    public static void setDiagramType(final KLayoutData layoutData, final String diagramType) {
        KStringOption typeOption = (KStringOption) layoutData.getOption(DIAGRAM_TYPE);
        if (typeOption == null) {
            typeOption = KLayoutDataFactory.eINSTANCE.createKStringOption();
            typeOption.setKey(DIAGRAM_TYPE);
            layoutData.getOptions().add(typeOption);
        }
        typeOption.setValue(diagramType);
    }

    /** layout option key: direction of layout. */
    public static final String LAYOUT_DIRECTION = "de.cau.cs.kieler.layout.options.layoutDirection";

    /**
     * Returns the layout direction for a given layout data instance.
     * 
     * @param layoutData layout data for a parent node
     * @return the layout direction for the given layout data, or {@code
     *         UNDEFINED} if there is no such option
     */
    public static LayoutDirection getLayoutDirection(final KLayoutData layoutData) {
        KIntOption directionOption = (KIntOption) layoutData.getOption(LAYOUT_DIRECTION);
        if (directionOption == null) {
            return LayoutDirection.UNDEFINED;
        } else {
            return LayoutDirection.valueOf(directionOption.getValue());
        }
    }

    /**
     * Sets the layout direction for the given layout data instance.
     * 
     * @param layoutData layout data for a parent node
     * @param layoutDirection layout direction to set
     */
    public static void setLayoutDirection(final KLayoutData layoutData,
            final LayoutDirection layoutDirection) {
        KIntOption directionOption = (KIntOption) layoutData.getOption(LAYOUT_DIRECTION);
        if (directionOption == null) {
            directionOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            directionOption.setKey(LAYOUT_DIRECTION);
            layoutData.getOptions().add(directionOption);
        }
        directionOption.setValue(layoutDirection.ordinal());
    }

    /** layout option key: distance of node contents to the boundary. */
    public static final String INSETS = "de.cau.cs.kieler.layout.options.insets";

    /**
     * Returns the insets for a given layout data instance. If no insets option
     * is set, default values are created.
     * 
     * @param layoutData layout data for a parent node
     * @return the insets for the given layout data
     */
    public static KInsets getInsets(final KLayoutData layoutData) {
        KObjectOption insetsOption = (KObjectOption) layoutData.getOption(INSETS);
        if (insetsOption == null || insetsOption.getValue() == null) {
            return KLayoutDataFactory.eINSTANCE.createKInsets();
        } else {
            return (KInsets) insetsOption.getValue();
        }
    }

    /** layout option key: side of a port on its node's boundary. */
    public static final String PORT_SIDE = "de.cau.cs.kieler.layout.options.portSide";

    /**
     * Returns the port side for a given layout data instance.
     * 
     * @param layoutData layout data for a port
     * @return the port side for the given layout data
     */
    public static PortSide getPortSide(final KLayoutData layoutData) {
        KIntOption sideOption = (KIntOption) layoutData.getOption(PORT_SIDE);
        if (sideOption == null) {
            return PortSide.UNDEFINED;
        } else {
            return PortSide.valueOf(sideOption.getValue());
        }
    }

    /**
     * Sets the port side for the given layout data instance.
     * 
     * @param layoutData layout data for a port
     * @param portSide port side to set
     */
    public static void setPortSide(final KLayoutData layoutData, final PortSide portSide) {
        KIntOption sideOption = (KIntOption) layoutData.getOption(PORT_SIDE);
        if (sideOption == null) {
            sideOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            sideOption.setKey(PORT_SIDE);
            layoutData.getOptions().add(sideOption);
        }
        sideOption.setValue(portSide.ordinal());
    }

    /** layout option key: constraints for port positions. */
    public static final String PORT_CONSTRAINTS = "de.cau.cs.kieler.layout.options.portConstraints";

    /**
     * Returns the port constraints for a given layout data instance.
     * 
     * @param layoutData layout data for a node
     * @return the port constraints for the given layout data
     */
    public static PortConstraints getPortConstraints(final KLayoutData layoutData) {
        KIntOption constraintsOption = (KIntOption) layoutData.getOption(PORT_CONSTRAINTS);
        if (constraintsOption == null) {
            return PortConstraints.UNDEFINED;
        } else {
            return PortConstraints.valueOf(constraintsOption.getValue());
        }
    }

    /**
     * Sets the port constraints for the given layout data instance.
     * 
     * @param layoutData layout data for a node
     * @param portConstraints port constraints to set
     */
    public static void setPortConstraints(final KLayoutData layoutData,
            final PortConstraints portConstraints) {
        KIntOption constraintsOption = (KIntOption) layoutData.getOption(PORT_CONSTRAINTS);
        if (constraintsOption == null) {
            constraintsOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            constraintsOption.setKey(PORT_CONSTRAINTS);
            layoutData.getOptions().add(constraintsOption);
        }
        constraintsOption.setValue(portConstraints.ordinal());
    }

    /** layout option key: rank of a port. */
    public static final String PORT_RANK = "de.cau.cs.kieler.layout.options.portRank";

    /**
     * Returns the port rank for a given layout data instance.
     * 
     * @param layoutData layout data for a port
     * @return the port rank for the given layout data, or -1 if there is no
     *         such option
     */
    public static int getPortRank(final KLayoutData layoutData) {
        KIntOption rankOption = (KIntOption) layoutData.getOption(PORT_RANK);
        if (rankOption == null) {
            return -1;
        } else {
            return rankOption.getValue();
        }
    }

    /**
     * Sets the port rank for the given layout data instance.
     * 
     * @param layoutData layout data for a port
     * @param rank port rank to set
     */
    public static void setPortRank(final KLayoutData layoutData, final int rank) {
        KIntOption rankOption = (KIntOption) layoutData.getOption(PORT_RANK);
        if (rankOption == null) {
            rankOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            rankOption.setKey(PORT_RANK);
            layoutData.getOptions().add(rankOption);
        }
        rankOption.setValue(rank);
    }

    /** layout option key: placement positions for edge labels. */
    public static final String EDGE_LABEL_PLACEMENT
            = "de.cau.cs.kieler.layout.options.edgeLabelPlacement";

    /**
     * Returns the edge label placement for a given layout data instance.
     * 
     * @param layoutData layout data for an edge label
     * @return the edge label placement for the given layout data
     */
    public static EdgeLabelPlacement getEdgeLabelPlacement(final KLayoutData layoutData) {
        KIntOption placementOption = (KIntOption) layoutData.getOption(EDGE_LABEL_PLACEMENT);
        if (placementOption == null) {
            return EdgeLabelPlacement.UNDEFINED;
        } else {
            return EdgeLabelPlacement.valueOf(placementOption.getValue());
        }
    }

    /**
     * Sets the edge label placement for the given layout data instance.
     * 
     * @param layoutData layout data for an edge label
     * @param placement edge label placement to set
     */
    public static void setEdgeLabelPlacement(final KLayoutData layoutData,
            final EdgeLabelPlacement placement) {
        KIntOption placementOption = (KIntOption) layoutData.getOption(EDGE_LABEL_PLACEMENT);
        if (placementOption == null) {
            placementOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            placementOption.setKey(EDGE_LABEL_PLACEMENT);
            layoutData.getOptions().add(placementOption);
        }
        placementOption.setValue(placement.ordinal());
    }

    /** layout option key: size constraint for nodes. */
    public static final String FIXED_SIZE = "de.cau.cs.kieler.layout.options.fixedSize";

    /**
     * Returns whether the fixed size option is active for the given layout data
     * instance.
     * 
     * @param layoutData layout data for a node
     * @return true if the fixed size option is active
     */
    public static boolean isFixedSize(final KLayoutData layoutData) {
        KBooleanOption sizeOption = (KBooleanOption) layoutData.getOption(FIXED_SIZE);
        if (sizeOption == null) {
            return false;
        } else {
            return sizeOption.isValue();
        }
    }

    /**
     * Activates or deactivates the fixed size option for the given layout data
     * instance.
     * 
     * @param layoutData layout data for a node
     * @param fixedSize true if the node's size shall be fixed
     */
    public static void setFixedSize(final KLayoutData layoutData, final boolean fixedSize) {
        KBooleanOption sizeOption = (KBooleanOption) layoutData.getOption(FIXED_SIZE);
        if (sizeOption == null) {
            sizeOption = KLayoutDataFactory.eINSTANCE.createKBooleanOption();
            sizeOption.setKey(FIXED_SIZE);
            layoutData.getOptions().add(sizeOption);
        }
        sizeOption.setValue(fixedSize);
    }

    /** layout option key: minimal distance between elements. */
    public static final String MIN_SPACING = "de.cau.cs.kieler.layout.options.minSpacing";

    /**
     * Returns the minimal spacing for a given layout data instance.
     * 
     * @param layoutData layout data for a parent node
     * @return the minimal spacing for the given layout data, or {@code NaN} if
     *         there is no such option
     */
    public static float getMinSpacing(final KLayoutData layoutData) {
        KFloatOption spacingOption = (KFloatOption) layoutData.getOption(MIN_SPACING);
        if (spacingOption == null) {
            return Float.NaN;
        } else {
            return spacingOption.getValue();
        }
    }

    /**
     * Sets the minimal spacing for the given layout data instance.
     * 
     * @param layoutData layout data for a parent node
     * @param spacing minimal spacing to set
     */
    public static void setMinSpacing(final KLayoutData layoutData, final float spacing) {
        KFloatOption spacingOption = (KFloatOption) layoutData.getOption(MIN_SPACING);
        if (spacingOption == null) {
            spacingOption = KLayoutDataFactory.eINSTANCE.createKFloatOption();
            spacingOption.setKey(MIN_SPACING);
            layoutData.getOptions().add(spacingOption);
        }
        spacingOption.setValue(spacing);
    }
    
    /** layout option key: distance to border of the drawing. */
    public static final String BORDER_SPACING = "de.cau.cs.kieler.layout.options.borderSpacing";

    /**
     * Returns the border spacing for a given layout data instance.
     * 
     * @param layoutData layout data for a parent node
     * @return the border spacing for the given layout data, or {@code NaN} if
     *         there is no such option
     */
    public static float getBorderSpacing(final KLayoutData layoutData) {
        KFloatOption spacingOption = (KFloatOption) layoutData.getOption(BORDER_SPACING);
        if (spacingOption == null) {
            return Float.NaN;
        } else {
            return spacingOption.getValue();
        }
    }

    /**
     * Sets the border spacing for the given layout data instance.
     * 
     * @param layoutData layout data for a parent node
     * @param spacing border spacing to set
     */
    public static void setBorderSpacing(final KLayoutData layoutData, final float spacing) {
        KFloatOption spacingOption = (KFloatOption) layoutData.getOption(BORDER_SPACING);
        if (spacingOption == null) {
            spacingOption = KLayoutDataFactory.eINSTANCE.createKFloatOption();
            spacingOption.setKey(BORDER_SPACING);
            layoutData.getOptions().add(spacingOption);
        }
        spacingOption.setValue(spacing);
    }

    /** layout option key: priority of elements. */
    public static final String PRIORITY = "de.cau.cs.kieler.layout.options.priority";

    /**
     * Sets the priority of the given layout data.
     * 
     * @param layoutData layout data to process
     * @param priority priority value for the corresponding graph element
     */
    public static void setPriority(final KLayoutData layoutData, final int priority) {
        KIntOption priorityOption = (KIntOption) layoutData.getOption(PRIORITY);
        if (priorityOption == null) {
            priorityOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            priorityOption.setKey(PRIORITY);
            layoutData.getOptions().add(priorityOption);
        }
        priorityOption.setValue(priority);
    }

    /**
     * Retrieves the assigned priority value for a given layout data.
     * 
     * @param layoutData layout data to process
     * @return the assigned priority, or 0 if no priority is assigned
     */
    public static int getPriority(final KLayoutData layoutData) {
        KIntOption priorityOption = (KIntOption) layoutData.getOption(PRIORITY);
        if (priorityOption == null) {
            return 0;
        } else {
            return priorityOption.getValue();
        }
    }

    /** layout option key: font name. */
    public static final String FONT_NAME = "de.cau.cs.kieler.layout.options.fontName";

    /**
     * Returns the font name for a given layout data instance.
     * 
     * @param layoutData layout data for a label
     * @return the font name for the given layout data, or {@code null} if there
     *         is no such option
     */
    public static String getFontName(final KLayoutData layoutData) {
        KStringOption fontOption = (KStringOption) layoutData.getOption(FONT_NAME);
        if (fontOption == null) {
            return null;
        } else {
            return fontOption.getValue();
        }
    }

    /**
     * Sets the font name for the given layout data instance.
     * 
     * @param layoutData layout data for a label
     * @param fontName font name to set
     */
    public static void setFontName(final KLayoutData layoutData, final String fontName) {
        KStringOption fontOption = (KStringOption) layoutData.getOption(FONT_NAME);
        if (fontOption == null) {
            fontOption = KLayoutDataFactory.eINSTANCE.createKStringOption();
            fontOption.setKey(FONT_NAME);
            layoutData.getOptions().add(fontOption);
        }
        fontOption.setValue(fontName);
    }

    /** layout option key: font size. */
    public static final String FONT_SIZE = "de.cau.cs.kieler.layout.options.fontSize";

    /**
     * Sets the font size of the given layout data.
     * 
     * @param layoutData layout data to process
     * @param size font size for the corresponding label
     */
    public static void setFontSize(final KLayoutData layoutData, final int size) {
        KIntOption sizeOption = (KIntOption) layoutData.getOption(FONT_SIZE);
        if (sizeOption == null) {
            sizeOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            sizeOption.setKey(FONT_SIZE);
            layoutData.getOptions().add(sizeOption);
        }
        sizeOption.setValue(size);
    }

    /**
     * Retrieves the font size for a given layout data.
     * 
     * @param layoutData layout data to process
     * @return the font size, or 0 if no font size is assigned
     */
    public static int getFontSize(final KLayoutData layoutData) {
        KIntOption sizeOption = (KIntOption) layoutData.getOption(FONT_SIZE);
        if (sizeOption == null) {
            return 0;
        } else {
            return sizeOption.getValue();
        }
    }

    /** layout option key: shape of a node. */
    public static final String SHAPE = "de.cau.cs.kieler.layout.options.shape";

    /**
     * Returns the shape for a given layout data instance.
     * 
     * @param layoutData layout data for a node
     * @return the shape for the given layout data
     */
    public static Shape getShape(final KLayoutData layoutData) {
        KIntOption shapeOption = (KIntOption) layoutData.getOption(SHAPE);
        if (shapeOption == null) {
            return Shape.UNDEFINED;
        } else {
            return Shape.valueOf(shapeOption.getValue());
        }
    }

    /**
     * Sets the shape for the given layout data instance.
     * 
     * @param layoutData layout data for a node
     * @param shape shape to set
     */
    public static void setShape(final KLayoutData layoutData, final Shape shape) {
        KIntOption shapeOption = (KIntOption) layoutData.getOption(SHAPE);
        if (shapeOption == null) {
            shapeOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            shapeOption.setKey(SHAPE);
            layoutData.getOptions().add(shapeOption);
        }
        shapeOption.setValue(shape.ordinal());
    }

    /** layout option key: expand nodes to fill their parent. */
    public static final String EXPAND_NODES = "de.cau.cs.kieler.layout.options.expandNodes";

    /**
     * Returns whether the expand nodes option is active for the given layout
     * data instance.
     * 
     * @param layoutData layout data for a parent node
     * @return true if the expand nodes option is active
     */
    public static boolean isExpandNodes(final KLayoutData layoutData) {
        KBooleanOption expandOption = (KBooleanOption) layoutData.getOption(EXPAND_NODES);
        if (expandOption == null) {
            return false;
        } else {
            return expandOption.isValue();
        }
    }

    /**
     * Activates or deactivates the expand nodes option for the given layout
     * data instance.
     * 
     * @param layoutData layout data for a parent node
     * @param expandNodes true if the contained nodes shall be expanded
     */
    public static void setExpandNodes(final KLayoutData layoutData, final boolean expandNodes) {
        KBooleanOption expandOption = (KBooleanOption) layoutData.getOption(EXPAND_NODES);
        if (expandOption == null) {
            expandOption = KLayoutDataFactory.eINSTANCE.createKBooleanOption();
            expandOption.setKey(EXPAND_NODES);
            layoutData.getOptions().add(expandOption);
        }
        expandOption.setValue(expandNodes);
    }

    /** layout option key: minimal width. */
    public static final String MIN_WIDTH = "de.cau.cs.kieler.layout.options.minWidth";

    /**
     * Sets the minimal width of the given layout data.
     * 
     * @param layoutData layout data to process
     * @param width minimal width for the corresponding node
     */
    public static void setMinWidth(final KLayoutData layoutData, final int width) {
        KIntOption sizeOption = (KIntOption) layoutData.getOption(MIN_WIDTH);
        if (sizeOption == null) {
            sizeOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            sizeOption.setKey(MIN_WIDTH);
            layoutData.getOptions().add(sizeOption);
        }
        sizeOption.setValue(width);
    }

    /**
     * Retrieves the minimal width for a given layout data.
     * 
     * @param layoutData layout data to process
     * @return the minimal width, or 0 if no minimal width is assigned
     */
    public static int getMinWidth(final KLayoutData layoutData) {
        KIntOption sizeOption = (KIntOption) layoutData.getOption(MIN_WIDTH);
        if (sizeOption == null) {
            return 0;
        } else {
            return sizeOption.getValue();
        }
    }

    /** layout option key: minimal height. */
    public static final String MIN_HEIGHT = "de.cau.cs.kieler.layout.options.minHeight";

    /**
     * Sets the minimal height of the given layout data.
     * 
     * @param layoutData layout data to process
     * @param height minimal height for the corresponding node
     */
    public static void setMinHeight(final KLayoutData layoutData, final int height) {
        KIntOption sizeOption = (KIntOption) layoutData.getOption(MIN_HEIGHT);
        if (sizeOption == null) {
            sizeOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            sizeOption.setKey(MIN_HEIGHT);
            layoutData.getOptions().add(sizeOption);
        }
        sizeOption.setValue(height);
    }

    /**
     * Retrieves the minimal height for a given layout data.
     * 
     * @param layoutData layout data to process
     * @return the minimal height, or 0 if no minimal height is assigned
     */
    public static int getMinHeight(final KLayoutData layoutData) {
        KIntOption sizeOption = (KIntOption) layoutData.getOption(MIN_HEIGHT);
        if (sizeOption == null) {
            return 0;
        } else {
            return sizeOption.getValue();
        }
    }
    
    /** layout option key: randomization seed. */
    public static final String RANDOM_SEED = "de.cau.cs.kieler.layout.options.randomSeed";

    /**
     * Sets the randomization seed of the given layout data.
     * 
     * @param layoutData layout data to process
     * @param seed randomization seed for the corresponding node
     */
    public static void setRandomSeed(final KLayoutData layoutData, final int seed) {
        KIntOption seedOption = (KIntOption) layoutData.getOption(RANDOM_SEED);
        if (seedOption == null) {
            seedOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            seedOption.setKey(RANDOM_SEED);
            layoutData.getOptions().add(seedOption);
        }
        seedOption.setValue(seed);
    }

    /**
     * Retrieves the randomization seed for a given layout data.
     * 
     * @param layoutData layout data to process
     * @return the randomization seed, or 1 if no seed is assigned
     */
    public static int getRandomSeed(final KLayoutData layoutData) {
        KIntOption seedOption = (KIntOption) layoutData.getOption(RANDOM_SEED);
        if (seedOption == null) {
            return 1;
        } else {
            return seedOption.getValue();
        }
    }
    
    /** layout option key: optimize layout for user interaction. */
    public static final String INTERACTIVE = "de.cau.cs.kieler.layout.options.interactive";

    /**
     * Returns whether the interactive layout option is active for the given layout
     * data instance.
     * 
     * @param layoutData layout data for a parent node
     * @return true if the interactive layout option is active
     */
    public static boolean isInteractive(final KLayoutData layoutData) {
        KBooleanOption interactiveOption = (KBooleanOption) layoutData.getOption(INTERACTIVE);
        if (interactiveOption == null) {
            return false;
        } else {
            return interactiveOption.isValue();
        }
    }

    /**
     * Activates or deactivates the interactive layout option for the given layout
     * data instance.
     * 
     * @param layoutData layout data for a parent node
     * @param interactive true if interactive layout shall be performed
     */
    public static void setInteractive(final KLayoutData layoutData, final boolean interactive) {
        KBooleanOption interactiveOption = (KBooleanOption) layoutData.getOption(INTERACTIVE);
        if (interactiveOption == null) {
            interactiveOption = KLayoutDataFactory.eINSTANCE.createKBooleanOption();
            interactiveOption.setKey(INTERACTIVE);
            layoutData.getOptions().add(interactiveOption);
        }
        interactiveOption.setValue(interactive);
    }
    
    /** layout option key: spacing of edge labels to edges. */
    public static final String LABEL_SPACING = "de.cau.cs.kieler.layout.options.labelSpacing";

    /**
     * Returns the label spacing for a given layout data instance.
     * 
     * @param layoutData layout data for an edge
     * @return the label spacing for the given layout data, or {@code NaN} if
     *         there is no such option
     */
    public static float getLabelSpacing(final KLayoutData layoutData) {
        KFloatOption spacingOption = (KFloatOption) layoutData.getOption(LABEL_SPACING);
        if (spacingOption == null) {
            return Float.NaN;
        } else {
            return spacingOption.getValue();
        }
    }

    /**
     * Sets the label spacing for the given layout data instance.
     * 
     * @param layoutData layout data for an edge
     * @param spacing label spacing to set
     */
    public static void setLabelSpacing(final KLayoutData layoutData, final float spacing) {
        KFloatOption spacingOption = (KFloatOption) layoutData.getOption(LABEL_SPACING);
        if (spacingOption == null) {
            spacingOption = KLayoutDataFactory.eINSTANCE.createKFloatOption();
            spacingOption.setKey(LABEL_SPACING);
            layoutData.getOptions().add(spacingOption);
        }
        spacingOption.setValue(spacing);
    }
    
}
