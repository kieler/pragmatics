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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.core.util.FilteredIterator;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.evol.alg.BasicEvolutionaryAlgorithm;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.ui.IEvolModelListener;
import de.cau.cs.kieler.kiml.evol.ui.IEvolModelListener.ModelChangeType;

/**
 * This class encapsulates the evolution model that is displayed in the
 * EvolView. The model basically consists of an evolutionary algorithm, a
 * population and a current individual. Additionally, it manages a population of
 * rating predictors that is evolved separately.
 *
 * @author bdu
 *
 */
public final class EvolModel {

    /** Number of rating predictors. */
    private static final int NUM_RATING_PREDICTORS = 25;

    // private fields
    /** The evolutionary algorithm for the layout option proposers. */

    private BasicEvolutionaryAlgorithm evolAlg;

    /** The evolutionary algorithm for the rating predictors. */
    private BasicEvolutionaryAlgorithm predictorsEvolAlg;

    /** Index of the currently selected individual. */
    private int position;

    /**
     * The ID of the layout algorithm that the current population was created
     * for.
     */
    private String layoutAlgorithmId;

    /** The list of listeners to the model. */
    private final List<IEvolModelListener> listeners = new LinkedList<IEvolModelListener>();

    /**
     * Adds a model listener. Duplicate listeners are ignored.
     *
     * @param listener
     *            the {@link IEvolModelListener} to add; must not be
     *            {@code null}
     *
     */
    public void addListener(final IEvolModelListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Argument must not be null: listener");
        }

        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    /**
     * Auto-rate all individuals in the appropriate editors.
     *
     * @param theMonitor
     *            a progress monitor; may be {@code null}
     */
    public void autoRateAll(final IKielerProgressMonitor theMonitor) {

        Population population = getPopulation();
        assert population != null;

        final Population predictors = getRatingPredictors();
        assert predictors != null;

        EvolUtil.autoRate(population.listIterator(), theMonitor, predictors);

        // Notify listeners.
        afterChange(ModelChangeType.AUTO_RATING);
    }

    /**
     * Changes the rating of the current individual.
     *
     * @param theDelta
     *            the amount of the change
     */
    public void changeCurrentRating(final double theDelta) {
        if (isValid()) {
            Genome current = getCurrentIndividual();

            Population pop = this.getPopulation();
            double rating = 0.0;
            // Nice-to-have: add preference to switch off compensation
            // Nice-to-have: Do evolution of rating predictors at lock step with
            // evolution of layout options. Use separate auto-rating and
            // user-rating fields for that purpose.
            double compensation = -theDelta / ((pop.size() - 1));

            for (final Genome g : pop.getGenomes()) {
                rating = g.hasUserRating() ? g.getUserRating() : 0;
                if (g == current) {
                    g.setUserRating(Double.valueOf(rating + theDelta));
                } else {
                    // compensation for counterbalance
                    g.setUserRating(Double.valueOf(rating + compensation));
                }
            }

            // Punish predictors
            String key = "proposedRating:" + current.getId();
            double newRating = current.hasUserRating() ? current.getUserRating() : 0.0;
            for (final Genome predictor : getRatingPredictors().getGenomes()) {
                Map<String, Object> features = predictor.getFeatures();
                if ((features != null) && features.containsKey(key)) {
                    Double prediction = (Double) features.get(key);
                    double diff = newRating - prediction;
                    double predictorRating =
                            predictor.hasUserRating() ? predictor.getUserRating() : 0.0;
                    predictor.setUserRating(predictorRating - Math.abs(diff));
                }
            }

            this.predictorsEvolAlg.step();

            afterChange(ModelChangeType.CURRENT_RATING);
        }
    }

    /**
     * Performs a step of the evolutionary algorithm.
     *
     * @param theMonitor
     *            a progress monitor; may be {@code null}
     */
    public void evolve(final IProgressMonitor theMonitor) {

        final IProgressMonitor monitor;
        // Ensure there is a monitor of some sort.
        monitor = (theMonitor != null) ? theMonitor : new NullProgressMonitor();

        int stepWork = 1;
        int afterStepWork = 1;
        int autoRateWork = 1;
        int listenersWork = 1;

        int total = stepWork + afterStepWork + autoRateWork + listenersWork;
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
            final FilteredIterator<Genome> unrated =
                    getPopulation().filteredIterator(Population.UNRATED_FILTER);
            assert unrated != null;

            final Population predictors = this.getRatingPredictors();
            // presuming predictors != null
            assert !predictors.isEmpty();

            Runnable runnable = new Runnable() {
                public void run() {
                    EvolUtil.autoRate(unrated, new KielerProgressMonitor(monitor, 1 * scale),
                            predictors);
                }
            };
            MonitoredOperation.runInUI(runnable, true);

            // reward predictors
            // XXX: this should be refactored. The predictors' fitness should
            // be calculated in a more sophisticated way.
            final double reward = 0.01;
            for (final Genome predictor : predictors.getGenomes()) {
                // presuming predictor != null
                double oldRating = predictor.hasUserRating() ? predictor.getUserRating() : 0.0;
                predictor.setUserRating(oldRating + reward);
            }

            monitor.worked(autoRateWork * scale);

            // Notify listeners.
            afterChange(ModelChangeType.EVOLVE);
            monitor.worked(listenersWork * scale);

        } finally {
            monitor.done();
        }
    }

    /**
     * Returns the current individual, that is the individual at the current
     * position.
     *
     * @return the current {@code Individual}, or {@code null} if none is
     *         selected.
     */
    public Genome getCurrentIndividual() {
        Population pop = this.getPopulation();
        int pos = this.getPosition();
        assert (pos >= 0) && (pos < pop.size()) : "position out of range";

        return pop.getGenomes().get(pos);
    }

    /**
     * Returns the layout algorithm ID that the population was created for.
     *
     * @return the layout algorithm id
     */
    public String getLayoutAlgorithmId() {
        return this.layoutAlgorithmId;
    }

    /**
     * Returns the current population.
     *
     * @return the population; may be empty.
     */
    public Population getPopulation() {
        if (this.evolAlg == null) {
            return new Population();
        }
        return this.evolAlg.getPopulation();
    }

    /**
     * Returns the current position.
     *
     * @return the current position.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     *
     * @return the population of rating predictors; may be empty but never
     *         {@code null}
     */
    public Population getRatingPredictors() {
        if (this.predictorsEvolAlg == null) {
            return new Population();
        }
        return this.predictorsEvolAlg.getPopulation();
    }

    /**
     * Checks if the model is in a valid state.
     *
     * @return {@code true} if the model is valid; otherwise {@code false}.
     *
     */
    public boolean isValid() {
        if (this.evolAlg == null) {
            EvolPlugin.logStatus("Algorithm is not set.");
            return false;
        }

        if (!isPredictorsAlgValid()) {
            return false;
        }

        Population pop = this.getPopulation();
        // Population should be non-null in any case.
        assert pop != null : "Population is not set.";

        if (pop.isEmpty()) {
            EvolPlugin.logStatus("Population is empty.");
            return false;
        }

        Genome currentIndividual = getCurrentIndividual();
        if (currentIndividual == null) {
            EvolPlugin.logStatus("No individual selected.");
            return false;
        }

        return true;
    }

    /**
     * Removes the specified model listener, if present. Requests to remove
     * non-existent listeners are ignored.
     *
     * @param listener
     *            the {@link IEvolModelListener} to remove
     */
    public void removeListener(final IEvolModelListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Reset the population and restart the algorithm.
     *
     * @param theMonitor
     *            a progress monitor; may be {@code null}
     */
    public void reset(final IKielerProgressMonitor theMonitor) {

        this.position = 0;
        this.evolAlg = null;
        this.predictorsEvolAlg = null;

        final IKielerProgressMonitor monitor;
        // Ensure there is a monitor of some sort.
        monitor =
                (theMonitor != null) ? theMonitor : new KielerProgressMonitor(
                        new NullProgressMonitor());

        int layoutOptionsWork = 1;
        int ratingPredictorsWork = 1;

        int totalWork = layoutOptionsWork + ratingPredictorsWork;
        final int scale = 100;

        try {
            monitor.begin("Resetting the model.", totalWork * scale);

            // Get the ID of the current layout algorithm.
            LayoutAlgorithmData algorithmData = getCurrentLayoutAlgorithmDataSync();
            this.layoutAlgorithmId = algorithmData != null ? algorithmData.getId() : null;

            // Get the editors.
            Set<IEditorPart> editors = getEditorsSync();

            // Create an initial population of layout option genomes.
            Population sourcePopulation = null;
            try {
                sourcePopulation = EvolUtil.createPopulation(editors);
            } catch (final Exception exception) {
                EvolPlugin.showError("A new population could not be created.", exception);
            }
            monitor.worked(layoutOptionsWork * scale);

            if ((sourcePopulation != null) && !sourcePopulation.isEmpty()) {

                // Create initial population of rating predictors.
                EvolPlugin.logStatus("Creating metric weights ...");
                Set<String> metricIds = EvolutionServices.getInstance().getLayoutMetricsIds();

                // execution speed needs special treatment
                Set<String> metricIdsWithExecutionSpeed = new HashSet<String>(metricIds);
                metricIdsWithExecutionSpeed.add(EvolPlugin.EXECUTION_SPEED_VALUE_ID);

                Population predictors = new Population();
                for (int i = 0; i < NUM_RATING_PREDICTORS; i++) {
                    Genome weightGenes =
                            GenomeFactory.createWeightGenes(metricIdsWithExecutionSpeed);
                    assert weightGenes != null;
                    predictors.add(weightGenes);
                }

                // Start evolution of rating predictors.
                this.predictorsEvolAlg = new BasicEvolutionaryAlgorithm(predictors);
                this.predictorsEvolAlg.step();

                // Start evolution of layout option genomes.
                this.evolAlg = new BasicEvolutionaryAlgorithm(sourcePopulation);
                this.evolAlg.step();
            }

            monitor.worked(ratingPredictorsWork * scale);

            // Notify listeners.
            afterChange(ModelChangeType.RESET);

        } finally {
            monitor.done();
        }
    }

    /**
     * Sets the current position.
     *
     * @param thePosition
     *            the new position; must be within the range of 0..{@code p},
     *            where {@code p} is the current population size.
     * @see #getCurrentIndividual()
     */
    public void setPosition(final int thePosition) {
        if ((thePosition < 0) || (thePosition > getPopulation().size())) {
            throw new IllegalArgumentException("position out of range: " + thePosition);
        }

        int oldPosition = this.position;

        if (oldPosition != thePosition) {
            this.position = thePosition;
            afterChange(ModelChangeType.SET_POSITION);
        }
    }

    /**
     * Notify listeners about a performed model change.
     *
     * @param cause
     *            indicates the cause of the model change
     */
    private void afterChange(final ModelChangeType cause) {
        for (final IEvolModelListener listener : this.listeners) {
            listener.afterChange(this, cause);
        }
    }

    /**
     * Returns the position of the first unrated individual in the population
     * list.
     *
     * @return {@code -1} if no unrated individual exists.
     */
    private int firstUnrated() {
        final Population pop = this.getPopulation();
        assert pop != null;

        int result = -1;
        for (int i = 0; i < pop.size(); i++) {
            Genome ind = pop.getGenomes().get(i);
            if (!ind.hasUserRating()) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Synchronously get the layout algorithm of the current editor.
     *
     * @return the current layout algorithm data, or {@code null} if none can be
     *         found
     */
    private LayoutAlgorithmData getCurrentLayoutAlgorithmDataSync() {
        final Maybe<LayoutAlgorithmData> maybeAlgorithmData = new Maybe<LayoutAlgorithmData>();
        Runnable runnable = new Runnable() {
            public void run() {
                IEditorPart editor = EvolUtil.getCurrentEditor();
                EditPart part = EvolUtil.getCurrentEditPart(editor);
                LayoutAlgorithmData algorithmData = EvolUtil.getLayoutAlgorithmData(editor, part);
                maybeAlgorithmData.set(algorithmData);
            }
        };
        MonitoredOperation.runInUI(runnable, true /* synch */);
        LayoutAlgorithmData algorithmData = maybeAlgorithmData.get();
        return algorithmData;
    }

    /**
     * Synchronously gets the set of visible graphical editors.
     *
     * @return set of visible graphical editors; may be empty
     */
    private Set<IEditorPart> getEditorsSync() {
        final Maybe<Set<IEditorPart>> maybeEditors = new Maybe<Set<IEditorPart>>();
        MonitoredOperation.runInUI(new Runnable() {
            public void run() {
                maybeEditors.set(EvolUtil.getEditors());
            }
        }, true /* synch */);

        Set<IEditorPart> editors = maybeEditors.get();
        return editors;
    }

    /**
     * Checks whether the predictors model is valid.
     *
     * @return {@code true} iff predictors model is valid
     */
    private boolean isPredictorsAlgValid() {
        if (this.predictorsEvolAlg == null) {
            EvolPlugin.logStatus("Weights algorithm is not set.");
            return false;
        }

        if (this.predictorsEvolAlg.getPopulation().isEmpty()) {
            EvolPlugin.logStatus("Predictor population is empty.");
            return false;
        }
        return true;
    }

    /**
     * Selects an interesting individual.
     */
    private void selectInterestingIndividual() {

        int firstUnrated = firstUnrated();
        if (firstUnrated > -1) {
            setPosition(firstUnrated);
        }

        updatePosition();
        assert getPosition() >= 0;
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
}
