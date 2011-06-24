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
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
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
import de.cau.cs.kieler.kiml.LayoutDataService;
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
     * Creates a layout configuration for Graphiti using an external configuration.
     * 
     * @param externalConfig
     *            an external layout configuration
     */
    public GraphitiLayoutConfig(final ILayoutConfig externalConfig) {
        super(externalConfig);
    }

    /**
     * Set the focus of the layout configuration on a specific edit part. The domain model element
     * of the edit part is passed to the super-class as well. This can be done without initializing
     * the layout configuration in order to use {@link #getAllProperties()} efficiently, since the
     * same configuration instance can be reused multiple times.
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
            PictogramElement pe = ((IPictogramElementEditPart) element).getPictogramElement();
            if (pe.getLink() != null && pe.getLink().getBusinessObjects().size() > 0) {
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
            clearChildOptions(diagram);
        }
    }

    /**
     * Clear all layout options from the given pictogram element.
     * 
     * @param pictogramElement
     *            a pictogram element
     */
    private static void clearChildOptions(final PictogramElement pictogramElement) {
        Iterator<Property> iter = pictogramElement.getProperties().iterator();

        while (iter.hasNext()) {
            Property p = iter.next();
            String key = p.getKey() == null ? "" : p.getKey();
            if (key.startsWith(PREFIX) || key.startsWith(DIAG_PREFIX)) {
                iter.remove();
            }
        }

        for (EObject pe : pictogramElement.eContents()) {
            if (pe instanceof PictogramElement) {
                clearChildOptions((PictogramElement) pe);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDefault(final LayoutOptionData<?> optionData) {
        EditPart editPart = getEditPart();
        if (editPart instanceof IPictogramElementEditPart) {
            PictogramElement pe = ((IPictogramElementEditPart) editPart).getPictogramElement();
            String optionKey = PREFIX + optionData.getId();
            for (Property property : pe.getProperties()) {
                if (optionKey.equals(property.getKey())) {
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
            EditPart editPart = getEditPart();
            if (editPart instanceof IPictogramElementEditPart) {
                setOption(optionData, value, PREFIX,
                        ((IPictogramElementEditPart) editPart).getPictogramElement());
            }
        } else {
            super.setProperty(property, value);
        }
    }

    /**
     * Set the option for the given pictogram element. Adds a new property to the property list
     * unless the given key already exists.
     * 
     * @param optionData
     *            layout option data
     * @param value
     *            the value
     * @param prefix
     *            the prefix for the property key
     * @param pictogramElement
     *            the pictogram element
     */
    private void setOption(final LayoutOptionData<?> optionData, final Object value,
            final String prefix, final PictogramElement pictogramElement) {
        if (value == null) {
            removeOption(optionData, prefix, pictogramElement);
        } else {
            String optionKey = prefix + optionData.getId();
            for (Property p : pictogramElement.getProperties()) {
                if (optionKey.equals(p.getKey())) {
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
     *            the prefix for the property key
     * @param pictogramElement
     *            the pictogram element
     */
    private void removeOption(final LayoutOptionData<?> optionData, final String prefix,
            final PictogramElement pictogramElement) {
        Iterator<Property> iter = pictogramElement.getProperties().iterator();
        String optionKey = prefix + optionData.getId();
        while (iter.hasNext()) {
            Property p = iter.next();
            if (optionKey.equals(p.getKey())) {
                iter.remove();
            }
        }
    }

    /**
     * Recursively remove an option from the given pictogram element and all its children.
     * 
     * @param optionData
     *            layout option data
     * @param prefix
     *            the prefix for the property key
     * @param pictogramElement
     *            the pictogram element
     */
    private void removeOptionRecursively(final LayoutOptionData<?> optionData, final String prefix,
            final PictogramElement pictogramElement) {
        removeOption(optionData, prefix, pictogramElement);
        if (pictogramElement instanceof ContainerShape) {
            for (Shape shape : ((ContainerShape) pictogramElement).getChildren()) {
                removeOptionRecursively(optionData, prefix, shape);
            }
        }
    }

    /**
     * Retrieve the value that is stored in the notation model or the default value for a layout
     * option.
     * 
     * @param <T>
     *            type of option
     * @param optionData
     *            a layout option
     * @return the stored or default value for the layout option
     */
    @Override
    protected <T> T doGetProperty(final LayoutOptionData<T> optionData) {
        T result = null;
        EditPart editPart = getEditPart();
        if (editPart instanceof IPictogramElementEditPart) {
            result = getOption(optionData, PREFIX,
                    ((IPictogramElementEditPart) editPart).getPictogramElement());
            if (result != null) {
                return result;
            }
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
     * Get a property from the given pictogram element.
     * 
     * @param <T>
     *            the type of the value of the option
     * @param optionData
     *            the layout option
     * @param prefix
     *            the prefix for the property key
     * @param pictogramElement
     *            a pictogram element
     * @return the value of the option, or {@code null}
     */
    private <T> T getOption(final LayoutOptionData<T> optionData, final String prefix,
            final PictogramElement pictogramElement) {
        String optionKey = prefix + optionData.getId();
        for (Property p : pictogramElement.getProperties()) {
            if (optionKey.equals(p.getKey())) {
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
        boolean hasChildren = false, hasPorts = false;
        if (pe instanceof Diagram) {
            super.initialize(Target.PARENTS, ep, getLayoutHint(pe));
            hasChildren = true;
        } else if (pe instanceof Shape) {
            super.initialize(Target.NODES, ep, getLayoutHint(((Shape) pe).getContainer()));
            if (pe instanceof ContainerShape) {
                // the same check for relevant children as in the layout manager must be made here
                for (Shape child : ((ContainerShape) pe).getChildren()) {
                    if (!child.getAnchors().isEmpty()) {
                        hasChildren = true;
                        break;
                    }
                }
                if (hasChildren) {
                    super.initialize(Target.PARENTS, ep, getLayoutHint(pe));
                }
            }
            // the same check for ports as in the layout manager must be made here
            for (Anchor anchor : ((Shape) pe).getAnchors()) {
                if (anchor instanceof BoxRelativeAnchor) {
                    hasPorts = true;
                    break;
                }
            }
        } else if (pe instanceof Connection) {
            ContainerShape parent = ((Shape) ((Connection) pe).getStart().getParent())
                    .getContainer();
            super.initialize(Target.EDGES, ep, getLayoutHint(parent));
        } else if (pe instanceof Anchor) {
            ContainerShape parent = ((Shape) ((Anchor) pe).getParent()).getContainer();
            super.initialize(Target.PORTS, ep, getLayoutHint(parent));
        }
        setChildren(hasChildren);
        setPorts(hasPorts);
    }

    /**
     * Get the layout hint either from the given pictogram element or the diagram default.
     * 
     * @param pictogramElement
     *            the element for which to get the layout hint
     * @return the layout hint or {@code null}
     */
    private String getLayoutHint(final PictogramElement pictogramElement) {
        LayoutOptionData<?> layoutHintData = LayoutDataService.getInstance().getOptionData(
                LayoutOptions.ALGORITHM_ID);
        String result = (String) getOption(layoutHintData, PREFIX, pictogramElement);
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
     * {@inheritDoc}
     */
    @Override
    public void setDiagramDefault(final LayoutOptionData<?> optionData, final Object value) {
        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            IPictogramElementEditPart pePart = (IPictogramElementEditPart) part;
            Diagram diagram = pePart.getConfigurationProvider().getDiagram();
            if (value != null) {
                removeOptionRecursively(optionData, PREFIX, diagram);
            }
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
            IPictogramElementEditPart pePart = (IPictogramElementEditPart) part;
            // add user defined global layout options
            addOptions(options, DIAG_PREFIX, pePart.getConfigurationProvider().getDiagram());
            // add user defined local layout options
            addOptions(options, PREFIX, pePart.getPictogramElement());
        }
    }

    /**
     * Add the options from the list of properties to the options map.
     * 
     * @param options
     *            map to add the options to
     * @param prefix
     *            the prefix for the property key
     * @param pe
     *            a pictogram element
     */
    private static void addOptions(final Map<IProperty<?>, Object> options, final String prefix,
            final PictogramElement pe) {
        LayoutDataService layoutServices = LayoutDataService.getInstance();
        for (Property prop : pe.getProperties()) {
            String key = prop.getKey();
            if (key != null && key.startsWith(prefix)) {
                LayoutOptionData<?> optionData = layoutServices.getOptionData(
                        key.substring(prefix.length()));
                if (optionData != null) {
                    Object value = optionData.parseValue(prop.getValue());
                    if (value != null) {
                        options.put(optionData, value);
                    }
                }
            }
        }
    }

}
