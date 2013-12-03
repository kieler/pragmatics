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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.LayoutEvolutionModel;
import de.cau.cs.kieler.kiml.evol.alg.EvaluationOperation;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.grana.AnalysisCategory;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.util.KGraphRenderer;

/**
 * The main user interface for evolutionary meta layout.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class EvolutionDialog extends Dialog {
    
    /** the number of individuals to display in the dialog. */
    private static final int INDIVIDUALS_DISPLAY = 16;
    /** the width of each preview image. */
    private static final int PREVIEW_WIDTH = 240;
    /** the height of each preview image. */
    private static final int PREVIEW_HEIGHT = 150;
    /** the number of evolution steps to perform when the "Evolve" button is pressed. */
    private static final int EVOLVE_STEPS = 1;
    /** the maximum value for sliders. */
    private static final int SLIDER_MAX = 100;
    /** maximal difference for metric weights. */
    private static final double MAX_WEIGHT_DIFF = 0.02;
    
    /** property for the preview image of an individual. */
    private static final IProperty<Image> PREVIEW_IMAGE = new Property<Image>("evol.previewImage");
    
    /** the layout mapping for the graph taken for preview. */
    private LayoutMapping<?> layoutMapping;
    /** the resources that need to be disposed. */
    private List<Resource> resources = new LinkedList<Resource>();
    /** the "thumbs up" image. */
    private Image thumbsupImage;
    /** the buttons for selection of individuals. */
    private Button[] selectionButtons;
    /** the labels for displaying individuals. */
    private Label[] previewLabels;
    /** the progress bar for displaying progress of operations. */
    private ProgressBar progressBar;
    /** the labels for displaying metrics results and the sliders for setting weights. */
    private Map<String, Pair<Label, Scale>> metricControls = Maps.newHashMap();
    /** the label for the total fitness value. */
    private Label fitnessLabel;
    /** the label for the generation number. */
    private Label generationLabel;
    /** controls to disable when no evolution model is available. */
    private List<Control> disablingControls = new LinkedList<Control>();
    
    /**
     * Creates an evolution dialog.
     * 
     * @param parentShell the parent shell
     * @param theLayoutMapping mapping for the graph to display as preview
     */
    public EvolutionDialog(final Shell parentShell, final LayoutMapping<?> theLayoutMapping) {
        super(parentShell);
        this.layoutMapping = theLayoutMapping;
        thumbsupImage = EvolPlugin.getImageDescriptor("icons/thumbsup16.gif").createImage();
        resources.add(thumbsupImage);
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
        boolean result = super.close();
        if (result) {
            for (Resource res : resources) {
                res.dispose();
            }
            resources.clear();
        }
        return result;
    }
    
    // CHECKSTYLEOFF MagicNumber
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        ((GridLayout) composite.getLayout()).numColumns = 2;
        
        // create a popup menu for copying the population to the clipboard
        MenuManager popupMenuManager = new MenuManager();
        popupMenuManager.add(new Action("Copy Population") {
            public void run() {
                LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
                Clipboard clipboard = new Clipboard(getShell().getDisplay());
                clipboard.setContents(new Object[] { evolutionModel.toString() },
                        new Transfer[] { TextTransfer.getInstance() });
                clipboard.dispose();
            }
        });
        IMenuService menuService = (IMenuService) PlatformUI.getWorkbench().getService(
                IMenuService.class);
        menuService.populateContributionManager(popupMenuManager, "popup:de.cau.cs.kieler.kiml.evol");
        composite.setMenu(popupMenuManager.createContextMenu(composite));
        
        // create preview areas
        Composite previewPane = new Composite(composite, SWT.NONE);
        previewPane.setMenu(composite.getMenu());
        previewPane.setLayout(new GridLayout((int) Math.sqrt(INDIVIDUALS_DISPLAY), true));
        selectionButtons = new Button[INDIVIDUALS_DISPLAY];
        previewLabels = new Label[INDIVIDUALS_DISPLAY];
        
        Population population = LayoutEvolutionModel.getInstance().getPopulation();
        for (int i = 0; i < INDIVIDUALS_DISPLAY; i++) {
            createPreviewArea(previewPane, i);
            Image image = null;
            if (i < population.size()) {
                image = createPreviewImage(population.get(i));
            }
            if (image == null) {
                // create an empty image in order to set the initial size
                image = new Image(getShell().getDisplay(), PREVIEW_WIDTH, PREVIEW_HEIGHT);
                resources.add(image);
                selectionButtons[i].setEnabled(false);
            }
            previewLabels[i].setImage(image);
        }
        
        // create metrics area
        Composite metricsPane = new Composite(composite, SWT.NONE);
        metricsPane.setMenu(composite.getMenu());
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.verticalSpacing = 8;
        metricsPane.setLayout(gridLayout);
        
        // create buttons for changing all sliders at once
        Composite buttonComposite = new Composite(metricsPane, SWT.NONE);
        buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));
        buttonComposite.setLayout(new FillLayout());
        Button all0Button = new Button(buttonComposite, SWT.PUSH | SWT.FLAT);
        all0Button.setText("All to 0%");
        all0Button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                for (Pair<Label, Scale> metricControl : metricControls.values()) {
                    metricControl.getSecond().setSelection(0);
                }
            }
        });
        Button all100Button = new Button(buttonComposite, SWT.PUSH | SWT.FLAT);
        all100Button.setText("All to 100%");
        all100Button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent event) {
                for (Pair<Label, Scale> metricControl : metricControls.values()) {
                    metricControl.getSecond().setSelection(SLIDER_MAX);
                }
            }
        });
        
        // create metrics sliders and labels
        AnalysisCategory category = AnalysisService.getInstance().getCategory(
                EvaluationOperation.METRIC_CATEGORY);
        for (AnalysisData data : category.getAnalyses()) {
            createMetricArea(metricsPane, data, population);
        }
        AnalysisData executionTimeData = new AnalysisData();
        executionTimeData.setId(EvaluationOperation.EXEC_TIME_METRIC);
        executionTimeData.setName("Execution Time");
        executionTimeData.setDescription("The measured execution time for layout computation.");
        createMetricArea(metricsPane, executionTimeData, population);
        
        // create label for fitness value
        GridData gridData = new GridData(SWT.FILL, SWT.LEFT, true, false);
        gridData.horizontalSpan = 2;
        gridData.verticalIndent = 10;
        new Label(metricsPane, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gridData);
        Label nameLabel = new Label(metricsPane, SWT.NONE);
        nameLabel.setText("Fitness: ");
        nameLabel.setToolTipText("The overall fitness of the individual.");
        nameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        fitnessLabel = new Label(metricsPane, SWT.NONE);
        fitnessLabel.setText("100%");
        fitnessLabel.setVisible(false);
        fitnessLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        
        return composite;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        // create the progress bar
        ((GridLayout) parent.getLayout()).numColumns++;
        progressBar = new ProgressBar(parent, SWT.HORIZONTAL);
        progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        progressBar.setVisible(false);
        
        // create the generation label
        ((GridLayout) parent.getLayout()).numColumns++;
        generationLabel = new Label(parent, SWT.NONE);
        generationLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        generationLabel.setText("Generation "
                + LayoutEvolutionModel.getInstance().getGenerationNumber());
        
        // create the buttons
        Button applyButton = createButton(parent, IDialogConstants.OK_ID, "Apply", true);
        disablingControls.add(applyButton);
        Button evolveButton = createButton(parent, IDialogConstants.PROCEED_ID, "Evolve", false);
        disablingControls.add(evolveButton);
        createButton(parent, IDialogConstants.ABORT_ID, "Restart", false);
        createButton(parent, IDialogConstants.CANCEL_ID, "Cancel", false);
        
        if (LayoutEvolutionModel.getInstance().getPopulation().isEmpty()) {
            applyButton.setEnabled(false);
            evolveButton.setEnabled(false);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonPressed(final int buttonId) {
        switch (buttonId) {
        case IDialogConstants.OK_ID:
            applyFirstSelected();
            applyMetricWeights(false);
            okPressed();
            break;
        case IDialogConstants.CANCEL_ID:
            cancelPressed();
            break;
        case IDialogConstants.PROCEED_ID:
            applyMetricWeights(true);
            adaptMetricWeights();
            evolve();
            break;
        case IDialogConstants.ABORT_ID:
            applyMetricWeights(false);
            restart();
            break;
        }
    }
    
    /**
     * Create a preview area for displaying an individual.
     * 
     * @param parent the parent composite
     * @param index the index of the individual
     */
    private void createPreviewArea(final Composite parent, final int index) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setMenu(parent.getMenu());
        GridLayout gridLayout = new GridLayout();
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        gridLayout.verticalSpacing = 2;
        composite.setLayout(gridLayout);
        
        Label previewLabel = new Label(composite, SWT.BORDER);
        previewLabel.setMenu(parent.getMenu());
        previewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        previewLabels[index] = previewLabel;
        previewLabel.addMouseTrackListener(new MouseTrackAdapter() {
            public void mouseEnter(final MouseEvent e) {
                refreshMetrics(index);
            }
            public void mouseExit(final MouseEvent e) {
                refreshMetrics(-1);
            }
        });
        previewLabel.addMouseListener(new MouseAdapter() {
            public void mouseUp(final MouseEvent e) {
                if (index < LayoutEvolutionModel.getInstance().getPopulation().size()) {
                    new PreviewDialog(getShell(), index).open();
                }
            }
        });
        
        Button selButton = new Button(composite, SWT.CHECK);
        selButton.setImage(thumbsupImage);
        selButton.setToolTipText("Select this layout and promote it for the next evolutions.");
        selButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        selectionButtons[index] = selButton;
    }
    
    /**
     * Create a preview image for the given individual.
     * 
     * @param genome an individual
     * @return a preview image for the individual, or {@code null} if the genome has no graph
     */
    private Image createPreviewImage(final Genome genome) {
        Image previewImage = genome.getProperty(PREVIEW_IMAGE);
        if (previewImage == null || previewImage.isDisposed()) {
            KNode graph = genome.getProperty(EvaluationOperation.LAYOUT_GRAPH);
            if (graph == null) {
                return null;
            }
            KShapeLayout graphSize = graph.getData(KShapeLayout.class);
            double scale;
            KVector offset = new KVector();
            if (graphSize.getWidth() / graphSize.getHeight() > (float) PREVIEW_WIDTH / PREVIEW_HEIGHT) {
                scale = PREVIEW_WIDTH / graphSize.getWidth();
                offset.y = (PREVIEW_HEIGHT - graphSize.getHeight() * scale) / 2;
            } else {
                scale = PREVIEW_HEIGHT / graphSize.getHeight();
                offset.x = (PREVIEW_WIDTH - graphSize.getWidth() * scale) / 2;
            }
            
            Rectangle area = new Rectangle(0, 0, PREVIEW_WIDTH, PREVIEW_HEIGHT);
            previewImage = new Image(getShell().getDisplay(), area.width, area.height);
            resources.add(previewImage);
            KGraphRenderer renderer = new KGraphRenderer(getShell().getDisplay(), scale, offset);
            renderer.render(graph, new GC(previewImage), area);
            genome.setProperty(PREVIEW_IMAGE, previewImage);
            renderer.dispose();
        }
        return previewImage;
    }
    
    /**
     * Create controls for displaying metric results and set their weight.
     * 
     * @param parent the parent composite
     * @param data the analysis data
     * @param population the current population
     */
    private void createMetricArea(final Composite parent, final AnalysisData data,
            final Population population) {
        // create labels for name and result
        Label nameLabel = new Label(parent, SWT.NONE);
        nameLabel.setText(data.getName() + ": ");
        nameLabel.setToolTipText(data.getDescription());
        nameLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        final Label resultLabel = new Label(parent, SWT.NONE);
        resultLabel.setText("100%");
        resultLabel.setVisible(false);
        resultLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
        
        // create slider for the weight
        final Scale scale = new Scale(parent, SWT.HORIZONTAL);
        scale.setMinimum(0);
        scale.setMaximum(SLIDER_MAX);
        GridData gridData = new GridData(SWT.FILL, SWT.TOP, false, false);
        gridData.horizontalIndent = 20;
        gridData.horizontalSpan = 2;
        scale.setLayoutData(gridData);
        scale.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                resultLabel.setText((100 * scale.getSelection() / SLIDER_MAX) + "%");
            }
        });
        scale.addMouseListener(new MouseAdapter() {
            public void mouseDown(final MouseEvent e) {
                resultLabel.setText((100 * scale.getSelection() / SLIDER_MAX) + "%");
                resultLabel.setVisible(true);
            }
            public void mouseUp(final MouseEvent e) {
                resultLabel.setVisible(false);
            }
        });
        metricControls.put(data.getId(), new Pair<Label, Scale>(resultLabel, scale));
        
        // set the initial value for the slider
        int value = SLIDER_MAX;
        Map<String, Double> metricWeights = population.getProperty(
                EvaluationOperation.METRIC_WEIGHT);
        if (metricWeights != null) {
            Double weight = metricWeights.get(data.getId());
            if (weight != null) {
                value = (int) (weight * SLIDER_MAX);
            }
        }
        scale.setSelection(value);
    }
    
    /**
     * Find the first selected individual and apply it as the selected one, used for
     * subsequent layouts. If no individual is selected, take the one with highest rating.
     */
    private void applyFirstSelected() {
        LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
        for (int i = 0; i < selectionButtons.length; i++) {
            if (selectionButtons[i] != null && selectionButtons[i].getSelection()) {
                evolutionModel.setSelected(i);
                return;
            }
        }
        // if no individual was selected, mark the first individual for meta layout
        evolutionModel.setSelected(0);
    }
    
    /**
     * Apply metric weights for all metrics.
     * 
     * @param updateFitness whether fitness values shall be updated if required
     */
    private void applyMetricWeights(final boolean updateFitness) {
        LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
        Population population = evolutionModel.getPopulation();
        Map<String, Double> metricWeights = population.getProperty(EvaluationOperation.METRIC_WEIGHT);
        if (metricWeights == null) {
            metricWeights = Maps.newHashMap();
            population.setProperty(EvaluationOperation.METRIC_WEIGHT, metricWeights);
        }
        
        boolean weightsChanged = false;
        for (Map.Entry<String, Pair<Label, Scale>> entry : metricControls.entrySet()) {
            String id = entry.getKey();
            Scale scale = entry.getValue().getSecond();
            Double oldWeight = metricWeights.get(id);
            double newWeight = (double) scale.getSelection() / SLIDER_MAX;
            metricWeights.put(id, newWeight);
            if (oldWeight != null && Math.abs(newWeight - oldWeight) > MAX_WEIGHT_DIFF) {
                weightsChanged = true;
            }
        }
        
        if (weightsChanged && updateFitness) {
            // recalculate all fitness values, since the weights have changed
            evolutionModel.recalculateFitness();
        }
    }
    
    /**
     * Evolve the current population.
     */
    private void evolve() {
        // perform some steps of the evolution
        try {
            progressBar.setVisible(true);
            BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {
                public void run() {
                    ProgressBarMonitor pbmonitor = new ProgressBarMonitor(progressBar);
                    pbmonitor.begin("Evolution steps", EVOLVE_STEPS);
                    LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
                    synchronized (evolutionModel) {
                        for (int i = 0; i < EVOLVE_STEPS; i++) {
                            evolutionModel.step(pbmonitor.subTask(1));
                        }
                    }
                    pbmonitor.done();
                }
            });
        } catch (Throwable throwable) {
            handleError(throwable);
            return;
        }
        progressBar.setVisible(false);

        refreshPreviews();
        generationLabel.setText("Generation "
                + LayoutEvolutionModel.getInstance().getGenerationNumber());
        generationLabel.getParent().layout();
    }
    
    /**
     * Reset the population to initial values.
     */
    private void restart() {
        // make a new selection of layout options
        OptionSelectionDialog optionSelectionDialog = new OptionSelectionDialog(getShell(),
                LayoutEvolutionModel.getInstance().getLayoutOptions());
        if (optionSelectionDialog.open() == OptionSelectionDialog.OK) {
            final List<LayoutOptionData<?>> selectedOptions = optionSelectionDialog.getSelection();
            if (selectedOptions.isEmpty()) {
                MessageDialog.openError(getShell(), "No Selected Options",
                        "The evolutionary process requires at least one selected option.");
                return;
            }
                        
            try {
                progressBar.setVisible(true);
                BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {
                    public void run() {
                        LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
                        synchronized (evolutionModel) {
                            // reinitialize the population
                            evolutionModel.initializePopulation(layoutMapping, selectedOptions,
                                    getShell().getDisplay(), new ProgressBarMonitor(progressBar));
                        }
                    }
                });
            } catch (Throwable throwable) {
                handleError(throwable);
                return;
            }
            progressBar.setVisible(false);
            
            refreshPreviews();
            for (Control control : disablingControls) {
                control.setEnabled(true);
            }
            generationLabel.setText("Generation "
                    + LayoutEvolutionModel.getInstance().getGenerationNumber());
            generationLabel.getParent().layout();
        }
    }
    
    /**
     * Refresh the preview images after population changes.
     */
    private void refreshPreviews() {
        Population population = LayoutEvolutionModel.getInstance().getPopulation();
        for (int i = 0; i < INDIVIDUALS_DISPLAY; i++) {
            if (i < population.size()) {
                Image image = createPreviewImage(population.get(i));
                previewLabels[i].setImage(image);
                selectionButtons[i].setEnabled(image != null);
            } else {
                previewLabels[i].setImage(null);
                selectionButtons[i].setEnabled(false);
            }
            selectionButtons[i].setSelection(false);
        }
    }
    
    /**
     * Refresh the result labels of layout metrics.
     * 
     * @param genomeIndex the index of the genome to consider, or -1 to disable the labels
     */
    private void refreshMetrics(final int genomeIndex) {
        Population population = LayoutEvolutionModel.getInstance().getPopulation();
        Map<String, Float> metricsResult = null;
        Double fitness = null;
        if (population != null && genomeIndex >= 0 && genomeIndex < population.size()) {
            Genome genome = population.get(genomeIndex);
            metricsResult = genome.getProperty(EvaluationOperation.METRIC_RESULT);
            fitness = genome.getProperty(Genome.FITNESS);
        }
        if (metricsResult == null) {
            metricsResult = Collections.emptyMap();
        }
        
        // set values for all metric results
        for (Map.Entry<String, Pair<Label, Scale>> entry : metricControls.entrySet()) {
            String metricId = entry.getKey();
            Label label = entry.getValue().getFirst();
            Float result = metricsResult.get(metricId);
            if (result == null) {
                label.setVisible(false);
            } else {
                label.setText(Math.round(result * 100) + "%");
                label.setVisible(true);
            }
        }
        
        // set overall fitness value
        if (fitness == null) {
            fitnessLabel.setVisible(false);
        } else {
            fitnessLabel.setText(Math.round(fitness * 100) + "%");
            fitnessLabel.setVisible(true);
        }
    }
    
    /**
     * Adapt the metric weights to the selected individuals.
     */
    private void adaptMetricWeights() {
        LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
        Population population = evolutionModel.getPopulation();
        // find the selected individuals
        int selectedCount = 0;
        boolean[] selected = new boolean[population.size()];
        for (int i = 0; i < selectionButtons.length; i++) {
            if (selectionButtons[i] != null && selectionButtons[i].getSelection()) {
                selected[i] = true;
                selectedCount++;
            }
        }

        if (selectedCount > 0) {
            // adapt the metric weights based on selection
            evolutionModel.adaptMetricWeights(selected);
            
            // recalculate all fitness values, since the weights have changed
            evolutionModel.recalculateFitness();
            
            // refresh the weight sliders from the new values
            Map<String, Double> metricWeights = population.getProperty(
                    EvaluationOperation.METRIC_WEIGHT);
            if (metricWeights != null) {
                for (Map.Entry<String, Pair<Label, Scale>> entry : metricControls.entrySet()) {
                    String id = entry.getKey();
                    Double weight = metricWeights.get(id);
                    if (weight != null) {
                        Scale scale = entry.getValue().getSecond();
                        scale.setSelection((int) Math.round(weight * SLIDER_MAX));
                    }
                }
            }
        }
    }
    
    /**
     * Handle an error that occurred during evolution operation.
     * 
     * @param throwable an error
     */
    private void handleError(final Throwable throwable) {
        setReturnCode(CANCEL);
        close();
        IStatus status = new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                "Error while performing evolution.", throwable);
        StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
    }

}
