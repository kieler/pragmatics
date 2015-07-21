/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.statistics;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Maps;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A convenience class to gather certain statistic values, e.g. usages of web services.
 * 
 * <b>Important</b> Methods should be fail silent, i.e. if no connection to the server was made, all
 * calls to convenience methods fail silently.
 * 
 * Statistics are divided into collections and documents. A collection might be called
 * 'de.cau.cs.kieler.kwebs' and contain different types of statistics related to kwebs. Different
 * statistics are to be stored in different documents. A document may hold the daily usage of
 * successful live layout requests ('kwebs.livelayout.succ').
 * 
 * There are documents that hold counters for with an optional key as further identifier as well as
 * documents that hold hold key/value pairs with a timestamp.
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
            
            // check if we can access the database
            try {
                db.getCollectionNames();
            } catch (Exception e) {
                throw new UnknownHostException("Could not connect to database.");
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
     * Specifies the granularity with which stats are recorded.
     */
    public static class Granularity {

        /** Exact timestamp (ms) is stored. */
        public static final int EXACT = 1;
        /** The day. */
        public static final int DAY = 2;
        /** The month. */
        public static final int MONTH = 4;
        /** The year. */
        public static final int YEAR = 8;

    }

    private static final String ID_KLAY_LAYERED = "de.cau.cs.kieler.klay.layered";

    private static Set<Class<?>> supportedTypes;
    static {
        supportedTypes = new HashSet<Class<?>>();
        supportedTypes.add(String.class);
        supportedTypes.add(Character.class);
        supportedTypes.add(Boolean.class);
        supportedTypes.add(Byte.class);
        supportedTypes.add(Short.class);
        supportedTypes.add(Long.class);
        supportedTypes.add(Integer.class);
        supportedTypes.add(Float.class);
        supportedTypes.add(Double.class);
    }

    /**
     * Only inspects the top level graph element, hence does not look into hierarchical nodes.
     * 
     * @param graph
     *            the graph to inspect.
     */
    public void recordKlayLayeredStats(final KNode graph) {

        // if it is klay layered, get some more insight into the configuration
        KLayoutData data = graph.getData(KLayoutData.class);
        String alg = data.getProperty(LayoutOptions.ALGORITHM);

        // get the exact property list
        if (alg != null && alg.equals(ID_KLAY_LAYERED)) {
            for (Entry<IProperty<?>, Object> entry : data.getProperties()) {
                if (entry.getValue() != null && supportedTypes.contains(entry.getValue().getClass())) {
                    recordValue(ID_KLAY_LAYERED, entry.getKey().getId(), entry.getValue());
                }
            }
        }
    }

    /**
     * Stores a certain value for the document along a timestamp.
     * 
     * Can be used to record the usage of a value of a layout option at a certain point in time. In
     * this case the 'documentName' is the layout option and the 'value' is the used value.
     * 
     * @param collection
     *            name of the collection
     * @param documentName
     *            name of the document
     * @param value
     *            a value to store in the document
     */
    public void recordValue(final String collection, final String documentName, final Object value) {
        // fail silent
        if (client == null || db == null) {
            return;
        }

        try {
            // get or create the collection
            DBCollection coll = db.getCollection(collection);

            // this finds the correct document
            BasicDBObject insertDoc = new BasicDBObject("name", documentName);
            insertDoc.append("value", value);
            insertDoc.append("timestamp", new Date());

            WriteResult result = coll.insert(insertDoc);

            if ((result != null && result.getError() != null)) {
                throw new RuntimeException("Error logging statistics: " + result.getError());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CHECKSTYLEOFF Javadoc convenient methods
    /**
     * @see #incCounter(String, String, String, String, int)
     */
    public void incCounter(final String collection, final String documentName, final int gran) {
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("name", documentName);
        incCounter(collection, queryMap, gran, 1);
    }

    /**
     * @see #incCounter(String, String, String, String, int)
     */
    public void incCounter(final String collection, final String documentName, final String value,
            final int gran) {
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("name", documentName);
        queryMap.put("value", value);
        incCounter(collection, queryMap, gran, 1);
    }

    /**
     * @see #incCounter(String, String, String, String, int)
     */
    public void incCounter(final String collection, final String documentName, final String key,
            final String value, final int gran) {
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("name", documentName);
        queryMap.put("key", key);
        queryMap.put("value", value);
        incCounter(collection, queryMap, gran, 1);
    }

    /**
     * @see #incCounter(String, String, String, String, int)
     */
    public void incCounter(final String collection, final Map<String, Object> match,
            final int granularity, final int inc) {
        Map<String, Object> vals = Collections.emptyMap();
        incCounter(collection, match, vals, granularity, inc);
    }

    // CHECKSTYLEON Javadoc

    /**
     * Increases a counter for the specified document.
     * 
     * @param collection
     *            in which the document should be stored.
     * @param match
     *            a map holding key value pairs that are used to match existing documents in the
     *            database.
     * @param values
     *            values that will be set for the found document.
     * @param granularity
     *            with which granularity the stat should be recorded, see {@link Granularity}.
     * @param inc
     *            number by which to increase the counter.
     */
    public void incCounter(final String collection, final Map<String, Object> match,
            final Map<String, Object> values, final int granularity, final int inc) {
        if ((granularity & Granularity.EXACT) != 0) {
            incCounterInternal(collection, match, values, Granularity.EXACT, inc);
        }
        if ((granularity & Granularity.DAY) != 0) {
            incCounterInternal(collection, match, values, Granularity.DAY, inc);
        }
        if ((granularity & Granularity.MONTH) != 0) {
            incCounterInternal(collection, match, values, Granularity.MONTH, inc);
        }
        if ((granularity & Granularity.YEAR) != 0) {
            incCounterInternal(collection, match, values, Granularity.YEAR, inc);
        }
    }

    private void incCounterInternal(final String collection, final Map<String, Object> match,
            final Map<String, Object> values, final int granularity, final int inc) {

        // fail silent
        if (client == null || db == null) {
            return;
        }

        try {
            // get or create the collection
            DBCollection coll = db.getCollection(collection);

            // this finds the correct document
            BasicDBObject queryDoc = new BasicDBObject();
            for (Entry<String, Object> entry : match.entrySet()) {
                queryDoc.append(entry.getKey(), entry.getValue());
            }

            // add the granularity
            if (Integer.bitCount(granularity) != 1) {
                throw new RuntimeException("Cannot add multiple timestamps to one db object.");
            }
            if ((granularity & Granularity.EXACT) != 0) {
                queryDoc.append("timestamp", new Date());
            }
            if ((granularity & Granularity.DAY) != 0) {
                queryDoc.append("day", getCurrentDay());
            }
            if ((granularity & Granularity.MONTH) != 0) {
                queryDoc.append("month", getCurrentMonth());
            }
            if ((granularity & Granularity.YEAR) != 0) {
                queryDoc.append("year", getCurrentYear());
            }

            // this increases the count value
            BasicDBObject valueObj = new BasicDBObject();
            for (Entry<String, Object> entry : values.entrySet()) {
                // use a $set object here as we don't want to replace the document
                // but add new values to existing ones
                BasicDBObject setObj = new BasicDBObject(entry.getKey(), entry.getValue());
                valueObj.append("$set", setObj);
            }

            // add the inc
            valueObj.append("$inc", new BasicDBObject("count", inc));

            // execute query
            WriteResult result = coll.update(queryDoc, valueObj, true, false);

            if ((result != null && result.getError() != null)) {
                throw new RuntimeException("Error logging statistics: " + result.getError());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCurrentYear() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }

    private String getCurrentMonth() {
        return new SimpleDateFormat("yyyy-MM").format(new Date());
    }

    private String getCurrentDay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

}
