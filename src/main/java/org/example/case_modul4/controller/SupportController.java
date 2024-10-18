package org.example.case_modul4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupportController {

    @GetMapping("/support")
    public String supportPage(Model model) {
        return "support";
    }
}