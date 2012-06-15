/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.karma.kivi;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.model.gmf.IAdvancedRenderingEditPart;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.karma.AdvancedRenderingLabelEditPart;
import de.cau.cs.kieler.karma.IRenderingProvider;
import de.cau.cs.kieler.karma.IRenderingProvider.CollapseStatus;
import de.cau.cs.kieler.karma.SwitchableFigure;

/**
 * 
 * With this effect you can manually trigger a new karma rendering. This behaves as if a condition
 * has just been evaluated to true and sets a new rendering according to the given parameters.
 * 
 * Be careful there are no conflicting conditions i.e. if at the same time as this is executed a
 * condition that wants a different rendering is evaluated true it might produce an unexpected
 * result.
 * 
 * @author ckru
 * 
 */
public class KarmaEffect extends AbstractEffect {

    private IRenderingProvider renderingProvider = null;
    private IFigure figure = null;
    private IFigure oldFigure = null;
    private EObject modelElement = null;
    private IGraphicalEditPart editPart = null;

    private String layoutParam = null;
    private String figureParam = null;
    private String borderItemParam = null;
    private Pair<Integer, Integer> figureSize = new Pair<Integer, Integer>(-1, -1);

    private CollapseStatus collapseStatus = CollapseStatus.UNCHANGED;

    /**
     * Constructor for initializing a new karma effect that executes a new rendering according to
     * the given parameters.
     * 
     * @param renderingProvider
     *            the rendering provider to be used for the rendering.
     * @param figure
     *            the figure whose rendering should be changed. This is supposed to be the container
     *            figure which is likely of type SwitchableFigure. 
     *            You can often get it by calling someIAdvancedREnderingEditPart.getPrimaryShape().
     * @param modelElement
     *            the modelelement whose rendering should be changed.
     * @param editPart
     *            the editpart whose rendering should be changed
     * @param figureParam
     *            the figureParam to be given to the rendering provider. May be null if no change is
     *            intended.
     * @param layoutParam
     *            the layoutParam to be given to the rendering provider. May be null if no change is
     *            intended.
     * @param borderItemParam
     *            the borderItemParam to be given to the rendering provider. May be null if no
     *            change is intended.
     * @param figureSize
     *            the figureSize that sets a fixed node size. May be null if no change is intended.
     * @param collapseStatus
     *            the collapse status to be given to the rendering provider, default is
     *            CollapseStatus.UNCHANGED .
     */
    public KarmaEffect(final IRenderingProvider renderingProvider, final IFigure figure,
            final EObject modelElement, final IGraphicalEditPart editPart,
            final String figureParam, final String layoutParam, final String borderItemParam,
            final Pair<Integer, Integer> figureSize, final CollapseStatus collapseStatus) {
        this.renderingProvider = renderingProvider;
        this.figure = figure;
        this.modelElement = editPart.getNotationView().getElement();
        this.editPart = editPart;
        this.layoutParam = layoutParam;
        this.figureParam = figureParam;
        this.borderItemParam = borderItemParam;
        if (figureSize != null) {
            this.figureSize = figureSize;
        }
        if (collapseStatus != null) {
            this.collapseStatus = collapseStatus;
        }
    }

    public void execute() {
        SwitchableFigure switchableFigure = null;
        if ((figure instanceof SwitchableFigure)) {
            switchableFigure = (SwitchableFigure) figure;
            oldFigure = switchableFigure.getCurrentFigure();
        } else {
            oldFigure = figure;
        }
        this.setFigure(renderingProvider, figureParam, oldFigure, modelElement, switchableFigure,
                editPart);
        this.setLayoutManager(figure, renderingProvider, layoutParam, modelElement);
        this.setBorderItemLocator(editPart, renderingProvider, borderItemParam, modelElement,
                figure);

        // setting a fixed node size
        if (((figureSize.getFirst() >= 0) && (figureSize.getSecond() >= 0))
                && switchableFigure != null) {
            Dimension size = new Dimension(figureSize.getFirst(), figureSize.getSecond());
            setFixedNodeSize(switchableFigure, figure, size);
        } else {
            Dimension size = renderingProvider.getSizeByString(figureParam, modelElement, editPart);
            if (size != null) {
                setFixedNodeSize(switchableFigure, figure, size);
            }
        }
    }

    public CollapseStatus getCollapseStatus() {
        return collapseStatus;
    }

    public void setCollapseStatus(CollapseStatus collapseStatus) {
        this.collapseStatus = collapseStatus;
    }

    /**
     * method that gets a figure from the renderingProvider and sets it to the SwitchableFigure for
     * display.
     * 
     * @param renderingProvider
     *            the renderingProvider to get the new figure from.
     * @param figureParam
     *            the string representation of the new figure to be given to the renderingProvider.
     * @param oldFigure
     *            the old figure.
     * @param modelElement
     *            the modelElement whose graphical representation should be changed
     * @param switchableFigure
     *            the enclosing SwitchableFigure
     */
    private void setFigure(final IRenderingProvider renderingProvider, final String figureParam,
            final IFigure oldFigure, final EObject modelElement,
            final SwitchableFigure switchableFigure, final EditPart part) {
        // setting the new figure
        IFigure newFigure = renderingProvider.getFigureByString(figureParam, oldFigure,
                modelElement, part);
        if (newFigure != null) {
            if (switchableFigure != null) {
                switchableFigure.setCurrentFigure(newFigure);
            }
        }
    }

    /**
     * Method to get a new LayoutManager from the RenderingProvider and set it.
     * 
     * @param figure
     *            the figure whose LayoutManager should be changed.
     * @param renderingProvider
     *            the RenderingProvider.
     * @param layoutParam
     *            the string representation of the layout to be given to the RenderingProvider.
     * @param modelElement
     *            the ModelElement whose figures LayoutManager should be changed.
     */
    private void setLayoutManager(final IFigure figure, final IRenderingProvider renderingProvider,
            final String layoutParam, final EObject modelElement) {
        // setting the LayoutManager
        if (figure != null) {
            LayoutManager newLayoutManager = renderingProvider.getLayoutManagerByString(
                    layoutParam, figure.getLayoutManager(), modelElement);
            if (newLayoutManager != null) {
                figure.setLayoutManager(newLayoutManager);
            }
        }
    }

    /**
     * Method to get a new BorderItemLocator from the RenderingProvider and setting it to the
     * BorderItem.
     * 
     * @param editPart
     *            the EditPart of the BorderItem.
     * @param renderingProvider
     *            the RenderingProvider.
     * @param borderItemParam
     *            the string representation of the new BorderItemLocator.
     * @param modelElement
     *            the ModelElement of the BorderItem
     * @param figure
     *            the Figure of the BorderItem
     */
    private void setBorderItemLocator(final IGraphicalEditPart editPart,
            final IRenderingProvider renderingProvider, final String borderItemParam,
            final EObject modelElement, final IFigure figure) {
        // sets the new BoderItemLocator. unfortunately pretty hacked to get the right elements and
        // special cases
        if (editPart instanceof IBorderItemEditPart) {
            if (editPart.getParent() instanceof AbstractBorderedShapeEditPart) {
                AbstractBorderedShapeEditPart parent = ((AbstractBorderedShapeEditPart) editPart
                        .getParent());
                IFigure mainFigure = parent.getMainFigure();
                // special case for labels since hierarchy is different
                if (editPart instanceof AdvancedRenderingLabelEditPart) {
                    IFigure contentPane = editPart.getContentPane();
                    if (contentPane != null) {
                        IBorderItemLocator oldLocator = (IBorderItemLocator) contentPane
                                .getParent().getLayoutManager().getConstraint(contentPane);
                        IBorderItemLocator newLocator = renderingProvider
                                .getBorderItemLocatorByString(borderItemParam, mainFigure,
                                        oldLocator, modelElement, this.collapseStatus);
                        parent.setLayoutConstraint(editPart, contentPane, newLocator);
                    } else {
                        // lastCondition = null;
                    }
                } else {
                    // this is the code for ports etc.
                    IFigure parentsParent = figure.getParent().getParent();
                    if (parentsParent instanceof BorderedNodeFigure) {
                        BorderedNodeFigure borderedNodeFigure = (BorderedNodeFigure) parentsParent;
                        if (borderedNodeFigure.getParent() != null) {
                            IBorderItemLocator oldLocator = (IBorderItemLocator) borderedNodeFigure
                                    .getParent().getLayoutManager()
                                    .getConstraint(borderedNodeFigure);
                            IBorderItemLocator newLocator = renderingProvider
                                    .getBorderItemLocatorByString(borderItemParam, mainFigure,
                                            oldLocator, modelElement, this.collapseStatus);
                            if (oldLocator != newLocator) {
                                parent.setLayoutConstraint(editPart, borderedNodeFigure, newLocator);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to set a fixed size of a SwitchableFigure.
     * 
     * @param switchableFigure
     *            enclosing figure of the figure whose size should be set.
     * @param figure
     *            the figure whose size should be set.
     * @param dim
     *            the new fixed size
     */
    private void setFixedNodeSize(final SwitchableFigure switchableFigure, final IFigure figure,
            final Dimension dim) {
        figure.getBounds().setSize(dim);
        figure.setMaximumSize(dim.getCopy());
        figure.setMinimumSize(dim.getCopy());
        figure.setPreferredSize(dim.getCopy());
        if (figure.getParent() instanceof DefaultSizeNodeFigure) {
            ((DefaultSizeNodeFigure) figure.getParent()).setDefaultSize(figure.getSize().getCopy());
        } else if (figure.getParent() instanceof NodeFigure) {
            ((NodeFigure) figure.getParent()).setSize(figure.getSize().getCopy());
        }
        switchableFigure.setResizeable(false);
    }

    public IRenderingProvider getRenderingProvider() {
        return renderingProvider;
    }

    public void setRenderingProvider(final IRenderingProvider renderingProvider) {
        this.renderingProvider = renderingProvider;
    }

    public IFigure getFigure() {
        return figure;
    }

    public void setFigure(final IFigure figure) {
        this.figure = figure;
    }

    public IFigure getOldFigure() {
        return oldFigure;
    }

    public void setOldFigure(final IFigure oldFigure) {
        this.oldFigure = oldFigure;
    }

    public EObject getModelElement() {
        return modelElement;
    }

    public void setModelElement(final EObject modelElement) {
        this.modelElement = modelElement;
    }

    public IGraphicalEditPart getEditPart() {
        return editPart;
    }

    public void setEditPart(final IGraphicalEditPart editPart) {
        this.editPart = editPart;
    }

    public String getLayoutParam() {
        return layoutParam;
    }

    public void setLayoutParam(final String layoutParam) {
        this.layoutParam = layoutParam;
    }

    public String getFigureParam() {
        return figureParam;
    }

    public void setFigureParam(final String figureParam) {
        this.figureParam = figureParam;
    }

    public String getBorderItemParam() {
        return borderItemParam;
    }

    public void setBorderItemParam(final String borderItemParam) {
        this.borderItemParam = borderItemParam;
    }

    public Pair<Integer, Integer> getFigureSize() {
        return figureSize;
    }

    public void setFigureSize(final Pair<Integer, Integer> figureSize) {
        this.figureSize = figureSize;
    }

}
