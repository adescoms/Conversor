package es.ae.convert;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import es.ae.utils.Constants;

/*
 * Implements IConvert for parse Strict Mode JSON to Shell Mode JSON
 */
@SuppressWarnings("rawtypes")
public class ToShellJSON implements IConvert {

	@Override
	public String parse(String doc) {
		StringBuffer bson = new StringBuffer();
		bson.append(Constants.OPEN_DOC);

		JSONObject json = new JSONObject(doc);
		json.toMap().forEach((k, v) -> bson.append(k + ": " + handle(v) + ","));

		String res = bson.substring(0, bson.length() - 1); // Remove the last ,

		res += Constants.CLOSE_DOC;

		return res;

	}

	@Override
	public String handle(Object v) {
		String res = null;

		if (v instanceof Map<?,?>) {

			if (null != ((Map) v).get("$regex")) {

				res = genRegExp(v);

			} else if (null != ((Map) v).get("$timestamp")) {

				res = genTimestamp(v);

			} else if (null != ((Map) v).get("$binary")) {

				res = genBinary(v);

			} else if (null != ((Map) v).get("$numberLong")) {

				res = genNumber(v);

			} else if (null != ((Map) v).get("$oid")) {

				res = genObjectId(v);

			} else if (null != ((Map) v).get("$date")) {

				res = genDate(v);

			} else if (null != ((Map) v).get("$undefined")) {

				res = genUndif(v);

			} else {

				res = genDoc(v);
			}

		} else if (v instanceof String) {
			res = "\"" + (String) v + "\"";
		} else if (v instanceof Boolean) {
			res = v.toString();
		} else if (v instanceof ArrayList) {
			res = genArray(v);
		} else {
			res = v.toString();
		}

		return res;
	}

	private String genRegExp(Object v) {
		String pattern = (String) ((Map) v).get("$regex");
		String options = (String) ((Map) v).get("$options");

		return "new RegExp(\"" + pattern + "\",\"" + options + "\")";
	}

	private String genTimestamp(Object v) {
		Map ts = (Map) ((Map) v).get("$timestamp");

		int seconds = (int) ts.get("t");
		int inc = (int) ts.get("i");

		return "Timestamp(" + seconds + "," + inc + ")";
	}

	@SuppressWarnings("unchecked")
	private String genBinary(Object v) {
		
		StringBuffer bin = new StringBuffer();
		bin.append("new BinData(");
		
		byte type = ((Integer) ((Map) v).get("$type")).byteValue();
		bin.append(type);
		bin.append(Constants.COMMA);
		
		ArrayList<Integer> list = (ArrayList<Integer>) ((Map) v).get("$binary");
		for (int i = 0; i < list.size(); i++) {
			bin.append(list.get(i));
		}
		bin.append(")");

		return bin.toString();
	}

	private String genNumber(Object v) {
		String num = (String) ((Map) v).get("$numberLong");

		return "NumberLong(" + num + ")";
	}

	private String genObjectId(Object v) {
		String oid = (String) ((Map) v).get("$oid");

		return "ObjectId(" + oid + ")";
	}

	private String genDate(Object v) {
		String date = (String) ((Map) v).get("$date");

		return "new Date(" + date + ")";
	}

	private String genUndif(Object v) {

		return "undefined";
	}

	@SuppressWarnings("unchecked")
	private String genDoc(Object v) {
		StringBuffer doc = new StringBuffer();
		doc.append(Constants.OPEN_DOC);
		((Map) v).forEach((k1, v1) -> {
			doc.append(k1);
			doc.append(Constants.DOUBLE_POINT);
			doc.append(handle(v1));
			doc.append(Constants.COMMA);
		});
		
		doc.delete(doc.length()-1, doc.length());
		doc.append(Constants.CLOSE_DOC);

		return doc.toString();
	}

	@SuppressWarnings("unchecked")
	private String genArray(Object v) {
		StringBuffer array = new StringBuffer();
		array.append(Constants.OPEN_ARRAY);
		((ArrayList) v).forEach(elem -> {
			array.append(handle(elem));
			array.append(Constants.COMMA);
		});
		
		array.delete(array.length()-1, array.length());
		array.append(Constants.CLOSE_ARRAY);
		
		return array.toString();
	}
}
