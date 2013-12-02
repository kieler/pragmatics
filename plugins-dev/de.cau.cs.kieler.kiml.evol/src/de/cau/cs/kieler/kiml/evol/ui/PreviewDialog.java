/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.evol.LayoutEvolutionModel;
import de.cau.cs.kieler.kiml.evol.alg.EvaluationOperation;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ui.util.KGraphRenderer;

/**
 * A popup dialog for displaying genome previews in 100% zoom factor.
 * 
 * @author msp
 */
public class PreviewDialog extends PopupDialog {
    
    /**
     * Create the title string for the dialog.
     * 
     * @param index the index of the individual to display
     * @return
     */
    private static String createTitle(final int index) {
        StringBuilder titleBuilder = new StringBuilder();
        titleBuilder.append("Preview of individual ").append(index);
        Population population = LayoutEvolutionModel.getInstance().getPopulation();
        if (index >= 0 && index < population.size()) {
            Genome genome = population.get(index);
            Double fitness = genome.getProperty(Genome.FITNESS);
            if (fitness != null) {
                // SUPPRESS CHECKSTYLE NEXT MagicNumber
                titleBuilder.append(" (fitness: ").append(Math.round(fitness * 100)).append("%)");
            }
        }
        return titleBuilder.toString();
    }
    
    /** the index of the displayed genome. */
    private final int genomeIndex;
    /** the image that is created to preview the genome. */
    private Image previewImage;
    
    /**
     * Create a preview dialog for the genome with given index.
     * 
     * @param parent the parent shell
     * @param index the index of the displayed genome
     */
    public PreviewDialog(final Shell parent, final int index) {
        super(parent, SWT.NONE, true, false, false, false, false,
                createTitle(index), null);
        this.genomeIndex = index;
    }
    
    /**
     * Retrieve the genome that is to be displayed.
     * 
     * @return the genome to display, or {@code null}
     */
    private Genome getGenome() {
        Population population = LayoutEvolutionModel.getInstance().getPopulation();
        if (genomeIndex >= 0 && genomeIndex < population.size()) {
            return population.get(genomeIndex);
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        if (previewImage != null) {
            previewImage.dispose();
        }
        return super.close();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final ScrolledComposite composite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        Genome genome = getGenome();
        if (genome != null) {
            KNode graph = genome.getProperty(EvaluationOperation.LAYOUT_GRAPH);
            if (graph != null) {
                KShapeLayout graphSize = graph.getData(KShapeLayout.class);
                
                Rectangle area = new Rectangle(0, 0, (int) graphSize.getWidth() + 1,
                        (int) graphSize.getHeight() + 1);
                previewImage = new Image(getShell().getDisplay(), area.width, area.height);
                KGraphRenderer renderer = new KGraphRenderer(getShell().getDisplay());
                renderer.render(graph, new GC(previewImage), area);
                
                Label label = new Label(composite, SWT.NONE);
                label.setImage(previewImage);
                label.setSize(area.width, area.height);
                composite.setContent(label);
                
                DragListener dragListener = new DragListener(composite);
                label.addMouseListener(dragListener);
                label.addMouseMoveListener(dragListener);
            }
        }
        return composite;
    }
    
    /**
     * Drag listener for convenient scrolling in large preview images.
     */
    private static class DragListener extends MouseAdapter implements MouseMoveListener {
        
        /** the scrolled composite to scroll. */
        private final ScrolledComposite composite;
        /** the last known mouse position. */
        private Point lastPosition;
        
        /**
         * Create a drag listener for the given scrolled composite.
         * 
         * @param composite the scrolled composite to scroll
         */
        DragListener(final ScrolledComposite composite) {
            this.composite = composite;
        }
        
        /**
         * {@inheritDoc}
         */
        public void mouseDown(final MouseEvent event) {
            Point origin = composite.getOrigin();
            lastPosition = new Point(event.x - origin.x, event.y - origin.y);
        }

        /**
         * {@inheritDoc}
         */
        public void mouseUp(final MouseEvent event) {
            lastPosition = null;
        }

        /**
         * {@inheritDoc}
         */
        public void mouseMove(final MouseEvent event) {
            if (lastPosition != null) {
                Point origin = composite.getOrigin();
                Point newPosition = new Point(event.x - origin.x, event.y - origin.y);
                origin.x -= newPosition.x - lastPosition.x;
                origin.y -= newPosition.y - lastPosition.y;
                composite.setOrigin(origin);
                lastPosition = newPosition;
            }
        }
        
    }

}
