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
package de.cau.cs.kieler.grana.text.ide.contentassist

import de.cau.cs.kieler.grana.AnalysisService
import de.cau.cs.kieler.grana.text.grana.EnumRange
import de.cau.cs.kieler.grana.text.grana.Job
import de.cau.cs.kieler.grana.text.grana.RangeJob
import de.cau.cs.kieler.grana.text.services.GranaGrammarAccess
import java.io.File
import javax.inject.Inject
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.elk.core.data.LayoutMetaDataService
import org.eclipse.elk.core.data.LayoutOptionData
import org.eclipse.elk.graph.ElkNode
import org.eclipse.elk.graph.text.ide.contentassist.ElkGraphProposalProvider
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.ide.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ide.editor.contentassist.IIdeContentProposalAcceptor

/**
 * Content assist proposals for the Grana Text DSL. 
 * 
 * Note that the implementation below breaks with the 
 * clear separation of the '.ide' plugin and eclipse-specific code
 * (as it accesses the workspace root). 
 * 
 * To avoid this, we would have to change the way 
 * the ide proposal providers are invoked by ELK Text's ui proposal provider.
 * 
 */
class GranaIdeProposalProvider extends ElkGraphProposalProvider {
    
    @Inject
    GranaGrammarAccess grammar
    
    override protected completePropertyKey(ContentAssistContext context, IIdeContentProposalAcceptor acceptor) {
        var model = context.currentModel
        switch model {
            ElkNode: proposeProperties(
                model,
                null /* any layout alg */,
                null as LayoutOptionData.Target /* any target */,
                context,
                acceptor)
            Job: proposeProperties(
                null /* any element */,
                null /* any layout alg */,
                null as LayoutOptionData.Target /* any target */,
                context,
                acceptor)
        } 
    }
    
    override protected _createProposals(Assignment assignment, ContentAssistContext context, IIdeContentProposalAcceptor acceptor) {
        super._createProposals(assignment, context, acceptor)
        
        switch assignment {
            case grammar.analysisAccess.nameAssignment:
                completeAnalysis_Name(context, acceptor)
            case grammar.localResourceAccess.pathAssignment_0:
                completeLocalResource_Path(context, acceptor, false)
            case grammar.localOutputAccess.pathAssignment:
                completeLocalResource_Path(context, acceptor, true)
            case grammar.rangeJobAccess.rangeAnalysisComponentAssignment_12_0_2_1:
                completeRangeJob_RangeAnalysisComponent(context, acceptor)
            case grammar.enumRangeAccess.valuesAssignment_1,
            case grammar.enumRangeAccess.valuesAssignment_2_1:
                completeEnumRange_Values(context, acceptor)
            case grammar.rangeJobAccess.rangeOptionAssignment_10:
                completePropertyKey(context, acceptor)

        }
    }
    
    
    /**
     * Proposals for graph analyses. 
     */
    def completeAnalysis_Name(ContentAssistContext context, IIdeContentProposalAcceptor acceptor) {

        switch model: context.currentModel {
            Job: {
                val filteredAnalyses = AnalysisService.instance.analyses.filter[ a |
                    !model.analyses.contains(a)
                ]
                // propose all known analyses that match the current prefix
                for (a : filteredAnalyses) {
                    val displayString = new StringBuilder(a.name).append(" (").append(a.id).append(")")
                    val entry = proposalCreator.createProposal(a.id, context) [
                        label = displayString.toString
                    ]
                    acceptor.accept(entry, proposalPriorities.getDefaultPriority(entry) + 1)
                }
            }
        }
    }
    
     // some constants for the following code completions for workspace navigation 
    val FS_PREFIX = "file://"
    val SUG_WORKSPACEROOT = "\"/"
    val DSP_WORKSPACE_ROOT = "Workspace Root"
    val SUG_FILESYSTEM = "\"" + FS_PREFIX
    val DSP_FILESYSTEM = "Filesystem"
            
    def completeLocalResource_Path(ContentAssistContext context, IIdeContentProposalAcceptor acceptor, boolean acceptFiles) {
        
        // remove quotes
        val stripped = context.prefix.replaceAll("^\"|\"$", "");
        // if string is empty suggest either the root of the workspace or the root of file system
        if (stripped.nullOrEmpty) {
            val entry = proposalCreator.createProposal(SUG_WORKSPACEROOT, context) [
                label = DSP_WORKSPACE_ROOT
            ]
            acceptor.accept(entry, proposalPriorities.getDefaultPriority(entry) + 1)
            val entry2 = proposalCreator.createProposal(SUG_FILESYSTEM, context) [
                label = DSP_FILESYSTEM
            ]
            acceptor.accept(entry2, proposalPriorities.getDefaultPriority(entry) + 1)
            return
        }
        
        val isFs = stripped.startsWith(FS_PREFIX)
        if (isFs) {
            // FILESYSTEM
            filesystemSuggestions(context, acceptor, acceptFiles)    
        } else {
            // WORKSPACE
            workspaceSuggestions(context, acceptor, acceptFiles)  
        }
    }

    private def filesystemSuggestions(ContentAssistContext context, IIdeContentProposalAcceptor acceptor, boolean acceptFiles) {
          
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
                val displayString = suggestion
                var quotedSuggestion = "\"" + FS_PREFIX + suggestion
                val entry = proposalCreator.createProposal(quotedSuggestion, context) [
                    label = displayString
                ]
                acceptor.accept(entry, proposalPriorities.getDefaultPriority(entry))             
            }
            return
        } 
        
        val woFilePrefix = woFile.substring(0, sepIndex + 1) 
         
        val file = new File(woFilePrefix)
        if (file.exists && file.directory) {
            for (f : file.listFiles.filter[ acceptFiles || it.directory ] ) {
                val suggestion = f.path.replaceAll("\\\\", "/")
                val displayString = suggestion
                
                var quotedSuggestion = "\"" + FS_PREFIX + suggestion
                var priority =  0
                if (f.directory) {
                    quotedSuggestion = quotedSuggestion + "/"
                    priority = 1
                } 
                val entry = proposalCreator.createProposal(quotedSuggestion, context) [
                    label = displayString
                ]     
                acceptor.accept(entry, proposalPriorities.getDefaultPriority(entry) + priority)
            } 
        }
    }
    
    private def workspaceSuggestions(ContentAssistContext context, IIdeContentProposalAcceptor acceptor, boolean acceptFiles) {
        
        val wsRoot = ResourcesPlugin.workspace.root
        // remove quotes
        val stripped = context.prefix.replaceAll("^\"|\"$", "");
        
        // extract a valid path (or at least what we think might be a valid path)
        val sepIndex = stripped.lastIndexOf("/") 
        val prefix = if (sepIndex != -1 && stripped.length > 0) stripped.substring(0, sepIndex + 1) else "/" 
        
        val container = wsRoot.findMember(prefix)
        if (container !== null && container instanceof IContainer) {
            for (member : (container as IContainer).members) {
                if (acceptFiles || member instanceof IContainer) {  
                    val suggestion = member.fullPath.toString
                    val displayString = suggestion

                    var quotedSuggestion = "\"" + suggestion
                    var priority =  0
                    if (member instanceof IContainer) {
                        quotedSuggestion = quotedSuggestion + "/"
                        priority = 1
                    }

                    val entry = proposalCreator.createProposal(quotedSuggestion, context) [
                        label = displayString
                    ]
                    acceptor.accept(entry, proposalPriorities.getDefaultPriority(entry) + priority)
                }
            }
        }
    }

     def completeRangeJob_RangeAnalysisComponent(ContentAssistContext context, IIdeContentProposalAcceptor acceptor) {
        switch model: context.currentModel {
            RangeJob: {
                val analysis = model.rangeAnalysis
                    if (analysis !== null) {
                    val analysisData = AnalysisService.getInstance.getAnalysis(analysis.name)
                    if (analysisData !== null) {
                        
                        if (analysisData === null || analysisData.components.isEmpty) {
                            val entry = proposalCreator.createProposal("0", context)
                            acceptor.accept(entry, proposalPriorities.getDefaultPriority(entry))       
                        }
                        
                        var i = 0
                        for (c : analysisData.components) {
                            val suggestion = i + ""
                            val displayString = i + " - " + c.first + " " + c.second
                            val entry = proposalCreator.createProposal(suggestion, context) [
                                label = displayString
                            ] 
                            acceptor.accept(entry, proposalPriorities.getDefaultPriority(entry))
                            i++
                        }
                    }
                }
            }
        }
    }

    def completeEnumRange_Values(ContentAssistContext context, IIdeContentProposalAcceptor acceptor) {
        switch job: context.currentModel.eContainer {
            RangeJob: {
                val rangeVals = job.rangeValues
                val known = switch rangeVals {
                    EnumRange: rangeVals.values.map[toString].toSet
                    default: emptySet
                } 
                val opt = job.rangeOption
                if (!opt.nullOrEmpty) {
                    val optData = LayoutMetaDataService.instance.getOptionDataBySuffix(opt)
                    if (optData.type == LayoutOptionData.Type.ENUM) {
                        for (e : optData.choices) {
                            if (!known.contains(e.toString)) {
                                val entry = proposalCreator.createProposal(e, context)
                                acceptor.accept(entry, proposalPriorities.getDefaultPriority(entry))    
                            }
                        }
                    }
                }
            }
        }
    }

}