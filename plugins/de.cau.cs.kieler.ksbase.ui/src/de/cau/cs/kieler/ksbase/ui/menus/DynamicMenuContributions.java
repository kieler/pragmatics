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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasEMenuContribution;
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.core.TransformationFrameworkFactory;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.m2m.TransformationDescriptor;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;
import de.cau.cs.kieler.ksbase.ui.kivi.IKSBasEHandler;
import de.cau.cs.kieler.ksbase.ui.kivi.KSBasECombination;
import de.cau.cs.kieler.ksbase.ui.m2m.XtendTransformationContext;

/**
 * Creates menus for all registered editor transformation settings and contributes them when
 * starting an eclipse instance.
 * 
 * @author mim, ckru
 */
public final class DynamicMenuContributions {

    /** DynamicMenuContribution instance. **/
    public static final DynamicMenuContributions INSTANCE = new DynamicMenuContributions();

    /**
     * Default constructor.
     */
    private DynamicMenuContributions() {

    }

    /**
     * Expression to determine whether a ksbase transformation is visible or not. To do this we try
     * to map the selection to the parameters of the transformation, if that fails the
     * transformation is not visible.
     * 
     * @author ckru
     * 
     */
    private class KsbaseVisibilityExpression extends Expression {

        /**
         * The transformation this expression belongs to.
         */
        private KSBasETransformation transformation;

        /**
         * The editor settings the transformation belongs to.
         */
        private EditorTransformationSettings editorSettings;

        /**
         * The transactional editing domain used to execute the validation.
         */
        // private TransactionalEditingDomain transDomain = null;

        /**
         * A cache of already evaluated validations for better performance. All
         * KsbaseVisibilityExpression share an instance of this.
         */
        private HashMap<String, HashMap<List<Object>, Boolean>> validationCache = null;

        private IKSBasEHandler activeHandler = null;

        /**
         * Constructs a new expression evaluating the visibility of an transformation by matching
         * the current selection and evaluation a validation method.
         * 
         * @param transformation
         *            the transformation this expression belongs to.
         * @param editorSettings
         *            the editor settings the transformation belongs to
         * @param validationCache
         *            a cache of evaluated evaluations for better performance.
         */
        public KsbaseVisibilityExpression(final KSBasETransformation transformation,
                final EditorTransformationSettings editorSettings,
                final HashMap<String, HashMap<List<Object>, Boolean>> validationCache) {
            this.transformation = transformation;
            this.editorSettings = editorSettings;
            this.validationCache = validationCache;
        }

        /**
         * This method will evaluate the validation method belonging to the transformation of this
         * expression.
         * 
         * @param selectionMapping
         *            a mapping of the current selection. Hopefully fits the parameters of the
         *            validation method.
         * @return true if validation evaluated positive false if negative or an error occured.
         */
        private boolean evaluateValidation(final List<Object> selectionMapping) {
            if (transformation.getValidation() != null) {
                // there might be a number of validations separated by ","
                String[] validations = transformation.getValidation().split(",");
                boolean result = true;
                for (String val : validations) {
                    // if validation has been evaluated under the same circumstances so don't do it
                    // again
                    HashMap<List<Object>, Boolean> cache = validationCache.get(val);
                    if (cache != null) {
                        Boolean cachedResult = cache.get(selectionMapping);
                        if (cachedResult != null) {
                            if (!cachedResult) {
                                return false;
                            } else {
                                // we have true cached so don't evaluate again and try the
                                // next validation instead
                                continue;
                            }
                        }
                    } else {
                        validationCache.put(val, new HashMap<List<Object>, Boolean>());
                    }
                    // execute the validation
                    if ((val != null)
                            && (!val.isEmpty())
                            && (activeHandler != null && (activeHandler.getEditingDomain() != null))) {
                        TransformationDescriptor descriptor =
                                new TransformationDescriptor(val, selectionMapping.toArray());
                        XtendTransformationContext context =
                                new XtendTransformationContext(
                                        editorSettings.getTransformationFile(), editorSettings
                                                .getModelPackages().toArray(
                                                        new String[editorSettings
                                                                .getModelPackages().size()]), null,
                                        activeHandler.getEditingDomain());
                        context.execute(descriptor);
                        Object valResult = descriptor.getResult();
                        if (valResult instanceof Boolean) {
                            cache = validationCache.get(val);
                            cache.put(selectionMapping, (Boolean) valResult);
                            if (!(Boolean) result) {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                return result;
            }
            // transformation has no validation so return true
            return true;
        }

        /**
         * Helper method for xtend2 to bring the current selection to a form we can easier pass as
         * parameters.
         * 
         * @param selection
         *            the current selection
         * @return the current selection of a hashmap with type as key and proposed parameter as
         *         value
         */
        @SuppressWarnings("unchecked")
        private HashMap<Object, Object> getSelectionHash(final List<EObject> selection) {
            HashMap<Object, Object> selectionCache = new HashMap<Object, Object>();
            for (EObject obj : selection) {
                Object cache = selectionCache.get(obj.getClass());
                List<Object> listCache;
                if (cache == null) {
                    listCache = new LinkedList<Object>();
                    selectionCache.put(obj.getClass(), listCache);
                    listCache.add(obj);
                } else if (cache instanceof List<?>) {
                    listCache = (List<Object>) cache;
                    listCache.add(obj);
                }
            }
            return selectionCache;
        }

        /**
         * This method tries to get the current selection.
         * 
         * @param context
         *            the evaluation context in which this expression was executed.
         * @return A list of the EObjects currently selected in the editor.
         */
        private List<EObject> getCurrentSelection(final IEvaluationContext context) {

            // get a fitting handler and the selection
            activeHandler = KSBasEUIPlugin.getDefault().getFittingKSBasEHandler(context);
            if (activeHandler != null) {
                return activeHandler.getSelection(context);
            }
            
            return null;
        }

        /**
         * A helper method for xtend2 to determine whether an object matches a certain type.
         * 
         * @param a
         *            the type. Likely the type of a parameter.
         * @param b
         *            the objects whose matchability to test
         * @return true if a matches the type of b else false
         */
        private boolean match(final Type a, final Object b) {
            if (a instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) a).getRawType();
                if (rawType instanceof Class) {
                    Class<?> c1 = (Class<?>) rawType;
                    Class<?> c2 = b.getClass();
                    if (c1.isAssignableFrom(c2)) {
                        // if its a list also check generics
                        if (c1.isAssignableFrom(List.class) && b instanceof List) {
                            for (Type actualType : ((ParameterizedType) a).getActualTypeArguments()) {
                                if (actualType instanceof Class) {
                                    Class<?> c3 = (Class<?>) actualType;
                                    Class<?> c4 = ((List<?>) b).get(0).getClass();
                                    if (c3.isAssignableFrom(c4)) {
                                        return true;
                                    }
                                }
                            }

                        } else {
                            return true;
                        }
                    }
                }
            } else if (a instanceof Class) {
                Class<?> c1 = (Class<?>) a;
                Class<?> c2 = b.getClass();
                if (c1.isAssignableFrom(c2)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Helper method for xtend2 to find a method of a certain name that matches the current
         * selection.
         * 
         * @param selectionHash
         *            the current selection
         * @param name
         *            the name of the method
         * @return the found method as well as the parameters to be given to it. null if no fitting
         *         # method is found
         */
        private Pair<Method, List<Object>> findMethod(final HashMap<Object, Object> selectionHash,
                final String name) {
            Method method = null;
            List<Object> params = new LinkedList<Object>();
            for (Method m : transformation.getTransformationClass().getClass().getMethods()) {
                if (m.getName().equals(name)) {
                    params = new LinkedList<Object>();
                    method = m;
                    // int index = 0;
                    int parameterindex = 0;
                    // Its a clone, of course the types fit.
                    @SuppressWarnings("unchecked")
                    HashMap<Object, Object> selectionValidationCache =
                            (HashMap<Object, Object>) selectionHash.clone();
                    for (Type t : m.getGenericParameterTypes()) {
                        Object param = null;
                        if (selectionValidationCache.isEmpty()) {
                            method = null;
                            break;
                        }
                        for (Object p : selectionHash.values()) {
                            if (match(t, p) && !params.contains(p)) {
                                param = p;
                                if ((p instanceof List<?>) && (!((List<?>) p).isEmpty())) {
                                    selectionValidationCache
                                            .remove(((List<?>) p).get(0).getClass());
                                } else {
                                    selectionValidationCache.remove(p.getClass());
                                }
                                break;
                            } else if ((p instanceof List<?>) && (!((List<?>) p).isEmpty())
                                    && match(t, ((List<?>) p).get(0))) {
                                param = ((List<?>) p).get(0);
                                if (((List<?>) selectionValidationCache.get(((List<?>) p).get(0)
                                        .getClass())).size() == 1) {
                                    selectionValidationCache
                                            .remove(((List<?>) p).get(0).getClass());
                                } else {
                                    ((List<?>) selectionValidationCache.get(((List<?>) p).get(0)
                                            .getClass())).remove(param);
                                }
                                // index++;
                                break;
                            }
                        }
                        if (param != null) {
                            params.add(param);
                        } else {
                            method = null;
                        }
                        if ((parameterindex + 1) == m.getGenericParameterTypes().length) {
                            if (!selectionValidationCache.isEmpty()) {
                                method = null;
                            }
                        }
                        parameterindex++;

                    }

                    if (method != null) {
                        break;
                    }

                }
            }
            return new Pair<Method, List<Object>>(method, params);
        }

        @Override
        public EvaluationResult evaluate(final IEvaluationContext context) throws CoreException {
            List<EObject> selection = getCurrentSelection(context);
            if (selection != null && transformation != null) {
                // this is an xtend2 transformation
                if (transformation.getTransformationClass() != null) {
                    HashMap<Object, Object> selectionHash = this.getSelectionHash(selection);
                    Method method =
                            this.findMethod(selectionHash, transformation.getTransformation())
                                    .getFirst();
                    boolean validationResult = true;
                    // evaluate validations
                    if (transformation.getValidation() != null) {
                        String[] validations = transformation.getValidation().split(",");
                        for (String val : validations) {
                            Pair<Method, List<Object>> validation =
                                    this.findMethod(selectionHash, val);
                            try {
                                if (validation.getFirst() == null) {
                                    // validation method matching the current parameter not found
                                    validationResult = false;
                                } else {
                                    Object res =
                                            validation.getFirst().invoke(
                                                    transformation.getTransformationClass(),
                                                    validation.getSecond().toArray());
                                    if (res instanceof Boolean) {
                                        if (!(Boolean) res) {
                                            validationResult = (Boolean) res;
                                        }
                                    }
                                }
                            } catch (IllegalArgumentException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }

                    if (method != null && validationResult) {
                        return EvaluationResult.TRUE;
                    }
                    // this is an xtend1 transformation
                } else {
                    List<Object> selectionMapping = null;
                    for (List<String> parameters : transformation.getParameterList()) {
                        selectionMapping =
                                TransformationFrameworkFactory.getDefaultTransformationFramework()
                                        .createParameterMapping(selection,
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
        HashMap<String, HashMap<List<Object>, Boolean>> validationCache =
                new HashMap<String, HashMap<List<Object>, Boolean>>();
        for (KSBasEMenuContribution contrib : editorSettings.getMenuContributions()) {
            for (String command : contrib.getCommands()) {
                if (!command.endsWith("_SEPARATOR")) {
                    KSBasETransformation transformation =
                            editorSettings.getTransformationById(command);
                    if (transformation == null) {
                        continue;
                    }
                    Expression visibility =
                            new KsbaseVisibilityExpression(transformation, editorSettings,
                                    validationCache);
                    KSBasECombination combination = new KSBasECombination(editorSettings);
                    ImageDescriptor icon = null;
                    if (transformation.getIcon() != null && !transformation.getIcon().equals("")) {
                        icon =
                                KSBasEUIPlugin.imageDescriptorFromPlugin(editorSettings
                                        .getContributor().getName(), transformation.getIcon());
                    }
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
                    if (contrib.getData().startsWith("menu:")) {
                        KSBasEMenuContributionService.INSTANCE.addToolbarButton(combination,
                                command + ".menu", transformation.getName(),
                                transformation.getToolTip(), icon, SWT.PUSH,
                                KSBasEMenuContributionService.LocationScheme.MENU, visibility,
                                keySequence, editorSettings.getContext(),
                                editorSettings.getEditorId());
                        combination.addTransformation(command + ".menu", transformation);
                    } else if (contrib.getData().startsWith("toolbar:")) {
                        KSBasEMenuContributionService.INSTANCE.addToolbarButton(combination,
                                command + ".toolbar", transformation.getName(),
                                transformation.getToolTip(), icon, SWT.PUSH,
                                KSBasEMenuContributionService.LocationScheme.TOOLBAR, visibility,
                                null, null, editorSettings.getEditorId());
                        combination.addTransformation(command + ".toolbar", transformation);
                    } else if (contrib.getData().startsWith("popup:")) {
                        KSBasEMenuContributionService.INSTANCE.addToolbarButton(combination,
                                command + ".popup", transformation.getName(),
                                transformation.getToolTip(), icon, SWT.PUSH,
                                KSBasEMenuContributionService.LocationScheme.POPUP, visibility,
                                null, null, editorSettings.getEditorId());
                        combination.addTransformation(command + ".popup", transformation);
                        // chsch: insertion due to KIELER-2281
                    } else if (contrib.getData().startsWith("templates:")) {
                        KSBasEMenuContributionService.INSTANCE.addToolbarButton(combination,
                                command + ".template", transformation.getName(),
                                transformation.getToolTip(), icon, SWT.PUSH,
                                KSBasEMenuContributionService.LocationScheme.TEMPLATES_MENU,
                                visibility, null, null, editorSettings.getEditorId());
                        combination.addTransformation(command + ".popup", transformation);
                        // insertion end
                    } else if (contrib.getData().startsWith("popupbar:")) {
                        // TODO popup balloons are currently deactivated
                        // KSbasEBalloonPopup contribution = new KSbasEBalloonPopup();
                        // HashMap<String, String> params = new HashMap<String, String>();
                        // params.put("editorId", editorSettings.getEditorId());
                        // params.put("transformationId", transformation.getTransformationId());
                        //
                        // contribution.init(params);
                        // this.balloonContributions.add(contribution);
                    } else if (contrib.getData().startsWith("custom:")) {
                        combination
                                .addTransformation(transformation.getCommandId(), transformation);
                    }
                } else {
                    if (/* !(contrib.getCommands().indexOf(command) == 0) */true) {

                        String separatedCommand =
                                contrib.getCommands().get(
                                        contrib.getCommands().indexOf(command) + 1);
                        KSBasETransformation separatedTransformation =
                                editorSettings.getTransformationById(separatedCommand);

                        if (contrib.getData().startsWith("menu:")) {
                            KSBasEMenuContributionService.INSTANCE.addSeparator(separatedCommand
                                    + ".menu" + ".separator",
                                    KSBasEMenuContributionService.LocationScheme.MENU,
                                    new KsbaseVisibilityExpression(separatedTransformation,
                                            editorSettings, validationCache), editorSettings
                                            .getEditorId());
                        } else if (contrib.getData().startsWith("toolbar:")) {
                            KSBasEMenuContributionService.INSTANCE.addSeparator(separatedCommand
                                    + ".toolbar" + ".separator",
                                    KSBasEMenuContributionService.LocationScheme.TOOLBAR,
                                    new KsbaseVisibilityExpression(separatedTransformation,
                                            editorSettings, validationCache), editorSettings
                                            .getEditorId());
                        } else if (contrib.getData().startsWith("popup:")) {
                            KSBasEMenuContributionService.INSTANCE.addSeparator(separatedCommand
                                    + ".popup" + ".separator",
                                    KSBasEMenuContributionService.LocationScheme.POPUP,
                                    new KsbaseVisibilityExpression(separatedTransformation,
                                            editorSettings, validationCache), editorSettings
                                            .getEditorId());
                            // chsch: insertion due to KIELER-2281
                        } else if (contrib.getData().startsWith("templates:")) {
                            KSBasEMenuContributionService.INSTANCE.addSeparator(separatedCommand
                                    + ".template" + ".separator",
                                    KSBasEMenuContributionService.LocationScheme.TEMPLATES_MENU,
                                    new KsbaseVisibilityExpression(separatedTransformation,
                                            editorSettings, validationCache), editorSettings
                                            .getEditorId());
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
     * Creates a valid plug-in project for each editor and injects it to the eclipse run-time.
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
