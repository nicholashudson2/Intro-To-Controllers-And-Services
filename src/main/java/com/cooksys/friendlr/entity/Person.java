package com.cooksys.friendlr.entity;

import java.util.HashSet;
import java.util.Set;

public class Person {

	Long id;
	String firstName; 
	String lastName;
	Set<Person> friends = new HashSet<Person>();
	
	public Person() {
		super();
	}
	
	public Person(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return a set of friends
	 */
	public Set<Person> getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(Set<Person> friends) {
		this.friends = friends;
	}
	
	/**
	 * @param friend the friend to add
	 */
	public void addFriend(Person friend) {
		this.friends.add(friend);
	}
	
	/**
	 * @param friend the friend to remove
	 */
	public void removeFriend(Person friend) {
		this.friends.remove(friend);
	}
	
}
