/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 *
 * @author bdu
 */
public class EvolPlugin extends AbstractUIPlugin {
    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kiml.evol";

    /** String that is used as line delimiter. */
    public static final String LINE_DELIMITER = System.getProperty("line.separator");

    // Preference constants and default values.
    /**
     * Preference constant for population size.
     */
    public static final String PREF_POPULATION_SIZE = "de.cau.cs.kieler.kiml.evol.populationSize";

    /**
     * Default value for population size.
     */
    public static final int DEF_POPULATION_SIZE = 12;

    /**
     * Maximum value for population size.
     */
    public static final int MAX_POPULATION_SIZE = 27;

    /**
     * Preference constant for editor selection.
     */
    public static final String PREF_EDITORS = "de.cau.cs.kieler.kiml.evol.editors";

    /**
     * Preference constant for option "Use layout hint from genome".
     */
    public static final String PREF_USE_LAYOUT_HINT_FROM_GENOME =
            "de.cau.cs.kieler.kiml.evol.useLayoutHint";

    /**
     * Preference constant for option
     * "Use layout hint from genome also for different type".
     */
    public static final String PREF_USE_DIFFERENT_TYPE_LAYOUT_HINT =
            "de.cau.cs.kieler.kiml.evol.useDifferentTypeLayoutHint";

    /**
     * Preference constant for option "Allow parthenogenesis". A preference that
     * indicates whether parthenogenesis (reproduction from only one parent) may
     * take place or not.
     */
    public static final String PREF_IS_PARTHENOGENESIS_ALLOWED =
            "de.cau.cs.kieler.kiml.evol.isParthenogenesisAllowed";

    /**
     * Preference value for editor selection: all editors.
     */
    public static final String ALL_EDITORS = "ALL";

    /**
     * Preference value for editor selection: current editor.
     */
    public static final String CURRENT_EDITOR = "CURRENT";

    /**
     * Default value for editor selection.
     */
    public static final String DEF_EDITORS = CURRENT_EDITOR;

    /**
     * Identifier for the metric weights.
     */
    public static final String WEIGHTS_ID = "de.cau.cs.kieler.kiml.evol.weights";

    /**
     * Identifier for the execution speed value.
     */
    public static final String EXECUTION_SPEED_VALUE_ID =
            "de.cau.cs.kieler.kiml.evol.executionSpeedValue";

    // The shared instance
    private static EvolPlugin plugin;

    /**
     * The constructor.
     */
    public EvolPlugin() {
        // Intentionally left empty.
    }

    /**
     * Show the given error message.
     *
     * @param message
     *            the message
     * @param exception
     *            a low-level exception, or {@code null} if not applicable
     */
    public static void showError(final String message, final Throwable exception) {
        exception.printStackTrace();
        Status status = new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID, message, exception);
        StatusManager.getManager().handle(status, StatusManager.SHOW);
    }

    /**
     * Log the given status message.
     *
     * @param message
     *            the message
     */
    public static final void logStatus(final String message) {
        Status status = new Status(IStatus.INFO, PLUGIN_ID, message, null);
        StatusManager.getManager().handle(status, StatusManager.LOG);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public final void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        EvolutionServices.createExtensionData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance
     */
    public static EvolPlugin getDefault() {
        return plugin;
    }
}
