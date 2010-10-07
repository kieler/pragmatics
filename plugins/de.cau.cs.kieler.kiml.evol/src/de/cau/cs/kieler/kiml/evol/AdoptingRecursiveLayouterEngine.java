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
     * @return the resulting layout graph
     */
    KNode calculateLayout(
            final Genome individual, final DiagramEditor editor,
            final IKielerProgressMonitor progressMonitor) {

        if (this.manager == null) {
            this.manager =
                EclipseLayoutServices.getInstance().getManager(editor, null);
        }

        KNode layoutGraph = this.manager.buildLayoutGraph(editor, null, false);
        // TODO: discuss: can we have manager.getEditor() ?

        // Transfer layout options from the individual to the KGraph.
        KNode adopted = adoptIndividual(individual, layoutGraph);

        // Make sure there is a monitor of some kind.
        IKielerProgressMonitor monitor =
                (progressMonitor != null ? progressMonitor : new BasicProgressMonitor());
        try {
            layout(adopted, monitor, false);
        } catch (final KielerException exception) {
            // layout failed
            exception.printStackTrace();
            return null;
        }
        return adopted;
    }

    /**
     * Transfers layout options from the given individual to the specified
     * graph.
     *
     * @param individual
     *            the {@link Genome} that is encoding the layout options to be
     *            transferred
     * @param originalGraph
     *            the original graph
     * @return a copy of the graph with the adopted layout options
     */
    private static KNode adoptIndividual(final Genome individual, final KNode originalGraph) {
        if ((individual == null) || (originalGraph == null)) {
            throw new IllegalArgumentException("Argument must not be null.");
        }

        EvolPlugin.logStatus("Adopting " + individual.toString());

        KNode copy = EcoreUtil.copy(originalGraph);
        KShapeLayout shapeLayout = copy.getData(KShapeLayout.class);

        LayoutServices layoutServices = LayoutServices.getInstance();

        // Set layout options according to the genome.
        for (final IGene<?> gene : individual) {

            Object value = gene.getValue();
            Object id = gene.getId();
            System.out.println(id + ": " + value);

            LayoutOptionData<?> data = layoutServices.getLayoutOptionData((String) id);
            assert data != null : "No layout option data for " + id;

            Type layoutOptionType = data.getType();

            // Nice-to-have: polymorphism should rather be used instead of
            // switch case statement
            switch (layoutOptionType) {
            case BOOLEAN:
                if (value instanceof Boolean) {
                    shapeLayout.setProperty(data, value);
                } else {
                    // XXX legacy handling of boolean values
                    shapeLayout
                            .setProperty(data, Math.round(((Float) value).floatValue()) == 1.0);
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
                    boolean canSetLayoutHint =
                            EvolPlugin.getDefault().getPreferenceStore()
                                    .getBoolean(EvolPlugin.PREF_USE_LAYOUT_HINT_FROM_GENOME);

                    if (!canSetLayoutHint) {
                        // We have nothing to do, for we are not allowed
                        // to set the layout hint at all.
                        break;
                    }

                    // Are we allowed to set the layout hint for different
                    // types?
                    boolean canSetForDifferentType =
                            EvolPlugin.getDefault().getPreferenceStore()
                                    .getBoolean(EvolPlugin.PREF_USE_DIFFERENT_TYPE_LAYOUT_HINT);

                    String newLayoutHintId = gene.toString();

                    String oldLayoutHintId = (String) shapeLayout.getProperty(data);
                    assert oldLayoutHintId != null;

                    LayoutProviderData providerData =
                            layoutServices.getLayoutProviderData(newLayoutHintId, null);

                    String newType = providerData.getType();

                    if (!canSetForDifferentType
                            && !EvolUtil.isCompatibleLayoutProvider(oldLayoutHintId, newType)) {
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
        } // for (IGene<?> gene : individual)
        return copy;
    }

}
