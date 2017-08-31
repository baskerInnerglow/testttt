package com.cmacgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmacgm.repository.ApplicationRepository;



@Controller
public class HomeController {

    @Autowired
    private ApplicationRepository applicationRepository;


    @RequestMapping("/")
    String index(){
    	 return "redirect:home";
    }
    
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String UsersList(Model model){
        model.addAttribute("applications", applicationRepository.findAll());
        return "home";
    }

   
}