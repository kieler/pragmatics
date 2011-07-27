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

import java.util.HashMap;
import de.unido.ls5.eti.common.certificates.parameters.EtiParameter;
import de.unido.ls5.eti.common.certificates.parameters.EtiParameterInt;

/**
 * This is an example certificate which can be used
 * to run a tool x times
 * put the class file in ./certificates/ to get it loaded
 * on server startup
 * 
 * @author David Karla
 *
 */
public class EtiCertificateCount implements EtiCertificate {
	
	private final String description = "Run a tool x times.";
	private final String name ="Count Certificate";
	private HashMap <String, EtiParameter> parameters;
	
	public EtiCertificateCount () {
		this.parameters = new HashMap<String, EtiParameter>();
		EtiParameterInt count = new EtiParameterInt();
		count.setIntValue(0);
		count.setName("Counter");
		count.setDescription("How many times this certificate can be used.");		
		this.addParameter(count);
	}

	private void addParameter(EtiParameter parameter) {		
		if (!this.parameters.containsKey(parameter.getName()))
			this.parameters.put(parameter.getName(), parameter);
	}

	public String getDescription() {
		return this.description;
	}	

	public String getName() {
		return this.name;
	}

	public HashMap<String, EtiParameter> getParameters() {
		return this.parameters;
	}

	public boolean isValid() {
		if (Integer.parseInt(this.getParameters().get("Counter").getValue()) > 0)
			return true;
		else
			return false;
	}

	/**
	 * What happens if the certificate is used?
	 * -- the counter value in this case.
	 */
	public void use() {
		EtiParameter counter = this.getParameters().get("Counter");
		int val = Integer.parseInt(counter.getValue());
		if ( val > 0) {
			val --;
			this.getParameters().get("Counter").setValue(Integer.toString(val));
		}
	}

}
