package de.cau.cs.kieler.klighd.effects;

import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.views.IDiagramWorkbenchPart;

public class KlighdPanningEffect extends AbstractEffect {

    ContextViewer viewer;
    Object element;
    int animationDuration = 0;
    float zoomLevel = 0;

    public KlighdPanningEffect(final IWorkbenchPart part, final Object element) {
        if (part instanceof IDiagramWorkbenchPart) {
            viewer = ((IDiagramWorkbenchPart) part).getContextViewer();
        }
        this.element = element;
    }

    public KlighdPanningEffect(final IWorkbenchPart part, final Object element,
            final int animationDuration, final float zoomLevel) {
        this(part, element);
        this.animationDuration = animationDuration;
        this.zoomLevel = zoomLevel;
    }

    public KlighdPanningEffect(final IWorkbenchPart part, final Object element,
            final float zoomLevel) {
        this(part, element);
        this.zoomLevel = zoomLevel;
    }

    public KlighdPanningEffect(final IWorkbenchPart part, final Object element,
            final int animationDuration) {
        this(part, element);
        this.animationDuration = animationDuration;
    }

    public void execute() {
        if (element instanceof KGraphElement) {
            viewer.centerOn((KGraphElement) element, animationDuration);
        } else {
            viewer.centerOn(element, animationDuration);
        }
        if (zoomLevel != 0) {
            viewer.zoom(zoomLevel, animationDuration);
        }
    }

}
