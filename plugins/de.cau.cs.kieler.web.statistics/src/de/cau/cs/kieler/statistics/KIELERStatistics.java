/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.statistics;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/**
 * A convenience class to gather certain statistic values, e.g. usages of web services.
 * 
 * <b>Important</b> Methods should be fail silent, i.e. if no connection to the server was made, all
 * calls to convenience methods fail silently.
 * 
 * @author uru
 */
public final class KIELERStatistics {

    private static final Map<String, KIELERStatistics> KNOWN_HOSTS = Maps.newHashMap();
    private static final String DB_NAME = "kieler";

    private DB db;
    private MongoClient client;

    /**
     * Private as we have a static creation method.
     */
    private KIELERStatistics(final String host) throws UnknownHostException,
            AuthenticationException {
        this(host, null, null);
    }

    /**
     * Private as we have a static creation method.
     */
    private KIELERStatistics(final String host, final String user, final String pass)
            throws UnknownHostException, AuthenticationException {

        if (host != null) {
            client = new MongoClient(host);
            db = client.getDB(DB_NAME);
            if (user != null && pass != null) {
                boolean succ = db.authenticate(user, pass.toCharArray());
                if (!succ) {
                    throw new AuthenticationException("Could not authenticate, "
                            + "make sure username and password are correct.");
                }
            }
        }

    }

    /**
     * Creates and returns a {@link KIELERStatistics} object. The objects are cached, hence for the
     * same 'host' string the same statistics instance will be returned.
     * 
     * In case the creation fails, exceptions are raised and all method calls to the returned
     * instance will fail silently.
     * 
     * @param host
     *            server to connect to in format host[:port].
     * 
     * @return a new {@link KIELERStatistics} object that can be used to record statistics
     * @throws UnknownHostException
     *             if no connection to the server can be established.
     * @throws AuthenticationException
     *             if username or password were wrong
     */
    public static KIELERStatistics forServer(final String host) throws UnknownHostException,
            AuthenticationException {
        if (KNOWN_HOSTS.containsKey(host)) {
            return KNOWN_HOSTS.get(host);
        } else {
            KIELERStatistics statObj = new KIELERStatistics(host);
            KNOWN_HOSTS.put(host, statObj);
            return statObj;
        }
    }

    /**
     * @return a dummy implementation doing nothing.
     */
    public static KIELERStatistics getDummy() {
        try {
            return new KIELERStatistics(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @see #incIntegerCounter(String, String, int)
     * 
     * @param collection
     *            .
     * @param documentName
     *            .
     */
    public void incIntegerCounter(final String collection, final String documentName) {
        incIntegerCounter(collection, documentName, 1);
    }

    /**
     * Increases the counter of the specified document within the specified collection by the amount
     * of 'inc'. If either of the two elements do not exist, they will be created.
     * 
     * @param collection
     *            .
     * @param documentName
     *            .
     * @param inc
     *            .
     */
    public void incIntegerCounter(final String collection, final String documentName, final int inc) {
        // fail silent
        if (client == null || db == null) {
            return;
        }

        try {
            // get or create the collection
            DBCollection coll = db.getCollection(collection);

            // this finds the correct document
            BasicDBObject queryDocMonth =
                    new BasicDBObject("name", documentName).append("month", getCurrentMonth());

            BasicDBObject queryDocDay =
                    new BasicDBObject("name", documentName).append("day", getCurrentDay());

            // this increases the count value
            BasicDBObject incObj = new BasicDBObject("$inc", new BasicDBObject("count", inc));

            WriteResult resultMonth = coll.update(queryDocMonth, incObj, true, false);
            WriteResult resultDay = coll.update(queryDocDay, incObj, true, false);

            if ((resultMonth != null && resultMonth.getError() != null)
                    || (resultDay != null && resultDay.getError() != null)) {
                System.out.println("Couldn't log stats");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getCurrentMonth() {
        return new SimpleDateFormat("yyyy-MM").format(new Date());
    }

    private String getCurrentDay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

}
