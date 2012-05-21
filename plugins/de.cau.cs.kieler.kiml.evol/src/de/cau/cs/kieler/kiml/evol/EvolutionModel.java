/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.evol.alg.BasicEvolutionaryAlgorithm;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * The main class for access to evolutionary meta layout.
 * This class is a singleton: at most one evolution model is active at any time. This is a
 * great limitation, and it should be extended in the future: it should be possible to
 * link an evolution model with specific diagrams, diagram types, or use cases. View management
 * might be a feasible approach for this.
 *
 * @author msp
 */
public final class EvolutionModel extends BasicEvolutionaryAlgorithm {
    
    /** the singleton instance. */
    private static EvolutionModel instance = new EvolutionModel();
    
    /**
     * Returns the active evolution model instance.
     * 
     * @return the active instance
     */
    public static EvolutionModel getInstance() {
        return instance;
    }
    
    /** the individual that was selected for meta layout. */
    private Genome selectedIndividual;
    
    /**
     * Hidden constructor to prevent instantiation from outside this class.
     */
    private EvolutionModel() {
        super();
    }
    
    /**
     * Returns the last individual that was selected for meta layout.
     * 
     * @return the last selected individual
     */
    public Genome getSelected() {
        return selectedIndividual;
    }
    
    public void initializePopulation(final KNode parentNode) {
        Population population = getPopulation();
        if (population.getSize() > 0) {
            population.getGenomes().clear();
        }
    }

}
