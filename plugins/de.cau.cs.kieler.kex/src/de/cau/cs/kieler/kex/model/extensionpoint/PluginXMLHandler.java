package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;
import de.cau.cs.kieler.kex.model.ExtPointConstants;

/**
 * manages plugin.xml changes.
 * 
 * @author pkl
 */
class PluginXMLHandler {

	private Document parsedXML = null;
	private static final String PLUGIN_XML = "plugin.xml";

	private File filterPluginXML(final File location) throws KielerException {
		// TODO evtl. ist der check auf ein java pluginproject schï¿½ner, als
		// nach
		// der plugin.xml zu suchen.

		// TODO check if File parent = new File(location.getPath) besser ist...
		File parent = location;
		File[] listFiles = null;
		while (parent != null && parent.exists() && parent.isDirectory()) {
			listFiles = parent.listFiles(new FileFilter() {

				public boolean accept(File pathname) {
					if (pathname.getName().equals(PLUGIN_XML))
						// TODO pathname auslesen und
						return true;
					return false;
				}
			});
			if (listFiles.length > 0)
				break;
			parent = parent.getParentFile();
		}
		if (listFiles.length == 0)
			throw new KielerException(
					"The given destination location is not an plugin project and not a folder in that.");
		if (listFiles.length > 1)
			throw new KielerException(new StringBuffer()
					.append("There are more than one plugin.xml file in ")
					.append(parent.getPath()).toString());
		return listFiles[0];
	}

	/**
	 * parses the given file.
	 * 
	 * @param file
	 *            , which will be parsed
	 * @return Document, parsed document
	 * @throws SAXException
	 *             , could be thrown by DOM framework
	 * @throws IOException
	 *             , could be thrown by DOM framework
	 * @throws ParserConfigurationException
	 *             , could be thrown by DOM framework
	 */
	private Document parserPluginXML(final File file) throws SAXException,
			IOException, ParserConfigurationException {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(file);
	}

	/**
	 * NOTE: parseElement could only be a type of Example or a example category
	 * in String representation.
	 * 
	 * @param location
	 * @param parseElement
	 * @throws KielerException
	 */
	public void addExtension(File location, Object parseElement)
			throws KielerException {
		try {
			File pluginXML = filterPluginXML(location);
			parsedXML = parserPluginXML(pluginXML);
			Node extensionKEX = filterExtensionKEX();
			// TODO only for testing!
			testMethod(extensionKEX.getChildNodes());
			if (parseElement instanceof Example) {
				extensionKEX.appendChild(toNode((Example) parseElement));
			}
			if (parseElement instanceof String) {
				extensionKEX.appendChild(toNode((String) parseElement));
			} else
				throw new RuntimeException(
						"PluginXMLHandler: wrong parameter for parseElement.");
			// TODO only for testing!
			testMethod(extensionKEX.getChildNodes());
			writePluginXML(pluginXML.getAbsolutePath());
			// TODO plugin.xml erweitern... mit geparstem file
		} catch (ParserConfigurationException e) {
			// TODO KexConstants einfuehren, d.h. eigene Klasse und diese
			// meldungen hier drin sammeln. (siehe visor constants)
			String msg = "Could not parse plugin.xml: "
					+ e.getLocalizedMessage();
			throw new KielerException(msg, e);
		} catch (SAXException e) {
			String msg = "Could not parse plugin.xml: "
					+ e.getLocalizedMessage();
			throw new KielerException(msg, e);
		} catch (IOException e) {
			String msg = "Could not parse plugin.xml: "
					+ e.getLocalizedMessage();
			throw new KielerException(msg, e);
		}
	}

	private Node filterExtensionKEX() {
		NodeList plugins = this.parsedXML
				.getElementsByTagName(PluginXmlConstants.PLUGIN);
		int pluginsLength = plugins.getLength();
		if (pluginsLength == 0 || pluginsLength > 1) {
			// dann fehlerfall ï¿½berlegen, oder sogar drauf reagieren kï¿½nnen,
			// evtl
			// dann anlegen.
		}
		Node extensionKEX = null;
		NodeList childNodes = plugins.item(0).getChildNodes();
		int length = childNodes.getLength();
		for (int i = 0; i < length; i++) {
			Node node = childNodes.item(i);
			if (PluginXmlConstants.EXTENSION.equals(node.getNodeName())) {
				NamedNodeMap attributes = node.getAttributes();
				Node namedItem = attributes
						.getNamedItem(PluginXmlConstants.POINT);
				if (ExtPointConstants.EXT_POINT
						.equals(namedItem.getNodeValue())) {
					extensionKEX = node;
					break;
				}
			}
		}

		// if (item.getLocalName().equals("de.cau.cs.kieler.kex")) {
		// TODO ggf zusï¿½tzlichen check einbauen, ob exampleId schon
		// existiert, normalerweise wird vorher geprï¿½ft.
		// es kï¿½nnen aber mehrere examples exportiert werden
		// ohne zwischendurch neu zu bauen.
		if (extensionKEX != null)
			testMethod(extensionKEX.getChildNodes());
		else {
			// create extension KEX
		}
		return extensionKEX;
	}

	private void writePluginXML(String pluginPath) throws KielerException {
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			DOMSource source = new DOMSource(parsedXML);
			FileOutputStream os = new FileOutputStream(new File(pluginPath));
			StreamResult result = new StreamResult(os);
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			throwPluginError(e);
		} catch (TransformerFactoryConfigurationError e) {
			throwPluginError(e);
		} catch (FileNotFoundException e) {
			throwPluginError(e);
		} catch (TransformerException e) {
			throwPluginError(e);
		}

	}

	private void throwPluginError(Throwable e) throws KielerException {
		throw new KielerException(
				"Could not write to plugin.xml of given project.\n"
						+ e.getLocalizedMessage());
	}

	private void testMethod(NodeList childNodes) {
		int length = childNodes.getLength();
		for (int i = 0; i < length; i++) {
			Node item = childNodes.item(i);
			if (ExtPointConstants.EXAMPLE.equals(item.getNodeName())) {
				NamedNodeMap attributes = item.getAttributes();
				int attLength = attributes.getLength();
				for (int j = 0; j < attLength; j++) {
					Node attItem = attributes.item(j);
					System.out.println(new StringBuffer()
							.append(attItem.getNodeName()).append("; ")
							.append(attItem.getNodeType()).toString());
				}
			}
		}
	}

	private Node toNode(String categoryId) {
		Element createdElement = parsedXML
				.createElement(ExtPointConstants.CATEGORY);
		createdElement.setAttribute(ExtPointConstants.ID, categoryId);
		return createdElement;
	}

	private Node toNode(Example example) {
		// TODO check nullpointer, they shouldn´t set to plugin.xml
		Element createdExample = parsedXML
				.createElement(ExtPointConstants.EXAMPLE);
		createdExample.setAttribute(ExtPointConstants.ID, example.getId());
		createdExample.setAttribute(ExtPointConstants.CONTACT,
				example.getContact());
		createdExample.setAttribute(ExtPointConstants.DESCRIPTION,
				example.getDescription());
		createdExample.setAttribute(ExtPointConstants.NAME, example.getName());
		createdExample.setAttribute(ExtPointConstants.VERSION, example
				.getVersion().toString());
		for (ExampleResource exResource : example.getResources()) {
			createdExample.appendChild(toNode(exResource));
		}
		return createdExample;
	}

	private Node toNode(ExampleResource exResource) {
		// TODO check nullpointer, they shouldn´t set to plugin.xml
		// but here are no nullpointer allowed.
		Element createdExResource = parsedXML
				.createElement(ExtPointConstants.EXAMPLE_RESOURCE);
		createdExResource.setAttribute(ExtPointConstants.CATEGORY,
				exResource.getCategory());
		createdExResource.setAttribute(ExtPointConstants.IS_HEAD_RESOURCE,
				(exResource.isHeadResource() ? "true" : "false"));
		// pfad des neuen platzes
		for (URL resource : exResource.getResources()) {
			Element createdResource = parsedXML
					.createElement(ExtPointConstants.RESOURCE);
			createdResource.setAttribute(ExtPointConstants.PATH,
					resource.getPath());
			createdExResource.appendChild(createdResource);
		}
		return createdExResource;
	}
}
