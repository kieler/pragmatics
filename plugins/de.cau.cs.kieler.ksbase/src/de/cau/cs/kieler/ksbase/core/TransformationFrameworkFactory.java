/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.core;

import de.cau.cs.kieler.ksbase.core.xtend.XtendTransformationFramework;

/**
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 * @kieler.rating 2009-12-15 proposed yellow 
 */
public final class TransformationFrameworkFactory {

    private TransformationFrameworkFactory() {

    }

    /**
     * Returns the current default {@link ITransformationFramework}.
     * 
     * 
     * @return An {@link XtendTransformationFramework}
     */
    public static ITransformationFramework getDefaultTransformationFramework() {
        return new XtendTransformationFramework();
    }
}
