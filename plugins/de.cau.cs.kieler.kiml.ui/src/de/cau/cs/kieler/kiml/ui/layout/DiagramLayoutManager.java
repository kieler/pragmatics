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
 */
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Animation;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.RecursiveLayouterEngine;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.triggers.LayoutGraphTrigger;
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas;

/**
 * Abstract superclass for managers of diagram layout.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public abstract class DiagramLayoutManager {

    /** maximal number of recursion levels for which progress is displayed. */
    public static final int MAX_PROGRESS_LEVELS = 2;

    /** the debug canvas to use. */
    private static final DebugCanvas DEBUG_CANVAS = new DebugCanvas();
    /** the layouter engine used to layout diagrams. */
    private static final RecursiveLayouterEngine LAYOUTER_ENGINE =
            new RecursiveLayouterEngine(DEBUG_CANVAS);

    /** the configured priority of the layout manager. */
    private int priority;
    /** a layout configuration that is injected externally. */
    private ILayoutConfig externalLayoutConfig;

    /**
     * Return the manager priority.
     * 
     * @return the priority
     */
    public final int getPriority() {
        return priority;
    }

    /**
     * Set the manager priority.
     * 
     * @param thepriority
     *            the priority to set
     */
    public final void setPriority(final int thepriority) {
        this.priority = thepriority;
    }

    /**
     * Set an external layout configuration to use with this layout manager.
     * Giving {@code null} as parameter resets the configuration to the
     * standard.
     * 
     * @param layoutConfig
     *            a layout configuration, or {@code null}
     */
    public final void setLayoutConfig(final ILayoutConfig layoutConfig) {
        this.externalLayoutConfig = layoutConfig;
    }

    /**
     * Returns the layout configuration that was set externally for this layout
     * manager.
     * 
     * @return the external layout configuration, or {@code null}
     */
    protected final ILayoutConfig getExternalConfig() {
        return externalLayoutConfig;
    }

    /**
     * Performs layout on the given editor or edit part using this layout
     * manager. A progress bar indicating progress of the layout algorithm is
     * optionally shown to the user.
     * 
     * @param workbenchPart
     *            the workbench part for which layout is performed, or {@code null} if
     *            the diagram is not part of an editor
     * @param editPart
     *            the parent edit part for which layout is performed, or
     *            {@code null} if the whole diagram shall be layouted
     * @param animate
     *            if true, Draw2D animation is activated
     * @param progressBar
     *            if true, a progress bar is displayed
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected edit
     *            part, but also for its ancestors
     * @param cacheLayout
     *            if true, the layout result is cached for the underlying model
     */
    public final void layout(final IWorkbenchPart workbenchPart,
            final EditPart editPart, final boolean animate,
            final boolean progressBar, final boolean layoutAncestors,
            final boolean cacheLayout) {
        layout(workbenchPart, editPart, animate, progressBar, layoutAncestors, cacheLayout, false);
    }

    /**
     * Performs layout on the given editor or edit part using this layout
     * manager. A progress bar indicating progress of the layout algorithm is
     * optionally shown to the user.
     * 
     * @param workbenchPart
     *            the workbench part for which layout is performed, or {@code null} if
     *            the diagram is not part of an editor
     * @param editPart
     *            the parent edit part for which layout is performed, or
     *            {@code null} if the whole diagram shall be layouted
     * @param animate
     *            if true, Draw2D animation is activated
     * @param progressBar
     *            if true, a progress bar is displayed
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected edit
     *            part, but also for its ancestors
     * @param cacheLayout
     *            if true, the layout result is cached for the underlying model
     * @param zoom
     *            if true, automatic zoom-to-fit is activated
     */
    public final void layout(final IWorkbenchPart workbenchPart,
            final EditPart editPart, final boolean animate,
            final boolean progressBar, final boolean layoutAncestors,
            final boolean cacheLayout, final boolean zoom) {
        // perform layout with a progress bar
        if (progressBar) {
            final MonitoredOperation monitoredOperation = new MonitoredOperation() {
                // first phase: build the layout graph
                @Override
                protected void preUIexec() {
                    KNode graph = buildLayoutGraph(workbenchPart, editPart, layoutAncestors);
                    LayoutGraphTrigger.triggerPreLayout(graph);
                }

                // second phase: execute layout algorithms
                @Override
                protected IStatus
                        execute(final IProgressMonitor monitor) {
                    return layout(new KielerProgressMonitor(monitor,
                            MAX_PROGRESS_LEVELS), layoutAncestors);
                }

                // third phase: apply layout with animation
                @Override
                protected void postUIexec(final IStatus status) {
                    if (status.getSeverity() == IStatus.OK) {
                        int nodeCount =
                                status == null ? 0 : status.getCode();
                        if (zoom) {
                            applyAndZoom(nodeCount, animate,
                                    cacheLayout);
                        } else {
                            applyAnimatedLayout(animate, cacheLayout,
                                    nodeCount);
                        }
                    }
                }
            };
            monitoredOperation.runMonitored();

            // perform layout without a progress bar
        } else {
            MonitoredOperation.runInUI(new Runnable() {
                // first phase: build the layout graph
                public void run() {
                    KNode graph = buildLayoutGraph(workbenchPart, editPart, layoutAncestors);
                    LayoutGraphTrigger.triggerPreLayout(graph);
                }
            }, true);
            // second phase: execute layout algorithms
            final IStatus status =
                    layout(new BasicProgressMonitor(0), layoutAncestors);
            MonitoredOperation.runInUI(new Runnable() {
                // third phase: apply layout with animation
                public void run() {
                    int nodeCount = status == null ? 0 : status.getCode();
                    if (zoom) {
                        applyAndZoom(nodeCount, animate, cacheLayout);
                    } else {
                        applyAnimatedLayout(animate, cacheLayout, nodeCount);
                    }
                }
            }, false);
        }
    }

    /**
     * Apply layout with zoom and animation.
     * 
     * @param animate
     *            if true, activate Draw2D animation
     * @param cacheLayout
     *            if true, the layout result is cached for the underlying model
     * @param nodeCount
     *            the number of nodes in the layouted diagram
     */
    public final void applyAndZoom(final int nodeCount, final boolean animate,
            final boolean cacheLayout) {
        // determine pre- or post-layout zoom
        final ZoomManager zoomManager =
                getBridge().getZoomManager(getEditPart(getLayoutGraph()));
        KNode parentNode = getLayoutGraph();
        while (parentNode.getParent() != null) {
            parentNode = parentNode.getParent();
        }
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        Dimension available =
                zoomManager.getViewport().getClientArea().getSize();
        float desiredWidth = parentLayout.getWidth();
        double scaleX =
                Math.min(available.width / desiredWidth,
                        zoomManager.getMaxZoom());
        float desiredHeight = parentLayout.getHeight();
        double scaleY =
                Math.min(available.height / desiredHeight,
                        zoomManager.getMaxZoom());
        final double scale = Math.min(scaleX, scaleY);
        final double oldScale = zoomManager.getZoom();

        if (scale < oldScale) {
            zoomManager.setViewLocation(new Point(0, 0));
            zoomManager.setZoom(scale);
            zoomManager.setViewLocation(new Point(0, 0));
        }
        applyAnimatedLayout(animate, cacheLayout, nodeCount);
        if (scale > oldScale) {
            zoomManager.setViewLocation(new Point(0, 0));
            zoomManager.setZoom(scale);
            zoomManager.setViewLocation(new Point(0, 0));
        }
    }

    /**
     * Apply layout with animation and zoom and scroll such that the given
     * elements are visible. Currently experimental, does not yet zoom
     * correctly.
     * 
     * @author haf
     * 
     * @param elements
     *            list of EditParts to which to zoom and scroll to
     * @param animate
     *            if true, activate Draw2D animation
     * @param cacheLayout
     *            if true, the layout result is cached for the underlying model
     * @param nodeCount
     *            the number of nodes in the layouted diagram
     */
    public final void applyAndZoomToElements(final List<EditPart> elements,
            final int nodeCount, final boolean animate,
            final boolean cacheLayout) {
        if (elements == null || elements.isEmpty()) {
            // fallback to normal zoom-to-fit
            applyAndZoom(nodeCount, animate, cacheLayout);
            return;
        }

        // calculate the bounding box for a set of edit parts
        float x1 = Float.NEGATIVE_INFINITY;
        float y1 = Float.NEGATIVE_INFINITY;
        float x2 = Float.NEGATIVE_INFINITY;
        float y2 = Float.NEGATIVE_INFINITY;

        for (EditPart editPart : elements) {
//            System.out.println("Figure Bounds: "
//                    + ((GraphicalEditPart) editPart).getFigure().getBounds());
            KNode node = getLayoutNode(editPart);
            KShapeLayout layout = node.getData(KShapeLayout.class);
            // calculate absolute coordinates
            float x = layout.getXpos(), y = layout.getYpos();
            float w = layout.getWidth(), h = layout.getHeight();
            while (node.getParent() != null) {
                node = node.getParent();
                layout = node.getData(KShapeLayout.class);
                x += layout.getXpos();
                y += layout.getYpos();
                KInsets insets = layout.getInsets();
                x += layout.getXpos() + insets.getLeft();
                y += layout.getYpos() + insets.getTop();
            }
            if (x > x1) {
                x1 = x;
            }
            if (y > y1) {
                y1 = y;
            }
            if (x + layout.getWidth() > x2) {
                x2 = x + w;
            }
            if (y + layout.getHeight() > y2) {
                y2 = y + h;
            }
        }

        // determine pre- or post-layout zoom
        final ZoomManager zoomManager =
                getBridge().getZoomManager(getEditPart(getLayoutGraph()));
        Dimension available =
                zoomManager.getViewport().getClientArea().getSize();
        float desiredWidth = x2 - x1;
        float desiredHeight = y2 - y1;
        double scaleX =
                Math.min(available.width / desiredWidth,
                        zoomManager.getMaxZoom());
        double scaleY =
                Math.min(available.height / desiredHeight,
                        zoomManager.getMaxZoom());
        final double scale = Math.min(scaleX, scaleY);
        final double oldScale = zoomManager.getZoom();

        System.out.println("Bounding box: " + x1 + " " + y1 + " " + x2 + " "
                + y2 + " New Scale: " + scale + " availaible: " + available);
        Point newLocation = new Point(x1, y1);
//        newLocation.scale(1 / scale);
//        RectangleFigure rect = new RectangleFigure();
//        rect.setBounds(new
//        Rectangle(
//                (int)newLocation.x,
//                (int)newLocation.y,
//                (int)(desiredWidth*scale),
//                (int)(desiredHeight*scale)));
//        //rect.setBounds(new Rectangle(39,248,100,100));
//        rect.setLineWidth(5);
//        rect.setForegroundColor(ColorConstants.red);
//        rect.setFill(false);
//        rect.setVisible(true);

        Viewport vp = zoomManager.getViewport();
        // find the top-level Viewport
        EditPart oneElement = elements.get(0); // we already know that elements is not empty
        if (oneElement instanceof GraphicalEditPart) {
            IFigure oneFigure = ((GraphicalEditPart) oneElement).getFigure();
            IFigure parent = oneFigure.getParent();
            while (parent != null) {
                if (parent instanceof Viewport) {
                    vp = (Viewport) parent;
                }
                parent = parent.getParent();
            }
        }
        
        System.out.println("VP and zoomManager VP the same: " + (zoomManager.getViewport() == vp));

        if (scale <= oldScale) {
            zoomManager.setViewLocation(newLocation);
            // zoomManager.setZoom(scale);
            zoomManager.setViewLocation(newLocation);
        }
        applyAnimatedLayout(animate, cacheLayout, nodeCount);
        if (scale > oldScale) {
            zoomManager.setViewLocation(newLocation);
            // zoomManager.setZoom(scale);
            zoomManager.setViewLocation(newLocation);
        }
        // zoomManager.setViewLocation(new Point(39,248));
    }

    /**
     * Apply layout with or without animation.
     * 
     * @param animate
     *            if true, activate Draw2D animation
     * @param cacheLayout
     *            if true, the layout result is cached for the underlying model
     * @param nodeCount
     *            the number of nodes in the layouted diagram
     */
    public final void applyAnimatedLayout(final boolean animate,
            final boolean cacheLayout, final int nodeCount) {
        // transfer layout to the diagram
        transferLayout(cacheLayout);
        if (animate) {
            // apply the layout with animation
            Animation.markBegin();
            applyLayout();
            Animation.run(calcAnimationTime(nodeCount));
        } else {
            // apply the layout without animation
            applyLayout();
        }
    }

    /**
     * Performs layout on the given editor or edit part using this layout
     * manager and a specific progress monitor. The layout graph must be built
     * before this method is called, and the layout must be applied after this
     * method returns.
     * 
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm
     *            is reported
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected edit
     *            part, but also for its ancestors
     * @return a status indicating success or failure; if successful, the status
     *         contains the number of layouted nodes as code value
     */
    public IStatus layout(final IKielerProgressMonitor progressMonitor,
            final boolean layoutAncestors) {
        try {
            // get the layout graph instance
            KNode layoutGraph = getLayoutGraph();
            LayoutGraphTrigger.triggerPostLayout(layoutGraph);

            // perform layout on the layout graph
            DEBUG_CANVAS.setManager(this);
            LAYOUTER_ENGINE.layout(layoutGraph, progressMonitor,
                    layoutAncestors);
            if (progressMonitor.isCanceled()) {
                return new Status(IStatus.CANCEL, KimlUiPlugin.PLUGIN_ID, 0,
                        null, null);
            }

            // return a positive status including graph size
            int graphSize = countNodes(layoutGraph);
            return new Status(IStatus.OK, KimlUiPlugin.PLUGIN_ID, graphSize,
                    null, null);

        } catch (Throwable exception) {
            String message = Messages.getString("kiml.ui.1");
            if (LAYOUTER_ENGINE.getLastLayoutProvider() != null) {
                message +=
                        " ("
                                + LAYOUTER_ENGINE.getLastLayoutProvider()
                                        .getClass().getSimpleName() + ")";
            }
            return new Status(IStatus.ERROR, KimlUiPlugin.PLUGIN_ID, message,
                    exception);
        }
    }

    /**
     * Counts the total number of children in the given node, including deep
     * hierarchies.
     * 
     * @param node
     *            parent node
     * @return number of children and grandchildren in the given parent
     */
    private int countNodes(final KNode node) {
        int count = 0;
        for (KNode child : node.getChildren()) {
            count += countNodes(child) + 1;
        }
        return count;
    }

    /** minimal animation time for diagram layout. */
    private static final int MIN_ANIMATION_TIME = 500;
    /** maximal animation time for diagram layout. */
    private static final int MAX_ANIMATION_TIME = 4000;
    /** factor for animation time calculation. */
    private static final double ANIM_FACT = 100.0;

    /**
     * Calculates animation time for the given graph size.
     * 
     * @param graphSize
     *            total number of nodes in the graph
     * @return number of milliseconds to animate
     */
    public static int calcAnimationTime(final int graphSize) {
        int time =
                MIN_ANIMATION_TIME + (int) (ANIM_FACT * Math.sqrt(graphSize));
        return time <= MAX_ANIMATION_TIME ? time : MAX_ANIMATION_TIME;
    }

    /**
     * Returns the edit part associated with the given layout node. This is only
     * valid after {@link #buildLayoutGraph(IEditorPart, EditPart, boolean)} was
     * called.
     * 
     * @param knode
     *            a node from the layout graph
     * @return the corresponding edit part, or {@code null}
     */
    public EditPart getEditPart(final KNode knode) {
        return null;
    }

    /**
     * Returns the layout node associated with the given edit part. This is only
     * valid after {@link #buildLayoutGraph(IEditorPart, EditPart, boolean)} was
     * called.
     * 
     * @param editPart
     *            an edit part of the currently layouted diagram
     * @return the corresponding layout node, or {@code null}
     */
    public KNode getLayoutNode(final EditPart editPart) {
        return null;
    }

    /*-------------------------------------------------------------------------------------------*/
    /*------------------- Abstract methods to be implemented by subclasses ----------------------*/

    /**
     * Determines whether this layout manager is able to perform layout for the
     * given editor.
     * 
     * @param workbenchPart
     *            a workbench part
     * @return true if this layout manager supports the editor part
     */
    protected abstract boolean supports(IWorkbenchPart workbenchPart);

    /**
     * Determines whether this layout manager is able to perform layout for the
     * given edit part.
     * 
     * @param editPart
     *            an edit part
     * @return true if this layout manager supports the edit part
     */
    protected abstract boolean supports(EditPart editPart);

    /**
     * Builds a KGraph instance for the given editor or edit part. The resulting
     * layout graph should reflect the structure of edit parts in the original
     * diagram.
     * 
     * @param workbenchPart
     *            the workbench part for which layout is performed, or {@code null}
     * @param editPart
     *            the parent edit part for which layout is performed, or
     *            {@code null} if the whole diagram shall be layouted
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected edit
     *            part, but also for its ancestors
     * @return a layout graph instance
     */
    public abstract KNode buildLayoutGraph(IWorkbenchPart workbenchPart,
            EditPart editPart, boolean layoutAncestors);

    /**
     * Returns the graphical framework bridge for this layout manager.
     * 
     * @return a framework bridge that is suitable for diagrams that are managed
     *         by this layout manager
     */
    public abstract IGraphicalFrameworkBridge getBridge();

    /**
     * Returns a layout configuration for the given edit part. If
     * {@code editPart} is {@code null}, a generic layout configuration is
     * created.
     * 
     * @param editPart
     *            an edit part
     * @return a layout configuration for the edit part, or a generic
     *         configuration
     */
    public abstract ILayoutConfig getLayoutConfig(EditPart editPart);

    /**
     * Transfers all layout data from the last created KGraph instance to the
     * original diagram. The diagram is not modified yet, but all required
     * preparations are performed.
     * 
     * @param cacheLayout
     *            if true, the layout result is cached for the underlying model
     */
    protected abstract void transferLayout(boolean cacheLayout);

    /**
     * Applies the transferred layout to the original diagram. This final step
     * is where the actual change to the diagram is done.
     */
    protected abstract void applyLayout();

    /**
     * Returns the last built layout graph.
     * 
     * @return the last built layout graph
     */
    public abstract KNode getLayoutGraph();

    /**
     * Returns the cached layout for the last layout run.
     * 
     * @return the last cached layout
     */
    protected abstract ICachedLayout getCachedLayout();

}
