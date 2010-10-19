package de.cau.cs.kieler.kex.model.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ErrorMessage;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
import de.cau.cs.kieler.kex.model.ExampleResource;
import de.cau.cs.kieler.kex.model.SourceType;

public class PluginExampleCollector extends ExampleCollector {

    // leichter HashMap, da wir eine Pruefung auf enthalten sein machen.
    // und wir dann bei einer liste komplett ueber alle elemente iterieren
    // muessten
    // um die id abzugleichen.

    private final Map<String, Example> examplePool;

    private List<String> categoryPool;

    public PluginExampleCollector() {
        examplePool = new HashMap<String, Example>();

    }

    /**
     * loads examples of extenders.
     * 
     * @throws KielerException
     */
    @Override
    public void load() throws KielerException {
        if (this.categoryPool == null) {
            this.categoryPool = new ArrayList<String>();
            loadCategories();
        }
        IConfigurationElement[] configElements = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(PluginConstants.KEX_EXT_POINT);
        for (IConfigurationElement element : configElements) {
            try {
                String elementName = element.getName();
                if (PluginConstants.EXAMPLE.equals(elementName)) {
                    String exampleTitle = element.getAttribute(PluginConstants.TITLE);
                    if (getExamplePool().containsKey(exampleTitle)) {
                        // TODO darf eigentlich nicht passieren
                        // RUNTIME Exception schmei�en...
                        // oder einfach annehmen, dass dies nicht geschieht
                        continue;
                    }
                    Example example = null;
                    try {
                        example = toExample(element);
                    } catch (Exception e) {
                        continue;
                    }
                    this.examplePool.put(exampleTitle, example);
                } else if (PluginConstants.CATEGORY.equals(elementName)) {
                    collectCategory(element);
                }
            } catch (InvalidRegistryObjectException e) {
                throw new KielerException("Error while loading example \""
                        + element.getAttribute(PluginConstants.ID) + "\". "
                        + e.getLocalizedMessage());
            } catch (IllegalArgumentException e1) {
                throw new KielerException("Error while loading example \""
                        + element.getAttribute(PluginConstants.ID) + "\". "
                        + e1.getLocalizedMessage());
            }
            // catch (KielerException e2) {
            // throw new KielerException("Error while loading example \""
            // + element.getAttribute(PluginConstants.ID) + "\". "
            // + e2.getLocalizedMessage());
            // }
        }
    }

    public void collectCategory(IConfigurationElement categoryElement) {
        String categoryId = categoryElement.getAttribute(PluginConstants.ID);
        if (categoryId == null || categoryId.length() < 4) {
            // TODO undefined state, think about it.
        } else {
            if (!getCategories().contains(categoryId))
                getCategories().add(categoryId);
            else {
                // TODO that should not happen
            }
        }
    }

    @Override
    public Map<String, Example> getExamplePool() {
        return this.examplePool;
    }

    public List<String> getCategories() {
        if (this.categoryPool == null) {
            this.categoryPool = new ArrayList<String>();
            loadCategories();
        }
        return categoryPool;
    }

    private void loadCategories() {
        IConfigurationElement[] configElements = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(PluginConstants.KEX_EXT_POINT);
        for (IConfigurationElement element : configElements) {
            if (PluginConstants.CATEGORY.equals(element.getName())) {
                collectCategory(element);
            }
        }
    }

    @Override
    public SourceType getSourceType() {
        return SourceType.KIELER;
    }

    public static Example toExample(final IConfigurationElement exampleElement)
            throws InvalidRegistryObjectException, IllegalArgumentException, KielerException {

        String titleAttr = exampleElement.getAttribute(PluginConstants.TITLE);
        // ein freier string, min. default besser noch regex.
        Example example = new Example(titleAttr, SourceType.KIELER);
        example.setDescription(exampleElement.getAttribute(PluginConstants.DESCRIPTION));
        example.setContact(exampleElement.getAttribute(PluginConstants.CONTACT));
        example.setAuthor(exampleElement.getAttribute(PluginConstants.AUTHOR));
        example.setOverviewPic(exampleElement.getAttribute(PluginConstants.OVERVIEW_PIC));
        String exNamespaceId = exampleElement.getNamespaceIdentifier();
        example.setNamespaceId(exNamespaceId);
        example.setRootDir(exampleElement.getAttribute(PluginConstants.ROOT_DIRECTORY));

        List<String> categories = filterElement(exampleElement, PluginConstants.CATEGORY,
                PluginConstants.ID);
        example.addCategories(categories);
        example.addResources(filterExampleResource(exampleElement));
        return example;
    }

    private static List<String> filterElement(IConfigurationElement exampleElement,
            String elementName, String attributeName) {
        List<String> result = new ArrayList<String>();
        for (IConfigurationElement configurationElement : exampleElement.getChildren(elementName)) {
            String catName = configurationElement.getAttribute(attributeName);
            if (catName != null)
                result.add(catName);
        }
        return result;
    }

    private static List<ExampleResource> filterExampleResource(IConfigurationElement exampleElement) {
        List<ExampleResource> result = new ArrayList<ExampleResource>();
        for (IConfigurationElement configurationElement : exampleElement
                .getChildren(PluginConstants.EXAMPLE_RESOURCE)) {
            String resourceType = configurationElement.getAttribute(PluginConstants.RESOURCE_TYPE);
            String localPath = configurationElement.getAttribute(PluginConstants.LOCAL_PATH);
            if (resourceType != null && localPath != null) {
                ExampleResource exRe = new ExampleResource(localPath,
                        ExampleResource.Type.valueOf(resourceType.toUpperCase()));
                String direct_open = configurationElement.getAttribute(PluginConstants.DIRECT_OPEN);
                if (direct_open != null)
                    exRe.setDirectOpen(Boolean.parseBoolean(direct_open));
                result.add(exRe);
            }
        }
        return result;
    }

    public static Example getExample(String exampleTitle) throws KielerException {
        IConfigurationElement[] configElements = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(PluginConstants.KEX_EXT_POINT);
        for (IConfigurationElement element : configElements) {
            String elementName = element.getName();
            if (PluginConstants.EXAMPLE.equals(elementName)) {
                if (element.getAttribute(PluginConstants.TITLE).equals(exampleTitle)) {
                    try {
                        return toExample(element);
                    } catch (InvalidRegistryObjectException e) {
                        throw new KielerException(ErrorMessage.LOAD_ERROR
                                + element.getAttribute(PluginConstants.ID) + "\". "
                                + e.getLocalizedMessage());
                    } catch (IllegalArgumentException e1) {
                        throw new KielerException(ErrorMessage.LOAD_ERROR
                                + element.getAttribute(PluginConstants.ID) + "\". "
                                + e1.getLocalizedMessage());
                    } catch (KielerException e2) {
                        throw new KielerException(ErrorMessage.LOAD_ERROR
                                + element.getAttribute(PluginConstants.ID) + "\". "
                                + e2.getLocalizedMessage());

                    }
                }
            }
        }

        return null;
    }
}
