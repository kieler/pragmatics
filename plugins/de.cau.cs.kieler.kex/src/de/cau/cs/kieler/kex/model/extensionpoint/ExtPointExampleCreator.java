package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
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
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.kex.controller.ErrorMessage;
import de.cau.cs.kieler.kex.controller.ExportResource;
import de.cau.cs.kieler.kex.controller.util.IOHandler;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;

/**
 * can be used for creating new extensions for KEX extension point.<br>
 * So it allows to create new examples or example categories.
 * 
 * @author pkl
 * 
 */

public class ExtPointExampleCreator {

	private final IPath workspacePath = Platform.getLocation();

	private Document parsedXML;

	private File pluginXML;

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
	private Document parsePluginXML(final File file) throws SAXException,
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
	 * @param deletableCategories
	 * @param creatableCategories
	 * @throws KielerException
	 */
	public void addExtension(File location, Example parseElement,
			List<String> creatableCategories) throws KielerException {

		Node pluginNode = getPluginNode(location, creatableCategories);

		editPluginCategories(pluginNode, creatableCategories);

		boolean isDuplicate = false;
		checkDuplicate(ExtPointConstants.TITLE, parseElement.getTitle(),
				pluginNode, isDuplicate);
		if (isDuplicate) {
			// fehlerfall �berlegen
		}

		Node extensionKEX = filterExtensionKEX(pluginNode);

		Node parentNode = extensionKEX.getParentNode();
		System.out.println("parent von extensionKEX: "
				+ parentNode.getNodeName());

		makeRootSource(location, parseElement);
		extensionKEX.appendChild(toNode(parseElement));
		writePluginXML(pluginXML.getAbsolutePath());
	}

	/**
	 * 
	 * @param searchKey
	 * @param searchValue
	 * @param searchNode
	 * @return true if a duplicate has been found, otherwise false;
	 */
	private void checkDuplicate(String searchKey, String searchValue,
			Node searchNode, boolean result) {
		if (result) {
			return;
		}
		NodeList childNodes = searchNode.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node item = childNodes.item(i);
			if (searchKey.equals(item.getNodeName())) {
				if (searchValue.equals(item.getNodeValue()))
					result = true;
			}
			if (item.hasChildNodes()) {
				checkDuplicate(searchKey, searchValue, item, result);
			}
		}
	}

	private void makeRootSource(File location, Object parseElement)
			throws KielerException {
		File project = IOHandler.searchUP(location, IOHandler.PROJECT_FILE)
				.getParentFile();
		String relativeLocation = location.getPath().substring(
				project.getPath().length());
		((Example) parseElement)
				.setRootResource((relativeLocation.length() > 0) ? relativeLocation
						.substring(1) : relativeLocation);
	}

	private Node getPluginNode(File locationFile,
			List<String> creatableCategories) throws KielerException {

		File pluginXML = null;
		File filteredFile;
		try {
			filteredFile = IOHandler.filterPluginXML(locationFile);
			if (!IOHandler.PLUGIN_XML.equals(filteredFile.getName())) {
				pluginXML = createPluginXML(filteredFile.getAbsolutePath());
			} else {
				pluginXML = filteredFile;
			}
			setPluginXML(pluginXML);
			parsedXML = parsePluginXML(pluginXML);
		} catch (ParserConfigurationException e) {
			String msg = ErrorMessage.NOT_PARSE_PLUGIN
					+ e.getLocalizedMessage();
			throw new KielerException(msg, e);
		} catch (SAXException e) {
			String msg = ErrorMessage.NOT_PARSE_PLUGIN
					+ e.getLocalizedMessage();
			throw new KielerException(msg, e);
		} catch (IOException e) {
			String msg = ErrorMessage.NOT_PARSE_PLUGIN
					+ e.getLocalizedMessage();
			throw new KielerException(msg, e);
		}

		NodeList plugins = this.parsedXML
				.getElementsByTagName(ExtPointConstants.PLUGIN);
		int pluginsLength = plugins.getLength();
		if (pluginsLength == 0 || pluginsLength > 1) {
			// dann fehlerfall ueberlegen, oder sogar drauf reagieren
			// koennen,
			// evtl
			// dann anlegen.
		}
		return plugins.item(0);
	}

	private void setPluginXML(File pluginXML) {
		this.pluginXML = pluginXML;
	}

	private Node filterExtensionKEX(Node pluginNode) {
		Node extensionKEX = null;
		NodeList extensions = pluginNode.getChildNodes();
		int length = extensions.getLength();
		for (int i = 0; i < length; i++) {
			Node node = extensions.item(i);
			if (ExtPointConstants.EXTENSION.equals(node.getNodeName())) {
				NamedNodeMap attributes = node.getAttributes();
				Node namedItem = attributes
						.getNamedItem(ExtPointConstants.POINT);
				if (ExtPointConstants.KEX_EXT_POINT.equals(namedItem
						.getNodeValue())) {
					extensionKEX = node;
					break;
				}
			}
		}
		if (extensionKEX == null) {
			extensionKEX = parsedXML
					.createElement(ExtPointConstants.KEX_EXT_POINT);
			pluginNode.appendChild(extensionKEX);
			// parent von extensionKEX ist plugin... muss also gehen
			// TODO test createElement, kann sein, dass noch an root knoten
			// angeschlossen werden muss. getestet GEHT NICHT, muss nochmal
			// ueberschaut werden.
		}

		return extensionKEX;
	}

	// TODO eclipse version mit reinkriegen momentan steht da standalone no!
	@SuppressWarnings("restriction")
	private File createPluginXML(String parentPath) {
		String path = parentPath + File.separatorChar + IOHandler.PLUGIN_XML;
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			writer = factory.createXMLStreamWriter(new FileOutputStream(path),
					"UTF-8");
			writer.writeCharacters("<?eclipse version=\"3.4\"?>");
			writer.writeStartDocument();
			writer.writeStartElement(ExtPointConstants.PLUGIN);
			writer.writeStartElement(ExtPointConstants.EXTENSION);
			writer.writeAttribute(ExtPointConstants.POINT,
					ExtPointConstants.KEX_EXT_POINT);
			writer.writeEndElement();
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.close();
			// java dom create example, transformer benutzen
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new File(path);
	}

	private void createTransformPlugin(String parentPath) {
		// We need a Document
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = dbfac.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document doc = docBuilder.newDocument();

		// //////////////////////
		// Creating the XML tree

		// create the root element and add it to the document
		Element root = doc.createElement(ExtPointConstants.PLUGIN);
		doc.appendChild(root);

		// create a comment and put it in the root element
		Comment comment = doc.createComment("<?eclipse version=\"3.4\"?>");
		root.appendChild(comment);

		// create child element, add an attribute, and add to root
		Element child = doc.createElement(ExtPointConstants.EXTENSION);
		child.setAttribute(ExtPointConstants.POINT,
				ExtPointConstants.KEX_EXT_POINT);
		root.appendChild(child);

		// ///////////////
		// Output the XML
		String path = parentPath + File.separatorChar + IOHandler.PLUGIN_XML;

		// set up a transformer
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans;
		try {
			trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			result.setOutputStream(new FileOutputStream(path));
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			String xmlString = sw.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * creates example files to given location
	 * 
	 * @param exampleId
	 * 
	 * @param sourceProject
	 */
	public void copyResources(File destFile, List<ExportResource> resources)
			throws KielerException {
		List<IPath> errorList = new ArrayList<IPath>();
		try {
			for (ExportResource resource : resources) {
				copyFile(resource, destFile.getPath(), errorList);
			}
		} catch (KielerException e) {
			throw new KielerModelException(e.getLocalizedMessage(), errorList);
		}
	}

	private void copyFile(ExportResource resource, String destPath,
			List<IPath> errorList) throws KielerException {
		StringBuffer destLocation = new StringBuffer();
		try {

			String sourcePath = this.workspacePath.toPortableString()
					+ resource.getResource().getFullPath().toOSString();

			destLocation.append(destPath).append(File.separatorChar)
					.append(resource.getLocalPath());
			Path destination = new Path(destLocation.toString());
			errorList.add(destination);

			IOHandler.writeFile(new File(sourcePath), destination.toFile());
		} catch (IOException e) {
			// TODO ErrorHandling ueberlegen.
		}
	}

	public void deleteExampleResource(List<IPath> resources) {
		for (IPath path : resources)
			IOHandler.deleteFile(path.toFile());
	}

	private void editPluginCategories(Node pluginNode,
			List<String> creatableCategories) {
		boolean isDuplicate = false;
		for (String category : creatableCategories) {
			checkDuplicate(ExtPointConstants.ID, category, pluginNode,
					isDuplicate);
		}

		if (isDuplicate) {
			// TODO fehlerfall �berlegen
		}

		if (creatableCategories != null) {
			@SuppressWarnings("unchecked")
			List<String> creates = creatableCategories;
			for (String creatable : creates) {
				Element createdCategory = parsedXML
						.createElement(ExtPointConstants.CATEGORY);
				createdCategory.setAttribute(ExtPointConstants.ID, creatable);
				pluginNode.appendChild(createdCategory);

			}
		}

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
			throwWritePluginError(e);
		} catch (TransformerFactoryConfigurationError e) {
			throwWritePluginError(e);
		} catch (FileNotFoundException e) {
			throwWritePluginError(e);
		} catch (TransformerException e) {
			throwWritePluginError(e);
		}

	}

	private void test() {
		NodeList childNodes = parsedXML.getChildNodes();
		int length = childNodes.getLength();
		for (int i = 0; i < length; i++) {
			System.out.println(childNodes.item(i).getNodeName());
		}
	}

	private void throwWritePluginError(Throwable e) throws KielerException {
		throw new KielerException(new StringBuffer()
				.append(ErrorMessage.NOT_WRITE_PLUGIN)
				.append(e.getLocalizedMessage()).toString());
	}

	private Node toNode(String categoryId) {
		Element createdElement = parsedXML
				.createElement(ExtPointConstants.CATEGORY);
		createdElement.setAttribute(ExtPointConstants.ID, categoryId);
		return createdElement;
	}

	private Node toNode(Example example) {
		Element createdExample = parsedXML
				.createElement(ExtPointConstants.EXAMPLE);
		createdExample
				.setAttribute(ExtPointConstants.TITLE, example.getTitle());
		createdExample.setAttribute(ExtPointConstants.DESCRIPTION,
				example.getDescription());
		createdExample.setAttribute(ExtPointConstants.GENERATION_DATE, example
				.getGenerationDate().toString());
		createdExample.setAttribute(ExtPointConstants.VERSION, example
				.getVersion().toString());

		String overviewPicPath = example.getOverViewPicPath();
		if (overviewPicPath != null)
			createdExample.setAttribute(ExtPointConstants.OVERVIEW_PIC,
					overviewPicPath);
		String author = example.getAuthor();
		if (author != null)
			createdExample.setAttribute(ExtPointConstants.AUTHOR, author);

		String contact = example.getContact();
		if (contact != null)
			createdExample.setAttribute(ExtPointConstants.CONTACT, contact);

		String rootResource = example.getRootResource();
		if (rootResource != null)
			createdExample.setAttribute(ExtPointConstants.ROOT_DIRECTORY,
					rootResource);

		for (String category : example.getCategories()) {
			createdExample.appendChild(toNode(category));
		}

		for (ExampleResource exResource : example.getResources()) {
			createdExample.appendChild(toNode(example.getRootResource(),
					exResource));
		}
		return createdExample;

	}

	private Node toNode(String relativePath, ExampleResource exResource) {
		Element createdExResource = parsedXML
				.createElement(ExtPointConstants.EXAMPLE_RESOURCE);
		createdExResource.setAttribute(ExtPointConstants.LOCAL_PATH,
				relativePath + "/" + exResource.toString());
		createdExResource.setAttribute(ExtPointConstants.RESOURCE_TYPE,
				ExampleResource.Type.map(exResource.getResourceType()));
		createdExResource.setAttribute(ExtPointConstants.DIRECT_OPEN,
				Boolean.toString(exResource.isDirectOpen()));
		return createdExResource;

	}

}
