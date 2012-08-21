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
package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;


/**
 * Diagram type provider used to initialize the diagram editor.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class DiagramTypeProvider extends AbstractDiagramTypeProvider {

    private IToolBehaviorProvider[] toolBehaviorProviders;

    /**
     * Constructor to initialize the diagram editor. Initialize the feature provider
     */
    public DiagramTypeProvider() {
        super();
        setFeatureProvider(new FeatureProvider(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAutoUpdateAtRuntime() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAutoUpdateAtStartup() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
        if (toolBehaviorProviders == null) {
            toolBehaviorProviders = new IToolBehaviorProvider[] { new ToolBehaviourProvider(this) };
        }
        return toolBehaviorProviders;
    }

}
