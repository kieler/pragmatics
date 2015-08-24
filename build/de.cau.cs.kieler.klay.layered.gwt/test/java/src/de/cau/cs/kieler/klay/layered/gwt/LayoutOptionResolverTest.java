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
package de.cau.cs.kieler.klay.layered.gwt;

import static de.cau.cs.kieler.kiml.options.LayoutOptions.ANIMATION_TIME_FACTOR;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.DIAGRAM_TYPE;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.LAYOUT_ANCESTORS;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.MAX_ANIMATION_TIME;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.MIN_ANIMATION_TIME;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.PROGRESS_BAR;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.RESET_CONFIG;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.SCALE_FACTOR;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.ZOOM_TO_FIT;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.gwt.client.layout.LayoutOptionResolver;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Tests for the {@link LayoutOptionResolver}.
 * 
 * @author uru
 */
public class LayoutOptionResolverTest {

    /**
     * A set of layout options that do not have to be resolved, e.g. because they are specific to
     * eclipse.
     */
    private static final Set<String> EXECPTIONS = ImmutableSet.of(
            // Eclipse specific options, not required for js
            MIN_ANIMATION_TIME.getId(), MAX_ANIMATION_TIME.getId(), ANIMATION_TIME_FACTOR.getId(),
            DIAGRAM_TYPE.getId(), // eclipse specific
            LAYOUT_ANCESTORS.getId(), PROGRESS_BAR.getId(), ZOOM_TO_FIT.getId(),
            // somewhat the same
            RESET_CONFIG.getId(),
            // klighd
            SCALE_FACTOR.getId());

    /**
     * Tests that all known layout options and specific klay layered options are resolved by the js
     * specific {@link LayoutOptionResolver}.
     * 
     * This test assures that newly created layout options are either added to the resolver or are
     * explicitly ignored using the {@link #EXECPTIONS} sets.
     * 
     * @throws IllegalAccessException .
     * @throws IllegalArgumentException .
     */
    @Test
    public void allKnownPropertiesAreResolved() throws IllegalArgumentException,
            IllegalAccessException {

        // gather all the layout options the resolver knows about
        final Set<String> knownOptions = Sets.newHashSet();

        Field[] resolverFields = LayoutOptionResolver.class.getDeclaredFields();
        for (Field f : resolverFields) {
            if (f.getType().isAssignableFrom(Pair.class)) {
                f.setAccessible(true); // required for private static fields
                @SuppressWarnings("unchecked")
                Pair<Set<String>, Map<String, IProperty<?>>> pair =
                        (Pair<Set<String>, Map<String, IProperty<?>>>) f.get(null);

                knownOptions.addAll(pair.getSecond().keySet());
            }
        }

        // gather all layout options that must be supported
        Field[] fields =
                ObjectArrays.concat(LayoutOptions.class.getDeclaredFields(),
                        Properties.class.getDeclaredFields(), Field.class);

        for (Field f : fields) {
            if (f.getType().isAssignableFrom(IProperty.class)) {
                IProperty<?> prop = (IProperty<?>) f.get(null);
                if (!EXECPTIONS.contains(prop.getId())) {
                    Assert.assertTrue("Should resolve layout option " + prop.getId(),
                            knownOptions.contains(prop.getId()));
                }
            }
        }
    }

}
