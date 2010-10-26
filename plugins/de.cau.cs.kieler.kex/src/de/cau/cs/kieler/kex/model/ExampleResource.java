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
package de.cau.cs.kieler.kex.model;

public class ExampleResource {

    private final String localPath;

    private final Type resourceType;

    private boolean directOpen = false;

    public ExampleResource(final String localPath, final Type resourceType) {
        this.localPath = localPath;
        this.resourceType = resourceType;
    }

    public void setDirectOpen(final boolean directOpen) {
        this.directOpen = directOpen;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public Type getResourceType() {
        return this.resourceType;
    }

    public boolean isDirectOpen() {
        return this.directOpen;
    }

    public enum Type {
        FILE, FOLDER, PROJECT;

        public static String map(final Type type) {
            switch (type) {
            case FILE:
                return "file";
            case FOLDER:
                return "folder";
            case PROJECT:
                return "project";
            }
            return null;
        }
    }
}
