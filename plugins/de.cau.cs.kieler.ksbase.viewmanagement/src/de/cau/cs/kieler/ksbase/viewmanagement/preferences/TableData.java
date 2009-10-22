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
 * The class TableData implements a single data entry that represents
 * a variable or signal within the data table. If it is a signal, then
 * it can be present or absent. An entry always contains of a key
 * and a value String expression.
 * 
 * @author Christian Motika - cmot AT informatik.uni-kiel.de
 * @changed nbe
 */
public class TableData {

	/** The parent TableDataList the entry contains to. */
	private TableDataList tableDataList;
	

	private boolean effectActive;
	
	/** The key of the entry. */
	private String  effectName;
	
	/** The effects priority */
	private int priority;
	
	
	
	//-------------------------------------------------------------------------

	/**
	 * Gets the parent TableDataList.
	 * 
	 * @return the parent TableDataList
	 */
	public TableDataList getParentTableDataList() {
		return tableDataList;
	}
	
	//-------------------------------------------------------------------------

	/**
	 * Instantiates a new TableData entry.
	 * 
	 * @param parentTableDataList the parent TableDataList
	 */
//	public TableData(TableDataList parentTableDataList) {
//		this.tableDataList = parentTableDataList;
//		this.comboActive = false;
//			this.comboName = "";
//	}
	
	//-------------------------------------------------------------------------

	/**
	 * Instantiates a new TableData entry.
	 * 
	 * @param parentTableDataList the parent TableDataList
	 * @param key the key of the entry
	 */
//	public TableData(TableDataList parentTableDataList, 
//					 String key) {
//		this.tableDataList = parentTableDataList;
//		this.comboActive = false;
//		
//		this.comboName = key;
//
//	}
	
	//-------------------------------------------------------------------------

	/**
	 * Instantiates a new TableData entry.
	 * 
	 * @param parentTableDataList the parent TableDataList
	 * @param key the key of the entry
	 * @param value the value of the entry
	 */
	public TableData(TableDataList parentTableDataList,boolean effectActive, 
			         String effectName, int priority
			         ) {
		this.tableDataList = parentTableDataList;
		this.effectActive = effectActive;
		this.priority = priority;
		this.effectName = effectName;
	
	}
	
	//-------------------------------------------------------------------------

	/**
	 * Instantiates a new TableData.
	 * 
	 * @param parentTableDataList the parent TableDataList
	 * @param present the present flag of the entry
	 * @param key the key of the entry
	 * @param value the value of the entry
	 */
//	public TableData(TableDataList parentTableDataList, 
//					 boolean present, 
//					 String key, 
//					 String value) {
//		this.tableDataList = parentTableDataList;
//		this.comboActive = present;
//		this.comboName = key;
//
//	}

	//-------------------------------------------------------------------------

	/**
	 * Instantiates a new TableData.
	 * 
	 * @param parentTableDataList the parent TableDataList
	 * @param present the present flag of the entry
	 * @param signal the signal flag of the entry
	 * @param key the key of the entry
	 * @param value the value of the entry
	 */
//	public TableData(TableDataList parentTableDataList, 
//			 boolean present, 
//			 boolean signal,
//			 String key, 
//			 String value) {
//		this.tableDataList = parentTableDataList;
//		this.comboActive = present;
//
//		this.comboName = key;
//
//	}
	
	//-------------------------------------------------------------------------

	/**
	 * Gets the value of the entry.
	 * 
	 * @return the value
	 */
//	public String getValue() {
//		return this.value;
//	}
	
	//-------------------------------------------------------------------------

	/**
	 * Gets the key of the entry.
	 * 
	 * @return the key
	 */
	public String getEffectName() {
		return this.effectName;
	}

	//-------------------------------------------------------------------------

	/**
	 * Sets the value of the entry.
	 * 
	 * @param value the new value
	 */
//	public void setValue(String value) {
//		this.value = value;
//	}
//	
	//-------------------------------------------------------------------------

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Sets the key of the entry.
	 * 
	 * @param comboName the new key
	 * 
	 * @throws Exception the exception if the key already exists in the parent
	 * 			         TableDataList
	 */
	public void setKey(String effectName) throws Exception {
		//only set the key if unique
		if (this.tableDataList.containsOther(effectName, this))
			throw new Exception("The key '"+effectName+"' already exists!");
		this.effectName = effectName;
	}
//	
	//-------------------------------------------------------------------------

	/**
	 * Sets the present flag (for signal entries only).
	 * 
	 * @param present the new present
	 */
	public void setComboActive(boolean active) {
		this.effectActive = active;
	}
	
	//-------------------------------------------------------------------------

	/**
	 * Checks whether the signal is present (for signal entries only).
	 * 
	 * @return true, if signal is present
	 */
	public boolean isComboActive() {
		//return present flag if signal AND present 
		return (this.effectActive);
	}

	//-------------------------------------------------------------------------

	/**
	 * Sets the signal flag of an entry.
	 * 
	 * @param signal true, if entry is a signal
	 */
//	public void setSignal(boolean signal) {
//		this.signal = signal;
//	}
	
	//-------------------------------------------------------------------------

	/**
	 * Checks whether the entry is a signal.
	 * 
	 * @return true, if entry is a signal
	 */
//	public boolean isSignal() {
//		//return signal 
//		return this.signal;
//	}
//	
	//-------------------------------------------------------------------------

	/**
	 * Sets the modified flag of an entry.
	 * 
	 * @param modified true, if entry was modified
	 */
//	public void setModified(boolean modified) {
//		this.modified = modified;
//	}
	
	//-------------------------------------------------------------------------

	/**
	 * Checks whether entry is flagged as modified.
	 * 
	 * @return true, entry is modified
	 */
//	public boolean isModified() {
//		//return modified flag if modified OR permanent 
//		return (this.modified || this.permanent);
//	}

	//-------------------------------------------------------------------------

	/**
	 * Sets the permanent flag of an entry.
	 * 
	 * @param permanent true, if entry is permanent
	 */
//	public void setPermanent(boolean permanent) {
//		this.permanent = permanent;
//	}
//	
	//-------------------------------------------------------------------------

	/**
	 * Checks whether entry is flagged as permanent.
	 * 
	 * @return true, if entry is permanent
	 */
//	public boolean isPermanent() {
//		return this.permanent;
//	}
	
}
