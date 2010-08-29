package de.cau.cs.kieler.kex.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.osgi.framework.Version;

public class Example {

	/**
	 * unique field.
	 */
	private final String title;

	/**
	 * initial version is set, if not specified.
	 */
	private final Version version;

	private final List<ExampleResource> resources;

	private final List<String> categories;

	private final SourceType sourceType;

	private final Date generationDate;

	private String description;

	private String contact;

	private String author;

	private String namespaceId;

	private String rootResource;

	private String overViewPicPath;

	public Example(final String title, Version version, SourceType importType) {
		this.title = title;
		this.version = version;
		this.sourceType = importType;
		this.resources = new ArrayList<ExampleResource>();
		this.categories = new ArrayList<String>();
		this.generationDate = Calendar.getInstance().getTime();
	}

	@Override
	public String toString() {
		return new StringBuffer().append("Example [title= ").append(getTitle())
				.append(",source type= ").append(
						SourceType.map(this.sourceType)).append(getContact())
				.append(", version= ").append(version.toString()).append(
						", author= ").append(this.author).append(
						", generated at= ").append(
						this.generationDate.toString()).append(
						", description= ").append(getDescription()).append("]")
				.toString();
	}

	public boolean contains(String category) {
		for (String element : getCategories()) {
			if (category.equals(element))
				return true;
		}
		return false;
	}

	public String getTitle() {
		return this.title;
	}

	public Date getGenerationDate() {
		return this.generationDate;
	}

	public Version getVersion() {
		return this.version;
	}

	public SourceType getSourceType() {
		return this.sourceType;
	}

	public void addCategories(List<String> categories) {
		this.categories.addAll(categories);
	}

	public List<String> getCategories() {
		return categories;
	}

	public void addResources(List<ExampleResource> exampleResources) {
		this.resources.addAll(exampleResources);
	}

	public List<ExampleResource> getResources() {
		return resources;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}

	public String getNamespaceId() {
		return this.namespaceId;
	}

	public void setRootResource(String rootResource) {
		this.rootResource = rootResource;
	}

	public String getRootResource() {
		return this.rootResource;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setOverViewPicPath(String overViewPicPath) {
		this.overViewPicPath = overViewPicPath;
	}

	public String getOverViewPicPath() {
		return overViewPicPath;
	}

}
