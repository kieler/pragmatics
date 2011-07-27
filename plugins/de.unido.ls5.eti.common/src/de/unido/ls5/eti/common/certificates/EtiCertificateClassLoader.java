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
package de.unido.ls5.eti.common.certificates;

import java.io.FileInputStream;

/**
 * This class loads all class file from the folder ./certificates.
 * It checks if the class files implement the EtiCertificate-interface
 * correctly. Otherwise loading is skipped fo the class file.
 * 
 * This class is used in EtiCertificateManager->loadCertificateClasses()
 * during server startup
 * 
 * @author David Karla
 *
 */

public class EtiCertificateClassLoader extends ClassLoader{
	

		public EtiCertificateClassLoader() {
			super();
		}
		
		private byte[] classBytes = null;

		public byte[] getClassBytes() {
			return classBytes;
		}

		protected Class findClass(String name) throws ClassNotFoundException 
		{
			FileInputStream fi = null;
			try {

				fi = new FileInputStream(name);
				byte[] classBytes = new byte[fi.available()];
				fi.read(classBytes);
				Class c = defineClass(null, classBytes, 0, classBytes.length);
				this.classBytes = classBytes;
				return c;
			} 
			catch (Exception e) 
			{
				throw new ClassNotFoundException(name);
			} 
			finally 
			{
				if (null != fi) {
					try {
						fi.close();
					} catch (Exception e) {
					}
				}
			}
		}
		
		public Class loadClassFromBytes(byte[] classBytes) throws ClassNotFoundException 
		{
			try {
				Class c = defineClass(null, classBytes, 0, classBytes.length);
				this.classBytes = classBytes;
				return c;
			} 
			catch (Exception e) 
			{
				throw new ClassNotFoundException("unknown");
			} 
		}
	


}
