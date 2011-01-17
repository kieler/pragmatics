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

/**
 * 
 * @author jjc
 *
 */
public interface IPatterns {

    // Breach to X cases
    
    public int breachBreach();
    
    public int breachSwitchLeftBranch();
    
    public int breachSwitchLeftStraight();
    
    public int breachSwitchLeftStump();
    
    public int breachSwitchRightBranch();
    
    public int breachSwitchRightStraight();
    
    public int breachSwitchRightStump();
    
    // Switch left up to X cases
    
    public int switchLeftBranchBreach();
    
    public int switchLeftBranchSwitchRightBranch();
    
    public int switchLeftBranchSwitchRightStraight();
    
    public int switchLeftBranchSwitchRightStump();
    
    public int switchLeftBranchSwitchLeftBranch();
    
    public int switchLeftBranchSwitchLeftStraight();
    
    public int switchLeftBranchSwitchLeftStump();
    
    // Switch left down to X cases
    
    public int switchLeftStraightBreach();
    
    public int switchLeftStraightSwitchRightBranch();
    
    public int switchLeftStraightSwitchRightStraight();
    
    public int switchLeftStraightSwitchRightStump();
    
    public int switchLeftStraightSwitchLeftBranch();
    
    public int switchLeftStraightSwitchLeftStraight();
    
    public int switchLeftStraightSwitchLeftStump();
    
    // Switch left stump to X cases
    
    public int switchLeftStumpBreach();
    
    public int switchLeftStumpSwitchRightBranch();
    
    public int switchLeftStumpSwitchRightStraight();
    
    public int switchLeftStumpSwitchRightStump();
    
    public int switchLeftStumpSwitchLeftBranch();
    
    public int switchLeftStumpSwitchLeftStraight();
    
    public int switchLeftStumpSwitchLeftStump();
    
    // Switch right up to X cases
    
    public int switchRightBranchBreach();
    
    public int switchRightBranchSwitchRightBranch();
    
    public int switchRightBranchSwitchRightStraight();
    
    public int switchRightBranchSwitchRightStump();
    
    public int switchRightBranchSwitchLeftBranch();
    
    public int switchRightBranchSwitchLeftStraight();
    
    public int switchRightBranchSwitchLeftStump();
    
    // Switch right down to X cases
    
    public int switchRightStraightBreach();
    
    public int switchRightStraightSwitchRightBranch();
    
    public int switchRightStraightSwitchRightStraight();
    
    public int switchRightStraightSwitchRightStump();
    
    public int switchRightStraightSwitchLeftBranch();
    
    public int switchRightStraightSwitchLeftStraight();
    
    public int switchRightStraightSwitchLeftStump();
    
    // Switch right stump to X cases
    
    public int switchRightStumpBreach();
    
    public int switchRightStumpSwitchRightBranch();
    
    public int switchRightStumpSwitchRightStraight();
    
    public int switchRightStumpSwitchRightStump();
    
    public int switchRightStumpSwitchLeftBranch();
    
    public int switchRightStumpSwitchLeftStraight();
    
    public int switchRightStumpSwitchLeftStump();
    
    // Both to both special cases
    
    public int switchLeftBothSwitchRightBoth();
    
    public int switchRightBothSwitchLeftBoth();
    

    
}
