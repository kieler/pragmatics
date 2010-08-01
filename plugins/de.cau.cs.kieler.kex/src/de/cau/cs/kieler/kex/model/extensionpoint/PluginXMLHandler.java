package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
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

	private File filterPluginXML(final String location) {
		// TODO filter mechanismus bauen...
		File pluginXML = new File(PLUGIN_XML);
		return pluginXML;

	}

	public Document parserPluginXML(File file) throws SAXException,
			IOException, ParserConfigurationException {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(file);
	}

	public void addExtension(String projectId, String location, Example example)
			throws KielerException {
		try {
			// FIXME mit projectId wird das nicht gehen, brauche externen
			// pfad...
			File file = filterPluginXML(projectId);
			parsedXML = parserPluginXML(file);
			extendPluginXML(parsedXML);
			// TODO plugin.xml erweitern... mit geparstem file
		} catch (ParserConfigurationException e) {

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

	private void extendPluginXML(Document pluginXML) {
		// parsedXML nun nutzen und aufloesen...
		// TODO Beispiel für doc zugriff nun erweitern...
		// NodeList nl = node.getChildNodes();
		//
		// for (int i = 0, cnt = nl.getLength(); i < cnt; i++) {
		// System.out.println("[" + nl.item(i) + "]");
		//
		// visit(nl.item(i), level + 1);
		// }
	}

}
