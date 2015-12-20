/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.analyses;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.ptolemy.attachmenteval.AttachmentData;
import de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment.CommentAttachmentEditor;

/**
 * Classes implementing this interface can be called by the {@link CommentAttachmentEditor} to gather
 * statistical data from a set of models. Classes have access to the {@link AttachmentData} instance
 * used by the editor.
 * 
 * @author cds
 */
public interface IAttachmentAnalysis {
    
    /**
     * Called by the attachment editor for every model file that should be put through the analysis.
     * 
     * @param model
     *            the model to analyze.
     * @param modelFilePath
     *            the relative path to the file the model was loaded from.
     * @param attachmentData
     *            the attachment data.
     */
    void process(KNode model, String modelFilePath, AttachmentData attachmentData);
    
    /**
     * Called once every model file has been processed.
     */
    void finish();
    
}
