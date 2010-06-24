package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;

 class PluginXMLHandler {

	private static final String PLUGIN_XML = "plugin.xml";
	private SAXParser saxParser;
	@SuppressWarnings("unused")
	private StringBuffer parsedXML;

	public PluginXMLHandler() throws KielerException {
		parsedXML = new StringBuffer();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			saxParser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			throw new KielerException("instantiating SAX parser failed", e);
		} catch (SAXException e) {
			throw new KielerException("instantiating SAX parser failed", e);
		}

	}

	
	public void addExtension(String projectId, String location, Example example) throws KielerException{
		try {
			parsePluginXML(projectId);
			extendPluginXML();
			// TODO plugin.xml erweitern... mit geparstem file
		} catch (SAXException e) {
			String msg = "Error appears while parsing plugin.xml of project: " + projectId;
			throw new KielerException(msg, e);
		} catch (IOException e) {
			String msg = "Error appears while parsing plugin.xml of project: " + projectId;
			throw new KielerException(msg, e);
		}
	}
	
	private void extendPluginXML() {
				// parsedXML nun nutzen und aufloesen...
	}


	private void parsePluginXML(String projectId) throws SAXException,
			IOException {
		IFile iPluginXML = filterPluginXML(projectId);
		if (!(iPluginXML instanceof File) || !iPluginXML.exists()) {
			// StatusManager... throw new Exception();
		}
		File pluginXML = (File) iPluginXML;
		pluginXML.setReadable(true);
		pluginXML.setWritable(true);
		saxParser.parse(pluginXML, new DefaultHandler() {
			@Override
			public void startElement(String uri, String localName,
					String qName, Attributes attributes) throws SAXException {
				// parsedXML.append(...)
			}
		});
	}

	private IFile filterPluginXML(String projectId) {
		// TODO momentan geht geht das ueber getProject(name),
		// da id und name zufaellig gleich geht das, ansonsten gibts probs.
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectId);
		return project.getFile(PLUGIN_XML);
	}
	
}
