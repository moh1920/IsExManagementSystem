package org.sayari.isexmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.sayari.isexmanagementsystem.dto.GraphDTo;
import org.sayari.isexmanagementsystem.service.stats.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/stats")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatsController {


    @Autowired
    private StatsService statsService;

    @GetMapping("/chart")
    public ResponseEntity<GraphDTo> getGraphDTo(){
        return ResponseEntity.ok(statsService.getChartData());
    }
    @GetMapping("/getstats")
    public ResponseEntity<?> getStats(){
        return ResponseEntity.ok(statsService.getStats());
    }

}
