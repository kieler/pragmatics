/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author dag
 *
 */
public final class SVGBuilder {
    private static String svg = "<svg><rect x=\"100\" y=\"100\" width=\"500\" height=\"200\" fill=\"white\" stroke=\"black\" stroke-width=\"20px\"/></svg>";
    public static void build() {
        try {
            PrintWriter out = new PrintWriter("debug.svg");
            out.print(svg);
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
