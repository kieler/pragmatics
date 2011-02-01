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

import java.util.Map.Entry;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.RecursiveLayouterEngine;
import de.cau.cs.kieler.kiml.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.evol.genetic.EnumGene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.UniversalNumberGene;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;

/**
 * A recursive layout engine that can adopt an individual.
 *
 * @author bdu
 * @see RecursiveLayouterEngine
 *
 */
class AdoptingRecursiveLayouterEngine extends RecursiveLayouterEngine {
    /**
     * Creates a new {@link AdoptingRecursiveLayouterEngine} instance.
     *
     */
    public AdoptingRecursiveLayouterEngine() {
        super(null);
    }

    /** Cached layout manager. */
    private DiagramLayoutManager manager;

    /**
     * @return the layout manager most recently used by this engine.
     */
    public DiagramLayoutManager getManager() {
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
    KNode calculateLayout(
            final Genome individual, final DiagramEditor editor,
            final IKielerProgressMonitor progressMonitor, final boolean shouldCopyGraph) {

        if (this.manager == null) {
            this.manager =
                EclipseLayoutServices.getInstance().getManager(editor, null);
        }

        KNode layoutGraph = this.manager.buildLayoutGraph(editor, null, false);
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
            layout(adopted, monitor, false /* layoutAncestors */);

        } catch (final KielerException exception) {
            // layout failed
            exception.printStackTrace();
            return null;
        }
        return adopted;
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
        // The LAYOUTER_HINT is needed to find out if the new one is compatible.
        // The others are needed because otherwise, layout providers might
        // crash.
        IProperty<?>[] importantOptions =
                new IProperty<?>[] { LayoutOptions.LAYOUTER_HINT, LayoutOptions.INSETS };
        ILayoutConfig oldValues = new VolatileLayoutConfig();
        for (IProperty<?> property : importantOptions) {
            oldValues.setProperty(property, shapeLayout.getProperty(property));
        }

        // Remove all options to make sure the omitted properties are removed.
        shapeLayout.getAllProperties().clear();

        // Restore important options.
        for (Entry<?, ?> entry : oldValues.getAllProperties().entrySet()) {
            shapeLayout.setProperty((IProperty<?>) entry.getKey(), entry.getValue());
        }

        LayoutServices layoutServices = LayoutServices.getInstance();

        // Set layout options according to the genome.
        for (final IGene<?> gene : individual) {

            Object value = gene.getValue();
            Object id = gene.getId();
            System.out.println(id + ": " + value);

            LayoutOptionData<?> data = layoutServices.getOptionData((String) id);
            assert data != null : "No layout option data for " + id;

            // Treat layout hint.
            if (LayoutOptions.LAYOUTER_HINT_ID.equalsIgnoreCase((String) id)) {
                handleLayouterHint(gene, shapeLayout);
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
     * Sets the layout provider hint.
     *
     * @param gene
     *            the gene that encodes the value to set
     * @param targetGraphData
     *            the graph data for which the layout provider shall be set
     */
    private static void handleLayouterHint(final IGene<?> gene, final KGraphData targetGraphData) {

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

        IProperty<String> data = LayoutOptions.LAYOUTER_HINT;

        String oldLayoutHintId = targetGraphData.getProperty(data);
        assert oldLayoutHintId != null;

        LayoutAlgorithmData providerData =
                new DefaultLayoutConfig().getLayouterData(newLayoutHintId, null);

        String newType = providerData.getType();

        if (!canSetForDifferentType
                && !EvolUtil.isCompatibleLayoutProvider(oldLayoutHintId, newType)) {
            // we are not allowed do this
            System.err.println("Attempt to set the layout hint to incompatible type: "
                    + newLayoutHintId);
            return;
        }

        targetGraphData.setProperty(data, newLayoutHintId);
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

        assert !LayoutOptions.LAYOUTER_HINT_ID.equalsIgnoreCase((String) id);

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
