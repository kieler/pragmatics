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
package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.graphiti.diagram.StyleProvider;

/**
 * 
 * @author atr
 * Class used to change the color of the entity
 */
public class ChangeColorEntityFeature extends AbstractCustomFeature {

    /** the style provider. */ 
    private StyleProvider styleProvider;
    private boolean background;
    
    /**
     *
     * @param backgrond . 
     * @param fp
     * Constructor.
     */
    public ChangeColorEntityFeature(final IFeatureProvider fp, final StyleProvider thestyleProvider,
            final boolean backgrond) {
        super(fp);
        this.styleProvider = thestyleProvider;
        this.background = backgrond;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        String colorType = background ? "&background" : "&foreground";
        return "Change " + colorType + " color";
    }

    
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        String colorType = background ? "background" : "foreground";
        return "Change the " + colorType + " color";
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canExecute(final ICustomContext context) {
        PictogramElement[] pes = context.getPictogramElements();
        if (pes == null || pes.length == 0) { // nothing selected
            return false;
        }

        // return true, if all elements are Entities
        
        for (PictogramElement pe : pes) {

            final Object bo = getBusinessObjectForPictogramElement(pe);
            if (!(bo instanceof Entity)) {
                return false;
            }
        }
        return true;
    }

 
    /**
     * 
     * {@inheritDoc}
     */
    public void execute(final ICustomContext context) {

//        Style style = StyleUtil.getStyleForEClass(getDiagram());
//         // let the user choose the new color
//        Color currentColor;
//        if (background) {
//            currentColor = style.getBackground();
//        } else {
//            currentColor = style.getForeground();
//        }
//        Color newColor = SampleUtil.editColor(currentColor);
//
//        if (newColor == null) { // user did not choose new color
//            return;
//        }
//
//
//       // set new color
//        if (background) {
//            style.setBackground(newColor);
//        } else {
//            style.setForeground(newColor);
//
//        }

    }

}
