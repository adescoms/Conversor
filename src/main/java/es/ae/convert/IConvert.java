package es.ae.convert;

/*
 * Interface for parse Shell Mode JSON to Strict Mode JSON and vice versa
 * https://docs.mongodb.com/v3.2/reference/mongodb-extended-json/#parsers-and-
 * supported-format https://www.npmjs.com/package/mongodb-extended-json
 * https://docs.mongodb.com/v3.2/reference/bson-types/#timestamps
 */
public interface IConvert {

    // Read a input JSON String and convert it
    public String parse(String doc);

    // Analyze the value of the Document and handles by type
    public Object handle(Object v);
}
