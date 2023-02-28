package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/greeting/show")
    public String showAll(Model model){
        Set<Greeting> greetingSet = greetingService.findAll();
        model.addAttribute("greetings", greetingSet);
        return "showAll";
    }

    //RequestParam Binding form
    @PostMapping("/greeting")
    public String greetingSubmit(@RequestParam("id") String id,
                                 @RequestParam("content") String content, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("content", content);

        log.info(id);
        log.info(content);
        Greeting greeting = new Greeting();
        greeting.setId(Long.valueOf(id));
        greeting.setContent(content);
        greetingService.saveGreeting(greeting);
        return "resultRequestParam";
    }


}
