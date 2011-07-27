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
package de.unido.ls5.eti.common.certificates.parameters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author David Karla
 */
public class EtiParameterDate extends EtiParameter{

	private Date value;
	
	public EtiParameterDate () {
		super();
		this.value = new Date();
	}
	
	@Override
	public String getValue() {
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
		return format.format(this.value);		
	}

	@Override
	/**
	 * Date format: yyyy-MM-dd
	 */
	public void setValue(String value) {
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" ); 
		try {
			this.value = format.parse(value);
		} catch (ParseException e) {
			try {
				this.value = format.parse("2007-01-01");
			} catch (ParseException e1) {				
			}
		}		
	}
	
	public Date getDateValue () {
		return this.value;
	}
	
	public void setDateValue (Date value){
		this.value = value;
	}

	@Override
	public int getType() {
		return EtiParameter.DATE;
	}

}
