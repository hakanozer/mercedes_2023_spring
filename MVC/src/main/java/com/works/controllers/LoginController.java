package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final AdminService adminService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String adminLogin(Admin admin) {
        boolean status = adminService.login(admin);
        if ( status ) {
            return "redirect:/dashboard";
        }
        return "redirect:/";
    }

}
