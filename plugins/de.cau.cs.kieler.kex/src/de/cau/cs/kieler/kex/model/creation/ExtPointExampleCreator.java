package de.cau.cs.kieler.kex.model.creation;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;

/**
 * can be used for creating new extension for KEX extension point. So it allows to create new examples or
 * example categories. *
 * 
 * @author pkl
 * 
 */
public class ExtPointExampleCreator {

  public void createExtension(Example example) {
  }

  /**
   * creates example files to given project path
   */
  public void createExampleResources(ExampleResource resource) {
//    List<File> resources = resource.getResources();
//    for (File file : resources) {
//      
//    }
  }

  public IProject[] getLocalProjects() {
   String workspacePath = ResourcesPlugin.getWorkspace().getRoot()
                        .getLocation().toString();
   IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
   for (IProject iProject : projects) {
    System.out.println(iProject.getName());
  }
   //TODO am besten nicht mit den ganzen projekten umher werfen, vielleicht kann man die dateipfade irgendwie raus holen...
                File tmpFile = new File(workspacePath
                        + File.separator + ((projects.length > 0) ? projects[0].getName() : "test")+ File.separator +"test.file"); 
                
                if (!tmpFile.exists()){
                    tmpFile.getParentFile().mkdirs();
                    try {
                      tmpFile.createNewFile();
                    }
                    catch (IOException e) {
                      //TODO fehlerhandling ueberlegen
                      e.printStackTrace();
                    }
                }
                return projects;
  }

  public void getWorkspaceProjects(){
//    Location instanceLocation = Platform.getInstanceLocation();
//    IPath location = Platform.getLocation();
    IExtensionRegistry reg = Platform.getExtensionRegistry();
    IExtensionPoint point = reg.getExtensionPoint("org.eclipse.core.filesystem.filesystems");
    IConfigurationElement[] elements = point.getConfigurationElements();

    System.out.println("There are " + elements.length + " implementations");
    for (int i = 0; i < elements.length; i++) {
      for (String attrString : elements[i].getAttributeNames()) {
        System.out.print(elements[i].getName());
        System.out.print(" " + attrString + ": " + elements[i].getAttribute(attrString));
        System.out.println();
      }
    }
  }
  

}
