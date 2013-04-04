/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus;

import java.util.Set;

import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.gmf.GmfLayoutConfig;

/**
 * This class overrides the standard GMFLayoutConfig in order to prevent the LayoutOptions from
 * being shown for the blank space around the interaction.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 */
public class PapyrusLayoutConfig extends GmfLayoutConfig {
    @Override
    public void enrich(final LayoutContext context) {
        super.enrich(context);
        if (context.getProperty(LayoutContext.DIAGRAM_PART) != null) {
            if (context.getProperty(LayoutContext.DIAGRAM_PART).getClass().getSimpleName()
                    .equals("PackageEditPart")) {
                // Disable LayoutOptions for the PackageEditPart
                Set<LayoutOptionData.Target> optionTargets = context
                        .getProperty(LayoutContext.OPT_TARGETS);
                optionTargets.clear();
            }
        }
    }
}
