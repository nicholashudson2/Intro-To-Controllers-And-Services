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

	public PersonService(PersonMapper personMapper) {
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
		return personMapper.toPersonDto(people.stream().filter(person -> person.getId() == id).findFirst().orElse(null));
	}

	private Person getPersonEntity(long id) {
		return people.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
	}

	public PersonDto createPerson(PersonDto person) {
		person.setId(index++);
		people.add(personMapper.toPerson(person));
		return person;
	}

	public PersonDto overwritePerson(long id, PersonDto person) {
		return (deletePerson(id) == true) ? createPerson(person) : null;
	}

	public boolean deletePerson(long id) {
		if (getPerson(id) != null) {
			for (Person friend : getPersonEntity(id).getFriends())
				friend.removeFriend(getPersonEntity(id));
			people.remove(getPerson(id));
			return true;
		}
		return false;
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

}
