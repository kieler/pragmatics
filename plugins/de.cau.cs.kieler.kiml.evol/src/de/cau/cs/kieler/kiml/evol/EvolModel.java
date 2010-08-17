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

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.evol.alg.BasicEvolutionaryAlgorithm;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.ui.IEvolModelListener;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * This class encapsulates the evolution model that is displayed in the
 * EvolView. The model basically consists of an evolutionary algorithm, a
 * population and a current individual.
 *
 * @author bdu
 *
 */
public final class EvolModel {

    /**
     * Creates a new {@link EvolModel} instance.
     */
    public EvolModel() {
        this.position = 0;
        this.layoutProviderId = null;
        this.layoutTypeId = null;
    }

    // private fields
    private BasicEvolutionaryAlgorithm evolAlg;
    private int position;
    private String layoutProviderId;
    private String layoutTypeId;
    private final List<IEvolModelListener> listeners = new LinkedList<IEvolModelListener>();

    /**
     * Adds a model listener.
     *
     * @param listener
     *            the {@link IEvolModelListener} to add
     */
    public void addListener(final IEvolModelListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Performs a step of the evolutionary algorithm.
     *
     * @param theMonitor
     *            a progress monitor; may be {@code null}
     */
    public void evolve(final IProgressMonitor theMonitor) {
        // if (!isValid()) {
        // return;
        // }

        final IProgressMonitor monitor;
        // Ensure there is a monitor of some sort.
        monitor = ((theMonitor != null) ? theMonitor : new NullProgressMonitor());

        final int stepWork = 1;
        final int afterStepWork = 1;
        final int autoRateWork = 1;
        final int listenersWork = 1;

        final int total = stepWork + afterStepWork + autoRateWork + listenersWork;
        final int scale = 100;

        try {
            monitor.beginTask("Performing evolutionary step.", total * scale);

            // Perform the step.
            this.evolAlg.step();
            monitor.worked(stepWork * scale);

            // The previously selected individual might not have survived in
            // evolution, so select a new one as current.
            selectInterestingIndividual();
            monitor.worked(afterStepWork * scale);

            // Calculate auto-rating for the yet unrated individuals.
            final Population unrated = getPopulation().select(Population.UNRATED_FILTER);

            final Runnable runnable = new Runnable() {
                public void run() {
                    EvolUtil.autoRate(unrated, new SubProgressMonitor(monitor, 1 * scale));
                }
            };
            MonitoredOperation.runInUI(runnable, true);
            monitor.worked(autoRateWork * scale);

            // Notify listeners.
            afterChange("evolve");
            monitor.worked(listenersWork * scale);

        } finally {
            monitor.done();
        }
    }

    /**
     * Auto-rate all individuals in the given editor.
     *
     * @param theMonitor
     *            a progress monitor; may be {@code null}
     */
    public void autoRateAll(final IProgressMonitor theMonitor) {

        final Population population = getPopulation();
        Assert.isNotNull(population);

        EvolUtil.autoRate(population, theMonitor);

        // Notify listeners.
        afterChange("autoRate");
    }

    /**
     *
     * @return the current {@code Individual}, or {@code null} if none is
     *         selected.
     */
    public Genome getCurrentIndividual() {
        final Population pop = this.getPopulation();
        final int pos = this.getPosition();
        Assert.isTrue((pos >= 0) && (pos < pop.size()), "position out of range");
        if ((pop == null) || pop.isEmpty()) {
            return null;
        }

        // ensure that the position is within the valid range
        if ((pos >= pop.size()) || (pos < 0)) {
            return null;
        }

        return pop.get(pos);
    }

    /**
     *
     * @return the evolutionary algorithm.
     */
    public BasicEvolutionaryAlgorithm getEvolAlg() {
        return this.evolAlg;
    }

    /**
     * The layout provider id that the population was created for.
     *
     * @return the layout provider id
     */
    public String getLayoutProviderId() {
        return this.layoutProviderId;
    }

    /**
     *
     * @return the population
     */
    public Population getPopulation() {
        if (this.evolAlg == null) {
            return new Population();
        }
        return this.evolAlg.getPopulation();
    }

    /**
     *
     * @return the current position.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Checks if the model is in a valid state.
     *
     * @return {@code true} if the model is valid; otherwise {@code false}.
     *
     */
    public boolean isValid() {
        if (this.evolAlg == null) {
            System.out.println("Algorithm is not set.");
            return false;
        }

        final Population pop = this.getPopulation();
        // Population should be non-null in any case.
        Assert.isNotNull(pop, "Population is not set.");

        if (pop.isEmpty()) {
            System.out.println("Population is empty.");
            return false;
        }

        final Genome currentIndividual = getCurrentIndividual();
        if (currentIndividual == null) {
            System.out.println("No individual selected.");
            return false;
        }

        // Must be in UI thread.
        final LayoutViewPart layoutViewPart = LayoutViewPart.findView();
        if (layoutViewPart == null) {
            System.out.println("LayoutView not found.");
            return false;
        }

        if (!isCompatibleLayoutProvider()) {
            // need to reset
            System.out
                    .println("The current population is not compatible to the layout provider.");
            System.out.println("Reset the population or select " + this.getLayoutProviderId());
            return false;
        }

        return true;
    }

    /**
     * Removes the specified model listener.
     *
     * @param listener
     *            the {@link IEvolModelListener} to remove
     */
    public void removeListener(final IEvolModelListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Reset the population and restart the algorithm.
     */
    public void reset() {
        final IEditorPart editor = EvolUtil.getCurrentEditor();
        final EditPart part = EvolUtil.getEditPart(editor);
        setPosition(0);
        setEvolAlg(null);

        final String providerId = EvolUtil.getLayoutProviderId(editor, part);
        setLayoutProviderId(providerId);

        if (providerId != null) {
            final LayoutServices layoutServices = LayoutServices.getInstance();
            Assert.isNotNull(layoutServices);
            final LayoutProviderData providerData =
                    layoutServices.getLayoutProviderData(providerId);
            Assert.isNotNull(providerData);
            final String typeId = providerData.getType();
            setLayoutTypeId(typeId);
        }

        final Set<IEditorPart> editors = EvolUtil.getEditors();
        final Population sourcePopulation = EvolUtil.createPopulation(editors);

        if (!sourcePopulation.isEmpty()) {
            // Create and initialize the algorithm.
            final BasicEvolutionaryAlgorithm alg =
                    new BasicEvolutionaryAlgorithm(sourcePopulation);
            setEvolAlg(alg);
            alg.step();
        }

        // Notify listeners.
        afterChange("reset");
    }

    /**
     * @param theLayoutTypeId
     */
    private void setLayoutTypeId(final String theLayoutTypeId) {
        this.layoutTypeId = theLayoutTypeId;
    }

    /**
     * @param thePosition
     *            the new position.
     */
    public void setPosition(final int thePosition) {
        Assert.isLegal(thePosition >= 0);
        Assert.isLegal((thePosition <= getPopulation().size()));

        final int oldPosition = this.position;
        this.position = thePosition;

        if (oldPosition != thePosition) {
            afterChange("setPosition");
        }
    }

    /**
     * Notify listeners about a performed model change.
     *
     * @param cause
     */
    private void afterChange(final String cause) {
        for (final IEvolModelListener listener : this.listeners) {
            listener.afterChange(this, cause);
        }
    }

    /**
     * Return position of first unrated individual in the list.
     *
     * @return {@code -1} if no unrated individual exists.
     */
    private int firstUnrated() {
        final Population pop = this.getPopulation();
        Assert.isNotNull(pop);

        int result = -1;
        for (int i = 0; i < pop.size(); i++) {
            final Genome ind = pop.get(i);
            if (!ind.hasUserRating()) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     *
     * @return indicates whether the layout provider has changed since the last
     *         reset.
     */
    private boolean layoutProviderHasChanged() {
        final IEditorPart editor = EvolUtil.getCurrentEditor();
        final EditPart editPart = EvolUtil.getEditPart(editor);

        final String oldId = getLayoutProviderId();
        final String newId = EvolUtil.getLayoutProviderId(editor, editPart);

        if ((newId == null) || (oldId == null)) {
            return (oldId != null) || (newId != null);
        }
        final boolean result = !(oldId.equalsIgnoreCase(newId));
        if (result) {
            System.out.println("Current layout provider : " + newId);
            final String newTypeId =
                    LayoutServices.getInstance().getLayoutProviderData(newId).getType();
            System.out.println("Type: " + newTypeId);
            System.out.println("Expected layout provider: " + oldId);
            final String oldTypeId =
                    LayoutServices.getInstance().getLayoutProviderData(oldId).getType();
            System.out.println("Type: " + oldTypeId);
        }
        return result;
    }

    private boolean isCompatibleLayoutProvider() {

        final IEditorPart editor = EvolUtil.getCurrentEditor();
        final EditPart editPart = EvolUtil.getEditPart(editor);

        final String oldId = this.layoutProviderId;
        final String newId = EvolUtil.getLayoutProviderId(editor, editPart);

        if ((newId == null) || (oldId == null)) {
            return (oldId == null) && (newId == null);
        }

        final String oldTypeId =
                LayoutServices.getInstance().getLayoutProviderData(oldId).getType();
        final String newTypeId =
                LayoutServices.getInstance().getLayoutProviderData(newId).getType();

        Assert.isTrue(oldTypeId.equalsIgnoreCase(this.layoutTypeId));

        final boolean result = oldTypeId.equalsIgnoreCase(newTypeId);

        if (!result) {
            System.out.println("expected type: " + this.layoutTypeId);
            System.out.println("present type: " + newTypeId);
        }

        return result;
    }

    /**
     * Selects an interesting individual.
     */
    private void selectInterestingIndividual() {
        // if (!isValid()) {
        // return;
        // }

        final int firstUnrated = firstUnrated();
        if (firstUnrated > -1) {
            setPosition(firstUnrated);
        }

        updatePosition();
        Assert.isTrue(getPosition() >= 0);
    }

    /**
     *
     * @param theEvolAlg
     *            the evolutionary algorithm
     */
    private void setEvolAlg(final BasicEvolutionaryAlgorithm theEvolAlg) {
        this.evolAlg = theEvolAlg;
    }

    /**
     * @param theLayoutProviderId
     *            a layout provider id
     */
    private void setLayoutProviderId(final String theLayoutProviderId) {
        this.layoutProviderId = theLayoutProviderId;
    }

    /**
     * Make sure that the current position is not beyond the last individual.
     */
    private void updatePosition() {
        final int lim = getPopulation().size();
        if (getPosition() >= lim) {
            setPosition(lim - 1);
        }
    }

    /**
     * Changes the rating of the current individual.
     *
     * @param delta
     *            the amount of the change
     */
    public void changeCurrentRating(final int delta) {
        if (isValid()) {
            final Genome ind = getCurrentIndividual();
            final int rating = ind.getUserRating() + delta;
            ind.setUserRating(rating);

            afterChange("changeCurrentRating");
            // TODO: in listener: only current individual needs to be updated
        }
    }
}
