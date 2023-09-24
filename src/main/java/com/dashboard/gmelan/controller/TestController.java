package com.dashboard.gmelan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dashboard.gmelan.service.NewsService;
import com.dashboard.gmelan.service.NoticeService;
import com.dashboard.gmelan.dataStructure.News;
import com.dashboard.gmelan.dataStructure.Notice;


@Controller
public class TestController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NoticeService noticeService;


    /**
     * Get index page.
    */
    @GetMapping(value="/")
    public String mainPage() {
        return "index";
    }


    /**
     * Get dashboard page.
     */
    @GetMapping(value="/dashboard")
    public String dashBoard() {
        return "dashboard";
    }

    /**
     * Get news page.
     */
    @GetMapping("news")
    public String news(Model model) {
        ArrayList<News> newsList = newsService.getGoogleNews();
        model.addAttribute("newsList", newsList);

        return "news";
    }

    /**
     * Get notice page.
     */
    @GetMapping("notice")
    public String notice(Model model) {
        ArrayList<Notice> noticeList = noticeService.getNotice();
        model.addAttribute("noticeList", noticeList);

        return "notice";
    }


}
