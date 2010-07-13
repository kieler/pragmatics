/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.graphs.ksbase.copypaste;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Utility class for cut, copy and paste. The main purpose is to provide funcitionality to
 * work with the clipboard.
 * 
 * @author soh (synccharts version)
 * @author mri
 */
public class Utils {

    /** The clipboard where the copied objects are stored. */
    private static Object clipBoard = null;

    /**
     * 
     */
    private Utils() {
        // does nothing
    }

    /**
     * Clone an eObject with the method from EcoreUtil.
     * 
     * @param object
     *            the eObject to clone
     * @return the clone
     */
    public static Object ecoreCopy(final Object object) {
        return EcoreUtil.copy((EObject) object);
    }

    /**
     * Get the systems native clipboard.
     * 
     * @return the clipboard
     */
    private static Clipboard getSystemClipboard() {
        Display display = PlatformUI.getWorkbench().getDisplay();
        return new Clipboard(display);
    }

    /**
     * Get the contents of the system clipboard and try to parse EObjects from it.
     * 
     * FIXME: not working yet. integrate into loading process
     * 
     * @return the objects parsed from it
     */
    private static Object getObjectFromSystemClipboard() {
        Clipboard systemClipboard = getSystemClipboard();
        TextTransfer textTransfer = TextTransfer.getInstance();
        Object obj = systemClipboard.getContents(textTransfer);
        if (obj != null && obj instanceof String) {
            String root = (String) obj;
            XMIResource xmiResource = new XMIResourceImpl();
            xmiResource.setURI(URI.createURI("dummy"));
            // XMIHelperImpl xmlHelper = new XMIHelperImpl(xmiResource);
            ByteArrayInputStream bais =
                    new ByteArrayInputStream(root.getBytes());

            Map<Object, Object> defaultLoadOptions =
                    xmiResource.getDefaultLoadOptions();
            defaultLoadOptions.put(XMLResource.OPTION_RESOURCE_HANDLER,
                    new BasicResourceHandler());

            try {
                xmiResource.load(bais, defaultLoadOptions);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            EList<EObject> results = xmiResource.getContents();

            if (results != null && !results.isEmpty()) {
                if (results.size() == 1) {
                    return results.get(0);
                }
                return EcoreUtil.copyAll(results);
            }
            return null;
        }
        return null;
    }

    /**
     * Get an object from the clipboard. This involves preparing the object so it can be
     * inserted right away.
     * 
     * @return an EObject or a list of eObjects
     */
    @SuppressWarnings("unchecked")
    public static Object getObjectFromClipboard() {
        getObjectFromSystemClipboard();
        if (clipBoard instanceof EObject) {
            Object copy = EcoreUtil.copy((EObject) clipBoard);
            if (copy instanceof KNode) {
                cloneKEdges((KNode) clipBoard, (KNode) copy);
            }
            return copy;
        } else if (clipBoard instanceof Collection<?>) {
            Collection<?> copy =
                    EcoreUtil
                            .copyAll((Collection<? extends EObject>) clipBoard);
            if (!copy.isEmpty()) {
                Object head = copy.iterator().next();
                if (head instanceof KNode) {
                    return getKNodesFromClipboard(
                            (Collection<KNode>) clipBoard,
                            (Collection<KNode>) copy);
                }
                if (head instanceof KEdge) {
                    return getKEdgesFromClipboard(
                            (Collection<KEdge>) clipBoard,
                            (Collection<KEdge>) copy);
                }
            }
            return null;
        }
        return null;
    }

    /**
     * Copy an object to clipboard.
     * 
     * @param object
     *            the object
     * @return the contents of the clipboard
     */
    public static Object objectToClipboard(final Object object) {
        resetClipboard();
        if (object instanceof EObject) {
            EObject o = EcoreUtil.copy((EObject) object);
            clipBoard = o;
        } else if (object instanceof List<?>) {
            List<?> list = (List<?>) object;
            clipBoard = EcoreUtil.copyAll(list);
        }
        objectToSystemClipboard(object);
        return clipBoard;
    }

    /**
     * Experimental method for writing the object or list of objects to the system
     * clipboard.
     * 
     * TODO: use this to implement copy and paste through the system to allow copying
     * between applications.
     * 
     * @param object
     *            the object that should be copied to clipboard
     */
    private static void objectToSystemClipboard(final Object object) {
        ArrayList<EObject> arrayList = new ArrayList<EObject>();
        if (object instanceof EObject) {
            arrayList.add((EObject) object);
        } else if (object instanceof List<?>) {
            for (Object o : (List<?>) object) {
                arrayList.add((EObject) o);
            }
        } else {
            return;
        }
        String returnStr = null;
        XMIResource xmiResource = new XMIResourceImpl();
        XMIHelperImpl xmiHelper = new XMIHelperImpl(xmiResource);
        try {
            returnStr =
                    XMIHelperImpl.saveString(new HashMap<Object, Object>(),
                            arrayList, "UTF-8", xmiHelper);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } catch (Exception e0) {
            e0.printStackTrace();
        }

        Clipboard systemClipboard = getSystemClipboard();
        TextTransfer textTransfer = TextTransfer.getInstance();
        systemClipboard.setContents(new Object[] { returnStr },
                new TextTransfer[] { textTransfer });
    }

    /**
     * Clears the clipboard.
     */
    private static void resetClipboard() {
        clipBoard = null;
    }

    /**
     * Check if there is any object inside the clipboard.
     * 
     * @return true if the clipboard is empty
     */
    public static boolean isClipboardEmpty() {
        return clipBoard == null;
    }

    /**
     * Clone all edges on a node.
     * 
     * @param source
     *            the source
     * @param target
     *            the clone
     */
    private static void cloneKEdges(final KNode source, final KNode target) {
        EList<KEdge> sourceEdges = source.getOutgoingEdges();
        EList<KEdge> targetEdges = target.getOutgoingEdges();
        targetEdges.clear();

        for (KEdge edge : sourceEdges) {
            if (edge.getTarget() == source) {
                KEdge clone = EcoreUtil.copy(edge);
                targetEdges.add(clone);
                clone.setTarget(target);
            }
        }
    }

    /**
     * Prepare a list of KNodes to be ready for insertion using xtend.
     * 
     * @param KNodesClipBoard
     *            the raw list of KNodes
     * @param copy
     *            the copy of the list
     * @return the KNodes the formatted list
     */
    public static List<KNode> getKNodesFromClipboard(
            final Collection<KNode> KNodesClipBoard,
            final Collection<KNode> copy) {
        if (!KNodesClipBoard.isEmpty()) {
            List<KNode> dummy = new LinkedList<KNode>();
            for (KNode KNode : copy) {
                dummy.add(KNode);

                // remove KEdges that leave the current selection
                Iterator<KEdge> iter = KNode.getOutgoingEdges().iterator();
                while (iter.hasNext()) {
                    KEdge trans = iter.next();
                    if (!copy.contains(trans.getTarget())) {
                        iter.remove();
                    }
                }
            }
            return dummy;
        }
        return null;
    }

    /**
     * Prepare a list of KEdges to be ready for insertion using xtend.
     * 
     * @param KEdgesClipBoard
     *            the raw list of KEdges
     * @param copy
     *            the copy of the list
     * @return the KNodes the formatted list
     */
    public static List<KEdge> getKEdgesFromClipboard(
            final Collection<KEdge> edgesClipBoard, final Collection<KEdge> copy) {
        if (!edgesClipBoard.isEmpty()) {
            List<KEdge> dummy = new LinkedList<KEdge>();
            for (KEdge KEdge : copy) {
                dummy.add(KEdge);
            }
            return dummy;
        }
        return null;
    }

    /**
     * Copy the object.
     * 
     * @param object
     *            the object
     * @return the copy
     */
    public static EObject copy(final Object object) {
        return EcoreUtil.copy((EObject) object);
    }

    /**
     * Debug output for xtend code.
     * 
     * @param object
     *            the message
     */
    public static void debug(final Object object) {
        System.out.println("COPY AND PASTE DEBUG: " + object.toString());
    }

    /**
     * 
     * @param aString
     *            the string to print
     */
    public static void dump(final String aString) {
        System.out.println(aString);
    }

    /**
     * Pseudo-method, allows setting breakpoints for analysing objects.
     * 
     * @param object
     *            the object
     * @return the object passed as param, casted to EObject
     */
    public static EObject analyze(final Object object) {
        return (EObject) object;
    }
}
