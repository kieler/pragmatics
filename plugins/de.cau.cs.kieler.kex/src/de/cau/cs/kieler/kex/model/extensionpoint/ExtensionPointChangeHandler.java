package de.cau.cs.kieler.kex.model.extensionpoint;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IFilter;
import org.osgi.framework.BundleContext;

@Deprecated
public class ExtensionPointChangeHandler implements IExtensionChangeHandler {

	private ExtensionTracker tracker;
	private final List<IExecutableExtension> addedExtensions = new ArrayList<IExecutableExtension>();

	public void start(BundleContext context) throws Exception {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IExtensionPoint ep = reg.getExtensionPoint("de.cau.cs.kieler.kex");
		tracker = new ExtensionTracker(reg);

		IFilter filter = ExtensionTracker.createExtensionPointFilter(ep);
		tracker.registerHandler(this, filter);
		IExtension[] extensions = ep.getExtensions();
		for (int i = 0; i < extensions.length; ++i)
			addExtension(tracker, extensions[i]);
	}

	public void stop(BundleContext context) throws Exception {
		if (tracker != null) {
			tracker.close();
			tracker = null;
		}
	}

	public void addExtension(IExtensionTracker tracker, IExtension extension) {
		IConfigurationElement[] configs = extension.getConfigurationElements();
		for (int i = 0; i < configs.length; ++i) {
			// use configuration properties for something
			// ...
			try {
				// TODO hier den richten parameter reinreichen...
				IExecutableExtension exeExtension = (IExecutableExtension) configs[i]
						.createExecutableExtension(configs[i].getName());
				// String name = configs[i].getName();
				// TODO has to be filled
				// exeExtension.setInitializationData(config, propertyName,
				// data)
				addedExtensions.add(exeExtension);
				// register association between object and extension
				// with the tracker
				tracker.registerObject(extension, exeExtension,
						IExtensionTracker.REF_WEAK);
			} catch (CoreException e) {
				// TODO kielerexception schmeissen
				e.printStackTrace();
			}
		}
	}

	public void removeExtension(IExtension extension, Object[] objects) {
		// stop using objects associated with
		// the removed extension
		for (int i = 0; i < objects.length; ++i)
			addedExtensions.remove(objects[i]);
	}

}
