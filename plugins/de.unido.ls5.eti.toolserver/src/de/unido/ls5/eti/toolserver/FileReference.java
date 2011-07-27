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
 *  This class must be used as parent for any Parameter to jETI tools that
 *  requires file opereations within the session Folder.
 *  
 *  It represents a file within the users session folder.
 *  
 *  The constructor will only receive the relative filename below the session
 *  folder, because the client cannot know the session folder. the pathPrefix
 *  is added by {@link EtiExecutorImpl}.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 */
public class FileReference {
	
	private String filename;
	
	/**
	 * The session folder path, as set by {@link EtiExecutorImpl}
	 */
	protected String pathPrefix;
	
	/**
	 * Sets the filename of this reference
	 * @param s the filename to set
	 */
	public void setFilename(String s) {
		filename = s;
	}
	
	/**
	 * Retrieves the relative filename
	 * @return the relative filename
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * Constructor that sets an empty filename
	 */
	protected FileReference() {
		filename = "";
	}
	
	/**
	 * Constructor with relative filename
	 * 
	 * @param s the relative filename
	 */
	public FileReference(String s) {
		filename = s;
	}
	
	/**
	 * Sets the path prefix
	 * @param pp the path prefix to set
	 */
	public void addPathPrefix(String pp) {
		pathPrefix = pp;
	}
	
	/**
	 * retrieves the pathPrefix + filename. This should give a
	 * valid path to the intended file. {@link RuntimeUnix} for example
	 * requires this.
	 * 
	 * @return the path to the file, with leading session folder
	 */
	public String toString() {
		//return absoluteFilename;
		return pathPrefix + filename;
	}
	
	
}

