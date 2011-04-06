/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.text.ui;

import de.cau.cs.kieler.core.model.handlers.ConvertModelHandler;

/**
 * A command handler used to convert KAOM models to the textual format.
 *
 * @author msp
 */
public class ConvertKaomModelHandler extends ConvertModelHandler {

    /**
     * Create a command handler for textual format conversion.
     */
    protected ConvertKaomModelHandler() {
        super("kaot");
    }

}
