package com.myspringlearning.spring6reactive.repositories;

import com.myspringlearning.spring6reactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {


    Person michael = Person.builder()
            .id(1)
            .firstName("Michael")
            .lastName("Weston")
            .build();
    Person fiona = Person.builder()
            .id(2)
            .firstName("Fiona")
            .lastName("Williams")
            .build();
    Person sam = Person.builder()
            .id(3)
            .firstName("Samuel")
            .lastName("Watson")
            .build();
    Person jesse = Person.builder()
            .id(4)
            .firstName("Jesse")
            .lastName("Smith")
            .build();

    @Override
    public Mono<Person> getById(Integer id) {

        return findAll().filter(person -> person.getId().equals(id)).next();
    }

    @Override
    public Flux<Person> findAll() {
        return
                Flux.just(michael, fiona, sam, jesse);
    }
}
