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

package de.cau.cs.kieler.kaom.custom;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.karma.IRenderingProvider;

/**
 * 
 * @author ckru
 *
 */
public class KaomFigureProvider implements IRenderingProvider {

    /**
     * {@inheritDoc}
     */
    
    public IFigure getFigureByString(final String input, final IFigure oldFigure, final EObject object) {
        // TODO Auto-generated method stub
        if (input.equals("ptolemyObject")) {
            return getDefaultFigure();
        } else {
            throw new RuntimeException();
        }
        /*
        if (input.equals("ramp")) {
            return createTestSVGFigure1(null, "ramp.svg");
        } else if (input.equals("addsubtract")) {
            return createTestSVGFigure1(null, "addsubtract.svg");
        } else if (input.equals("director")) {
            //org.ptolemy.tiny.ptolemy.actor.lib.Ramp;
            return createDirector();
            */
        
        
    }

    /**
     * {@inheritDoc}
     */
    
    public IFigure getDefaultFigure() {
        // TODO Auto-generated method stub
       // org.ptolemy.
        RectangleFigure defaultFigure = new RectangleFigure();
        defaultFigure.setLineWidth(1);
        defaultFigure.setForegroundColor(ColorConstants.black);
        defaultFigure.setBackgroundColor(ColorConstants.white);
        return defaultFigure;
    }

    /**
     * playing around for learning purposes. ignore this method
     * 
     * @param oldFigure
     * @param file
     * @return 
     */
    
    private IFigure createTestSVGFigure1(final IFigure oldFigure, final String file) {
        /*
         * URL url = FileLocator.find(SyncChartsCustomPlugin.getDefault().getBundle(), new
         * Path("test/one.svg"), null);
         */
        //URI uri = URI.createURI("platform:/de.cau.cs.kieler.synccharts.custom/test/one.svg");
        // String path = uri.path();
    
        String url2 = "E:/kielerworkspace/de.cau.cs.kieler.synccharts.custom/test/" + file; // one.svg";
        return null;
        //IWorkspace workspace = ResourcesPlugin.getWorkspace();
        //IWorkspaceRoot myWorkspaceRoot = workspace.getRoot();
        //IProject myProject = myWorkspaceRoot.getProject("de.cau.cs.kieler.synccharts.custom");
        //IFolder myFolder = myProject.getFolder("test");
        //IFile myFile = myFolder.getFile(file);
        //URI uri = URI.createPlatformResourceURI(myFile.getFullPath().toString(),true);
        // IPath workspacePath = myWorkspaceRoot.getLocation();
        //IPath projectPath = myFile.getFullPath();
        // IPath projectPath2 = myFile.getLocation();
        //String myurl = "platform:" + projectPath.toString();

        // String url3 = "E:/kielerworkspace/de.cau.cs.kieler.synccharts.custom/test/two.svg";
        // RenderedImage img = RenderedImageFactory.getInstance(path);
        //RenderedImage img2 = RenderedImageFactory.getInstance(url2);
        // RenderInfo info = new RenderedImageKey();
        // info.setValues(50, 50, true, true, info.getBackgroundColor(), info.getForegroundColor());
        // RenderedImage img3 = RenderedImageFactory.getInstance(url3, info);
        /*
        if (oldFigure instanceof ScalableImageFigure) {
            ((ScalableImageFigure) oldFigure).setRenderedImage(img2);
            return oldFigure;
        } else {
            ScalableImageFigure fig = new ScalableImageFigure(img2, false, true, true);
            return fig;
        }
        */
        // fig.setRenderedImage(img2);
        // fig.setSize(50, 50);
        // fig.setMinimumSize(fig.getBounds().getSize());
        // fig.getRenderedImage().getRenderInfo().setValues(50, 50, true, true,
        // fig.getRenderedImage().getRenderInfo().getBackgroundColor(),
        // fig.getRenderedImage().getRenderInfo().getForegroundColor());
        // fig.getImage().getImageData().height = 50;
        // return new ScalableImageFigure(RenderedImageFactory.getInstance(url2), true, true, true);
        // return fig;
    }
    
   /*
    private class testFigure extends ScalableImageFigure {
        
        public testFigure (String file) {
            
        }
        
    }
    */
    
    /**
     * 
     * @return
     */
    
    private IFigure createDirector() {
        RectangleFigure rf = new RectangleFigure();
        //this.setCurrentFigure(rf);
        rf.setFill(false);
        
        rf.setForegroundColor(ColorConstants.black);
        rf.setBackgroundColor(ColorConstants.green);
        //rf.setBorder(new MarginBorder(getMapMode().DPtoLP(5), getMapMode().DPtoLP(5),
        //    getMapMode().DPtoLP(5), getMapMode().DPtoLP(5)));
        return rf;
    }

    public LayoutManager getLayoutManagerByString(String input, LayoutManager oldLayoutManager,
            EObject object) {
        // TODO Auto-generated method stub
        return oldLayoutManager;
    }

    public LayoutManager getDefaultLayoutManager() {
        // TODO Auto-generated method stub
        return null;
    }
   
}
