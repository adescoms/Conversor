package es.ae.convert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.bson.BsonRegularExpression;
import org.bson.BsonTimestamp;
import org.bson.BsonUndefined;
import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Implements IConvert for parse Shell Mode JSON to Strict Mode JSON
 */
public class ToStrictJSON implements IConvert {

    @Override
    public String parse(String doc) {
        JSONObject json = new JSONObject();

        Document bson = Document.parse(doc);
        bson.forEach((k, v) -> json.put(k, handle(v)));

        return json.toString();
    }

    @Override
    public Object handle(Object v) {

        Object res = null;

        if (v instanceof ObjectId) {
            JSONObject ts = new JSONObject();
            ts.put("$oid", ((ObjectId) v).toHexString());
            res = ts;            

        } else if (v instanceof Date) {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            df.setTimeZone(tz);
            
            JSONObject j = new JSONObject();
        	j.put("$date", df.format(v));
            
            res = j;
            
        } else if (v instanceof BsonTimestamp) {
        	
            JSONObject ts = new JSONObject();
            ts.put("t", ((BsonTimestamp) v).getTime());
            ts.put("i", ((BsonTimestamp) v).getInc());
            
            JSONObject j = new JSONObject();
        	j.put("$timestamp", ts);
        	
            res = j;

        } else if (v instanceof BsonUndefined) {
        	JSONObject ts = new JSONObject();
            ts.put("$undefined", true);
            res = ts;

        } else if (v instanceof Long) {
        	JSONObject ts = new JSONObject();
            ts.put("$numberLong", v.toString());
            res = ts;

        } else if (v instanceof Double) {
            res = v.toString();

        } else if (v instanceof BsonRegularExpression) {
            JSONObject ts = new JSONObject();
            ts.put("$regex", ((BsonRegularExpression) v).getPattern());
            ts.put("$options", ((BsonRegularExpression) v).getOptions());

            res = ts;

        } else if (v instanceof Binary) {
            JSONObject ts = new JSONObject();
            ts.put("$binary", ((Binary) v).getData());
            ts.put("$type", ((Binary) v).getType());

            res = ts;
        } else if (v instanceof Document) {
            JSONObject ts = new JSONObject();
            ((Document) v).forEach((kDoc, vDoc) -> ts.put(kDoc, handle(vDoc)));
            res = ts;

        } else if (v instanceof ArrayList) {
            JSONArray ts = new JSONArray();
            ((ArrayList<?>) v).forEach((elem) -> ts.put(handle(elem)));
            res = ts;

        } else {
            res = v;
        }

        return res;
    }
}
