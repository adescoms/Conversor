package es.ae.io;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class ReaderTest extends TestCase {

    Reader reader;

    /**
     * @throws java.lang.Exception
     */
    @Override
    @Before
    public void setUp() throws Exception {

        reader = new Reader();
    }

    /**
     * Test method for {@link es.ae.Reader#getExtension(String))} .
     */
    @Test
    public void testChangeExtension() {
        String newFile = reader.getExtension("/home/json/doc.json");

        assertEquals("", "json", newFile);
    }
    
    /**
     * Test method for {@link es.ae.Reader#reader(String))} .
     */
    @Test
    public void testReader() {

        String content;
		try {
			content = reader.readFile("resources/test_to_bson.json");
			System.out.println(content);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
        

    }
}
