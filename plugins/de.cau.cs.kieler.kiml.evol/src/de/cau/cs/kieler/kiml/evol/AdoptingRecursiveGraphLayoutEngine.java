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

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.evol.genetic.EnumGene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.UniversalNumberGene;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * A recursive layout engine that can adopt an individual.
 *
 * @author bdu
 * @see RecursiveGraphLayoutEngine
 *
 */
class AdoptingRecursiveGraphLayoutEngine extends RecursiveGraphLayoutEngine {

    /** Cached layout manager. */
    private IDiagramLayoutManager<?> manager;

    /**
     * @return the layout manager most recently used by this engine.
     */
    public IDiagramLayoutManager<?> getManager() {
        return this.manager;
    }

    /**
     * Calculates a layout for the given individual in the given editor.
     *
     * @param individual
     *            the {@link Genome} from which layout options shall be adopted;
     *            may not be {@code null}
     * @param editor
     *            the {@link DiagramEditor} in which layout shall be calculated;
     *            may not be {@code null}
     * @param progressMonitor
     *            monitor to which progress of the layout algorithms is
     *            reported; may be {@code null}
     * @param shouldCopyGraph
     *            indicates whether the layout shall be calculated for a copy of
     *            the layout graph or on the original layout graph.
     * @return the resulting layout graph
     */
    LayoutMapping<?> calculateLayout(
            final Genome individual, final DiagramEditor editor,
            final IKielerProgressMonitor progressMonitor, final boolean shouldCopyGraph) {

        if (this.manager == null) {
            this.manager =
                EclipseLayoutInfoService.getInstance().getManager(editor, null);
        }

        LayoutMapping<?> mapping = this.manager.buildLayoutGraph(editor, null);
        KNode layoutGraph = mapping.getLayoutGraph();
        // TODO: discuss: can we have manager.getEditor() ?

        // Transfer layout options from the individual to the KGraph.
        KNode adopted;
        if (shouldCopyGraph) {
            adopted = adoptIndividual(individual, layoutGraph);
        } else {
            adopted = adoptIndividualInPlace(individual, layoutGraph);
        }

        // Make sure there is a monitor of some kind.
        IKielerProgressMonitor monitor =
                progressMonitor != null ? progressMonitor : new BasicProgressMonitor();
        try {
            // perform recursive layout on the graph
            layout(adopted, monitor);

        } catch (final Throwable exception) {
            // layout failed
            EvolPlugin.showError("Layout algorithm failed", exception);
            return null;
        }
        return mapping;
    }

    /**
     * Transfers layout options from the given individual to a copy of the
     * specified graph. The given graph instance is left unmodified.
     *
     * @param individual
     *            the {@link Genome} that is encoding the layout options to be
     *            transferred
     * @param originalGraph
     *            the graph
     * @return a copy of the given graph with the adopted layout options
     * @see {@link #adoptIndividualInPlace(Genome, KNode)}
     */
    private static KNode adoptIndividual(final Genome individual, final KNode originalGraph) {

        KNode copy = EcoreUtil.copy(originalGraph);

        return adoptIndividualInPlace(individual, copy);
    }

    /**
     * Transfers layout options from the given individual to the specified
     * graph. <strong>NOTE</strong>: This alters the given graph instance.
     *
     * @param individual
     *            the {@link Genome} that is encoding the layout options to be
     *            transferred
     * @param graph
     *            the target graph
     * @return the given graph with the adopted layout options
     * @see {@link #adoptIndividual(Genome, KNode)}
     */
    private static KNode adoptIndividualInPlace(final Genome individual, final KNode graph) {
        if ((individual == null) || (graph == null)) {
            throw new IllegalArgumentException("Argument must not be null.");
        }

        EvolPlugin.logStatus("Adopting " + individual.toString());

        KShapeLayout shapeLayout = graph.getData(KShapeLayout.class);

        // Memorize important options.
        // The ALGORITHM option is needed to find out if the new one is compatible.
        String algorithm = shapeLayout.getProperty(LayoutOptions.ALGORITHM);

        // Remove all options to make sure the omitted properties are removed.
        shapeLayout.getProperties().clear();

        // Restore important options.
        shapeLayout.setProperty(LayoutOptions.ALGORITHM, algorithm);

        LayoutDataService layoutServices = LayoutDataService.getInstance();

        // Set layout options according to the genome.
        for (final IGene<?> gene : individual.getGenes()) {

            Object value = gene.getValue();
            Object id = gene.getId();
            System.out.println(id + ": " + value);

            LayoutOptionData<?> data = layoutServices.getOptionData((String) id);
            assert data != null : "No layout option data for " + id;

            // Treat layout hint.
            if (LayoutOptions.ALGORITHM_ID.equalsIgnoreCase((String) id)) {
                handleAlgorithm(gene, shapeLayout);
                continue;
            }

            Type layoutOptionType = data.getType();

            // Nice-to-have: polymorphism should rather be used instead of
            // switch case statement
            switch (layoutOptionType) {
            case BOOLEAN:
                handleBooleanOption(data, gene, shapeLayout);
                break;

            case ENUM:
                handleEnumOption(data, gene, shapeLayout);
                break;

            case FLOAT:
                handleFloatOption(data, gene, shapeLayout);
                break;

            case INT:
                handleIntegerOption(data, gene, shapeLayout);
                break;

            case STRING:
                handleStringOption(data, gene, shapeLayout);
                break;

            default:
                shapeLayout.setProperty(data, value.toString());
                break;
            }
        }

        return graph;
    }

    /**
     * Sets the layout algorithm hint.
     *
     * @param gene
     *            the gene that encodes the value to set
     * @param targetGraphData
     *            the graph data for which the layout algorithm shall be set
     */
    private static void handleAlgorithm(final IGene<?> gene, final KGraphData targetGraphData) {

        // Cannot use the int value of the gene because it
        // is the index for the internal list in the gene.

        // Are we allowed to set the layout hint?
        boolean canSetLayoutHint =
                EvolPlugin.getDefault().getPreferenceStore()
                        .getBoolean(EvolPlugin.PREF_USE_LAYOUT_HINT_FROM_GENOME);

        if (!canSetLayoutHint) {
            // We have nothing to do, for we are not allowed
            // to set the layout hint at all.
            return;
        }

        // Are we allowed to set the layout hint for different
        // types?
        boolean canSetForDifferentType =
                EvolPlugin.getDefault().getPreferenceStore()
                        .getBoolean(EvolPlugin.PREF_USE_DIFFERENT_TYPE_LAYOUT_HINT);

        String newLayoutHintId = gene.toString();

        IProperty<String> property = LayoutOptions.ALGORITHM;

        String oldLayoutHintId = targetGraphData.getProperty(property);
        if (oldLayoutHintId == null) {
            System.err.println("Layout hint was null");
        }

        LayoutAlgorithmData algorithmData =
                DefaultLayoutConfig.getLayouterData(newLayoutHintId, null);

        String newType = algorithmData.getType();

        if ((oldLayoutHintId != null) && !canSetForDifferentType
                && !EvolUtil.isCompatibleLayoutAlgorithm(oldLayoutHintId, newType)) {
            // we are not allowed do this
            System.err.println("Attempt to set the layout hint to incompatible type: "
                    + newLayoutHintId);
            return;
        }

        targetGraphData.setProperty(property, newLayoutHintId);
    }

    /**
     * @param data
     *            specifies the option to set
     * @param gene
     *            the gene that encodes the value to set
     * @param targetGraphData
     *            the graph data for which the option shall be set
     */
    private static void handleStringOption(
            final LayoutOptionData<?> data, final IGene<?> gene, final KGraphData targetGraphData) {

        Object value = gene.getValue();
        Object id = gene.getId();

        assert !LayoutOptions.ALGORITHM_ID.equalsIgnoreCase((String) id);

        // a normal string option
        targetGraphData.setProperty(data, value.toString());
    }

    /**
     * @param data
     *            specifies the option to set
     * @param gene
     *            the gene that encodes the value to set
     * @param targetGraphData
     *            the graph data for which the option shall be set
     */
    private static void handleIntegerOption(
            final LayoutOptionData<?> data, final IGene<?> gene, final KGraphData targetGraphData) {
        Object value = gene.getValue();
        if (value instanceof Integer) {
            targetGraphData.setProperty(data, value);
        } else {
            int intValue = ((UniversalNumberGene) gene).getIntValue();
            targetGraphData.setProperty(data, intValue);
        }
    }

    /**
     * @param data
     *            specifies the option to set
     * @param gene
     *            the gene that encodes the value to set
     * @param targetGraphData
     *            the graph data for which the option shall be set
     */
    private static void handleEnumOption(
            final LayoutOptionData<?> data, final IGene<?> gene, final KGraphData targetGraphData) {

        Object value = gene.getValue();
        try {
            if (gene instanceof EnumGene) {
                Class<? extends Enum<?>> enumClass =
                        ((EnumGene) gene).getTypeInfo().getTypeClass();
                Enum<?> enumConst = enumClass.getEnumConstants()[(Integer) value];
                targetGraphData.setProperty(data, enumConst);
            } else {
                // XXX legacy handling of enums
                targetGraphData.setProperty(data, value);
            }

        } catch (final NullPointerException e) {
            // could not set enum property to new enum value
            EvolPlugin.showError("Enum property could not be set: " + gene.getId(), e);
            throw new AssertionError(value);
        }
    }

    /**
     * @param data
     *            specifies the option to set
     * @param gene
     *            the gene that encodes the value to set
     * @param targetGraphData
     *            the graph data for which the option shall be set
     */
    private static void handleBooleanOption(
            final LayoutOptionData<?> data, final IGene<?> gene, final KGraphData targetGraphData) {
        Object value = gene.getValue();

        if (value instanceof Boolean) {
            targetGraphData.setProperty(data, value);
        } else {
            // XXX legacy handling of boolean values
            boolean boolValue = ((UniversalNumberGene) gene).getBoolValue();

            targetGraphData.setProperty(data, boolValue);
        }
    }

    /**
     * @param data
     *            specifies the option to set
     * @param gene
     *            the gene that encodes the value to set
     * @param targetGraphData
     *            the graph data for which the option shall be set
     */
    private static void handleFloatOption(
            final LayoutOptionData<?> data, final IGene<?> gene, final KGraphData targetGraphData) {
        Object value = gene.getValue();
        if (value instanceof Float) {
            targetGraphData.setProperty(data, value);
        } else {
            // XXX legacy handling of floats
            targetGraphData.setProperty(data, gene.toString());
        }
    }
    
}
