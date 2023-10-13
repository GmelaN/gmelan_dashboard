package com.dashboard.gmelan.news.controller;

import com.dashboard.gmelan.news.entity.NewsEntity;
import com.dashboard.gmelan.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;
    @GetMapping("news")
    public String news(Model model) {
        ArrayList<NewsEntity> newsEntityList = newsService.getGoogleNews();
        model.addAttribute("newsList", newsEntityList);

        return "news";
    }
}
