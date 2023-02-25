package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class GreetingSertviceImp implements GreetingService{

    private final GreetingService greetingRepository;

    public GreetingSertviceImp(GreetingService greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting saveGreeting(){
        //greetingRepository. nu gaseste nici un method de la CRUD???

    }
}
