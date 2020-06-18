/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.graphs.klighd.ui;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;

import de.cau.cs.kieler.formats.GraphFormatsService;

/**
 * Tests for the 'Open General Graphs Handler' whether kieler.formats 
 * supports the selected graph file, e.g. .gml or .kgx. 
 * 
 * @author uru
 */
public class SupportedGraphFormatPropertyTester extends PropertyTester {

    /**
     * {@inheritDoc}
     */
    public boolean test(final Object receiver, final String property, final Object[] args,
            final Object expectedValue) {
        if (receiver instanceof IFile) {
            return GraphFormatsService.getInstance().getFormatDataBySuffix(
                    ((IFile) receiver).getFileExtension()) != null;
        }
        
        return false;
    }
}
