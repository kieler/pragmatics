package de.cau.cs.kieler.karma;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.util.ICondition;

public interface ICustomCondition extends ICondition<EObject> {

    public void initialize(String key, String value);
    
}
