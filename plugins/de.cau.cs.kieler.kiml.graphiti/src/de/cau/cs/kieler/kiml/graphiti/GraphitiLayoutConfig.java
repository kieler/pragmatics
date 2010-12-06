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
 */
package de.cau.cs.kieler.kiml.graphiti;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.impl.PropertyImpl;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Target;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutConfig;

/**
 * @author soh
 */
@SuppressWarnings("restriction")
public class GraphitiLayoutConfig extends EclipseLayoutConfig {

    /**
     * Custom property that allow assigning key, value in the constructor.
     * 
     * @author soh
     */
    public static class GraphitiLayoutProperty extends PropertyImpl implements
            Property {

        /**
         * 
         * Creates a new GraphitiLayoutProperty.
         * 
         * @param keyParam
         *            the key
         * @param valueParam
         *            the value
         */
        public GraphitiLayoutProperty(final String keyParam,
                final String valueParam) {
            super.setKey(keyParam);
            super.setValue(valueParam);
        }
    }

    @Override
    public void clearProperties() {
        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            final IPictogramElementEditPart focusEditPart = (IPictogramElementEditPart) part;
            GraphitiLayoutInspector li = new GraphitiLayoutInspector(
                    focusEditPart);
            final TransactionalEditingDomain ed = li.getEditingDomain();
            CommandStack cs = ed.getCommandStack();
            cs.execute(new RecordingCommand(ed) {

                @Override
                protected void doExecute() {
                    EList<Property> prop = focusEditPart.getPictogramElement()
                            .getProperties();

                    Iterator<Property> iter = prop.iterator();

                    while (iter.hasNext()) {
                        Property p = iter.next();

                        if (p.getKey().startsWith("de.cau.cs.kieler")) {
                            iter.remove();
                        }
                    }
                }
            });
        }
    }

    /**
     * Determines whether the given layout option is already stored in the
     * notation view of the selected element. This requires
     * {@link #initialize(IGraphicalEditPart) initialize} to be called first in
     * order to work properly.
     * 
     * @param optionData
     *            a layout option
     * @return whether the option has its default value or not
     */
    @Override
    public boolean isDefault(final LayoutOptionData<?> optionData) {
        EditPart ed = getEditPart();
        IPictogramElementEditPart focusEditPart = (IPictogramElementEditPart) ed;
        EList<Property> prop = focusEditPart.getPictogramElement()
                .getProperties();
        // check option value from notation model
        for (Property property : prop) {
            if (property.getKey().equals(optionData.getId())) {
                String value = property.getValue();
                if (value != null) {
                    if (optionData.parseValue(value) != null) {
                        return false;
                    }
                }
            }
        }
        return super.isDefault(optionData);
    }

    /**
     * Stores the given value in the notation view of the selected element. This
     * requires {@link #initialize(IGraphicalEditPart) initialize} to be called
     * first in order to work properly.
     * 
     * @param property
     *            a layout option
     * @param value
     *            an option value
     */
    @Override
    public void setProperty(final IProperty<?> property, final Object value) {
        if (property instanceof LayoutOptionData<?>) {
            LayoutOptionData<?> optionData = (LayoutOptionData<?>) property;
            if (value == null) {
                removeOption(optionData.getId());
            } else {
                setOption(optionData.getId(), value.toString());
            }
        } else {
            super.setProperty(property, value);
        }
    }

    /**
     * @param id
     * @param string
     */
    private void setOption(final String id, final String string) {
        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            final IPictogramElementEditPart focusEditPart = (IPictogramElementEditPart) part;
            GraphitiLayoutInspector li = new GraphitiLayoutInspector(
                    focusEditPart);
            final TransactionalEditingDomain ed = li.getEditingDomain();
            CommandStack cs = ed.getCommandStack();
            cs.execute(new RecordingCommand(ed) {

                @Override
                protected void doExecute() {
                    EList<Property> prop = focusEditPart.getPictogramElement()
                            .getProperties();

                    Iterator<Property> iter = prop.iterator();

                    while (iter.hasNext()) {
                        Property p = iter.next();

                        if (p.getKey().equals(id)) {
                            p.setValue(string);
                            return;
                        }
                    }
                    prop.add(new GraphitiLayoutProperty(id, string));
                }
            });
        }
    }

    /**
     * @param id
     */
    private void removeOption(final String id) {
        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            final IPictogramElementEditPart focusEditPart = (IPictogramElementEditPart) part;
            GraphitiLayoutInspector li = new GraphitiLayoutInspector(
                    focusEditPart);
            final TransactionalEditingDomain ed = li.getEditingDomain();
            CommandStack cs = ed.getCommandStack();
            cs.execute(new RecordingCommand(ed) {

                @Override
                protected void doExecute() {
                    EList<Property> prop = focusEditPart.getPictogramElement()
                            .getProperties();

                    Iterator<Property> iter = prop.iterator();

                    while (iter.hasNext()) {
                        Property p = iter.next();

                        if (p.getKey().equals(id)) {
                            iter.remove();
                        }
                    }
                }
            });
        }
    }

    /**
     * Retrieve the value that is stored in the notation model or the default
     * value for a layout option.
     * 
     * @param <T>
     *            type of option
     * @param optionData
     *            a layout option
     * @return the stored or default value for the layout option
     */
    @Override
    protected <T> T doGetProperty(final LayoutOptionData<T> optionData) {
        T result;
        // check option value from notation model
        if (super.getEditPart() instanceof IPictogramElementEditPart) {
            // add user defined global layout options
            IPictogramElementEditPart editPart = (IPictogramElementEditPart) super
                    .getEditPart();

            for (Property p : editPart.getPictogramElement().getProperties()) {
                if (p.getKey().equals(optionData.getId())) {
                    String value = p.getValue();
                    if (value != null) {
                        result = optionData.parseValue(value);
                        if (result != null) {
                            return result;
                        }
                    }
                }
            }
        }

        // fall back to the user-stored or preconfigured configuration
        return super.doGetProperty(optionData);
    }

    /**
     * Initialize the configuration for a graphical edit part.
     * 
     * @param editPart
     *            an edit part
     */
    public final void initialize(final IPictogramElementEditPart editPart) {
        // find an appropriate property source and set the layout option targets
        final IPictogramElementEditPart focusEditPart = editPart;

        setFocus(focusEditPart);

        PictogramElement pe = editPart.getPictogramElement();
        if (pe instanceof Diagram) {
            super.initialize(Target.PARENTS, getEditPart(), null);
        } else if (pe instanceof ContainerShape) {
            super.initialize(Target.NODES, getEditPart(), null);
            List<PictogramElement> children = editPart.getModelChildren();
            boolean hasPorts = false;
            boolean hasChildren = false;
            for (PictogramElement pe1 : children) {
                if (pe1 instanceof Anchor) {
                    hasPorts = true;
                } else {
                    hasChildren = true;
                }
            }
            if (hasChildren) {
                super.initialize(Target.EDGES, getEditPart(), null);
                super.initialize(Target.PARENTS, getEditPart(), null);
            }
            if (hasPorts) {
                super.initialize(Target.PORTS, getEditPart(), null);
            }

        } else if (pe instanceof Connection) {
            super.initialize(Target.EDGES, getEditPart(), null);
        } else if (pe instanceof Anchor) {
            super.initialize(Target.PORTS, getEditPart(), null);
        }
    }

    /**
     * Get the layout properties from the given editpart.
     * 
     * @param editPart
     *            the edit part
     * @return the list of properties
     */
    public static List<Property> getProperties(
            final IPictogramElementEditPart editPart) {
        List<Property> result = new LinkedList<Property>();

        EList<Property> prop = editPart.getPictogramElement().getProperties();

        for (Property p : prop) {
            if (p.getKey().startsWith("de.cau.cs.kieler")) {
                String key = p.getKey();
                String value = p.getValue();

                result.add(new GraphitiLayoutProperty(key, value));
            }
        }
        return result;
    }

    /**
     * Add all notation model values to the given map of layout options.
     * 
     * @param options
     *            a map of layout option values
     */
    @Override
    protected void addProperties(final Map<IProperty<?>, Object> options) {
        super.addProperties(options);

        EditPart part = super.getEditPart();

        if (part instanceof IPictogramElementEditPart) {
            // add user defined global layout options
            IPictogramElementEditPart editPart = (IPictogramElementEditPart) part;
            addOptions(options, false, getProperties(editPart));
        }
    }

    /**
     * Add the options from the list of properties to the options map.
     * 
     * @param options
     *            map to add the options to
     * @param onlyDefault
     *            true if only defaults should be added
     * @param props
     *            the list of properties
     */
    private static void addOptions(final Map<IProperty<?>, Object> options,
            final boolean onlyDefault, final List<Property> props) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        for (Property option : props) {
            LayoutOptionData<?> optionData = layoutServices
                    .getLayoutOptionData(option.getKey());
            if (optionData != null) {
                Object value = optionData.parseValue(option.getValue());
                if (value != null) {
                    options.put(optionData, value);
                }
            }
        }
    }
}
