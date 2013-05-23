/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.kdiagram.ui.wizard;

import java.util.List;

import org.eclipse.xtext.ui.wizard.IProjectCreator;

import de.cau.cs.kieler.core.krendering.KRenderingPlugin;
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensionsPlugin;
import de.cau.cs.kieler.klighd.KlighdPlugin;

/**
 * Customized {@link IProjectCreator} contributing the list of required bundles.
 * 
 * @author chsch
 */
public class CustomizedKDiagramProjectCreator extends KDiagramProjectCreator {

    protected List<String> getRequiredBundles() {
        List<String> result = super.getRequiredBundles();
        result.add(DSL_GENERATOR_PROJECT_NAME);
        result.add(KRenderingPlugin.PLUGIN_ID);
        result.add(KRenderingExtensionsPlugin.PLUGIN_ID);
        result.add(KlighdPlugin.PLUGIN_ID);
        result.add("org.eclipse.xtext.xbase.lib");
        return result;
    }

}
