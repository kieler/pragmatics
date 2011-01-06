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
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.impl.PropertyImpl;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.editor.DiagramEditorInternal;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Target;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutConfig;

/**
 * @author soh
 */
@SuppressWarnings("restriction")
public class GraphitiLayoutConfig extends EclipseLayoutConfig {

    /** Prefix for all layout options. */
    public static final String PREFIX = "layout:";

    /** Prefix for diagram defaults stored in the top-level editPart. */
    public static final String DIAG_PREFIX = "diagramDefaultLayout:";

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

    /**
     * 
     * {@inheritDoc}
     */
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

                        if (p.getKey().startsWith(PREFIX)
                                || p.getKey().startsWith(DIAG_PREFIX)) {
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
            String key = property.getKey();
            if (key.startsWith(PREFIX)) {
                key = key.replaceFirst(PREFIX, "");
                if (key.equals(optionData.getId())) {
                    String value = property.getValue();
                    if (value != null) {
                        if (optionData.parseValue(value) != null) {
                            return false;
                        }
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
            doSetProperty(optionData, value, PREFIX);
        } else {
            super.setProperty(property, value);
        }
    }

    /**
     * Set the property with the given prefix. If the value is null remove it.
     * 
     * @param optionData
     *            a layout option
     * @param value
     *            an option value
     * @param prefix
     *            the prefix under which the property is stored
     */
    private void doSetProperty(final LayoutOptionData<?> optionData,
            final Object value, final String prefix) {
        if (value == null) {
            removeOption(optionData.getId(), prefix);
        } else {
            setOption(optionData.getId(), value.toString(), prefix);
        }
    }

    /**
     * Set the option. Adds a new property to the property list unless the given
     * key already exists.
     * 
     * @param id
     *            the id of the property
     * @param value
     *            the value.
     * @param prefix
     *            the prefix for the storage
     */
    private void setOption(final String id, final String value,
            final String prefix) {
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

                        String key = p.getKey();
                        if (key.startsWith(prefix)) {
                            key = key.replaceFirst(prefix, "");
                            if (key.equals(id)) {
                                p.setValue(value);
                                return;
                            }
                        }
                    }
                    prop.add(new GraphitiLayoutProperty(prefix + id, value));
                }
            });
        }
    }

    /**
     * Remove an option from this EditPart.
     * 
     * @param id
     *            the id for the option
     * @param prefix
     *            the prefix for the option
     */
    private void removeOption(final String id, final String prefix) {
        EditPart part = getEditPart();
        removeOption(part, id, prefix);
    }

    /**
     * Remove the option from this EditPart and all its children.
     * 
     * @param id
     *            the id for the option
     * @param prefix
     *            the prefix for the option
     */
    private void removeOptionRecursively(final String id, final String prefix) {
        removeOptionRecursively(getEditPart(), id, prefix);
    }

    /**
     * Recursively remove the given option from the given EditPart and all its
     * children.
     * 
     * @param part
     *            the EditPart
     * @param id
     *            the id for the option
     * @param prefix
     *            the prefix for the option
     */
    private static void removeOptionRecursively(final EditPart part,
            final String id, final String prefix) {
        removeOption(part, id, prefix);
        if (part instanceof IPictogramElementEditPart) {
            IPictogramElementEditPart picto = (IPictogramElementEditPart) part;
            List<PictogramElement> children = picto.getModelChildren();
            DiagramEditorInternal editor = picto.getConfigurationProvider()
                    .getDiagramEditor();
            for (PictogramElement child : children) {
                GraphicalEditPart childPart = editor
                        .getEditPartForPictogramElement(child);
                removeOptionRecursively(childPart, id, prefix);
            }
        }
    }

    /**
     * Remove an option from the given EditPart.
     * 
     * @param part
     *            the EditPart
     * @param id
     *            the id for the option
     * @param prefix
     *            the prefix for the option
     */
    private static void removeOption(final EditPart part, final String id,
            final String prefix) {
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

                        String key = p.getKey();
                        if (key.startsWith(prefix)) {
                            key = key.replaceFirst(prefix, "");
                            if (key.equals(id)) {
                                iter.remove();
                            }
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
        T result = getProperty(optionData, PREFIX);
        if (result != null) {
            return result;
        }

        // check default option of diagram edit part
        result = getDiagramDefault(optionData);
        if (result != null) {
            return result;
        }

        // fall back to the user-stored or preconfigured configuration
        return super.doGetProperty(optionData);
    }

    /**
     * Get the property with the given prefix from this editPart.
     * 
     * @param <T>
     *            the type of the value of the option
     * @param optionData
     *            the layout option
     * @param prefix
     *            the prefix
     * @return the value or null
     */
    private <T> T getProperty(final LayoutOptionData<T> optionData,
            final String prefix) {
        T result;
        // check option value from notation model
        if (super.getEditPart() instanceof IPictogramElementEditPart) {
            // add user defined global layout options
            IPictogramElementEditPart editPart = (IPictogramElementEditPart) super
                    .getEditPart();

            for (Property p : editPart.getPictogramElement().getProperties()) {
                String key = p.getKey();
                if (key.startsWith(prefix)) {
                    key = key.replaceFirst(prefix, "");
                    if (key.equals(optionData.getId())) {
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
        }
        return null;
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

        String layoutHint = getLayoutHint();

        PictogramElement pe = editPart.getPictogramElement();
        if (pe instanceof Diagram) {
            super.initialize(Target.PARENTS, getEditPart(), layoutHint);
        } else if (pe instanceof ContainerShape) {
            super.initialize(Target.NODES, getEditPart(), layoutHint);
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
                super.initialize(Target.EDGES, getEditPart(), layoutHint);
                super.initialize(Target.PARENTS, getEditPart(), layoutHint);
            }
            if (hasPorts) {
                super.initialize(Target.PORTS, getEditPart(), layoutHint);
            }

        } else if (pe instanceof Connection) {
            super.initialize(Target.EDGES, getEditPart(), layoutHint);
        } else if (pe instanceof Anchor) {
            super.initialize(Target.PORTS, getEditPart(), layoutHint);
        }
    }

    /**
     * Get the layout hint either from this EditPart or the diagram default.
     * 
     * @return the layout hint or null
     */
    private String getLayoutHint() {
        String result = getPropertyValue(LayoutOptions.LAYOUTER_HINT_ID, PREFIX);
        if (result == null) {
            result = getDiagramDefault(LayoutOptions.LAYOUTER_HINT_ID);
        }
        return result;
    }

    /**
     * Get a property value from this editpart.
     * 
     * @param key
     *            the key
     * @param prefix
     *            the prefix
     * @return the value or null
     */
    private String getPropertyValue(final String key, final String prefix) {
        List<Property> list = getProperties(
                (IPictogramElementEditPart) getEditPart(), prefix);
        for (Property p : list) {
            if (p.getKey().equals(key)) {
                return p.getValue();
            }
        }
        return null;
    }

    /**
     * Get the layout properties from the given EditPart.
     * 
     * @param editPart
     *            the edit part
     * @param prefix
     *            the prefix to search for
     * @return the list of properties
     */
    public static List<Property> getProperties(
            final IPictogramElementEditPart editPart, final String prefix) {
        return getProperties(editPart.getPictogramElement(), prefix);
    }

    /**
     * Get the layout properties from the given PictogramElement.
     * 
     * @param pe
     *            the pictogram element
     * @param prefix
     *            the prefix to search for
     * @return the list of properties
     */
    public static List<Property> getProperties(final PictogramElement pe,
            final String prefix) {
        List<Property> result = new LinkedList<Property>();

        EList<Property> prop = pe.getProperties();

        for (Property p : prop) {
            if (p.getKey().startsWith(prefix)) {
                String key = p.getKey().replaceFirst(prefix, "");
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
            Diagram diag = editPart.getConfigurationProvider().getDiagram();
            addOptions(options, true, getProperties(diag, DIAG_PREFIX));
            addOptions(options, false, getProperties(editPart, PREFIX));
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

    @Override
    public void setDiagramDefault(final LayoutOptionData<?> optionData,
            final Object value) {
        removeOptionRecursively(optionData.getId(), PREFIX);
        doSetProperty(optionData, value, DIAG_PREFIX);
    }

    /**
     * Returns the default value for the selected diagram.
     * 
     * @param <T>
     *            type of option
     * @param optionData
     *            a layout option
     * @return the current diagram-default value
     */
    public <T> T getDiagramDefault(final LayoutOptionData<T> optionData) {
        T result;
        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            IPictogramElementEditPart picto = (IPictogramElementEditPart) part;
            List<Property> props = picto.getConfigurationProvider()
                    .getDiagram().getProperties();
            for (Property p : props) {
                String key = p.getKey();
                if (key.startsWith(DIAG_PREFIX)) {
                    key = key.replaceFirst(DIAG_PREFIX, "");
                    if (key.equals(optionData.getId())) {
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
        }
        return null;
    }

    /**
     * Get the diagram default for the given key.
     * 
     * @param id
     *            the id of the option
     * @return the diagram default.
     */
    public String getDiagramDefault(final String id) {
        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            IPictogramElementEditPart picto = (IPictogramElementEditPart) part;
            List<Property> props = picto.getConfigurationProvider()
                    .getDiagram().getProperties();
            for (Property p : props) {
                String key = p.getKey();
                if (key.startsWith(DIAG_PREFIX)) {
                    key = key.replaceFirst(DIAG_PREFIX, "");
                    if (key.equals(id)) {
                        String value = p.getValue();
                        if (value != null) {
                            return value;
                        }
                    }
                }
            }
        }
        return null;
    }
}
