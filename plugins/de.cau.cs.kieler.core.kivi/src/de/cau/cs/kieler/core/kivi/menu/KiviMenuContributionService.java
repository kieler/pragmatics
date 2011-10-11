/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi.menu;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.expressions.Expression;
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

import de.cau.cs.kieler.core.kivi.ICombination;

/**
 * A simple programmatic API to specify toolbar buttons to be used with the KIELER View Management.
 * 
 * @author haf, chsch
 */
public class KiviMenuContributionService {

    /** Singleton instance of this service. */
    public static final KiviMenuContributionService INSTANCE = new KiviMenuContributionService();

    /** internal list of Buttons. */
    private List<ButtonConfiguration> buttonConfigurations = new ArrayList<ButtonConfiguration>();
    
    /** 
     * A location scheme enumeration allowing to determine the locations of KIVi menu contributions.
     *   
     * @author chsch
     */
    public enum LocationScheme {
        /** Menu contribution is to be attached to the main menu only. */
        MENU,
        /** Menu contribution is to be attached to popup menus only. */
        POPUP,
        /** Menu contribution is to be attached to the toolbar only. */
        TOOLBAR,
        /** Menu contribution is to be attached to both main and popup menus. */
        MENU_POPUP,
        /** Menu contribution is to be attached to both main menu and toolbar. */
        MENU_TOOLBAR,
        /** Menu contribution is to be attached to both popup menus and toolbar. */
        POPUP_TOOLBAR,
        /** Menu contribution is to be attached to main menu, popup menus, and toolbar. */
        MENU_POPUP_TOOLBAR
    }
    

    // CHECKSTYLEOFF ParameterNumber
    /**
     * Add a button configuration with all possible parameters.
     * 
     * @param responsibleCombination
     *            the KiVi Combination which is responsible for this button. the button will be made
     *            invisible if the combination gets deactivated
     * @param id
     *            the ID that the button should get. Need to identify the button lateron with this
     *            ID
     * @param label
     *            an optional label for the button. May be null. Either the label or the icon should
     *            be specified.
     * @param tooltip
     *            a tooltip for the button, may be null
     * @param icon
     *            the icon for the button, may be null. Either the label or the icon should be
     *            specified. An Image descriptor can be obtained by the plugin activator with the
     *            plugin ID and the relative path of the icon within that plugin.
     * @param style
     *            a SWT style constant, either SWT.PUSH, SWT.RADIO or SWT.CHECK
     * @param locationSchemeExpression
     *            a {@link LocationScheme} enumeration value indicating the locations the button is
     *            to be attached to
     * @param visibilityExpression
     *            an eclipse core Expression that gets registered as visibility expression, may be
     *            null
     * @param keySequence
     *            a keySequence for binding a key combination to the command of this button, may be null
     * @param activeEditors
     *            Strings of editor IDs for which this button should be made visible only, parameter
     *            to avoid using the visibilityExpression, may be left
     */
    public void addToolbarButton(final ICombination responsibleCombination, final String id,
            final String label, final String tooltip, final ImageDescriptor icon, final int style,
            final LocationScheme locationSchemeExpression, final Expression visibilityExpression, 
            final KeySequence keySequence, final String... activeEditors) {
        assert locationSchemeExpression != null;
        addButtonConfiguration(new ButtonConfiguration(responsibleCombination, id, label, tooltip,
                icon, style, locationSchemeExpression, visibilityExpression, activeEditors, 
                false, keySequence));
    }
    // CHECKSTYLEON ParameterNumber

    // CHECKSTYLEOFF ParameterNumber
    /**
     * Add a separator button configuration.
     * 
     * @param id
     *            the ID that the separator should get. Need to identify the button lateron with this
     *            ID
     * @param locationSchemeExpression
     *            a {@link LocationScheme} enumeration value indicating the locations the button is
     *            to be attached to
     * @param visibilityExpression
     *            an eclipse core Expression that gets registered as visibility expression, may be
     *            null          
     * @param activeEditors
     *            Strings of editor IDs for which this button should be made visible only, parameter
     *            to avoid using the visibilityExpression, may be left
     */
    public void addSeparator(final String id, final LocationScheme locationSchemeExpression, 
            final Expression visibilityExpression, final String... activeEditors) {
        assert locationSchemeExpression != null;
        addButtonConfiguration(new ButtonConfiguration(null, id, null, null,
                null, 0, locationSchemeExpression, visibilityExpression, activeEditors, true, null));
    }
    // CHECKSTYLEON ParameterNumber
    
    /**
     * Add a button configuration with only a few parameters.
     * 
     * @param responsibleCombination
     *            the KiVi Combination which is responsible for this button. the button will be made
     *            invisible if the combination gets deactivated
     * @param id
     *            the ID that the button should get. Need to identify the button lateron with this
     *            ID
     * @param tooltip
     *            a tooltip for the button, may be null
     * @param icon
     *            the icon for the button, may be null. Either the label or the icon should be
     *            specified. An Image descriptor can be obtained by the plugin activator with the
     *            plugin ID and the relative path of the icon within that plugin.
     * @param activeEditors
     *            Strings of editor IDs for which this button should be made visible only, parameter
     *            to avoid using the visibilityExpression, may be left
     */
    public void addToolbarButton(final ICombination responsibleCombination, final String id,
            final String tooltip, final ImageDescriptor icon, final String... activeEditors) {
        addButtonConfiguration(new ButtonConfiguration(responsibleCombination, id, "KiviButton",
                tooltip, icon, SWT.PUSH, LocationScheme.MENU_POPUP_TOOLBAR, null, activeEditors, 
                false, null));
    }

    /**
     * Add the simplest button configuration possible. Usually used only for quick testing.
     * 
     * @param responsibleCombination
     *            the KiVi Combination which is responsible for this button. the button will be made
     *            invisible if the combination gets deactivated
     * @param id
     *            the ID that the button should get. Need to identify the button lateron with this
     *            ID
     * @param label
     *            an optional label for the button. May be null. Either the label or the icon should
     *            be specified.
     */
    public void addToolbarButton(final ICombination responsibleCombination, final String id,
            final String label) {
        addButtonConfiguration(new ButtonConfiguration(responsibleCombination, id, label, null,
                null, SWT.PUSH, LocationScheme.MENU_POPUP_TOOLBAR, null, null, false, null));
    }

    /**
     * Get the list of registered ButtonConfigurations.
     * 
     * @return all registered ButtonConfigurations
     */
    public List<ButtonConfiguration> getButtonConfigurations() {
        return buttonConfigurations;
    }

    /* Add a button config, replace an old one with the same ID */
    private void addButtonConfiguration(final ButtonConfiguration b) {
        ButtonConfiguration existing = this.getButtonConfiguration(b.id);
        if (existing == null) {
            this.buttonConfigurations.add(b);
        } else {
            int existingIndex = this.buttonConfigurations.indexOf(existing);
            this.buttonConfigurations.remove(existing);
            this.buttonConfigurations.add(existingIndex, b);
        }
    }

    /**
     * Get a ButtonCnfiguration with the given ID.
     * 
     * @param id
     *            given ID
     * @return null if there is no config registered for the given ID
     */
    public ButtonConfiguration getButtonConfiguration(final String id) {
        for (ButtonConfiguration config : buttonConfigurations) {
            if (config != null && config.getId() != null && config.getId().equals(id)) {
                return config;
            }
        }
        return null;
    }

    /**
     * A container class for configurations for buttons.
     * 
     * @author haf, chsch
     * 
     */
    public static class ButtonConfiguration {
        private String id;
        private String label;
        private String tooltip;
        private boolean separator = false;

        private ImageDescriptor icon;
        private int style;

        private LocationScheme locationSchemeExpression;
        private Expression visibilityExpression;
        private String[] activeEditors;

        private ICombination responsiveCombination;
        
        private KeySequence keySequence;

        // CHECKSTYLEOFF ParameterNumber
        ButtonConfiguration(final ICombination responsiveCombination, final String id,
                final String label, final String tooltip, final ImageDescriptor icon,
                final int style, final LocationScheme locationSchemeExpression,
                final Expression visibilityExpression, final String[] activeEditors, 
                final boolean separator, final KeySequence keySequence) {
            this.responsiveCombination = responsiveCombination;
            this.id = id;
            this.label = label;
            this.tooltip = tooltip;
            this.icon = icon;
            this.style = style;
            this.locationSchemeExpression = locationSchemeExpression;
            this.visibilityExpression = visibilityExpression;
            this.activeEditors = activeEditors;
            this.separator = separator;
            this.keySequence = keySequence;
        }
        // CHECKSTYLEOFF ParameterNumber

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @return the label
         */
        public String getLabel() {
            return label;
        }

        /**
         * @return the tooltip
         */
        public String getTooltip() {
            return tooltip;
        }

        /**
         * @return the icon
         */
        public ImageDescriptor getIcon() {
            return icon;
        }

        /**
         * @return the style
         */
        public int getStyle() {
            return style;
        }

        /**
         * @return the visibilityExpression
         */
        public LocationScheme getLocationSchemeExpression() {
            return locationSchemeExpression;
        }

        /**
         * @return the visibilityExpression
         */
        public Expression getVisibilityExpression() {
            return visibilityExpression;
        }

        /**
         * @return the activeEditors
         */
        public String[] getActiveEditors() {
            return activeEditors;
        }

        /**
         * @return the responsiveCombination
         */
        public ICombination getResponsiveCombination() {
            return responsiveCombination;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "B:" + this.id;
        }

        /**
         * @return true if this is a separator
         */
        public boolean isSeparator() {
            return this.separator;
        }
        
        /**
         * @return the key Sequence
         */
        public KeySequence getKeySequence() {
            return this.keySequence;
        }
        
    }

}
