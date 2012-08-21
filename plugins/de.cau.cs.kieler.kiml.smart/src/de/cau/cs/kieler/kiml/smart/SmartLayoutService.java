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
import java.util.LinkedList;
import java.util.List;

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
    /** name of the 'priority' attribute in the extension points. */
    protected static final String ATTRIBUTE_PRIORITY = "priority";
    
    /** the singleton instance of the smart layout service. */
    private static SmartLayoutService instance;
    
    /**
     * Returns the singleton instance of the smart layout service, creating one if it does not
     * exist yet.
     * 
     * @return the singleton instance
     */
    public static synchronized SmartLayoutService getInstance() {
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
    
    /**
     * Metadata class for smart layout rules.
     */
    public static class SmartRuleData {
        private ISmartRule rule;
        private String name;
        private int priority = 0;
        
        /**
         * Returns the smart layout rule instance.
         * 
         * @return the smart layout rule
         */
        public ISmartRule getRule() {
            return rule;
        }
        
        /**
         * Returns the name of the smart layout rule.
         * 
         * @return the name
         */
        public String getName() {
            return name;
        }
        
        /**
         * Returns the priority of the smart layout rule.
         * 
         * @return the priority
         */
        public int getPriority() {
            return priority;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            if (name == null) {
                return super.toString();
            }
            return name;
        }
    }
    
    /** list of registered smart layout rules. */
    private List<SmartRuleData> smartRules = new LinkedList<SmartRuleData>();
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
                    SmartRuleData data = new SmartRuleData();
                    data.rule = (ISmartRule) element.createExecutableExtension(ATTRIBUTE_CLASS);
                    data.name = element.getAttribute(ATTRIBUTE_NAME);
                    try {
                        String prioString = element.getAttribute(ATTRIBUTE_PRIORITY);
                        if (prioString != null) {
                            data.priority = Integer.parseInt(prioString);
                        }
                    } catch (NumberFormatException e) {
                        // ignore exception
                    }
                    smartRules.add(data);
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
    public Collection<SmartRuleData> getSmartRules() {
        return Collections.unmodifiableCollection(smartRules);
    }
    
    /**
     * Returns the log for the smart layout plugin.
     * 
     * @return the log
     */
    public ILog getLog() {
        return log;
    }

}
