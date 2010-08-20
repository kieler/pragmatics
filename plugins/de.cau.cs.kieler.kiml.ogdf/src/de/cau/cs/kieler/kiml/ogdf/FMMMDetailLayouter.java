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
package de.cau.cs.kieler.kiml.ogdf;

import net.ogdf.lib.Ogdf;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * The FMMM layouter from the OGDF library with low-level options enabled
 * instead of only the high-level ones.
 * 
 * @author mri
 */
public class FMMMDetailLayouter extends OgdfLayouter {

    /** the cool temperature option. */
    public static final String COOL_TEMPERATURE =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.coolTemperature";
    /** the default value for the cool temperature option. */
    public static final boolean DEF_COOL_TEMPERATURE = false;
    /** the cool value option. */
    public static final String COOL_VALUE =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.coolValue";
    /** the default value for the cool value option. */
    public static final float DEF_COOL_VALUE = 0.99f;
    /** the fine tune scalar option. */
    public static final String FINE_TUNE_SCALAR =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.fineTuneScalar";
    /** the default value for the fine tune scalar option. */
    public static final float DEF_FINE_TUNE_SCALAR = 0.2f;
    /** the fine tuning iterations option. */
    public static final String FINE_TUNING_ITERATIONS =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.fineTuningIterations";
    /** the default value for the fine tuning iterations option. */
    public static final int DEF_FINE_TUNING_ITERATIONS = 20;
    /** the fixed iterations option. */
    public static final String FIXED_ITERATIONS =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.fixedIterations";
    /** the default value for the fixed iterations option. */
    public static final int DEF_FIXED_ITERATIONS = 30;
    /** the force scaling option. */
    public static final String FORCE_SCALING_FACTOR =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.forceScalingFactor";
    /** the default value for the force scaling option. */
    public static final float DEF_FORCE_SCALING_FACTOR = 0.05f;
    /** the grid quotient option. */
    public static final String GRID_QUOTIENT =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.frGridQuotient";
    /** the default value for the grid quotient option. */
    public static final int DEF_GRID_QUOTIENT = 2;
    /** the max iter option. */
    public static final String MAX_ITER_FACTOR =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.maxIterFactor";
    /** the default value for the max iter option. */
    public static final int DEF_MAX_ITER_FACTOR = 10;
    /** the min dist cc option. */
    public static final String MIN_DIST_CC =
            "de.cau.cs.kieler.kiml.ogdf.option.minDistCC";
    /** the default value for the min dist cc option. */
    public static final float DEF_MIN_DIST_CC = 100.0f;
    /** the min graph size option. */
    public static final String MIN_GRAPH_SIZE =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.minGraphSize";
    /** the default value for the min graph size option. */
    public static final int DEF_MIN_GRAPH_SIZE = 50;
    /** the particles in leaves option. */
    public static final String PARTICLES_IN_LEAVES =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.nmParticlesInLeaves";
    /** the default value for the particles in leaves option. */
    public static final int DEF_PARTICLES_IN_LEAVES = 25;
    /** the precision option. */
    public static final String PRECISION =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.nmPrecision";
    /** the default value for the precision option. */
    public static final int DEF_PRECISION = 4;
    /** the post spring strength option. */
    public static final String POST_SPRING_STRENGTH =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.postSpringStrength";
    /** the default value for the post spring strength option. */
    public static final float DEF_POST_SPRING_STRENGTH = 2.0f;
    /** the post strength of rep forces option. */
    public static final String POST_STRENGTH_OF_REP_FORCES =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.postStrengthOfRepForces";
    /** the default value for the post strength of rep forces option. */
    public static final float DEF_POST_STRENGTH_OF_REP_FORCES = 0.01f;
    /** the random tries option. */
    public static final String RANDOM_TRIES =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.randomTries";
    /** the default value for the random tries option. */
    public static final int DEF_RANDOM_TRIES = 20;
    /** the the rep forces strength option. */
    public static final String REP_FORCES_STRENGTH =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.repForcesStrength";
    /** the default value for the the rep forces strength option. */
    public static final float DEF_REP_FORCES_STRENGTH = 1.0f;
    /** the spring strength option. */
    public static final String SPRING_STRENGTH =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.springStrength";
    /** the default value for the spring strength option. */
    public static final float DEF_SPRING_STRENGTH = 1.0f;
    /** the steps for rotating components option. */
    public static final String STEPS_FOR_ROTATING_COMPONENTS =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.stepsForRotatingComponents";
    /** the default value for the steps for rotating components option. */
    public static final int DEF_STEPS_FOR_ROTATING_COMPONENTS = 10;
    /** the threshold option. */
    public static final String THRESHOLD =
            "de.cau.cs.kieler.kiml.ogdf.option.fmmm.threshold";
    /** the default value for the threshold option. */
    public static final float DEF_THRESHOLD = 0.01f;
    /** default value for border spacing. */
    private static final float DEF_BORDER_SPACING = 15;
    /** default value for label edge distance. */
    private static final float DEF_LABEL_SPACING = 15.0f;
    /** default value for label margin distance. */
    private static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = KimlUtil.getShapeLayout(layoutNode);

        boolean coolTemperature =
                LayoutOptions.getBoolean(parentLayout, COOL_TEMPERATURE);

        float coolValue = LayoutOptions.getFloat(parentLayout, COOL_VALUE);
        if (Float.isNaN(coolValue)) {
            coolValue = DEF_COOL_VALUE;
        }

        float fineTuneScalar =
                LayoutOptions.getFloat(parentLayout, FINE_TUNE_SCALAR);
        if (Float.isNaN(fineTuneScalar)) {
            fineTuneScalar = DEF_FINE_TUNE_SCALAR;
        }

        int fineTuningIterations =
                LayoutOptions.getInt(parentLayout, FINE_TUNING_ITERATIONS);
        if (fineTuningIterations == 0) {
            fineTuningIterations = DEF_FINE_TUNING_ITERATIONS;
        }

        int fixedIterations =
                LayoutOptions.getInt(parentLayout, FIXED_ITERATIONS);
        if (fixedIterations == 0) {
            fixedIterations = DEF_FIXED_ITERATIONS;
        }

        float forceScalingFactor =
                LayoutOptions.getFloat(parentLayout, FORCE_SCALING_FACTOR);
        if (Float.isNaN(forceScalingFactor)) {
            forceScalingFactor = DEF_FORCE_SCALING_FACTOR;
        }

        int gridQuotient = LayoutOptions.getInt(parentLayout, GRID_QUOTIENT);
        if (gridQuotient == 0) {
            gridQuotient = DEF_GRID_QUOTIENT;
        }

        int maxIterFactor = LayoutOptions.getInt(parentLayout, MAX_ITER_FACTOR);
        if (maxIterFactor == 0) {
            maxIterFactor = DEF_MAX_ITER_FACTOR;
        }

        float minDistCC = LayoutOptions.getFloat(parentLayout, MIN_DIST_CC);
        if (Float.isNaN(minDistCC)) {
            minDistCC = DEF_MIN_DIST_CC;
        }

        int minGraphSize = LayoutOptions.getInt(parentLayout, MIN_GRAPH_SIZE);
        if (minGraphSize == 0) {
            minGraphSize = DEF_MIN_GRAPH_SIZE;
        }

        int particlesInLeaves =
                LayoutOptions.getInt(parentLayout, PARTICLES_IN_LEAVES);
        if (particlesInLeaves == 0) {
            particlesInLeaves = DEF_PARTICLES_IN_LEAVES;
        }

        int precision = LayoutOptions.getInt(parentLayout, PRECISION);
        if (precision == 0) {
            precision = DEF_PRECISION;
        }

        float postSpringStrength =
                LayoutOptions.getFloat(parentLayout, POST_SPRING_STRENGTH);
        if (Float.isNaN(postSpringStrength)) {
            postSpringStrength = DEF_POST_SPRING_STRENGTH;
        }

        float postStrengthOfRepForces =
                LayoutOptions.getFloat(parentLayout,
                        POST_STRENGTH_OF_REP_FORCES);
        if (Float.isNaN(postStrengthOfRepForces)) {
            postStrengthOfRepForces = DEF_POST_STRENGTH_OF_REP_FORCES;
        }

        int randomTries = LayoutOptions.getInt(parentLayout, RANDOM_TRIES);
        if (randomTries == 0) {
            randomTries = DEF_RANDOM_TRIES;
        }

        float repForcesStrength =
                LayoutOptions.getFloat(parentLayout, REP_FORCES_STRENGTH);
        if (Float.isNaN(repForcesStrength)) {
            repForcesStrength = DEF_REP_FORCES_STRENGTH;
        }

        float springStrength =
                LayoutOptions.getFloat(parentLayout, SPRING_STRENGTH);
        if (Float.isNaN(springStrength)) {
            springStrength = DEF_SPRING_STRENGTH;
        }

        int stepsForRotatingComponents =
                LayoutOptions.getInt(parentLayout,
                        STEPS_FOR_ROTATING_COMPONENTS);
        if (stepsForRotatingComponents == 0) {
            stepsForRotatingComponents = DEF_STEPS_FOR_ROTATING_COMPONENTS;
        }

        float threshold = LayoutOptions.getFloat(parentLayout, THRESHOLD);
        if (Float.isNaN(threshold)) {
            threshold = DEF_THRESHOLD;
        }

        Ogdf.createFMMMLayouterDetail(coolTemperature, coolValue,
                fineTuneScalar, fineTuningIterations, fixedIterations,
                forceScalingFactor, gridQuotient, maxIterFactor, minDistCC,
                minGraphSize, particlesInLeaves, precision, postSpringStrength,
                postStrengthOfRepForces, randomTries, repForcesStrength,
                springStrength, stepsForRotatingComponents, threshold);
        
        // remove self-loops from the graph
        loopRouter.preProcess(layoutNode);
    }
    
    /**
     * {@inheritDoc}
     */
    protected void postProcess(final KNode layoutNode) {
        loopRouter.postProcess();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getDefault(final String optionId) {
        if (optionId
                .equals(COOL_TEMPERATURE)) {
            return DEF_COOL_TEMPERATURE;
        } else if (optionId
                .equals(COOL_VALUE)) {
            return DEF_COOL_VALUE;
        } else if (optionId
                .equals(FINE_TUNE_SCALAR)) {
            return DEF_FINE_TUNE_SCALAR;
        } else if (optionId
                .equals(FINE_TUNING_ITERATIONS)) {
            return DEF_FINE_TUNING_ITERATIONS;
        } else if (optionId
                .equals(FIXED_ITERATIONS)) {
            return DEF_FIXED_ITERATIONS;
        } else if (optionId
                .equals(FORCE_SCALING_FACTOR)) {
            return DEF_FORCE_SCALING_FACTOR;
        } else if (optionId
                .equals(GRID_QUOTIENT)) {
            return DEF_GRID_QUOTIENT;
        } else if (optionId
                .equals(MAX_ITER_FACTOR)) {
            return DEF_MAX_ITER_FACTOR;
        } else if (optionId
                .equals(MIN_DIST_CC)) {
            return DEF_MIN_DIST_CC;
        } else if (optionId
                .equals(MIN_GRAPH_SIZE)) {
            return DEF_MIN_GRAPH_SIZE;
        } else if (optionId
                .equals(PARTICLES_IN_LEAVES)) {
            return DEF_PARTICLES_IN_LEAVES;
        } else if (optionId
                .equals(PRECISION)) {
            return DEF_PRECISION;
        } else if (optionId
                .equals(POST_SPRING_STRENGTH)) {
            return DEF_POST_SPRING_STRENGTH;
        } else if (optionId
                .equals(POST_STRENGTH_OF_REP_FORCES)) {
            return DEF_POST_STRENGTH_OF_REP_FORCES;
        } else if (optionId
                .equals(RANDOM_TRIES)) {
            return DEF_RANDOM_TRIES;
        } else if (optionId
                .equals(REP_FORCES_STRENGTH)) {
            return DEF_REP_FORCES_STRENGTH;
        } else if (optionId
                .equals(SPRING_STRENGTH)) {
            return DEF_SPRING_STRENGTH;
        } else if (optionId
                .equals(STEPS_FOR_ROTATING_COMPONENTS)) {
            return DEF_STEPS_FOR_ROTATING_COMPONENTS;
        } else if (optionId
                .equals(THRESHOLD)) {
            return DEF_THRESHOLD;
        } else if (optionId.equals(OPT_LABEL_EDGE_DISTANCE)) {
            return DEF_LABEL_SPACING;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else if (optionId.equals(LayoutOptions.BORDER_SPACING_ID)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(OPT_LABEL_EDGE_DISTANCE)) {
            return DEF_LABEL_SPACING;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else {
            return null;
        }
    }
}
