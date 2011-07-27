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
/*
 * Created on 19.01.2005
 */
package de.unido.ls5.eti.toolserver;

/**
 * This class should be used as parameter to jETI tools for output files
 * of that tool.
 * 
 * By naming convention (classname.contains("OutputFile")) this is recognized
 * by the sibcreator as an output file and thus will be included in
 * {@code see de.metaframe.jabc.sib.ETI#getOutputFiles()}.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 */
public class OutputFileReference extends FileReference {

	/**
	 * Create a File Reference with s as relative part below session folder
	 * 
	 * @param s the relative part of this file reference
	 * 
	 * @see FileReference#FileReference(String)
	 */
	public OutputFileReference(String s) {
		super(s);
	}
	
}
