/**
 * 
 */
package de.cau.cs.kieler.ptolemy.attachmenteval;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Models the information required by the attachment editor. Attachment data
 * can be loaded and saved.
 * 
 * @author cds
 */
public class AttachmentData {
    
    ////////////////////////////////////////////////////////////////////
    // Constants
    
    /** Separates the different sections of the attachments file. */
    private static final String FILE_SECTION_DELIMITER = "-----";

    
    ////////////////////////////////////////////////////////////////////
    // Fields
    
    /** The folder that contains the Ptolemy models. */
    private IContainer modelsFolder = null;
    
    /**
     * The relative paths of those files included in the evaluation.
     */
    private Set<String> selectedFiles = Sets.newTreeSet();
    
    /**
     * Maps file names to the number of annotation nodes they have.
     */
    private Map<String, Integer> annotationCounts = Maps.newTreeMap();
    
    /**
     * The raw comment-to-actor associations for each file in the models folder. The first map is
     * indexed by paths of the model files, relative to the models folder. The values are hash maps
     * that map the URI fragments of comments in the original Ptolemy model to URI fragments of the
     * actors they are associated to in the original Ptolemy model.
     * 
     * This map is always updated when an association is changed.
     */
    private Map<String, Map<String, String>> rawAssociations = Maps.newTreeMap();
    
    
    ////////////////////////////////////////////////////////////////////
    // Constructors / Factories
    
    /**
     * Instantiated via static factory methods.
     */
    private AttachmentData() {
        
    }
    
    
    /**
     * Returns a new empty instance.
     * 
     * @return new empty attachment data.
     */
    public static AttachmentData create() {
        return new AttachmentData();
    }
    
    /**
     * Creates a new instance initialized from the given file.
     * 
     * @param file the file to initialize the attachment data from.
     * @return new attachment data.
     * @throws Exception if something goes wrong.
     */
    public static AttachmentData fromFile(final IFile file) throws Exception {
        AttachmentData attachmentData = new AttachmentData();
        
        InputStream editorInputStream;
        editorInputStream = file.getContents(true);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(editorInputStream, "UTF-8"));
        
        // Read models folder
        String line = bufferedReader.readLine();
        if (line != null) {
            IPath modelsPath = Path.fromPortableString(line);
            IResource modelsRes = ResourcesPlugin.getWorkspace().getRoot().findMember(modelsPath);
            
            if (modelsRes instanceof IContainer) {
                attachmentData.setModelsFolder((IContainer) modelsRes);
            }
        }
        
        // The rest of the parsing only makes sense if the models folder is not null
        if (attachmentData.getModelsFolder() != null) {
            while((line = bufferedReader.readLine()) != null) {
                // Read until we encounter the file section delimiter
                if (line.equals(FILE_SECTION_DELIMITER)) {
                    break;
                }
                
                attachmentData.getSelectedFiles().add(line.trim());
            }
            
            // Read the associations
            Map<String, String> leMap = null;
            String currentCommentFragmentUri = null;
            while((line = bufferedReader.readLine()) != null) {
                // Read until we encounter the file section delimiter (or the end of the file)
                if (line.equals(FILE_SECTION_DELIMITER)) {
                    break;
                }
                
                // If we have an empty line, we expect to see a new file name next
                if (line.trim().length() == 0) {
                    leMap = null;
                } else if (leMap == null) {
                    // It's the file name!
                    leMap = Maps.newHashMap();
                    attachmentData.getAssociations().put(line.trim(), leMap);
                } else if (currentCommentFragmentUri == null) {
                    currentCommentFragmentUri = line.trim();
                } else {
                    leMap.put(currentCommentFragmentUri, line.trim());
                    currentCommentFragmentUri = null;
                }
            }
            
            // Read the annotation counts
            String currentFileName = null;
            while((line = bufferedReader.readLine()) != null) {
                if (currentFileName == null) {
                    // New file
                    currentFileName = line;
                } else {
                    // Annotation count
                    attachmentData.annotationCounts.put(currentFileName, Integer.valueOf(line));
                    currentFileName = null;
                }
            }
        }
        
        editorInputStream.close();
        return attachmentData;
    }
    
    
    ////////////////////////////////////////////////////////////////////
    // Methods
    
    /**
     * Saves the attachment data into the given file.
     * 
     * @param file the file to save to.
     * @param monitor progress monitor.
     * @throws Exception if anything goes horribly wrong.
     */
    public void save(final IFile file, final IProgressMonitor monitor) throws Exception {
        // Prepare the output
        StringBuffer buffer = new StringBuffer(10000);
        
        // Model folder
        if (modelsFolder != null) {
            buffer.append(modelsFolder.getFullPath().toPortableString()).append("\n");
            
            // Selected files
            for (String selectedFile : getSelectedFiles()) {
                buffer.append(selectedFile).append("\n");
            }
            buffer.append(FILE_SECTION_DELIMITER).append("\n");
            
            // Associations in the different files
            for (Entry<String, Map<String, String>> fileAttachments : rawAssociations.entrySet()) {
                // Don't mention the file if there are no associations anyway
                if (fileAttachments.getValue().isEmpty()) {
                    continue;
                }
                
                buffer.append(fileAttachments.getKey()).append("\n");
                
                for (Entry<String, String> attachment : fileAttachments.getValue().entrySet()) {
                    buffer.append(attachment.getKey()).append("\n");
                    buffer.append(attachment.getValue()).append("\n");
                }
                
                buffer.append("\n");
            }
            buffer.append(FILE_SECTION_DELIMITER).append("\n");
            
            // Number of annotation nodes
            for (Entry<String, Integer> annotationCount : annotationCounts.entrySet()) {
                buffer.append(annotationCount.getKey()).append("\n");
                buffer.append(annotationCount.getValue()).append("\n");
            }
        }
        
        // Write the stuff out
        file.setContents(
                new ByteArrayInputStream(buffer.toString().getBytes()),
                true,
                false,
                monitor);
    }

    
    ////////////////////////////////////////////////////////////////////
    // Accessors

    public IContainer getModelsFolder() {
        return modelsFolder;
    }

    public void setModelsFolder(IContainer modelsFolder) {
        this.modelsFolder = modelsFolder;
    }

    public Set<String> getSelectedFiles() {
        return selectedFiles;
    }
    
    public Map<String, Integer> getAnnotationCounts() {
        return annotationCounts;
    }

    public Map<String, Map<String, String>> getAssociations() {
        return rawAssociations;
    }
    
    
    ////////////////////////////////////////////////////////////////////
    // Statistics
    
    /**
     * Returns the sum of all registered annotation counts. This of course requires that the annotation
     * counts have been set correctly.
     * 
     * @return number of annotations.
     */
    public int getAnnotationCountsSum() {
        int sum = 0;
        
        for (String file : selectedFiles) {
            sum += annotationCounts.get(file);
        }
        
        return sum;
    }
    
    /**
     * Returns the number of registered associations.
     * 
     * @return number of registered associations.
     */
    public int getAssociationCountsSum() {
        int sum = 0;
        
        for (String file : selectedFiles) {
            sum += rawAssociations.get(file).size();
        }
        
        return sum;
    }
}
