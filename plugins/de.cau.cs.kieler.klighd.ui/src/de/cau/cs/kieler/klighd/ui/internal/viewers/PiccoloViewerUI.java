/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.ui.internal.viewers;

import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.google.common.base.Function;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.IModelModificationHandler;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.ISynthesis;
import de.cau.cs.kieler.klighd.piccolo.internal.events.KlighdBasicInputEventHandler;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.ITracingElement;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloOutlinePage;
import de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer;
import de.cau.cs.kieler.klighd.piccolo.viewer.PrintAction;
import de.cau.cs.kieler.klighd.ui.KlighdUIPlugin;
import de.cau.cs.kieler.klighd.ui.modifymodel.ModelModificationHandlerProvider;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PInputEvent;

/**
 * UI stuff such as the text input mechanism and registry of actions are extracted from the
 * PiccoloViewer to this class to optimize dependencies. PiccoloViewerUi instance will be generated
 * by the PiccoloViewerProvider.
 * 
 * @author ckru
 * @author chsch
 */
public class PiccoloViewerUI extends PiccoloViewer {

    /**
     * SWT text element that acts as an overlay for labels in some situations.
     */
    private Text textinput;

    /**
     * Listens to text inputs and applies changes accordingly.
     */
    private KlighdTextInputVerifyListener textinputlistener = new KlighdTextInputVerifyListener();

    /**
     * Creates a Piccolo2D viewer with default style.
     * 
     * @param parentViewer
     *            the parent {@link ContextViewer}
     * @param parent
     *            the parent composite
     */
    public PiccoloViewerUI(final ContextViewer parentViewer, final Composite parent) {
        this(parentViewer, parent, SWT.None);
    }

    /**
     * Creates a Piccolo2D viewer with given style.
     * 
     * @param parentViewer
     *            the parent {@link ContextViewer}
     * @param parent
     *            the parent composite
     * @param style
     *            the style attributes
     */
    public PiccoloViewerUI(final ContextViewer parentViewer, final Composite parent, final int style) {
        super(parentViewer, parent);

        // registers a print action by means of the global action bars
        final IActionBars actions;
        final IDiagramWorkbenchPart part = getViewContext().getDiagramWorkbenchPart();

        if (part instanceof IEditorPart) {
            actions = ((IEditorPart) part).getEditorSite().getActionBars();

        } else if (getViewContext().getDiagramWorkbenchPart() instanceof IViewPart) {
            actions = ((IViewPart) part).getViewSite().getActionBars();

        } else {
            actions = null;
        }

        // register print action
        if (actions != null) {
            final PiccoloViewer thisViewer = this;

            actions.setGlobalActionHandler(ActionFactory.PRINT.getId(), new Action() {
                private final PrintAction printer = new PrintAction(thisViewer);

                @Override
                public void run() {
                    printer.run();
                }
            });
        }

        this.getCanvas().getCamera().addInputEventListener(new KlighdTextInputHandler());

        addTextInput(parentViewer);
    }

    @Override
    protected PiccoloOutlinePage createDiagramOutlinePage() {
        return new PiccoloContentOutlinePage();
    }

    /**
     * Adds a text widget to the viewer that can be used to select and edit texts.
     * 
     * @param parentViewer
     *            the viewer to which to add the text widget
     */
    private void addTextInput(final ContextViewer parentViewer) {
        textinput = new Text(this.getCanvas(), SWT.MULTI);
        textinput.setDoubleClickEnabled(false);
        
        textinput.addListener(SWT.MouseUp, new Listener() {
            public void handleEvent(final Event event) {
                // textinput.setSize(textinput.getSize().x + 50, textinput.getSize().y);
                textinput.setEditable(true);
            }
        });
        
        textinput.addListener(SWT.MouseDown, new Listener() {
            public void handleEvent(final Event event) {
                if (event.count == 2) {
                    textinput.setSelection(0, textinput.getText().length());
                    
                    textinput.getAccessible().textSelectionChanged();
                    final Point selection = textinput.getSelection();
                    final Event e = new Event();
                    e.x = selection.x;
                    e.y = selection.y;
                    textinput.notifyListeners(SWT.Selection, e);
                }                
            }
        });

        // add a selection changed listener to the diagram viewer in order to deactivate
        //  the cursor selection text widget on diagram selections
        final ISelectionChangedListener selectionListener = new ISelectionChangedListener() {
            public void selectionChanged(final SelectionChangedEvent event) {
                textinputlistener.handleEvent(null);
                textinput.setEditable(false);
                textinput.setSelection(0, 0);
                textinput.setVisible(false);
            }
        };
        parentViewer.addSelectionChangedListener(selectionListener);

        textinput.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(final DisposeEvent e) {
                parentViewer.removeSelectionChangedListener(selectionListener);
            }
        });

        textinput.addKeyListener(new KeyListener() {

            public void keyReleased(final KeyEvent e) {

            }

            public void keyPressed(final KeyEvent e) {
                if (((e.stateMask & SWT.SHIFT) != 0) && ((char) e.keyCode == SWT.CR)) {
                    textinputlistener.handleEvent(null);
                    textinput.setEditable(false);
                    textinput.setVisible(false);
                }

            }
        });
        textinput.setEditable(false);

        // create a additional (context) menu manager, ...
        final MenuManager menu = new MenuManager();

        // ... install it on the text input control, and ...
        textinput.setMenu(menu.createContextMenu(textinput));
        
        this.getCanvas().getCamera().addPropertyChangeListener(PCamera.PROPERTY_VIEW_TRANSFORM,
                new PropertyChangeListener() {

                    public void propertyChange(final PropertyChangeEvent arg0) {
                        PiccoloViewerUI.this.updateWidgetBounds(null);
                    }
                });

        // ... and register it in the workbench part site, in order to let the work bench populate it!
        final IWorkbenchPart part = parentViewer.getViewContext().getDiagramWorkbenchPart();
        part.getSite().registerContextMenu(KlighdUIPlugin.FLOATING_TEXT_MENU_ID, menu,
                new ISelectionProvider() {

                    // Note that this selection provider is not registered in part site as such,
                    //  the selection provided by this method is, thus, not propagated into the
                    //  global selection.
                    // Instead, it is considered the 'activeMenuSelection'
                    //  (ISources#ACTIVE_MENU_SELECTION_NAME).
                    // Therefore, it cannot obtained, e.g., via HandlerUtil.getCurrentSelection(...),
                    //  but, e.g., via HandlerUtil.getActiveMenuSelection(...)!

                    public void setSelection(final ISelection selection) {
                    }

                    public void removeSelectionChangedListener(
                            final ISelectionChangedListener listener) {
                    }

                    public ISelection getSelection() {
                        return new StructuredSelection(textinput.getSelectionText());
                    }

                    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
                    }
                });
    }

    /**
     * Listens to committed text inputs.
     * 
     * @author ckru
     */
    private class KlighdTextInputVerifyListener implements Listener {

        /**
         * Node the committed text is linked to.
         */
        private KText node;

        /**
         * The element whose text is supposed to change.
         */
        private KGraphElement element;

        /**
         * Set node currently linked to the textinput.
         * 
         * @param n
         *            node currently linked to the textinput.
         */
        public void setNode(final KText n, final KGraphElement e) {
            node = n;
            element = e;
        }

        /**
         * {@inheritDoc}
         */
        public void handleEvent(final Event event) {
            if (node == null) {
                return;
            }

            if (textinput.getText().equals(node.getText())) {
                return;
            }

            final ViewContext viewContext = PiccoloViewerUI.this.getViewContext();
            final ISynthesis synth = viewContext.getDiagramSynthesis();
            final Function<String, Void> f = synth.getTextUpdateFunction(node, element);

            if (f == null) {
                return;
            }

            final IWorkbenchPart wPart = viewContext.getSourceWorkbenchPart();
            final IModelModificationHandler handler =
                    ModelModificationHandlerProvider.getInstance().getFittingHandler(wPart);
            try {
                if (handler != null) {
                    handler.execute(wPart, f, textinput.getText());
                } else {
                    f.apply(textinput.getText());
                }
            } catch (final Exception e) {
                final String msg =
                        "KLighD: An error occured while applying the updated string value in "
                                + viewContext.getDiagramWorkbenchPart().getPartId() + "!";
                StatusManager.getManager().handle(
                        new Status(IStatus.ERROR, KlighdUIPlugin.PLUGIN_ID, msg, e));
            }
            textinput.setEditable(false);
        }

    }

    /**
     * Handles mouse-over events on a text element and displays a text input dialog in that case.
     * 
     * @author ckru
     */
    private class KlighdTextInputHandler extends KlighdBasicInputEventHandler {

        /**
         * Constructor that just calls super.
         */
        public KlighdTextInputHandler() {
            super();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseMoved(final PInputEvent event) {
            updateTextInput(event);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseDragged(final PInputEvent event) {
            updateTextInput(event);
        }

        /**
         * Gets the element in the graph hierarchy thats linking the given PNode to the KGraph.
         * 
         * @param n
         *            the PNode whose text is about to be changed
         * @return the element thats linked to the KGraph.
         */
        private ITracingElement<?> getParentTracingElement(final PNode n) {
            final PNode parent = n.getParent();
            if (parent != null) {
                if (parent instanceof ITracingElement<?>) {
                    return (ITracingElement<?>) parent;
                } else {
                    return getParentTracingElement(parent);
                }
            } else {
                return null;
            }
        }

        /**
         * Sets position, style and text of the text input widget to the text element the mouse
         * currently hovers over.
         * 
         * @param event
         *            the event that triggered this update.
         */
        private void updateTextInput(final PInputEvent event) {
            final PNode n = event.getPickedNode();
            KText kText = null;
            final KlighdStyledText styledText;

            final KGraphElement element;
            if (n instanceof KlighdStyledText) {
                styledText = (KlighdStyledText) n;
                final Object o = this.getParentTracingElement(n).getGraphElement();
                if (o instanceof KGraphElement) {
                    element = (KGraphElement) o;
                } else {
                    element = null;
                }
                kText = styledText.getGraphElement();

            } else {
                element = null;
                styledText = null;
            }

            if ((kText == null || !kText.isCursorSelectable())
                    || (textinput.getSelectionCount() > 0)) {
                // set input widget invisible if mouse is not over a text element
                if (!textinput.getEditable() && !(textinput.getSelectionCount() > 0)) {
                    textinput.setVisible(false);
                }
                return;
            }
            final String text = styledText.getText();

            // determine text value
            if (text == null) {
                if (kText != null && kText.getText() != null) {
                    textinput.setText(kText.getText());
                } else {
                    return;
                }
            } else {
                textinput.setText(text);
            }

            updateWidgetBounds(styledText);

            // determine text color
            final Color oldColor = textinput.getForeground();
            final Color newColor = new Color(textinput.getDisplay(), styledText.getPenColor());
            textinput.setForeground(newColor);
            oldColor.dispose();

            // link this currently selected node to verify listener
            textinputlistener.setNode(kText, element);

            textinput.setVisible(true);
        }

    }

    private void updateWidgetBounds(final KlighdStyledText styledText) {
        KlighdStyledText theStyledText;
        if (styledText != null) {
            textinput.setData("STYLED_TEXT_FIGURE", styledText);
            theStyledText = styledText;
        } else {
            theStyledText = (KlighdStyledText) textinput.getData("STYLED_TEXT_FIGURE");
            if (theStyledText == null) {
                return;
            }
        }

        // determine global position of the text element
        final Rectangle2D bounds =
                NodeUtil.clipRelativeGlobalBoundsOf(theStyledText, PiccoloViewerUI.this.getCanvas()
                        .getCamera().getDisplayedINode());
        PiccoloViewerUI.this.getCanvas().getCamera().getViewTransformReference()
                .transform(bounds, bounds);
        textinput.setLocation((int) Math.round(bounds.getX()), (int) Math.round(bounds.getY()));

        // determine font data (i.e. font size)
        final FontData fd = new FontData(theStyledText.getFontData().toString());
        // fd.height = (float) (styledText.getFontData().getHeight() *
        // PiccoloViewerUI.this.getCanvas().getCamera().getViewScale());
        final float height =
                (float) (theStyledText.getFontData().getHeight() * PiccoloViewerUI.this.getCanvas()
                        .getCamera().getViewScale());
        // fd = FontData.win32_new(fd.data, height);
        fd.setHeight(Math.round(height));
        textinput.setFont(new Font(textinput.getDisplay(), fd));
        textinput.setSize(textinput.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    }

    /**
     * A subclass of the {@link PiccoloOutlinePage} that implements the required
     * {@link IContentOutlinePage} interface.<br>
     * <br>
     * Since that interface requires an additional UI dependency but luckily is simply a composition
     * of {@link org.eclipse.ui.part.IPage IPage} and
     * {@link org.eclipse.jface.viewers.ISelectionProvider ISelectionProvider}, and we do not
     * support any selection providing functionality, {@link PiccoloOutlinePage} only implements
     * {@link org.eclipse.ui.part.IPage IPage} for the sake of reducing dependencies. The required
     * (empty) {@link org.eclipse.jface.viewers.ISelectionProvider ISelectionProvider} methods are
     * than contributed by this sub class.
     * 
     * @author chsch
     */
    private static class PiccoloContentOutlinePage extends PiccoloOutlinePage implements
            IContentOutlinePage {

        /**
         * {@inheritDoc}
         */
        public void addSelectionChangedListener(final ISelectionChangedListener listener) {
            // selection is not supported by this outline page
        }

        /**
         * {@inheritDoc}
         */
        public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
            // selection is not supported by this outline page
        }

        /**
         * {@inheritDoc}
         */
        public ISelection getSelection() {
            // selection is not supported by this outline page
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public void setSelection(final ISelection selection) {
            // selection is not supported by this outline page
        }
    }
}
