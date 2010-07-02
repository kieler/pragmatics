package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.cau.cs.kieler.kiml.evol.Individual;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Content provider for table view.
 *
 * @author bdu
 *
 */
public class PopulationTableContentProvider implements IStructuredContentProvider {
    public void dispose() {
        // do nothing
    }

    public Object[] getElements(final Object inputElement) {
        // suppose inputElement contains a reference to a Population object.
        final Population inputPopulation;
        if (!(inputElement instanceof Population)) {
            return new PopulationTableEntry[] {};
        }
        inputPopulation = (Population) inputElement;
        final PopulationTableEntry[] result = new PopulationTableEntry[inputPopulation.size()];
        int i = 0;
        for (final Individual individual : inputPopulation) {
            result[i] = new PopulationTableEntry();
            result[i].setIndividual(individual);
            result[i].setIndex(i);
            i++;
        }
        return result;
    }

    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        System.out.println("Viewer " + viewer.toString() + " input changed.");
    }
}
