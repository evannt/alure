package com.outdeved.alure.model.weather;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Temperature ranges may be expanded to include additional information
 * about the viability of lures or weather factors that dictate their use
 */
public enum TemperatureRange {
    DEEP_WINTER("Deep Winter", 0, 35, Set.of(Month.DECEMBER, Month.JANUARY, Month.FEBRUARY)),
    LATE_WINTER("Late Winter", 35, 45, Set.of(Month.FEBRUARY, Month.MARCH)),

    EARLY_SPRING("Early Spring", 45, 55, Set.of(Month.MARCH, Month.APRIL)),
    LATE_SPRING("Late Spring", 55, 65, Set.of(Month.APRIL, Month.MAY)),

    EARLY_SUMMER("Early Summer", 65, 78, Set.of(Month.MAY, Month.JUNE)),
    PEAK_SUMMER("Peak Summer", 78, 100, Set.of(Month.JUNE, Month.JULY, Month.AUGUST)),
    LATE_SUMMER("Late Summer", 70, 78, Set.of(Month.JULY, Month.AUGUST, Month.SEPTEMBER)),

    EARLY_FALL("Early Fall", 60, 75, Set.of(Month.SEPTEMBER, Month.OCTOBER)),
    LATE_FALL("Late Fall", 50, 60, Set.of(Month.OCTOBER, Month.NOVEMBER)),
    EARLY_WINTER("Early Winter", 35, 50, Set.of(Month.NOVEMBER, Month.DECEMBER));

    public final String name;
    public final int lowerBoundWaterTemp; // inclusive
    public final int upperBoundWaterTemp; // exclusive
    public final Set<Month> months;

    private TemperatureRange(String name, int lowerBoundWaterTemp, int upperBoundWaterTemp, Set<Month> months) {
        this.name = name;
        this.lowerBoundWaterTemp = lowerBoundWaterTemp;
        this.upperBoundWaterTemp = upperBoundWaterTemp;
        this.months = months;
    }

    public static TemperatureRange fromWaterTemp(int waterTemp, Month month) {
        List<TemperatureRange> possibleRanges = new ArrayList<>();
        // Finds range based on month and temperature range
        for (TemperatureRange tempRange : TemperatureRange.values()) {
            boolean containsMonth = tempRange.months.contains(month);
            if (containsMonth) {
                possibleRanges.add(tempRange);
            }
            if (waterTemp >= tempRange.lowerBoundWaterTemp
                    && waterTemp < tempRange.upperBoundWaterTemp
                    && containsMonth) {
                return tempRange;
            }
        }
        // Finds range based on month with closest temperature
        TemperatureRange range = TemperatureRange.LATE_SPRING;
        int rangeDistance = Math.min(Math.abs(range.lowerBoundWaterTemp - waterTemp),
                Math.abs(range.upperBoundWaterTemp - waterTemp));
        for (TemperatureRange possibleRange : possibleRanges) {
            int possibleDistance = Math.min(Math.abs(possibleRange.lowerBoundWaterTemp - waterTemp),
                    Math.abs(possibleRange.upperBoundWaterTemp - waterTemp));

            if (possibleDistance < rangeDistance) {
                range = possibleRange;
                rangeDistance = possibleDistance;
            }
        }

        return range;
    }

}
