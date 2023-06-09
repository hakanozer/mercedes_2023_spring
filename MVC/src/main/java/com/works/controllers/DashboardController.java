package com.works.controllers;

import com.works.models.Product;
import com.works.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Product> ls = dashboardService.products();
        model.addAttribute("products", ls);
        System.out.println( ls );
        return "dashboard";
    }


}
