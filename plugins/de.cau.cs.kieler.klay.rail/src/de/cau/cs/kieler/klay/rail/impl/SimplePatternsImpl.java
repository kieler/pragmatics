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
package de.cau.cs.kieler.klay.rail.impl;

import java.util.List;

import de.cau.cs.kieler.klay.rail.IPatterns;

public class SimplePatternsImpl implements IPatterns {

    public int breachBreach(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchLeftBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin - 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchLeftStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchLeftStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchRightBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchRightStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchRightStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftBranchBreach(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin - 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchRightBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchRightStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchRightStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin - 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchLeftBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchLeftStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchLeftStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin - 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightBreach(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchRightBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchRightStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchRightStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchLeftBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchLeftStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchLeftStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchLeftStumpBreach(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchRightBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchRightStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchRightStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchLeftBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchLeftStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchLeftStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchBreach(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchRightBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchRightStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchRightStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchLeftBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchLeftStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchLeftStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightBreach(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchRightBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchRightStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchRightStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchLeftBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchLeftStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchLeftStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpBreach(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchRightBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchRightStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchRightStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchLeftBranch(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchLeftStraight(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchLeftStump(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftBothSwitchRightBoth(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBothSwitchLeftBoth(List<Integer> occupiedPlaces, int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

}
