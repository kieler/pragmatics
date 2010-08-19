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
package de.cau.cs.kieler.kiml.evol.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.evol.EvolPlugin;

/**
 * Command for toggling between single and multiple editors mode.
 *
 * @author bdu
 *
 */
public class MultipleEditorsHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent theEvent) throws ExecutionException {

        final IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();

        final String prefEditors = store.getString(EvolPlugin.PREF_EDITORS);

        final boolean wantAllEditors = EvolPlugin.ALL_EDITORS.equalsIgnoreCase(prefEditors);

        final String newValue;
        if (wantAllEditors) {
            newValue = EvolPlugin.CURRENT_EDITOR;
        } else {
            newValue = EvolPlugin.ALL_EDITORS;
        }

        store.setValue(EvolPlugin.PREF_EDITORS, newValue);

        return null;
    }
}
