package com.example.batterysaver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BatteryController {

    @Autowired
    private BatteryMonitor batteryMonitor;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("status", batteryMonitor.getStatus());
        model.addAttribute("batteryLevel", batteryMonitor.getBatteryLevel());
        model.addAttribute("saverMode", batteryMonitor.isSaverMode());
        return "index";
    }

    @GetMapping("/status")
    public String getStatus(Model model) {
        model.addAttribute("status", batteryMonitor.getStatus());
        return "status";
    }
}