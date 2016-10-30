package es.ae;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    /**
     * @throws java.lang.Exception
     */
    @Override
    @Before
    public void setUp() throws Exception {

        
    }
    
    @Test
	public void testToJson() {
    	String[] args = new String[1];
    	args[0] = "resources/test_to_json.bson";
    	App.main(args);
	}
    
    @Test
	public void testToShell() {
    	String[] args = new String[1];
    	
    	args[0] = "resources/test_to_bson.json";
    	App.main(args);
	}
    
    @Test
    public void testDoubleConvert() {
    	String[] args = new String[1];
    	
    	args[0] = "resources/toBSON.json";
    	App.main(args);
    	
    	args[0] = "resources/toBSON.bson";
    	App.main(args);
    	
	}
}
