package es.ae;

import java.util.Date;

import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import es.ae.convert.IConvert;
import es.ae.convert.ToStrictJSON;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class toStrictJsonTest extends TestCase {

	IConvert convert;
	
    /**
     * @throws java.lang.Exception
     */
    @Override
    @Before
    public void setUp() throws Exception {
    	convert = new ToStrictJSON();
        
    }
    
    @Test
	public void testObjectId() {
    	ObjectId oid = new ObjectId("581660025c353d0f68e27d50");
    	JSONObject res = (JSONObject) convert.handle(oid);
    	assertEquals(res.get("$oid"), "581660025c353d0f68e27d50");
    }
    
    @Test
	public void testDate() {
    	JSONObject res = (JSONObject) convert.handle(new Date(2738393933L));
    	assertEquals(res.get("$date"), "1970-02-01T16:39Z");
    }
}
