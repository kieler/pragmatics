package de.cau.cs.kieler.kex.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.util.ExampleExport;
import de.cau.cs.kieler.kex.controller.util.ExampleImport;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.SourceType;
import de.cau.cs.kieler.kex.model.database.DBExampleCollector;
import de.cau.cs.kieler.kex.model.plugin.PluginExampleCollector;
import de.cau.cs.kieler.kex.model.plugin.PluginExampleCreator;

public class ExampleManager {

    private static ExampleManager instance;

    private boolean isLoaded;

    private final PluginExampleCollector extensionCollector;
    private final DBExampleCollector databaseCollector;

    private final PluginExampleCreator extensionCreator;

    // TODO KEXMessages einbauen als statische klasse, die alle konstanten
    // h�lt.

    // TODO wenn in ui ein editor offen ist, dann macht er den wizard nicht auf.

    // TODO Thesis, begr�nden weshalb hier instance genommen wurde.
    // da wir den Examplepool nicht jedes mal erneut laden wollen, wenn
    // wir darauf zugreifen wollen, k�nnen unter anderem viele werden.

    // TODO category refactoring, das wird so alles nicht mehr gebraucht...
    // da wir die kategorien aus den examples filtern.

    private ExampleManager() {
        this.extensionCollector = new PluginExampleCollector();
        this.extensionCreator = new PluginExampleCreator();
        this.databaseCollector = new DBExampleCollector();
    }

    public synchronized static ExampleManager get() {
        if (instance == null)
            instance = new ExampleManager();
        return instance;
    }

    /**
     * Loads examples, if not loaded before.
     * 
     * @param forceLoad
     *            , set this parameter to force loading of examples
     * @throws KielerException
     */
    public void load(boolean forceLoad) throws KielerException {
        if (!this.isLoaded || forceLoad) {
            load();
            // after completely loaded
            this.isLoaded = true;
        }
    }

    public Example quickStartLoad(SourceType type, String exampleTitle) throws KielerException {
        if (type == SourceType.KIELER) {
            return PluginExampleCollector.getExample(exampleTitle);
        }
        if (type == SourceType.PUBLIC) {
            // search in online interface for example
            return DBExampleCollector.getExample(exampleTitle);
        }
        return null;
    }

    private void load() throws KielerException {
        // TODO exception pool aufbauen und alle examples laden die gehen, rest
        // als messagebox anzeigen.
        this.extensionCollector.load();
        // test impl of an online interface.
        this.databaseCollector.load();
    }

    public Map<String, Example> getExamples() {
        Map<String, Example> result = this.extensionCollector.getExamplePool();
        result.putAll(databaseCollector.getExamplePool());
        return result;
    }

    public List<String> getCategories() {
        List<String> result = new ArrayList<String>();
        result.addAll(databaseCollector.getCategories());
        result.addAll(extensionCollector.getCategories());
        return result;
    }

    public List<String> importExamples(IPath selectedResource, List<Example> selectedExamples,
            boolean checkDuplicate) throws KielerException {
        ExampleImport.validate(selectedResource, selectedExamples, checkDuplicate);
        return ExampleImport.importExamples(selectedResource, selectedExamples, checkDuplicate);
    }

    /**
     * Exports a given example. Created and deleted categories will managed, too.
     * 
     * @param properties
     * @throws KielerException
     */
    public void export(Map<ExampleElement, Object> properties) throws KielerException {

        ExampleExport.validate(properties, this.extensionCollector, this.databaseCollector);

        if (SourceType.KIELER.equals(properties.get(ExampleElement.SOURCETYPE)))
            ExampleExport.exportInPlugin(properties, this.extensionCreator);
        else if (SourceType.PUBLIC.equals(properties.get(ExampleElement.SOURCETYPE))) {
            // TODO build online interface
        } else
            throw new KielerException(ErrorMessage.NO_SOURCETYPE);
    }

    public InputStream loadOverviewPic(Example example) throws KielerException {
        return ExampleImport.loadOverviewPic(example);
    }

    public InputStream loadStandardPic() {
        return ExampleImport.loadStandardPic();
    }

    public List<String> quickStartImport(Example quickStarter) throws KielerException {
        List<Example> quickStarts = new ArrayList<Example>();
        return ExampleImport.importExamples(null, quickStarts, false);
    }

}
