package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class GreetingSertviceImp implements GreetingService{

    private final GreetingRepository greetingRepository;

    public GreetingSertviceImp(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    @Override
    public Set<Greeting> findAll(){
        return greetingRepository.findAll();
    }

    @Override
    public Optional<Greeting> findById(Long id) {
        return greetingRepository.findById(id);
    }
}
