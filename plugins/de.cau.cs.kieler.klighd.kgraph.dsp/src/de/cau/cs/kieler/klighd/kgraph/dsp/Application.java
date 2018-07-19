package de.cau.cs.kieler.klighd.kgraph.dsp;

import java.util.Map;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

    @Override
    public Object start(final IApplicationContext context) throws Exception {
        final Map<?,?> args = context.getArguments();
        final String[] appArgs = (String[]) args.get("application.args");
        RunSocketServer.main(appArgs);

        return IApplication.EXIT_OK;
    }

    @Override
    public void stop() {
//    nothing to do
    }
}