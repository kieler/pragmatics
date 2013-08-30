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
package de.cau.cs.kieler.kiml.evol.genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutContext;

/**
 * A genome contains lists of Gene objects associated with graphs. It can be used as an individual in
 * an evolutionary algorithm.
 *
 * @author bdu
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class Genome extends MapPropertyHolder {

    /** the serial version UID. */
    private static final long serialVersionUID = -2795552035498265257L;

    /** property for the fitness calculated in evaluation. */
    public static final IProperty<Double> FITNESS = new Property<Double>("evol.fitness");

    /** The genes of this genome. */
    private final Map<LayoutContext, ArrayList<Gene<?>>> geneMap;

    /**
     * Constructs an empty genome.
     */
    public Genome() {
        geneMap = Maps.newHashMap();
    }

    /**
     * Copy constructor for a genome.
     *
     * @param genome the template genome
     */
    public Genome(final Genome genome) {
        this.geneMap = Maps.newHashMapWithExpectedSize(genome.geneMap.size());
        for (Map.Entry<LayoutContext, ArrayList<Gene<?>>> entry : genome.geneMap.entrySet()) {
            this.geneMap.put(entry.getKey(), new ArrayList<Gene<?>>(entry.getValue()));
        }
        this.copyProperties(genome);
    }
    
    /**
     * Returns the collection of layout contexts to which genes are associated.
     * 
     * @return the layout contexts
     */
    public Collection<LayoutContext> getContexts() {
        return Collections.unmodifiableCollection(geneMap.keySet());
    }
    
    /**
     * Add the given layout context to this genome.
     * 
     * @param context a layout context
     * @param size expected number of genes in that context
     */
    public void addContext(final LayoutContext context, final int size) {
        geneMap.put(context, new ArrayList<Gene<?>>(size));
    }

    /**
     * Returns the number of genes associated with the given context in this genome.
     *
     * @param context one of the contexts contained in {@link #getContexts()}
     * @return the number of genes for the given graph
     */
    public final int getSize(final LayoutContext context) {
        ArrayList<Gene<?>> genes = geneMap.get(context);
        if (genes == null) {
            return 0;
        }
        return genes.size();
    }

    /**
     * Returns the genes associated with the given context.
     * 
     * @param context one of the contexts contained in {@link #getContexts()}
     * @return the genes for the given graph, or {@code null} if the context is unknown
     */
    public List<Gene<?>> getGenes(final LayoutContext context) {
        return geneMap.get(context);
    }
    
    /**
     * Find the stored layout context associated with the given graph element.
     * 
     * @param diagramPart an element of the diagram
     * @return the associated layout context, or {@code null}
     */
    public LayoutContext findContext(final Object diagramPart) {
        for (LayoutContext context : geneMap.keySet()) {
            if (context.getProperty(LayoutContext.DIAGRAM_PART).equals(diagramPart)) {
                return context;
            }
        }
        return null;
    }

    /**
     * Find a gene with the given identifier in the context of the given diagram element.
     *
     * @param theId a gene identifier
     * @param diagramPart an element of the diagram
     * @return a gene with the given ID, or {@code null} if none can be found
     */
    public Gene<?> findGene(final String theId, final Object diagramPart) {
        for (Map.Entry<LayoutContext, ArrayList<Gene<?>>> entry : geneMap.entrySet()) {
            if (entry.getKey().getProperty(LayoutContext.DIAGRAM_PART).equals(diagramPart)) {
                for (Gene<?> gene : entry.getValue()) {
                    if (gene.getTypeInfo().getId().equals(theId)) {
                        return gene;
                    }
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<LayoutContext, ArrayList<Gene<?>>> entry : geneMap.entrySet()) {
            KGraphElement graphElement = entry.getKey().getProperty(LayoutContext.GRAPH_ELEM);
            if (graphElement != null) {
                String graphString = graphElement.toString();
                if (graphString.length() <= 8) { // SUPPRESS CHECKSTYLE MagicNumber
                    result.append(graphString);
                } else {
                    result.append(graphElement.eClass().getName());
                }
            }
            result.append("(");
            ListIterator<Gene<?>> geneIter = entry.getValue().listIterator();
            while (geneIter.hasNext()) {
                Gene<?> gene = geneIter.next();
                if (gene.isActive() && gene.getValue() != null) {
                    if (geneIter.previousIndex() > 0) {
                        result.append("|");
                    }
                    result.append(gene.toString());
                }
            }
            result.append(")");
        }
        if (geneMap.isEmpty()) {
            result.append("empty genome");
        }
        return result.toString();
    }

    /** Default distance for layout type genes. */
    private static final double TYPE_GENE_DISTANCE = 7;
    /** Default distance for layout algorithm genes. */
    private static final double ALGO_GENE_DISTANCE = 2.5;
    /** Default gene distance. */
    private static final double DEFAULT_GENE_DISTANCE = 1.0;
    /** Scaling factor for float gene distances. */
    private static final double FLOAT_GENE_SCALE = 0.8;

    /**
     * Returns the distance between the given genomes. The genomes must be compatible, i.e. have the
     * same gene types in the same order. The distance is normalized over the number of genes and
     * the number of layout contexts in the genomes.
     *
     * @param genome0 a genome
     * @param genome1 another genome
     * @return the normalized distance between the genomes
     */
    public static double distance(final Genome genome0, final Genome genome1) {
        double dist = 0.0;
        int contextCount = 0;
        for (Map.Entry<LayoutContext, ArrayList<Gene<?>>> entry : genome0.geneMap.entrySet()) {
            double contextDist = 0.0;
            int geneCount = 0;
            Iterator<Gene<?>> geneIter0 = entry.getValue().iterator();
            Iterator<Gene<?>> geneIter1 = genome1.geneMap.get(entry.getKey()).iterator();
            while (geneIter0.hasNext() && geneIter1.hasNext()) {
                Gene<?> gene0 = geneIter0.next();
                Gene<?> gene1 = geneIter1.next();
    
                if (gene0.isActive() || gene1.isActive()) {
                    if (!gene0.equals(gene1)) {
                        switch (gene0.getTypeInfo().getGeneType()) {
                        case LAYOUT_TYPE:
                            contextDist += TYPE_GENE_DISTANCE;
                            break;
                        case LAYOUT_ALGO:
                            contextDist += ALGO_GENE_DISTANCE;
                            break;
                        case FLOAT:
                        case INTEGER:
                            if (gene0.getValue() == null || gene1.getValue() == null) {
                                contextDist += DEFAULT_GENE_DISTANCE;
                            } else {
                                // Distance of float genes is proportional to the absolute difference,
                                // related to the variance in order to make it more comparable.
                                double var0 = gene0.getTypeInfo().getVariance();
                                double var1 = gene1.getTypeInfo().getVariance();
                                double var = (var0 + var1) / 2;
                                double absDiff = Math.abs(gene0.floatValue() - gene1.floatValue());
                                contextDist += absDiff * FLOAT_GENE_SCALE / var;
                            }
                            break;
                        default:
                            contextDist += DEFAULT_GENE_DISTANCE;
                        }
                    }
                    geneCount++;
                }
            }
            dist += contextDist / geneCount;
            contextCount++;
        }
        return dist / contextCount;
    }
    
    /**
     * Comparator for genomes that is based on the fitness value.
     */
    public static class FitnessComparator implements Comparator<Genome> {

        /**
         * {@inheritDoc}
         */
        public int compare(final Genome g1, final Genome g2) {
            Double fitness1 = g1.getProperty(FITNESS);
            Double fitness2 = g2.getProperty(FITNESS);
            if (fitness1 != null && fitness2 != null) {
                return fitness2.compareTo(fitness1);
            } else if (fitness1 != null) {
                return -1;
            } else if (fitness2 != null) {
                return 1;
            }
            return 0;
        }
        
    }
    
}
