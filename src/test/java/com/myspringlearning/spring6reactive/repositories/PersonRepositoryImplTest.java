package com.myspringlearning.spring6reactive.repositories;

import com.myspringlearning.spring6reactive.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {


    PersonRepository personRepository = new PersonRepositoryImpl();

    //not the way we should do it
    @Test
    void testMonoByIdBlock() {

        Mono<Person> personMono = personRepository.getById(1);

        Person person = personMono.block();
        System.out.println(person.toString());
    }

    @Test
    void testGetByIdSubscriber() {
        Mono<Person> personMono = personRepository.getById(1);

        personMono.subscribe(

                person -> System.out.println(person.toString())
        );

    }

    @Test
    void testMapOperation() {
        Mono<Person> personMono = personRepository.getById(1);

        personMono.map(person -> person.getFirstName()).subscribe(firstName -> System.out.println(firstName));


    }

}