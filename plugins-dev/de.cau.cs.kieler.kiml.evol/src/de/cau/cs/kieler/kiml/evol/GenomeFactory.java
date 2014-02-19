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
package de.cau.cs.kieler.kiml.evol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.ILayoutData;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo.GeneType;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.service.ExtensionLayoutConfigService;
import de.cau.cs.kieler.kiml.service.LayoutMapping;
import de.cau.cs.kieler.kiml.util.BoxLayoutProvider;
import de.cau.cs.kieler.kiml.util.FixedLayoutProvider;

/**
 * A factory for genes and genomes.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public final class GenomeFactory {

    /**
     * Hidden constructor to prevent instantiation.
     */
    private GenomeFactory() {
    }

    /** identifier for the layout type gene. */
    public static final String LAYOUT_TYPE_ID = "de.cau.cs.kieler.kiml.evol.layoutType";

    /** probability for mutation of layout type genes. */
    private static final double P_LAYOUT_TYPE_MUTATION = 0.12;
    /** probability for mutation of layout algorithm genes. */
    private static final double P_LAYOUT_ALGO_MUTATION = 0.18;
    /** probability for mutation of boolean type genes. */
    private static final double P_BOOLEAN_MUTATION = 0.25;
    /** probability for mutation of enumeration type genes. */
    private static final double P_ENUM_MUTATION = 0.30;
    /** probability for mutation of integer type genes. */
    private static final double P_INT_MUTATION = 0.45;
    /** probability for mutation of floating point type genes. */
    private static final double P_FLOAT_MUTATION = 0.50;
    
    /**
     * Create a genome with default values from the given layout mapping.
     * 
     * @param layoutMapping a layout mapping
     * @param config a layout configurator for obtaining default values
     * @param options the layout options to consider in the constructed genome
     * @return a genome filled with genes
     */
    public static Genome createInitialGenome(final LayoutMapping<?> layoutMapping,
            final ILayoutConfig config, final Collection<LayoutOptionData> options) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        LayoutOptionData algoOptionData = dataService.getOptionData(
                LayoutOptions.ALGORITHM.getId());
        LayoutOptionData diagTypeData = dataService.getOptionData(
                LayoutOptions.DIAGRAM_TYPE.getId());
        
        Genome genome = new Genome();
        
        // traverse the compound hierarchy of the graph
        LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.add(layoutMapping.getLayoutGraph());
        
        do {
            KNode parentNode = nodeQueue.removeFirst();
            
            // create layout context for the parent node
            LayoutContext context = createContext(parentNode, layoutMapping, config);
            genome.addContext(context, dataService.getOptionData().size() + 1);
            String algorithmId = (String) config.getOptionValue(algoOptionData, context);
            String diagramType = (String) config.getOptionValue(diagTypeData, context);
            LayoutAlgorithmData algorithmData = DefaultLayoutConfig.getLayouterData(
                    algorithmId, diagramType);

            if (options.contains(algoOptionData)) {
                // create genes for the layout type and algorithm
                LayoutTypeData typeData = dataService.getTypeData(algorithmData.getType());
                genome.getGenes(context).add(createLayoutTypeGene(typeData));
                genome.getGenes(context).add(createAlgorithmGene(typeData, algorithmData));
            }
            
            // create genes for the other layout options (the algorithm option is excluded)
            for (LayoutOptionData optionData : options) {
                TypeInfo<?> typeInfo = createTypeInfo(optionData);
                if (typeInfo != null) {
                    Gene<?> gene;
                    if (algorithmData.knowsOption(optionData)) {
                        gene = createDefaultGene(algorithmData, optionData, typeInfo, config,
                                context);
                    } else {
                        gene = Gene.create(null, typeInfo, false);
                    }
                    genome.getGenes(context).add(gene);
                }
            }
            
            // look for other parent nodes
            for (KNode child : parentNode.getChildren()) {
                if (!child.getChildren().isEmpty()) {
                    nodeQueue.add(child);
                }
            }
        } while (!nodeQueue.isEmpty());
        return genome;
    }
    
    /**
     * Create a gene for the layout type.
     * 
     * @param typeData the layout type data
     * @return a gene initialized to the given type
     */
    public static Gene<Integer> createLayoutTypeGene(final LayoutTypeData typeData) {
        List<LayoutTypeData> typeList = new ArrayList<LayoutTypeData>(LayoutDataService.getInstance()
                .getTypeData());
        // remove layout types that have no registered algorithms
        ListIterator<LayoutTypeData> typeIter = typeList.listIterator();
        while (typeIter.hasNext()) {
            if (typeIter.next().getLayouters().isEmpty()) {
                typeIter.remove();
            }
        }
        
        int index = typeList.indexOf(typeData);
        if (index < 0) {
            throw new IllegalStateException("The active layout type is not registered.");
        }
        TypeInfo<Integer> typeInfo = new TypeInfo<Integer>(LAYOUT_TYPE_ID, GeneType.LAYOUT_TYPE, 0,
                typeList.size() - 1, typeList, P_LAYOUT_TYPE_MUTATION, 1);
        return Gene.create(index, typeInfo, true);
    }
    
    /**
     * Create a gene for the layout algorithm. The gene will offer only algorithms of the given type.
     * 
     * @param layoutType the type of layout algorithm
     * @param algoData the layout algorithm data
     * @return a gene initialized to the given algorithm
     */
    public static Gene<Integer> createAlgorithmGene(final LayoutTypeData layoutType,
            final LayoutAlgorithmData algoData) {
        List<LayoutAlgorithmData> algoList = getAlgoList(layoutType);
        int index = algoList.indexOf(algoData);
        TypeInfo<Integer> typeInfo = new TypeInfo<Integer>(LayoutOptions.ALGORITHM.getId(),
                GeneType.LAYOUT_ALGO, 0, algoList.size() - 1, algoList, P_LAYOUT_ALGO_MUTATION, 1);
        return Gene.create(index, typeInfo, true);
    }
    
    /**
     * Create a gene for the layout algorithm and randomly select one.
     * 
     * @param layoutType the type of layout algorithm
     * @param random a random number generator
     * @return a gene initialized to a random algorithm of the given type
     */
    public static Gene<Integer> createAlgorithmGene(final LayoutTypeData layoutType,
            final Random random) {
        List<LayoutAlgorithmData> algoList = getAlgoList(layoutType);
        TypeInfo<Integer> typeInfo = new TypeInfo<Integer>(LayoutOptions.ALGORITHM.getId(),
                GeneType.LAYOUT_ALGO, 0, algoList.size() - 1, algoList, P_LAYOUT_ALGO_MUTATION, 1);
        if (algoList.isEmpty()) {
            return Gene.create(null, typeInfo, false);
        }
        int randomAlgoIndex = random.nextInt(algoList.size());
        return Gene.create(randomAlgoIndex, typeInfo, true);
    }
    
    /**
     * Return a filtered list of layout algorithms for the given layout type. Algorithms that
     * are not suitable for use in evolutionary layout are removed.
     * 
     * @param layoutType a layout type
     * @return filtered algorithms
     */
    public static List<LayoutAlgorithmData> getAlgoList(final LayoutTypeData layoutType) {
        List<LayoutAlgorithmData> algoList = layoutType.getLayouters();
        if (layoutType.getId().length() == 0) {
            // This is the 'Other' layout type
            algoList = new ArrayList<LayoutAlgorithmData>(algoList);
            Iterator<LayoutAlgorithmData> iterator = algoList.iterator();
            while (iterator.hasNext()) {
                LayoutAlgorithmData data = iterator.next();
                if (FixedLayoutProvider.ID.equals(data.getId())
                        || BoxLayoutProvider.ID.equals(data.getId())) {
                    iterator.remove();
                }
            }
        }
        return algoList;
    }
    
    /**
     * Create gene type information for the given layout option.
     * 
     * @param optionData a layout option data
     * @return gene type information for the given option, or  {@code null} if the type is not supported
     */
    public static TypeInfo<?> createTypeInfo(final LayoutOptionData optionData) {
        switch (optionData.getType()) {
        case BOOLEAN:
            return new TypeInfo<Integer>(optionData.getId(), GeneType.BOOLEAN, 0,
                    1, optionData, P_BOOLEAN_MUTATION, 1);
        case ENUM:
            int enumConstCount = optionData.getOptionClass().getEnumConstants().length;
            return new TypeInfo<Integer>(optionData.getId(), GeneType.ENUM, 0,
                    enumConstCount - 1, optionData,
                    enumConstCount > 2 ? P_ENUM_MUTATION : P_BOOLEAN_MUTATION, 1);
        case INT:
            return new TypeInfo<Integer>(optionData.getId(), GeneType.INTEGER,
                    optionData.getLowerBound(), optionData.getUpperBound(),
                    optionData, P_INT_MUTATION, optionData.getVariance());
        case FLOAT:
            return new TypeInfo<Float>(optionData.getId(), GeneType.FLOAT,
                    optionData.getLowerBound(), optionData.getUpperBound(),
                    optionData, P_FLOAT_MUTATION, optionData.getVariance());
        default:
            return null;
        }
    }
    
    /**
     * Create a layout configurator for obtaining default values.
     * Note: the semantic layout configurations are not considered for default values.
     * 
     * @param layoutMapping a layout mapping created by a diagram layout manager
     * @return a layout configurator
     */
    public static ILayoutConfig createConfig(final LayoutMapping<?> layoutMapping) {
        CompoundLayoutConfig clc = new CompoundLayoutConfig();
        clc.add(new DefaultLayoutConfig());
        for (ILayoutConfig config : ExtensionLayoutConfigService.getInstance().getActiveConfigs()) {
            if (!(config instanceof EvolLayoutConfig)) {
                clc.add(config);
            }
        }
        clc.addAll(layoutMapping.getLayoutConfigs());
        return clc;
    }
    
    /**
     * Create a layout context for a graph element.
     * 
     * @param graphElement the graph element for which to create the context
     * @param layoutMapping the layout mapping, or {@code null}
     * @param layoutConfig the layout configurator
     * @return a layout context
     */
    public static LayoutContext createContext(final KGraphElement graphElement,
            final LayoutMapping<?> layoutMapping, final ILayoutConfig layoutConfig) {
        // create a layout context for the given layout graph
        LayoutContext context = new LayoutContext();
        context.setProperty(LayoutContext.GRAPH_ELEM, graphElement);
        if (layoutMapping != null) {
            Object diagramPart = layoutMapping.getGraphMap().get(graphElement);
            context.setProperty(LayoutContext.DIAGRAM_PART, diagramPart);
            EObject modelElement = (EObject) layoutMapping.getAdapterFactory().getAdapter(
                    diagramPart, EObject.class);
            context.setProperty(LayoutContext.DOMAIN_MODEL, modelElement);
            IWorkbenchPart workbenchPart = (IWorkbenchPart) Iterables.find(
                    layoutMapping.getAllProperties().values(),
                    Predicates.instanceOf(IWorkbenchPart.class), null);
            context.setProperty(EclipseLayoutConfig.WORKBENCH_PART, workbenchPart);
        }

        // enrich the layout context using the given configurator
        DiagramLayoutEngine.INSTANCE.getOptionManager().enrich(context, layoutConfig, true);
        
        return context;
    }
    
    /**
     * Create a gene with default value using an existing type info. The gene is initially active.
     * 
     * @param algoData the active layout algorithm
     * @param optionData the layout option for which to create the gene
     * @param typeInfo type information for the layout option
     * @param config a layout configurator for obtaining default values
     * @param context a layout context for obtaining default value
     * @param <T> value type of the gene to create
     * @return a new gene with the obtained default value
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> Gene<T> createDefaultGene(
            final LayoutAlgorithmData algoData, final LayoutOptionData optionData,
            final TypeInfo<T> typeInfo, final ILayoutConfig config, final LayoutContext context) {
        context.setProperty(DefaultLayoutConfig.CONTENT_ALGO, algoData);
        T value = translateToGene(config.getOptionValue(optionData, context), typeInfo);
        
        Comparable<? super T> lowerBound = typeInfo.getLowerBound();
        if (lowerBound.compareTo(value) > 0) {
            value = (T) lowerBound;
        } else {
            Comparable<? super T> upperBound = typeInfo.getUpperBound();
            if (upperBound.compareTo(value) < 0) {
                value = (T) upperBound;
            }
        }
        return Gene.create(value, typeInfo, true);
    }
    
    /**
     * Translate a layout option value to a gene value.
     * 
     * @param value a layout option value
     * @param typeInfo the type information of the gene
     * @param <T> type of result value
     * @return a gene value
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> T translateToGene(final Object value,
            final TypeInfo<T> typeInfo) {
        switch (typeInfo.getGeneType()) {
        case LAYOUT_ALGO:
            LayoutAlgorithmData algoData = LayoutDataService.getInstance().getAlgorithmData(
                    (String) value);
            int algoIndex = ((List<?>) typeInfo.getTypeParam()).indexOf(algoData);
            return (T) Integer.valueOf(algoIndex);
        case LAYOUT_TYPE:
            LayoutTypeData typeData = LayoutDataService.getInstance().getTypeData((String) value);
            int typeIndex = ((List<?>) typeInfo.getTypeParam()).indexOf(typeData);
            return (T) Integer.valueOf(typeIndex);
        case BOOLEAN:
            return (T) Integer.valueOf((((Boolean) value) ? 1 : 0));
        case ENUM:
            return (T) Integer.valueOf(((Enum<?>) value).ordinal());
        default:
            return (T) value;
        }
    }
    
    /**
     * Translate a gene value into a type understood by meta layout. The gene value must
     * not be {@code null}.
     * 
     * @param gene a gene
     * @return the translated gene value
     */
    public static Object translateFromGene(final Gene<?> gene) {
        GeneType geneType = gene.getTypeInfo().getGeneType();
        switch (geneType) {
        case LAYOUT_ALGO:
        case LAYOUT_TYPE:
            return ((ILayoutData) gene.listValue()).getId();
        case BOOLEAN:
            return (Integer) gene.getValue() != 0;
        case ENUM:
            return gene.enumValue();
        default:
            return gene.getValue();
        }
    }
    
    /**
     * Configure a graph using a given genome.
     * 
     * @param genome the genome holding layout option values
     * @param graphMap map of context graph elements to elements of the test graph that is configured
     */
    public static void configureGraph(final Genome genome, final Map<EObject, EObject> graphMap) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        for (LayoutContext context : genome.getContexts()) {
            EObject element = graphMap.get(context.getProperty(LayoutContext.GRAPH_ELEM));
            if (element instanceof KNode) {
                KShapeLayout nodeLayout = ((KNode) element).getData(KShapeLayout.class);
                for (Gene<?> gene : genome.getGenes(context)) {
                    if (gene.getValue() != null) {
                        LayoutOptionData optionData = dataService.getOptionData(
                                gene.getTypeInfo().getId());
                        if (optionData != null) {
                            nodeLayout.setProperty(optionData, translateFromGene(gene));
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Check whether the given genome is consistent with respect to the active flags of its genes.
     * 
     * @param genome a genome
     */
    public static void checkGenome(final Genome genome) {
        for (LayoutContext context : genome.getContexts()) {
            LayoutAlgorithmData algoData = null;
            for (Gene<?> gene : genome.getGenes(context)) {
                TypeInfo<?> typeInfo = gene.getTypeInfo();
                if (typeInfo.getGeneType() == TypeInfo.GeneType.LAYOUT_ALGO) {
                    algoData = (LayoutAlgorithmData) gene.listValue();
                } else if (algoData != null) {
                    LayoutOptionData optionData = (LayoutOptionData) typeInfo.getTypeParam();
                    boolean knowsOption = algoData.knowsOption(optionData);
                    if (knowsOption && !gene.isActive()) {
                        throw new IllegalStateException("Gene " + gene
                                + " is inactive, but its algorithm " + algoData
                                + " knows the option.");
                    } else if (!knowsOption && gene.isActive()) {
                        throw new IllegalStateException("Gene " + gene
                                + " is active, but its algorithm " + algoData
                                + " does not know the option.");
                    }
                }
            }
        }
    }
    
    /**
     * Check the size of all node labels and ensure that the nodes are large enough to include
     * their labels.
     * 
     * @param parentNode the parent node of the graph
     * @param device the device used to generate preview images
     */
    public static void checkLabels(final KNode parentNode, final Device device) {
        GC gc = new GC(new Image(device, 1, 1));
        LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.add(parentNode);
        do {
            KNode node = nodeQueue.removeFirst();
            
            float maxX = 0, maxY = 0;
            for (KLabel label : node.getLabels()) {
                if (label.getText() != null && label.getText().length() > 0) {
                    KVector bounds = calculateTextBounds(label.getText(), gc);
                    KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                    if (labelLayout.getWidth() < bounds.x) {
                        labelLayout.setWidth((float) bounds.x);
                    }
                    if (labelLayout.getHeight() < bounds.y) {
                        labelLayout.setHeight((float) bounds.y);
                    }
                    maxX = Math.max(maxX, labelLayout.getXpos() + labelLayout.getWidth());
                    maxY = Math.max(maxY, labelLayout.getYpos() + labelLayout.getHeight());
                }
            }
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            if (nodeLayout.getWidth() < maxX) {
                nodeLayout.setWidth(maxX);
            }
            if (nodeLayout.getHeight() < maxY) {
                nodeLayout.setHeight(maxY);
            }
            
            nodeQueue.addAll(node.getChildren());
        } while (!nodeQueue.isEmpty());
    }
    
    /**
     * Calculates the bounds of the text in the box as measured by the given
     * graphics context and font metrics.
     * 
     * @param gc graphics context from which the measurements are done
     * @return point representing the dimensions of the text's bounds
     */
    private static KVector calculateTextBounds(final String text, final GC gc) {
        FontMetrics fm = gc.getFontMetrics();
        KVector textBounds = new KVector(0, 0);

        StringTokenizer tokenizer = new StringTokenizer(text, "\n\r");
        boolean firstLine = true;
        while (tokenizer.hasMoreTokens()) {
            String line = tokenizer.nextToken();
            Point lineBounds = gc.stringExtent(line);
            if (firstLine) {
                textBounds.x = lineBounds.x;
                textBounds.y = fm.getAscent() + fm.getDescent() + fm.getLeading();
                firstLine = false;
            } else {
                textBounds.x = Math.max(lineBounds.x, textBounds.x);
                textBounds.y += fm.getHeight();
            }
        }
        return textBounds;
    }

}
