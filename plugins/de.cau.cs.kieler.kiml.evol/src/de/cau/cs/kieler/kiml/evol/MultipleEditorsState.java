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
/**
 *
 */
package de.cau.cs.kieler.kiml.evol;

import org.eclipse.jface.commands.ToggleState;
import org.eclipse.jface.menus.IMenuStateIds;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * @author bdu
 *
 */
public class MultipleEditorsState extends ToggleState implements IMenuStateIds {

    @Override
    public Object getValue() {
        // TODO Auto-generated method stub
        return super.getValue();
    }

    @Override
    public void setValue(final Object theValue) {
        // TODO Auto-generated method stub
        super.setValue(theValue);
    }

    /**
     * Creates a new {@link MultipleEditorsState} instance.
     *
     */
    public MultipleEditorsState() {
        final IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();
        final boolean value =
                store.getString(EvolPlugin.PREF_EDITORS).equalsIgnoreCase(EvolPlugin.ALL_EDITORS);
        setValue(value);
    }


}
