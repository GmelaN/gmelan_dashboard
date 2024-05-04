package com.dashboard.gmelan.controller;

import com.dashboard.gmelan.news.entity.News;
import service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NewsController {
    private final NewsService newsService;
    public NewsController() {
         this.newsService = new NewsService();
    }

    @GetMapping("news")
    public String news(Model model) {
        List<News> newsList = newsService.getGoogleNews();
        model.addAttribute("newsList", newsList);

        return "news";
    }
}
