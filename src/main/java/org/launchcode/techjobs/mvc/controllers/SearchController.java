package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController {

    // Default selected radio button is "All"
    private String searchRadioType = "all";

    private String searchQueryString = "Leave blank to search all";

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchRadioType", searchRadioType);
        model.addAttribute("searchQueryString", searchQueryString);
        return "search";
    }

    // Create a handler to process a search request and render the updated search view.
    @PostMapping(value ="results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;

        // Sanitize input
        String searchTermTrimmed = searchTerm.trim();

        if (searchTermTrimmed.equals("all") || searchTermTrimmed.equals("")){
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTermTrimmed);
        }

        setSearchRadioType(searchType);
        setSearchQueryString(searchTermTrimmed);

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchRadioType", searchRadioType);
        model.addAttribute("searchQueryString", searchQueryString);

        return "search";
    }

    public void setSearchRadioType(String searchRadioType) {
        this.searchRadioType = searchRadioType;
    }

    public void setSearchQueryString(String searchQueryString) {
        this.searchQueryString = searchQueryString;
    }

}
