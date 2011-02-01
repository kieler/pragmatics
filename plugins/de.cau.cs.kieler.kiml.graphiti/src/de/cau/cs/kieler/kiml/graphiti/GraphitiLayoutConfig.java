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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Target;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutConfig;

/**
 * Layout option configuration for Graphiti.
 * 
 * @author soh
 * @author msp
 */
@SuppressWarnings("restriction")
public class GraphitiLayoutConfig extends EclipseLayoutConfig {

    /** Prefix for all layout options. */
    public static final String PREFIX = "layout:";

    /** Prefix for diagram defaults stored in the top-level edit part. */
    public static final String DIAG_PREFIX = "defaultLayout:";

    /**
     * Creates a layout configuration for Graphiti.
     */
    public GraphitiLayoutConfig() {
        super();
    }

    /**
     * Creates a layout configuration for Graphiti using an external
     * configuration.
     * 
     * @param externalConfig
     *            an external layout configuration
     */
    public GraphitiLayoutConfig(final ILayoutConfig externalConfig) {
        super(externalConfig);
    }

    /**
     * Set the focus of the layout configuration on a specific edit part. The
     * domain model element of the edit part is passed to the super-class as
     * well. This can be done without initializing the layout configuration in
     * order to use {@link #getAllProperties()} efficiently, since the same
     * configuration instance can be reused multiple times.
     * 
     * @param element
     *            an instance of {@link IGraphicalEditPart}
     */
    @Override
    public void setFocus(final Object element) {
        if (element != null) {
            // first clear the current focus
            super.setFocus(null);
        }
        super.setFocus(element);
        if (element instanceof IPictogramElementEditPart) {
            PictogramElement pe =
                    ((IPictogramElementEditPart) element).getPictogramElement();
            if (pe.getLink() != null
                    && pe.getLink().getBusinessObjects().size() > 0) {
                super.setFocus(pe.getLink().getBusinessObjects().get(0));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearProperties() {
        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            IPictogramElementEditPart pePart = (IPictogramElementEditPart) part;
            Diagram diagram = pePart.getConfigurationProvider().getDiagram();
            clearPropertiesRecursively(diagram);
        }
    }

    /**
     * Clear all layout options from the given pictogram element.
     * 
     * @param pictogramElement
     *            a pictogram element
     */
    private static void clearPropertiesRecursively(
            final PictogramElement pictogramElement) {
        Iterator<Property> iter = pictogramElement.getProperties().iterator();

        while (iter.hasNext()) {
            Property p = iter.next();
            if (p.getKey().startsWith(PREFIX)
                    || p.getKey().startsWith(DIAG_PREFIX)) {
                iter.remove();
            }
        }

        for (EObject pe : pictogramElement.eContents()) {
            if (pe instanceof PictogramElement) {
                clearPropertiesRecursively((PictogramElement) pe);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDefault(final LayoutOptionData<?> optionData) {
        EditPart ed = getEditPart();
        IPictogramElementEditPart focusEditPart =
                (IPictogramElementEditPart) ed;
        // check option value from notation model
        for (Property property : focusEditPart.getPictogramElement()
                .getProperties()) {
            String key = property.getKey();
            if (key.startsWith(PREFIX)) {
                if (key.substring(PREFIX.length()).equals(optionData.getId())) {
                    String value = property.getValue();
                    if (optionData.parseValue(value) != null) {
                        return false;
                    }
                }
            }
        }
        return super.isDefault(optionData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProperty(final IProperty<?> property, final Object value) {
        if (property instanceof LayoutOptionData<?>) {
            LayoutOptionData<?> optionData = (LayoutOptionData<?>) property;
            setOption(optionData, value, PREFIX, getEditPart());
        } else {
            super.setProperty(property, value);
        }
    }

    /**
     * Set the option for the given edit part.
     * 
     * @param optionData
     *            layout option data
     * @param value
     *            the value
     * @param prefix
     *            the prefix for storage
     * @param editPart
     *            the edit part
     */
    private void setOption(final LayoutOptionData<?> optionData,
            final Object value, final String prefix, final EditPart editPart) {
        if (editPart instanceof IPictogramElementEditPart) {
            setOption(optionData, value, prefix,
                    ((IPictogramElementEditPart) editPart)
                            .getPictogramElement());
        }
    }

    /**
     * Set the option for the given pictogram element. Adds a new property to
     * the property list unless the given key already exists.
     * 
     * @param optionData
     *            layout option data
     * @param value
     *            the value
     * @param prefix
     *            the prefix for storage
     * @param pictogramElement
     *            the pictogram element
     */
    private void setOption(final LayoutOptionData<?> optionData,
            final Object value, final String prefix,
            final PictogramElement pictogramElement) {
        if (value == null) {
            removeOption(optionData, prefix, pictogramElement);
        } else {
            for (Property p : pictogramElement.getProperties()) {
                String key = p.getKey();
                if (key.startsWith(prefix)
                        && key.substring(prefix.length()).equals(
                                optionData.getId())) {
                    p.setValue(value.toString());
                    return;
                }
            }

            Property p = MmFactory.eINSTANCE.createProperty();
            p.setKey(prefix + optionData.getId());
            p.setValue(value.toString());
            pictogramElement.getProperties().add(p);
        }
    }

    /**
     * Remove an option from the given pictogram element.
     * 
     * @param optionData
     *            layout option data
     * @param prefix
     *            the prefix for storage
     * @param pictogramElement
     *            the pictogram element
     */
    private void removeOption(final LayoutOptionData<?> optionData,
            final String prefix, final PictogramElement pictogramElement) {
        Iterator<Property> iter = pictogramElement.getProperties().iterator();
        while (iter.hasNext()) {
            Property p = iter.next();

            String key = p.getKey();
            if (key.startsWith(prefix)
                    && key.substring(prefix.length())
                            .equals(optionData.getId())) {
                iter.remove();
            }
        }
    }

    /**
     * Recursively remove the given option from the given pictogram element and
     * all its children.
     * 
     * @param optionData
     *            layout option data
     * @param prefix
     *            the prefix for storage
     * @param pictogramElement
     *            the pictogram element
     */
    private void removeOptionRecursively(final LayoutOptionData<?> optionData,
            final String prefix, final PictogramElement pictogramElement) {
        removeOption(optionData, prefix, pictogramElement);
        if (pictogramElement instanceof ContainerShape) {
            for (Shape shape : ((ContainerShape) pictogramElement)
                    .getChildren()) {
                removeOptionRecursively(optionData, prefix, shape);
            }
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
        T result = getOption(optionData, PREFIX, getEditPart());
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
     * Get a property from the given edit part.
     * 
     * @param <T>
     *            the type of the value of the option
     * @param optionData
     *            the layout option
     * @param prefix
     *            the prefix
     * @param editPart
     *            an edit part
     * @return the value of the option, or {@code null}
     */
    private <T> T getOption(final LayoutOptionData<T> optionData,
            final String prefix, final EditPart editPart) {
        if (editPart instanceof IPictogramElementEditPart) {
            return getOption(optionData, prefix,
                    ((IPictogramElementEditPart) editPart)
                            .getPictogramElement());
        }
        return null;
    }

    /**
     * Get a property from the given pictogram element.
     * 
     * @param <T>
     *            the type of the value of the option
     * @param optionData
     *            the layout option
     * @param prefix
     *            the prefix
     * @param pictogramElement
     *            a pictogram element
     * @return the value of the option, or {@code null}
     */
    private <T> T getOption(final LayoutOptionData<T> optionData,
            final String prefix, final PictogramElement pictogramElement) {
        for (Property p : pictogramElement.getProperties()) {
            String key = p.getKey();
            if (key.startsWith(prefix)
                    && key.substring(prefix.length())
                            .equals(optionData.getId())) {
                T result = optionData.parseValue(p.getValue());
                if (result != null) {
                    return result;
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
        setFocus(editPart);
        PictogramElement pe = editPart.getPictogramElement();
        EditPart ep = (EditPart) editPart;
        if (pe instanceof Diagram) {
            super.initialize(Target.PARENTS, ep, getLayoutHint(pe));
        } else if (pe instanceof Shape) {
            super.initialize(Target.NODES, ep,
                    getLayoutHint(((Shape) pe).getContainer()));
            boolean hasChildren = false;
            if (pe instanceof ContainerShape) {
                // the same check for relevant children as in the layout manager
                // must be made here
                for (Shape child : ((ContainerShape) pe).getChildren()) {
                    for (Anchor anchor : child.getAnchors()) {
                        if (anchor instanceof ChopboxAnchor) {
                            hasChildren = true;
                            break;
                        }
                    }
                }
            }
            if (hasChildren) {
                super.initialize(Target.PARENTS, ep, getLayoutHint(pe));
            }
        } else if (pe instanceof Connection) {
            ContainerShape parent =
                    ((Shape) ((Connection) pe).getStart().getParent())
                            .getContainer();
            super.initialize(Target.EDGES, ep, getLayoutHint(parent));
        } else if (pe instanceof Anchor) {
            ContainerShape parent =
                    ((Shape) ((Anchor) pe).getParent()).getContainer();
            super.initialize(Target.PORTS, ep, getLayoutHint(parent));
        }
    }

    /**
     * Get the layout hint either from the given pictogram element or the
     * diagram default.
     * 
     * @param pictogramElement
     *            the element for which to get the layout hint
     * @return the layout hint or {@code null}
     */
    private String getLayoutHint(final PictogramElement pictogramElement) {
        LayoutOptionData<?> layoutHintData =
                LayoutServices.getInstance().getLayoutOptionData(
                        LayoutOptions.LAYOUTER_HINT_ID);
        String result =
                (String) getOption(layoutHintData, PREFIX, pictogramElement);
        if (result == null && pictogramElement instanceof Shape) {
            Shape container = (Shape) pictogramElement;
            while (!(container instanceof Diagram)) {
                container = container.getContainer();
            }
            result = (String) getOption(layoutHintData, DIAG_PREFIX, container);
        }
        return result;
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
        for (Property p : pe.getProperties()) {
            if (p.getKey().startsWith(prefix)) {
                String key = p.getKey().substring(prefix.length());
                String value = p.getValue();

                Property p2 = MmFactory.eINSTANCE.createProperty();
                p2.setKey(key);
                p2.setValue(value);
                result.add(p2);
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

        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            // add user defined global layout options
            IPictogramElementEditPart pePart = (IPictogramElementEditPart) part;
            addOptions(
                    options,
                    getProperties(pePart.getConfigurationProvider()
                            .getDiagram(), DIAG_PREFIX));
            addOptions(options,
                    getProperties(pePart.getPictogramElement(), PREFIX));
        }
    }

    /**
     * Add the options from the list of properties to the options map.
     * 
     * @param options
     *            map to add the options to
     * @param props
     *            the list of properties
     */
    private static void addOptions(final Map<IProperty<?>, Object> options,
            final List<Property> props) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        for (Property option : props) {
            LayoutOptionData<?> optionData =
                    layoutServices.getLayoutOptionData(option.getKey());
            if (optionData != null) {
                Object value = optionData.parseValue(option.getValue());
                if (value != null) {
                    options.put(optionData, value);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDiagramDefault(final LayoutOptionData<?> optionData,
            final Object value) {
        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            IPictogramElementEditPart pePart = (IPictogramElementEditPart) part;
            Diagram diagram = pePart.getConfigurationProvider().getDiagram();
            removeOptionRecursively(optionData, PREFIX, diagram);
            setOption(optionData, value, DIAG_PREFIX, diagram);
        }
    }

    /**
     * Returns the default value for the selected diagram.
     * 
     * @param <T>
     *            type of option
     * @param optionData
     *            layout option data
     * @return the current diagram-default value, or {@code null}
     */
    public <T> T getDiagramDefault(final LayoutOptionData<T> optionData) {
        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            IPictogramElementEditPart pePart = (IPictogramElementEditPart) part;
            Diagram diagram = pePart.getConfigurationProvider().getDiagram();
            return getOption(optionData, DIAG_PREFIX, diagram);
        }
        return null;
    }

}
