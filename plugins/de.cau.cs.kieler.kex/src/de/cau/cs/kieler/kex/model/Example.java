/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kex.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The mainmodel class of KEX. This model has all attributes for an KEX example.
 * 
 * @author pkl
 * 
 */
public class Example {

    /**
     * unique field.
     */
    private final String title;

    private final List<ExampleResource> resources;

    private final List<String> categories;

    private final SourceType sourceType;

    private final Date generationDate;

    private String description;

    private String contact;

    private String author;

    private String namespaceId;

    private String rootDir;

    private String overviewPic;

    private Boolean isProject;

    /**
     * Creates a new example model.
     * 
     * @param titleParam
     *            , {@link String}
     * @param sourceTypeParam
     *            , {@link SourceType}
     */
    public Example(final String titleParam, final SourceType sourceTypeParam) {
        this.title = titleParam;
        this.sourceType = sourceTypeParam;
        this.resources = new ArrayList<ExampleResource>();
        this.categories = new ArrayList<String>();
        this.generationDate = Calendar.getInstance().getTime();
    }

    @Override
    public String toString() {
        return new StringBuffer().append("Example [title= ").append(getTitle())
                .append(",source type= ").append(SourceType.map(this.sourceType))
                .append(getContact()).append(", author= ").append(this.author)
                .append(", generated at= ").append(this.generationDate.toString())
                .append(", description= ").append(getDescription()).append("]").toString();
    }

    /**
     * Searches for a category.
     * 
     * @param category
     *            , {@link String}
     * @return boolean
     */
    public boolean contains(final String category) {
        for (String element : getCategories()) {
            if (category.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for example title.
     * 
     * @return {@link String}
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Getter for generationdate.
     * 
     * @return {@link Date}
     */
    public Date getGenerationDate() {
        return this.generationDate;
    }

    /**
     * Getter for sourcetype.
     * 
     * @return {@link SourceType}
     */
    public SourceType getSourceType() {
        return this.sourceType;
    }

    /**
     * adds categories to example.
     * 
     * @param categoriesParam
     *            , {@link List} of {@link String}s.
     */
    public void addCategories(final List<String> categoriesParam) {
        this.categories.addAll(categoriesParam);
    }

    /**
     * Getter for example categories.
     * 
     * @return {@link List} of {@link String}s
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * adds new example resources to example.
     * 
     * @param exampleResources
     *            {@link List} of {@link ExampleResource}s
     */
    public void addResources(final List<ExampleResource> exampleResources) {
        this.resources.addAll(exampleResources);
    }

    /**
     * Getter for example resources.
     * 
     * @return {@link List} of {@link ExampleResource}s
     */
    public List<ExampleResource> getResources() {
        return resources;
    }

    /**
     * Setter for example description.
     * 
     * @param descriptionParam
     *            , {@link String}
     */
    public void setDescription(final String descriptionParam) {
        this.description = descriptionParam;
    }

    /**
     * Getter for example description.
     * 
     * @return {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for contact.
     * 
     * @return {@link String}
     */
    public String getContact() {
        return this.contact;
    }

    /**
     * Setter for example contact.
     * 
     * @param contactParam
     *            , {@link String}
     */
    public void setContact(final String contactParam) {
        this.contact = contactParam;
    }

    /**
     * Setter for example namespaceId.
     * 
     * @param namespaceIdParam
     *            , {@link String}
     */
    public void setNamespaceId(final String namespaceIdParam) {
        this.namespaceId = namespaceIdParam;
    }

    /**
     * Getter for example namespace id.
     * 
     * @return {@link String}
     */
    public String getNamespaceId() {
        return this.namespaceId;
    }

    /**
     * Setter for example root directory.
     * 
     * @param rootDirParam
     *            , {@link String}
     */
    public void setRootDir(final String rootDirParam) {
        this.rootDir = rootDirParam;
    }

    /**
     * Getter for root directory.
     * 
     * @return {@link String}
     */
    public String getRootDir() {
        return this.rootDir;
    }

    /**
     * Setter for example author.
     * 
     * @param authorParam
     *            , {@link String}
     */
    public void setAuthor(final String authorParam) {
        this.author = authorParam;
    }

    /**
     * Getter for example author.
     * 
     * @return {@link String}
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter for example overviewPic.
     * 
     * @param overviewPicParam
     *            , {@link String}
     */
    public void setOverviewPic(final String overviewPicParam) {
        this.overviewPic = overviewPicParam;
    }

    /**
     * Getter for example preview picture.
     * 
     * @return {@link String}
     */
    public String getOverviewPic() {
        return overviewPic;
    }

    /**
     * checks if the example resources contains a project.
     * 
     * @return boolean
     */
    public boolean isProject() {
        // should only be loaded one time.
        if (isProject == null) {
            for (ExampleResource resource : resources) {
                if (ExampleResource.Type.PROJECT == resource.getResourceType()) {
                    isProject = Boolean.valueOf(true);
                    return isProject;
                }
            }
            isProject = Boolean.valueOf(false);
            return isProject;
        } else {
            return isProject;
        }

    }

}
