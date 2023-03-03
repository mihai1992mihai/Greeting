package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
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

    @GetMapping("/greeting/{id}/view")
    public String showGreeting(@PathVariable Long id, Model model){
        Optional<Greeting> greetingOptional = greetingService.findById(id);
        log.info(String.valueOf(greetingOptional.get().getId()));

        if (!greetingOptional.isPresent()){
            log.info("recipe not found");

        }
        model.addAttribute("greeting", greetingOptional.get());
        return "showOne";

    }


}
