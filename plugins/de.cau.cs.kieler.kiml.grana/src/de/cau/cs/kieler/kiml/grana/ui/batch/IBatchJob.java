/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui.batch;

import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.grana.AnalysisData;

/**
 * Represents an analysis batch job.
 *  
 * @author uru
 * @param <T>
 *            the type of the parameter
 */
public interface IBatchJob<T> {

    /**
     * Executes the job which consists of retrieving a KGraph instance through the KGraph provider
     * and performing the given analyses on it.
     * 
     * @param analyses
     *            the analyses
     * @param monitor
     *            the progress monitor
     * @return the job result
     * @throws Exception
     *             any exception
     */
    BatchJobResult<T> execute(List<AnalysisData> analyses, IKielerProgressMonitor monitor)
            throws Exception;

    /**
     * Returns the parameter of the job.
     * 
     * @return the parameter
     */
    T getParameter();

}
