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
package de.cau.cs.kieler.klighd.kdiagram.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.wizard.IProjectCreator;

/**
 * Use this class to register components to be used within the IDE.
 */
public class KDiagramUiModule extends de.cau.cs.kieler.klighd.kdiagram.ui.AbstractKDiagramUiModule {
    public KDiagramUiModule(AbstractUIPlugin plugin) {
        super(plugin);
    }

    /**
     * Customized {@link IProjectCreator} contributing the list of required bundles.<br>
     * <br>
     * {@inheritDoc}
     */
    public Class<? extends org.eclipse.xtext.ui.wizard.IProjectCreator> bindIProjectCreator() {
        return de.cau.cs.kieler.klighd.kdiagram.ui.wizard.CustomizedKDiagramProjectCreator.class;
    }

}
