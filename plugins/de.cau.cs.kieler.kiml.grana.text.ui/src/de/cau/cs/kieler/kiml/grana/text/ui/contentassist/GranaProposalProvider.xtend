/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.text.ui.contentassist

import com.google.inject.Inject
import de.cau.cs.kieler.core.kgraph.PersistentEntry
import de.cau.cs.kieler.kiml.LayoutMetaDataService
import de.cau.cs.kieler.kiml.LayoutOptionData.Type
import de.cau.cs.kieler.kiml.grana.AnalysisService
import de.cau.cs.kieler.kiml.grana.text.services.GranaGrammarAccess
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.ui.LayoutOptionLabelProvider
import java.io.File
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.emf.ecore.EObject
import org.eclipse.jface.viewers.StyledString
import org.eclipse.swt.graphics.Image
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
    
    @Inject
    private GranaGrammarAccess grammarAccess;
    
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
    
    /**
     * Proposals for layout option keys.
     */
    override completePersistentEntry_Key(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        
        // create and register the completion proposal for every element in the list
        for (optionData : LayoutMetaDataService.instance.optionData) {
            val displayString = new StyledString(optionData.toString(),
                            if (optionData.isAdvanced()) StyledString.COUNTER_STYLER else null)
                            
            displayString.append(" (" + optionData.getId() + ")", StyledString.QUALIFIER_STYLER)

            val proposal = getValueConverter().toString(optionData.getId(),
                                grammarAccess.getQualifiedIDRule().getName())

            val labelProvider = new LayoutOptionLabelProvider(optionData)
            val image = labelProvider.getImage(optionData.getDefault())

            handleKeyProposal(context, acceptor, optionData.getId(), proposal, displayString, image);
        }
    }
    
    /**
     * Copied from {@link KGraphProposalProvider}.
     */
    private def handleKeyProposal(ContentAssistContext context,
            ICompletionProposalAcceptor acceptor, String id, String proposal,
            StyledString displayString, Image image) {
        
        if (isValidProposal(proposal, context.getPrefix(), context)) {
            // accept the proposal with unmodified prefix
            acceptor.accept(doCreateProposal(proposal, displayString, image, getPriorityHelper()
                    .getDefaultPriority(), context));
        } else {
            val lastDotIndex = id.lastIndexOf('.');
            if (lastDotIndex >= 0) {
                // accept the proposal with enhanced prefix
                val prefix =
                        new StringBuilder(id.substring(0, lastDotIndex + 1))
                prefix.append(context.getPrefix())
                // add escape characters as required
                var i = 0
                while (i < proposal.length && i < prefix.length) {
                    
                    if (proposal.charAt(i) != prefix.charAt(i)) {
                        if (proposal.charAt(i) == '^') {
                            prefix.insert(i, '^');
                        } else {
                            // break
                            i = proposal.length
                        }
                    }
                    
                    i = i + 1
                }
                if (isValidProposal(proposal, prefix.toString(), context)) {
                    // accept the proposal with unmodified prefix
                    acceptor.accept(doCreateProposal(proposal, displayString, image,
                            getPriorityHelper().getDefaultPriority(), context));
                }
            }
        }
    }
    
    override completePersistentEntry_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        super.completePersistentEntry_Value(model, assignment, context, acceptor)
        
        valueProposal(context, acceptor)
    }
    
    /**
     * Copied from {@link KGraphProposalProvider}.
     * 
     * Computes the annotation value proposals based on a foregoing layout parameter.
     * 
     * @param context Xtext API
     * @param acceptor Xtext API
     * 
     * @author sgu, chsch
     */
    private def valueProposal(ContentAssistContext context,
            ICompletionProposalAcceptor acceptor) {
        // check if the prefix is a KIELER annotation
        if (context.getCurrentModel() instanceof PersistentEntry) {

            val annotationName = (context.getCurrentModel() as PersistentEntry).getKey();
            if (!annotationName.nullOrEmpty) {

                // get the option list
                val layoutServices = LayoutMetaDataService.getInstance();

                // find the specific option an display all possible values
                var optionData = layoutServices.getOptionData(annotationName);
                
                // if option data is null, try to add the kieler prefix
                if (optionData == null) {
                    optionData = layoutServices.getOptionData("de.cau.cs.kieler." + annotationName);
                }
                
                val theType = if (optionData != null) optionData.getType() else Type.UNDEFINED;
                var String proposal = null;
                
                switch (theType) {
                // show the available choices for boolean and enumeration/
                case Type.BOOLEAN,
                case Type.ENUM,
                case Type.ENUMSET:
                    for ( j : 0..< optionData.getChoices().length) {
                        proposal = optionData.choices.get(j)
                        acceptor.accept(createCompletionProposal(proposal, context));
                    }

                // for string, float, integer and object show the type of the value and give a
                //  corresponding default value
                case theType == Type.STRING && annotationName.equals(LayoutOptions.ALGORITHM.getId()): {
                    var String displayString = null;
                    for (data : layoutServices.getAlgorithmData()) {
                        proposal = '"' + data.getId() + '"';
                        displayString = data.getName();
                        acceptor.accept(createCompletionProposal(proposal, displayString, null,
                                context));
                    }
                }

                // fall through if STRING but above predicate is false

                //case Type.FLOAT:
                //case Type.INT:
                //case Type.OBJECT:
                case Type.STRING,
                case Type.FLOAT,
                case Type.INT,
                case Type.OBJECT: {
                    // chose the corresponding default value
                    switch (theType) {
                        case Type.STRING:
                            proposal = "\"\""
                        case Type.FLOAT:
                            proposal = "0.0"
                        case Type.INT:
                            proposal = "0"
                        case Type.OBJECT: {
                            try {
                                proposal = "\""
                                        + optionData.getOptionClass().newInstance().toString()
                                        + "\"";
                            } catch (InstantiationException e) {
                                proposal = "\"\"";
                            } catch (IllegalAccessException e) {
                                proposal = "\"\"";
                            }
                        }
                        default: 
                        	proposal = ""
                    }
                    acceptor.accept(createCompletionProposal(proposal, optionData.getType()
                            .toString(), null, context));
                }
                }
            }
        }
    }
 
    // some constants for the following code completions for workspace navigation 
    val FS_PREFIX = "file://"
    val SUG_WORKSPACEROOT = "\"/"
    val DSP_WORKSPACE_ROOT = new StyledString("Workspace Root")
    val SUG_FILESYSTEM = "\"" + FS_PREFIX + "/"
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
        val prefix = stripped.substring(0, sepIndex)
        val container = wsRoot.findMember(prefix)
        
        if (container != null && container instanceof IContainer) {
            for (member : (container as IContainer).members) {
                if (acceptFiles || member instanceof IContainer) {
                    val suggestion = member.fullPath.toString
                    if (isValidProposal(suggestion, stripped, context)) {
                        val displayString = new StyledString(suggestion)

                        var quotedSuggestion = "\"" + FS_PREFIX + suggestion
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
     
    
}
