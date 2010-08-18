/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/

package de.cau.cs.kieler.karma;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.BoundsImpl;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;

/**
 * @author ckru
 */
public abstract class AdvancedRenderingShapeNodeEditPart extends ShapeNodeEditPart implements
        IAdvancedRenderingEditPart {

    /**
     * Figure that that represents the model element.
     */
    // SUPPRESS CHECKSTYLE NEXT VisibilityModifier
    protected IFigure primaryShape;

    /**
     * The list of conditions and the corresponding string for generating the figure.
     */
    private List<Pair<String, ICondition<EObject>>> conditions;

    /**
     * The figure provider for generating the figures from a string.
     */
    private IFigureProvider figureProvider;

    /**
     * Container for the last positive condition. Used for performance optimizations.
     */
    private ICondition<EObject> lastCondition = null;

    /**
     * The constructor. Just calls super.
     * 
     * @param view
     *            to be given to super
     */
    public AdvancedRenderingShapeNodeEditPart(final View view) {
        super(view);
        String className = this.getClass().getName();
        ConditionProvider conditionProvider = ConditionProvider.getInstance();
        conditions = conditionProvider.getPairs(className);
        figureProvider = conditionProvider.getFigureProvider(className);

    }

    @Override
    public void handleNotificationEvent(final Notification notification) {
        super.handleNotificationEvent(notification);
        /*
         * if (flag) { annotate(); }
         */
        if (!(notification.isTouch()) && !(notification.getNotifier() instanceof BoundsImpl)) {
            if (primaryShape != null) {
                if (primaryShape instanceof SwitchableFigure) {
                    SwitchableFigure attrFigure = (SwitchableFigure) primaryShape;
                    // attrFigure.setCurrentFigure(figureProvider.getDefaultFigure());
                    boolean changed = this.updateFigure(attrFigure);
                    if (changed) {
                        LayoutManager layoutManager = attrFigure.getLayoutManager();
                        if (layoutManager != null) {
                            layoutManager.layout(attrFigure);
                        }
                        this.refresh();
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateFigure(final IFigure figure) {
        if ((figure instanceof SwitchableFigure) && (conditions != null)) {
            if (!(conditions.isEmpty())) {
                SwitchableFigure attrFigure = (SwitchableFigure) figure;
                IFigure oldFigure = attrFigure.getCurrentFigure();
                IFigure newFigure = null;
                for (Pair<String, ICondition<EObject>> cf : conditions) {
                    if (cf.getSecond().evaluate(this.getModelElement())) {
                        if (lastCondition == cf.getSecond()) {
                            return false;
                        } else {
                            newFigure = figureProvider.getFigureByString(cf.getFirst(), oldFigure,
                                    null);
                            attrFigure.setCurrentFigure(newFigure);
                            lastCondition = cf.getSecond();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * test stuff, ignore this.
     */
    /*
     * private boolean flag = true;
     */
    /**
     * even more test stuff, ignore this.
     */
    /*
     * private void annotate() { EObject modelElement = this.getModelElement(); if (modelElement
     * instanceof Annotatable) { Annotatable annotatable = (Annotatable) modelElement;
     * 
     * StringAnnotation stringAnnotation = AnnotationsFactoryImpl.eINSTANCE
     * .createStringAnnotation(); stringAnnotation.setName("MyTestName1");
     * stringAnnotation.setValue("testValue");
     * 
     * BooleanAnnotation booleanAnnotation = AnnotationsFactoryImpl.eINSTANCE
     * .createBooleanAnnotation(); booleanAnnotation.setName("MyTestName2");
     * booleanAnnotation.setValue(true);
     * 
     * FloatAnnotation floatAnnotation = AnnotationsFactoryImpl.eINSTANCE .createFloatAnnotation();
     * floatAnnotation.setName("MyTestName3"); floatAnnotation.setValue(42.23f);
     * 
     * IntAnnotation intAnnotation = AnnotationsFactoryImpl.eINSTANCE.createIntAnnotation();
     * intAnnotation.setName("MyTestName4"); intAnnotation.setValue(42);
     * 
     * ReferenceAnnotation referenceAnnotation = AnnotationsFactoryImpl.eINSTANCE
     * .createReferenceAnnotation(); referenceAnnotation.setName("MyTestName5");
     * referenceAnnotation.setObject(new testClass());
     * 
     * ContainmentAnnotation containmentAnnotation =
     * AnnotationsFactoryImpl.eINSTANCE.createContainmentAnnotation();
     * containmentAnnotation.setName("MyTestName6"); containmentAnnotation.setObject(new
     * testClass());
     * 
     * annotatable.getAnnotations().add(containmentAnnotation); flag = false; } }
     * 
     * public class testClass extends EObjectImpl{
     * 
     * }
     */
    /**
     * Getter of the model element for conveniences sake.
     * 
     * @return the modelElement of this editPart
     */
    public EObject getModelElement() {
        return this.getNotationView().getElement();
    }

}
