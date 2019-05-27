/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * ServiceLoader adapted to the KIELER project.
 * 
 * @author als, nre
 */
public class KlighdServiceLoader {

    /**
     * Loads and instantiates all implementing classes of the given service registered via
     * '/META-INF/services/'. <br>
     * If expected with a running eclipse platform the implementations will be loaded from the
     * Bundles, otherwise the standard ServiceLoader is used.
     * 
     * @param service
     *            The interface or abstract class representing the service.
     * 
     * @return An iterable with instances of all implementing classes.
     */
    @SuppressWarnings("unchecked")
    public static <S extends Object> Iterable<S> load(final Class<S> service) {
        Bundle b = FrameworkUtil.getBundle(KlighdServiceLoader.class);
        if (b != null) {
            BundleContext context = b.getBundleContext();
            if (context != null) {
                HashSet<Class<? extends S>> serviceClasses = new HashSet<>();
                for (Bundle bundle : context.getBundles()) {
                    try {
                        URL file = bundle
                                .getResource("/META-INF/services/" + service.getCanonicalName());
                        if (file != null) {
                            BufferedReader reader =
                                    new BufferedReader(new InputStreamReader(file.openStream()));
                            reader.lines().forEach(line -> {
                                try {
                                    serviceClasses.add((Class<? extends S>) bundle.loadClass(line));
                                } catch (ClassNotFoundException e) {
                                    // ignore
                                }
                            });
                            reader.close();
                        }
                    } catch (Exception e) {
                        // ignore
                        return (Iterable<S>) null;
                    }
                }
                return serviceClasses.stream().filter(c -> c != null).map(c -> {
                    Constructor<? extends S> constr;
                    try {
                        constr = c.getConstructor();
                    } catch (Throwable e) {
                        // Ignore.
                        return (S) null;
                    }
                    if (constr != null) {
                        try {
                            return (S) constr.newInstance();
                        } catch (Exception e) {
                            // Ignore.
                            return (S) null;
                        }
                    }
                    return (S) null;
                }).filter(i -> i != null).collect(Collectors.toList());
            }
        } else {
            return ServiceLoader.load(service);
        }
        return null;
    }
}
