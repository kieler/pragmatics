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
		File, Folder, Project;

		public static String map(Type type) {
			switch (type) {
			case File:
				return "file";
			case Folder:
				return "folder";
			case Project:
				return "project";
			}
			return null;
		}
	}

}
