package com.dashboard.gmelan.controller;

import com.dashboard.gmelan.entity.Notice;
import com.dashboard.gmelan.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NoticeController {
    private final NoticeService noticeService;
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /**
     * Get notice page.
     */
    @GetMapping("notice")
    public String notice(Model model) {
        List<Notice> noticeList = noticeService.getNotice();
        model.addAttribute("noticeList", noticeList);

        return "notice";
    }
}
