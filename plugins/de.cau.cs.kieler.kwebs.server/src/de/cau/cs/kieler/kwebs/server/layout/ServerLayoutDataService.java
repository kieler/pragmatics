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

package de.cau.cs.kieler.kwebs.server.layout;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Version;

import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.service.ExtensionLayoutDataService;
import de.cau.cs.kieler.kwebs.logging.Logger;

/**
 * This class is the server equivalent of {@link EclipseLayoutServices} but
 * without the unneccesary support for ui interaction. It provides all
 * extension based registered layout information at runtime and also the
 * client side needed meta data about supported layout capabilities.
 *
 * @kieler.rating  2011-05-09 red
 * @author  swe
 */
public final class ServerLayoutDataService extends ExtensionLayoutDataService {

    /** Caching the layout service meta data. */
    private static String capabilities;

    /** Caching the version of the plugin as service version. */
    private static String version;

    /**
     * Private constructor.
     */
    private ServerLayoutDataService() {
        createCapabilities();
        createVersion();
    }

    /**
     * Initializes singleton instance of {@code ServerLayoutServiceService}
     * from the extension points.
     */
    public static void create() {
        if (LayoutDataService.getInstance() == null
            || LayoutDataService.getInstanceOf(LayoutDataService.SERVICEDATASERVICE) == null) {
            ServerLayoutDataService lds = new ServerLayoutDataService();
            LayoutDataService.addService(lds);
            lds.loadLayoutProviderExtensions();
        }
    }

    /**
     * Returns the singleton instance of {@code ServerLayoutDataService}.
     *
     * @return the singleton instance, or {@code null} if {@code ServerLayoutDataService}
     *         has not been registered yet
     */
    public static ServerLayoutDataService getInstance() {
        return LayoutDataService.getInstanceOf(
            LayoutDataService.SERVICEDATASERVICE
        );
    }

    /**
     * {@inheritDoc}
     *//*
    protected Class<?> loadClass(final IConfigurationElement element) {
        String className = element.getAttribute(ATTRIBUTE_CLASS);
        Class<?> clas = super.loadClass(element);
        if (clas == null) {
            Logger.log(
                Messages.getString(
                    "server.ServerLayoutDataService.message.0" //$NON-NLS-1$
                )
                + " " + className + " " //$NON-NLS-1$ //$NON-NLS-2$
                + Messages.getString(
                    "server.ServerLayoutDataService.message.1" //$NON-NLS-1$
                )
            );
            debugElement(element);
        }
        return clas;
    }*/

    /**
     * Returns the layout meta data in xml.
     *
     * @return String
     *             the meta data
     */
    public static String getCapabilities() {
        return capabilities;
    }

    /**
     * Returns the version of this plugin which is used to identify
     * the version of this layout service.
     *
     * @return String
     *             the version
     */
    public static String getVersion() {
        return version;
    }

    /** name of the 'preview' attribute in the extension points. */
    public static final String ATTRIBUTE_PREVIEW
        = "preview"; //$NON-NLS-1$

    /** name of the 'appliesTo' attribute in the extension points. */
    public static final String ATTRIBUTE_APPLIESTO
        = "appliesTo"; //$NON-NLS-1$

    /**
     * Creates the XML representation of the layout capabilities of this
     * server.
     */
    private void createCapabilities() {
        IConfigurationElement[] extensions =
            Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_PROVIDERS);
        capabilities = "<?xml version='1.0' encoding='UTF-8'?>\n" //$NON-NLS-1$
                       + "<lws:capabilities" //$NON-NLS-1$
                       + " xmlns:lws='http://de.cau.cs.kieler/" //$NON-NLS-1$
                       + "2011-05-15/LayoutService/lws.xsd'>\n"; //$NON-NLS-1$
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_LAYOUT_ALGORITHM.equals(element.getName())) {
                createLACapability(element);
            } else if (ELEMENT_LAYOUT_TYPE.equals(element.getName())) {
                createLTCapability(element);
            } else if (ELEMENT_CATEGORY.equals(element.getName())) {
                createCATCapability(element);
            } else if (ELEMENT_LAYOUT_OPTION.equals(element.getName())) {
                createLOCapability(element);
            }
        }
        capabilities += "\n</lws:capabilities>\n"; //$NON-NLS-1$
    }

    /**
     *
     * @param element
     *            extension element defining the layout algorithm
     */
    private void createLACapability(final IConfigurationElement element) {
        IContributor contributor = element.getContributor();
        
        //CHECKSTYLEOFF MagicNumber
        
        if (contributor != null) {
            String name = contributor.getName();
            capabilities += "\n  <!-- contributed by: " //$NON-NLS-1$
                            + name
                            + " -->"; //$NON-NLS-1$
        }
        capabilities += getXMLElement(element, 2, false, "layoutAlgorithm",
            new String[] {"id", "name", "class", "parameter",
                "category", "type", "description", "preview"});
        for (IConfigurationElement child : element.getChildren()) {
            if (ELEMENT_KNOWN_OPTION.equals(child.getName())) {
                capabilities += getXMLElement(child, 4, true, "knownOption",
                    new String[] {"option", "default"});
            } else if (ELEMENT_SUPPORTED_DIAGRAM.equals(child.getName())) {
                capabilities += getXMLElement(child, 4, true, "supportedDiagram",
                    new String[] {"type", "priority"});
            }
        }
        capabilities += "\n  </lws:layoutAlgorithm>\n"; //$NON-NLS-1$
        
        //CHECKSTYLEON MagicNumber
        
    }

    /**
     *
     * @param element
     *            extension element defining the type of the layout algorithm
     */
    private void createLTCapability(final IConfigurationElement element) {
        capabilities += getXMLElement(element, 2, true, "layoutType",
            new String[] {"id", "name", "description"});
    }

    /**
     *
     * @param element
     *            extension element defining the category
     */
    private void createCATCapability(final IConfigurationElement element) {
        capabilities += getXMLElement(element, 2, true, "category",
            new String[] {"id", "name"});
    }

    /**
     *
     * @param element
     *            extension element defining the layout option
     */
    private void createLOCapability(final IConfigurationElement element) {
        String type = element.getAttribute("type");
        String clas = element.getAttribute("class");
        Map<String, List<String>> extraAtts
            = new HashMap<String, List<String>>();
        if (type != null && type.equals("enum") && clas != null) {
            try {
                //FIXME add other types like kvector
                Class<?> c = Class.forName(clas);
                if (c.isEnum()) {
                    List<String> enumValues = new LinkedList<String>();
                    for (Enum<?> e : (Enum<?>[]) c.getEnumConstants()) {
                        enumValues.add(e.toString());
                    }
                    extraAtts.put("enumValues", enumValues);
                } else {
                    throw new IllegalArgumentException(
                        clas + " is not an enum type." //$NON-NLS-1$
                    );
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        capabilities += getXMLElement(element, 2, true, "layoutOption",
            new String[] {"id", "type", "name", "description",
                "appliesto", "default", "class", "advanced"}, extraAtts);
    }

    /**
     *
     * @param element
     *            extension element
     * @param indent
     *            indentation of the created tag
     * @param isSimple
     *            whether created tag shall contain child elements
     * @param tagName
     *            name of the tag
     * @param attributes
     *            attributes of the extension element to be
     *            added to the created tag
     *
     * @return String
     *             the created tag
     */
    private String getXMLElement(final IConfigurationElement element,
        final int indent, final boolean isSimple, final String tagName,
        final String[] attributes) {
        return getXMLElement(element, indent, isSimple, tagName, attributes, null);
    }

    /**
     *
     * @param element
     *            extension element
     * @param indent
     *            indentation of the created tag
     * @param tagName
     *            name of the tag
     * @param attributes
     *            attributes of the extension element to be
     *            added to the created tag
     *
     * @return String
     *             the created tag
     */
    private String getXMLElement(final IConfigurationElement element,
        final int indent, final boolean isSimple, final String tagName,
        final String[] attributes,
        final Map<String, List<String>> extraAtts) {
        String tag = "";
        String attrs = "";
        String value = null;
        if (element != null && attributes != null) {
            String prefix = "";
            for (int i = 0; i < indent; i++) {
                prefix += " ";
            }
            attrs = "";
            for (String name : attributes) {
                value = escapeChars(element.getAttribute(name));
                if (value != null && value.length() > 0) {
                    attrs += "\n" + prefix + "  " + name + "='" + value + "'";
                }
            }
            if (extraAtts != null) {
                for (String attrName : extraAtts.keySet()) {
                    value = "";
                    for (String attrValue : extraAtts.get(attrName)) {
                        if (value.length() > 0) {
                            value += " ";
                        }
                        value += attrValue;
                    }
                    value = escapeChars(value);
                    attrs += "\n" + prefix + "  "
                          + attrName + "='" + value + "'";
                }
            }
            tag += "\n" + prefix + "<lws:" + tagName;
            if (attrs.length() > 0) {
                tag += attrs + "\n" + prefix;
            }
            if (isSimple) {
                 tag += "/";
            }
            tag += ">\n";
        }
        return tag;
    }

    /**
     *
     * @param value
     *            the String to be escaped
     * @return String
     *             the escaped string
     */
    private String escapeChars(final String value) {
        if (value == null) {
            return null;
        }
        return value.replace("<", "&lt;").replace(">", "&gt;").
               replace("&", "&amp;").replace("'", "&apos;").
               replace("\"", "&quot;");
    }

    /** Identifier of this plugin. */
    private static final String PLUGIN_ID
        = "de.cau.cs.kieler.kwebs.server";

    /**
     * Read the version of this plugin and cache it in private member.
     */
    private void createVersion() {
        Version tmp = Platform.getBundle(PLUGIN_ID).getVersion();
        version = tmp.getMajor() + "." + tmp.getMinor() + "." + tmp.getMicro();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportError(final String extensionPoint,
        final IConfigurationElement element, final String attribute,
        final Throwable exception) {
/*
        System.out.println("\n!!!!! FATAL (in ServerLayoutDataService):\n\n");
        if (extensionPoint != null) {
            System.out.println(
                "EP  : " + extensionPoint + " \n" +
                "EXCP: " + exception.getMessage() + " \n"
            );
            debugElement(element);
        }
        System.out.println("!!!!!\n");
*/
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportError(final CoreException exception) {
        reportError(null, null, null, exception);
    }

    /**
     *
     * @param element
     */
/*
    private static void debugElement(final IConfigurationElement element) {
        System.out.println("###");
        for (String name : element.getAttributeNames()) {
            int l = 0;
            
            //CHECKSTYLEOFF MagicNumber
            
            if (name.length() <  8) {
                l = 2;
            } else if (name.length() < 16) {
                l = 1;
            }
            
            //CHECKSTYLEON MagicNumber
            
            System.out.print(name);
            while (l > 0) {
                System.out.print("\t");
                l--;
            }
            System.out.println(": " + element.getAttribute(name));
        }
        System.out.println();
        if (element.getAttribute("class") != null) {
            String name = element.getAttribute("class");
            @SuppressWarnings("rawtypes")
            Class clas = null;
            System.out.print("Class is avaliable   : ");
            try {
                clas = Class.forName(name);
                System.out.println("YES");
            } catch (Throwable e) {
                System.out.println("NO");
            }
            System.out.print("Class is instantiable: ");
            try {
                clas.newInstance();
                System.out.println("YES");
            } catch (Throwable e) {
                System.out.println("NO");
            }
        }
        System.out.println("###\n");
        //try { System.in.read(); } catch (Throwable e) {}
    }
*/
}
