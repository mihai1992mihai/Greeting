package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GreetingRepository extends CrudRepository<Greeting, Long> {
    Set<Greeting> findAll();
}
