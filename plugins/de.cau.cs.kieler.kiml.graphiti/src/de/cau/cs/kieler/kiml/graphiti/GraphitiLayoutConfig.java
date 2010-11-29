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

import java.util.HashMap;
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
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutConfig;

/**
 * @author soh
 */
public class GraphitiLayoutConfig extends EclipseLayoutConfig {

    public static class GraphitiLayoutProperty extends PropertyImpl implements
            Property {

        public GraphitiLayoutProperty(final String keyParam,
                final String valueParam) {
            super.setKey(keyParam);
            super.setValue(valueParam);
        }
    }

    private Map<String, String> optionMap = new HashMap<String, String>();

    @Override
    public void clearProperties() {
        EditPart part = getEditPart();
        if (part instanceof IPictogramElementEditPart) {
            optionMap = new HashMap<String, String>();
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

                        if (p.getKey().startsWith("KIELER_")) {
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
        // check option value from notation model
        if (optionMap.isEmpty()) {
            String value = optionMap.get(optionData.getId());
            if (value != null) {
                if (optionData.parseValue(value) != null) {
                    return false;
                }
            }

            // KOption koption = koptionMap.get(optionData);
            // if (koption != null) {
            // if (optionData.parseValue(koption.getValue()) != null) {
            // return false;
            // }
            // }
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
    @SuppressWarnings("unchecked")
    public void setProperty(final IProperty<?> property, final Object value) {
        if (property instanceof LayoutOptionData<?>) {
            LayoutOptionData<?> optionData = (LayoutOptionData<?>) property;
            if (value == null) {
                optionMap.remove(optionData.getId());
                removeOption(optionData.getId());
                // if (optionStyle != null) {
                // removeKOption(optionStyle, optionData);
                // koptionMap.remove(optionData);
                // }
            } else {
                String currentValue = optionMap.get(optionData.getId());
                if (currentValue == null) {
                    optionMap.put(optionData.getId(), value.toString());
                    // if (optionStyle == null) {
                    // if (getEditPart() instanceof IGraphicalEditPart) {
                    // IGraphicalEditPart focusEditPart = (IGraphicalEditPart)
                    // getEditPart();
                    // optionStyle = LayoutOptionsFactory.eINSTANCE
                    // .createLayoutOptionStyle();
                    // focusEditPart.getNotationView().getStyles()
                    // .add(optionStyle);
                    // } else {
                    // super.setProperty(property, value);
                    // return;
                    // }
                    // }
                    // koption = LayoutOptionsFactory.eINSTANCE.createKOption();
                    // koption.setKey(optionData.getId());
                    // optionStyle.getOptions().add(koption);
                    // koptionMap.put(optionData, koption);
                }
                optionMap.put(optionData.getId(), value.toString());
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

                        if (p.getKey().equals("KIELER_" + id)) {
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

                        if (p.getKey().equals("KIELER_" + id)) {
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
        if (!optionMap.isEmpty()) {
            String value = optionMap.get(optionData.getId());
            if (value != null) {
                result = optionData.parseValue(value);
                if (result != null) {
                    return result;
                }
            }
        }

        // check default option of diagram edit part
        // result = getDiagramDefault(optionData);
        // if (result != null) {
        // return result;
        // }

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
        optionMap = new HashMap<String, String>();
        // find an appropriate property source and set the layout option targets
        final IPictogramElementEditPart focusEditPart = editPart;

        setFocus(focusEditPart);

        GraphitiLayoutInspector li = new GraphitiLayoutInspector(focusEditPart);
        final TransactionalEditingDomain ed = li.getEditingDomain();
        CommandStack cs = ed.getCommandStack();
        cs.execute(new RecordingCommand(ed) {

            @Override
            protected void doExecute() {
                EList<Property> prop = focusEditPart.getPictogramElement()
                        .getProperties();

                for (Property p : prop) {
                    if (p.getKey().startsWith("KIELER_")) {
                        String key = p.getKey().replaceFirst("KIELER_", "");
                        String value = p.getValue();

                        optionMap.put(key, value);
                    }
                }
            }
        });
    }

    public static List<Property> getProperties(
            final IPictogramElementEditPart editPart) {
        List<Property> result = new LinkedList<Property>();

        EList<Property> prop = editPart.getPictogramElement().getProperties();

        for (Property p : prop) {
            if (p.getKey().startsWith("KIELER_")) {
                String key = p.getKey().replaceFirst("KIELER_", "");
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

        if (super.getEditPart() instanceof IPictogramElementEditPart) {
            // add user defined global layout options
            IPictogramElementEditPart editPart = (IPictogramElementEditPart) super
                    .getEditPart();
            addOptions(options, false, getProperties(editPart));

            // DiagramEditPart diagramEditPart =
            // GraphitiLayoutInspector.getDiagramEditPart(editPart);
            // if (diagramEditPart != editPart && diagramEditPart != null) {
            // LayoutOptionStyle style = (LayoutOptionStyle)
            // diagramEditPart.getNotationView()
            // .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            // addOptions(options, style, true);
            // }
            //
            // // add user defined local layout options
            // LayoutOptionStyle style = (LayoutOptionStyle)
            // editPart.getNotationView().getStyle(
            // LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            // addOptions(options, style, false);
        }
    }

    private static void addOptions(final Map<IProperty<?>, Object> options,
            final boolean onlyDefault, final List<Property> props) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        for (Property option : props) {
            // if (!onlyDefault || option.isDefault()) {
            LayoutOptionData<?> optionData = layoutServices
                    .getLayoutOptionData(option.getKey());
            if (optionData != null) {
                Object value = optionData.parseValue(option.getValue());
                if (value != null) {
                    options.put(optionData, value);
                }
            }
            // }
        }
    }
}
