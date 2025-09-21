package com.moneyminder.presentation;

import com.moneyminder.presentation.dto.DailyReportDto;
import com.moneyminder.presentation.dto.MonthlyReportDto;
import com.moneyminder.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/daily")
    public ResponseEntity<DailyReportDto> getDailyReport(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate reportDate = date != null ? date : LocalDate.now();
        DailyReportDto report = reportService.getDailyReport(reportDate);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/daily/today")
    public ResponseEntity<DailyReportDto> getTodayReport() {
        DailyReportDto report = reportService.getTodayReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/monthly")
    public ResponseEntity<MonthlyReportDto> getMonthlyReport(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {
        
        LocalDate now = LocalDate.now();
        int reportYear = year != null ? year : now.getYear();
        int reportMonth = month != null ? month : now.getMonthValue();
        
        MonthlyReportDto report = reportService.getMonthlyReport(reportYear, reportMonth);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/monthly/current")
    public ResponseEntity<MonthlyReportDto> getCurrentMonthReport() {
        MonthlyReportDto report = reportService.getCurrentMonthReport();
        return ResponseEntity.ok(report);
    }
}
