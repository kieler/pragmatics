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

import net.ogdf.bin.OgdfServer;

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
    /** 'numberOfRounds' property. */
    private static final IProperty<Integer> NUMBER_OF_ROUNDS = new Property<Integer>(
            "de.cau.cs.kieler.kiml.ogdf.option.numberOfRounds", 20000);
    /** 'minimalTemperature' property. */
    private static final IProperty<Float> MINIMAL_TEMPERATURE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minimalTemperature", 0.005f);
    /** 'initialTemperature' property. */
    private static final IProperty<Float> INITIAL_TEMPERATURE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.initialTemperature", 10.0f);
    /** 'gravitationalConstant' property. */
    private static final IProperty<Float> GRAVITATIONAL_CONSTANT = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.gravitationalConstant", 0.0625f);
    /** 'maximalDisturbance' property. */
    private static final IProperty<Float> MAXIMAL_DISTURBANCE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.maximalDisturbance", 0.0f);
    /** 'rotationAngle' property. */
    private static final IProperty<Float> ROTATION_ANGLE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.rotationAngle", 0.33f);
    /** 'oscillationAngle' property. */
    private static final IProperty<Float> OSCILLATION_ANGLE = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.oscillationAngle", 0.5f);
    /** 'rotationSensitivity' property. */
    private static final IProperty<Float> ROTATION_SENSITIVITY = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.rotationSensitivity", 0.01f);
    /** 'oscillationSensitivity' property. */
    private static final IProperty<Float> OSCILLATION_SENSITIVITY = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.oscillationSensitivity", 0.3f);
    /** 'attractionFormula' property. */
    private static final IProperty<AttractionFormula> ATTRACTION_FORMULA =
            new Property<AttractionFormula>("de.cau.cs.kieler.kiml.ogdf.option.attractionFormula",
                    AttractionFormula.FRUCHTERMAN_REINGOLD);
    /** 'minDistCC' property. */
    private static final IProperty<Float> MIN_DIST_CC = new Property<Float>(
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC", 20.0f);

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
        addOption(OgdfServer.OPTION_PAGE_RATIO, pageRatio);
        // desiredLength
        float minSpacing = parentLayout.getProperty(SPACING);
        addOption(OgdfServer.OPTION_DESIRED_LENGTH, minSpacing);
        // numberOfRounds
        int numberOfRounds = parentLayout.getProperty(NUMBER_OF_ROUNDS);
        addOption(OgdfServer.OPTION_NUMBER_OF_ROUNDS, numberOfRounds);
        // minimalTemperature
        float minimalTemperature = parentLayout.getProperty(MINIMAL_TEMPERATURE);
        addOption(OgdfServer.OPTION_MINIMAL_TEMPERATURE, minimalTemperature);
        // initialTemperature
        float initialTemperature = parentLayout.getProperty(INITIAL_TEMPERATURE);
        addOption(OgdfServer.OPTION_INITIAL_TEMPERATURE, initialTemperature);
        // gravitationalConstant
        float gravitationalConstant = parentLayout.getProperty(GRAVITATIONAL_CONSTANT);
        addOption(OgdfServer.OPTION_GRAVITATIONAL_CONSTANT, gravitationalConstant);
        // maximalDisturbance
        float maximalDisturbance = parentLayout.getProperty(MAXIMAL_DISTURBANCE);
        addOption(OgdfServer.OPTION_MAXIMAL_DISTURBANCE, maximalDisturbance);
        // rotationAngle
        float rotationAngle = parentLayout.getProperty(ROTATION_ANGLE);
        addOption(OgdfServer.OPTION_ROTATION_ANGLE, rotationAngle);
        // oscillationAngle
        float oscillationAngle = parentLayout.getProperty(OSCILLATION_ANGLE);
        addOption(OgdfServer.OPTION_OSCILLATION_ANGLE, oscillationAngle);
        // rotationSensitivity
        float rotationSensitivity = parentLayout.getProperty(ROTATION_SENSITIVITY);
        addOption(OgdfServer.OPTION_ROTATION_SENSITIVITY, rotationSensitivity);
        // oscillationSensitivity
        float oscillationSensitivity = parentLayout.getProperty(OSCILLATION_SENSITIVITY);
        addOption(OgdfServer.OPTION_OSCILLATION_SENSITIVITY, oscillationSensitivity);
        // attractionFormula
        AttractionFormula attractionFormula = parentLayout.getProperty(ATTRACTION_FORMULA);
        switch (attractionFormula) {
        case FRUCHTERMAN_REINGOLD:
            addOption(OgdfServer.OPTION_ATTRACTION_FORMULA, 1);
            break;
        case GEM:
            addOption(OgdfServer.OPTION_ATTRACTION_FORMULA, 2);
            break;
        }
        // minDistCC
        float minDistCC = parentLayout.getProperty(MIN_DIST_CC);
        addOption(OgdfServer.OPTION_MIN_DIST_CC, minDistCC);
    }
    
}
