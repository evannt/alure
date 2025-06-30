package com.outdeved.alure.core;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.outdeved.alure.model.lure.Lure;
import com.outdeved.alure.model.weather.TemperatureRange;

public class LureRecommendationSystem {
    private static final Map<Lure, Map<TemperatureRange, Integer>> LURE_RANKINGS = Map.ofEntries(

            Map.entry(Lure.SPINNER_BAIT, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 2),
                    Map.entry(TemperatureRange.LATE_WINTER, 4),
                    Map.entry(TemperatureRange.EARLY_SPRING, 6),
                    Map.entry(TemperatureRange.LATE_SPRING, 8),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 9),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 7),
                    Map.entry(TemperatureRange.LATE_SUMMER, 8),
                    Map.entry(TemperatureRange.EARLY_FALL, 8),
                    Map.entry(TemperatureRange.LATE_FALL, 6),
                    Map.entry(TemperatureRange.EARLY_WINTER, 4))),

            Map.entry(Lure.SWIM_BAIT, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 3),
                    Map.entry(TemperatureRange.LATE_WINTER, 5),
                    Map.entry(TemperatureRange.EARLY_SPRING, 7),
                    Map.entry(TemperatureRange.LATE_SPRING, 8),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 9),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 9),
                    Map.entry(TemperatureRange.LATE_SUMMER, 9),
                    Map.entry(TemperatureRange.EARLY_FALL, 8),
                    Map.entry(TemperatureRange.LATE_FALL, 6),
                    Map.entry(TemperatureRange.EARLY_WINTER, 4))),

            Map.entry(Lure.JERK_BAIT, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 10),
                    Map.entry(TemperatureRange.LATE_WINTER, 10),
                    Map.entry(TemperatureRange.EARLY_SPRING, 9),
                    Map.entry(TemperatureRange.LATE_SPRING, 7),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 6),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 3),
                    Map.entry(TemperatureRange.LATE_SUMMER, 4),
                    Map.entry(TemperatureRange.EARLY_FALL, 6),
                    Map.entry(TemperatureRange.LATE_FALL, 8),
                    Map.entry(TemperatureRange.EARLY_WINTER, 9))),

            Map.entry(Lure.HARD_TOPWATERS, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 1),
                    Map.entry(TemperatureRange.LATE_WINTER, 1),
                    Map.entry(TemperatureRange.EARLY_SPRING, 2),
                    Map.entry(TemperatureRange.LATE_SPRING, 4),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 7),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 8),
                    Map.entry(TemperatureRange.LATE_SUMMER, 8),
                    Map.entry(TemperatureRange.EARLY_FALL, 6),
                    Map.entry(TemperatureRange.LATE_FALL, 3),
                    Map.entry(TemperatureRange.EARLY_WINTER, 1))),

            Map.entry(Lure.CRANK_BAIT, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 8),
                    Map.entry(TemperatureRange.LATE_WINTER, 5),
                    Map.entry(TemperatureRange.EARLY_SPRING, 6),
                    Map.entry(TemperatureRange.LATE_SPRING, 7),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 8),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 8),
                    Map.entry(TemperatureRange.LATE_SUMMER, 8),
                    Map.entry(TemperatureRange.EARLY_FALL, 8),
                    Map.entry(TemperatureRange.LATE_FALL, 6),
                    Map.entry(TemperatureRange.EARLY_WINTER, 5))),

            Map.entry(Lure.SPOON, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 9),
                    Map.entry(TemperatureRange.LATE_WINTER, 8),
                    Map.entry(TemperatureRange.EARLY_SPRING, 6),
                    Map.entry(TemperatureRange.LATE_SPRING, 4),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 4),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 5),
                    Map.entry(TemperatureRange.LATE_SUMMER, 7),
                    Map.entry(TemperatureRange.EARLY_FALL, 8),
                    Map.entry(TemperatureRange.LATE_FALL, 7),
                    Map.entry(TemperatureRange.EARLY_WINTER, 6))),

            Map.entry(Lure.BUZZ_BAIT, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 1),
                    Map.entry(TemperatureRange.LATE_WINTER, 1),
                    Map.entry(TemperatureRange.EARLY_SPRING, 1),
                    Map.entry(TemperatureRange.LATE_SPRING, 3),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 6),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 8),
                    Map.entry(TemperatureRange.LATE_SUMMER, 8),
                    Map.entry(TemperatureRange.EARLY_FALL, 5),
                    Map.entry(TemperatureRange.LATE_FALL, 2),
                    Map.entry(TemperatureRange.EARLY_WINTER, 1))),

            Map.entry(Lure.PLASTIC_WORM, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 5),
                    Map.entry(TemperatureRange.LATE_WINTER, 6),
                    Map.entry(TemperatureRange.EARLY_SPRING, 6),
                    Map.entry(TemperatureRange.LATE_SPRING, 7),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 8),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 9),
                    Map.entry(TemperatureRange.LATE_SUMMER, 8),
                    Map.entry(TemperatureRange.EARLY_FALL, 8),
                    Map.entry(TemperatureRange.LATE_FALL, 6),
                    Map.entry(TemperatureRange.EARLY_WINTER, 4))),

            Map.entry(Lure.CREATURE_BAIT, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 5),
                    Map.entry(TemperatureRange.LATE_WINTER, 6),
                    Map.entry(TemperatureRange.EARLY_SPRING, 6),
                    Map.entry(TemperatureRange.LATE_SPRING, 8),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 9),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 8),
                    Map.entry(TemperatureRange.LATE_SUMMER, 8),
                    Map.entry(TemperatureRange.EARLY_FALL, 7),
                    Map.entry(TemperatureRange.LATE_FALL, 6),
                    Map.entry(TemperatureRange.EARLY_WINTER, 4))),

            Map.entry(Lure.GRUB, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 8),
                    Map.entry(TemperatureRange.LATE_WINTER, 8),
                    Map.entry(TemperatureRange.EARLY_SPRING, 7),
                    Map.entry(TemperatureRange.LATE_SPRING, 5),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 5),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 6),
                    Map.entry(TemperatureRange.LATE_SUMMER, 7),
                    Map.entry(TemperatureRange.EARLY_FALL, 6),
                    Map.entry(TemperatureRange.LATE_FALL, 7),
                    Map.entry(TemperatureRange.EARLY_WINTER, 8))),

            Map.entry(Lure.JIG, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 8),
                    Map.entry(TemperatureRange.LATE_WINTER, 7),
                    Map.entry(TemperatureRange.EARLY_SPRING, 7),
                    Map.entry(TemperatureRange.LATE_SPRING, 8),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 8),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 9),
                    Map.entry(TemperatureRange.LATE_SUMMER, 8),
                    Map.entry(TemperatureRange.EARLY_FALL, 8),
                    Map.entry(TemperatureRange.LATE_FALL, 7),
                    Map.entry(TemperatureRange.EARLY_WINTER, 4))),

            Map.entry(Lure.TUBE, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 9),
                    Map.entry(TemperatureRange.LATE_WINTER, 9),
                    Map.entry(TemperatureRange.EARLY_SPRING, 8),
                    Map.entry(TemperatureRange.LATE_SPRING, 7),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 6),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 6),
                    Map.entry(TemperatureRange.LATE_SUMMER, 7),
                    Map.entry(TemperatureRange.EARLY_FALL, 6),
                    Map.entry(TemperatureRange.LATE_FALL, 7),
                    Map.entry(TemperatureRange.EARLY_WINTER, 8))),

            Map.entry(Lure.FROG, Map.ofEntries(
                    Map.entry(TemperatureRange.DEEP_WINTER, 1),
                    Map.entry(TemperatureRange.LATE_WINTER, 1),
                    Map.entry(TemperatureRange.EARLY_SPRING, 1),
                    Map.entry(TemperatureRange.LATE_SPRING, 3),
                    Map.entry(TemperatureRange.EARLY_SUMMER, 6),
                    Map.entry(TemperatureRange.PEAK_SUMMER, 10),
                    Map.entry(TemperatureRange.LATE_SUMMER, 9),
                    Map.entry(TemperatureRange.EARLY_FALL, 6),
                    Map.entry(TemperatureRange.LATE_FALL, 3),
                    Map.entry(TemperatureRange.EARLY_WINTER, 1))));

    public static List<LureRecommendation> getLureCatalog() {
        return LURE_RANKINGS.entrySet().stream()
                .map(e -> {
                    Lure lure = e.getKey();
                    Map.Entry<TemperatureRange, Integer> range = e.getValue().entrySet().stream()
                            .max(Map.Entry.comparingByValue()).orElse(Map.entry(TemperatureRange.EARLY_FALL, 5));
                    String rangeName = range.getKey().name;
                    int rangeScore = range.getValue();
                    return new LureRecommendation(lure, rangeName, rangeScore);
                })
                .collect(Collectors.toList());
    }

    public static List<LureRecommendation> getLureRecommendations(int waterTemperature, Month month) {
        return getLureRecommendations(5, waterTemperature, month);
    }

    public static List<LureRecommendation> getLureRecommendations(int maxLures, int waterTemperature, Month month) {
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(waterTemperature, month);
        return LURE_RANKINGS.entrySet().stream()
                .map(e -> new LureRecommendation(e.getKey(), tempRange.name, e.getValue().get(tempRange)))
                .sorted((a, b) -> b.getScore() - a.getScore())
                .limit(maxLures)
                .collect(Collectors.toList());
    }

}
