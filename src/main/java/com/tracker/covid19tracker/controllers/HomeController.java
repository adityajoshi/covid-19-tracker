package com.tracker.covid19tracker.controllers;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tracker.covid19tracker.models.LocationStats;
import com.tracker.covid19tracker.services.Covid19DataService;

@Controller
public class HomeController {
	
	@Autowired
	Covid19DataService dataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = dataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats", allStats);
		
		model.addAttribute("totalCases",totalCases);
		model.addAttribute("totalNewCases",totalNewCases);
		return "home";
	}
}
