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
package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;

/**
 * Provides labels for LayoutSet table.
 *
 * @author bdu
 *
 */
public class PopulationTableLabelProvider extends LabelProvider implements ITableLabelProvider {
    /**
     *
     */
    private final EvolView evolView;

    /**
     * Creates a new {@link PopulationTableLabelProvider}.
     *
     * @param theEvolView
     *            the evolution view
     */
    public PopulationTableLabelProvider(final EvolView theEvolView) {
        this.evolView = theEvolView;
        // Nothing to do here.
    }

    private final Image currentImage = AbstractUIPlugin.imageDescriptorFromPlugin(
            EvolPlugin.PLUGIN_ID, "icons/current.png").createImage();

    private final Image defaultImage = AbstractUIPlugin.imageDescriptorFromPlugin(
            EvolPlugin.PLUGIN_ID, "icons/default.png").createImage();

    @Override
    public void dispose() {
        super.dispose();
        this.currentImage.dispose();
        this.defaultImage.dispose();
    }

    /**
     * {@inheritDoc}
     */
    public Image getColumnImage(final Object element, final int columnIndex) {
        switch (columnIndex) {
        case 0:
            final int pos = this.evolView.getEvolModel().getPosition();
            if ((element instanceof PopulationTableEntry)
                    && (((PopulationTableEntry) element).getIndex() == pos)) {
                return this.currentImage;
            }
            return this.defaultImage;
        default:
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getColumnText(final Object element, final int columnIndex) {
        // TODO: use CellLabelProviders

        switch (columnIndex) {
        case 0:
            return ((PopulationTableEntry) element).getId();
        case 1:
            final Genome individual = ((PopulationTableEntry) element).getIndividual();
            return (individual.size() + " genes, rating: " + individual.getUserRating());
        default: // do nothing
            return null;
        }
    }

    @Override
    public boolean isLabelProperty(final Object element, final String property) {
        return false;
    }
}
