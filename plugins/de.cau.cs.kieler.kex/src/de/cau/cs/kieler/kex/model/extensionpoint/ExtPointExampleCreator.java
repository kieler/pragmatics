package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.kex.controller.util.IOHandler;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExportResource;

/**
 * can be used for creating new extensions for KEX extension point.<br>
 * So it allows to create new examples or example categories.
 * 
 * @author pkl
 * 
 */

public class ExtPointExampleCreator {

	private final IPath workspacePath = Platform.getLocation();

	private Document parsedXML = null;

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
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
				file);
	}

	/**
	 * NOTE: parseElement could only be a type of Example or a example category
	 * in String representation.
	 * 
	 * @param location
	 * @param parseElement
	 * @param destResources
	 * @param deletableCategories
	 * @param creatableCategories
	 * @throws KielerException
	 */
	public void addExtension(File location, Object parseElement,
			List<IPath> destResources, Object creatableCategories,
			Object deletableCategories) throws KielerException {
		try {
			File pluginXML = null;
			File filteredFile = IOHandler.filterPluginXML(location);
			if (!IOHandler.PLUGIN_XML.equals(filteredFile.getName())) {
				pluginXML = createPluginXML(filteredFile);
			} else {
				pluginXML = filteredFile;
			}
			parsedXML = parserPluginXML(pluginXML);
			NodeList plugins = this.parsedXML
					.getElementsByTagName(PluginConstants.PLUGIN);
			int pluginsLength = plugins.getLength();
			if (pluginsLength == 0 || pluginsLength > 1) {
				// dann fehlerfall ï¿½berlegen, oder sogar drauf reagieren
				// kï¿½nnen,
				// evtl
				// dann anlegen.
			}
			Node pluginNode = plugins.item(0);

			editPluginCategories(pluginNode, creatableCategories,
					deletableCategories);

			Node extensionKEX = filterExtensionKEX(pluginNode.getChildNodes());

			if (extensionKEX == null) {
				extensionKEX = parsedXML
						.createElement(ExtPointConstants.EXT_POINT);
				pluginNode.appendChild(extensionKEX);

				// parent von extensionKEX ist plugin... muss also gehen
				// TODO test createElement, kann sein, dass noch an root knoten
				// angeschlossen werden muss. getestet GEHT NICHT, muss nochmal
				// ueberschaut werden.
			}

			Node parentNode = extensionKEX.getParentNode();
			System.out.println("parent von extensionKEX: "
					+ parentNode.getNodeName());

			if (parseElement instanceof Example) {
				extensionKEX.appendChild(toNode((Example) parseElement,
						destResources));
			} else if (parseElement instanceof String) {
				extensionKEX.appendChild(toNode((String) parseElement));
			} else {
				throw new RuntimeException(
						"PluginXMLHandler: wrong parameter for parseElement.");
			}
			writePluginXML(pluginXML.getAbsolutePath());
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

	private void editPluginCategories(Node pluginNode,
			Object creatableCategories, Object deletableCategories) {
		if (creatableCategories != null) {
			@SuppressWarnings("unchecked")
			List<String> creates = (List<String>) creatableCategories;
			for (String creatable : creates) {
				Element createdCategory = parsedXML
						.createElement(ExtPointConstants.CATEGORY);
				createdCategory.setAttribute(ExtPointConstants.ID, creatable);
				pluginNode.appendChild(createdCategory);

			}
		}

		// TODO geht so nicht, da wir nur die category kennen NICHT aber das
		// project, muss erst gesucht werden, verbinden mit der suche von todo
		// eine zeile tiefer
		// TODO prüfen, ob es noch examples damit gibt, bevor geloescht werden
		// kann.
		if (deletableCategories != null) {
			@SuppressWarnings("unchecked")
			List<String> deletes = (List<String>) deletableCategories;
			NodeList childNodes = pluginNode.getChildNodes();
			for (String category : deletes) {
				for (int i = 0; i < childNodes.getLength(); i++) {
					Node item = childNodes.item(0);
					if (item.getNodeName().equals(ExtPointConstants.CATEGORY)) {
						NamedNodeMap attributes = item.getAttributes();
						Node namedItem = attributes
								.getNamedItem(ExtPointConstants.ID);
						if (category.equals(namedItem.getNodeValue())) {
							pluginNode.removeChild(item);
						}
					}
				}
			}
		}

	}

	// TODO eclipse version mit reinkriegen momentan steht da standalone no!
	@SuppressWarnings("restriction")
	private File createPluginXML(File parent) {
		String path = parent.getAbsolutePath() + File.separatorChar
				+ IOHandler.PLUGIN_XML;
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			writer = factory.createXMLStreamWriter(new FileOutputStream(path));
			writer.writeStartDocument();
			writer.writeStartElement(PluginConstants.PLUGIN);
			writer.writeStartElement(PluginConstants.EXTENSION);
			writer.writeAttribute(PluginConstants.POINT,
					ExtPointConstants.EXT_POINT);
			writer.writeEndElement();
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new File(path);

	}

	private Node filterExtensionKEX(NodeList plugins) {
		Node extensionKEX = null;
		int length = plugins.getLength();
		for (int i = 0; i < length; i++) {
			Node node = plugins.item(i);
			if (PluginConstants.EXTENSION.equals(node.getNodeName())) {
				NamedNodeMap attributes = node.getAttributes();
				Node namedItem = attributes.getNamedItem(PluginConstants.POINT);
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

	/**
	 * creates example files to given location
	 * 
	 * @param exampleId
	 * 
	 * @param sourceProject
	 */
	public List<IPath> copyResources(File destFile,
			List<ExportResource> resources, String exampleId)
			throws KielerException {
		List<IPath> errorList = new ArrayList<IPath>();
		List<IPath> result = new ArrayList<IPath>();
		try {
			if (exampleId != null) {
				destFile = new File(destFile.getPath() + "/" + exampleId + "/");
				destFile.mkdir();
				result.add(new Path(exampleId + "/"));
			}
			for (ExportResource resource : resources) {
				copyFile(resource, destFile.getPath(), errorList);
				result.add(new Path(exampleId + "/"
						+ resource.getLocalPath().toPortableString()));
			}
		} catch (KielerException e) {
			throw new KielerModelException(e.getLocalizedMessage(), errorList);
		}
		return result;
	}

	private void copyFile(ExportResource resource, String destPath,
			List<IPath> errorList) throws KielerException {
		StringBuffer destLocation = new StringBuffer();
		try {

			String sourcePath = this.workspacePath.toPortableString()
					+ resource.getResource().getFullPath().toPortableString();

			destLocation.append(destPath).append(File.separatorChar).append(
					resource.getLocalPath());
			Path destination = new Path(destLocation.toString());
			errorList.add(destination);

			IOHandler.writeFile(new File(sourcePath), destination.toFile());
		} catch (IOException e) {
			// TODO ErrorHandling ï¿½berlegen.
		}
	}

	public void deleteExampleResource(List<IPath> resources) {
		for (IPath path : resources)
			IOHandler.deleteFile(path.toFile());
	}

	private void writePluginXML(String pluginPath) throws KielerException {
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			DOMSource source = new DOMSource(parsedXML);
			test();
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

	private void test() {
		NodeList childNodes = parsedXML.getChildNodes();
		int length = childNodes.getLength();
		for (int i = 0; i < length; i++) {
			System.out.println(childNodes.item(i).getNodeName());
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
		createdExample.setAttribute(ExtPointConstants.CONTACT, example
				.getContact());
		createdExample.setAttribute(ExtPointConstants.DESCRIPTION, example
				.getDescription());
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
		createdExResource.setAttribute(ExtPointConstants.RESOURCE, exResource
				.toPortableString());
		return createdExResource;
	}

}
