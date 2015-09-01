/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.gwt.linker;

import java.util.Set;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.CompilationResult;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.dev.util.DefaultTextOutput;

/**
 * @author uru
 */
@LinkerOrder(LinkerOrder.Order.PRIMARY)
public class PlainJSLinker extends AbstractLinker {

    /**
     * {@inheritDoc}
     */
    public ArtifactSet link(final TreeLogger logger, final LinkerContext context,
            final ArtifactSet artifacts) throws UnableToCompleteException {

        ArtifactSet toReturn = new ArtifactSet(artifacts);
        DefaultTextOutput out = new DefaultTextOutput(true);

        // get compilation result
        Set<CompilationResult> results = artifacts.find(CompilationResult.class);
        CompilationResult result = results.iterator().next();
        // get the generated javascript
        String[] javaScript = result.getJavaScript();

        out.print("var klayregister;");
        out.newline();
        out.print("var klaycallback;");
        out.newline();
        out.print("(function(){");
        out.newline();
        out.print("var $wnd, $doc;");
        out.print("if(typeof(window) !== 'undefined'){ $wnd = window; $doc = $wnd.document; }");
        out.newline();
        out.print("else { $wnd = { Array: function(){} }; }"); // fake wnd
        out.newline();
        out.print(javaScript[0]);
        out.newline();

        // create dummies for several things requested by by gwt
        out.print("var $moduleName, $moduleBase, ");
        out.print("$stats = function(){}, ");
        out.print("$sessionId = function(){};");
        out.newline();
        out.print("gwtOnLoad(null,'" + context.getModuleName() + "',null);");
        out.newline();
        out.print("})();");
        out.newline();

        String filename = "klay.js";

        toReturn.add(emitString(logger, out.toString(), filename));

        return toReturn;
    }

    @Override
    public String getDescription() {
        return "plain javascript linker - generates javascript that can be"
                + " used with html web workers and node js.";
    }

}
