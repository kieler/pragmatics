/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.config.text.ui

import com.google.inject.Guice
import com.google.inject.Module
import de.cau.cs.kieler.config.text.ui.internal.TextActivator
import org.apache.log4j.Logger
import org.eclipse.xtext.util.Modules2
import de.cau.cs.kieler.config.text.ide.LayoutConfigIdeModule

/**
 *
 */
class LayoutConfigUiActivator extends TextActivator {
    
    protected def Module getIdeModule(String grammar) {
        if (grammar == DE_CAU_CS_KIELER_CONFIG_TEXT_LAYOUTCONFIG) {
            return new LayoutConfigIdeModule
        }
        throw new IllegalArgumentException(grammar)
    }
    
    override protected createInjector(String language) {
        try {
            val runtimeModule = getRuntimeModule(language)
            val ideModule = getIdeModule(language)
            val sharedStateModule = getSharedStateModule()
            val uiModule = getUiModule(language)
            val mergedModule = Modules2.mixin(runtimeModule, ideModule, sharedStateModule, uiModule)
            return Guice.createInjector(mergedModule)
        } catch (Exception e) {
            val logger = Logger.getLogger(LayoutConfigUiActivator)
            logger.error("Failed to create injector for " + language, e)
            throw new RuntimeException("Failed to create injector for " + language, e)
        }
    }

}
