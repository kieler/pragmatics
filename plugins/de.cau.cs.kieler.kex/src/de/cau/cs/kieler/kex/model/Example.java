package de.cau.cs.kieler.kex.model;

import java.net.URL;
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

	// TODO versuchen beim import ein ipath raus zu zaubern... nicht mit urls
	// arbeiten
	private List<URL> resources;

	private String contact;

	private String namespaceId;

	private URL headResource;

	private List<String> categories;

	private SourceType importType;

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
		this.resources = new ArrayList<URL>();
		this.categories = new ArrayList<String>();
	}

	@Override
	public String toString() {
		return "Example [id=" + getId() + ", name= " + getName() + ",contact="
				+ getContact() + ", version=" + version.toString()
				+ "description= " + getDescription() + "]";
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

	// TODO auf jeden fall �ber string path (nutze namespace identifier) l�sen.
	public List<URL> getResources() {
		return resources;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void addResources(List<URL> exampleResources) {
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

	public void setHeadResource(URL headResource) {
		this.headResource = headResource;
	}

	public URL getHeadResource() {
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

}
