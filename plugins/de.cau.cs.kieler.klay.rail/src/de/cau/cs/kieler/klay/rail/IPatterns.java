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
 * Interface for all possible pattern cases between two rail nodes. Implementation has to return a
 * position to place the next node to and can use the position of the origin node and a list of
 * occupied positions in it's layer.
 * 
 * @author jjc
 * 
 */
public interface IPatterns {

    // CHECKSTYLEOFF javadoc
    // Cases are self-explanatory and Javadoc comments would be redundant

    // Breach to X cases

    int breachBreach(List<Integer> occupiedPlaces, int origin);

    int breachSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);

    int breachSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);

    int breachSwitchLeftStump(List<Integer> occupiedPlaces, int origin);

    int breachSwitchRightBranch(List<Integer> occupiedPlaces, int origin);

    int breachSwitchRightStraight(List<Integer> occupiedPlaces, int origin);

    int breachSwitchRightStump(List<Integer> occupiedPlaces, int origin);

    // Switch left up to X cases

    int switchLeftBranchBreach(List<Integer> occupiedPlaces, int origin);

    int switchLeftBranchSwitchRightBranch(List<Integer> occupiedPlaces, int origin);

    int switchLeftBranchSwitchRightStraight(List<Integer> occupiedPlaces, int origin);

    int switchLeftBranchSwitchRightStump(List<Integer> occupiedPlaces, int origin);

    int switchLeftBranchSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);

    int switchLeftBranchSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);

    int switchLeftBranchSwitchLeftStump(List<Integer> occupiedPlaces, int origin);

    // Switch left down to X cases

    int switchLeftStraightBreach(List<Integer> occupiedPlaces, int origin);

    int switchLeftStraightSwitchRightBranch(List<Integer> occupiedPlaces, int origin);

    int switchLeftStraightSwitchRightStraight(List<Integer> occupiedPlaces, int origin);

    int switchLeftStraightSwitchRightStump(List<Integer> occupiedPlaces, int origin);

    int switchLeftStraightSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);

    int switchLeftStraightSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);

    int switchLeftStraightSwitchLeftStump(List<Integer> occupiedPlaces, int origin);

    // Switch left stump to X cases

    int switchLeftStumpBreach(List<Integer> occupiedPlaces, int origin);

    int switchLeftStumpSwitchRightBranch(List<Integer> occupiedPlaces, int origin);

    int switchLeftStumpSwitchRightStraight(List<Integer> occupiedPlaces, int origin);

    int switchLeftStumpSwitchRightStump(List<Integer> occupiedPlaces, int origin);

    int switchLeftStumpSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);

    int switchLeftStumpSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);

    int switchLeftStumpSwitchLeftStump(List<Integer> occupiedPlaces, int origin);

    // Switch right up to X cases

    int switchRightBranchBreach(List<Integer> occupiedPlaces, int origin);

    int switchRightBranchSwitchRightBranch(List<Integer> occupiedPlaces, int origin);

    int switchRightBranchSwitchRightStraight(List<Integer> occupiedPlaces, int origin);

    int switchRightBranchSwitchRightStump(List<Integer> occupiedPlaces, int origin);

    int switchRightBranchSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);

    int switchRightBranchSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);

    int switchRightBranchSwitchLeftStump(List<Integer> occupiedPlaces, int origin);

    // Switch right down to X cases

    int switchRightStraightBreach(List<Integer> occupiedPlaces, int origin);

    int switchRightStraightSwitchRightBranch(List<Integer> occupiedPlaces, int origin);

    int switchRightStraightSwitchRightStraight(List<Integer> occupiedPlaces, int origin);

    int switchRightStraightSwitchRightStump(List<Integer> occupiedPlaces, int origin);

    int switchRightStraightSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);

    int switchRightStraightSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);

    int switchRightStraightSwitchLeftStump(List<Integer> occupiedPlaces, int origin);

    // Switch right stump to X cases

    int switchRightStumpBreach(List<Integer> occupiedPlaces, int origin);

    int switchRightStumpSwitchRightBranch(List<Integer> occupiedPlaces, int origin);

    int switchRightStumpSwitchRightStraight(List<Integer> occupiedPlaces, int origin);

    int switchRightStumpSwitchRightStump(List<Integer> occupiedPlaces, int origin);

    int switchRightStumpSwitchLeftBranch(List<Integer> occupiedPlaces, int origin);

    int switchRightStumpSwitchLeftStraight(List<Integer> occupiedPlaces, int origin);

    int switchRightStumpSwitchLeftStump(List<Integer> occupiedPlaces, int origin);

    // Both to both special cases

    int switchLeftBothSwitchRightBoth(List<Integer> occupiedPlaces, int origin);

    int switchRightBothSwitchLeftBoth(List<Integer> occupiedPlaces, int origin);

}
