package com.dashboard.gmelan.notice.controller;

import com.dashboard.gmelan.notice.entity.NoticeEntity;
import com.dashboard.gmelan.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    /**
     * Get notice page.
     */
    @GetMapping("notice")
    public String notice(Model model) {
        ArrayList<NoticeEntity> noticeEntityList = noticeService.getNotice();
        model.addAttribute("noticeList", noticeEntityList);

        return "notice";
    }
}
