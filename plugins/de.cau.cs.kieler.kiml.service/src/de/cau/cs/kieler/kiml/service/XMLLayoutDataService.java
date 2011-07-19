/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.service;

import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutTypeData;

/**
 * .
 *
 * @kieler.rating 2011-05-17 red
 * @author swe
 */
public abstract class XMLLayoutDataService extends LayoutDataService {

    /** */
    private static final String ELEMENT_NAME_CAPABILITIES     
    	= "capabilities";

    /** */
    private static final String ELEMENT_NAME_CATEGORY         
    	= "category";

    /** */
    private static final String ELEMENT_NAME_LAYOUTALGORITHM  
    	= "layoutAlgorithm";

    /** */
    private static final String ELEMENT_NAME_LAYOUTTYPE       
    	= "layoutType";

    /** */
    private static final String ELEMENT_NAME_LAYOUTOPTION     
    	= "layoutOption";

    /** */
    private static final String ELEMENT_NAME_KNOWNOPTION      
    	= "knownOption";

    /** */
    private static final String ELEMENT_NAME_SUPPORTEDDIAGRAM 
    	= "supportedDiagram";

    /** name of the 'advanced' attribute in the extension points. */
    public static final String ATTRIBUTE_ADVANCED 
    	= "advanced";
    
    /** name of the 'appliesTo' attribute in the extension points. */
    public static final String ATTRIBUTE_APPLIESTO 
    	= "appliesTo";
    
    /** name of the 'category' attribute in the extension points. */
    public static final String ATTRIBUTE_CATEGORY 
    	= "category";
    
    /** name of the 'class' attribute in the extension points. */
    public static final String ATTRIBUTE_CLASS 
    	= "class";
    
    /** name of the 'default' attribute in the extension points. */
    public static final String ATTRIBUTE_DEFAULT 
    	= "default";
    
    /** name of the 'description' attribute in the extension points. */
    public static final String ATTRIBUTE_DESCRIPTION 
   		= "description";
    
    /** name of the 'id' attribute in the extension points. */
    public static final String ATTRIBUTE_ID
    	= "id";
    
    /** name of the 'name' attribute in the extension points. */
    public static final String ATTRIBUTE_NAME 
    	= "name";
    
    /** name of the 'option' attribute in the extension points. */
    public static final String ATTRIBUTE_OPTION 
    	= "option";
    
    /** name of the 'parameter' attribute in the extension points. */
    public static final String ATTRIBUTE_PARAMETER 
    	= "parameter";
    
    /** name of the 'priority' attribute in the extension points. */
    public static final String ATTRIBUTE_PRIORITY 
    	= "priority";
    
    /** name of the 'type' attribute in the extension points. */
    public static final String ATTRIBUTE_TYPE 
    	= "type";

    /**
     * 
     */
    protected XMLLayoutDataService() {
    }

    /**
     * 
     * @param reader
     * @return
     */
    protected boolean initializeFromReader(Reader reader) {
        if (reader == null) {
            return false;
        }
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            CapabilitiesHandler ch = new CapabilitiesHandler();
            xr.setContentHandler(ch);
            xr.setErrorHandler(ch);
            xr.parse(new InputSource(reader));
            return true;
        } catch (Exception e) {
        	reportError("Error while parsing layout server meta data", e);
        }
        return false;
    }

    /**
     * 
     * @param message
     */
    protected abstract void reportError(String message);

    /**
     * 
     * @param message
     * @param exception
     */
    protected abstract void reportError(String message, Exception exception);

    // ********** SAX handler for parsing service capabilities.

    /**
     * Private class for creating the internal representation of the
     * layout capabilities.
     */
    //TODO implement
    private class CapabilitiesHandler extends DefaultHandler {

        private Registry registry          
        	= getRegistry();
        private String lastProviderId    
        	= null;
        private List<String[]> knownOptions    
        	= new LinkedList<String[]>();
        private List<String[]> supportedDiagrams 
        	= new LinkedList<String[]>();

        public CapabilitiesHandler() {
            super();
        }

        public void startDocument() {
        }


        public void endDocument() {
        }

        public void startElement(final String uri, final String name,
        	final String qName, final Attributes atts) {            
            if ( name.equals(ELEMENT_NAME_LAYOUTALGORITHM)) {
                createLayoutAlgoritm(atts);
            } else if (name.equals(ELEMENT_NAME_KNOWNOPTION)) {
                createKnownOption(atts);
            } else if (name.equals(ELEMENT_NAME_SUPPORTEDDIAGRAM)) {
                createSupportedDiagram(atts);
            } else if (name.equals(ELEMENT_NAME_LAYOUTTYPE)) {
                createLayoutType(atts);
            } else if ( name.equals(ELEMENT_NAME_CATEGORY)) {
                createCategory(atts);
            } else if ( name.equals(ELEMENT_NAME_LAYOUTOPTION)) {
                createLayoutOption(atts);
            }
        }

        /**
         * 
         * @param atts
         */
        private void createLayoutAlgoritm(final Attributes atts) {
            LayoutAlgorithmData algoData = new LayoutAlgorithmData();
            lastProviderId = atts.getValue(ATTRIBUTE_ID);
            if (lastProviderId == null || lastProviderId.length() == 0) {
                reportError("createLayoutAlgoritm: no last provider id set" + attributesToString(atts));
                return;
            }
            algoData.setId(lastProviderId);
            algoData.setName(atts.getValue(ATTRIBUTE_NAME));
            algoData.setDescription(atts.getValue(ATTRIBUTE_DESCRIPTION));
            algoData.setCategory(atts.getValue(ATTRIBUTE_CATEGORY));
            String layoutType = atts.getValue(ATTRIBUTE_TYPE);
            if (layoutType == null) {
                layoutType = "";
            }
            LayoutTypeData typeData = getTypeData(layoutType);
            if (typeData == null) {
                typeData = new LayoutTypeData();
                typeData.setId(layoutType);
                registry.addLayoutType(typeData);
            }
            algoData.setType(layoutType);
            typeData.getLayouters().add(algoData);            
            registry.addLayoutProvider(algoData);            
        }

        private void createKnownOption(Attributes atts) {

            if (lastProviderId == null) {
                reportError("createKnownOption: no last provider id set" + attributesToString(atts));
                return;
            }

            String option = atts.getValue(ATTRIBUTE_OPTION);

            if (option == null || option.length() == 0) {
                reportError("createKnownOption: option attribute not valid " + attributesToString(atts));
            }

            String defauld = atts.getValue(ATTRIBUTE_DEFAULT);

            knownOptions.add(new String[] { lastProviderId, option, defauld });

        }

        private void createSupportedDiagram(Attributes atts) {

            String type = atts.getValue(ATTRIBUTE_TYPE);

            if (type == null || type.length() == 0) {
                reportError("createSupportedDiagram: type attribute not valid " + attributesToString(atts));
                return;
            }

            supportedDiagrams.add(new String[] { lastProviderId, type, atts.getValue(ATTRIBUTE_PRIORITY) });

        }

        private void createLayoutType(Attributes atts) {

            String id = atts.getValue(ATTRIBUTE_ID);

            if (id == null || id.length() == 0) {
                reportError("createLayoutType: id attribute not valid " + attributesToString(atts));
                return;
            }

            LayoutTypeData typeData = new LayoutTypeData();

            typeData.setId(id);
            typeData.setName(atts.getValue(ATTRIBUTE_NAME));
            typeData.setDescription(atts.getValue(ATTRIBUTE_DESCRIPTION));

            registry.addLayoutType(typeData);

        }

        private void createCategory(Attributes atts) {

            String id   = atts.getValue(ATTRIBUTE_ID);
            String name = atts.getValue(ATTRIBUTE_NAME);

            if (id == null || id.length() == 0) {
                reportError("createCategory: id attribute not valid " + attributesToString(atts));
            } else if (name == null || name.length() == 0) {
                reportError("createCategory: name attribute not valid " + attributesToString(atts));
            } else {
                registry.addCategory(id, name);
            }

        }

        private void createLayoutOption(Attributes atts) {

            LayoutOptionData<Object> optionData = new LayoutOptionData<Object>();

            String id = atts.getValue(ATTRIBUTE_ID);

            if (id == null || id.length() == 0) {
                reportError("createLayoutOption: id attribute not valid " + attributesToString(atts));
                return;
            }

            optionData.setId(id);
            
            String type = atts.getValue(ATTRIBUTE_TYPE);

            if (type == null || type.length() == 0) {
                reportError("createLayoutOption: type attribute not valid " + attributesToString(atts));
                return;
            }

            try {
                optionData.setType(type);
            } catch (IllegalArgumentException exception) {
                reportError("createLayoutOption: type attribute not valid " + attributesToString(atts));
                return;
            }

            String className = atts.getValue(ATTRIBUTE_CLASS);

            if (className == null || className.length() == 0) {
            	if ( type.equalsIgnoreCase("object") || type.equalsIgnoreCase("enum") ) {
            		reportError("createLayoutOption: class attribute not valid " + attributesToString(atts));
            	}
            } else {

                Class<?> clas = null;

                try {
                    clas = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    reportError("createLayoutOption: class not found" + attributesToString(atts));
                }

                optionData.setOptionClass(clas);

            }

            try {

                Object defauld = optionData.parseValue(
                    atts.getValue(ATTRIBUTE_DEFAULT));

                optionData.setDefault(defauld);

            } catch (IllegalStateException exception) {
                reportError("createLayoutOption: default attribute not valid " + attributesToString(atts));
            }

            optionData.setName(atts.getValue(ATTRIBUTE_NAME));
            optionData.setDescription(atts.getValue(ATTRIBUTE_DESCRIPTION));
            optionData.setTargets(atts.getValue(ATTRIBUTE_APPLIESTO));

            String advanced = atts.getValue(ATTRIBUTE_ADVANCED);

            optionData.setAdvanced(advanced != null && advanced.equals("true"));

            registry.addLayoutOption(optionData);

        }

        public void endElement(String uri, String name, String qName) {

            if (name.equals(ELEMENT_NAME_LAYOUTALGORITHM)) {
                lastProviderId = null;
            } else if (name.equals(ELEMENT_NAME_CAPABILITIES)) {
                storeKnownOptions();
                storeSupportedDiagrams();
            }

        }

        private void storeKnownOptions() {

            for (String[] entry : knownOptions) {

                LayoutAlgorithmData algoData   = getAlgorithmData(entry[0]);
                LayoutOptionData<?> optionData = getOptionData(entry[1]);

                if (algoData != null && optionData != null) {

                    try {

                        Object defaultValue = optionData.parseValue(entry[2]);

                        algoData.setOption(optionData, defaultValue);

                    } catch (IllegalStateException exception) {
                        reportError("storeKnownOptions: default attribute not valid ");
                    }

                }

            }

        }

        private void storeSupportedDiagrams() {

            for (String[] entry : supportedDiagrams) {

                LayoutAlgorithmData algoData = getAlgorithmData(entry[0]);

                if (algoData != null) {

                    try {
                        algoData.setDiagramSupport(
                            entry[1], Integer.parseInt(entry[2]));
                    } catch (NumberFormatException exception) {
                        reportError("storeSupportedDiagrams: priotity attribute not valid ");
                    }

                }

            }

        }

        public void characters(char ch[], int start, int length) {
        }

        private String attributesToString(Attributes atts) {

            String result = "";

            if (atts != null) {
                for (int i = 0; i < atts.getLength(); i++ ) {
                    result += atts.getQName(i) + "=" + atts.getValue(i) + ", ";
                }
            }

            return "{ " + result + "}";

        }

    }

}
