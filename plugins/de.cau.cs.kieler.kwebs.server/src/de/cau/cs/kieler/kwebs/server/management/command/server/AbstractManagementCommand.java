/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.management.command.server;

import de.cau.cs.kieler.kwebs.server.management.command.client.IManagementExchange;

/**
 *
 * @author  swe
 * 
 */
public abstract class AbstractManagementCommand implements IManagementCommand {

    //////////

	/** */
	private IManagementExchange exchange;
	
	//////////
	
	/**
	 * 
	 * @return the exchange data object associated to this management command
	 */
	public IManagementExchange getExchange() {
		return exchange;
	}

	//////////

	/**
	 * 
	 * @param exchange
	 *            the exchange data object to be associated to this management command
	 * @throws Exception
	 */
	public void initialize(final IManagementExchange exchange) throws Exception {
		this.exchange = exchange;
	}
	
}
