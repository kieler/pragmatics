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
package de.cau.cs.kieler.kivi.examples;

import org.eclipse.draw2d.Label;
import org.eclipse.gef.GraphicalEditPart;

import de.cau.cs.kieler.kivi.core.impl.AbstractEffect;

/**
 * Writes text onto an edit part.
 * 
 * @author mmu
 *
 */
public class TextEffect extends AbstractEffect {

    private String text;
    
    private GraphicalEditPart editPart;
    
    private Label label;
    
    /**
     * Default constructor.
     */
    public TextEffect() {
        
    }
    
    /**
     * Create new text effect.
     * 
     * @param t text to write
     * @param ep the edit part to write on
     */
    public TextEffect(final String t, final GraphicalEditPart ep) {
        text = t;
        editPart = ep;
    }
    
    @Override
    public void execute() {
        label = new Label(text);
        editPart.getFigure().add(label);
    }
    
    @Override
    public void undo() {
        editPart.getFigure().remove(label);
    }

}
