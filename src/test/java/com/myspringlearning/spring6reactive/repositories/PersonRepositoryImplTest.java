package com.myspringlearning.spring6reactive.repositories;

import com.myspringlearning.spring6reactive.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

        personMono.map(Person::getFirstName).subscribe(firstName -> System.out.println(firstName));
    }

    @Test
    void fluxBlockFirst() {
        Flux<Person> personFlux = personRepository.findAll();
        Person person = personFlux.blockFirst();
        System.out.println(person);
        Person person2 = personFlux.blockLast();
        System.out.println(person2);
    }

    @Test
    void testFluxSubscriber() {

        Flux<Person> personFlux = personRepository.findAll();


        personFlux.subscribe(
                person -> {
                    System.out.println(person);
                }
        );
    }

    @Test
    void testFluxMap() {
        Flux<Person> personFlux = personRepository.findAll();

        personFlux.map(Person::getFirstName).subscribe(firstName ->
                System.out.println(firstName));
    }

    @Test
    void testFluxToMonoList() {
        Flux<Person> personFlux = personRepository.findAll();


        Mono<List<Person>> personMonoList = personFlux.collectList();

        personMonoList.subscribe(
                list -> {
                    list.forEach(
                            person -> {
                                System.out.println(person.getFirstName());
                            }
                    );
                }
        );
    }


}