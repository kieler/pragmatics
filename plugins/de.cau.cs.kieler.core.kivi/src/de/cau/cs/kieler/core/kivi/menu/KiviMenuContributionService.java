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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

import de.cau.cs.kieler.core.kivi.ICombination;

/**
 * A simple programmatic API to specify toolbar buttons to be used with the KIELER View Management.
 * 
 * @author haf
 */
public class KiviMenuContributionService {

    /** Singleton instance of this service. */
    public static final KiviMenuContributionService INSTANCE = new KiviMenuContributionService();

    /** internal list of Buttons. */
    private List<ButtonConfiguration> buttonConfigurations = new ArrayList<ButtonConfiguration>();

    /** Add a button configuration with all possible parameters. */
    public void addToolbarButton(ICombination responsibleCombination, final String id,
            final String label, final String tooltip, final ImageDescriptor icon, final int style,
            final Expression visibilityExpression, final String... activeEditors) {
        buttonConfigurations.add(new ButtonConfiguration(responsibleCombination, id, label,
                tooltip, icon, style, visibilityExpression, activeEditors));
    }

    /** Add a button configuration with only a few parameters. */
    public void addToolbarButton(ICombination responsibleCombination, final String id,
            final String tooltip, final ImageDescriptor icon, final String... activeEditors) {
        buttonConfigurations.add(new ButtonConfiguration(responsibleCombination, id, "KiviButton",
                tooltip, icon, SWT.PUSH, null, activeEditors));
    }

    /** Add the simplest button configuration possible. Usually used only for quick testing. */
    public void addToolbarButton(ICombination responsibleCombination, final String id,
            final String label) {
        buttonConfigurations.add(new ButtonConfiguration(responsibleCombination, id, label, null,
                null, SWT.PUSH, null, null));
    }

    /** Get the list of registered ButtonConfigurations. */
    public List<ButtonConfiguration> getButtonConfigurations() {
        return buttonConfigurations;
    }

    public static class ButtonConfiguration {
        String id;
        String label;
        String tooltip;

        ImageDescriptor icon;
        int style;

        Expression visibilityExpression;
        String[] activeEditors;

        ICombination responsiveCombination;

        ButtonConfiguration(ICombination responsiveCombination, String id, String label,
                String tooltip, ImageDescriptor icon, int style, Expression visibilityExpression,
                String[] activeEditors) {
            this.responsiveCombination = responsiveCombination;
            this.id = id;
            this.label = label;
            this.tooltip = tooltip;
            this.icon = icon;
            this.style = style;
            this.visibilityExpression = visibilityExpression;
            this.activeEditors = activeEditors;
        }

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

    }

}
