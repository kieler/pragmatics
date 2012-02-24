/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.smart;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * Service for smart layout rules.
 *
 * @author msp
 */
public final class SmartLayoutService {
    
    /** identifier of the smart layout plugin. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kiml.smart";
    /** identifier of the extension point for layout rules. */
    public static final String EXTP_ID_LAYOUT_RULES = "de.cau.cs.kieler.kiml.smart.layoutRules";
    /** name of the 'rule' element in the 'layout rules' extension point. */
    protected static final String ELEMENT_RULE = "rule";
    /** name of the 'class' attribute in the extension points. */
    protected static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'name' attribute in the extension points. */
    protected static final String ATTRIBUTE_NAME = "name";
    
    /** the singleton instance of the smart layout service. */
    private static SmartLayoutService instance;
    
    /**
     * Returns the singleton instance of the smart layout service, creating one if it does not
     * exist yet.
     * 
     * @return the singleton instance
     */
    public static SmartLayoutService getInstance() {
        if (instance == null) {
            instance = new SmartLayoutService();
            Bundle bundle = Platform.getBundle(PLUGIN_ID);
            if (bundle != null) {
                instance.log = Platform.getLog(bundle);
            }
            instance.readLayoutRulesExtensionPoint();
        }
        return instance;
    }
    
    /** map of registered smart layout rules to their names. */
    private Map<ISmartRule, String> smartRuleMap = new HashMap<ISmartRule, String>();
    /** logger for the smart layout plugin. */
    private ILog log;

    /**
     * Hidden constructor to prevent instantiation from outside this class.
     */
    private SmartLayoutService() {
    }
    
    /**
     * Read layout rules from the extension point.
     */
    private void readLayoutRulesExtensionPoint() {
        IConfigurationElement[] elements = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_RULES);
        for (IConfigurationElement element : elements) {
            if (ELEMENT_RULE.equals(element.getName())) {
                try {
                    ISmartRule smartRule = (ISmartRule) element.createExecutableExtension(
                            ATTRIBUTE_CLASS);
                    String name = element.getAttribute(ATTRIBUTE_NAME);
                    smartRuleMap.put(smartRule, name);
                } catch (CoreException exception) {
                    if (log != null) {
                        log.log(exception.getStatus());
                    }
                }
            }
        }
    }
    
    /**
     * Returns the registered smart layout rules.
     * 
     * @return the registered rules
     */
    public Collection<ISmartRule> getSmartRules() {
        return Collections.unmodifiableCollection(smartRuleMap.keySet());
    }
    
    /**
     * Returns the registered name of the given smart layout rule.
     * 
     * @param rule a smart layout rule
     * @return the registered name, or {@code null} if the rule is not registered
     */
    public String getName(final ISmartRule rule) {
        return smartRuleMap.get(rule);
    }

}
