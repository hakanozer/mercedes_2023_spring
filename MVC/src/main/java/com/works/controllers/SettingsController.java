package com.works.controllers;

import com.works.services.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SettingsController {

    final SettingsService settingsService;

    @GetMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("admins", settingsService.admins());
        return "settings";
    }

}
