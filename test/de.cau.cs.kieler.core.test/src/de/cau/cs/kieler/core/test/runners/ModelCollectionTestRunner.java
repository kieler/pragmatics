/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.test.runners;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Test;
import org.junit.runner.Runner;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * <p>
 * This class defines a JUnit4TestRunner dedicated to run tests on model data bases. It provides
 * various Java annotations:
 * <ul>
 * <li><code>Models,</code></li>
 * <li><code>BundleId,</code></li>
 * <li><code>ModelPath,</code></li>
 * <li><code>ModelFilter,</code></li>
 * <li><code>ResourceSet</code></li>
 * </ul>
 * </p>
 * <p>
 * In a test implementation the model base can be provided in two ways:
 * <ol>
 * <li>Implementing a method <code>public static Collection&lt;Object&gt; methodName() {...}</code>
 *     annotated with <code>(at)Models</code>,</li>
 * <li>Implementing <code>public static String ...</code> methods providing the bundle id, annotated
 *     with <code>(at)BundleId</code>, and the path to the models, annotated with
 *     <code>(at)ModelPath</code>.<br>
 *     In addition, a model file filter may be provided in form of a regular expression string by
 *     means of a method annotated with <code>(at)ModelFilter</code> and a special
 *     {@link org.eclipse.emf.ecore.resource.ResourceSet ResourceSet} may registered by means a
 *     method <code>public static ResourceSet ...</code> annotated with (at)ResourceSet.</li>
 * </ol>
 * 
 * The test classes may have a constructor with zero or one Argument(s) of type {@link Object} or
 * {@link EObject} in order to inject the model into the test. The same holds for the test methods
 * (annotated with <code>(at)Test</code>) - 
 * </p>
 *
 * Examples of valid test specifications:
 *<pre>
 * (at)RunWith(ModelCollectionTestRunner.class) {
 * public class Test {
 * 
 *     (at)Models
 *     public static Collection<?> getModels() {
 *         List<Object> models = Lists.newLinkedList();
 *         return models;
 *     }
 *   
 *     (at)Test
 *     public void test(EObject model) {
 *         System.out.println(((KNode) model).getData().get(0));
 *     }
 * }
 *</pre>
 *
 *<pre>
 * (at)RunWith(ModelCollectionTestRunner.class) {
 * public class Test {
 * 
 *     (at)BundleId
 *     public static String getBundleId() {
 *         return "de.cau.cs.kieler.klighd.test";
 *     }
 *    
 *     (at)ModelPath
 *     public static String getModelPath() {
 *         return "sizeEstimationTests/";
 *     }
 *    
 *     (at)ModelFilter
 *     public static String getModelFilter() {
 *         return "*.kgt";
 *     }
 *    
 *     (at)ModelCollectionTestRunner.ResourceSet
 *     public static ResourceSet getResourceSet() {
 *         return KGraphStandaloneSetup.doSetup().getInstance(XtextResourceSet.class);
 *     }
 *   
 *     (at)Test
 *     public void test(EObject model) {
 *         System.out.println(((KNode) model).getData().get(0));
 *     }
 * }
 *</pre>
 *
 * 
 * @author chsch
 */
public class ModelCollectionTestRunner extends Suite {

    /**
     * The declaration of the Java annotation 'Models', see documentation of
     * {@link ModelCollectionTestRunner} for details.
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Models {
    }

    /**
     * The declaration of the Java annotation 'BundleId', see documentation of
     * {@link ModelCollectionTestRunner} for details.
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface BundleId {
    }

    /**
     * The declaration of the Java annotation 'ModelPath', see documentation of
     * {@link ModelCollectionTestRunner} for details.
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ModelPath {
    }

    /**
     * The declaration of the Java annotation 'ModelFilter', see documentation of
     * {@link ModelCollectionTestRunner} for details.
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ModelFilter {
    }

    /**
     * The declaration of the Java annotation 'ResourceSet', see documentation of
     * {@link ModelCollectionTestRunner} for details.
     * 
     * @author chsch
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ResourceSet {
    }

    /**
     * Constructor.
     * 
     * @param clazz the tests class
     * @throws Throwable if something unexpeced happens
     */
    public ModelCollectionTestRunner(final Class<?> clazz) throws Throwable {
        super(clazz, Lists.<Runner>newLinkedList());

        // try to obtain the test models by means of a method annotated with 'Models'
        List<?> models = Lists.newLinkedList(getModelsByModelsMethod());

        // if no models are found they are most probably provided by the path-based method hooks 
        //  so try to obtain them the 2nd way
        if (models.isEmpty()) {
            models = Lists.newLinkedList(getModelsByPathMethods());
        }

        // if no models are found either then there is most probably something wrong in the
        // registration - throw a verbose exception pointing the test author precisely to the issue
        if (models.isEmpty()) {
            throw new NoSuchMethodException(
                    "Expected a public static method annotated width @Models returning a "
                    + "non-empty Collection<Object> in "
                    + getTestClass().getName()
                    + ", or methods annotated with @BundleId, @ModelPath, and @ResourceSet as "
                    + "documented in the ModelCollectionTestRunner class enabling to obtain a "
                    + "non-empty collection of models. If you chose the 2nd way, make sure the "
                    + "provided paths are correct and contain model files.");
        }

        // for each of the revealed model objects determine a name (the fragmentURI in case of
        //  EObjects) and create a related child test runner
        
        for (Object o : models) {
            String modelName = o.toString();
            if (o instanceof EObject && ((EObject) o).eResource() != null) {
                URI uri = EcoreUtil.getURI((EObject) o);
                modelName = uri.path() + uri.fragment();
            }
            this.getChildren().add(
                    new ModelCollectionTestRunnerForModels(getTestClass().getJavaClass(), o,
                            modelName));
        }
    }

    /**
     * Method tries to reveal test models by means of a method annotated with @Models. 
     * 
     * @return A Collection of models or an empty list if the attempt fails.
     */
    private Collection<Object> getModelsByModelsMethod() {
        try {

            // try to obtain the provider method:
            FrameworkMethod modelsMethod = getAnnotatedMethod(getTestClass(), Models.class);
            if (modelsMethod == null
                    || !modelsMethod.getMethod().getReturnType().isAssignableFrom(Collection.class)) {
                return Collections.emptyList();
            }
            
            // now reveal the provided information ...
            @SuppressWarnings("unchecked")
            Collection<Object> models = (Collection<Object>) modelsMethod.invokeExplosively(null);
            if (models != null) {
                // if models are available return them,
                return models;
            } else {
                // ... otherwise:
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            return Collections.emptyList();
        }
    }

    /**
     * Method tries to reveal test models by means of path denoting methods.
     * 
     * @return A Collection of models or an empty list if the attempt fails.
     */
    private Collection<Object> getModelsByPathMethods() {
        String bundleId = null;
        try {
            
            // try to obtain the mandatory and optional provider methods:
            FrameworkMethod bundleIdMethod = getAnnotatedMethod(getTestClass(), BundleId.class);
            FrameworkMethod modelPathMethod = getAnnotatedMethod(getTestClass(), ModelPath.class);
            // optional:
            FrameworkMethod resourceSetMethod = getAnnotatedMethod(getTestClass(), ResourceSet.class);
            FrameworkMethod modelFilterMethod = getAnnotatedMethod(getTestClass(), ModelFilter.class);

            // check whether the mandatory ones are available
            boolean valid = true;
            if (bundleIdMethod == null
                    || !bundleIdMethod.getMethod().getReturnType().equals(String.class)) {
                valid = false;
            } else if (modelPathMethod == null
                    || !modelPathMethod.getMethod().getReturnType().equals(String.class)) {
                valid = false;
            }
          
            // check whether the optional one is available
            boolean filtered = true;
            if (modelFilterMethod == null
                    || !modelFilterMethod.getMethod().getReturnType().equals(String.class)) {
                filtered = false;
            }
            
            // check whether a custom ResourceSet is provided
            boolean customResourceSet = true;
            if (resourceSetMethod == null
                || !resourceSetMethod.getMethod().getReturnType()
                        .isAssignableFrom(org.eclipse.emf.ecore.resource.ResourceSet.class)) {
                customResourceSet = false;
            }
            
            if (valid) {
                // now reveal the provided information ...
                bundleId = (String) bundleIdMethod.invokeExplosively(null);
                String modelPath = (String) modelPathMethod.invokeExplosively(null);
                String modelFilter = filtered ? (String) modelFilterMethod.invokeExplosively(null)
                        : null;
                final org.eclipse.emf.ecore.resource.ResourceSet set = (customResourceSet)
                        ? (org.eclipse.emf.ecore.resource.ResourceSet) resourceSetMethod
                        .invokeExplosively(null) : new ResourceSetImpl();
                
                // the final bundleId variable is needed for logging purposes later on
                final String bundleIdf = bundleId;
                
                // ... and try to access the specified path
                Enumeration<URL> urls = Platform.getBundle(bundleId).findEntries(modelPath,
                        modelFilter, true);

                if (urls == null) {
                    // if nothing is found (without a failing) return an empty list
                    return Collections.emptyList();
                }

                // if some model files are available
                //  transform the Enumeration into a list
                //  and try to load each Resource by means of the provided ResourceSet
                Iterable<? extends Object> models = Iterables.concat(Iterables.transform(
                        Collections.list(urls), new Function<URL, List<EObject>>() {
                            public List<EObject> apply(final URL url) {
                                try {
                                    final Resource r = set.getResource(
                                            URI.createURI(url.toString()), true);
                                    return r.getContents();
                                } catch (WrappedException w) {
                                    // if the resource load fails (e.g. as no
                                    //  valid ResourceFactory has been registered)
                                    //  return the empty list
                                    String message = "ModelCollectionTestRunner: Loading model"
                                            + " resource " + url.toString() + " of " + bundleIdf
                                            + " failed with the following exception:"
                                            + System.getProperty("line.separator");
                                    Platform.getLog(Platform.getBundle(bundleIdf)).log(
                                            new Status(IStatus.ERROR, bundleIdf, message, w));
                                    return Collections.emptyList();
                                }
                            }
                        }));
                // finally return the revealed models
                return ImmutableList.copyOf(models);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            if (!Strings.isNullOrEmpty(bundleId)) {
                String message = "ModelCollectionTestRunner:Loading model resources of " + bundleId
                        + "failed with the following exception:"
                        + System.getProperty("line.separator");
                Platform.getLog(Platform.getBundle(bundleId)).log(
                        new Status(IStatus.ERROR, bundleId, message, t));
            }
            return Collections.emptyList();
        }
    }

    /**
     * Gets the method annotated with an instance of 'annotationClass'.
     * 
     * @author chsch
     * 
     * @param testClass
     *            the test class
     * @param annotationClass
     *            the annotation type to look for
     * @return the annotated method
     * @throws Exception
     *             the exception
     */
    protected FrameworkMethod getAnnotatedMethod(final TestClass testClass,
            final Class<? extends Annotation> annotationClass) throws Exception {
        List<FrameworkMethod> methods = testClass.getAnnotatedMethods(annotationClass);
        for (FrameworkMethod each : methods) {
            int modifiers = each.getMethod().getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                return each;
            }
        }

        throw new Exception("No public static @" + annotationClass.getSimpleName()
                + " method in class " + testClass.getName());
    }

    /**
     * A specialized {@link BlockJUnit4ClassRunner} running tests on a given model element. 
     *
     * @author chsch
     */
    protected class ModelCollectionTestRunnerForModels extends BlockJUnit4ClassRunner {

        private Object model = null;
        private String modelName = null;

        /**
         * Constructor.
         * 
         * @param <T>
         *            the type of the current model
         * @param clazz
         *            the test class
         * @param theModel
         *            the model to perform the tests on
         * @param theModelName
         *            the name of model, used to provide a useful output in the JUnit view
         * @throws InitializationError
         *             if the super implementation throws such an error
         */
        public <T extends Object> ModelCollectionTestRunnerForModels(final Class<?> clazz,
                final T theModel, final String theModelName) throws InitializationError {
            super(clazz);
            this.model = theModel;
            this.modelName = theModelName;
        }

        // --------------------------------------------------------------------

        /**
         * {@inheritDoc}
         */
        @Override
        protected void validateConstructor(final List<Throwable> errors) {
            validateOnlyOneConstructor(errors);
            Class<?>[] constructorParams = getTestClass().getOnlyConstructor().getParameterTypes();
            
            if (constructorParams.length > 1
                    || constructorParams.length == 1
                    && !(constructorParams[0].equals(Object.class) || EObject.class
                            .isAssignableFrom(constructorParams[0]))) {
                errors.add(new Exception("Constructor of test class " + getTestClass().getName()
                        + " should have at most one parameter of type Object or (a sub type of)"
                        + " EObject."));
            }
        }

        // --------------------------------------------------------------------

        /**
         * Adds to {@code errors} for each method annotated with {@code @Test}that
         * is not a public, void instance method with zero arguments or one
         * Object/(a sub type of) EObject argument.
         * 
         * @param errors the error collecting list
         */
        protected void validateTestMethods(final List<Throwable> errors) {
            List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(Test.class);

            for (FrameworkMethod testMethod : methods) {
                    testMethod.validatePublicVoid(false, errors);
                    Method method = testMethod.getMethod();
                    final Class<?>[] methodParams = method.getParameterTypes();
                boolean methodOK = (methodParams.length > 1 || (methodParams.length == 1
                        && (methodParams[0].equals(Object.class)
                                || EObject.class.isAssignableFrom(methodParams[0]))));
                if (!methodOK) {
                    errors.add(new Exception("Method " + testMethod.getMethod().getName()
                            + " should have at most one parameter of type Object or (a sub type of)"
                            + " EObject."));
                }
                    
            }
        }

        // --------------------------------------------------------------------

        /**
         * Depending on the number of constructor parameters and, id a parameter is present the
         * parameter type, this method creates a new instance of the test class by calling the
         * (only) constructor and optionally injecting the current model.
         * 
         * @throws Exception
         *             if no fitting constructor is declared or the constructor call fails
         * @return the created test class instance
         */
        @Override
        public Object createTest() throws Exception {
            Class<?>[] constructorParams = getTestClass().getOnlyConstructor().getParameterTypes();
            if (constructorParams.length == 0) {
                return getTestClass().getOnlyConstructor().newInstance();
            } else if (constructorParams.length == 1
                    && constructorParams[0].isAssignableFrom(this.model.getClass())) {
                return getTestClass().getOnlyConstructor().newInstance(this.model);
            } else {
                throw new NoSuchMethodError("Type of first parameter of constructor of "
                        + getTestClass() + " is not compatible with the current model's type "
                        + this.model.getClass().getSimpleName());
            }
        }

        // --------------------------------------------------------------------

        /**
         * Returns a {@link Statement} that invokes {@code method} on {@code test} with parameter
         * {@code model} if required.
         * 
         * @param method
         *            the test method to be invoked
         * @param testClassInstance
         *            the testClassInstance to invoke the test method on
         * @return the {@link Statement} wrapping the test method call
         */
        protected Statement methodInvoker(final FrameworkMethod method,
                final Object testClassInstance) {
            return new InvokeMethodOnModel(method, testClassInstance, this.model);
        }
        
        // --------------------------------------------------------------------

        /**
         * The name of the runner is computed by the KielerTestRunner implementation.
         * 
         * @return the name
         */
        @Override
        protected String getName() {
            return ModelCollectionTestRunner.this.getClass().getSimpleName();
        }

        // --------------------------------------------------------------------

        /**
         * The name of the test of a concrete test run.
         * Due to some weird reason the JUnit view cuts the test name right before
         * the first occurrence of '(', used '[]' instead.
         * 
         * @param method
         *            the method
         * @return the string
         */
        @Override
        protected String testName(final FrameworkMethod method) {
            return "test method '" + method.getName() + "[]' testing model '" + this.modelName + "'";
        }
    }
    
    /**
     * A custom {@link Statement} encapsulating the invocation of a test method executed by the
     * JUnit {@link Runner}. It enables the invocation of test methods expecting the model element
     * as a (exactly one) parameter.
     * 
     * @author chsch
     */
    private static class InvokeMethodOnModel extends Statement {

        private FrameworkMethod method = null;
        private Object testClassInstance = null;
        private Object model = null;
        
        public InvokeMethodOnModel(final FrameworkMethod theMethod, final Object theTestClassInstance,
                final Object theModel) {
            this.method = theMethod;
            this.testClassInstance = theTestClassInstance;
            
            final Class<?>[] methodParams = theMethod.getMethod().getParameterTypes();
            if (methodParams.length == 1
                    && (methodParams[0].equals(Object.class) || (methodParams[0]
                            .isAssignableFrom(theModel.getClass())))) {
                this.model = theModel;
            } else {
                this.model = null;
            }
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void evaluate() throws Throwable {
            if (model == null) {
                method.invokeExplosively(testClassInstance);
            } else {
                method.invokeExplosively(testClassInstance, this.model);
            }
        }
    }
}
