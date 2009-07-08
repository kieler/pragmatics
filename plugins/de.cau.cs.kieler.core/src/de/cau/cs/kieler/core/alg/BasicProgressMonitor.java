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
package de.cau.cs.kieler.core.alg;

import java.util.LinkedList;
import java.util.List;


/**
 * Base class for implementations of progress monitors. This class performs
 * execution time measurement, keeps track of the amount of completed work,
 * and handles sub-tasks properly.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class BasicProgressMonitor implements IKielerProgressMonitor {

	/** the parent monitor */
    private BasicProgressMonitor parentMonitor = null;
    /** indicates whether the monitor has been closed */
	private boolean closed = false;
	/** list of child monitors */
	private List<IKielerProgressMonitor> children = new LinkedList<IKielerProgressMonitor>();
	/** the number of work units that will be consumed after completion
	 *  of the currently active child task */
	private int currentChildWork = -1;
	/** the start time of the associated task, in nanoseconds */
	private long startTime;
	/** the total time of the associated task, in seconds */
	private double totalTime;
	/** the name of the associated task */
	private String taskName;
	/** the amount of work that is completed */
	private float completedWork = 0.0f;
	/** the number of work units that can be completed in total */
	private int totalWork;


	/* (non-Javadoc)
	 * @see de.cau.cs.kieler.core.alg.IKielerProgressMonitor#begin(java.lang.String, int)
	 */
	public final void begin(String name, int totalWork) {
		if (!closed) {
			this.taskName = name;
			this.totalWork = totalWork;
			doBegin(name, totalWork, parentMonitor == null);
			startTime = System.nanoTime();
		}
	}
	
	/**
	 * Invoked when a task begins, to be overridden by subclasses. This
	 * implementation does nothing.
	 * 
	 * @param name task name
	 * @param totalWork total amount of work for the new task
	 * @param topInstance if true, this progress monitor is the top instance
	 */
	protected void doBegin(String name, int totalWork, boolean topInstance) {
	}

	/* (non-Javadoc)
	 * @see de.cau.cs.kieler.core.alg.IKielerProgressMonitor#done()
	 */
	public final void done() {
		if (!closed) {
			totalTime = (System.nanoTime() - startTime) * 1e-9;
			if (completedWork < totalWork)
				internalWorked(totalWork - completedWork);
			closed = true;
		}
	}
	
	/**
	 * Invoked when a task ends, to be overridden by subclasses. This
	 * implementation does nothing.
	 * 
	 * @param topInstance if true, this progress monitor is the top instance
	 */
	protected void doDone(boolean topInstance) {
	}

	/* (non-Javadoc)
	 * @see de.cau.cs.kieler.core.alg.IKielerProgressMonitor#getExecutionTime()
	 */
	public final double getExecutionTime() {
		return totalTime;
	}

	/* (non-Javadoc)
	 * @see de.cau.cs.kieler.core.alg.IKielerProgressMonitor#getSubMonitors()
	 */
	public final List<IKielerProgressMonitor> getSubMonitors() {
		return children;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.core.alg.IKielerProgressMonitor#getParentMonitor()
	 */
	public final IKielerProgressMonitor getParentMonitor() {
		return parentMonitor;
	}

	/* (non-Javadoc)
	 * @see de.cau.cs.kieler.core.alg.IKielerProgressMonitor#getTaskName()
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

	/* (non-Javadoc)
	 * @see de.cau.cs.kieler.core.alg.IKielerProgressMonitor#subTask(int)
	 */
	public final IKielerProgressMonitor subTask(int work) {
		if (!closed) {
			BasicProgressMonitor subMonitor = doSubTask(work);
			children.add(subMonitor);
			subMonitor.parentMonitor = this;
			currentChildWork = work;
			return subMonitor;
		}
		else return null;
	}
	
	/**
	 * Invoked when a sub-task is created, to be overridden by subclasses.
	 * This implementation creates a new {@code BasicProgressMonitor} instance.
	 * 
	 * @param work amount of work that is completed in the current monitor
	 *     instance when the sub-task ends
	 * @return a new progress monitor instance
	 */
	protected BasicProgressMonitor doSubTask(int work) {
	    return new BasicProgressMonitor();
	}

	/* (non-Javadoc)
	 * @see de.cau.cs.kieler.core.alg.IKielerProgressMonitor#worked(int)
	 */
	public void worked(int work) {
		if (work > 0 && !closed)
			internalWorked(work);
	}
	
	/**
	 * Sets the current work counters of this monitor and all
	 * parent monitors.
	 * 
	 * @param work amount of work that has been completed
	 */
	private void internalWorked(float work) {
		if (totalWork > 0 && completedWork < totalWork) {
			completedWork += work;
			doWorked(work, completedWork, parentMonitor == null);
			if (parentMonitor != null && parentMonitor.currentChildWork > 0) {
				 parentMonitor.internalWorked(work / totalWork
						 * parentMonitor.currentChildWork);
			}
		}
	}
	
	/**
	 * Invoked when work is done for this progress monitor, to be overridden
	 * by subclasses. This implementation does nothing.
	 * 
	 * @param work amount of work that has just been done
	 * @param completedWork total number of work that is done for this task
	 * @param topInstance if true, this progress monitor is the top instance
	 */
	protected void doWorked(float work, float completedWork,
	        boolean topInstance) {
	}

}
