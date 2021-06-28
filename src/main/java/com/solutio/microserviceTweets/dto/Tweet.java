/**
 * 
 */
package com.solutio.microserviceTweets.dto;

/**
 * @author Yuto
 *
 */
public class Tweet {
	
	public Tweet() {
		// TODO Auto-generated constructor stub
	}

	private String user;
	private String text;
	private String location;
	private boolean validation;
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the validation
	 */
	public boolean isValidation() {
		return validation;
	}
	/**
	 * @param validation the validation to set
	 */
	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	
}
