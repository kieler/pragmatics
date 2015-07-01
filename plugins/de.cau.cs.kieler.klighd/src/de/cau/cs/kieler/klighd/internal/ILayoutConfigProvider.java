/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.internal;

import de.cau.cs.kieler.kiml.config.ILayoutConfig;

/**
 * In interface of classes contributing an {@link ILayoutConfig}.<br>
 * It is currently implemented by the {@link de.cau.cs.kieler.klighd.IDiagramWorkbenchPart
 * IDiagramWorkbenchParts} in <code>de.cau.cs.kieler.klighd.ui</code>. This interface is separated
 * from {@link de.cau.cs.kieler.klighd.IDiagramWorkbenchPart IDiagramWorkbenchPart} since it
 * describes an orthogonal aspect and is used for internal purposes only.
 *
 * @author chsch
 *
 * @kieler.design proposed by chsch
 * @kieler.rating proposed yellow by chsch
 */
public interface ILayoutConfigProvider {

    /**
     * Getter.
     *
     * @return the {@link ILayoutConfig} provided by the implementing class
     */
    ILayoutConfig getLayoutConfig();

    /**
     * Drops all layout configurations in the employed {@link ILayoutConfig} and performs a
     * subsequent layout run.
     */
    void resetLayoutConfig();

    /**
     * Drops all layout configurations in the employed {@link ILayoutConfig}.
     *
     * @param doLayout
     *            if <code>true</code> a subsequent layout run will be triggered
     */
    void resetLayoutConfig(boolean doLayout);
}