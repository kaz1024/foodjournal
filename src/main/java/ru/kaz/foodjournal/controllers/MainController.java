package ru.kaz.foodjournal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String Home(Model model) {
        model.addAttribute("title", "Пищевой дневник");
        return "home";
    }

    @GetMapping("/about")
    public String About(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }

}