package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
import org.w3c.dom.DOMImplementation;
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
	private Document parseDocument(final File file) throws SAXException,
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
	public void addExtension(File location, Example parseElement,
			List<String> creatableCategories) throws KielerException {

		Node pluginNode = getPluginNode(location, creatableCategories);

		if (creatableCategories != null && creatableCategories.size() > 0) {
			addExampleCategories(pluginNode, creatableCategories);
		}
		boolean isDuplicate = false;
		// TODO duplicate checker überprüfen mitteles debug
		checkDuplicate(ExtPointConstants.TITLE, parseElement.getTitle(),
				pluginNode, isDuplicate);
		if (isDuplicate) {
			// fehlerfall ï¿½berlegen
		}

		Node extensionKEX = filterExtensionKEX(pluginNode);

		extensionKEX.appendChild(toNode(parseElement, location));
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

	private void makeRootSource(File location, Example example)
			throws KielerException {
		File project = IOHandler.searchUP(location, IOHandler.PROJECT_FILE)
				.getParentFile();
		String relativeLocation = location.getPath().substring(
				project.getPath().length());
		example
				.setRootResource((relativeLocation.length() > 0) ? relativeLocation
						.substring(1)
						: relativeLocation);
	}

	private Node getPluginNode(File locationFile,
			List<String> creatableCategories) throws KielerException {

		try {
			this.pluginXML = IOHandler.filterPluginXML(locationFile);
			if (IOHandler.PLUGIN_XML.equals(this.pluginXML.getName())) {
				parsedXML = parseDocument(this.pluginXML);
			} else {
				// project path + .project file as path
				this.pluginXML = new File(this.pluginXML.getAbsolutePath()
						+ File.separatorChar + IOHandler.PLUGIN_XML);
				parsedXML = createPluginDocument();
			}
		} catch (ParserConfigurationException e) {
			String msg = ErrorMessage.NOT_PARSE_PLUGIN + "\n"
					+ e.getLocalizedMessage();
			throw new KielerException(msg, e);
		} catch (SAXException e) {
			String msg = ErrorMessage.NOT_PARSE_PLUGIN + "\n"
					+ e.getLocalizedMessage();
			throw new KielerException(msg, e);
		} catch (IOException e) {
			String msg = ErrorMessage.NOT_PARSE_PLUGIN + "\n"
					+ e.getLocalizedMessage();
			throw new KielerException(msg, e);
		}

		NodeList plugins = this.parsedXML
				.getElementsByTagName(ExtPointConstants.PLUGIN);
		if (plugins.getLength() == 1) {
			return plugins.item(0);
		}
		// TODO
		throw new KielerException("");
	}

	private Node filterExtensionKEX(Node pluginNode) {
		Node extensionKEX = null;
		NodeList nodes = pluginNode.getChildNodes();
		int length = nodes.getLength();
		for (int i = 0; i < length; i++) {
			Node node = nodes.item(i);
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

			Element extElement = pluginNode.getOwnerDocument().createElement(
					ExtPointConstants.EXTENSION);
			extElement.setAttribute(ExtPointConstants.POINT,
					ExtPointConstants.KEX_EXT_POINT);
			pluginNode.appendChild(extElement);
			return filterExtensionKEX(pluginNode);
		}

		return extensionKEX;
	}

	private Document createPluginDocument() throws KielerException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);

		DocumentBuilder docBuilder = null;
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException p) {
			throw new KielerException(ErrorMessage.DOC_BUILDER_NEW_ERROR + ", "
					+ p.getLocalizedMessage());
		}

		DOMImplementation impl = docBuilder.getDOMImplementation();
		Document doc = impl.createDocument(null, null, null);
		Element root = doc.createElement(ExtPointConstants.PLUGIN);
		doc.appendChild(root);

		Element child = doc.createElement(ExtPointConstants.EXTENSION);
		child.setAttribute(ExtPointConstants.POINT,
				ExtPointConstants.KEX_EXT_POINT);
		root.appendChild(child);
		return doc;
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

			destLocation.append(destPath).append(File.separatorChar).append(
					resource.getLocalPath());
			Path destination = new Path(destLocation.toString());
			errorList.add(destination);

			IOHandler.writeFile(new File(sourcePath), destination.toFile());
		} catch (IOException e) {
			throw new KielerException(ErrorMessage.PLUGIN_WRITE_ERROR
					+ ",\ndestination: " + destPath + ", resource: "
					+ resource.getLocalPath().toPortableString());
		}
	}

	public void deleteExampleResource(List<IPath> resources) {
		for (IPath path : resources)
			IOHandler.deleteFile(path.toFile());
	}

	private void addExampleCategories(Node pluginNode,
			List<String> creatableCategories) {

		boolean isDuplicate = false;
		for (String category : creatableCategories) {

			checkDuplicate(ExtPointConstants.ID, category, pluginNode,
					isDuplicate);
		}

		if (isDuplicate) {
			// TODO fehlerfall ï¿½berlegen
		}
		List<String> creates = creatableCategories;
		for (String creatable : creates) {
			Element createdCategory = parsedXML
					.createElement(ExtPointConstants.CATEGORY);
			createdCategory.setAttribute(ExtPointConstants.ID, creatable);
			pluginNode.appendChild(createdCategory);

		}

	}

	private void writePluginXML(String pluginPath) throws KielerException {
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			DOMSource source = new DOMSource(parsedXML);
			FileOutputStream os = new FileOutputStream(new File(pluginPath));
			StreamResult result = new StreamResult(os);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.setParameter("eclipse version", "3.4");
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

	// try {
	// DOMSource domSource = new DOMSource(doc);
	// TransformerFactory tf = TransformerFactory.newInstance();
	// Transformer transformer = tf.newTransformer();
	// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	// transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	// transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	// transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
	// transformer.setParameter("eclipse version", "3.4");
	// StreamResult sr = new StreamResult(new FileOutputStream(path));
	// transformer.transform(domSource, sr);
	// } catch (FileNotFoundException f) {
	// throw new KielerException(ErrorMessage.TRANSFORM_ERROR + ", "
	// + f.getLocalizedMessage());
	// } catch (TransformerException t) {
	// throw new KielerException(ErrorMessage.TRANSFORM_ERROR + ", "
	// + t.getLocalizedMessage());
	// }
	// return new File(path);

	private void throwWritePluginError(Throwable e) throws KielerException {
		throw new KielerException(new StringBuffer().append(
				ErrorMessage.NOT_WRITE_PLUGIN).append(e.getLocalizedMessage())
				.toString());
	}

	private Node toNode(String categoryId) {
		Element createdElement = parsedXML
				.createElement(ExtPointConstants.CATEGORY);
		createdElement.setAttribute(ExtPointConstants.ID, categoryId);
		return createdElement;
	}

	private Node toNode(Example example, File location) throws KielerException {
		Element createdExample = parsedXML
				.createElement(ExtPointConstants.EXAMPLE);
		createdExample
				.setAttribute(ExtPointConstants.TITLE, example.getTitle());
		createdExample.setAttribute(ExtPointConstants.DESCRIPTION, example
				.getDescription());
		createdExample.setAttribute(ExtPointConstants.GENERATION_DATE, example
				.getGenerationDate().toString());
		createdExample.setAttribute(ExtPointConstants.VERSION, example
				.getVersion().toString());

		makeRootSource(location, example);

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
				relativePath + "/" + exResource.getLocalPath());
		createdExResource.setAttribute(ExtPointConstants.RESOURCE_TYPE,
				ExampleResource.Type.map(exResource.getResourceType()));
		createdExResource.setAttribute(ExtPointConstants.DIRECT_OPEN, Boolean
				.toString(exResource.isDirectOpen()));
		return createdExResource;

	}

}
