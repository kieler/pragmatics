/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.genetic;

import java.util.ArrayList;
import java.util.LinkedList;

import org.eclipse.core.runtime.Assert;

/**
 * 
 * A genome is a List of Gene objects.
 * 
 * @author bdu
 * 
 */
public class Genome extends ArrayList<IGene<?>> {
    /**
     * 
     */
    private static final long serialVersionUID = 6338612803085690432L;
    
    // return a recombination of the given genomes.
    private static Genome newRecombination(final Genome... genomes) {
        Genome result = new Genome(null);
        if (genomes.length == 1) {
            result = new Genome(genomes[0]);
        } else {
            final int size = genomes[0].size();
            // iterate genes
            for (int g = 0; g < size; g++) {
                LinkedList<IGene<?>> geneList = new LinkedList<IGene<?>>();
                int gm = 0;
                for (Genome genome : genomes) {
                    if (gm++ > 0) {
                        Assert.isTrue(genome.size() == size);
                        geneList.add(genome.get(g));
                    }
                }
                IGene[] otherGenes = geneList.toArray(new IGene[geneList.size()]);
                IGene<?> oldGene = genomes[0].get(g);
                IGene<?> newGene = oldGene.recombineWith(otherGenes);
                result.add(newGene);
            }
        }
        return result;
    }
    
    /**
     * Copy constructor for a genome.
     * 
     * @param genome
     *            template genome
     */
    public Genome(final Genome genome) {
        super();
        if (genome != null) {
            for (IGene<?> gene : genome) {
                if (gene != null) {
                    this.add(gene);
                }
            }
        }
    }
    
    /**
     * Create a new genome that is a cross over of this genome and the given
     * genome.
     * 
     * @param otherGenome
     *            a genome
     * @return a new genome
     */
    public Genome newRecombination(final Genome otherGenome) {
        return newRecombination(this, otherGenome);
    }
}
