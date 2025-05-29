package com.outdeved.alure.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.outdeved.alure.core.LureRecommendation;
import com.outdeved.alure.core.LureRecommendationReport;
import com.outdeved.alure.service.LureRankingService;

@RestController
@RequestMapping("/lures")
public class LureController {

    @Autowired
    private LureRankingService lureRankingService;

    @GetMapping("/catalog")
    public List<LureRecommendation> getAllLures() {
        return lureRankingService.getLureCatalog();
    }

    @GetMapping("/suggest")
    public LureRecommendationReport getLureRecommendations(@RequestParam String location)
            throws IOException, InterruptedException {
        return lureRankingService.getLureRecommendations(location);
    }

}
