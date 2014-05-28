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
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.grana.AnalysisFailed.Type;
import de.cau.cs.kieler.kiml.grana.DynamicAnalysisResult;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;

/**
 * This is not really an {@link IAnalysis} in it's original intention. The analysis checks if an
 * {@link IKielerProgressMonitor} is attached to the passed KGraph. If so it extracts information
 * about execution times from the progress monitor.
 * 
 * The main intention of this is to allow serialization (eg as csv) of detailed execution times
 * during a batch analysis of real world diagrams.
 * 
 * @author uru
 */
public class ExecutionTimeAnalysis implements IAnalysis {

    /**
     * The id used to look for an attached progress monitor.
     */
    public static final String PROGRESS_MONITOR_PROPERTY_ID = "kiml.grana.progressMonitor";
    
    private static final IProperty<IKielerProgressMonitor> PROGRESS_MONITOR_PROPERTY =
            new Property<IKielerProgressMonitor>(PROGRESS_MONITOR_PROPERTY_ID);

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Execution Time Analysis", 1);

        IKielerProgressMonitor pm =
                parentNode.getData(KLayoutData.class).getProperty(PROGRESS_MONITOR_PROPERTY);

        if (pm == null) {
            progressMonitor.done();
            return new AnalysisFailed(Type.Failed);
        }
        Stack<IKielerProgressMonitor> mons = new Stack<IKielerProgressMonitor>();
        mons.add(pm);
        
        List<String> labels = Lists.newLinkedList();
        List<Object> values = Lists.newLinkedList();
        while (!mons.isEmpty()) {
            IKielerProgressMonitor mon = mons.pop();
            labels.add(mon.getTaskName());
            values.add(mon.getExecutionTime());

            mons.addAll(Lists.reverse(mon.getSubMonitors()));
        }

        DynamicAnalysisResult result = new DynamicAnalysisResult(labels, values);

        progressMonitor.done();
        return result;
    }

}
