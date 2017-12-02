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
package de.cau.cs.kieler.grana.text.ui

import com.google.inject.Guice
import com.google.inject.Module
import de.cau.cs.kieler.grana.text.ide.GranaIdeModule
import de.cau.cs.kieler.grana.text.ui.internal.TextActivator
import org.apache.log4j.Logger
import org.eclipse.elk.graph.text.ui.ElkGraphTextUiActivator
import org.eclipse.xtext.util.Modules2

/**
 *
 */
class GranaTextUiActivator extends TextActivator {
    
    public static final String PLUGIN_ID = "de.cau.cs.kieler.grana.text.ui";
    
    protected def Module getIdeModule(String grammar) {
        if (grammar == DE_CAU_CS_KIELER_GRANA_TEXT_GRANA) {
            return new GranaIdeModule
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
            val logger = Logger.getLogger(ElkGraphTextUiActivator)
            logger.error("Failed to create injector for " + language, e)
            throw new RuntimeException("Failed to create injector for " + language, e)
        }
    }
    
}