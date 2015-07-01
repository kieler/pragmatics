/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.config.text.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.util.Strings;

import com.google.inject.Inject;

import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.config.text.services.LayoutConfigGrammarAccess;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.LayoutOptionLabelProvider;

/**
 * Basically copied from the
 * {@link de.cau.cs.kieler.core.kgraph.text.ui.contentassist.KGraphProposalProvider}.
 * 
 * Small adaption in lines 110ff.
 * 
 * Care: when regenerating the grammar, a xtend version of this class will be created, just delete
 * the xtend class.
 * 
 * @author uru
 */
public class LayoutConfigProposalProvider extends AbstractLayoutConfigProposalProvider {

    @Inject
    private LayoutConfigGrammarAccess grammmarAccess;

    /**
     * {@inheritDoc}
     */
    @Override
    public void completePersistentEntry_Key(EObject model, Assignment assignment,
            ContentAssistContext context, ICompletionProposalAcceptor acceptor) {

        // super
        super.completePersistentEntry_Key(model, assignment, context, acceptor);

        // we
        LayoutMetaDataService layoutServices = LayoutMetaDataService.getInstance();

        // create and register the completion proposal for every element in the list
        for (LayoutOptionData optionData : layoutServices.getOptionData()) {
            StyledString displayString =
                    new StyledString(optionData.toString(),
                            (optionData.isAdvanced()) ? StyledString.COUNTER_STYLER : null);
            displayString.append(" (" + optionData.getId() + ")", StyledString.QUALIFIER_STYLER);

            String proposal =
                    getValueConverter().toString(optionData.getId(),
                            grammmarAccess.getQualifiedIDRule().getName());

            LayoutOptionLabelProvider labelProvider = new LayoutOptionLabelProvider(optionData);
            Image image = labelProvider.getImage(optionData.getDefault());

            boolean escape = proposal.contains("^");
            ICompletionProposal completeProposal =
                    createCompletionProposal(proposal, displayString, image, getPriorityHelper()
                            .getDefaultPriority(), "de.cau.cs.kieler." + (escape ? "^" : "")
                            + context.getPrefix(), context);

            if (completeProposal != null) {
                acceptor.accept(completeProposal);
            } else {
                acceptor.accept(createCompletionProposal(proposal, displayString, image, context));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void completePersistentEntry_Value(EObject model, Assignment assignment,
            ContentAssistContext context, ICompletionProposalAcceptor acceptor) {

        // super
        super.completePersistentEntry_Value(model, assignment, context, acceptor);

        // check if the prefix is a KIELER annotation

        // uru: there seems to be some bug with the content assist for the embedded editor
        // instead of the PersistentEntry, a KNode is passed as current model of the context
        // when opening content assist for a key
        // for this reason we peel the persistent entry out of the previous model element

        // if (context.getCurrentModel() instanceof PersistentEntry) {
        if (context.getPreviousModel() instanceof PersistentEntry) {

            String annotationName = ((PersistentEntry) context.getPreviousModel()).getKey();
            if (!Strings.isEmpty(annotationName)) {

                // get the option list
                LayoutMetaDataService layoutServices = LayoutMetaDataService.getInstance();

                // find the specific option an display all possible values
                LayoutOptionData optionData = layoutServices.getOptionData(annotationName);
                
                // if option data is null, try to add the kieler prefix
                if(optionData == null) {
                    optionData = layoutServices.getOptionData("de.cau.cs.kieler." + annotationName);
                }
                
                Type theType = (optionData != null) ? optionData.getType() : Type.UNDEFINED;
                String proposal = null;

                switch (theType) {
                // show the available choices for boolean and enumeration/
                case BOOLEAN:
                case ENUM:
                case ENUMSET:
                    for (int j = 0; j < optionData.getChoices().length; j++) {
                        proposal = optionData.getChoices()[j];
                        acceptor.accept(createCompletionProposal(proposal, context));
                    }
                    break;

                // for string, float, integer and object show the type of the value and give a
                // corresponding default value

                case STRING:
                    if (annotationName.equals(LayoutOptions.ALGORITHM.getId())) {
                        String displayString = null;
                        for (LayoutAlgorithmData data : layoutServices.getAlgorithmData()) {
                            //proposal = '"' + data.getId() + '"';
                            // the textual config does not like quotes
                            proposal = data.getId();
                            displayString = data.getName();
                            acceptor.accept(createCompletionProposal(proposal, displayString, null,
                                    context));
                        }
                        break;
                    }
                case FLOAT:
                case INT:
                case OBJECT:

                    // chose the corresponding default value
                    switch (theType) {
                    case STRING:
                        proposal = "\"\"";
                        break;
                    case FLOAT:
                        proposal = "0.0";
                        break;
                    case INT:
                        proposal = "0";
                        break;
                    case OBJECT:
                        try {
                            proposal =
                                    "\"" + optionData.getOptionClass().newInstance().toString()
                                            + "\"";
                        } catch (InstantiationException e) {
                            proposal = "\"\"";
                        } catch (IllegalAccessException e) {
                            proposal = "\"\"";
                        }
                        break;

                    default:
                        break;
                    }
                    acceptor.accept(createCompletionProposal(proposal, optionData.getType()
                            .toString(), null, context));
                    break;

                default:
                    break;
                }
            }
        }
    }

}
