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

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import de.unido.ls5.eti.common.certificates.parameters.EtiParameter;


/**
 * This class reperesents a sold certificate.
 * There are two types of certificates: The raw certificates which
 * just store information about the certificate itself (name, parameters)
 * and the EtiRuntimeCertificates (this class) which store additional information
 * about the current values of the parameters and the id of the
 * certificate (which have to be stored to the db). 
 * This means that an object of this class represents
 * a specific sold certificate and not just information about
 * the type of the certificate. 
 * 
 * @author David Karla
 *
 */
public class EtiRuntimeCertificate {

	private String type;
	private String name;
	private EtiCertificate certificateType;
	private String username;
	private String hashcode;
	private String toolname;
	private String certificateId;
	private Timestamp registerDate;
	
	/**
	 * Genererate a hashcode (id) for this certificate
	 * @return
	 */
	public String calculateHashcode () {
		Date now = new Timestamp(System.currentTimeMillis());		
		int inthashcode = now.toString().hashCode() + this.getUsername().hashCode() + this.getToolname().hashCode() +
							this.type.hashCode();
		String hashcode = Integer.toString(inthashcode);
		this.hashcode = hashcode;
		return hashcode;
	}
	
	public String getCertificateId() {
	return certificateId;
}
public void setCertificateId(String certificateId) {
	this.certificateId = certificateId;
}
	public EtiCertificate getCertificateType() {
		return certificateType;
	}
	
	/**
	 * Set the type of the certificate (Means: instance of which class)
	 * @param certificateType
	 */
	public void setCertificateType(EtiCertificate certificateType) {
		this.certificateType = certificateType;
		this.name = certificateType.getName();
		this.type = certificateType.getClass().getName();
	}
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	public String getToolname() {
		return toolname;
	}
	public void setToolname(String toolname) {
		this.toolname = toolname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Timestamp getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public HashMap<String, EtiParameter> getParameters (){
		return this.certificateType.getParameters();
	}
	
	public void use () {
		this.certificateType.use();
	}
	
	public boolean isValid () {
		return this.getCertificateType().isValid();
	}

	/**
	 * Just for debug: Show content of the certificate
	 */
	public String toString () {
		String output = this.getName() + ":" + this.getType() + "\n" +
						this.certificateId + ":" + this.hashcode + ":"+this.getToolname() + ":"+this.getUsername() +
						"\nParameters:\n";
		Iterator <EtiParameter> iter = this.getParameters().values().iterator();
		while (iter.hasNext()){
			output = output + iter.next().toString();
		}
		return output;						
	}
	
	public String getRegisterDateString () {
		return this.registerDate.toString();
	}
}
