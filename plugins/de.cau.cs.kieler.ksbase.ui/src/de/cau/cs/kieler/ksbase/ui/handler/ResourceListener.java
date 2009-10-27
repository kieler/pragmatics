package de.cau.cs.kieler.ksbase.ui.handler;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

public class ResourceListener implements IResourceChangeListener {

    private class DeltaWalker implements IResourceDeltaVisitor {

        public boolean visit(IResourceDelta delta) throws CoreException {
            System.out.println("visiting : "+delta.getKind());
            return false;
        }
        
    }
    public void resourceChanged(IResourceChangeEvent event) {
        
        System.out.println("event :"+event.getBuildKind()+" | "+event.getDelta().getKind());
        for ( IResourceDelta d: event.getDelta().getAffectedChildren(IResourceDelta.CHANGED)) {
            System.out.println("\t d:"+d);
        }
        try {
            event.getDelta().accept(new DeltaWalker());
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }


}
