package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.runtime.Activator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IFilter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;

/**
 * can be used for creating new extensions for KEX extension point.<br>
 * So it allows to create new examples or example categories.
 * 
 * @author pkl
 * 
 */
@SuppressWarnings("restriction")
public class ExtPointExampleCreator {

	PluginXMLHandler xmlHandler;

	// TODO ist das so sinnvoll mit der KielerException im konstruktor.
	// vlt. sollte man lieber die saxparser factory in einer init() methode
	// aufnehmen, diese dann aufrufen und eine exception werfen lassen...
	public ExtPointExampleCreator() throws KielerException {
		new PluginXMLHandler();
	}

	public void addExtension(String projectId, String location, Example example)
			throws KielerException {
		// validateProject(projectId, location);
		// xmlHandler.addExtension(projectId, location, example);
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<?eclipse version=\"3.4\"?>");
		sb.append("<plugin>");
		sb.append("<extension point=\"de.cau.cs.kieler.kex\"");
		sb.append("<category id=\"creationTest\">");
		sb.append("</category>");
		sb.append("<example ");
		sb.append("contact=\"pkl@informatik.uni-kiel.de\"");
		sb.append("description=\"creationTest\"");
		sb.append("id=\"de.cau.cs.kieler.kex.creationtest\"");
		sb.append("name=\"Creation Test\"");
		sb.append("version=\"1.0\">");
		sb.append("<example_resource ");
		sb.append("category=\"creationTest=\"");
		sb.append("is_head_resource=\"true\"");
		sb.append("resource=\"dataflow/Flight.dataflow_diagram\">");
		sb.append("</example_resource>");
		sb.append("</example>");
		sb.append("</extension>");
		sb.append("</plugin>");

		try {
			new ExtensionPointChangeHandler().start(org.eclipse.core.internal.registry.osgi.Activator.getContext());
		} catch (Exception e) {
			// bla bla bla
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * common way, but does not work.
	 * @param sb
	 */
	private void addExtension(String sb){
		try {
			// use Eclipse Dynamic Extension API
			IExtensionRegistry reg = RegistryFactory.getRegistry();
			Object key = ((ExtensionRegistry) reg).getTemporaryUserToken();
			Bundle bundle = Activator.getDefault().getBundle(
					"de.cau.cs.kieler.kex");
			org.eclipse.core.internal.registry.osgi.Activator.getContext();
			IContributor contributor = ContributorFactoryOSGi
					.createContributor(bundle);
			ByteArrayInputStream is = new ByteArrayInputStream((sb.toString())
					.getBytes("UTF-8"));
			reg.addContribution(is, contributor, false, null, null, key);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * creates example files to given project path
	 */
	public void createExampleResources(String location, ExampleResource resource) {
		// String workspacePath = ResourcesPlugin.getWorkspace().getRoot()
		// .getLocation().toString();
		// List<File> resources = resource.getResources();
		// for (File file : resources) {
		// File tmpFile = new File(workspacePath
		// + File.separator + ((projects.length > 0) ? projects[0].getName() :
		// "test")+ File.separator +"test.file");
		//      
		// if (!tmpFile.exists()){
		// tmpFile.getParentFile().mkdirs();
		// try {
		// tmpFile.createNewFile();
		// }
		// catch (IOException e) {
		// //TODO fehlerhandling ueberlegen
		// e.printStackTrace();
		// }
		// }
		// }
	}

	private void validateProject(String projectId, String location)
			throws KielerException {
		File file = new File(location);
		if (!file.exists()) {
			throw new KielerException("There is no file for given location:"
					+ location);
		}
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectId);
		if (project == null)
			throw new KielerException(
					"There is no project for given project id:" + projectId);
	}

}
