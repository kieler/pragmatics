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
 * 
 */
package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ErrorMessage;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExportResource;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
import de.cau.cs.kieler.kex.model.ExampleResource;
import de.cau.cs.kieler.kex.model.ExampleResource.Type;
import de.cau.cs.kieler.kex.model.SourceType;
import de.cau.cs.kieler.kex.model.plugin.PluginExampleCreator;

/**
 * A collection of example export functions.
 * 
 * @author pkl
 * 
 */
public final class ExampleExport {

    private static final String PROJECT_CLASS = "org.eclipse.core.internal.resources.Project";
    private static final String FOLDER_CLASS = "org.eclipse.core.internal.resources.Folder";
    private static final String FILE_CLASS = "org.eclipse.core.internal.resources.File";
    private static final int EXAMPLE_TITLE_MIN = 4;
    private static final int AUTHOR_MIN = 3;
    private static final int DESCRIPTION_MIN = 10;
    private static final int CONTACT_MIN = 5;

    private ExampleExport() {
        // should not called
    }

    /**
     * Method for validating given map elements. This contains minimumlengths- and duplicate-checks.
     * 
     * @param map
     *            , Map of {@link ExampleElement} and an arbitrary {@link Object}.
     * @param collectors
     *            , {@link ExampleCollector}s
     * @throws KielerException
     *             , if any check fail a {@link KielerException} will throw.
     */
    @SuppressWarnings("unchecked")
    public static void validate(final Map<ExampleElement, Object> map,
            final ExampleCollector... collectors) throws KielerException {
        checkAttributes(map);

        Object sourceType = map.get(ExampleElement.SOURCETYPE);
        if (!(sourceType instanceof SourceType)) {
            throw new KielerException("No source type has been defined.");
        }

        String destLocation = (String) map.get(ExampleElement.DEST_LOCATION);
        validateField(destLocation, 2, "Destination Location");

        List<String> categories = (List<String>) map.get(ExampleElement.CATEGORIES);
        validateElement(categories, 1, "Categories");

        List<ExportResource> exportedResources = (List<ExportResource>) map
                .get(ExampleElement.RESOURCES);
        validateElement(exportedResources, 1, "Exported Resources");

        // first example duplicate check
        ExampleExport.checkDuplicate((String) map.get(ExampleElement.TITLE), collectors);

    }

    private static void checkAttributes(final Map<ExampleElement, Object> map)
            throws KielerException {
        String exampleTitle = (String) map.get(ExampleElement.TITLE);
        validateField(exampleTitle, EXAMPLE_TITLE_MIN, "Example Title");

        String author = (String) map.get(ExampleElement.AUTHOR);
        // min. uni abbreviations like pkl
        validateField(author, AUTHOR_MIN, "Author");

        String exampleDescription = (String) map.get(ExampleElement.DESCRIPTION);
        validateField(exampleDescription, DESCRIPTION_MIN, "Example Description");

        String exampleContact = (String) map.get(ExampleElement.CONTACT);
        // min 5 chars a@b.c
        validateField(exampleContact, CONTACT_MIN, "Example Contact");
    }

    private static void validateElement(final List<?> list, final int minLength,
            final String listName) throws KielerException {
        if (list == null || list.size() < minLength) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("No ").append(listName).append(" has been selected.\n")
                    .append("Please choose at least ").append(String.valueOf(minLength));
            throw new KielerException(errorMsg.toString());
        }
    }

    private static void validateField(final String checkable, final int minLength,
            final String checkableName) throws KielerException {
        if (checkable == null || checkable.length() < minLength) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("The field ").append(checkableName)
                    .append(" has to be set with at least ").append(String.valueOf(minLength))
                    .append(" characters.");
            throw new KielerException(errorMsg.toString());
        }
    }

    /**
     * checks the collectors for given exampleTitle. If exists, a {@link KielerException} will
     * thrown.
     * 
     * @param exampleTitle
     *            , String
     * @param collectors
     *            , {@link ExampleCollector}...
     * @throws KielerException
     *             , throws if duplicate found.
     * 
     */
    public static void checkDuplicate(final String exampleTitle,
            final ExampleCollector... collectors) throws KielerException {
        if (exampleTitle == null) {
            throw new KielerException("Title of an example could not be null.");
        }
        for (ExampleCollector collector : collectors) {
            if (collector.getExamplePool().containsKey(exampleTitle)) {
                throw new KielerException("Duplicate example title. Please choose an other one!");
            }
        }
    }

    /**
     * extends a plugin with a new example.
     * 
     * @param properties
     *            , {@link Map} with {@link ExampleElement} as key and an {@link Object} as value.
     * @param extensionCreator
     *            , {@link PluginExampleCreator}
     * @throws KielerException
     *             , will be caused if some errors occurs.
     */
    @SuppressWarnings("unchecked")
    public static void exportInPlugin(final Map<ExampleElement, Object> properties,
            final PluginExampleCreator extensionCreator) throws KielerException {

        Example mappedExample = ExampleExport.mapToExample(properties);

        File destFile = new File(mappedExample.getRootDir());
        if (!destFile.exists()) {
            throw new KielerException(ErrorMessage.DESTFILE_NOT_EXIST + mappedExample.getRootDir());
        }

        List<ExportResource> exportResources = (List<ExportResource>) properties
                .get(ExampleElement.RESOURCES);
        List<IPath> finishedResources = new ArrayList<IPath>();
        try {
            extensionCreator.copyResources(destFile, exportResources, finishedResources);
            mappedExample.addResources(ExampleExport.mapToExampleResource(exportResources));

            String absOverviewPic = copyOverviewPic(
                    (String) properties.get(ExampleElement.OVERVIEW_PIC), extensionCreator,
                    destFile, finishedResources);

            extensionCreator
                    .addExtension(destFile, mappedExample,
                            (List<String>) properties.get(ExampleElement.CREATE_CATEGORIES),
                            absOverviewPic);
        } catch (KielerException e) {
            extensionCreator.deleteExampleResources(finishedResources);
            throw e;
        }
    }

    private static String copyOverviewPic(final String overviewPic,
            final PluginExampleCreator extensionCreator, final File destFile,
            final List<IPath> finishedResources) throws KielerException {
        if (overviewPic != null && overviewPic.length() > 1) {
            String absolutePath = extensionCreator.copyOverviewPic(destFile.getPath(), overviewPic,
                    finishedResources);
            return absolutePath;
        }
        return null;
    }

    /**
     * mapping of properties onto an example.
     * 
     * @param properties
     *            , Map<String, Object>
     * @param rootResource
     * @return Example
     */
    @SuppressWarnings("unchecked")
    public static Example mapToExample(final Map<ExampleElement, Object> properties) {
        Example result = new Example((String) properties.get(ExampleElement.ID),
                (String) properties.get(ExampleElement.TITLE),
                (SourceType) properties.get(ExampleElement.SOURCETYPE));
        result.setDescription((String) properties.get(ExampleElement.DESCRIPTION));
        result.setContact((String) properties.get(ExampleElement.CONTACT));
        result.addCategories((List<String>) properties.get(ExampleElement.CATEGORIES));
        result.setAuthor((String) properties.get(ExampleElement.AUTHOR));
        result.setRootDir((String) properties.get(ExampleElement.DEST_LOCATION));
        result.setOverviewPic((String) properties.get(ExampleElement.OVERVIEW_PIC));
        return result;
    }

    private static List<ExampleResource> mapToExampleResource(
            final List<ExportResource> exportResources) {
        List<ExampleResource> result = new ArrayList<ExampleResource>();
        for (ExportResource exRe : exportResources) {
            ExampleResource.Type st = filterSourceType(exRe.getResource().getClass().getName());
            ExampleResource resultItem = new ExampleResource(
                    exRe.getLocalPath().toPortableString(), st);
            resultItem.setDirectOpen(exRe.isDirectOpen());
            result.add(resultItem);
        }
        return result;
    }

    private static ExampleResource.Type filterSourceType(final String name) {
        if (PROJECT_CLASS.equals(name)) {
            return Type.PROJECT;
        }
        if (FOLDER_CLASS.equals(name)) {
            return Type.FOLDER;
        }
        if (FILE_CLASS.equals(name)) {
            return Type.FILE;
        }
        return null;
    }
}
