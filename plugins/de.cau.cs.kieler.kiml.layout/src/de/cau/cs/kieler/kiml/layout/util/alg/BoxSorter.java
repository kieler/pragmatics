/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.layout.util.alg;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * Algorithm that sorts nodes according to their priority and
 * size.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class BoxSorter extends AbstractAlgorithm {

    /**
     * Sorts nodes according to priority and size. Nodes with
     * higher priority get a lower rank.
     * 
     * @param parentNode parent node
     * @return sorted list of children
     */
    public List<KNode> sort(KNode parentNode) {
        getMonitor().begin("Box sorting", 1);
        List<KNode> sortedBoxes = new LinkedList<KNode>(
                parentNode.getChildren());
        
        Collections.sort(sortedBoxes, new Comparator<KNode>() {
            public int compare(KNode child1, KNode child2) {
                KShapeLayout layout1 = KimlLayoutUtil.getShapeLayout(child1);
                int prio1 = LayoutOptions.getPriority(layout1);
                KShapeLayout layout2 = KimlLayoutUtil.getShapeLayout(child2);
                int prio2 = LayoutOptions.getPriority(layout2);
                if (prio1 > prio2)
                    return -1;
                else if (prio1 < prio2)
                    return 1;
                else {
                    // boxes have same priority - compare their size
                    float size1 = layout1.getWidth() * layout1.getHeight();
                    float size2 = layout2.getWidth() * layout2.getHeight();
                    return Float.compare(size1, size2); // TODO make this comparison customizable
                }
            }
        });
        
        getMonitor().done();
        return sortedBoxes;
    }
    
}
