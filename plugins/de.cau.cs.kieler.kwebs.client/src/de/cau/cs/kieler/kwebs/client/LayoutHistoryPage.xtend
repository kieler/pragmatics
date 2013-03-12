/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client

import java.text.NumberFormat

/**
 * Utility class for generating HTML representation of the collected statistics.
 *
 * @kieler.rating 2011-05-14
 *
 * @author swe
 */
class LayoutHistoryPage {

    /** Constant for calculating seconds out of nano seconds. */
    private static double FACTOR_NANOTOSECONDS
        = Double::valueOf("1e-9")

    /** Formatter for double values. */
    private static NumberFormat numberFormat
        = NumberFormat::instance

    /**
     *
     * @return
     */
    def static String generateHtml() {

        return
            '''
            <html>
                <head>
                    <style type='text/css'>
                        <!--
                            body { 
                                margin           : 20px;
                            }
                            table.chart {
                                border-style     : solid; 
                                border-width     : 0px 0px 1px 1px; 
                                border-color     : #000000; 
                            }
                            td.bar { 
                                width            : 40px;
                            }
                            td.spacer { 
                                width            : 20px;
                            }
                            .centeredtext { 
                                font-family      : Sans-Serif; 
                                font-size          : 7pt; 
                                text-align          : center; 
                                padding-bottom   : 5px;
                            }
                            .lefttext { 
                                font-family      : Sans-Serif; 
                                font-size          : 7pt; 
                                text-align          : left; 
                                padding-bottom   : 5px;
                            }
                            .red { 
                                background-color : #f00000;
                            }
                            .green { 
                                background-color : #00f000;
                            }
                            .blue { 
                                background-color : #0000f0;
                            }
                            .yellow { 
                                background-color : #f0f000;
                            }
                            .cyan { 
                                background-color : #00f0f0;
                            }
                            .magenta { 
                                background-color : #f000f0;
                            }
                        //-->
                    </style>
                </head>
                <body>
                    <table class='chart' cellspacing='0' cellpadding='0' height='230px'>
                        <tr height='10px'>
                        <td class='text'></td>
                        «LayoutHistory::INSTANCE.statistics.map(s | 
                            '''
                            <td class='centeredtext' colspan='3' align='middle'>
                                «numberFormat.format(s.timeTotal * FACTOR_NANOTOSECONDS)» sec
                            </td>
                            '''
                        ).join»
                        <td class='text'></td>
                        </tr>
                        <tr height='10px'>
                        <td class='text'></td>
                        «LayoutHistory::INSTANCE.statistics.map(s |
                            '''
                            <td class='centeredtext' colspan='3' align='middle'>
                                «s.elementCount» elements
                            </td>
                            '''
                        ).join»
                        <td class='text'></td>
                        </tr>
                        <tr height='10px'>
                        <td class='text'></td>
                        «LayoutHistory::INSTANCE.statistics.map(s |
                            '''
                            <td class='centeredtext' colspan='3' align='middle'>
                                «s.bytes»«if (s.compression) ''' (C)'''»
                            </td>
                            '''
                        ).join»
                        <td class='text'></td>
                        </tr>
                        <tr height='150px'>
                        <td class='spacer'></td>
                        «LayoutHistory::INSTANCE.statistics.map(s |            
                            '''
                            <td class='spacer'></td>
                            <td style='vertical-align:bottom;'>
                                <table cellspacing='0' cellpadding='0' height='100%'>
                                    <tr class='green' style='height:" + «s.layoutPart» + "%;'><td class='bar'></td></tr>
                                    <tr class='blue' style='height:" + «s.networkPart» + "%;'><td class='bar'></td></tr>
                                    <tr class='red' style='height:" + «s.remoteSupplementalPart» + "%;'><td class='bar'></td></tr>
                                    <tr class='yellow' style='height:" + «s.localSupplementalPart» + "%;'><td class='bar'></td></tr>
                                </table>
                            </td>
                            <td class='spacer'></td>
                            '''
                        ).join»
                        <td class='spacer'></td>
                        </tr>
                    </table>
                    <p class='lefttext'>
                        All layout durations are normalized to 100%. The colored parts represent the percentage of the layout time the according component of the layout process did take.
                    </p>
                    <p>
                        <table cellspacing='0' cellpadding='0'>
                            <tr>
                                <td class='green' width='50px'></td><td class='lefttext'>&nbsp;&nbsp;&nbsp;Layout&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            </tr>
                            <tr>
                                <td class='blue' width='50px'></td><td class='lefttext'>&nbsp;&nbsp;&nbsp;Network transfer</td>
                            </tr>
                            <tr>
                                <td class='red' width='50px'></td><td class='lefttext'>&nbsp;&nbsp;&nbsp;Remote supplementary operations&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            </tr>
                            <tr>
                                <td class='yellow' width='50px'></td><td class='lefttext'>&nbsp;&nbsp;&nbsp;Local supplementary operations&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            </tr>
                        </table>
                    </p>
                </body>
            </html>
            '''.toString

    }

    /**
     * Private constructor.
     */
    private new() {
    }

}
