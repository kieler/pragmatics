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
package de.cau.cs.kieler.klay.layered;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import lpsolve.AbortListener;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;

/**
 * An abort listener for LpSolve based algorithms used to abort the execution when the timeout is
 * reached.
 *
 * @author msp
 */
public class LPSolveAborter implements AbortListener {

    /** the timeout after which to abort the process. */
    private long timeout;
    /** whether a timeout has occurred. */
    private boolean timeoutOccurred;
    /** a progress monitor that signals user abortion. */
    private IKielerProgressMonitor monitor;
    
    /**
     * Creates an LpSolve aborter.
     * 
     * @param thetimeout timeout after which to abort the process
     * @param themonitor a progress monitor that signals user abortion
     */
    public LPSolveAborter(final long thetimeout, final IKielerProgressMonitor themonitor) {
        this.timeout = thetimeout;
        this.monitor = themonitor;
    }
    
    /** the start time for the timeout. */
    private long startTime = System.currentTimeMillis();
    
    /**
     * {@inheritDoc}
     */
    public boolean abortfunc(final LpSolve problem, final Object userhandle)
            throws LpSolveException {
        timeoutOccurred = System.currentTimeMillis() - startTime > timeout;
        return timeoutOccurred || monitor.isCanceled();
    }
    
    /**
     * Returns whether a timeout has occurred during execution.
     * 
     * @return whether a timeout has occurred
     */
    public boolean isTimeoutOccurred() {
        return timeoutOccurred;
    }

    /**
     * Returns a readable error message for an LpSolve error.
     * 
     * @param lpSolveError
     *            the return value of LpSolve indicating an error
     * @return the readable message created for the LpSolve error
     */
    public static String getErrorMessage(final int lpSolveError) {
        switch (lpSolveError) {
        case LpSolve.NOMEMORY:
            return "The LpSolver has run out of memory";
        case LpSolve.INFEASIBLE:
            return "The LP is infeasible.";
        case LpSolve.UNBOUNDED:
            return "The LP is unbounded.";
        case LpSolve.DEGENERATE:
            return "The LP is degenerative.";
        case LpSolve.NUMFAILURE:
            return "A numerical failure has been encountered.";
        case LpSolve.USERABORT:
            return "LpSolver aborted by user.";
        case LpSolve.TIMEOUT:
            return "No feasible solution found after the timeout has elapsed.";
        case LpSolve.PROCFAIL:
            return "The branch and bound routine failed.";
        case LpSolve.PROCBREAK:
            return "The branch and bound was stopped because of a break-at-first or a break-at-value.";
        case LpSolve.NOFEASFOUND:
            return "No feasible branch-and-bound solution found.";
        default:
            return "No feasible solution found. LpSolve return value: " + lpSolveError;
        }
    }

}
