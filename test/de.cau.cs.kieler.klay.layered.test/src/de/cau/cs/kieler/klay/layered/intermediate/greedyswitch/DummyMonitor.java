package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;

public class DummyMonitor implements IKielerProgressMonitor {

    public DummyMonitor() {
    }

    public void worked(final float work) {
    }

    public IKielerProgressMonitor subTask(final float work) {
        return this;
    }

    public boolean isRunning() {
        return true;
    }

    public boolean isCanceled() {
        return false;
    }

    public String getTaskName() {
        return "test task";
    }

    public List<IKielerProgressMonitor> getSubMonitors() {
        return new ArrayList<IKielerProgressMonitor>();
    }

    public IKielerProgressMonitor getParentMonitor() {
        return this;
    }

    public double getExecutionTime() {
        return 0.1d;
    }

    public void done() {
    }

    public boolean begin(final String name, final float totalWork) {
        return true;
    }

}
