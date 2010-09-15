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
import de.cau.cs.kieler.kex.model.SourceType;
import de.cau.cs.kieler.kex.model.plugin.PluginExampleCreator;

public class ExampleExport {

    @SuppressWarnings("unchecked")
    public static void validate(Map<ExampleElement, Object> map,
            final ExampleCollector... collectors) throws KielerException {
        checkAttributes(map);

        Object sourceType = map.get(ExampleElement.SOURCETYPE);
        if (!(sourceType instanceof SourceType))
            throw new KielerException("No source type has been defined.");

        String destLocation = (String) map.get(ExampleElement.DEST_LOCATION);
        validateField(destLocation, 2, "Destination Location");

        List<String> categories = (List<String>) map.get(ExampleElement.CATEGORIES);
        validateElement(categories, 1, "Categories");

        List<ExportResource> exportedResources = (List<ExportResource>) map
                .get(ExampleElement.RESOURCES);
        validateElement(exportedResources, 1, "Exported Resources");

        // TODO prüfen, ob erzeugbar
        // map.get(ExampleElement.CREATE_CATEGORIES);

        // first duplicate check
        ExampleExport.checkDuplicate((String) map.get(ExampleElement.TITLE), collectors);

    }

    private static void checkAttributes(Map<ExampleElement, Object> map) throws KielerException {
        String exampleTitle = (String) map.get(ExampleElement.TITLE);
        validateField(exampleTitle, 4, "Example Title");

        String author = (String) map.get(ExampleElement.AUTHOR);
        // min. uni abbreviations like pkl
        validateField(author, 3, "Author");

        String exampleDescription = (String) map.get(ExampleElement.DESCRIPTION);
        validateField(exampleDescription, 10, "Example Description");

        String exampleContact = (String) map.get(ExampleElement.CONTACT);
        // min 5 chars a@b.c
        validateField(exampleContact, 5, "Example Contact");

    }

    private static void validateElement(List<?> list, int minLength, String listName)
            throws KielerException {
        if (list == null || list.size() < minLength) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("No ").append(listName).append(" has been selected.\n")
                    .append("Please choose at least ").append(String.valueOf(minLength));
            throw new KielerException(errorMsg.toString());
        }
    }

    private static void validateField(String checkable, int minLength, String checkableName)
            throws KielerException {
        if (checkable == null || checkable.length() < minLength) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("The field ").append(checkableName)
                    .append(" has to be set with at least ").append(String.valueOf(minLength))
                    .append(" characters.");
            throw new KielerException(errorMsg.toString());
        }
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
    public static Example mapToExample(Map<ExampleElement, Object> properties) {
        Example result = new Example((String) properties.get(ExampleElement.TITLE),
                (SourceType) properties.get(ExampleElement.SOURCETYPE));
        result.setDescription((String) properties.get(ExampleElement.DESCRIPTION));
        result.setContact((String) properties.get(ExampleElement.CONTACT));
        result.addCategories((List<String>) properties.get(ExampleElement.CATEGORIES));
        result.setAuthor((String) properties.get(ExampleElement.AUTHOR));
        result.setRootDir((String) properties.get(ExampleElement.DEST_LOCATION));
        return result;
    }

    /**
     * 
     * @param example
     * @throws KielerException
     */
    public static void checkDuplicate(String exampleTitle, ExampleCollector... collectors)
            throws KielerException {
        if (exampleTitle == null)
            throw new KielerException("ID of an example could not be null.");
        for (ExampleCollector collector : collectors) {
            if (collector.getExamplePool().containsKey(exampleTitle))
                throw new KielerException("Duplicate example id. Please choose an other one!");
        }
    }

    @SuppressWarnings("unchecked")
    public static void exportInPlugin(final Map<ExampleElement, Object> properties,
            PluginExampleCreator extensionCreator) throws KielerException {

        Example mappedExample = ExampleExport.mapToExample(properties);

        File destFile = new File(mappedExample.getRootDir());
        if (!destFile.exists())
            throw new KielerException(ErrorMessage.DESTFILE_NOT_EXIST + mappedExample.getRootDir());

        List<ExportResource> exportResources = (List<ExportResource>) properties
                .get(ExampleElement.RESOURCES);
        List<IPath> finishedResources = new ArrayList<IPath>();
        try {
            extensionCreator.copyResources(destFile, exportResources, finishedResources);
            extensionCreator.addExtension(destFile, mappedExample,
                    (List<String>) properties.get(ExampleElement.CREATE_CATEGORIES));
            mappedExample.addResources(ExampleExport.mapToExampleResource(exportResources));
        } catch (KielerException e) {
            extensionCreator.deleteExampleResources(finishedResources);
            throw e;
        }
    }

    private static List<ExampleResource> mapToExampleResource(List<ExportResource> exportResources) {
        List<ExampleResource> result = new ArrayList<ExampleResource>();
        for (ExportResource exRe : exportResources) {
            ExampleResource resultItem = new ExampleResource(
                    exRe.getLocalPath().toPortableString(), ExampleResource.Type.valueOf(exRe
                            .getResource().getClass().getSimpleName()));
            resultItem.setDirectOpen(exRe.isDirectOpen());
            result.add(resultItem);
        }
        return result;
    }
}
