/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 - 2022 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.pragmatics.language.server

import com.google.inject.Guice
import de.cau.cs.kieler.kgraph.text.ide.KGraphLSSetup
import de.cau.cs.kieler.klighd.lsp.launch.ILanguageRegistration
import org.eclipse.elk.graph.json.text.ElkGraphJsonRuntimeModule
import org.eclipse.elk.graph.json.text.ElkGraphJsonStandaloneSetupGenerated
import org.eclipse.elk.graph.json.text.ide.ElkGraphJsonIdeModule
import org.eclipse.elk.graph.text.ElkGraphRuntimeModule
import org.eclipse.elk.graph.text.ElkGraphStandaloneSetup
import org.eclipse.elk.graph.text.ide.ElkGraphIdeModule
import org.eclipse.xtext.util.Modules2

/**
 * Binds and registers the KGraph and ElkGraph languages.
 * 
 * @author sdo, nre
 */
class PragmaticsLanguageRegistration implements ILanguageRegistration {
    
    override bindAndRegisterLanguages() {        
        KGraphLSSetup.doLSSetup
        new ElkGraphStandaloneSetup {
            
            override createInjector() {
                Guice.createInjector(Modules2.mixin(new ElkGraphRuntimeModule, new ElkGraphIdeModule))
            }
            
        }.createInjectorAndDoEMFRegistration
        
        new ElkGraphJsonStandaloneSetupGenerated {
            override createInjector() {
                Guice.createInjector(Modules2.mixin(new ElkGraphJsonRuntimeModule, new ElkGraphJsonIdeModule))
            }
        }.createInjectorAndDoEMFRegistration
    }
}