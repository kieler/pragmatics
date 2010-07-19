package de.cau.cs.kieler.kex.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExampleResource {

	private List<URL> resources;
	private boolean isHeadResource;
	private String category;

	public ExampleResource() {
		setResources(new ArrayList<URL>());
	}

	public void setResources(List<URL> resources) {
		this.resources = resources;
	}

	public void addResource(URL resourceURL) {
		this.resources.add(resourceURL);
	}

	public List<URL> getResources() {
		return this.resources;
	}

	public void setHeadResource(boolean isHeadResource) {
		this.isHeadResource = isHeadResource;
	}

	public boolean isHeadResource() {
		return isHeadResource;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
