/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019, 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.pragmatics.language.server

import de.cau.cs.kieler.klighd.lsp.KGraphLanguageClient
import de.cau.cs.kieler.klighd.lsp.interactive.layered.LayeredInteractiveLanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.interactive.rectpacking.RectpackingInteractiveLanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.launch.AbstractLsCreator

/** 
 * Provides methods to create a LS.
 * This involves binding of modules and creating, starting, and configure logging for an LS.
 * 
 * @author sdo, nre
 */
class PragmaticsLsCreator extends AbstractLsCreator {
    
    LayeredInteractiveLanguageServerExtension constraints
    
    RectpackingInteractiveLanguageServerExtension rectPack
    
    override getLanguageServerExtensions() {
        constraints = injector.getInstance(LayeredInteractiveLanguageServerExtension)
        rectPack = injector.getInstance(RectpackingInteractiveLanguageServerExtension)
        val iLanguageServerExtensions = newArrayList(
            injector.getInstance(PragmaticsRegistrationLanguageServerExtension), constraints, rectPack
        )
        return iLanguageServerExtensions
    }
    
    override getRemoteInterface() {
        KGraphLanguageClient
    }
    
    override onConnect() {
        constraints.client = languageClient as KGraphLanguageClient
        rectPack.client = languageClient as KGraphLanguageClient
    }
    
}
