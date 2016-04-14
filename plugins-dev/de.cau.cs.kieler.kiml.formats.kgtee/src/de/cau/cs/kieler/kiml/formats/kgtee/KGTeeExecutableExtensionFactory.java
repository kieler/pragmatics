/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kiml.formats.kgtee;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.Bundle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import de.cau.cs.kieler.kgraph.text.KGraphRuntimeModule;
import de.cau.cs.kieler.kgraph.text.ui.KGraphUiModule;

/**
 * This class is based on the executable extension factory in
 * {@code de.cau.cs.kieler.core.kgraph.text.ui}. It is required to properly instantiate the
 * {@link KGTeeFormatHandler}. For injection reasons.
 */
public class KGTeeExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

    @Override
    protected Bundle getBundle() {
        return KGTeeFormatActivator.getInstance().getBundle();
    }

    @Override
    protected Injector getInjector() {
        Module runtimeModule = new KGraphRuntimeModule();
        Module sharedStateModule = new SharedStateModule();
        Module uiModule = new KGraphUiModule(KGTeeFormatActivator.getInstance());
        Module mergedModule = Modules2.mixin(runtimeModule, sharedStateModule, uiModule);
        return Guice.createInjector(mergedModule);
    }

}
