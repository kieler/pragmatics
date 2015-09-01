/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.gwt.linker;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.linker.DirectInstallLinker;

/**
 * Customize the original linker of GWT to allow to use our library 
 * within a Chrome Packaged App.
 *  
 * @author uru
 */
public class CustomLinker extends DirectInstallLinker {

    @Override
    protected String getJsInstallLocation(final LinkerContext context) {
        return "com/google/gwt/core/ext/linker/impl/installLocationMainWindow.js";
    } 
    
}
