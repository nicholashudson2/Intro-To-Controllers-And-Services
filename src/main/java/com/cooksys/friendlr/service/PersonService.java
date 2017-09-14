package com.cooksys.friendlr.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cooksys.friendlr.PersonMapper;
import com.cooksys.friendlr.dto.PersonDto;
import com.cooksys.friendlr.entity.Person;

@Service
public class PersonService {
	Set<Person> people = new HashSet<Person>();
	private static long index = 0;
	
	private PersonMapper personMapper;
	
	public PersonService(PersonMapper personMapper){

//	public PersonService() {
		this.personMapper = personMapper;
		Person person = new Person(index++, "Nicholas", "Hudson");
		Person personTwo = new Person(index++, "Greg", "Hill");
		Person personThree = new Person(index++, "Travis", "Pettrey");

		people.add(person);
		people.add(personTwo);
		people.add(personThree);
	}

	public Set<PersonDto> getPeople() {
		return personMapper.toPersonDtos(people);
	}

	public PersonDto getPerson(long id) {
		for (Person person : people) {
			if (person.getId().equals(id))
				return personMapper.toPersonDto(person);
		}
		return null;

	}
	
	private Person getPersonEntity(long id) {
		for (Person person : people) {
			if (person.getId().equals(id))
				return person;
		}
		return null;

	}

	public PersonDto createPerson(PersonDto person) { 
		person.setId(index++);
		people.add(personMapper.toPerson(person));
		return person;
	}

	public PersonDto overwritePerson(long id, PersonDto person) {
		if (deletePerson(id) == true) {
			createPerson(person);
			return person;
		} else {
			return null;
		}
	}
	
	public boolean deletePerson(long id) {
		if (getPerson(id) != null) {
			for(Person friend : getPersonEntity(id).getFriends()) {
				friend.removeFriend(getPersonEntity(id));
			}
			people.remove(getPerson(id));
			return true;
		} else {
			return false;
		}
	}
	
	public Set<PersonDto> getFriends(long id) {
		return personMapper.toPersonDtos(getPersonEntity(id).getFriends());
	}
	
	public void addFriend(long id, long friendId) {
		getPersonEntity(id).addFriend(getPersonEntity(friendId));
		getPersonEntity(friendId).addFriend(getPersonEntity(id));
	}
	
	public void removeFriend(long id, long friendId) {
		getPersonEntity(id).removeFriend(getPersonEntity(friendId));
		getPersonEntity(friendId).removeFriend(getPersonEntity(id));
	}
		
//	public Person removeFriend(long id, PersonDto friend) {
//		Set<PersonDto> friends = getPersonEntity(id).getFriends();
//		if (getFriends(id).contains(friend)) {
//			friends.remove(friend);
//			getPerson(id).setFriends(friends);
//			return friend;
//		} else {
//			return null;
//		}
//	}	
		
}
