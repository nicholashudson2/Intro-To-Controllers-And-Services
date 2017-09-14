package com.cooksys.friendlr.controller;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.friendlr.dto.PersonDto;
import com.cooksys.friendlr.service.PersonService;

@RestController
@RequestMapping("person")
public class PersonController {

	private PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	public Set<PersonDto> getPeople() {
		return personService.getPeople();
	}

	@GetMapping("{id}")
	public PersonDto getPerson(@PathVariable Integer id, HttpServletResponse response) {
		PersonDto person = personService.getPerson(id);
		if (person == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		response.setStatus(HttpServletResponse.SC_FOUND);
		return person;
	}

	@PostMapping
	public PersonDto createPerson(@RequestBody PersonDto person, HttpServletResponse response) {
		PersonDto newPerson = personService.createPerson(person);
		if (newPerson == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return newPerson;
	}

	@PutMapping("{id}")
	public PersonDto overwritePerson(@PathVariable long id, @RequestBody PersonDto person,
			HttpServletResponse response) {
		PersonDto replacementPerson = personService.overwritePerson(id, person);
		if (replacementPerson == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return replacementPerson;
	}

	@PatchMapping("{id}")
	public void deletePerson(@PathVariable long id, HttpServletResponse response) {
		boolean deleted = personService.deletePerson(id);
		if (deleted == true)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

	@GetMapping("{id}/friends")
	public Set<PersonDto> getFriends(@PathVariable long id, HttpServletResponse response) {
		Set<PersonDto> friends = personService.getFriends(id);
		if (friends == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		response.setStatus(HttpServletResponse.SC_FOUND);
		return friends;
	}

	@PostMapping("{id}/friends/{friendId}")
	public void addFriend(@PathVariable long id, @PathVariable long friendId, HttpServletResponse response) {
		if (personService.getPerson(id) == null || personService.getPerson(friendId) == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		personService.addFriend(id, friendId);
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

	@DeleteMapping("{id}/friends/{friendId}")
	public void removeFriend(@PathVariable long id, @PathVariable long friendId, HttpServletResponse response) {
		if (personService.getPerson(id) == null || personService.getPerson(friendId) == null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		personService.removeFriend(id, friendId);
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
	}

}
