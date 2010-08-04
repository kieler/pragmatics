package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
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

/**
 * manages plugin.xml changes.
 * 
 * @author pkl
 */
class PluginXMLHandler {

	private Document parsedXML = null;
	private static final String PLUGIN_XML = "plugin.xml";

	private File filterPluginXML(final File location) throws KielerException {
		// TODO evtl. ist der check auf ein java pluginproject schöner, als nach
		// der plugin.xml zu suchen.

		// TODO check if File parent = new File(location.getPath) besser ist...
		File parent = location;
		File[] listFiles = null;
		while (parent != null && parent.exists() && parent.isDirectory()) {
			listFiles = parent.listFiles(new FileFilter() {

				public boolean accept(File pathname) {
					if (pathname.getName().equals(PLUGIN_XML))
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

	public void addExtension(String projectId, File location, Example example)
			throws KielerException {
		try {
			// FIXME mit projectId wird das nicht gehen, brauche externen
			// pfad...
			File pluginXML = filterPluginXML(location);
			parsedXML = parserPluginXML(pluginXML);
			// TODO think about a exampleName.meta for informations of example
			// instead of this example here.
			modifyPluginXML(example);
			// TODO plugin.xml erweitern... mit geparstem file
		} catch (ParserConfigurationException e) {
			// TODO KexConstants einfuehren, d.h. eigene Klasse und diese
			// meldungen hier drin sammeln. (siehe visor constants)
			String msg = "Error appears while parsing plugin.xml of project: "
					+ projectId;
			throw new KielerException(msg, e);
		} catch (SAXException e) {
			String msg = "Error appears while parsing plugin.xml of project: "
					+ projectId;
			throw new KielerException(msg, e);
		} catch (IOException e) {
			String msg = "Error appears while parsing plugin.xml of project: "
					+ projectId;
			throw new KielerException(msg, e);
		}
	}

	private void extendPluginXML(Document pluginXML, final String category) {
		NodeList nl = pluginXML.getChildNodes();
		int nlLength = nl.getLength();
		for (int i = 0; i < nlLength; i++) {
			nl.item(i);
		}

		// parsedXML nun nutzen und aufloesen...
		// TODO Beispiel fï¿½r doc zugriff nun erweitern...
		// NodeList nl = node.getChildNodes();
		//
		// for (int i = 0, cnt = nl.getLength(); i < cnt; i++) {
		// System.out.println("[" + nl.item(i) + "]");
		//
		// visit(nl.item(i), level + 1);
		// }

	}

	// TODO has to change, cause its not really performant and I do not really
	// know if it works...
	// and has to ziehen auseinander.
	private void modifyPluginXML(final Example example) throws KielerException {
		Element root = this.parsedXML.getDocumentElement();
		NodeList plugins = this.parsedXML.getElementsByTagName("plugin");
		int pluginsLength = plugins.getLength();
		// if(pluginsLength == 0 || pluginsLength > 1)
		// dann fehlerfall überlegen, oder sogar drauf reagieren können, evtl
		// dann anlegen.

		Node extPointKEX = null;
		NodeList childNodes = plugins.item(0).getChildNodes();
		int length = childNodes.getLength();
		for (int i = 0; i < length; i++) {
			Node node = childNodes.item(i);
			// FIXME für die plugin tags und attribute benötigen wir eine
			// enumeration
			if ("extension".equals(node.getNodeName())) {
				NamedNodeMap attributes = node.getAttributes();
				Node namedItem = attributes.getNamedItem("point");
				if ("de.cau.cs.kieler.kex".equals(namedItem.getNodeValue()))
					extPointKEX = node;
				break;
			}
		}

		// if (item.getLocalName().equals("de.cau.cs.kieler.kex")) {
		// TODO ggf zusätzlichen check einbauen, ob exampleId schon
		// existiert, normalerweise wird vorher geprüft.
		// es können aber mehrere examples exportiert werden
		// ohne zwischendurch neu zu bauen.
		testMethod(extPointKEX.getChildNodes());

		// xmlDoc=loadXMLDoc("books.xml");
		//
		// Element createdElement = pluginXML.createElement("example");
		// createdElement.setAttribute("contact", "pkl@PluginTest");
		// createdElement.setAttribute("description", "pluginTest description");
		// createdElement.setAttribute("id",
		// "de.cau.cs.kieler.pluginExportTest");
		// createdElement.setAttribute("name", "pluginExportTest");
		// Element createdElement2 =
		// pluginXML.createElement("example_resource");
		// createdElement2.setAttribute("category",
		// "de.cau.cs.kieler.dataflow");
		// createdElement2.setAttribute("is_head_resource", "true");
		// // pfad aus dem workspace der kieler instance
		// createdElement2.setAttribute("resource", "null");
		// createdElement.appendChild(createdElement2);
		root.appendChild(parsedXML.createElement("ladida"));

		// setting up a transformer
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans;
		try {
			trans = transfac.newTransformer();
		} catch (TransformerConfigurationException e1) {
			throw new KielerException(e1.getMessage());
		}

		// generating string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(parsedXML);
		try {
			trans.transform(source, result);
		} catch (TransformerException e) {
			throw new KielerException(e.getMessage());
		}
		String xmlString = sw.toString();

		// Saving the XML content to File
		OutputStream f0;
		byte buf[] = xmlString.getBytes();
		try {
			f0 = new FileOutputStream("testXML.xml");
		} catch (FileNotFoundException e) {
			throw new KielerException(e.getMessage());
		}
		for (int i = 0; i < buf.length; i++) {
			try {
				f0.write(buf[i]);
			} catch (IOException e) {
				throw new KielerException(e.getMessage());
			}
		}
		try {
			f0.close();
		} catch (IOException e) {
			throw new KielerException(e.getMessage());
		}
		buf = null;

	}

	private void testMethod(NodeList childNodes) {
		int length = childNodes.getLength();
		for (int i = 0; i < length; i++)
			if ("example".equals(childNodes.item(i).getNodeName())) {
				Node item = childNodes.item(i);
				item.getAttributes();
			}
	}

	private Node toNode(String category) {
		return null;
	}

	private Node toNode(Example example) {
		// - <example contact="pkl@informatik.uni-kiel.de"
		// description="just a test"
		// id="de.cau.cs.kieler.core.kex.models.flight" name="Flight"
		// version="1.0">
		// <example_resource category="de.cau.cs.kieler.dataflow"
		// is_head_resource="true" resource="dataflow/Flight.dataflow_diagram"/>
		// <example_resource category="de.cau.cs.kieler.dataflow"
		// is_head_resource="false"
		// resource="dataflow/Orthogonal.dataflow_diagram" />
		// </example>

		// return new NodeImpl();

		return null;
	}
}
