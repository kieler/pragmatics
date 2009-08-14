package de.cau.cs.kieler.ksbase.transformations;

import java.net.URI;

public class Transformation {

	private String name; //Menu entry name
	private String transformation; //Xtend command string
	private int showIn; //Visibility configuration (TODO Enum vals)
	private int numSelections; //Number of selections;
	private URI iconURI; //URI to icon
	
	/**
	 * Creates a new Transformation
	 */
	public Transformation() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTransformation(String value) {
		this.transformation = value;
	}
	
	public void setShow(int value) {
		this.showIn = value;
	}
	
	public void setNumSelections(int value) {
		this.numSelections = value;
	}
	
	public void setIconURI(URI uri) {
		this.iconURI = uri;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getTransformation() {
		return this.transformation;
	}
	
	public int getShowIn() {
		return this.showIn;
	}
	
	public int getNumSelectiosn() {
		return this.numSelections;
	}
	
	public URI getIconURI() {
		return this.iconURI;
	}
}
