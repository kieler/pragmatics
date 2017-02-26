/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.graphs.testcases

import java.util.List
import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.IResource
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.ui.handlers.HandlerUtil

class GenerateTestFilesHandler extends AbstractHandler {
    
    public static val PLUGIN_ID = "de.cau.cs.kieler.graphs.testcases";
    
    static val testGraphsFolderName = "testGraphs"
    static val testGraphImagesFolderName = "testGraphImages"
        
    override execute(ExecutionEvent event) throws ExecutionException {
        val sel = HandlerUtil.getCurrentSelection(event)
        try {
            switch sel { IStructuredSelection: {
                sel.toList.filter(IResource).forEach[ r |
                    val outs = switch r {
                        IProject: r.createOutputFolders(r.name)
                        IFolder: r.parent.createOutputFolders(r.name)
                    }
                    
                    val graphFiles = newArrayList()
                    collectGraphFiles(r, graphFiles)
                    val job = new GeneratorJob("Generating Test Graphs", graphFiles, r.fullPath, outs.key, outs.value)
                    job.user = true
                    job.schedule
                ]
            }}
        } catch  (Throwable t){
            t.printStackTrace
        }
        
        return null
    }
    
    def dispatch createOutputFolders(IProject proj, String prefix) {
        proj.getOrCreateFolder(prefix + "_" + testGraphsFolderName) 
        -> proj.getOrCreateFolder(prefix + "_" + testGraphImagesFolderName)
    }
    
    def dispatch createOutputFolders(IFolder f, String prefix) {
        f.getOrCreateFolder(prefix + "_" + testGraphsFolderName) 
        -> f.getOrCreateFolder(prefix + "_" + testGraphImagesFolderName)
    }
    
    def IFolder getOrCreateFolder(IProject p, String folderName) {
        p.getFolder(folderName) => [ f |
            if (!f.exists)
                f.create(true, true, null)
        ]
    }
    
    def IFolder getOrCreateFolder(IFolder p, String folderName) {
        p.getFolder(folderName) => [ f |
            if (!f.exists)
                f.create(true, true, null)
        ]
    }
     
    def void collectGraphFiles(IResource res, List<IFile> files) {
        switch res {
            IContainer case !res.name.contains(testGraphsFolderName) && !res.name.contains(testGraphImagesFolderName):
                res.members.forEach[ it.collectGraphFiles(files) ]
            IFile:
                files += res
        }
    }
    
}