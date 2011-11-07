/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.ui.menus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService;
import de.cau.cs.kieler.core.model.gmf.policies.IBalloonContribution;
import de.cau.cs.kieler.core.model.m2m.TransformationDescriptor;
import de.cau.cs.kieler.core.model.xtend.m2m.XtendTransformationContext;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasEMenuContribution;
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.core.TransformationFrameworkFactory;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;
import de.cau.cs.kieler.ksbase.ui.kivi.KSBasECombination;
import de.cau.cs.kieler.ksbase.ui.kivi.UpdateVisibilityCombination;

/**
 * Creates menus for all registered editor transformation settings and
 * contributes them when starting an eclipse instance.
 * 
 * @author mim, ckru
 * 
 * @kieler.rating 2009-12-15 proposed yellow
 */
public final class DynamicMenuContributions {

    /** DynamicMenuContribution instance. **/
    public static final DynamicMenuContributions INSTANCE = new DynamicMenuContributions();

    private List<IBalloonContribution> balloonContributions = new LinkedList<IBalloonContribution>();
    
    public List<IBalloonContribution> getBalloonContributions() {
        return this.balloonContributions;
    }
    
    
    /**
     * Default constructor.
     */
    private DynamicMenuContributions() {

    }

    /**
     * Expression to determine whether a ksbase transformation is visible or not.
     * To do this we try to map the selection to the parameters of the transformation, if that
     * fails the transformation is not visible.
     * 
     * @author ckru
     * 
     */
    private class KsbaseVisibilityExpression extends Expression {

        private KSBasETransformation transformation;

        private EditorTransformationSettings editorSettings;

        private TransactionalEditingDomain transDomain = null;
        
        private HashMap<String, HashMap<List<Object>, Boolean>> validationCache = null; 

        public KsbaseVisibilityExpression(final KSBasETransformation transformation,
                final EditorTransformationSettings editorSettings, 
                final HashMap<String, HashMap<List<Object>, Boolean>> validationCache) {
            this.transformation = transformation;
            this.editorSettings = editorSettings;
            this.validationCache = validationCache;
        }

        private boolean evaluateValidation(final List<Object> selectionMapping) {
            final String val = transformation.getValidation();
            HashMap<List<Object>, Boolean> cache = validationCache.get(val);
            if (cache != null) {
                Boolean cachedResult = cache.get(selectionMapping);
                if (cachedResult != null) {
                    return cachedResult;
                }                
            } else {
                validationCache.put(val, new HashMap<List<Object>, Boolean>());
            }
            
            if ((val != null) && (!val.isEmpty()) && (transDomain != null)) {
                TransformationDescriptor descriptor = new TransformationDescriptor(
                        transformation.getValidation(), selectionMapping.toArray());
                XtendTransformationContext context = new XtendTransformationContext(
                        editorSettings.getTransformationFile(), editorSettings.getModelPackages()
                                .toArray(new String[editorSettings.getModelPackages().size()]),
                        null, transDomain);
                context.execute(descriptor);
                Object result = descriptor.getResult();
                if (result instanceof Boolean) {
                    cache = validationCache.get(val);
                    cache.put(selectionMapping, (Boolean) result);
                    return (Boolean) result;
                } else {
                    return false;
                }
            }
            return true;
        }

        private List<EObject> getCurrentSelection(final IEvaluationContext context) {
            Object defaultVar = context.getDefaultVariable();
            if (defaultVar instanceof List) {
                if (!((List<?>) defaultVar).isEmpty()) {
                    Object realvar = ((List<?>) defaultVar).get(0);
                    IDiagramEditDomain domain = null;
                    if (realvar instanceof DiagramEditPart) {
                        DiagramEditPart diagramPart = (DiagramEditPart) realvar;
                        domain = diagramPart.getDiagramEditDomain();
                    } else if (realvar instanceof EditPart) {
                        EditPart part = (EditPart) realvar;
                        EditDomain editDomain = part.getRoot().getViewer().getEditDomain();
                        if (editDomain instanceof IDiagramEditDomain) {
                            domain = (IDiagramEditDomain) editDomain;
                        }
                    }

                    if (domain != null && domain instanceof DiagramEditDomain) {
                        IEditorPart editor = ((DiagramEditDomain) domain).getEditorPart();
                        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;
                        this.transDomain = diagramEditor.getEditingDomain();
                        List<EditPart> selectedParts = diagramEditor.getDiagramGraphicalViewer()
                                .getSelectedEditParts();
                        EditPart root = diagramEditor.getDiagramGraphicalViewer().getRootEditPart();
                        IGraphicalEditPart groot = (IGraphicalEditPart) root.getChildren().get(0);
                        EObject rootObject = groot.getNotationView().getElement();

                        // get the current selection
                        List<EObject> selectionList = new ArrayList<EObject>();
                        for (EditPart part : selectedParts) {
                            if (part instanceof IGraphicalEditPart) {
                                IGraphicalEditPart gpart = (IGraphicalEditPart) part;
                                selectionList.add(gpart.getNotationView().getElement());
                            }
                        }
                        // if the selection is empty assume the root object as selected
                        if (selectionList.isEmpty()) {
                            selectionList.add(rootObject);
                        }
                        return selectionList;
                    }
                }
            }
            return null;
        }

        
        @Override
        public EvaluationResult evaluate(final IEvaluationContext context) throws CoreException {
            
            List<EObject> selection = getCurrentSelection(context);
            if (selection != null) {
                List<Object> selectionMapping = null;
                for (List<String> parameters : transformation.getParameterList()) {
                    selectionMapping = TransformationFrameworkFactory
                            .getDefaultTransformationFramework().createParameterMapping(selection,
                                    parameters.toArray(new String[parameters.size()]));
                }
                if (selectionMapping == null) {
                    return EvaluationResult.FALSE;
                } else {
                    boolean validation = this.evaluateValidation(selectionMapping);
                    if (validation) {
                        return EvaluationResult.TRUE;
                    } else {
                        return EvaluationResult.FALSE;
                    }
                }
            }
            return EvaluationResult.FALSE;
        }
    }

    /**
     * Creates a menu for an editor.
     * 
     * @param editorSettings
     *            The editor to create the menu for.
     */
    public void createMenuForEditor(final EditorTransformationSettings editorSettings) {
        Assert.isNotNull(editorSettings);
        UpdateVisibilityCombination com = new UpdateVisibilityCombination();
        com.setActive(true);
        HashMap<String, HashMap<List<Object>, Boolean>> validationCache = new HashMap<String, HashMap<List<Object>, Boolean>>();
        for (KSBasEMenuContribution contrib : editorSettings.getMenuContributions()) {
            for (String command : contrib.getCommands()) {
                if (!command.endsWith("_SEPARATOR")) {
                    KSBasETransformation transformation = editorSettings
                            .getTransformationById(command);
                    Expression visibility = new KsbaseVisibilityExpression(transformation,
                            editorSettings, validationCache);
                    KSBasECombination combination = new KSBasECombination(editorSettings);
                    ImageDescriptor icon = null;
                    icon = KSBasEUIPlugin.imageDescriptorFromPlugin(editorSettings.getContributor()
                            .getName(), transformation.getIcon());
                    KeySequence keySequence = null;

                    try {
                        String shortcut = transformation.getKeyboardShortcut();
                        keySequence = KeySequence.getInstance(shortcut);
                        if (keySequence.isEmpty()) {
                            keySequence = null;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String con = contrib.getData();
                    if (contrib.getData().startsWith("menu:")) {
                        KiviMenuContributionService.INSTANCE.addToolbarButton(combination, command
                                + ".menu", transformation.getName(), transformation.getToolTip(),
                                icon, SWT.PUSH, KiviMenuContributionService.LocationScheme.MENU,
                                visibility, keySequence, editorSettings.getContext(),
                                editorSettings.getEditorId());
                        combination.addTransformation(command + ".menu", transformation);
                    } else if (contrib.getData().startsWith("toolbar:")) {
                        KiviMenuContributionService.INSTANCE.addToolbarButton(combination, command
                                + ".toolbar", transformation.getName(),
                                transformation.getToolTip(), icon, SWT.PUSH,
                                KiviMenuContributionService.LocationScheme.TOOLBAR, visibility,
                                null, null, editorSettings.getEditorId());
                        combination.addTransformation(command + ".toolbar", transformation);
                    } else if (contrib.getData().startsWith("popup:")) {
                        KiviMenuContributionService.INSTANCE.addToolbarButton(combination, command
                                + ".popup", transformation.getName(), transformation.getToolTip(),
                                icon, SWT.PUSH, KiviMenuContributionService.LocationScheme.POPUP,
                                visibility, null, null, editorSettings.getEditorId());
                        combination.addTransformation(command + ".popup", transformation);
                    } else if (contrib.getData().startsWith("popupbar:")) {
                        KSbasEBalloonPopup contribution = new KSbasEBalloonPopup();
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("editorId", editorSettings.getEditorId());
                        params.put("transformationId", transformation.getTransformationId());
                        
                        contribution.init(params);
                        this.balloonContributions.add(contribution);
                    }
                } else {
                    if (!(contrib.getCommands().indexOf(command) == 0)) {

                        String separatedCommand = contrib.getCommands().get(
                                contrib.getCommands().indexOf(command) + 1);
                        KSBasETransformation separatedTransformation = editorSettings
                                .getTransformationById(separatedCommand);

                        if (contrib.getData().startsWith("menu:")) {
                            KiviMenuContributionService.INSTANCE.addSeparator(separatedCommand
                                    + ".menu" + ".separator",
                                    KiviMenuContributionService.LocationScheme.MENU,
                                    new KsbaseVisibilityExpression(separatedTransformation,
                                            editorSettings, validationCache), editorSettings.getEditorId());
                        } else if (contrib.getData().startsWith("toolbar:")) {
                            KiviMenuContributionService.INSTANCE.addSeparator(separatedCommand
                                    + ".toolbar" + ".separator",
                                    KiviMenuContributionService.LocationScheme.TOOLBAR,
                                    new KsbaseVisibilityExpression(separatedTransformation,
                                            editorSettings, validationCache), editorSettings.getEditorId());
                        } else if (contrib.getData().startsWith("popup:")) {
                            KiviMenuContributionService.INSTANCE.addSeparator(separatedCommand
                                    + ".popup" + ".separator",
                                    KiviMenuContributionService.LocationScheme.POPUP,
                                    new KsbaseVisibilityExpression(separatedTransformation,
                                            editorSettings, validationCache), editorSettings.getEditorId());
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates all menu contributions for all existing editors.
     */
    public void createAllMenuContributions() {
        createMenuForEditors(TransformationManager.INSTANCE.getEditors());
    }

    /**
     * Creates a valid plug-in project for each editor and injects it to the
     * eclipse run-time.
     * 
     * @param collection
     *            The list of editors to create the menu for
     */
    public void createMenuForEditors(final Collection<EditorTransformationSettings> collection) {
        if (collection != null) {
            // Iterate through editors and create extension point contents
            for (EditorTransformationSettings editor : collection) {
                createMenuForEditor(editor);
            }
        }
    }
}
