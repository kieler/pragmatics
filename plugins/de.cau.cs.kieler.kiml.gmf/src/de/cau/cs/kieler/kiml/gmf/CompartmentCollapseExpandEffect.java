package de.cau.cs.kieler.kiml.gmf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.tools.CompartmentCollapseTracker;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.BasicCompartment;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.kivi.IEffect;
import de.cau.cs.kieler.core.model.util.ModelingUtil;

/**
 * This Effect collapses or expands compartments. The execute() method expands while the undo method
 * collapses.
 * 
 * @author haf
 */
public class CompartmentCollapseExpandEffect extends AbstractEffect {

    private int compartmentLevel = 0;
    private IResizableCompartmentEditPart targetEditPart;
    private EObject targetNode;
    private boolean collapse;
    private boolean originalCollapseState;
    private boolean doLayout;
    private boolean executed = false;
    private DiagramEditor editor;

    /**
     * The compartment level gives the hierarchy to which to search for compartments to collapse.
     * 
     * @param theCompartmentLevel hierarchy level. 0 means only exactly the given EditPart.
     */
    public CompartmentCollapseExpandEffect(DiagramEditor targetEditor, EObject targetNode,
        EStructuralFeature featureToCollapse, final int theCompartmentLevel, boolean layout,
        boolean collapse) {
        this.compartmentLevel = theCompartmentLevel;
        this.collapse = collapse;
        this.editor = targetEditor;
        this.doLayout = layout;
        this.targetNode = targetNode;
        // FIXME: the original state should be taken from the diagram itself
        this.originalCollapseState = !collapse;
        EditPart parentPart = ModelingUtil.getEditPart(targetEditor.getDiagramEditPart(),
            targetNode);
        if (parentPart != null) {
            outer: for (Object child : parentPart.getChildren()) {
                if (child instanceof IResizableCompartmentEditPart) {
                    for (Object grandChild : ((IResizableCompartmentEditPart) child).getChildren()) {
                        if (grandChild instanceof EditPart) {
                            EObject grandChildSemantic = ((View) ((EditPart) grandChild).getModel())
                                .getElement();
                            if (grandChildSemantic.eContainingFeature() == featureToCollapse) {
                                targetEditPart = (IResizableCompartmentEditPart) child;
                                break outer;
                            }
                        }
                    }
                }
            }
        }
    }

    public void execute() {
        if (targetEditPart != null && !executed) {
            executed = true;
            setCollapsed(targetEditPart, collapse);
            if (doLayout) {
                new LayoutEffect(editor, targetNode).schedule();
            }
        }
    }

    /**
     * Undo the effect, i.e. expand a collapsed compartment.
     */
    public void undo() {
        if (targetEditPart != null) {
            executed = false;
            setCollapsed(targetEditPart, originalCollapseState);
            if (doLayout) {
                new LayoutEffect(editor, targetNode).schedule();
            }
        }
    }

    public void setCollapsed(boolean collapsed) {
        if(collapsed != collapse){
            this.collapse = collapsed;
            executed = false;
        }
        
    }

    public boolean getCollapsed() {
        return this.collapse;
    }

    /*
     * The following works but performance is quite slow. It correctly collapses but it takes some
     * serious amount of time until connections get hidden/visible.
     * 
     * private void setCollapsed(final GraphicalEditPart editPart, final boolean value) throws
     * KielerModelException {
     * 
     * final View view = (View) editPart.getModel(); try { final TransactionalEditingDomain domain =
     * TransactionUtil.getEditingDomain(view);
     * 
     * AbstractEMFOperation op = new AbstractEMFOperation(domain,
     * "Viewmanagement set collapsed state") {
     * 
     * @Override protected IStatus doExecute(final IProgressMonitor monitor, final IAdaptable info)
     * throws ExecutionException {
     * 
     * List<BasicCompartment> compartments = getNestedCompartments(view, compartmentLevel); for
     * (BasicCompartment compartment : compartments) {
     * ViewUtil.setStructuralFeatureValue(compartment, NotationPackage.eINSTANCE
     * .getDrawerStyle_Collapsed(), value); } return Status.OK_STATUS; } }; op.execute(null, null);
     * } catch (ExecutionException e0) { throw new
     * KielerModelException("Could change collapsed state of compartments.", view .getElement(),
     * e0); } }
     */
    /**
     * Set the collapsed state of the given compartment.
     * 
     * @param editPart the input editPart
     * @param value true iff should get collapsed, false if expanded
     * @throws KielerModelException
     */
    private void setCollapsed(final IResizableCompartmentEditPart editPart, final boolean value) {
        CompartmentCollapseTrackerEx tracker = new CompartmentCollapseTrackerEx(editPart);
        tracker.setCollapsed(value);
    }

    /**
     * Give all compartments of a view. The list will also contain the input view itself if it is a
     * compartment. Additionally it traverses the whole child tree and also returns all nested child
     * compartments. The list is ordered from root following a dfs.
     * 
     * @param view input view where search is started
     * @param level how deep to go into hierarchy: 0 will return only the view itself if it is a
     *            compartment
     * @return a List of all compartments of the view
     */
    private static List<BasicCompartment> getNestedCompartments(final View view, final int level) {
        List<BasicCompartment> compartments = new ArrayList<BasicCompartment>();
        if (view instanceof BasicCompartment) {
            compartments.add((BasicCompartment) view);
        }
        // recursively call method for all children
        if (level > 0) {
            List<?> childViews = view.getChildren();
            for (Object child : childViews) {
                if (child instanceof View) {
                    compartments.addAll(getNestedCompartments((View) child, (level - 1)));
                }
            }
        }
        return compartments;
    }

    /**
     * Inner class that takes care about the concrete collapse command. Extends the official
     * CompartmentCollapseTracker that is also used for the manual collapsing with the mouse.
     * 
     * FIXME: This might be some overhead as the tracker is a quite heavy tool.
     * 
     * @author haf
     * 
     */
    class CompartmentCollapseTrackerEx extends CompartmentCollapseTracker {

        public CompartmentCollapseTrackerEx(IResizableCompartmentEditPart compartmentEditPart) {
            super(compartmentEditPart);
            this.setEditDomain((EditDomain) compartmentEditPart.getDiagramEditDomain());
        }

        void setCollapsed(boolean value) {
            setCurrentCommand(getCommand(value));
            executeCurrentCommand();
        }

    }
}
