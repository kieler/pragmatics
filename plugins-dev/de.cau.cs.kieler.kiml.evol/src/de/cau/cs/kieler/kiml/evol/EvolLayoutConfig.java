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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
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

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    public Object getContextValue(final IProperty<?> property, final LayoutContext context) {
        LayoutEvolutionModel model = LayoutEvolutionModel.getInstance();
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (model.getSelected() != null && diagramPart != null) {
            if (property.equals(DefaultLayoutConfig.CONTENT_HINT)
                    || property.equals(DefaultLayoutConfig.CONTAINER_HINT)) {
                Gene<?> algorithmGene = model.getSelected().findGene(LayoutOptions.ALGORITHM.getId(),
                        diagramPart);
                if (algorithmGene != null) {
                    return GenomeFactory.translateFromGene(algorithmGene);
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Object getOptionValue(final LayoutOptionData optionData, final LayoutContext context) {
        LayoutEvolutionModel model = LayoutEvolutionModel.getInstance();
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (model.getSelected() != null && diagramPart != null) {
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
    public Collection<IProperty<?>> getAffectedOptions(final LayoutContext inputContext) {
        LayoutEvolutionModel model = LayoutEvolutionModel.getInstance();
        Object diagramPart = inputContext.getProperty(LayoutContext.DIAGRAM_PART);
        List<IProperty<?>> options = new LinkedList<IProperty<?>>();
        if (model.getSelected() != null && diagramPart != null) {
            LayoutContext keyContext = model.getSelected().findContext(diagramPart);
            if (keyContext != null) {
                LayoutMetaDataService dataService = LayoutMetaDataService.getInstance();
                for (Gene<?> gene : model.getSelected().getGenes(keyContext)) {
                    if (gene.getValue() != null) {
                        LayoutOptionData optionData = dataService.getOptionData(
                                gene.getTypeInfo().getId());
                        if (optionData != null) {
                            options.add(optionData);
                        }
                    }
                }
            }
        }
        return options;
    }

}
