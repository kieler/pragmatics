/**
 * Java Electronic Tool Integration - jETI
 * Copyright (C) 2004-2011 Chair for Programming Systems, TU Dortmund
 *
 * This file is part of jETI.
 *
 * jETI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * jETI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with jETI. If not, see <http://www.gnu.org/licenses/>.
 */
package de.unido.ls5.eti.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This class provides some static helper methods for (de)serialization of
 * the jETI Tool Descriptor (tools.xml).
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 */

public class ToolXmlHelper {

/**
 * String which is used for one level of indentation
 */
private static String INDENT = "  ";


/**
 *  log4j-Logger
 */
private static final Logger logger = Logger.getLogger(ToolXmlHelper.class);
	

/**
 * Gets an attribute of a node, identified by name. If no attribute
 * with the specified name is found, "" is returned
 * 
 * @param node The node where attribute shall be retrieved from. 
 * See also: {@link Node#getAttributes()}
 * 
 * @param name the name of the attribute to search for
 * 
 * @return The value of the attribute or "".
 */
private static String getAttribute(Node node, String name) {
	String s = "";
   	NamedNodeMap attributes = node.getAttributes();
   	if (attributes.getNamedItem(name) != null) {
   		s = attributes.getNamedItem(name).getNodeValue();
   	}
   	return s;
}
   
   
   
/**
 * Analyzes a "&lt;parameter&gt;" node from tools.xml and instanciates
 * an appropriate {@link Parameter} object.
 * 
 * @param node The "&lt;parameter&gt;" node that is analyzed.
 * 
 * @return The parameter object that corresponds to the node.
 */
private static Parameter parseParameterNode(Node node) {
	
 	Parameter param = new Parameter();
 	
 	param.setClassIdentifier(getAttribute(node, "class"));
 	param.setDefaultValue(getAttribute(node,"default"));
 	param.setDescription(getAttribute(node,"description"));
 	param.setName(getAttribute(node,"name"));
 	if (getAttribute(node,"required").equals("true"))
		param.setRequired(true);
	if (getAttribute(node,"contextExpression").equals("true"))
		param.setContextExpression(true);
 	param.setValue(getAttribute(node,"value"));
 	param.setClassArgument(getAttribute(node, "classargument"));	
	return param;
}
 
 
/**
 * Analyzes a "&lt;union&gt;" node from tools.xml and instanciates
 * an appropriate {@link ParameterUnion} object.
 * 
 * @param node The "&lt;union&gt;" node that is analyzed.
 * 
 * @return The union object that corresponds to the node.
 */
private static ParameterUnion parseUnionNode(Node node) {
	ParameterUnion union = new ParameterUnion();
	
	NodeList nodes = node.getChildNodes();
	
	for (int i = 0; i < nodes.getLength(); i++) {
		
		Node currentNode = nodes.item(i);
		
		if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
			
			if (currentNode.getNodeName().equals("parameter")) {
				Parameter p = parseParameterNode(currentNode); 
				union.addEtiParameter(p);				
			}
		}
		
	}	
	
	return union;
}


/**
 * Analyzes an "&lt;array&gt;" node from tools.xml and instanciates
 * an appropriate {@link ParameterArray} object.
 * 
 * @param node The "&lt;array&gt;" node that is analyzed.
 * 
 * @return The array object that corresponds to the node.
 */
private static ParameterArray parseArrayNode (Node node) {
	ParameterArray array = new ParameterArray();
	
	array.setClassIdentifier(getAttribute(node,"class"));
	
	NodeList nodes = node.getChildNodes();
	
	for (int i = 0; i < nodes.getLength(); i++) {
		
		Node currentNode = nodes.item(i);
		
		if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
			
			if (currentNode.getNodeName().equals("parameter")) {
				Parameter p = parseParameterNode(currentNode); 
				array.addEtiParameter(p);
				
			} else if (currentNode.getNodeName().equals("union")) {
				ParameterUnion u = parseUnionNode(currentNode);
				array.addEtiParameterUnion(u);
			}
		}
		
	}	
	
	return array;	
}

   
    
/**
 * Analyzes an "&lt;tool&gt;" node from tools.xml and instanciates
 * an appropriate {@link Tool} object.
 * 
 * @param node The "&lt;tool&gt;" node that is analyzed.
 * 
 * @return The tool object that corresponds to the node.
 */
private static Tool parseToolNode(Node node) {    	


	Tool tool = new Tool();

	if (getAttribute(node,"active").equals("true"))
		tool.setActive(true);
	tool.setClassIdentifier(getAttribute(node,"class"));
	tool.setMethodIdentifier(getAttribute(node,"method"));
	tool.setName(getAttribute(node,"name"));


	NodeList nodes = node.getChildNodes();

	for (int i = 0; i < nodes.getLength(); i++) {

		Node currentNode = nodes.item(i);

		if (currentNode.getNodeType() == Node.ELEMENT_NODE) {

			if (currentNode.getNodeName().equals("description")) {
				String description = "";
				Node descChild = currentNode.getFirstChild();

				if ((descChild != null) && (descChild.getNodeValue() != null)) {
					description = descChild.getNodeValue();
				}

				tool.setDescription(description); 
			} else if (currentNode.getNodeName().equals("longDescription")) {
				String description = "";
				Node descChild = currentNode.getFirstChild();

				if ((descChild != null) && (descChild.getNodeValue() != null)) {
					description = descChild.getNodeValue();
				}
				tool.setLongDescription(description); 
			} else if (currentNode.getNodeName().equals("icon")) {
				logger.warn("<icon> tag in Tool Descriptor found (tool: " +tool.getName() + "). I will ignore it, but you should remove it (it is deprecated)");
			} else if (currentNode.getNodeName().equals("certificate")) {
				/*
				 * Certificates.. added 30-04-2007
				 */
				String type = getAttribute(currentNode, "type");
				tool.addCertificateType(type);
				// System.out.println ("FoundCERT: " + type);
			}	else if (currentNode.getNodeName().equals("array")) {					
				ParameterArray a = parseArrayNode(currentNode);
				tool.addEtiParameterArray(a);

			} else if (currentNode.getNodeName().equals("union")) {
				ParameterUnion u = parseUnionNode(currentNode);
				tool.addEtiParameterUnion(u);			

			} else if (currentNode.getNodeName().equals("parameter")) {
				Parameter p = parseParameterNode(currentNode); 
				tool.addEtiParameter(p);
			}
		}

	}
	return tool;
}
    
    
/**
 * Parses a Tool Descriptor file (tools.xml) into {@link Tool} objects.
 * If xmlfile points to a non-existing file, an empty map will be returned.
 * 
 * @param xmlfile The Tool Descriptor file which shall be parsed
 * 
 * @return Map with tool names as keys
 * 
 * @throws IllegalArgumentException if the {@code xmlfile} if malformed, e.g. is not
 *
 * @throws SAXException if  {@link DOMParser#parse(InputSource)} throws it.
 * 
 * @throws IOException if {@link DOMParser#parse(InputSource)} throws it.
 * 
 * @throws NullPointerException if xmlfile == null
 */
public static Map<String, Tool>parseToolXML(File xmlfile)
throws IllegalArgumentException, SAXException, IOException {
	
	if (xmlfile == null)
		throw new NullPointerException("XML file to parse was null");

	Map<String, Tool> hm = new TreeMap<String, Tool>();
	
	if (!xmlfile.exists())
		return hm;
	
	DOMParser parser = new DOMParser();
	parser.parse(new InputSource(new FileInputStream(xmlfile)));
	Document document = parser.getDocument();
	NodeList nodes = document.getChildNodes();

	if ((nodes.getLength() == 1) && (nodes.item(0).getNodeName().equals("etitoolserver"))) {
		NodeList childNodes = nodes.item(0).getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node currentNode = childNodes.item(i);
			if ( (currentNode.getNodeType() == Node.ELEMENT_NODE) && currentNode.getNodeName().equals("tool")) {
				Tool tool = parseToolNode(currentNode);
				hm.put(tool.getName(), tool);
			}
		}       	

	} else {
		throw new IllegalArgumentException("XML file must start with etitoolserver tag");
	}     

	return hm;
}

/**
 * serializes a {@link Parameter} into an xml snipped (&lt;parameter..../>)
 * 
 * @param p The parameter that shall be serialized
 * @param indent String of white spaces that is put at the beginning of the return string. 
 * can be used to correctly indent the whole XML file.
 * @return S
 */
    private static String generateParameterXML(Parameter p, String indent) {
    	StringBuffer sb = new StringBuffer();
    	
    	sb.append(indent);
    	sb.append("<parameter ");
    	if (!p.getName().equals("")) sb.append("name=\"" + xmlize(p.getName()) + "\" ");
    	if (!p.getClassIdentifier().equals("")) sb.append("class=\"" + xmlize(p.getClassIdentifier()) + "\" ");
    	if (!p.getClassArgument().equals("")) sb.append("classargument=\"" + xmlize(p.getClassArgument()) + "\" ");
    	if (p.isRequired()) sb.append("required=\"true\" ");
		if (p.isContextExpression()) sb.append("contextExpression=\"true\" ");
    	if (!p.getDefaultValue().equals("")) sb.append("default=\"" + xmlize(p.getDefaultValue()) + "\" ");
    	if (!p.getValue().equals("")) sb.append("value=\"" + xmlize(p.getValue()) + "\" ");
    	if (!p.getDescription().equals("")) sb.append("description=\"" + xmlize(p.getDescription()) + "\" ");
    	sb.append("/>\n");
    	
    	return sb.toString();
    	
    }
    
    /**
     * Generate the XML code for the certificates
     * 
     * @param certs names of certificates
     * @return the tool descriptor elements\" XML code for the certificates
     */
    private static String generateCertificatesXML (List<String> certs, String indent) {
    	String value = "";
    	Iterator<String> iter = certs.iterator();
		while (iter.hasNext()) {
			value = value + (indent + "<certificate type=\""+iter.next()+"\"/>\n");
		}
		return value;
    }
    
    
    
    private static String generateUnionXML(ParameterUnion u, String indent) {
    	StringBuffer sb = new StringBuffer();
    	
    	sb.append(indent + "<union>\n");
    	
    	Iterator i = u.getUnionElements().iterator();
    	while (i.hasNext()) {
    		sb.append(generateParameterXML((Parameter)i.next(), indent + INDENT));
    		
    	}    	
    	
    	sb.append(indent + "</union>\n");
    	    	
    	return sb.toString();    	
    }
    
    
    
    private static String generateArrayXML(ParameterArray a, String indent) {
    	StringBuffer sb = new StringBuffer();
    	
    	sb.append(indent + "<array");
    	if (!a.getClassIdentifier().equals("")) sb.append(" class=\"" + xmlize(a.getClassIdentifier()) + "\"");
    	sb.append(">\n");
    	
    	Iterator i = a.getElements().iterator();
    	while (i.hasNext()) {
    		Object arrayElement = i.next();
    		if (arrayElement instanceof Parameter) {
    			sb.append(generateParameterXML((Parameter)arrayElement, indent + INDENT));
    		}
    		else if (arrayElement instanceof ParameterUnion) {
    			sb.append(generateUnionXML((ParameterUnion)arrayElement, indent + INDENT));
    		}
    	}
    	
    	sb.append(indent + "</array>\n");
    	
    	return sb.toString();
    	
    }
    
    
    
    
    /** Creates the XML File (as String) from a HashMap of EtiTool Objects
     * 
     * @param hm
     * 		The HashMap which contains the EtiTool Objects
     * @return
     * 		XML formatted String to save as tools.xml file
     */
    public static String generateToolXML(Map<String, Tool> hm) {
    	StringBuffer sb = new StringBuffer();
    	
    	sb.append("<etitoolserver>\n");
    	
    	Iterator iTools = hm.keySet().iterator();
    	
    	while (iTools.hasNext()) {
    		Tool tool = hm.get(iTools.next());
    		
    		sb.append("\n");
    		sb.append(INDENT + "<tool name=\"" + xmlize(tool.getName()) + "\" active=\"" + String.valueOf(tool.isActive()) + "\" class=\"" + xmlize(tool.getClassIdentifier()) + "\" method=\"" + xmlize(tool.getMethodIdentifier()) + "\">\n");
    		sb.append(INDENT + INDENT + "<description>" + xmlize(tool.getDescription()) + "</description>\n");
    		sb.append(INDENT + INDENT + "<longDescription>" + xmlize(tool.getLongDescription()) + "</longDescription>\n");
    		/*
    		 * Certificates
    		 */
    		sb.append (generateCertificatesXML(tool.getCertificateTypes(), INDENT+INDENT));
    		
    		
    		// Parameters  		
    		Iterator iParameters = tool.getElements().iterator();
    		while (iParameters.hasNext()) {
    			Object currentElement = iParameters.next();
    			
    			if (currentElement instanceof Parameter) {
    				Parameter p = (Parameter)currentElement;
    				// System.err.println("sb.append(generateParameterXML(p, INDENT + INDENT));");
    				sb.append(generateParameterXML(p, INDENT + INDENT));    				
    			}
    			
    			else if (currentElement instanceof ParameterUnion) {
    				ParameterUnion u = (ParameterUnion)currentElement;
    				sb.append(generateUnionXML(u, INDENT + INDENT));
    			}
    			
    			else if (currentElement instanceof ParameterArray) {
    				ParameterArray a = (ParameterArray)currentElement;
    				sb.append(generateArrayXML(a, INDENT + INDENT));
    			}   			
    					
    		}    		
    		
    		sb.append(INDENT + "</tool>\n");    		
    	}
    	
    	sb.append("\n");
    	sb.append("</etitoolserver>\n");
    	
    	return sb.toString();
    }


    /**
     * Short wrapper method for 
     * {@link StringEscapeUtils#escapeXml(String)}
     * @param s
     * @return
     */
    private static String xmlize(String s) {
    	return StringEscapeUtils.escapeXml(s);
    }
    
    
}
