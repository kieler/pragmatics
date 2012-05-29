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

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.LayoutEvolutionModel;
import de.cau.cs.kieler.kiml.evol.alg.EvaluationOperation;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.util.KGraphRenderer;

/**
 * The main user interface for evolutionary meta layout.
 *
 * @author msp
 */
public class EvolutionDialog extends Dialog {
    
    /** the number of individuals to display in the dialog. */
    private static final int INDIVIDUALS_DISPLAY = 16;
    /** the width of each preview image. */
    private static final int PREVIEW_WIDTH = 240;
    /** the height of each preview image. */
    private static final int PREVIEW_HEIGHT = 150;
    /** the number of evolution steps to perform when the "Evolve" button is pressed. */
    private static final int EVOLVE_STEPS = 10;
    
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
        if (evolutionModel.getPopulation() == null) {
            try {
                PlatformUI.getWorkbench().getProgressService().busyCursorWhile(
                        new IRunnableWithProgress() {
                    public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                            InterruptedException {
                        monitor.beginTask("Initialize Evolution", 1);
                        evolutionModel.initializePopulation(layoutMapping);
                        monitor.done();
                    }
                });
            } catch (Exception exception) {
                throw new WrappedException(exception);
            }
        }
        
        Composite composite = (Composite) super.createDialogArea(parent);
        ((GridLayout) composite.getLayout()).numColumns = (int) Math.sqrt(INDIVIDUALS_DISPLAY);
        selectionButtons = new Button[INDIVIDUALS_DISPLAY];
        previewLabels = new Label[INDIVIDUALS_DISPLAY];
        
        Population population = evolutionModel.getPopulation();
        for (int i = 0; i < INDIVIDUALS_DISPLAY; i++) {
            createPreviewArea(composite, i);
            if (i < population.size()) {
                Image image = createPreviewImage(population.get(i));
                previewLabels[i].setImage(image);
            } else {
                selectionButtons[i].setEnabled(false);
            }
        }
        
        return composite;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, "Apply", true);
        createButton(parent, IDialogConstants.NEXT_ID, "Evolve", false);
        createButton(parent, IDialogConstants.CANCEL_ID, "Cancel", false);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonPressed(final int buttonId) {
        switch (buttonId) {
        case IDialogConstants.OK_ID:
            applyUserRating();
            okPressed();
            break;
        case IDialogConstants.CANCEL_ID:
            cancelPressed();
            break;
        case IDialogConstants.NEXT_ID:
            applyUserRating();
            evolve();
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
        composite.setLayout(new GridLayout());
        
        Label previewLabel = new Label(composite, SWT.BORDER);
        previewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        previewLabels[index] = previewLabel;
        
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
     * @return a preview image for the individual
     */
    private Image createPreviewImage(final Genome genome) {
        Image previewImage = genome.getProperty(PREVIEW_IMAGE);
        if (previewImage == null || previewImage.isDisposed()) {
            KNode graph = genome.getProperty(EvaluationOperation.LAYOUT_GRAPH);
            if (graph == null) {
                throw new IllegalStateException("Missing evaluation graph for genome preview.");
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
        }
        return previewImage;
    }
    
    /**
     * Apply user rating to all individuals.
     */
    private void applyUserRating() {
        LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
        boolean selectedFound = false;
        for (int i = 0; i < selectionButtons.length; i++) {
            if (selectionButtons[i] != null && selectionButtons[i].getSelection()) {
                Genome genome = evolutionModel.getPopulation().get(i);
                genome.setProperty(Genome.USER_RATING, 1.0);
                genome.setProperty(Genome.USER_WEIGHT, 1.0);
                if (!selectedFound) {
                    selectedFound = true;
                    evolutionModel.setSelected(i);
                }
            }
        }
        // if no individual was selected, mark the first individual for meta layout
        if (!selectedFound) {
            evolutionModel.setSelected(0);
        }
    }
    
    /**
     * Evolve the current population.
     */
    private void evolve() {
        final LayoutEvolutionModel evolutionModel = LayoutEvolutionModel.getInstance();
        
        // perform some steps of the evolution
        try {
            PlatformUI.getWorkbench().getProgressService().busyCursorWhile(
                    new IRunnableWithProgress() {
                public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                        InterruptedException {
                    monitor.beginTask("Evolve", EVOLVE_STEPS);
                    for (int i = 0; i < EVOLVE_STEPS; i++) {
                        evolutionModel.step();
                        monitor.worked(1);
                    }
                    monitor.done();
                }
            });
        } catch (Exception exception) {
            throw new WrappedException(exception);
        }

        // replace the current preview images by the new population
        Population population = evolutionModel.getPopulation();
        for (int i = 0; i < INDIVIDUALS_DISPLAY; i++) {
            if (i < population.size()) {
                Image image = createPreviewImage(population.get(i));
                previewLabels[i].setImage(image);
                selectionButtons[i].setEnabled(true);
            } else {
                previewLabels[i].setImage(null);
                selectionButtons[i].setEnabled(false);
            }
        }
    }

}
