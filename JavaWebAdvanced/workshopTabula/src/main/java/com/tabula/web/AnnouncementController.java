package com.tabula.web;

import com.tabula.service.AnnouncementService;
import com.tabula.service.impl.AnnouncementServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/announcements")
public class AnnouncementController {


    private final AnnouncementService announcementService;

    @GetMapping
    public String announcement(Model model){

        model.addAttribute("announcements",
                announcementService.findAll());

        return "announcement/announcements";
    }
}
