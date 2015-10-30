/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui.visualizers;

import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.ui.visualization.AbstractSimpleVisualizer;

/**
 * A special visualizer for arrays.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class ArrayResultVisualizer extends AbstractSimpleVisualizer<String> {

    /**
     * {@inheritDoc}
     */
    public boolean canVisualize(final Object result) {
        return (result instanceof Object[]);
    }

    /**
     * {@inheritDoc}
     */
    public String visualize(final AnalysisData analysis, final Object result) {
        if (result instanceof Object[]) {
            Object[] objects = (Object[]) result;
            StringBuilder vis = new StringBuilder("(");
            boolean first = true;
            for (Object object : objects) {
                if (first) {
                    first = false;
                } else {
                    vis.append(", ");
                }
                vis.append(object.toString());
            }
            vis.append(")");
            return vis.toString();
        } else {
            return result.toString();
        }
    }
}
