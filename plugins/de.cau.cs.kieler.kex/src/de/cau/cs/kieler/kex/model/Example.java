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
	private Version version;

	private List<String> resources;

	private String contact;

	private String namespaceId;

	private String headResource;

	private List<String> categories;

	private SourceType importType;

	private String rootResource;

	public Example(String id, String name, Version version,
			SourceType importType) {
		init(id, name, version, importType);
	}

	private void init(String id, String name, Version version,
			SourceType importType) {
		this.id = id;
		this.name = name;
		this.version = version;
		this.setImportType(importType);
		this.resources = new ArrayList<String>();
		this.categories = new ArrayList<String>();
	}

	@Override
	public String toString() {
		return new StringBuffer().append("Example [id=").append(getId())
				.append(", name= ").append(getName()).append(",contact=")
				.append(getContact()).append(", version=").append(
						version.toString()).append("description= ").append(
						getDescription()).append("]").toString();
	}

	public boolean contains(String category) {
		for (String element : getCategories()) {
			if (category.equals(element))
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

	public List<String> getResources() {
		return resources;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void addResources(List<String> exampleResources) {
		this.resources.addAll(exampleResources);
	}

	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}

	public String getNamespaceId() {
		return this.namespaceId;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setHeadResource(String headResource) {
		this.headResource = headResource;
	}

	public String getHeadResource() {
		return headResource;
	}

	public void setImportType(SourceType importType) {
		this.importType = importType;
	}

	public SourceType getImportType() {
		return importType;
	}

	public void addCategories(List<String> categories) {
		this.categories.addAll(categories);
	}

	public void setRootResource(String rootResource) {
		this.rootResource = rootResource;
	}

	public String getRootResource() {
		return this.rootResource;
	}

}
