/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.text.ui.contentassist

import de.cau.cs.kieler.kiml.grana.AnalysisService
import de.cau.cs.kieler.kiml.grana.text.grana.RangeJob
import java.io.File
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.emf.ecore.EObject
import org.eclipse.jface.viewers.StyledString
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor

/**
 * see http://www.eclipse.org/Xtext/documentation.html#contentAssist on how to customize content assistant
 * 
 * @author uru
 */
class GranaProposalProvider extends AbstractGranaProposalProvider {
    
    /**
     * Proposals for graph analyses. 
     */
    override completeAnalysis_Name(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        super.completeAnalysis_Name(model, assignment, context, acceptor)

        // propose all known analyses that match the current prefix
        for (a : AnalysisService.instance.analyses) {
            if (isValidProposal(a.id, context.prefix, context)) {
                val displayString = new StyledString(a.name)
                acceptor.accept(doCreateProposal(a.id, displayString, null, priorityHelper.defaultPriority + 1, context))
            }
        }
    }

    // some constants for the following code completions for workspace navigation 
    val FS_PREFIX = "file://"
    val SUG_WORKSPACEROOT = "\"/"
    val DSP_WORKSPACE_ROOT = new StyledString("Workspace Root")
    val SUG_FILESYSTEM = "\"" + FS_PREFIX
    val DSP_FILESYSTEM = new StyledString("Filesystem")
            
    override completeLocalResource_Path(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        super.completeLocalResource_Path(model, assignment, context, acceptor)
        
        // remove quotes
        val stripped = context.prefix.replaceAll("^\"|\"$", "");
        // if string is empty suggest either the root of the workspace or the root of file system
        if (stripped.nullOrEmpty) {
            acceptor.accept(doCreateProposal(SUG_WORKSPACEROOT, DSP_WORKSPACE_ROOT, 
                            null, priorityHelper.defaultPriority + 1, context))
            acceptor.accept(doCreateProposal(SUG_FILESYSTEM, DSP_FILESYSTEM, 
                            null, priorityHelper.defaultPriority + 1, context))
            return
        }
        
        val isFs = stripped.startsWith(FS_PREFIX)
        if (isFs) {
            // FILESYSTEM
            filesystemSuggestions(context, acceptor, false)    
        } else {
            // WORKSPACE
            workspaceSuggestions(context, acceptor, false)  
        }
    }
    
    override complete_LocalOutput(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        super.complete_LocalOutput(model, ruleCall, context, acceptor)
        
        
        // remove quotes
        val stripped = context.prefix.replaceAll("^\"|\"$", "");
        // if string is empty suggest either the root of the workspace or the root of file system
        if (stripped.nullOrEmpty) {
            acceptor.accept(doCreateProposal(SUG_WORKSPACEROOT, DSP_WORKSPACE_ROOT, 
                            null, priorityHelper.defaultPriority + 1, context))
            acceptor.accept(doCreateProposal(SUG_FILESYSTEM, DSP_FILESYSTEM, 
                            null, priorityHelper.defaultPriority + 1, context))
            return
        }
        
        val isFs = stripped.startsWith(FS_PREFIX)
        if (isFs) {
            // FILESYSTEM
            filesystemSuggestions(context, acceptor, true)    
        } else {
            // WORKSPACE
            workspaceSuggestions(context, acceptor, true)  
        }
    }
    
    private def filesystemSuggestions(ContentAssistContext context, ICompletionProposalAcceptor acceptor, boolean acceptFiles) {
          
        // remove quotes
        val stripped = context.prefix.replaceAll("^\"|\"$", "");
        
        // extract a valid path (or at least what we think might be a valid path)
        val woFile = stripped.substring(FS_PREFIX.length)
        // at least leave over the slash representing the root
        val sepIndex = woFile.lastIndexOf("/")
        
        // no root selected yet?
        if (woFile.length == 0 || sepIndex == -1) {
            for (root : File.listRoots) {
                val suggestion = root.path.replaceAll("\\\\", "/")
                val displayString = new StyledString(suggestion)
                var quotedSuggestion = "\"" + FS_PREFIX + suggestion
                acceptor.accept(doCreateProposal(quotedSuggestion, displayString, 
                            null, priorityHelper.defaultPriority + 0, context))             
            }
            return
        } 
        
        val woFilePrefix = woFile.substring(0, sepIndex + 1) 
         
        val file = new File(woFilePrefix)
        if (file.exists && file.directory) {
            for (f : file.listFiles.filter[ acceptFiles || it.directory ] ) {
                val suggestion = f.path.replaceAll("\\\\", "/")
                if (isValidProposal(suggestion, woFile, context)) {
                    val displayString = new StyledString(suggestion)
                    
                    var quotedSuggestion = "\"" + FS_PREFIX + suggestion
                    var priority =  0
                    if (f.directory) {
                        quotedSuggestion = quotedSuggestion + "/"
                        priority = 1
                    } 
                    
                    acceptor.accept(doCreateProposal(quotedSuggestion, displayString, 
                            null, priorityHelper.defaultPriority + priority, context))
               } 
            }
        }
    }
    
    private def workspaceSuggestions(ContentAssistContext context, ICompletionProposalAcceptor acceptor, boolean acceptFiles) {
        
        val wsRoot = ResourcesPlugin.workspace.root
        // remove quotes
        val stripped = context.prefix.replaceAll("^\"|\"$", "");
        
        // extract a valid path (or at least what we think might be a valid path)
        val sepIndex = stripped.lastIndexOf("/") 
        val prefix = if (sepIndex != -1 && stripped.length > 0) stripped.substring(0, sepIndex + 1) else "/" 
        
        val container = wsRoot.findMember(prefix)
        if (container != null && container instanceof IContainer) {
            for (member : (container as IContainer).members) {
                if (acceptFiles || member instanceof IContainer) {
                    val suggestion = member.fullPath.toString
                    if (isValidProposal(suggestion, stripped, context)) {
                        val displayString = new StyledString(suggestion)

                        var quotedSuggestion = "\"" + suggestion
                        var priority =  0
                        if (member instanceof IContainer) {
                            quotedSuggestion = quotedSuggestion + "/"
                            priority = 1
                        }

                        acceptor.accept(doCreateProposal(quotedSuggestion, displayString, 
                            null, priorityHelper.defaultPriority + priority, context))
                    }
                }
            }
        }
    }
     
    override completeRangeJob_RangeAnalysisComponent(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        super.completeRangeJob_RangeAnalysisComponent(model, assignment, context, acceptor)
        if (model instanceof RangeJob) {
            val analysis = (model as RangeJob).rangeAnalysis
            if (analysis != null) {
                val analysisData = AnalysisService.getInstance.getAnalysis(analysis.name)
                if (analysisData != null) {
                    
                    if (analysisData == null || analysisData.components.isEmpty) {
                        acceptor.accept(doCreateProposal("0", new StyledString("0"), null, priorityHelper.defaultPriority, context))
                    }
                    
                    var i = 0
                    for (c : analysisData.components) {
                        val suggestion = i + ""
                        val displayString = new StyledString(i + " - " + c.first + " " + c.second)
                        acceptor.accept(doCreateProposal(suggestion, displayString, null, priorityHelper.defaultPriority, context))
                        i++
                    }
                }
            }
        }
    }

}
