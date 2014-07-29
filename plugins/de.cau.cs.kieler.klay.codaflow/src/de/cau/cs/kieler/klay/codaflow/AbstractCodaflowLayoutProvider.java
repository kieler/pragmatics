/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow;

import java.util.Set;

import org.eclipse.emf.common.util.WrappedException;

import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.klay.codaflow.graphimport.IGraphImporter;
import de.cau.cs.kieler.klay.codaflow.processors.ILayoutProcessor;
import de.cau.cs.kieler.klay.codaflow.properties.InternalCodaflowProperties;
import de.cau.cs.kieler.klay.codaflow.util.CodaflowUtil;

/**
 * Common superclass for Codaflow's layout providers. Provides several convenience methods for
 * setup, import, and layout application.
 * 
 * @author uru
 */
public abstract class AbstractCodaflowLayoutProvider extends AbstractLayoutProvider {

    /**
     * Executes the specified {@link ILayoutProcessor} <b>if necessary</b>, ie if it has not been
     * executed before.
     * 
     * @param clazz
     *            the class of the processor to be executed
     * @param cgraph
     *            the graph to execute the processor on
     * @param pm
     *            a progress monitor
     * 
     * @return {@code true} if the processor was executed previously and is _not_ executed again,
     *         false otherwise.
     */
    protected boolean executeProcessor(final Class<? extends ILayoutProcessor> clazz,
            final CGraph cgraph, final IKielerProgressMonitor pm) {

        Set<Class<? extends ILayoutProcessor>> executedProcessors =
                cgraph.getProperty(InternalCodaflowProperties.EXECUTED_PROCESSPORS);
        // was the processor executed previously?
        if (executedProcessors.contains(clazz)) {
            return true;
        }

        // otherwise we execute it now
        try {
            ILayoutProcessor proc = clazz.newInstance();
            proc.process(cgraph, pm.subTask(1));

            executedProcessors.add(clazz);

        } catch (Exception e) {
            throw new WrappedException(e);
        }

        return false;
    }

    /**
     * Imports the specified KGraph. Note that the {@link #applyLayout(CGraph, KNode)} method must
     * be called after the layout process to write to calculated coordinates back to the original
     * KGraph. This should be done even if the layout is executed within a pipeline to guarantee a
     * consistent state.
     * 
     * @param parent
     *            the graph to import.
     * @return the imported {@link CGraph}.
     */
    protected CGraph importGraph(final KNode parent) {
        return CodaflowUtil.importGraphIfNecessary(parent);
    }

    /**
     * Writes calculated positions back to the original graph.
     * 
     * @param graph
     *            the {@link CGraph} on which layout was executed.
     * @param parent
     *            the original graph.
     */
    protected void applyLayout(final CGraph graph, final KNode parent) {
        IGraphImporter<KNode, CGraph> importer =
                parent.getData(KLayoutData.class).getProperty(
                        InternalCodaflowProperties.IMPORTER_USED);
        importer.applyLayout(graph);
    }

}
