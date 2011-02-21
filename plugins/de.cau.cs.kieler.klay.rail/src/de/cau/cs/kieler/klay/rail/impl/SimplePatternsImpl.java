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

/**
 * Implementation with simple rules for node placement for railways.
 * 
 * @author jjc
 * 
 */
public class SimplePatternsImpl implements IPatterns {

    // CHECKSTYLEOFF javadoc
    // Cases are self-explanatory and Javadoc comments would be redundant

    public int breachBreach(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchLeftBranch(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin - 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchLeftStraight(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchLeftStump(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchRightBranch(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchRightStraight(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int breachSwitchRightStump(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftBranchBreach(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin - 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchRightBranch(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchRightStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchRightStump(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin - 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchLeftBranch(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchLeftStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchLeftBranchSwitchLeftStump(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin - 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightBreach(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchRightBranch(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchRightStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchRightStump(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchLeftBranch(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchLeftStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStraightSwitchLeftStump(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchLeftStumpBreach(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchRightBranch(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchRightStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchRightStump(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchLeftBranch(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchLeftStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftStumpSwitchLeftStump(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchBreach(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchRightBranch(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchRightStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchRightStump(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess++;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchLeftBranch(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchLeftStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBranchSwitchLeftStump(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin + 1;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightBreach(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchRightBranch(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchRightStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchRightStump(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchLeftBranch(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchLeftStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStraightSwitchLeftStump(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpBreach(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchRightBranch(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchRightStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchRightStump(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchLeftBranch(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchLeftStraight(final List<Integer> occupiedPlaces,
            final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightStumpSwitchLeftStump(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchLeftBothSwitchRightBoth(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

    public int switchRightBothSwitchLeftBoth(final List<Integer> occupiedPlaces, final int origin) {
        int firstGuess = origin;
        while (occupiedPlaces.contains(firstGuess)) {
            firstGuess--;
        }
        return firstGuess;
    }

}
