/*
 * generated by Xtext
 */
package de.cau.cs.kieler.uml.sequence.text.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import de.cau.cs.kieler.uml.sequence.text.ui.internal.SequenceActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class SequenceExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return SequenceActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return SequenceActivator.getInstance().getInjector(SequenceActivator.DE_CAU_CS_KIELER_UML_SEQUENCE_TEXT_SEQUENCE);
	}
	
}
