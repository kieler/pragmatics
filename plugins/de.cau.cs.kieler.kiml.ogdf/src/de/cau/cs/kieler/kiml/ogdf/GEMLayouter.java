/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import net.ogdf.bin.OgdfServerAPI;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ogdf.options.AttractionFormula;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * The GEM layouter from the OGDF library.
 * 
 * @author mri
 */
public class GEMLayouter extends OgdfLayouter {

    /** default value for page ratio. */
    public static final float DEF_PAGE_RATIO = 1.3f;
    /** default value for spacing. */
    public static final float DEF_SPACING = 30.0f;

    /** 'aspectRatio' property. */
    private static final IProperty<Float> ASPECT_RATIO = new Property<Float>(
            LayoutOptions.ASPECT_RATIO, DEF_PAGE_RATIO);
    /** 'spacing' property. */
    private static final IProperty<Float> SPACING = new Property<Float>(LayoutOptions.SPACING,
            DEF_SPACING);
    /** the 'numberOfRounds' option identifier. */
    private static final String NUMBER_OF_ROUNDS_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.numberOfRounds";
    /** 'numberOfRounds' property. */
    private static final IProperty<Integer> NUMBER_OF_ROUNDS = new Property<Integer>(
            NUMBER_OF_ROUNDS_ID, 20000);
    /** the 'minimalTemperature' option identifier. */
    private static final String MINIMAL_TEMPERATURE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.minimalTemperature";
    /** 'minimalTemperature' property. */
    private static final IProperty<Float> MINIMAL_TEMPERATURE = new Property<Float>(
            MINIMAL_TEMPERATURE_ID, 0.005f);
    /** the 'initialTemperature' option identifier. */
    private static final String INITIAL_TEMPERATURE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.initialTemperature";
    /** 'initialTemperature' property. */
    private static final IProperty<Float> INITIAL_TEMPERATURE = new Property<Float>(
            INITIAL_TEMPERATURE_ID, 10.0f);
    /** the 'gravitationalConstant' option identifier. */
    private static final String GRAVITATIONAL_CONSTANT_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.gravitationalConstant";
    /** 'gravitationalConstant' property. */
    private static final IProperty<Float> GRAVITATIONAL_CONSTANT = new Property<Float>(
            GRAVITATIONAL_CONSTANT_ID, 0.0625f);
    /** the 'maximalDisturbance' option identifier. */
    private static final String MAXIMAL_DISTURBANCE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.maximalDisturbance";
    /** 'maximalDisturbance' property. */
    private static final IProperty<Float> MAXIMAL_DISTURBANCE = new Property<Float>(
            MAXIMAL_DISTURBANCE_ID, 0.0f);
    /** the 'rotationAngle' option identifier. */
    private static final String ROTATION_ANGLE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.rotationAngle";
    /** 'rotationAngle' property. */
    private static final IProperty<Float> ROTATION_ANGLE = new Property<Float>(ROTATION_ANGLE_ID,
            0.33f);
    /** the 'oscillationAngle' option identifier. */
    private static final String OSCILLATION_ANGLE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.oscillationAngle";
    /** 'oscillationAngle' property. */
    private static final IProperty<Float> OSCILLATION_ANGLE = new Property<Float>(
            OSCILLATION_ANGLE_ID, 0.5f);
    /** the 'rotationSensitivity' option identifier. */
    private static final String ROTATION_SENSITIVITY_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.rotationSensitivity";
    /** 'rotationSensitivity' property. */
    private static final IProperty<Float> ROTATION_SENSITIVITY = new Property<Float>(
            ROTATION_SENSITIVITY_ID, 0.01f);
    /** the 'oscillationSensitivity' option identifier. */
    private static final String OSCILLATION_SENSITIVITY_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.oscillationSensitivity";
    /** 'oscillationSensitivity' property. */
    private static final IProperty<Float> OSCILLATION_SENSITIVITY = new Property<Float>(
            OSCILLATION_SENSITIVITY_ID, 0.3f);
    /** the 'attractionFormula' option identifier. */
    private static final String ATTRACTION_FORMULA_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.attractionFormula";
    /** 'attractionFormula' property. */
    private static final IProperty<AttractionFormula> ATTRACTION_FORMULA =
            new Property<AttractionFormula>(ATTRACTION_FORMULA_ID,
                    AttractionFormula.FRUCHTERMAN_REINGOLD);
    /** the 'minDistCC' option identifier. */
    private static final String MIN_DIST_CC_ID = "de.cau.cs.kieler.kiml.ogdf.option.minDistCC";
    /** 'minDistCC' property. */
    private static final IProperty<Float> MIN_DIST_CC = new Property<Float>(MIN_DIST_CC_ID, 20.0f);

    /**
     * Constructs a GEMLayouter.
     */
    public GEMLayouter() {
        super("GEM");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // pageRatio
        float pageRatio = parentLayout.getProperty(ASPECT_RATIO);
        addOption(OgdfServerAPI.OPTION_PAGE_RATIO, pageRatio);
        // desiredLength
        float minSpacing = parentLayout.getProperty(SPACING);
        addOption(OgdfServerAPI.OPTION_DESIRED_LENGTH, minSpacing);
        // numberOfRounds
        int numberOfRounds = parentLayout.getProperty(NUMBER_OF_ROUNDS);
        addOption(OgdfServerAPI.OPTION_NUMBER_OF_ROUNDS, numberOfRounds);
        // minimalTemperature
        float minimalTemperature = parentLayout.getProperty(MINIMAL_TEMPERATURE);
        addOption(OgdfServerAPI.OPTION_MINIMAL_TEMPERATURE, minimalTemperature);
        // initialTemperature
        float initialTemperature = parentLayout.getProperty(INITIAL_TEMPERATURE);
        addOption(OgdfServerAPI.OPTION_INITIAL_TEMPERATURE, initialTemperature);
        // gravitationalConstant
        float gravitationalConstant = parentLayout.getProperty(GRAVITATIONAL_CONSTANT);
        addOption(OgdfServerAPI.OPTION_GRAVITATIONAL_CONSTANT, gravitationalConstant);
        // maximalDisturbance
        float maximalDisturbance = parentLayout.getProperty(MAXIMAL_DISTURBANCE);
        addOption(OgdfServerAPI.OPTION_MAXIMAL_DISTURBANCE, maximalDisturbance);
        // rotationAngle
        float rotationAngle = parentLayout.getProperty(ROTATION_ANGLE);
        addOption(OgdfServerAPI.OPTION_ROTATION_ANGLE, rotationAngle);
        // oscillationAngle
        float oscillationAngle = parentLayout.getProperty(OSCILLATION_ANGLE);
        addOption(OgdfServerAPI.OPTION_OSCILLATION_ANGLE, oscillationAngle);
        // rotationSensitivity
        float rotationSensitivity = parentLayout.getProperty(ROTATION_SENSITIVITY);
        addOption(OgdfServerAPI.OPTION_ROTATION_SENSITIVITY, rotationSensitivity);
        // oscillationSensitivity
        float oscillationSensitivity = parentLayout.getProperty(OSCILLATION_SENSITIVITY);
        addOption(OgdfServerAPI.OPTION_OSCILLATION_SENSITIVITY, oscillationSensitivity);
        // attractionFormula
        AttractionFormula attractionFormula = parentLayout.getProperty(ATTRACTION_FORMULA);
        switch (attractionFormula) {
        case FRUCHTERMAN_REINGOLD:
            addOption(OgdfServerAPI.OPTION_ATTRACTION_FORMULA, 1);
            break;
        case GEM:
            addOption(OgdfServerAPI.OPTION_ATTRACTION_FORMULA, 2);
            break;
        }
        // minDistCC
        float minDistCC = parentLayout.getProperty(MIN_DIST_CC);
        addOption(OgdfServerAPI.OPTION_MIN_DIST_CC, minDistCC);
    }
    
}
