package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * MVC Controller – serves Thymeleaf HTML views.
 */
@Controller
public class AppController {

    @Autowired
    private StaffManager manager;

    /* Home page – list all staff */
    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("stafflist", manager.getAllStaff());
        return "index";
    }

    /* Show "create" form */
    @GetMapping("/new")
    public String showCreatePage() {
        return "create_book_form";
    }

    /* Save (create or update) */
    @PostMapping("/save")
    public String saveStaff(@ModelAttribute Staff staff) {
        manager.saveStaff(staff);
        return "redirect:/";
    }

    /* Show "edit" form */
    @GetMapping("/edit/{id}")
    public String editStaff(@PathVariable Long id, Model model) {
        model.addAttribute("staff", manager.getStaffById(id));
        return "edit_form";
    }

    /* Delete and redirect */
    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable Long id) {
        manager.deleteStaff(id);
        return "redirect:/";
    }
}
