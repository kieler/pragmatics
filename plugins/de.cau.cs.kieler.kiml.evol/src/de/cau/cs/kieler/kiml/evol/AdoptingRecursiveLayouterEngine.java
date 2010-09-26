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
/**
 *
 */
package de.cau.cs.kieler.kiml.evol;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.RecursiveLayouterEngine;
import de.cau.cs.kieler.kiml.evol.genetic.EnumGene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;

/**
 * A recursive layout engine that can adopt an individual.
 *
 * @author bdu
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

    /**
     * Calculates a layout for the given individual in the given editor.
     *
     * @param individual
     *            the individual that shall be adopted
     * @param editor
     *            the editor in which layout shall be calculated
     * @return a {@link LayoutResult} object
     */
    LayoutResult calculateLayout(
            final Genome individual, final DiagramEditor editor) {

        DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, null);
        KNode layoutGraph = manager.buildLayoutGraph(editor, null, false);
        // TODO: discuss: can we have manager.getEditor() ?

        // Transfer layout options from the individual to the KGraph.
        KNode adopted = adoptIndividual(individual, layoutGraph);
        IKielerProgressMonitor monitor = new BasicProgressMonitor();
        try {
            layout(adopted, monitor, false);
        } catch (final KielerException exception) {
            // TODO: do something about the failed layout
            exception.printStackTrace();
        }
        return new LayoutResult(adopted, monitor);
    }

    /**
     * Transfers layout options from the given individual to the specified
     * graph.
     *
     * @param individual
     *            the individual encoding the layout options to be transferred
     * @param originalGraph
     *            the original graph
     * @return a copy of the graph with the adopted layout options
     */
    private static KNode adoptIndividual(final Genome individual, final KNode originalGraph) {

        Assert.isLegal((individual != null) && (originalGraph != null));
        if ((individual == null) || (originalGraph == null)) {
            return null;
        }

        EvolPlugin.logStatus("Adopting " + individual.toString());

        final KNode copy = EcoreUtil.copy(originalGraph);
        final KShapeLayout shapeLayout = copy.getData(KShapeLayout.class);

        final LayoutServices layoutServices = LayoutServices.getInstance();

        // Set layout options according to the genome.
        for (final IGene<?> gene : individual) {

            final Object value = gene.getValue();
            final Object id = gene.getId();
            System.out.println(id + ": " + value);

            final LayoutOptionData<?> data = layoutServices.getLayoutOptionData((String) id);
            assert data != null : "No layout option data for " + id;

            final Type layoutOptionType = data.getType();

            switch (layoutOptionType) {
            case BOOLEAN:
                if (value instanceof Boolean) {
                    shapeLayout.setProperty(data, Integer.valueOf((((Boolean) value) ? 1 : 0)));
                } else {
                    shapeLayout.setProperty(data,
                            Integer.valueOf(Math.round(((Float) value).floatValue())));
                }
                break;

            case ENUM:
                try {
                    if (gene instanceof EnumGene) {
                        Class<? extends Enum<?>> enumClass =
                                ((EnumGene) gene).getTypeInfo().getTypeClass();
                        Enum<?> enumConst = enumClass.getEnumConstants()[(Integer) value];
                        shapeLayout.setProperty(data, enumConst);
                    } else {
                        shapeLayout.setProperty(data, value);
                    }

                } catch (final NullPointerException e) {
                    EvolPlugin.showError("Enum property could not be set: " + id, e);
                    throw new AssertionError(value);
                }
                break;

            case FLOAT:
                if (value instanceof Float) {
                    shapeLayout.setProperty(data, value);
                } else {
                    shapeLayout.setProperty(data, gene.toString());
                }
                break;

            case INT:
                if (value instanceof Integer) {
                    shapeLayout.setProperty(data, value);
                } else {
                    shapeLayout.setProperty(data, gene.toString());
                }
                break;

            case STRING:
                if (LayoutOptions.LAYOUTER_HINT_ID.equalsIgnoreCase((String) id)) {
                    // Cannot use the int value of the gene because it
                    // is the index for the internal list in the gene.

                    // Are we allowed to set the layout hint?
                    final boolean canSetLayoutHint =
                            EvolPlugin.getDefault().getPreferenceStore()
                                    .getBoolean(EvolPlugin.PREF_USE_LAYOUT_HINT_FROM_GENOME);

                    // Even for different types?
                    final boolean canSetForDifferentType =
                            EvolPlugin.getDefault().getPreferenceStore()
                                    .getBoolean(EvolPlugin.PREF_USE_DIFFERENT_TYPE_LAYOUT_HINT);

                    if (!canSetLayoutHint) {
                        // We have nothing to do, for we are not allowed
                        // to set
                        // the layout hint at all.
                        break;
                    }

                    final String newLayoutHintId = gene.toString();

                    final String oldLayoutHintId = (String) shapeLayout.getProperty(data);
                    Assert.isNotNull(oldLayoutHintId);

                    final LayoutProviderData providerData =
                            layoutServices.getLayoutProviderData(newLayoutHintId, null);

                    final String newType = providerData.getType();

                    if ((!canSetForDifferentType && !EvolUtil.isCompatibleLayoutProvider(
                            oldLayoutHintId, newType))) {
                        // we are not allowed do this
                        System.err
                                .println("Attempt to set the layout hint to incompatible type: "
                                        + newLayoutHintId);
                        break;
                    }

                    shapeLayout.setProperty(data, newLayoutHintId);

                } else {
                    // a normal string option
                    shapeLayout.setProperty(data, value.toString());
                }

                break;

            default:
                shapeLayout.setProperty(data, value.toString());
                // TODO: make sure the omitted properties are removed

                break;
            }


        }
        return copy;
    }

    /**
     * @author bdu
     *
     */
    public class LayoutResult {

        private final KNode layoutGraph;
        private final IKielerProgressMonitor monitor;

        /**
         * Creates a new {@link LayoutResult} instance.
         *
         * @param theLayoutGraph
         * @param theMonitor
         */
        public LayoutResult(final KNode theLayoutGraph, final IKielerProgressMonitor theMonitor) {
            this.layoutGraph = theLayoutGraph;
            this.monitor = theMonitor;
        }

        /**
         *
         * @return the layout graph
         */
        public KNode getLayoutGraph() {
            return this.layoutGraph;
        }

        /**
         * @return the monitor
         */
        public IKielerProgressMonitor getMonitor() {
            return this.monitor;
        }

    }

}