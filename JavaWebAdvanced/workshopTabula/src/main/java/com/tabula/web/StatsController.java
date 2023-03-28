package com.tabula.web;

import com.tabula.stats.service.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsController {

    private final StatService statService;

    public StatsController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/stats")
    public String stats(Model model){
        model.addAttribute("requestCount",statService.getRequestCount());
        model.addAttribute("startedOn",statService.getStartedOn());
        return "stats/stats";
    }
}
