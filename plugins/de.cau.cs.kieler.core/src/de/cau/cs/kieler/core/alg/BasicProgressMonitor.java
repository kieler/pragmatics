/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.core.alg;

import java.util.LinkedList;
import java.util.List;

/**
 * Base class for implementations of progress monitors. This class performs
 * execution time measurement, keeps track of the amount of completed work, and
 * handles sub-tasks properly.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class BasicProgressMonitor implements IKielerProgressMonitor {

    /** the parent monitor. */
    private BasicProgressMonitor parentMonitor = null;
    /** indicates whether the monitor has been closed. */
    private boolean closed = false;
    /** list of child monitors. */
    private List<IKielerProgressMonitor> children = new LinkedList<IKielerProgressMonitor>();
    /**
     * the number of work units that will be consumed after completion of the
     * currently active child task.
     */
    private int currentChildWork = -1;
    /** the start time of the associated task, in nanoseconds. */
    private long startTime;
    /** the total time of the associated task, in seconds. */
    private double totalTime;
    /** the name of the associated task. */
    private String taskName;
    /** the amount of work that is completed. */
    private float completedWork = 0.0f;
    /** the number of work units that can be completed in total. */
    private int totalWork;

    /**
     * {@inheritDoc}
     */
    public final void begin(final String name, final int thetotalWork) {
        if (!closed) {
            this.taskName = name;
            this.totalWork = thetotalWork;
            doBegin(name, thetotalWork, parentMonitor == null);
            startTime = System.nanoTime();
        }
    }

    /**
     * Invoked when a task begins, to be overridden by subclasses. This
     * implementation does nothing.
     * 
     * @param name task name
     * @param newTotalWork total amount of work for the new task
     * @param topInstance if true, this progress monitor is the top instance
     */
    protected void doBegin(final String name, final int newTotalWork, final boolean topInstance) {
    }

    /** factor for nanoseconds. */
    private static final double NANO_FACT = 1e-9;

    /**
     * {@inheritDoc}
     */
    public final void done() {
        if (!closed) {
            totalTime = (System.nanoTime() - startTime) * NANO_FACT;
            if (completedWork < totalWork) {
                internalWorked(totalWork - completedWork);
            }
            closed = true;
        }
    }

    /**
     * Invoked when a task ends, to be overridden by subclasses. This
     * implementation does nothing.
     * 
     * @param topInstance if true, this progress monitor is the top instance
     */
    protected void doDone(final boolean topInstance) {
    }

    /**
     * {@inheritDoc}
     */
    public final double getExecutionTime() {
        return totalTime;
    }

    /**
     * {@inheritDoc}
     */
    public final List<IKielerProgressMonitor> getSubMonitors() {
        return children;
    }

    /**
     * {@inheritDoc}
     */
    public final IKielerProgressMonitor getParentMonitor() {
        return parentMonitor;
    }

    /**
     * {@inheritDoc}
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * This implementation always returns {@code false}.
     * 
     * @return {@code false}
     */
    public boolean isCanceled() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public final IKielerProgressMonitor subTask(final int work) {
        if (!closed) {
            BasicProgressMonitor subMonitor = doSubTask(work);
            children.add(subMonitor);
            subMonitor.parentMonitor = this;
            currentChildWork = work;
            return subMonitor;
        } else {
            return null;
        }
    }

    /**
     * Invoked when a sub-task is created, to be overridden by subclasses. This
     * implementation creates a new {@code BasicProgressMonitor} instance.
     * 
     * @param work amount of work that is completed in the current monitor
     *            instance when the sub-task ends
     * @return a new progress monitor instance
     */
    protected BasicProgressMonitor doSubTask(final int work) {
        return new BasicProgressMonitor();
    }

    /**
     * {@inheritDoc}
     */
    public void worked(final int work) {
        if (work > 0 && !closed) {
            internalWorked(work);
        }
    }

    /**
     * Sets the current work counters of this monitor and all parent monitors.
     * 
     * @param work amount of work that has been completed
     */
    private void internalWorked(final float work) {
        if (totalWork > 0 && completedWork < totalWork) {
            completedWork += work;
            doWorked(work, completedWork, parentMonitor == null);
            if (parentMonitor != null && parentMonitor.currentChildWork > 0) {
                parentMonitor.internalWorked(work / totalWork * parentMonitor.currentChildWork);
            }
        }
    }

    /**
     * Invoked when work is done for this progress monitor, to be overridden by
     * subclasses. This implementation does nothing.
     * 
     * @param work amount of work that has just been done
     * @param thecompletedWork total number of work that is done for this task
     * @param topInstance if true, this progress monitor is the top instance
     */
    protected void doWorked(final float work, final float thecompletedWork, final boolean topInstance) {
    }

}
