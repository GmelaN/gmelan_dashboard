package com.dashboard.gmelan.velog.controller;

import com.dashboard.gmelan.velog.service.VelogService;
import org.springframework.stereotype.Controller;

@Controller
public class VelogController {
    private final VelogService velogService;
    public VelogController(VelogService velogService) {
        this.velogService = velogService;
    }
}
