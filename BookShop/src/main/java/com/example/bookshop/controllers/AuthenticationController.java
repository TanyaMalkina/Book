package com.example.bookshop.controllers;

import com.example.bookshop.models.Product;
import com.example.bookshop.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {



    @GetMapping("/authentication")
    public String login(){
        return "authentication";
    }



}
