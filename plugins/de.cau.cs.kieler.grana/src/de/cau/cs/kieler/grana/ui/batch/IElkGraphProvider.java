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
package de.cau.cs.kieler.grana.ui.batch;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

/**
 * The interface for ELK Graph providers.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 * @param <T> the parameter type
 */
public interface IElkGraphProvider<T> {

    /**
     * Returns a KGraph instance from a specific source using the parameter.
     * 
     * @param parameter
     *            the parameter
     * @param monitor
     *            the progress monitor
     * @return the Elk Graph instance
     * @throws Exception
     *             any kind of exception
     */
    ElkNode getElkGraph(T parameter, IElkProgressMonitor monitor) throws Exception;
}
