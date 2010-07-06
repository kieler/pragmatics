package de.cau.cs.kieler.kex.model;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Version;

public class Example {

	/**
	 * unique identifier of an example
	 */
	private String id;

	/**
	 * name of an example
	 */
	private String name;

	private String description;

	/**
	 * initial version is set, if not specified.
	 */
	private static final Version INITVERSION = new Version(1, 0, 0);

	private Version version;

	private List<ExampleResource> resources;

	private String contact;

	public Example(String id, String name) {
		init(id, name, INITVERSION);
	}

	public Example(String id, String name, Version version) {
		init(id, name, version);
	}

	private void init(String id, String name, Version version) {
		this.id = id;
		this.name = name;
		this.version = version;
		resources = new ArrayList<ExampleResource>();
	}

	@Override
	public String toString() {
		return "Example [id=" + getId()
				+ ((getName() != null) ? ", name= " + getName() : "")
				+ ",contact=" + getContact() + ", version="
				+ version.toString() + "]";
	}

	public boolean contains(String category) {
		for (ExampleResource exampleResource : getResources()) {
			if (category.equals(exampleResource.getCategory()))
				return true;
		}
		return false;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Version getVersion() {
		return this.version;
	}

	public List<ExampleResource> getResources() {
		return resources;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void addResources(List<ExampleResource> exampleResources) {
		this.resources.addAll(exampleResources);
	}

	public List<String> getExampleCategories() {
		List<String> categories = new ArrayList<String>();
		boolean contained = false;
		for (ExampleResource resource : this.resources) {
			contained = false;
			String category = resource.getCategory();
			for (String element : categories) {
				if (element.equals(category)) {
					contained = true;
					break;
				}
			}
			if (!contained)
				categories.add(resource.getCategory());
		}
		return categories;

	}

}
