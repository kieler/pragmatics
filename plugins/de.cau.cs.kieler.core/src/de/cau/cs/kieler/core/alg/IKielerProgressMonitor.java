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

import java.util.List;

/**
 * Interface for monitors of progress of a job. A progress monitor can be used
 * in conjunction with {@link IAlgorithm} to track progress of an algorithm run.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public interface IKielerProgressMonitor {

    /** constant indicating an unknown amount of work. */
    int UNKNOWN_WORK = -1;

    /**
     * Notifies that the task will begin after this method has been called. This
     * method will have no effect is the monitor is closed.
     * 
     * @param name readable name of the new task
     * @param totalWork total amount of work units, or <code>UNKNOWN_WORK</code>
     *            if this is not specified
     */
    void begin(String name, int totalWork);

    /**
     * Notifies that the current task is done and closes the monitor. This
     * method may be called multiple times, without any effect after the first
     * time.
     */
    void done();

    /**
     * Notifies that the given number of work units has been completed. This
     * method will have no effect is the monitor is closed.
     * 
     * @param work number of work units
     */
    void worked(int work);

    /**
     * Returns whether cancellation of the task has been requested.
     * 
     * @return true if cancellation has been requested
     */
    boolean isCanceled();

    /**
     * Creates a new sub-task that will complete the given amount of work units
     * when it is done. The sub-task begins when {@link #begin(String, int)} is
     * called for the new progress monitor instance, and it ends when
     * {@link #done()} is called for that instance.
     * 
     * @param work number of work units that are completed in the current
     *            monitor instance when the sub-task is done
     * @return a progress monitor for the new sub-task, or null if the monitor
     *         is closed
     */
    IKielerProgressMonitor subTask(int work);

    /**
     * Returns a list of all monitors associated with direct sub-tasks.
     * 
     * @return list of sub-task monitors
     */
    List<IKielerProgressMonitor> getSubMonitors();

    /**
     * Returns the parent monitor. The parent monitor is the one for which a
     * call to {@link #subTask(int)} resulted in the current monitor instance.
     * 
     * @return the parent monitor, or null if there is none
     */
    IKielerProgressMonitor getParentMonitor();

    /**
     * Returns the name of the task associated with this progress monitor.
     * 
     * @return task name
     */
    String getTaskName();

    /**
     * Returns the measured execution time for the task associated with this
     * monitor.
     * 
     * @return number of seconds used for execution
     */
    double getExecutionTime();

}
