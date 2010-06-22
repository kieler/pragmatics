package de.cau.cs.kieler.kex.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExampleResource {

	private String context;
	private List<File> resources;
	private boolean isHeadResource;
	private String category;

	public ExampleResource() {
		setResources(new ArrayList<File>());
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getContext() {
		return context;
	}

	public void setResources(List<File> resources) {
		this.resources = resources;
	}

	public void addResource(File file) {
		this.resources.add(file);
	}

	public List<File> getResources() {
		return resources;
	}

	public void setHeadResource(boolean isHeadResource) {
		this.isHeadResource = isHeadResource;
	}

	public boolean isHeadResource() {
		return isHeadResource;
	}

	public String getCategory(){
		return this.category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

}
