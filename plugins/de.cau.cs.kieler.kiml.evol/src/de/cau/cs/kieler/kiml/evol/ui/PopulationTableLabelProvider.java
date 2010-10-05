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

import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.evol.EvolModel;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.ListItemGene;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Provides labels for the population table of {@link EvolView}.
 *
 * @author bdu
 *
 */
public class PopulationTableLabelProvider extends LabelProvider implements ITableLabelProvider {
    /**
     * The associated evolution model.
     */
    private final EvolModel evolModel;

    /**
     * Creates a new {@link PopulationTableLabelProvider}.
     * 
     * @param theEvolModel
     *            the associated evolution model
     */
    public PopulationTableLabelProvider(final EvolModel theEvolModel) {
        this.evolModel = theEvolModel;
        // Nothing to do here.
    }

    /**
     * Image for the currently selected row.
     */
    private final Image currentImage = AbstractUIPlugin.imageDescriptorFromPlugin(
            EvolPlugin.PLUGIN_ID, "icons/current.png").createImage();

    /**
     * Image for a non-selected row.
     */
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
            int pos = this.evolModel.getPosition();
            if ((element instanceof PopulationTableEntry)
                    && (((PopulationTableEntry) element).getIndex() == pos)) {
                // it is the current individual
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
        // Note: this may be improved by using CellLabelProviders

        final Genome individual = ((PopulationTableEntry) element).getIndividual();
        switch (columnIndex) {
        case 0:
            return ((PopulationTableEntry) element).getId();
        case 1:
            final int ratingFactor = 100;
            int scaledRating =
                    (int) Math.round(individual.hasUserRating() ? individual.getUserRating()
                            * ratingFactor : 0);
            return (scaledRating + "");
        case 2:
            IGene<?> hintGene = individual.find(LayoutOptions.LAYOUTER_HINT_ID);
            String providerName = null;
            if (hintGene instanceof ListItemGene) {
                final String hintId = hintGene.toString();

                final LayoutProviderData provider =
                        LayoutServices.getInstance().getLayoutProviderData(hintId);
                if (provider != null) {
                    providerName = provider.getName();
                }
            }
            return (providerName);

        default: // do nothing
            return null;
        }
    }

    @Override
    public boolean isLabelProperty(final Object element, final String property) {
        return false;
    }
}
