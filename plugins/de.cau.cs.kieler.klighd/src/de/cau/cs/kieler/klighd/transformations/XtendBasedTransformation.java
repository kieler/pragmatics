package de.cau.cs.kieler.klighd.transformations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.XtendFacade;

import de.cau.cs.kieler.klighd.IModelTransformation;

public class XtendBasedTransformation implements IModelTransformation<Object, Object> {

    private static final String TRANSFORMATION_EXTENSION_NAME = "transform";
    
    private String extfile;
    
    public XtendBasedTransformation(String theExtFile) {
        this.extfile = theExtFile;
    }
    
    public EObject transform(Object model) {
        
        XtendFacade facade = XtendFacade.create(this.extfile);
        //FIXME metamodels to be registered
        
        facade.call(TRANSFORMATION_EXTENSION_NAME, model);
        
        return null;
    }

    public boolean isModelSupported(Object model) {
        // TODO Auto-generated method stub
        return false;
    }

}
