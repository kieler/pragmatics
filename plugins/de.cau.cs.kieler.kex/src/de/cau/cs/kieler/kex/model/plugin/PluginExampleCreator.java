/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kex.model.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class PluginExampleCreator {

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
    private Document parseDocument(final File file) throws SAXException, IOException,
            ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }

    /**
     * NOTE: parseElement could only be a type of Example or a example category in String
     * representation.
     * 
     * @param location
     *            , {@link File}
     * @param parseElement
     *            , {@link Example}
     * @param creatableCategories
     *            , {@link List} of {@link String}s
     * @param absOverviewPic
     *            , {@link String}
     * @throws KielerException
     *             , if duplicatechecks fails or can be thrown by getPluginNode(...).
     */
    public void addExtension(final File location, final Example parseElement,
            final List<String> creatableCategories, final String absOverviewPic)
            throws KielerException {

        Node pluginNode = getPluginNode(location);
        Node extensionKEX = filterExtensionKEX(pluginNode);

        checkDuplicate(extensionKEX, parseElement.getTitle(), creatableCategories);

        parseElement.setOverviewPic(createLocalPluginPath(absOverviewPic));

        addExampleCategories(extensionKEX, creatableCategories);

        extensionKEX.appendChild(toNode(parseElement, location));
        writePluginXML(pluginXML.getAbsolutePath());
    }

    // TODO testen...
    private void checkDuplicate(final Node extensionKEX, final String exampleTitle,
            final List<String> creatableCategories) throws KielerException {
        NodeList childNodes = extensionKEX.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (PluginConstants.CATEGORY.equals(nodeName)) {
                Node namedItem = item.getAttributes().getNamedItem(PluginConstants.ID);
                if (namedItem != null) {
                    for (String creatableCategory : creatableCategories) {
                        if (creatableCategory.equals(namedItem.getNodeValue())) {
                            throw new KielerException(ErrorMessage.DUPLICATE_ELEMENT
                                    + "The category \"" + creatableCategory
                                    + "\" exists already in choosen plugin project.");
                        }
                    }
                }
            } else if (PluginConstants.EXAMPLE.equals(nodeName)) {
                Node namedItem = item.getAttributes().getNamedItem(PluginConstants.TITLE);
                if (namedItem != null && exampleTitle.equals(namedItem.getNodeValue())) {
                    throw new KielerException(ErrorMessage.DUPLICATE_ELEMENT + "The example \""
                            + exampleTitle + "\" exists already in choosen plugin project.");
                }
            }
        }
    }

    private String createLocalPluginPath(final String absOverviewPic) {
        String projectPath = this.pluginXML.getAbsolutePath().substring(0,
                this.pluginXML.getAbsolutePath().length() - IOHandler.PLUGIN_XML.length() - 1);
        return new Path(absOverviewPic.substring(projectPath.length())).toPortableString();
    }

    private void makeRootSource(final File location, final Example example) throws KielerException {
        File project = IOHandler.searchUP(location, IOHandler.PROJECT_FILE).getParentFile();
        String relativeLocation = location.getPath().substring(project.getPath().length());
        example.setRootDir((relativeLocation.length() > 0) ? relativeLocation.substring(1)
                : relativeLocation);
    }

    private Node getPluginNode(final File locationFile) throws KielerException {

        try {
            this.pluginXML = IOHandler.filterPluginXML(locationFile);
            if (IOHandler.PLUGIN_XML.equals(this.pluginXML.getName())) {
                parsedXML = parseDocument(this.pluginXML);
            } else {
                // project path + .project file as path
                this.pluginXML = new File(this.pluginXML.getAbsolutePath() + File.separatorChar
                        + IOHandler.PLUGIN_XML);
                parsedXML = createPluginDocument();
            }
        } catch (ParserConfigurationException e) {
            String msg = ErrorMessage.NOT_PARSE_PLUGIN + "\n" + e.getLocalizedMessage();
            throw new KielerException(msg, e);
        } catch (SAXException e) {
            String msg = ErrorMessage.NOT_PARSE_PLUGIN + "\n" + e.getLocalizedMessage();
            throw new KielerException(msg, e);
        } catch (IOException e) {
            String msg = ErrorMessage.NOT_PARSE_PLUGIN + "\n" + e.getLocalizedMessage();
            throw new KielerException(msg, e);
        }

        NodeList plugins = this.parsedXML.getElementsByTagName(PluginConstants.PLUGIN);
        if (plugins.getLength() == 1) {
            return plugins.item(0);
        }
        throw new KielerException("Could not filter plugin node. " + locationFile.getPath());
    }

    private Node filterExtensionKEX(final Node pluginNode) {
        Node extensionKEX = null;
        NodeList nodes = pluginNode.getChildNodes();
        int length = nodes.getLength();
        for (int i = 0; i < length; i++) {
            Node node = nodes.item(i);
            if (PluginConstants.EXTENSION.equals(node.getNodeName())) {
                NamedNodeMap attributes = node.getAttributes();
                Node namedItem = attributes.getNamedItem(PluginConstants.POINT);
                if (PluginConstants.KEX_EXT_POINT.equals(namedItem.getNodeValue())) {
                    extensionKEX = node;
                    break;
                }
            }
        }
        if (extensionKEX == null) {

            Element extElement = pluginNode.getOwnerDocument().createElement(
                    PluginConstants.EXTENSION);
            extElement.setAttribute(PluginConstants.POINT, PluginConstants.KEX_EXT_POINT);
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
        Element root = doc.createElement(PluginConstants.PLUGIN);
        doc.appendChild(root);

        Element child = doc.createElement(PluginConstants.EXTENSION);
        child.setAttribute(PluginConstants.POINT, PluginConstants.KEX_EXT_POINT);
        root.appendChild(child);
        return doc;
    }

    /**
     * creates example files to given location.
     * 
     * @param finishedResources
     *            , {@link List} of {@link IPath}
     * 
     * @param resources
     *            , {@link List} of {@link ExportResource}
     * 
     * @param destFile
     *            , {@link File}
     * @throws KielerException
     *             , if copyResource(...) throws it.
     */
    public void copyResources(final File destFile, final List<ExportResource> resources,
            final List<IPath> finishedResources) throws KielerException {
        for (ExportResource resource : resources) {
            copyResource(resource, destFile.getPath(), finishedResources);
        }
    }

    private void copyResource(final ExportResource resource, final String destPath,
            final List<IPath> finishedResources) throws KielerException {
        StringBuilder destLocation = new StringBuilder();
        try {

            String sourcePath = this.workspacePath.toOSString()
                    + resource.getResource().getFullPath().toOSString();

            destLocation.append(destPath).append(File.separatorChar)
                    .append(resource.getLocalPath());
            IPath destination = Path.fromPortableString(destLocation.toString());
            finishedResources.add(destination);

            IOHandler.writeResource(new File(sourcePath), destination.toFile());
        } catch (IOException e) {
            throw new KielerException(e.getMessage());
        }
    }

    /**
     * Deletes resources.
     * 
     * @param resources
     *            , {@link List} of {@link IPath}.
     */
    public void deleteExampleResources(final List<IPath> resources) {
        for (IPath path : resources) {
            IOHandler.deleteFile(path.toFile());
        }
    }

    private boolean addExampleCategories(final Node node, final List<String> creatableCategories) {
        List<String> creates = creatableCategories;
        for (String creatable : creates) {
            Element createdCategory = parsedXML.createElement(PluginConstants.CATEGORY);
            createdCategory.setAttribute(PluginConstants.ID, creatable);
            node.appendChild(createdCategory);
        }
        return true;
    }

    private void writePluginXML(final String pluginPath) throws KielerException {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
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

    private void throwWritePluginError(final Throwable e) throws KielerException {
        throw new KielerException(new StringBuffer().append(ErrorMessage.NOT_WRITE_PLUGIN)
                .append(e.getLocalizedMessage()).toString());
    }

    private Node toNode(final String categoryId) {
        Element createdElement = parsedXML.createElement(PluginConstants.CATEGORY);
        createdElement.setAttribute(PluginConstants.ID, categoryId);
        return createdElement;
    }

    private Node toNode(final Example example, final File location) throws KielerException {
        Element createdExample = parsedXML.createElement(PluginConstants.EXAMPLE);
        createdExample.setAttribute(PluginConstants.TITLE, example.getTitle());
        createdExample.setAttribute(PluginConstants.DESCRIPTION, example.getDescription());
        createdExample.setAttribute(PluginConstants.GENERATION_DATE, example.getGenerationDate()
                .toString());
        makeRootSource(location, example);

        String overviewPicPath = example.getOverviewPic();
        if (overviewPicPath != null) {
            createdExample.setAttribute(PluginConstants.OVERVIEW_PIC, overviewPicPath);
        }
        String author = example.getAuthor();
        if (author != null) {
            createdExample.setAttribute(PluginConstants.AUTHOR, author);
        }

        String contact = example.getContact();
        if (contact != null) {
            createdExample.setAttribute(PluginConstants.CONTACT, contact);
        }

        String rootDirectory = example.getRootDir();
        if (rootDirectory != null) {
            createdExample.setAttribute(PluginConstants.ROOT_DIRECTORY, rootDirectory);
        }

        for (String category : example.getCategories()) {
            createdExample.appendChild(toNode(category));
        }

        for (ExampleResource exResource : example.getResources()) {
            createdExample.appendChild(toNode(rootDirectory, exResource));
        }
        return createdExample;

    }

    private Node toNode(final String relativePath, final ExampleResource exResource) {
        Element createdExResource = parsedXML.createElement(PluginConstants.EXAMPLE_RESOURCE);
        createdExResource.setAttribute(PluginConstants.LOCAL_PATH,
                relativePath + "/" + exResource.getLocalPath());
        createdExResource.setAttribute(PluginConstants.RESOURCE_TYPE,
                ExampleResource.Type.map(exResource.getResourceType()));
        createdExResource.setAttribute(PluginConstants.DIRECT_OPEN,
                Boolean.toString(exResource.isDirectOpen()));
        return createdExResource;

    }

    /**
     * Copies the preview picture of an example.
     * 
     * @param destPath
     *            {@link String}
     * @param sourcePath
     *            , {@link String}
     * @param finishedResources
     *            , {@link List} of {@link IPath}
     * @return {@link String}
     * @throws KielerException
     *             , if an IOHandler.writeResource throws an exception.
     */
    public String copyOverviewPic(final String destPath, final String sourcePath,
            final List<IPath> finishedResources) throws KielerException {
        File file = new File(sourcePath);
        String destLocation = destPath + File.separatorChar + file.getName();
        IPath destination = Path.fromPortableString(destLocation.toString());
        finishedResources.add(destination);
        try {
            IOHandler.writeResource(new File(sourcePath), destination.toFile());
        } catch (IOException e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append(ErrorMessage.PLUGIN_WRITE_ERROR).append("\ndestination: ")
                    .append(destPath).append(", image: ").append(sourcePath);
            throw new KielerException(errorMessage.toString());
        }
        return destLocation;

    }

    /**
     * Makes a absolute path, relative to export project of workspace.
     * 
     * @param projectPath
     *            {@link String}
     * @param absolutePath
     *            {@link String}
     * @return {@link String}
     */
    public String makeRelativePath(final String projectPath, final String absolutePath) {
        // TODO der projekt pfad wird bei filerPluginProjekt ermittelt,
        // der muss hier herein gereicht werden und der vom absolutenpath
        // abgetrennt werden.
        return null;
    }

}
