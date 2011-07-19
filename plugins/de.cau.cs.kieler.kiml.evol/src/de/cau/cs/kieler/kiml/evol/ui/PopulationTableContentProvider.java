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

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Content provider for table view.
 *
 * @author bdu
 *
 */
public class PopulationTableContentProvider implements IStructuredContentProvider {
    /**
     * {@inheritDoc}
     */
    public void dispose() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(final Object inputElement) {
        // suppose inputElement contains a reference to a Population object.
        Population inputPopulation;
        if (!(inputElement instanceof Population)) {
            return new PopulationTableEntry[] {};
        }
        inputPopulation = (Population) inputElement;
        PopulationTableEntry[] result = new PopulationTableEntry[inputPopulation.size()];
        int i = 0;
        for (final Genome individual : inputPopulation.getGenomes()) {
            result[i] = new PopulationTableEntry();
            result[i].setIndividual(individual);
            result[i].setIndex(i);
            i++;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        System.out.println("Viewer " + viewer.toString() + " input changed.");
        System.out.println(newInput);
    }
}
