/**
 * Java Electronic Tool Integration - jETI
 * Copyright (C) 2004-2011 Chair for Programming Systems, TU Dortmund
 *
 * This file is part of jETI.
 *
 * jETI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * jETI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with jETI. If not, see <http://www.gnu.org/licenses/>.
 */
package de.unido.ls5.eti.sps.apis;

/**
 * Provides constants for server side error cases.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public interface ErrorCodeConstants {
	
	/**
	 * 100: Tool does not exist on server.
	 */
	public static final int TOOL_DOES_NOT_EXIST = 100; 
	
	/**
	 * 101: Illegal session.
	 */
	public static final int ILLEGAL_SESSION = 101;
	
	/**
	 * 102: Server Configuration Error.
	 */
	public static final int SERVER_CONFIG_ERROR = 102;
	
	/**
	 * 103: Forward failed.
	 */
	public static final int FORWARD_FAILED = 103;
	
	/**
	 *  104: Execution failed for some reason.
	 */
	public static final int EXECUTION_FAILED = 104;
	
	/**
	 * 105: to-be-retrieved file was not found
	 */
	public static final int FILE_NOT_FOUND = 105;
	
	/**
	 * 106: Execution failed, because required parameter was not set.
	 */
	public static final int REQUIRED_PARAM_NOT_SET = 106;
	
	/**
	 * 107: Class Argument Verification failed
	 */
	public static final int CLASS_ARG_VERIFY_FAILED = 107;
	
	/**
	 * 108: Parameter instanciation failed due to wrong serialization
	 */
	public static final int PARAM_INSTANCIATION_FAILED = 108;
	
	
	
	
	
	
	/**
	 * Error Code 245. Reserved for protocol internal errors.
	 */
	public static final int PROTOCOL_SPECIFIC_1 = 245;
	/**
	 * Error Code 246. Reserved for protocol internal errors.
	 */
	public static final int PROTOCOL_SPECIFIC_2 = 246;
	/**
	 * Error Code 247. Reserved for protocol internal errors.
	 */
	public static final int PROTOCOL_SPECIFIC_3 = 247;
	/**
	 * Error Code 248. Reserved for protocol internal errors.
	 */
	public static final int PROTOCOL_SPECIFIC_4 = 248;
	/**
	 * Error Code 249. Reserved for protocol internal errors.
	 */
	public static final int PROTOCOL_SPECIFIC_5 = 249;
	/**
	 * Error Code 250. Reserved for protocol internal errors.
	 */
	public static final int PROTOCOL_SPECIFIC_6 = 250;
	/**
	 * Error Code 251. Reserved for protocol internal errors.
	 */
	public static final int PROTOCOL_SPECIFIC_7 = 251;
	/**
	 * Error Code 252. Reserved for protocol internal errors.
	 */
	public static final int PROTOCOL_SPECIFIC_8 = 252;
	/**
	 * Error Code 253. Reserved for protocol internal errors.
	 */
	public static final int PROTOCOL_SPECIFIC_9 = 253;
	/**
	 * Error Code 254. Reserved for protocol internal errors.
	 */
	public static final int PROTOCOL_SPECIFIC_10 = 254;
	
	/**
	 * 255: Other Error
	 */
	public static final int OTHER_ERROR = 255;
	
}
