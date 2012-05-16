package de.cau.cs.kieler.kaom.karma.ptolemy.krendering;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.IFigure;
import org.eclipse.ui.statushandlers.StatusManager;
import org.w3c.dom.Document;

import ptolemy.kernel.util.NamedObj;
import ptolemy.vergil.icon.EditorIcon;
import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.krendering.KBackgroundColor;
import de.cau.cs.kieler.core.krendering.KGridPlacementData;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRendering;
import de.cau.cs.kieler.core.krendering.KRenderingFactory;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.karma.ptolemy.Activator;
import de.cau.cs.kieler.kaom.karma.ptolemy.figurecreation.PtolemyFetcher;

public class KRenderingProvider {

    public static KRendering getKNodeRendering(Annotatable annotatable) {
        StringAnnotation language = (StringAnnotation) annotatable.getAnnotation("language");
        if (language != null && language.getValue().equals("ptolemy") ) {
            Annotation director = annotatable.getAnnotation("Director");
            if (director != null) {
                return getDirectorRendering();
            } else if ((annotatable instanceof Entity) && ((Entity) annotatable).getChildEntities().isEmpty()) {
                return getPtolemySvgRendering(annotatable);
            } else {
                return getDefaultRendering();
            }
        }
        return null;
    }
    
    
    public static KRendering getPtolemySvgRendering(Annotatable annotatable) {
        NamedObj nObj = PtolemyFetcher.getPtolemyInstance(annotatable);
        if (nObj == null) {
            return null;//figureProvider.getErrorFigure();
        } else {
            FigureProviderKRendering figureProvider = new FigureProviderKRendering();
            // get all icons for this element
            List<EditorIcon> icons = PtolemyFetcher.fetchIcons(nObj);
            // if there is none use svg description
            if (icons.isEmpty()) {
                Document doc = PtolemyFetcher.fetchSvgDoc(nObj);
                if (doc != null) {
                    
                    KRendering figure = figureProvider.createFigureFromSvg(doc);
                    return figure;
                } else {
                    Status myStatus = new Status(IStatus.WARNING, Activator.PLUGIN_ID,
                            "couldn't get svg document from ptolemy");
                    StatusManager.getManager().handle(myStatus, StatusManager.SHOW);
                    return null;//figureProvider.getErrorFigure();
                }

                // else use the first icon (usually there should be only one anyway)
            } else {
                EditorIcon icon = icons.get(0);
                KRendering figure = figureProvider.createFigureFromIcon(icon);
                return figure;
            }

        }
    }
    
    public static KRendering getDefaultRendering() {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KRectangle rectangle = factory.createKRectangle();
        return rectangle;
    }
    
    public static KRendering getDirectorRendering() {
        KRenderingFactory factory = KRenderingFactory.eINSTANCE;
        KRectangle rectangle = factory.createKRectangle();
        
        KGridPlacementData placement = factory.createKGridPlacementData();
        placement.setHeightHint(30);
        placement.setWidthHint(100);
        rectangle.setPlacementData(placement);
        
        KBackgroundColor fill = factory.createKBackgroundColor();
        rectangle.getStyles().add((KBackgroundColor) FigureParserKRendering.lookupColor("green", fill));
        
        return rectangle;
    }
    
}
