/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd.transformation

import com.google.inject.Inject
import de.cau.cs.kieler.core.krendering.KRendering
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.ptolemy.klighd.transformation.util.GraphicsUtils
import diva.canvas.CanvasUtilities
import diva.canvas.Figure
import diva.canvas.toolbox.ImageFigure
import java.awt.AlphaComposite
import java.awt.Color
import java.awt.Image
import java.awt.RenderingHints
import java.awt.geom.Rectangle2D$Double
import java.awt.image.BufferedImage
import java.util.List
import org.w3c.dom.Document
import ptolemy.data.expr.XMLParser
import ptolemy.kernel.Entity
import ptolemy.kernel.util.ConfigurableAttribute
import ptolemy.moml.test.TestIconLoader
import ptolemy.vergil.icon.EditorIcon

/**
 * Provides methods to retrieve KRenderings for the SVG graphics that specify how Ptolemy objects look.
 * 
 * @author ckru
 * @author cds
 */
class PtolemyFigureInterface {
    
    /** Instantiating Ptolemy entities. */
    @Inject extension PtolemyInterface
    /** KRendering utility methods. */
    @Inject extension KRenderingExtensions
    
    /** Factory used to instantiate KRendering classes. */
    val renderingFactory = KRenderingFactory::eINSTANCE
    
    
    /**
     * Tries to return a KRendering of actors with the given class name.
     * 
     * @param className class name of the actor.
     * @return the actor's KRendering representation or {@code null} if there was a problem.
     */
    def KRendering createPtolemyFigureRendering(String className) {
        // Try to instantiate the Ptolemy entity
        var Entity entity = null;
        try {
            entity = instantiatePtolemyEntity(className)
        } catch (Exception e) {
            return null;
        }
        
        // Try to load all icons for this element
        val List<EditorIcon> icons = loadIconsForEntity(entity)
        if (icons.empty) {
            // We couldn't load any icons; try to load SVG description and turn it into a KRendering
            val Document svgDocument = loadSvgForEntity(entity)
            val figure = GraphicsUtils::createFigureFromSvg(svgDocument)
            return figure
        } else {
            return createRenderingFromIcon(icons.get(0))
        }
    }
    
    /**
     * Turns an editor icon into a proper rendering.
     * 
     * @param icon the icon.
     * @return the KRendering representation of the icon.
     */
    def private KRendering createRenderingFromIcon(EditorIcon icon) {
        val ptFigure = icon.createBackgroundFigure()
        GraphicsUtils::repairEditorIcon(icon, ptFigure)
        
        val figureImage = ptFigure.toImage()
        val width = figureImage.getWidth(null)
        val height = figureImage.getHeight(null)
        val resizedImage = new BufferedImage(width, height, BufferedImage::TYPE_INT_RGB)
        
        val graphics = resizedImage.createGraphics()
        graphics.setComposite(AlphaComposite::Src)
        graphics.setRenderingHint(RenderingHints::KEY_INTERPOLATION,
            RenderingHints::VALUE_INTERPOLATION_BILINEAR)
        graphics.setRenderingHint(RenderingHints::KEY_RENDERING,
            RenderingHints::VALUE_RENDER_QUALITY)
        graphics.setRenderingHint(RenderingHints::KEY_ANTIALIASING,
            RenderingHints::VALUE_ANTIALIAS_ON)
        graphics.drawImage(figureImage, 0, 0, null)
        graphics.dispose()
        
        val swtImage = GraphicsUtils::convertToSwt(resizedImage)
        
        // Now that we have the image, create a KRendering version of it
        val kImage = renderingFactory.createKImage()
        kImage.imageObject = swtImage
        kImage.setAreaPlacementData(
            createKPosition(LEFT, 0, 0, TOP, 0, 0),
            createKPosition(LEFT, swtImage.width, 0, TOP, swtImage.height, 0))
        
        return kImage
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Loading Actor Graphics
    
    /**
     * Loads the EditorIcons for a given entity.
     * 
     * @param entity the entity.
     * @return list of editor icons or {@code null} if none could be loaded.
     */
    def private List<EditorIcon> loadIconsForEntity(Entity entity) {
        // strange ptolemy stuff. Apparently only way to get the icons of Entities
        // using this display method without having an actual Vergil editor.
        val loader = new TestIconLoader()
        
        try {
            loader.loadIconForClass(entity.className, entity)
            return entity.attributeList(typeof(EditorIcon))
        } catch (Exception e) {
            return null
        }
    }
    
    /**
     * Loads an SVG description of the figure used to represent the given entity, if any.
     * 
     * @param entity the entity.
     * @return the SVG description, if any could be loaded without problems.
     */
    def private Document loadSvgForEntity(Entity entity) {
        var svgString = (entity.getAttribute("_iconDescription") as ConfigurableAttribute).configureText
        var Document svgDocument = null
        
        try {
            val xmlParser = new XMLParser()
            
            // Try to parse the XML (we're preemptively repairing the SVG string here; we originally did
            // that only when a first initial parsing attempt failed, but that resulted in occasional
            // error messages that we are unable to suppress)
            svgString = GraphicsUtils::repairString(svgString)
            svgDocument = xmlParser.parser(svgString)
            svgDocument = GraphicsUtils::repairSvg(svgDocument)
            
            return svgDocument
        } catch (Exception e) {
            return null
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Graphics Stuff
    
    /**
     * Tries to turn the given Diva figure into an AWT image.
     * 
     * @param figure the Diva figure.
     * @return the AWT image or {@code null} if anything went wrong.
     */
    def private Image toImage(Figure figure) {
        if (figure instanceof ImageFigure) {
            // An ImageFigure has an image ready for us to use
            val image = (figure as ImageFigure).image
            if (image == null) {
                return null;
            } else {
                return image.getScaledInstance(
                    image.getWidth(null), image.getHeight(null), Image::SCALE_DEFAULT)
            }
        } else {
            // It's not an ImageFigure, so try to get some SWT graphics stuff and turn that into
            // an image
            val bounds = figure.bounds
            val size = new Rectangle2D$Double(0, 0, bounds.width, bounds.height)
            val transform = CanvasUtilities::computeFitTransform(bounds, size)
            figure.transform(transform)
            
            val image = new BufferedImage(
                bounds.width as int, bounds.height as int, BufferedImage::TYPE_4BYTE_ABGR)
            
            val graphics = image.createGraphics()
            graphics.setRenderingHint(
                RenderingHints::KEY_ANTIALIASING, RenderingHints::VALUE_ANTIALIAS_ON)
            graphics.setBackground(new Color(255, 255, 255, 255))
            
            graphics.clearRect(0, 0, bounds.width as int, bounds.height as int)
            figure.paint(graphics)
            
            return image
        }
    }
}