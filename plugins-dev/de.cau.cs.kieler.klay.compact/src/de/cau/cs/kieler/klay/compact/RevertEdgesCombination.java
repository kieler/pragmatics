package de.cau.cs.kieler.klay.compact;

/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

import java.util.List;

import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.kivi.LayoutEffect;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;

/**
 * A combination that allows to add a 'revert' layout property to edges.
 * 
 * When using this be aware that the 'hard' way to revert the edges, i.e. within the model
 * might yield different results of the cycle breaking phase!
 * 
 * @author uru
 */
public class RevertEdgesCombination extends AbstractCombination {

    public static IProperty<Boolean> p = new Property<Boolean>(
            "de.cau.cs.kieler.klay.compact.reversal", false);

    private List<KEdge> revertedLastTime = Lists.newLinkedList();

    public void execute(SelectionState klighdSelectionState) {

        // the parent graph, if available
        KNode graph = null;

        // undo last reversals
        for (KEdge e : revertedLastTime) {
            e.getData(KLayoutData.class).setProperty(p, null);

            revert(e);

            graph = ((KEdge) e).getSource().getParent();
        }
        revertedLastTime.clear();

        // check if there is a edge within the selection
        for (Object o : klighdSelectionState.getSelectionElements()) {
            if (o instanceof KEdge) {
                System.out.println(o);
                // if so revert and remember
                ((KEdge) o).getData(KLayoutData.class).setProperty(p, true);
                revertedLastTime.add((KEdge) o);

                KEdge edge = (KEdge) o;
                revert(edge);

                graph = ((KEdge) o).getSource().getParent();
            }
        }

        // layout if we found a graph
        if (graph != null) {
            LayoutEffect le = new LayoutEffect(klighdSelectionState.getWorkbenchPart(), graph);
            le.execute();
        }

    }

    /**
     * Revert the edge in the model itself! 
     */
    private void revert(final KEdge edge) {
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                KNode target = edge.getTarget();
                edge.setTarget(edge.getSource());
                edge.setSource(target);
                
                KPort tp = edge.getTargetPort();
                edge.setTargetPort(edge.getSourcePort());
                edge.setSourcePort(tp);
            }
        });

    }
}
