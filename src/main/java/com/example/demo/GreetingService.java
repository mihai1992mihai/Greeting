package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Set;


public interface GreetingService {

    public Greeting saveGreeting(Greeting greeting);

    public Set<Greeting> findAll();
}
