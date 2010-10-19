package de.cau.cs.kieler.kex.model;

public class ExampleResource {

    private final String localPath;

    private final Type resourceType;

    private boolean directOpen = false;

    public ExampleResource(String localPath, Type resourceType) {
        this.localPath = localPath;
        this.resourceType = resourceType;
    }

    public void setDirectOpen(boolean directOpen) {
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

        public static String map(Type type) {
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
