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
package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kiml.evol.EvolutionModel;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;

/**
 * The main user interface for evolutionary meta layout.
 *
 * @author msp
 */
public class EvolutionDialog extends Dialog {
    
    /** the layout mapping for the graph taken for preview. */
    private LayoutMapping<?> layoutMapping;
    
    /**
     * Creates an evolution dialog.
     * 
     * @param parentShell the parent shell
     * @param theLayoutMapping mapping for the graph to display as preview
     */
    public EvolutionDialog(final Shell parentShell, final LayoutMapping<?> theLayoutMapping) {
        super(parentShell);
        this.layoutMapping = theLayoutMapping;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText("Evolutionary Layout");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        if (getReturnCode() == Dialog.OK) {
            
        }
        return super.close();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        EvolutionModel evolutionModel = initializeContent();
        return null;
    }
    
    private EvolutionModel initializeContent() {
        EvolutionModel evolutionModel = EvolutionModel.getInstance();
        if (evolutionModel.getPopulation().getSize() == 0) {
            evolutionModel.initializePopulation(layoutMapping);
        }
        return evolutionModel;
    }

}
