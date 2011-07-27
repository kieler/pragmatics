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

import java.io.File;
import java.io.FilenameFilter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.Logger;

import de.unido.ls5.eti.common.Database;
import de.unido.ls5.eti.common.certificates.parameters.EtiParameter;

/**
 * @author David Karla
 */
public class EtiCertificateManager {
	
	private static Logger logger = Logger.getLogger(EtiCertificateManager.class);
	
	
	protected static EtiCertificateManager instance = null;
	private HashMap  <String, Class> certificateClasses; 
	private Database db;
		
	public static EtiCertificateManager getInstance(){
        if (instance == null) {
            instance = new EtiCertificateManager();
        }
        return instance;
	}
	
	private EtiCertificateManager () {
		this.certificateClasses = new HashMap <String,Class>();
		this.loadCertificateClasses();
		this.db = Database.getInstance();
	}
	
	/**
	 * Returns a String vector with the names of the certificates
	 * @return
	 */
	public Vector<String> getCertificateClassList () {
		Vector<String> names = new Vector<String>();
		Iterator<String> iter = this.certificateClasses.keySet().iterator();
		while (iter.hasNext())
			names.add(iter.next());
		return names;
	}
	
	/**
	 * Load all java .class file from the folder
	 * ./certificates. Check if the files contain valid classes.
	 * Create a list of	them.
	 */
	public void loadCertificateClasses () {
		String[] classFiles = this.getClassFiles();		
		// check if the class files contain valid certificate classes
		for (int i = 0; i < classFiles.length; i++){
			String filename = "./certificates/" + classFiles[i];					
			EtiCertificateClassLoader cl = new EtiCertificateClassLoader();
			String classfile = filename;
			try {
				Class current = cl.loadClass(classfile);
				// System.out.println ("Class loaded " + filename);				
				for (int j = 0; j < current.getInterfaces().length; j++){
					// System.out.println (filename + " impl. interface: " + current.getInterfaces()[j].getName());
					if (current.getInterfaces()[j].getName().equals("de.unido.ls5.eti.common.certificates.EtiCertificate")){
						try {
							EtiCertificate newInstance = (EtiCertificate)current.newInstance();							
							this.certificateClasses.put(newInstance.getClass().getName(), current);
							logger.info("Certificates: " + this.certificateClasses.size());
							logger.info("Certificate loaded: "+newInstance.getName() + " - " + filename);
						} catch (InstantiationException e) {
							logger.error("Could not create instance of " + filename);
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							logger.error("Could not create instance of " + filename);
							e.printStackTrace();
						}
					}
				}
				
			} catch (ClassNotFoundException e) {
				logger.warn("Error loading certificate class file: "+ filename);
				e.printStackTrace();
			}			
		}
	}
	
	/**
	 * Remove a certificate from the database
	 * 1. parameters for the certificate
	 * 2. the certificate
	 * @param certificateId
	 */
	public void remove (int certificateId){
		// delete params
		db.update("DELETE FROM certificateparams WHERE certificateid="+certificateId);
		// delete cert
		db.update("DELETE FROM certificate WHERE certid="+certificateId);
	}	
	
	/**
	 * When a certificate is used the parameter values might change
	 * Update the values in db. 
	 */
	public void update (EtiRuntimeCertificate cert){
		// update parameters only
		Iterator<EtiParameter> iter = cert.getParameters().values().iterator();
		while (iter.hasNext()) {
			EtiParameter current = iter.next();
			db.update("UPDATE certificate SET value='"+current.getValue()+"'WHERE certificateid="+cert.getCertificateId()+ " AND name='"+current.getName()+"'");	
		}		
	}
	
	/**
	 * Store a sold certificate to the database
	 * @param cert
	 */
	public void add (EtiRuntimeCertificate cert){
		calcHashcode(cert);		
		cert.setRegisterDate(new Timestamp(System.currentTimeMillis()));
		System.out.println ("INSERT INTO certificate (type, hashcode, toolname, user, registerdate) VALUES ('"+
				cert.getCertificateType().getClass().getName() + "','"+cert.getHashcode()+"', '"+cert.getToolname()+"','"+cert.getUsername()+"', '"+cert.getRegisterDate().toString()+"')");
		db.update("INSERT INTO certificate (type, hashcode, toolname, user, registerdate) VALUES ('"+
				cert.getCertificateType().getClass().getName() + "','"+cert.getHashcode()+"', '"+cert.getToolname()+"','"+cert.getUsername()+"', '"+cert.getRegisterDate().toString()+"')");
		// get the id of the stored certificate
		ResultSet rs = db.getQueryResult("SELECT certid FROM certificate WHERE hashcode='"+cert.getHashcode()+"'");
		try {
			while (rs.next()){
				cert.setCertificateId(rs.getString("certid"));	
			}
		} catch (SQLException e) {
			logger.warn ("Error storing certificate " + cert.getHashcode());
		}
		// store parameters
		Iterator<EtiParameter> iter = cert.getCertificateType().getParameters().values().iterator();
		while (iter.hasNext()) {
			EtiParameter current = iter.next();
			System.out.println ("INSERT INTO certificateparams (certificateid, type, name, value) VALUES ('"+
					cert.getCertificateId()+"','"+current.getClass().getName()+"','"+current.getName()+"', '"+current.getValue()+"')");
			db.update("INSERT INTO certificateparams (certificateid, type, name, value) VALUES ('"+
					cert.getCertificateId()+"','"+current.getClass().getName()+"','"+current.getName()+"', '"+current.getValue()+"')");
		}
	}
	
	/**
	 * return a list of all class files in the directory
	 * ./certificates
	 */
	private String[] getClassFiles (){
		File dir = new File("./certificates");
		String[] files = dir.list();
		
		FilenameFilter filter = new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.endsWith(".class");
	        }
	    };
	    files = dir.list(filter);		
		return files;
	}
	
	/**
	 * Creates a list for the webinterface with %count certificates
	 * starting with %offset certificate. The list isloaded from the db.
	 * The list can be grouped by %searchoptions (e.g. username, date)
	 * @param offset
	 * @param count
	 * @param searchOptions
	 * @return
	 */
	public Vector<EtiRuntimeCertificate> getCertList(int offset, int count, String searchOptions){
		Vector<EtiRuntimeCertificate> certList = new Vector<EtiRuntimeCertificate>();
		ResultSet rs = null;

		if (searchOptions.equals("user")) 
			rs = db.getQueryResult("SELECT LIMIT "+offset+" "+count+" * FROM certificate ORDER BY user");
		else if (searchOptions.equals("date")) 
			rs = db.getQueryResult("SELECT LIMIT "+offset+" "+count+" * FROM certificate ORDER BY registerdate");
		// other cases -> sort by tool
		else
			rs = db.getQueryResult("SELECT LIMIT "+offset+" "+count+" * FROM certificate ORDER BY toolname");		
		try {
			while (rs.next()) {				
				String type = rs.getString("type");
				EtiRuntimeCertificate cert = new EtiRuntimeCertificate();
				cert.setCertificateType((EtiCertificate)this.getCertificateClasses().get(type).newInstance());
				cert.setCertificateId(Integer.toString(rs.getInt("certid")));
				cert.setName(type);
				cert.setHashcode(rs.getString("hashcode"));
				cert.setToolname(rs.getString("toolname"));
				cert.setUsername (rs.getString("user"));
				cert.setRegisterDate(rs.getTimestamp("registerdate"));
				// 	add the parameter values
				ResultSet rs2 = db.getQueryResult("SELECT * FROM CERTIFICATEPARAMS WHERE CERTIFICATEID="+cert.getCertificateId());
				while (rs2.next()){
					String name = rs2.getString("name");
					EtiParameter current = cert.getCertificateType().getParameters().get(name);
					current.setValue(rs2.getString("value"));
				}
				certList.add(cert);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return certList;
	}
	
	/**
	 * Randomly genererates a hashcode (the certificate id)
	 * @param rtCert
	 */
	public static void calcHashcode (EtiRuntimeCertificate rtCert){
		Date myDate = new Date();
		String hashString = myDate.toString() + rtCert.getToolname() + rtCert.getUsername();
		rtCert.setHashcode(Integer.toString(hashString.hashCode()));
	}

	public HashMap<String, Class> getCertificateClasses() {
		return certificateClasses;
	}

	public void setCertificateClasses(HashMap<String, Class> certificateClasses) {
		this.certificateClasses = certificateClasses;
	}
	
	/**
	 * Load a specific certificate from the database
	 * @param hashcode
	 * @return
	 */
	public EtiRuntimeCertificate load (String hashcode) {
		EtiRuntimeCertificate cert = new EtiRuntimeCertificate();
		ResultSet rs = db.getQueryResult("SELECT * FROM certificate WHERE hashcode="+hashcode);
		try {
			while (rs.next()) {
				String type = rs.getString("type");
				cert.setCertificateType((EtiCertificate)this.getCertificateClasses().get(type).newInstance());
				cert.setCertificateId(Integer.toString(rs.getInt("certid")));
				cert.setName(type);
				cert.setHashcode(rs.getString("hashcode"));
				cert.setToolname(rs.getString("toolname"));
				cert.setUsername (rs.getString("user"));
				cert.setRegisterDate(rs.getTimestamp("registerdate"));
				// 	add the parameter values
				ResultSet rs2 = db.getQueryResult("SELECT * FROM CERTIFICATEPARAMS WHERE CERTIFICATEID="+cert.getCertificateId());
				while (rs2.next()){
					String name = rs2.getString("name");
					EtiParameter current = cert.getCertificateType().getParameters().get(name);
					current.setValue(rs2.getString("value"));
				}			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cert;
	}

	/**
	 * How many certificates are stored in the database?
	 * @return
	 */
	public int getCertificateCount() {
		int count = 0;
		Database db = Database.getInstance();
		ResultSet rs = db.getQueryResult("SELECT COUNT (certid) AS ct FROM certificate");
		try {
			rs.next();
			count = rs.getInt("ct");
		} catch (SQLException e) {
			logger.warn("Could not count certificate objects.");
			e.printStackTrace();
		}
		return count;
	} 

}
