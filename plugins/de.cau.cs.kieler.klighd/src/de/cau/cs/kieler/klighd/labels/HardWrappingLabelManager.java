/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.labels;

import org.eclipse.swt.graphics.FontData;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.klighd.microlayout.PlacementUtil;

/**
 * Label manager which hard wraps the text so it fits a certain width. Hard wrapping means that a new
 * line is inserted after the last character that still fits into the target width, regardless of
 * whether the line break interrupts a word or not.
 * 
 * @author ybl
 */
public class HardWrappingLabelManager extends AbstractKlighdLabelManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public String resizeLabel(final KLabel label, final double targetWidth) {
        final FontData font = PlacementUtil.fontDataFor(label);

        if (PlacementUtil.estimateTextSize(font, label.getText()).getWidth() > targetWidth) {
            String textWithoutLineBreaks = label.getText().replace("\n", " ");
            String restText = textWithoutLineBreaks;
            StringBuilder resultText = new StringBuilder(label.getText().length());

            while (restText.length() != 0) {
                // Delete surrounding whitespace
                restText = restText.trim();
                
                // Find the part of the rest of the string which fits the line
                String fittingString = LabelManagementUtil.findFittingString(
                        restText, font, targetWidth);

                // Break if the targetWidth is too small to find something
                if (fittingString.equals("")) {
                    return "";
                }
                
                resultText.append(fittingString).append("\n");
                restText = restText.substring(fittingString.length());
            }
            
            // Delete last \n
            return resultText.substring(0, resultText.length() - 1);
        }
        
        return null;
    }

}
