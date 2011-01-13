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
    
    public int breachSwitchLeftUp();
    
    public int breachSwitchLeftDown();
    
    public int breachSwitchLeftStump();
    
    public int breachSwitchRightUp();
    
    public int breachSwitchRightDown();
    
    public int breachSwitchRightStump();
    
    // Switch left up to X cases
    
    public int switchLeftUpBreach();
    
    public int switchLeftUpSwitchRightUp();
    
    public int switchLeftUpSwitchRightDown();
    
    public int switchLeftUpSwitchRightStump();
    
    public int switchLeftUpSwitchLeftUp();
    
    public int switchLeftUpSwitchLeftDown();
    
    public int switchLeftUpSwitchLeftStump();
    
    // Switch left down to X cases
    
    public int switchLeftDownBreach();
    
    public int switchLeftDownSwitchRightUp();
    
    public int switchLeftDownSwitchRightDown();
    
    public int switchLeftDownSwitchRightStump();
    
    public int switchLeftDownSwitchLeftUp();
    
    public int switchLeftDownSwitchLeftDown();
    
    public int switchLeftDownSwitchLeftStump();
    
    // Switch left stump to X cases
    
    public int switchLeftStumpBreach();
    
    public int switchLeftStumpSwitchRightUp();
    
    public int switchLeftStumpSwitchRightDown();
    
    public int switchLeftStumpSwitchRightStump();
    
    public int switchLeftStumpSwitchLeftUp();
    
    public int switchLeftStumpSwitchLeftDown();
    
    public int switchLeftStumpSwitchLeftStump();
    
    // Switch right up to X cases
    
    public int switchRightUpBreach();
    
    public int switchRightUpSwitchRightUp();
    
    public int switchRightUpSwitchRightDown();
    
    public int switchRightUpSwitchRightStump();
    
    public int switchRightUpSwitchLeftUp();
    
    public int switchRightUpSwitchLeftDown();
    
    public int switchRightUpSwitchLeftStump();
    
    // Switch right down to X cases
    
    public int switchRightDownBreach();
    
    public int switchRightDownSwitchRightUp();
    
    public int switchRightDownSwitchRightDown();
    
    public int switchRightDownSwitchRightStump();
    
    public int switchRightDownSwitchLeftUp();
    
    public int switchRightDownSwitchLeftDown();
    
    public int switchRightDownSwitchLeftStump();
    
    // Switch right stump to X cases
    
    public int switchRightStumpBreach();
    
    public int switchRightStumpSwitchRightUp();
    
    public int switchRightStumpSwitchRightDown();
    
    public int switchRightStumpSwitchRightStump();
    
    public int switchRightStumpSwitchLeftUp();
    
    public int switchRightStumpSwitchLeftDown();
    
    public int switchRightStumpSwitchLeftStump();
    
    // Both to both special cases
    
    public int switchLeftBothSwitchRightBoth();
    
    public int switchRightBothSwitchLeftBoth();
    

    
}
