/******************************************************************************
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
 ******************************************************************************/

package de.cau.cs.kieler.ksbase.viewmanagement.preferences;

/**
 * The class TableData implements a single data entry that represents a variable
 * or signal within the data table. If it is a signal, then it can be present or
 * absent. An entry always contains of a key and a value String expression.
 * 
 * @author Christian Motika - cmot AT informatik.uni-kiel.de changed Michael
 *         Matzen - mim AT informatik.uni-kiel.de
 */
public class TableData {

    /** The parent TableDataList the entry contains to. */
    private TableDataList tableDataList;

    private boolean effectActive;

    /** The key of the entry. */
    private String effectName;

    /** The effects priority */
    private int priority;

    /**
     * Gets the parent TableDataList.
     * 
     * @return the parent TableDataList
     */
    public TableDataList getParentTableDataList() {
	return tableDataList;
    }

    /**
     * Instantiates a new TableData entry.
     * 
     * @param parentTableDataList
     *            the parent TableDataList
     * @param effectActive
     *            Is the value initially activated?
     * @param effectName
     *            Name of the effect
     * @param priority
     *            Initial priority.
     */
    public TableData(TableDataList parentTableDataList, boolean effectActive,
	    String effectName, int priority) {
	this.tableDataList = parentTableDataList;
	this.effectActive = effectActive;
	this.priority = priority;
	this.effectName = effectName;

    }

    /**
     * Gets the name of the effect.
     * 
     * @return The name of the effect
     */
    public String getEffectName() {
	return this.effectName;
    }

    /**
     * Gets the priority of the entry.
     * 
     * @return the priority value
     */
    public int getPriority() {
	return priority;
    }

    /**
     * Sets the priority value of this entry.
     * 
     * @param priority
     *            The new priority
     */
    public void setPriority(int priority) {
	this.priority = priority;
    }

    /**
     * Sets the key of the entry.
     * 
     * @param effectName
     *            the new key
     * 
     * @throws Exception
     *             the exception if the key already exists in the parent
     *             TableDataList
     */
    public void setKey(String effectName) throws Exception {
	// only set the key if unique
	if (this.tableDataList.containsOther(effectName, this))
	    throw new Exception("The key '" + effectName + "' already exists!");
	this.effectName = effectName;
    }

    /**
     * Sets the present flag (for signal entries only).
     * 
     * @param active
     *            the new present
     */
    public void setEffectActive(boolean active) {
	this.effectActive = active;
    }

    /**
     * Checks whether the signal is present (for signal entries only).
     * 
     * @return true, if signal is present
     */
    public boolean isEffectActive() {
	// return present flag if signal AND present
	return (this.effectActive);
    }
}
