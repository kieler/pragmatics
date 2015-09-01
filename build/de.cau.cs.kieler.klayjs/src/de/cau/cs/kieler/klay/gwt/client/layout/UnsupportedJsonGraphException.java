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
package de.cau.cs.kieler.klay.gwt.client.layout;

import com.google.common.base.Joiner;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import de.cau.cs.kieler.kiml.UnsupportedGraphException;

/**
 * A dedicated exception class holding an additional json value that is responsible for the
 * exception as well as an optional json context value.
 * 
 * @author uru
 */
public class UnsupportedJsonGraphException extends UnsupportedGraphException {

    private static final long serialVersionUID = -8500307753182091567L;

    private JSONValue value;
    private JSONValue context;

    /**
     * @param msg
     *            A description of the problem.
     */
    public UnsupportedJsonGraphException(final String msg) {
        super(msg);
    }

    /**
     * @param msg
     *            A description of the problem.
     * @param value
     *            The {@link JSONValue} that caused the problem.
     */
    public UnsupportedJsonGraphException(final String msg, final JSONValue value) {
        super(msg);
        this.value = value;
    }

    /**
     * @param msg
     *            A description of the problem.
     * @param value
     *            The {@link JSONValue} that caused the problem.
     * @param context
     *            The {@link JSONValue} that is the context of the value.
     */
    public UnsupportedJsonGraphException(final String msg, final JSONValue value,
            final JSONValue context) {
        super(msg);
        this.value = value;
        this.context = context;
    }

    /**
     * @return a json object representing this exception.
     */
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("type", new JSONString(this.getClass().getName()));
        obj.put("text", new JSONString(super.getMessage()));
        if (value != null) {
            obj.put("value", value);
        }
        if (context != null) {
            obj.put("context", context);
        }
        obj.put("stacktrace", new JSONString(Joiner.on("\n").join(getStackTrace())));
        return obj;
    }
}
