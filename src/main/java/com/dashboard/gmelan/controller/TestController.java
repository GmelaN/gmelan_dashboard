package com.dashboard.gmelan.controller;

import java.util.ArrayList;
import java.util.List;

import com.dashboard.gmelan.dataStructure.Todo;
import com.dashboard.gmelan.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dashboard.gmelan.service.NewsService;
import com.dashboard.gmelan.service.NoticeService;
import com.dashboard.gmelan.dataStructure.News;
import com.dashboard.gmelan.dataStructure.Notice;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class TestController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private TodoService todoService;


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

    @GetMapping("todo_list")
    public String todoList(Model model) {
        Long userId = 0L;

        List<Todo> todoList = todoService.getTodoListByUserId(userId);

        model.addAttribute("todoList", todoList);
        return "todoList";
    }
}
