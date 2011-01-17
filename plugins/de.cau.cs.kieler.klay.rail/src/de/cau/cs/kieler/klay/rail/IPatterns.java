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
package de.cau.cs.kieler.klay.rail;

import java.util.List;

/**
 * 
 * @author jjc
 *
 */
public interface IPatterns {

    // Breach to X cases
    
    public int breachBreach(List<Integer> occupiedPlaces, int origin);
    
    public int breachSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);
    
    public int breachSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);
    
    public int breachSwitchLeftStump(List<Integer> occupiedPlaces, int origin);
    
    public int breachSwitchRightBranch(List<Integer> occupiedPlaces, int origin);
    
    public int breachSwitchRightStraight(List<Integer> occupiedPlaces, int origin);
    
    public int breachSwitchRightStump(List<Integer> occupiedPlaces, int origin);
    
    // Switch left up to X cases
    
    public int switchLeftBranchBreach(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftBranchSwitchRightBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftBranchSwitchRightStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftBranchSwitchRightStump(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftBranchSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftBranchSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftBranchSwitchLeftStump(List<Integer> occupiedPlaces, int origin);
    
    // Switch left down to X cases
    
    public int switchLeftStraightBreach(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStraightSwitchRightBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStraightSwitchRightStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStraightSwitchRightStump(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStraightSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStraightSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStraightSwitchLeftStump(List<Integer> occupiedPlaces, int origin);
    
    // Switch left stump to X cases
    
    public int switchLeftStumpBreach(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStumpSwitchRightBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStumpSwitchRightStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStumpSwitchRightStump(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStumpSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStumpSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchLeftStumpSwitchLeftStump(List<Integer> occupiedPlaces, int origin);
    
    // Switch right up to X cases
    
    public int switchRightBranchBreach(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightBranchSwitchRightBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightBranchSwitchRightStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightBranchSwitchRightStump(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightBranchSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightBranchSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightBranchSwitchLeftStump(List<Integer> occupiedPlaces, int origin);
    
    // Switch right down to X cases
    
    public int switchRightStraightBreach(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStraightSwitchRightBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStraightSwitchRightStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStraightSwitchRightStump(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStraightSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStraightSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStraightSwitchLeftStump(List<Integer> occupiedPlaces, int origin);
    
    // Switch right stump to X cases
    
    public int switchRightStumpBreach(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStumpSwitchRightBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStumpSwitchRightStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStumpSwitchRightStump(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStumpSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStumpSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightStumpSwitchLeftStump(List<Integer> occupiedPlaces, int origin);
    
    // Both to both special cases
    
    public int switchLeftBothSwitchRightBoth(List<Integer> occupiedPlaces, int origin);
    
    public int switchRightBothSwitchLeftBoth(List<Integer> occupiedPlaces, int origin);
    
    
}
