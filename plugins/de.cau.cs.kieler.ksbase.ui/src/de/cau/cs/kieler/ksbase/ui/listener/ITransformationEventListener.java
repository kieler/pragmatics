package de.cau.cs.kieler.ksbase.ui.listener;

/**
 * Interface for a transformation listener. This listener has to be registered
 * in the {@link de.cau.cs.kieler.ksbase.ui.TransformationUIManager} and is
 * notified after each transformation.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
public interface ITransformationEventListener {

    void transformationExecuted(Object[] args);
}
