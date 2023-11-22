package com.dashboard.gmelan.velog.controller;

import com.dashboard.gmelan.velog.service.VelogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/velog")
public class VelogController {
    private final VelogService velogService;
    public VelogController(VelogService velogService) {
        this.velogService = velogService;
    }

    @GetMapping("velog/newest")
    public String newestPage(Model model) {

        return "velog";
    }
}
