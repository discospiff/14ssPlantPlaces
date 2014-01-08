package edu.uc.service;

import edu.uc.dto.User;

public interface IUserService {
	
	/**
	 * Attempt to logon the user with the given username and password.
	 * @param username the user's entered username.
	 * @param password the user's entered password.
	 * @return true if able to logon, false if not.
	 */
	public boolean logon(String username, String password) throws Exception;
	
	/**
	 * Save the given user.
	 * @param user A user object representing a user to save.
	 * @throws Exception Any error in the underlying persistence mechanism.
	 */
	public void save (User user) throws Exception;
	
	/**
	 * Retrieve the given user.
	 * 
	 * @param username the username that belongs to the user to retrieve.
	 * @return the user retrieved
	 * @throws Exception if the user does not exist, or if there is an underlying error in the persistence mechanism.
	 */
	public User getUser (String username) throws Exception;

}
