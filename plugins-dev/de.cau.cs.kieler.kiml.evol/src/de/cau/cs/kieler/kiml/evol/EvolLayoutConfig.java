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

import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Evolutionary layout configurator. Uses evolutionary algorithms together with graph drawing
 * analysis to derive layout option values.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class EvolLayoutConfig implements ILayoutConfig {
    
    /** the priority for the evolutionary layout configurator. */
    public static final int PRIORITY = 16;
    
    /** property for activation of the evolutionary layout configurator. */
    public static final Property<Boolean> ACTIVATION = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.evol", false);
    
    /** property for the evolution model stored in the layout context. */
    private static final Property<LayoutEvolutionModel> EVOL_MODEL = new Property<LayoutEvolutionModel>(
            "evol.model");

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    public void enrich(final LayoutContext context) {
        LayoutEvolutionModel model = LayoutEvolutionModel.getInstance();
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (model.getSelected() != null && diagramPart != null) {
            context.setProperty(EVOL_MODEL, model);
            if (context.getProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS)) {
                Gene<?> algorithmGene = model.getSelected().findGene(LayoutOptions.ALGORITHM.getId(),
                        diagramPart);
                if (algorithmGene != null && algorithmGene.getValue() != null) {
                    String algorithm = (String) GenomeFactory.translateFromGene(algorithmGene);
                    
                    // set layout algorithm identifier for the content
                    if (context.getProperty(DefaultLayoutConfig.CONTENT_HINT) == null) {
                        context.setProperty(DefaultLayoutConfig.CONTENT_HINT, algorithm);
                    }
                    
                    // set layout algorithm identifier for the container
                    if (context.getProperty(DefaultLayoutConfig.CONTAINER_HINT) == null) {
                        context.setProperty(DefaultLayoutConfig.CONTAINER_HINT, algorithm);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        LayoutEvolutionModel model = context.getProperty(EVOL_MODEL);
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (model != null && diagramPart != null) {
            Gene<?> gene = model.getSelected().findGene(optionData.getId(), diagramPart);
            if (gene != null && gene.getValue() != null) {
                return GenomeFactory.translateFromGene(gene);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KLayoutData graphData, final LayoutContext inputContext) {
        LayoutEvolutionModel model = inputContext.getProperty(EVOL_MODEL);
        Object diagramPart = inputContext.getProperty(LayoutContext.DIAGRAM_PART);
        if (model != null && diagramPart != null) {
            LayoutContext keyContext = model.getSelected().findContext(diagramPart);
            if (keyContext != null) {
                LayoutDataService dataService = LayoutDataService.getInstance();
                for (Gene<?> gene : model.getSelected().getGenes(keyContext)) {
                    if (gene.getValue() != null) {
                        LayoutOptionData<?> optionData = dataService.getOptionData(
                                gene.getTypeInfo().getId());
                        if (optionData != null) {
                            graphData.setProperty(optionData, GenomeFactory.translateFromGene(gene));
                        }
                    }
                }
            }
        }
    }

}
