package edu.uc.dao;

import java.util.HashMap;

import edu.uc.dto.User;


/**
 * Data methods for a User object.
 * @author Brandan
 *
 */
public interface IUserDAO {

	/**
	 * Persist the given user.
	 * @param user the user to save
	 * @throws Exception any problem in the underlying persistence mechanism.
	 */
	public void save(User user) throws Exception;
	
	/**
	 * Fetch the user with the given name.
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public User fetch(String username) throws Exception;
	
	/**
	 * Return all users as a Hashtable.
	 * @return a collection of all users.
	 */
	public HashMap<String, User> fetchAll();
	
}
