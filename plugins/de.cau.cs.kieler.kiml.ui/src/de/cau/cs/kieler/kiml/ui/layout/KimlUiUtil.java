/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.kiml.ui.layout;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption;
import de.cau.cs.kieler.kiml.layout.services.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.services.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.services.LayoutServices;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsFactory;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage;

/**
 * Utility methods used for the KIML UI.
 *
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class KimlUiUtil {
    
    /**
     * Retrieves the default value for the given layout option.
     * 
     * @param optionData a layout option data
     * @param providerData the active layout provider data
     * @param editPart the current edit part
     * @return an object with the default value
     */
    public static Object getDefault(LayoutOptionData optionData, LayoutProviderData providerData,
            EditPart editPart) {
        Object result = editPart != null ? LayoutServices.INSTANCE.getOption(
                editPart.getClass(), optionData.id) : null;
        if (result == null) {
            result = providerData != null ? providerData.instance.getDefault(optionData.id) : null;
            if (result == null) {
                switch (optionData.type) {
                case STRING:
                    return "";
                case BOOLEAN:
                    return Boolean.FALSE;
                case ENUM:
                case INT:
                    return Integer.valueOf(0);
                case FLOAT:
                    return Float.valueOf(0.0f);
                default:
                    return null;
                }
            }
        }
        if (result instanceof Enum<?>)
            return ((Enum<?>)result).ordinal();
        else
            return result;
    }
    
    /**
     * Returns the {@link KOption} with given key that is stored for the edit part.
     * 
     * @param editPart the edit part for which the option shall be fetched
     * @param optionId the identifier of the option
     * @return the corresponding option, or {@code null} if there is no such option
     */
    public static KOption getKOption(IGraphicalEditPart editPart, String optionId) {
        LayoutOptionStyle optionStyle = (LayoutOptionStyle)editPart.getNotationView()
                .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (optionStyle != null) {
            for (KOption koption : optionStyle.getOptions()) {
                if (koption.getKey().equals(optionId))
                    return koption;
            }
        }
        return null;
    }
    
    /**
     * Adds a layout option style to the given notation view or its domain element
     * by using the command stack.
     * 
     * @param notationView notation view of a graphical edit part
     * @param editingDomain the editing domain of the edit part
     * @return the new layout option style
     */
    public static LayoutOptionStyle addLayoutOptionStyle(final View notationView,
            TransactionalEditingDomain editingDomain) {
        final Maybe<LayoutOptionStyle> optionStyleWrap = new Maybe<LayoutOptionStyle>();
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @SuppressWarnings("unchecked")
            protected void doExecute() {
                optionStyleWrap.object = LayoutOptionsFactory.eINSTANCE.createLayoutOptionStyle();
//                EObject domainElement = notationView.getElement();
//                if (domainElement instanceof EModelElement) {
//                    optionStyleWrap.object.setSource(LayoutOptionStyle.class.getName());
//                    ((EModelElement)domainElement).getEAnnotations().add(optionStyleWrap.object);
//                }
//                else
                notationView.getStyles().add(optionStyleWrap.object);
            }
        });
        return optionStyleWrap.object;
    }
    
    /**
     * Adds a {@link KOption} to the given layout option style by using the command stack.
     * 
     * @param optionStyle layout option style of a notation view
     * @param optionData the layout option data for which the {@code KOption} shall be created
     * @param editingDomain the editing domain of the related edit part
     * @return the new {@code KOption}
     */
    public static KOption addKOption(final LayoutOptionStyle optionStyle,
            final LayoutOptionData optionData, TransactionalEditingDomain editingDomain) {
        final Maybe<KOption> koptionWrap = new Maybe<KOption>();
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            protected void doExecute() {
                switch (optionData.type) {
                case STRING:
                    koptionWrap.object = KLayoutDataFactory.eINSTANCE.createKStringOption();
                    break;
                case BOOLEAN:
                    koptionWrap.object = KLayoutDataFactory.eINSTANCE.createKBooleanOption();
                    break;
                case ENUM:
                case INT:
                    koptionWrap.object = KLayoutDataFactory.eINSTANCE.createKIntOption();
                    break;
                case FLOAT:
                    koptionWrap.object = KLayoutDataFactory.eINSTANCE.createKFloatOption();
                    break;
                default:
                    return;
                }
                koptionWrap.object.setKey(optionData.id);
                optionStyle.getOptions().add(koptionWrap.object);
            }
        });
        return koptionWrap.object;
    }

    /**
     * Returns the value of the given {@link KOption} as an {@code Object}.
     * 
     * @param koption the {@code KOption} for which the value shall be retrieved
     * @param optionData the layout option data related with the option
     * @return the current value of the option
     */
    public static Object getValue(KOption koption, LayoutOptionData optionData) {
        switch (optionData.type) {
        case STRING:
            return ((KStringOption)koption).getValue();
        case BOOLEAN:
            return Boolean.valueOf(((KBooleanOption)koption).isValue());
        case ENUM:
        case INT:
            return Integer.valueOf(((KIntOption)koption).getValue());
        case FLOAT:
            return Float.valueOf(((KFloatOption)koption).getValue());
        default:
            return null;
        }
    }
    
    /**
     * Sets the value of the given {@link KOption} by using the command stack.
     * 
     * @param koption the {@code KOption} for which the value shall be set
     * @param optionData the layout option data related with the option
     * @param value the new value of the option
     * @param editingDomain the editing domain of the related edit part
     */
    public static void setValue(final KOption koption, final LayoutOptionData optionData,
            final Object value, TransactionalEditingDomain editingDomain) {
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            protected void doExecute() {
                switch (optionData.type) {
                case STRING:
                    ((KStringOption)koption).setValue((String)value);
                    break;
                case BOOLEAN:
                    ((KBooleanOption)koption).setValue(((Boolean)value).booleanValue());
                    break;
                case ENUM:
                case INT:
                    ((KIntOption)koption).setValue(((Integer)value).intValue());
                    break;
                case FLOAT:
                    ((KFloatOption)koption).setValue(((Float)value).floatValue());
                    break;
                }
            }
        });
    }

}
