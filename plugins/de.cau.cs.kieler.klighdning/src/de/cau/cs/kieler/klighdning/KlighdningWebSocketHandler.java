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
package de.cau.cs.kieler.klighdning;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.websocket.WebSocket;

import com.google.common.collect.Maps;
import com.google.common.io.Files;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighdning.viewer.SVGBrowsingViewer;

/**
 * @author uru
 * 
 */
public class KlighdningWebSocketHandler implements WebSocket, WebSocket.OnTextMessage {

    // private Shell shell;
    private File docRoot;

    private boolean debug = false;

    // FIXME remove the statics
    // room mappings
    private static Map<String, SVGBrowsingViewer> roomViewerMap = Maps.newConcurrentMap();
    private static MultiMap<String> roomConnectionMap = new MultiMap<String>();

    // single browsing mapping
    private static Map<Connection, SVGBrowsingViewer> individualConnectionMap = Maps
            .newConcurrentMap();

    private String currentRoom = null;
    private Connection connection;
    
    private Checksum checksum = new Adler32();

    /**
     * Determines to which clients a message is broadcasted.
     */
    private static enum Broadcast {
        All, OnlyThis, AllButThis
    }

    /**
     * @param docRoot
     *            the root folder containing the models.
     */
    public KlighdningWebSocketHandler(final File docRoot) {
        this.docRoot = docRoot;
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    private void leaveIndividualConnections() {
        individualConnectionMap.remove(connection);
    }

    private void joinRoom(final String room) {

        // remove from the individual list
        leaveIndividualConnections();

        // join the new room
        currentRoom = room;

        // check if there exists a viewer for this room
        if (!roomViewerMap.containsKey(room)) {
            roomViewerMap.put(room, createViewer());
        }

        // add this connection to the room map
        roomConnectionMap.add(room, connection);
    }

    private void leaveCurrentRoom(final boolean addToIndividuals) {
        // remove this connection from the room
        roomConnectionMap.removeValue(currentRoom, connection);

        // if the room is empty delete the viewer as well
        if (roomConnectionMap.getValues(currentRoom) == null
                || roomConnectionMap.getValues(currentRoom).isEmpty()) {
            roomViewerMap.remove(currentRoom);
        }

        currentRoom = null;

        // add this connection to the individual ones
        if (addToIndividuals) {
            individualConnectionMap.put(connection, createViewer());
        }
    }

    private SVGBrowsingViewer getCurrentViewer() {
        if (currentRoom != null) {
            return roomViewerMap.get(currentRoom);
        } else {
            return individualConnectionMap.get(connection);
        }
    }

    @SuppressWarnings("unused")
    private void layoutBroadcastSVG() {
        layoutBroadcastSVG(Broadcast.All, false);
    }

    /**
     * Make sure to call this method from the display thread!
     * 
     * @param onlyThis
     *            whether the svg is broadcasted to all matching connections or just this one.
     */
    private void layoutBroadcastSVG(final Broadcast broadcastType, final boolean zoomToFit) {

        // get viewer and layout
        SVGBrowsingViewer viewer = getCurrentViewer();

        // get the new SVG
        String svg = SVGLayoutProvider.getInstance().layout(viewer, zoomToFit);

        // send the new svg to all corresponding connections
        broadcastJson(broadcastType, "type", "SVG", "data", svg);
    }

    private void broadcastJson(final String... pairs) {
        broadcastJson(Broadcast.All, pairs);
    }

    private void broadcastJson(final Broadcast broadcastType, final String... pairs) {
        // transform args to a map
        Map<String, String> jsonMap = Maps.newHashMap();
        for (int i = 0; i < pairs.length; i += 2) {
            jsonMap.put(pairs[i], pairs[i + 1]);
        }

        // check if a type exists
        if (!jsonMap.containsKey("type")) {
            throw new IllegalArgumentException("The json object requires a 'type' field.");
        }

        String json = JSON.toString(jsonMap);
        broadcastJson(json, broadcastType);
    }

    private void broadcastJson(final String json, final Broadcast broadcastType) {
        try {

            if (broadcastType != Broadcast.OnlyThis && currentRoom != null) {
                @SuppressWarnings("unchecked")
                List<Connection> cons = roomConnectionMap.getValues(currentRoom);

                // send svg to every connection
                for (Connection c : cons) {
                    if (c != connection || broadcastType != Broadcast.AllButThis) {
                        c.sendMessage(json);
                    }
                }
            } else {
                if (broadcastType != Broadcast.AllButThis) {
                    connection.sendMessage(json);
                }
            }
        } catch (IOException e) {
            // TODO check why on F5 no close signal is send, remove the connection here
            e.printStackTrace();
        }
    }

    private void broadcastPermaLink() {
        // add perma link
        String perma = "/resource" + getCurrentViewer().assemblePermaLink();
        // System.out.println("PERMA LINK: " + perma);

        broadcastJson("type", "PERMALINK", "perma", perma);
    }

    private void sendError(final String error) {
        // assemble json
        Map<String, String> jsonMap = Maps.newHashMap();
        jsonMap.put("type", "ERROR");
        jsonMap.put("data", error);

        String json = JSON.toString(jsonMap);

        try {
            connection.sendMessage(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SVGBrowsingViewer createViewer() {
        return new SVGBrowsingViewer();
    }

    /**
     * {@inheritDoc}
     */
    public void onOpen(final Connection theConnection) {

        if (debug) {
            System.err.printf("%s#onOpen %s\n", this.getClass().getSimpleName(), theConnection);
        }
        this.connection = theConnection;

        // initially add to the individual list
        individualConnectionMap.put(theConnection, createViewer());
    }

    /**
     * {@inheritDoc}
     */
    public void onMessage(final String data) {
        if (debug) {
            System.err.printf("%s#onMessage     %s\n", this.getClass().getSimpleName(), data);
        }

        try {
            // we expect json
            @SuppressWarnings("unchecked")
            Map<String, Object> json = (Map<String, Object>) JSON.parse(data);
            String type = (String) json.get("type");

            if (type.equals("JOIN")) {
                /*
                 * JOIN -------------------------------------------------------------------
                 */
                // if already in a room
                if (currentRoom != null) {
                    throw new IllegalStateException("Already in a room, cannot join another one.");
                }

                // add to the room
                String room = (String) json.get("room");
                joinRoom(room);

                // send current svg
                layoutBroadcastSVG(Broadcast.OnlyThis, false);

                System.out.println(connection);

            } else if (type.equals("LEAVE")) {
                /*
                 * LEAVE -------------------------------------------------------------------
                 */
                leaveCurrentRoom(true);
                System.out.println(connection);

            } else if (type.equals("RESOURCE")) {
                /*
                 * RESOURCE -------------------------------------------------------------------
                 */
                final String path = (String) json.get("path");
                final String viewport = (String) json.get("viewport");
                final String expand = (String) json.get("expand");

                ResourceSet rs = new ResourceSetImpl();

                // MOML
                Map<String, Boolean> parserFeatures = Maps.newHashMap();
                parserFeatures.put("http://xml.org/sax/features/validation", Boolean.FALSE);
                parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
                        Boolean.FALSE);
                parserFeatures.put(
                        "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                        Boolean.FALSE);

                rs.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
                rs.getLoadOptions().put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
                // rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
                // .put("xml", new MomlResourceFactoryImpl());

                File file = new File(docRoot, path);
                final Resource r =
                        rs.getResource(
                                URI.createFileURI(file.getAbsolutePath()), true);

                System.out.println("Loading resource (WS): " + r);

                SVGBrowsingViewer viewer = getCurrentViewer();
                viewer.setSvgTransform(null);
                
                // translate and set the model
                try {
                    KNode currentModel =
                            LightDiagramServices.translateModel(r.getContents().get(0), null);
                    viewer.setModel(currentModel, true);
                    viewer.setResourcePath(path);
                    viewer.setResourceChecksum(Files.getChecksum(file, checksum) + "");
                    
                    // if we have initial permalink information, apply them!
                    viewer.applyPermalink(expand, viewport);

                    layoutBroadcastSVG(Broadcast.All, true);
                                        
                    broadcastPermaLink();

                } catch (Exception e) {
                    // tell the user!
                    sendError("ERROR: " + e.getLocalizedMessage());
                }

            } else if (type.equals("EXPAND")) {
                /*
                 * EXPAND -------------------------------------------------------------------
                 */
                final String id = (String) json.get("id");
                // expand and layout
                getCurrentViewer().toggleExpansion(id);

                layoutBroadcastSVG(Broadcast.All, false);
                broadcastPermaLink();

            } else if (type.equals("TRANSFORM")) {
                /*
                 * TRANSLATE -------------------------------------------------------------------
                 */
                final String transform = (String) json.get("transform");
                getCurrentViewer().setSvgTransform(transform);

                broadcastJson(Broadcast.AllButThis, "type", "TRANSFORM", "transform", transform);
                broadcastPermaLink();
            }

        } catch (Exception e) {
            e.printStackTrace();
            sendError("ERROR: " + e.getLocalizedMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void onClose(final int code, final String message) {
        if (debug) {
            System.err.printf("%s#onDisonnect %d %s\n", this.getClass().getSimpleName(), code,
                    message);
        }

        // remove either from the room list, or from individual list
        if (currentRoom != null) {
            leaveCurrentRoom(false);
        } else {
            leaveIndividualConnections();
        }
    }

}
