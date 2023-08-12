package com.dashboard.gmelan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dashboard.gmelan.service.NewsService;

import com.dashboard.gmelan.dataStructure.NewsList;


@Controller
public class TestController {

    @Autowired
    private NewsService newsService;

    @GetMapping(value="index")
    public String mainPage() {
        return "index";
    }

    @GetMapping(value="/dashboard")
    public String dashBoard() {
        return "dashboard";
    }
    
    @GetMapping(value="b")
    public String b(){
        return "b";
    }

    @GetMapping("news")
    public String news(Model model) {
        List<NewsList> newsList = newsService.getGoogleNews();
        model.addAttribute("newsList", newsList);

        return "news";
    }
}
