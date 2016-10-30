package es.ae;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import es.ae.convert.IConvert;
import es.ae.convert.ToShellJSON;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class toShellJsonTest extends TestCase {

	IConvert convert;
	
    /**
     * @throws java.lang.Exception
     */
    @Override
    @Before
    public void setUp() throws Exception {
    	convert = new ToShellJSON();
        
    }
    
    @Test
	public void testObjectId() {
    	
    	Map<String, String> oid = new HashMap<String, String>();
    	oid.put("$oid", "53c2ab5e4291b17b666d742a");

    	String res = (String) convert.handle(oid);
    	
    	assertEquals(res, "ObjectId(53c2ab5e4291b17b666d742a)");
    }
    
    @Test
	public void testDate() {

    	Map<String, String> date = new HashMap<String, String>();
    	date.put("$date", "1970-02-01T16:39Z");
    	
    	String res = (String) convert.handle(date);
    	assertEquals(res, "new Date(1970-02-01T16:39Z)");
    }
}
