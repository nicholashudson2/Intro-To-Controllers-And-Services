package com.cooksys.friendlr;

import java.util.Set;

import org.mapstruct.Mapper;

import com.cooksys.friendlr.dto.PersonDto;
import com.cooksys.friendlr.entity.Person;

@Mapper(componentModel="spring")
public interface PersonMapper {
	
	PersonDto toPersonDto(Person person);
	
	Person toPerson(PersonDto personDto);
	
	Set<PersonDto> toPersonDtos(Set<Person> people);
	
	Set<Person> toPeople(Set<PersonDto> people);
	
}
