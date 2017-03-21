package register.register;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import junit.framework.TestCase;

public class RegisterTest extends TestCase {
	
	private static Register cr;
	
	public RegisterTest() {
	    super();
	}
	
	@Test
	public void testTotal() throws Exception {
		Map<Integer, Integer> cash = new TreeMap<Integer, Integer>();
		cash.put(20, 2);
		cash.put(10, 4);
		cash.put(5, 6);
		cash.put(2, 4);
		cash.put(1, 10);
		
		cr = new Register(cash);
		Integer total = 128;
		assertEquals(cr.getTotal(), total);
	}
	
    @Test
    public void testRemoval() {
    	Map<Integer, Integer> cash = new TreeMap<Integer, Integer>();
		cash.put(20, 2);
		cash.put(10, 4);
		cash.put(5, 6);
		cash.put(2, 4);
		cash.put(1, 10);
		
		cr = new Register(cash);
		
		cr.removeMoney(20, 1);
		cr.removeMoney(10, 4);
		cr.removeMoney(5, 3);
		cr.removeMoney(1, 10);
		
		Integer total = 43;
		assertEquals(cr.getTotal(), total);
    }
    
    @Test
    public void testMakeChange11() throws Exception {
    	Map<Integer, Integer> cash = new TreeMap<Integer, Integer>();
		cash.put(20, 1);
		cash.put(10, 0);
		cash.put(5, 3);
		cash.put(2, 4);
		cash.put(1, 0);
		
		Map<Integer, Integer> change = new TreeMap<Integer, Integer>();
		change.put(5, 1);
		change.put(2, 3);
		
		cr = new Register(cash);
		assertEquals(cr.makeChange(11), change);
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testShouldThrow() throws Exception {
    	Map<Integer, Integer> cash = new TreeMap<Integer, Integer>();
		cash.put(20, 1);
		cash.put(10, 0);
		cash.put(5, 2);
		cash.put(2, 1);
		cash.put(1, 0);
		
		Map<Integer, Integer> change = new TreeMap<Integer, Integer>();
		
		cr = new Register(cash);
		try {
			cr.makeChange(14);
			fail("Should throw exception");
		} catch (Exception e) {
			assertTrue(e.getClass().equals(Exception.class)); 
		}
    }
 
}

