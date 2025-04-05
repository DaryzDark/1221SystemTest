package com._Systems.test.controller;

import com._Systems.test.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;


    @GetMapping("/daily")
    public ReportService.DailyReport getDailyReport(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return reportService.getDailyReport(userId, date);
    }

    @GetMapping("/check")
    public boolean isWithinLimit(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return reportService.isWithinCalorieLimit(userId, date);
    }

    @GetMapping("/history")
    public Map<LocalDate, Double> getHistory(@RequestParam Long userId) {
        return reportService.getCalorieHistory(userId);
    }
}
