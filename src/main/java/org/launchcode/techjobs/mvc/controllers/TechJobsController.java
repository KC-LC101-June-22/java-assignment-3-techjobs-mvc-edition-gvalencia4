package org.launchcode.techjobs.mvc.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;

public class TechJobsController {

    static HashMap<String, String> actions = new HashMap<>();

    public TechJobsController () {
        actions.put("search", "Search");
        actions.put("list", "List");
    }

    @ModelAttribute("actions")
    public static HashMap<String, String> getActions() {
        return actions;
    }
}
