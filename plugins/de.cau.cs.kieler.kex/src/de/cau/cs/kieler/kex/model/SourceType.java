package de.cau.cs.kieler.kex.model;

public enum SourceType {

	KIELER, PUBLIC;

	public static String map(SourceType type) {
		switch (type) {
		case KIELER:
			return "Kieler";
		case PUBLIC:
			return "Public";
		}
		return null;
	}
}
