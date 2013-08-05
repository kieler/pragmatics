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
package de.cau.cs.kieler.klighd.piccolo.internal.controller;

import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KCustomRendering;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KImage;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingRef;
import de.cau.cs.kieler.core.krendering.KRoundedBendsPolyline;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KSpline;
import de.cau.cs.kieler.core.krendering.KStyle;
import de.cau.cs.kieler.core.krendering.KText;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.krendering.KCustomRenderingWrapperFactory;
import de.cau.cs.kieler.klighd.microlayout.Bounds;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KCustomConnectionFigureNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KEdgeNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdImage;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdPath;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KlighdStyledText;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PAlignmentNode;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PAlignmentNode.HAlignment;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PAlignmentNode.VAlignment;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.PEmptyNode;
import de.cau.cs.kieler.klighd.piccolo.internal.util.NodeUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.util.PiccoloPlacementUtil;
import de.cau.cs.kieler.klighd.piccolo.internal.util.Styles;
import de.cau.cs.kieler.klighd.util.KlighdProperties;
import edu.umd.cs.piccolo.PNode;

/**
 * Collection of KRendering-related figure creation methods.<br>
 * These methods are outsourced from {@link AbstractKGERenderingController} in order
 * to reduce the size of that class.
 * 
 * @author chsch
 */
final class KGERenderingControllerHelper {

    /**
     * Standard constructor.
     */
    private KGERenderingControllerHelper() {
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KEllipse}.
     * 
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param ellipse
     *            the ellipse rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createEllipse(
            final AbstractKGERenderingController<?, ?> controller, final KEllipse ellipse,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {

        final KlighdPath path = KlighdPath.createEllipse(0, 0, initialBounds.getWidth(),
                initialBounds.getHeight());
        controller.initializeRenderingNode(path);
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (ellipse.getChildren().size() > 0) {
            controller.handleChildren(ellipse.getChildren(), ellipse.getChildPlacement(),
                    propagatedStyles, path);
        }

        // return a controller for the ellipse
        return new KlighdPathController(path) {
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setPathToEllipse(0, 0, bounds.getWidth(), bounds.getHeight());
                NodeUtil.applyTranslation(getNode(), bounds.getX(), bounds.getY());
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KRectangle}.
     * 
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param rect
     *            the rectangle rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createRectangle(
            final AbstractKGERenderingController<?, ?> controller, final KRectangle rect,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {
        // create the rectangle
        final KlighdPath path = KlighdPath.createRectangle(0, 0, initialBounds.getWidth(),
                initialBounds.getHeight());
        controller.initializeRenderingNode(path);
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (rect.getChildren().size() > 0) {
            controller.handleChildren(rect.getChildren(), rect.getChildPlacement(),
                    propagatedStyles, path);
        }

        // create a controller for the rectangle and return it
        return new KlighdPathController(path) {
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setPathToRectangle(0, 0, bounds.getWidth(), bounds.getHeight());
                NodeUtil.applyTranslation(getNode(), bounds.getX(), bounds.getY());
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KRoundedRectangle}.
     * 
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param rect
     *            the rounded rectangle rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createRoundedRectangle(
            final AbstractKGERenderingController<?, ?> controller, final KRoundedRectangle rect,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {
        final float cornerWidth = 2 * rect.getCornerWidth();
        final float cornerHeight = 2 * rect.getCornerHeight();
        
        // create the rounded rectangle
        final KlighdPath path = KlighdPath.createRoundRectangle(0, 0, initialBounds.getWidth(),
                initialBounds.getHeight(), cornerWidth, cornerHeight);
        controller.initializeRenderingNode(path);
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (rect.getChildren().size() > 0) {
            controller.handleChildren(rect.getChildren(), rect.getChildPlacement(),
                    propagatedStyles, path);
        }

        // create a controller for the rounded rectangle and return it
        return new KlighdPathController(path) {
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setPathToRoundRectangle(0, 0, bounds.getWidth(), bounds.getHeight(),
                        cornerWidth, cornerHeight);
                NodeUtil.applyTranslation(getNode(), bounds.getX(), bounds.getY());
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KArc}.
     * 
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param arc
     *            the arc rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createArc(
            final AbstractKGERenderingController<?, ?> controller, final KArc arc,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {

        // create the arc
        final KlighdPath path = KlighdPath.createArc(0, 0, initialBounds.getWidth(), initialBounds
                .getHeight(), arc.getStartAngle(), arc.getArcAngle(), arc.getArcType().getValue());

        path.setPaint((RGB) null);
        controller.initializeRenderingNode(path);
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (arc.getChildren().size() > 0) {
            controller.handleChildren(arc.getChildren(), arc.getChildPlacement(), propagatedStyles,
                    path);
        }

        // create a controller for the rounded rectangle and return it
        return new KlighdPathController(path) {
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setPathToArc(0, 0, bounds.getWidth(), bounds.getHeight(),
                        arc.getStartAngle(), arc.getArcAngle(), arc.getArcType().getValue());
                NodeUtil.applyTranslation(getNode(), bounds.getX(), bounds.getY());
            }
        };
    }

    /**
     * Creates a {@code PSWTText} representation for the {@code KText}.
     * 
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param text
     *            the text rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdStyledText> createText(
            final AbstractKGERenderingController<?, ?> controller, final KText text,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {
        // create the text
        KlighdStyledText textNode = new KlighdStyledText(text);
        controller.initializeRenderingNode(textNode);

        // supplement (chsch)
        Boolean b = text.getProperty(KlighdProperties.KLIGHD_SELECTION_UNPICKABLE);
        textNode.setPickable(b != null && b.equals(Boolean.TRUE) ? false : true);

        // create the alignment node wrapping the text
        final PAlignmentNode alignmentNode = new PAlignmentNode();
        controller.initializeRenderingNode(alignmentNode);
        alignmentNode.translate(initialBounds.getX(), initialBounds.getY());
        alignmentNode.setBounds(0, 0, initialBounds.getWidth(), initialBounds.getHeight());
        alignmentNode.addChild(textNode);
        alignmentNode.setHorizontalAlignment(textNode, HAlignment.CENTER);
        alignmentNode.setVerticalAlignment(textNode, VAlignment.CENTER);
        parent.addChild(alignmentNode);

        // create a controller for the text and return it
        return new KlighdTextController(textNode) {
            public void setBounds(final Bounds bounds) {
                NodeUtil.applySmartBounds(alignmentNode, bounds);
            }

            public void setHorizontalAlignment(final HAlignment alignment) {
                alignmentNode.setHorizontalAlignment(getNode(), alignment);
            }

            public void setVerticalAlignment(final VAlignment alignment) {
                alignmentNode.setVerticalAlignment(getNode(), alignment);
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KPolyline} or
     * {@code KSpline}.
     * 
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param line
     *            the polyline or spline rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createLine(
            final AbstractKGERenderingController<?, ?> controller, final KPolyline line,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {

        Point2D[] points = PiccoloPlacementUtil.evaluatePolylinePlacement(line, initialBounds);

        final KlighdPath path;
        if (line instanceof KSpline) {
            // create the spline
            path = KlighdPath.createSpline(points);
        } else if (line instanceof KRoundedBendsPolyline) {
            // create the rounded bends polyline
            path = KlighdPath.createRoundedBendPolyline(points,
                    ((KRoundedBendsPolyline) line).getBendRadius());
        } else {
            // create the polyline
            path = KlighdPath.createPolyline(points);
        }

        controller.initializeRenderingNode(path);
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (line.getChildren().size() > 0) {
            List<KRendering> restChildren = Lists.newLinkedList();
            for (final KRendering rendering : line.getChildren()) {
                if (PiccoloPlacementUtil.getDecoratorPlacementData(rendering) != null)
                {
                    controller.handleDecoratorPlacementRendering(rendering, propagatedStyles, path);
                } else {
                    restChildren.add(rendering);
                }
            }

            // handle children without decorator placement data if any
            if (restChildren.size() > 0) {
                // create a proxy parent for the children without decorator placement data
                final PNode proxyParent = new PEmptyNode();
                path.addChild(proxyParent);
                NodeUtil.applySmartBounds(proxyParent, path.getBoundsReference());
                controller.addListener(PNode.PROPERTY_BOUNDS, path, proxyParent,
                        new PropertyChangeListener() {
                            public void propertyChange(final PropertyChangeEvent arg0) {
                                NodeUtil.applySmartBounds(proxyParent, path.getBoundsReference());
                            }
                        });

                controller.handleChildren(restChildren, line.getChildPlacement(), propagatedStyles,
                        proxyParent);
            }
        }

        // create a controller for the polyline and return it
        return new KlighdPathController(path) {
            public void setBounds(final Bounds bounds) {
                // apply the bounds

                Point2D[] points = PiccoloPlacementUtil.evaluatePolylinePlacement(line, bounds);

                if (line instanceof KSpline) {
                    // update spline
                    getNode().setPathToSpline(points);
                } else if (line instanceof KRoundedBendsPolyline) {
                    // update rounded bend polyline
                    getNode().setPathToRoundedBendPolyline(points,
                            ((KRoundedBendsPolyline) line).getBendRadius());
                } else {
                    // update polyline
                    getNode().setPathToPolyline(points);
                }

                NodeUtil.applyTranslation(getNode(), bounds.getX(), bounds.getY());
            }
        };
    }

    /**
     * Creates a {@code PSWTAdvancedPath} representation for the {@code KPolygon}.
     * 
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param polygon
     *            the polygon rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<KlighdPath> createPolygon(
            final AbstractKGERenderingController<?, ?> controller, final KPolygon polygon,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {
        // create the polygon
        final KlighdPath path = KlighdPath.createPolygon(PiccoloPlacementUtil
                .evaluatePolylinePlacement(polygon, initialBounds));
        controller.initializeRenderingNode(path);
        path.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(path);

        // handle children
        if (polygon.getChildren().size() > 0) {
            List<KRendering> restChildren = Lists.newLinkedList();
            for (final KRendering rendering : polygon.getChildren()) {
                if (PiccoloPlacementUtil.getDecoratorPlacementData(rendering) != null) {
                    controller.handleDecoratorPlacementRendering(rendering, propagatedStyles, path);
                } else {
                    restChildren.add(rendering);
                }
            }

            // handle children without decorator placement data if any
            if (restChildren.size() > 0) {
                // chsch: Why is that proxy node needed. Don't see the point... 
                // 
                // create a proxy parent for the children without decorator placement data
                // final PNode proxyParent = new PEmptyNode();
                // path.addChild(proxyParent);
                // NodeUtil.applySmartBounds(proxyParent, path.getBoundsReference());
                // controller.addListener(PNode.PROPERTY_BOUNDS, path, proxyParent,
                //         new PropertyChangeListener() {
                //             public void propertyChange(final PropertyChangeEvent arg0) {
                //                 NodeUtil.applySmartBounds(proxyParent, path.getBoundsReference());
                //             }
                //         });

                controller.handleChildren(restChildren, polygon.getChildPlacement(), propagatedStyles,
                        path); //proxyParent);
            }
        }

        // create a controller for the polyline and return it
        return new KlighdPathController(path) {
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setPathToPolygon(
                        (PiccoloPlacementUtil.evaluatePolylinePlacement(polygon, bounds)));
                NodeUtil.applyTranslation(getNode(), bounds.getX(), bounds.getY());
            }
        };
    }

    /**
     * Creates a representation for the {@code KRenderingRef}.
     * 
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param renderingReference
     *            the rendering reference
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<?> createRenderingReference(
            final AbstractKGERenderingController<?, ?> controller,
            final KRenderingRef renderingReference, final Styles styles,
            final List<KStyle> propagatedStyles, final PNode parent, final Bounds initialBounds) {

        KRendering rendering = renderingReference.getRendering();
        if (rendering == null) {
            // create a dummy node
            return createDummy(parent, initialBounds);
        }

        List<KStyle> renderingStyles = renderingReference.getStyles();

        // determine the styles for propagation to child nodes
        final List<KStyle> childPropagatedStyles = controller.determinePropagationStyles(
                renderingStyles, propagatedStyles);

        // dispatch the rendering
        final PNodeController<?> pnodeController = controller.createRendering(rendering,
                childPropagatedStyles, parent, initialBounds, styles);

        // remember the KRendering-controller pair in the controller's 'pnodeControllers' map 
        controller.addPNodeController(rendering, pnodeController);
        
        // determine the styles for this rendering
        styles.deriveStyles(childPropagatedStyles);
        
        // set the styles for the created rendering node using the controller
        pnodeController.applyChanges(styles);

        // return a controller for the reference which sets the bounds of the referenced node
        return new PNodeController<PNode>(pnodeController.getNode()) {

            public void applyChanges(final Styles styles) {
                pnodeController.applyChanges(styles);
            }
            
            public void setBounds(final Bounds bounds) {
                pnodeController.setBounds(bounds);
            }
        };
    }
    
    private static final Map<String, ImageData> IMAGE_BUFFER = Maps.newHashMap();

    /**
     * Creates a representation for the {@link KImage}.
     * 
     * @param controller
     *            the {@link AbstractKGERenderingController} that is delegated to in this method (and
     *            should be the caller of this method)
     * @param image
     *            the image rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<?> createImage(final AbstractKGERenderingController<?, ?> controller,
            final KImage image, final List<KStyle> propagatedStyles, final PNode parent,
            final Bounds initialBounds) {

        final KlighdImage imageNode;

        if (image.getImageObject() instanceof Image) {
            imageNode = new KlighdImage((Image) image.getImageObject());

        } else if (image.getImageObject() instanceof ImageData) {
            imageNode = new KlighdImage((ImageData) image.getImageObject());

        } else {
            
            final String id = image.getBundleName() + "#" + image.getImagePath();
            ImageData imageData = IMAGE_BUFFER.get(id);
            
            if (imageData == null) {
                Bundle bundle = null;
                if (image.getBundleName() != null) {
                    // determine the containing bundle,
                    //  trim potentially leading and trailing quotation marks
                    bundle = Platform.getBundle(image.getBundleName().replace("\"", ""));
                }
                if (bundle == null) {
                    return createDummy(parent, initialBounds);
                }
                
                // get the bundle and actual image,
                //  trim potentially leading and trailing quotation marks
                final URL entry = bundle.getEntry(image.getImagePath().replace("\"", ""));
                try {
                    imageData = new ImageData(entry.openStream());
                    IMAGE_BUFFER.put(id, imageData);
                } catch (Exception e) {
                    final String msg = "KLighD: Error occurred while loading the image "
                            + image.getImagePath() + " in bundle " + image.getBundleName();
                    StatusManager.getManager().handle(
                            new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg, e),
                            StatusManager.LOG);
                    return createDummy(parent, initialBounds);
                }
            }

            // create the image, the bounds of imageNode are set within the KlighdImage implementation
            imageNode = new KlighdImage(imageData);
        }

        // initialize the node
        controller.initializeRenderingNode(imageNode);
        imageNode.translate(initialBounds.getX(), initialBounds.getY());
        imageNode.setBounds(0, 0, initialBounds.getWidth(), initialBounds.getHeight());
        parent.addChild(imageNode);

        // handle children
        if (image.getChildren().size() > 0) {
            controller.handleChildren(image.getChildren(), image.getChildPlacement(),
                    propagatedStyles, imageNode);
        }

        // create a standard default node controller
        return new PNodeController<PNode>(imageNode) {
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                NodeUtil.applySmartBounds(getNode(), bounds);
            }
        };
    }

    /**
     * Creates a representation for the {@code KCustomRendering}.
     * 
     * @param customRendering
     *            the custom rendering
     * @param styles
     *            the styles container for the rendering
     * @param propagatedStyles
     *            the styles propagated to the rendering's children
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<?> createCustomRendering(
            final AbstractKGERenderingController<?, ?> controller,
            final KCustomRendering customRendering, final List<KStyle> propagatedStyles,
            final PNode parent, final Bounds initialBounds) {

        // get a wrapping PNode containing the actual figure
        // by means of the KCustomRenderingWrapperFactory
        PNode node;
        if (customRendering.getFigureObject() != null) {
            if (parent instanceof KEdgeNode) {
                node = KCustomRenderingWrapperFactory.getInstance().getWrapperInstance(
                        customRendering.getFigureObject(), KCustomConnectionFigureNode.class);
            } else {
                node = KCustomRenderingWrapperFactory.getInstance().getWrapperInstance(
                        customRendering.getFigureObject(), PNode.class);
            }

        } else {
            if (parent instanceof KEdgeNode) {
                node = KCustomRenderingWrapperFactory.getInstance().getWrapperInstance(
                        customRendering.getBundleName(), customRendering.getClassName(),
                        KCustomConnectionFigureNode.class);
            } else {
                node = KCustomRenderingWrapperFactory.getInstance().getWrapperInstance(
                        customRendering.getBundleName(), customRendering.getClassName(),
                        PNode.class);
            }
        }
        if (node == null) {
            return createDummy(parent, initialBounds);
        }
        // initialize the bounds of the node
        node.setBounds(0, 0, initialBounds.getWidth(), initialBounds.getHeight());

        // initialize the node
        controller.initializeRenderingNode(node);
        node.translate(initialBounds.getX(), initialBounds.getY());
        parent.addChild(node);

        // handle children
        if (customRendering.getChildren().size() > 0) {
            controller.handleChildren(customRendering.getChildren(),
                    customRendering.getChildPlacement(), propagatedStyles, node);
        }

        // create a standard default node controller
        return new PNodeController<PNode>(node) {
            public void setBounds(final Bounds bounds) {
                // apply the bounds
                getNode().setBounds(0, 0, bounds.getWidth(), bounds.getHeight());
                NodeUtil.applyTranslation(getNode(), bounds.getX(), bounds.getY());
            }
        };
    }

    /**
     * Creates a dummy node.
     * 
     * @param parent
     *            the parent Piccolo node
     * @param initialBounds
     *            the initial bounds
     * @return the controller for the created Piccolo node
     */
    static PNodeController<?> createDummy(final PNode parent, final Bounds initialBounds) {
        final PNode dummyChild = new PEmptyNode();
        NodeUtil.applySmartBounds(dummyChild, initialBounds);
        parent.addChild(dummyChild);
        return new PNodeController<PNode>(dummyChild) {
            public void setBounds(final Bounds bounds) {
                NodeUtil.applySmartBounds(dummyChild, bounds);
            }
        };
    }
}