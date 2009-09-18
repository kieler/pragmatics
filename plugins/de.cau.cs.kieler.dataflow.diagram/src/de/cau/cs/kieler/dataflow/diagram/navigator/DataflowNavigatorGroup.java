/*
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
package de.cau.cs.kieler.dataflow.diagram.navigator;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @generated
 */
public class DataflowNavigatorGroup extends DataflowAbstractNavigatorItem {

    /**
     * @generated
     */
    private String myGroupName;

    /**
     * @generated
     */
    private String myIcon;

    /**
     * @generated
     */
    private Collection myChildren = new LinkedList();

    /**
     * @generated
     */
    DataflowNavigatorGroup(String groupName, String icon, Object parent) {
        super(parent);
        myGroupName = groupName;
        myIcon = icon;
    }

    /**
     * @generated
     */
    public String getGroupName() {
        return myGroupName;
    }

    /**
     * @generated
     */
    public String getIcon() {
        return myIcon;
    }

    /**
     * @generated
     */
    public Object[] getChildren() {
        return myChildren.toArray();
    }

    /**
     * @generated
     */
    public void addChildren(Collection children) {
        myChildren.addAll(children);
    }

    /**
     * @generated
     */
    public void addChild(Object child) {
        myChildren.add(child);
    }

    /**
     * @generated
     */
    public boolean isEmpty() {
        return myChildren.size() == 0;
    }

    /**
     * @generated
     */
    public boolean equals(Object obj) {
        if (obj instanceof de.cau.cs.kieler.dataflow.diagram.navigator.DataflowNavigatorGroup) {
            de.cau.cs.kieler.dataflow.diagram.navigator.DataflowNavigatorGroup anotherGroup = (de.cau.cs.kieler.dataflow.diagram.navigator.DataflowNavigatorGroup) obj;
            if (getGroupName().equals(anotherGroup.getGroupName())) {
                return getParent().equals(anotherGroup.getParent());
            }
        }
        return super.equals(obj);
    }

    /**
     * @generated
     */
    public int hashCode() {
        return getGroupName().hashCode();
    }

}
