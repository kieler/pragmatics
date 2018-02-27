/*
 * generated by Xtext
 */
package de.cau.cs.kieler.kiml.config.text;

import org.eclipse.elk.graph.text.conversion.ElkGraphValueConverterService;
import org.eclipse.elk.graph.text.naming.ElkGraphQualifiedNameProvider;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.naming.IQualifiedNameProvider;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class LayoutConfigRuntimeModule extends de.cau.cs.kieler.kiml.config.text.AbstractLayoutConfigRuntimeModule {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends IValueConverterService> bindIValueConverterService() {
        return ElkGraphValueConverterService.class;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
        return ElkGraphQualifiedNameProvider.class;
    }
    
}