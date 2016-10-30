package es.ae.io;

import org.junit.Before;
import org.junit.Test;

import es.ae.utils.Constants;
import junit.framework.TestCase;


public class WriterTest extends TestCase {

    Writer writer;

    /**
     * @throws java.lang.Exception
     */
    @Override
    @Before
    public void setUp() throws Exception {

        writer = new Writer();

    }

    /**
     * Test method for {@link es.ae.Writer#changeExtension(String, String))} .
     */
    @Test
    public void testChangeExtension() {
    	
        String newFile = writer.changeExtension("/home/json/doc.json", Constants.EXTENSION_BSON);
        assertEquals("", "/home/json/doc.bson", newFile);
    }
    
    /**
     * Test method for {@link es.ae.Writer#writeFile(String, String))} .
     */
    @Test
    public void testWriteFile() {
    	
        String newFile = writer.writeFile("resources/test.json", "{\"Num\": 1}");
        assertEquals("", "resources/test.json", newFile);
    }
}
