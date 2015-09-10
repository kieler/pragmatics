/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.util;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Utility methods used for the KIML UI.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public final class KimlUiUtil {
    
    /**
     * Hidden constructor.
     */
    private KimlUiUtil() {
    }
    
    /**
     * Performs the model changes specified in the given runnable in a safe context.
     * If the given editing domain is a {@link TransactionalEditingDomain}, the changes done in
     * the runnable are recorded, otherwise the operation is not undoable.
     * 
     * @param runnable a runnable that performs model changes
     * @param editingDomain the editing domain for the changes, or {@code null}
     * @param label a user friendly label shown for the undo action, or {@code null}
     */
    public static void runModelChange(final Runnable runnable,
            final EditingDomain editingDomain, final String label) {
        if (editingDomain instanceof TransactionalEditingDomain) {
            // execute with a transactional editing domain
            editingDomain.getCommandStack().execute(new RecordingCommand(
                    (TransactionalEditingDomain) editingDomain, label) {
                protected void doExecute() {
                    runnable.run();
                }
            });
        } else if (editingDomain != null) {
            // execute with an arbitrary editing domain
            editingDomain.getCommandStack().execute(new AbstractCommand(label) {
                public void execute() {
                    runnable.run();
                }
                @Override
                public boolean canUndo() {
                    return false;
                }
                public void redo() {
                    execute();
                }
            });
        } else {
            // execute without an editing domain
            runnable.run();
        }
    }
    
    /**
     * Returns the layout option data that matches the given user-friendly name and is known by the
     * given layout provider.
     * 
     * @param providerData a layout provider data
     * @param optionName user-friendly name of a layout option
     * @return the corresponding layout option data
     */
    public static LayoutOptionData getOptionData(final LayoutAlgorithmData providerData,
            final String optionName) {
        for (LayoutOptionData data : LayoutMetaDataService.getInstance().getOptionData()) {
            if (data.getName().equals(optionName) && providerData.knowsOption(data)) {
                return data;
            }
        }
        return null;
    }

    /**
     * Retrieves a suitable layout option data instance that matches the given
     * user friendly display name.
     * 
     * @param providerDataArray array of applicable layout provider data
     * @param displayName display name of the layout option as seen by the user
     * @return the most suitable layout option data
     */
    public static LayoutOptionData getOptionData(final LayoutAlgorithmData[] providerDataArray,
            final String displayName) {
        for (LayoutAlgorithmData providerData : providerDataArray) {
            LayoutOptionData optionData = getOptionData(providerData, displayName);
            if (optionData != null) {
                return optionData;
            }
        }
        // the only option data that is added without explicit support by layouters is layout hint
        return LayoutMetaDataService.getInstance().getOptionData(LayoutOptions.ALGORITHM.getId());
    }

}
