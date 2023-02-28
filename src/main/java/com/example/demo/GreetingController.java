package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Log
@Controller
public class GreetingController {

    public final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());

        return "greeting";
    }

//    ModelAttribute binding form
    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        greetingService.saveGreeting(greeting);
        log.info(String.valueOf(greeting.getId()));
        log.info(String.valueOf(greeting.getContent()));
        return "resultModelAttribute";
    }

    @GetMapping("/greeting/show")
    public String showAll(Model model){
        Set<Greeting> greetingSet = greetingService.findAll();
        model.addAttribute("greetings", greetingSet);
        return "showAll";
    }



}
