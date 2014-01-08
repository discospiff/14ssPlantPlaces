package edu.uc.service.test;

import java.util.HashMap;

import junit.framework.TestCase;
import edu.uc.dto.User;
import edu.uc.service.IUserService;
import edu.uc.service.UserServiceStub;

public class UserServiceTest extends TestCase {
	
	IUserService userService;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		userService = new UserServiceStub();
	}
	
	/**
	 * Test scenarios:
	 * 	Method logon:
	 * 	should invoke fetchAll on IUserDAO to get a HashMap<String, User> of User objects.
	 * 	Should fetch the user from the HashMap<String, User> using the username as a key, and return true if the username returns a valid user, and the password matches that user's password.
	 * 	should return false for all other combinations.  Do not iterate over the HashMap; look up the object directly using the username as a key.  Hint: look at the methods that HashMap provides, and find one that will fetch a value without iterating.
	 */
	public void testLogon() {
		try {
			assertTrue(userService.logon("Caz", "Cha5e5Cars"));
		} catch (Exception e) {
			fail("logon should not throw exception");
		}
		try {
			assertFalse(userService.logon("Bugsy", "Siegel"));
		} catch (Exception e) {
			fail("logon should not throw exception");
		}
	}
	
	/**
	 * Test these scenarios:
	 * 	Should invoke the fetch(username) method in IUserDAO.
	 * Method fetch:
	 * 	Should create and return a new User object if the username is Caz.  See attribues above.
	 * 	Should throw an exception in other circumstances.  This exception should contain a message. 
	 */
	public void testGetUser() {
		
		try {
			User caz = userService.getUser("Caz");
			assertEquals(caz.getFirstName(), "Caz");
			assertEquals(caz.getLastName(), "Dolowicz");
			assertEquals(caz.getUsername(), "Caz");
		} catch (Exception e) {
			fail("Should not throw Exception");
		}
		
		try {
			userService.getUser("Bugsy");
			fail("Should have thrown exception for invalid username");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test scenarios:
	 * Method save:
	 *  should invoke the save() method in IUserDAO.
	 * 	should process normally if username and password are completed.
	 * 	should throw an exception if username and password  are blank or null.  The exception must have a message – don’t leave it with an empty constructor!
	 */
	public void testSave() {
		User validUser = new User();
		validUser.setFirstName("Caz");
		validUser.setLastName("Dolowicz");
		validUser.setUsername("Caz");
		validUser.setPassword("Cha$esCar$");
		try {
			userService.save(validUser);
		} catch (Exception e) {
			fail("Should not throw Exception for valid user");
		}
		
		User invalidUser = new User();
		try {
			userService.save(invalidUser);
			fail("Should have thrown exception for saving invalid username");
		} catch (Exception e) {
		}
	}
}
