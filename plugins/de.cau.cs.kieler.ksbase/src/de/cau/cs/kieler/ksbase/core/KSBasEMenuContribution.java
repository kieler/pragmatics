package de.cau.cs.kieler.ksbase.core;

import java.util.LinkedList;


/**
 * A menu contribution, defined by an extension point.
 * Contains a list of commands, sub menus and a data property.
 * If this contribution is a root contribution, the data is a
 * locationURI, else it is the id of a menu. 
 * @author mim
 *
 */
public class KSBasEMenuContribution {
    
    private String data;
    private String label;
    private LinkedList<String> commands;
    private LinkedList<KSBasEMenuContribution> subMenus;
    
    public KSBasEMenuContribution(String data) {
        this.data = data;
        this.commands = new LinkedList<String>();
        this.subMenus = new LinkedList<KSBasEMenuContribution>();
        this.label = "";
    }
    
    public void addCommand(String transformationId) {
        commands.add(transformationId);
        
    }
    
    public void addSubMenu(KSBasEMenuContribution menu) {
        subMenus.add(menu);
    }
    
    public LinkedList<KSBasEMenuContribution> getMenus() {
        return subMenus;
    }
    
    public LinkedList<String> getCommands() {
        return commands;
    }
    
    public String getData()
    {
        return data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
