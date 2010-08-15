package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IPath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.util.IOHandler;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExtPointConstants;

/**
 * can be used for creating new extensions for KEX extension point.<br>
 * So it allows to create new examples or example categories.
 * 
 * @author pkl
 * 
 */

public class ExtPointExampleCreator {

	private Document parsedXML = null;
	private static final String PLUGIN_XML = "plugin.xml";

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
	 * @param destResources
	 * @throws KielerException
	 */
	public void addExtension(File location, Object parseElement,
			List<IPath> destResources) throws KielerException {
		try {
			// FIXME filterFile so umbauen, dass in höheren instancen nach
			// ".project" gesucht wird, dann annehmen, dass java project
			// gefunden. dann nach manifest.mf suchen -> annehmen pluginproject;
			// dann plugin.xml suchen
			// wenn vorhanden, dann erweitern ansonsten neue plugin.xml
			// hinzufügen.

			File pluginXML = IOHandler.filterFile(location, PLUGIN_XML);
			parsedXML = parserPluginXML(pluginXML);
			Node extensionKEX = filterExtensionKEX();
			if (extensionKEX == null) {
				extensionKEX = parsedXML
						.createElement(ExtPointConstants.EXT_POINT);
			}
			if (parseElement instanceof Example) {
				extensionKEX.appendChild(toNode((Example) parseElement,
						destResources));
			} else if (parseElement instanceof String) {
				extensionKEX.appendChild(toNode((String) parseElement));
			} else
				throw new RuntimeException(
						"PluginXMLHandler: wrong parameter for parseElement.");

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
		if (extensionKEX == null) {
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

	private Node toNode(String categoryId) {
		Element createdElement = parsedXML
				.createElement(ExtPointConstants.CATEGORY);
		createdElement.setAttribute(ExtPointConstants.ID, categoryId);
		return createdElement;
	}

	private Node toNode(Example example, List<IPath> resources) {
		// TODO check nullpointer, they shouldnï¿½t set to plugin.xml
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
		URL headResource = example.getHeadResource();
		if (headResource != null)
			createdExample.setAttribute(ExtPointConstants.HEAD_RESOURCE,
					headResource.getPath());

		for (String category : example.getCategories()) {
			createdExample.appendChild(toNode(category));
		}

		for (IPath exResource : resources) {
			createdExample.appendChild(toNode(exResource));
		}
		return createdExample;
	}

	private Node toNode(IPath exResource) {
		Element createdExResource = parsedXML
				.createElement(ExtPointConstants.EXAMPLE_RESOURCE);
		createdExResource.setAttribute(ExtPointConstants.RESOURCE,
				exResource.toPortableString());
		return createdExResource;
	}

}
