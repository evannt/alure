package com.outdeved.alure.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Month;

import org.junit.jupiter.api.Test;

import com.outdeved.alure.model.weather.TemperatureRange;

public class TemperatureRangeTest {

    @Test
    public void testDeepWinterRange() {
        int temp = 30;
        Month month = Month.JANUARY;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.DEEP_WINTER, tempRange);
    }

    @Test
    public void testLateWinterRange() {
        int temp = 44;
        Month month = Month.FEBRUARY;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.LATE_WINTER, tempRange);
    }

    @Test
    public void testEarlySpringRange() {
        int temp = 49;
        Month month = Month.MARCH;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.EARLY_SPRING, tempRange);
    }

    @Test
    public void testLateSpringRange() {
        int temp = 55;
        Month month = Month.APRIL;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.LATE_SPRING, tempRange);
    }

    @Test
    public void testEarlySummerRange() {
        int temp = 70;
        Month month = Month.MAY;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.EARLY_SUMMER, tempRange);
    }

    @Test
    public void testPeakSummerRange() {
        int temp = 79;
        Month month = Month.JUNE;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.PEAK_SUMMER, tempRange);
    }

    @Test
    public void testLateSummerRange() {
        int temp = 70;
        Month month = Month.JULY;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.LATE_SUMMER, tempRange);
    }

    @Test
    public void testEarlyFallRange() {
        int temp = 62;
        Month month = Month.SEPTEMBER;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.EARLY_FALL, tempRange);
    }

    @Test
    public void testLateFallRange() {
        int temp = 55;
        Month month = Month.OCTOBER;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.LATE_FALL, tempRange);
    }

    @Test
    public void testEarlyWinterRange() {
        int temp = 44;
        Month month = Month.NOVEMBER;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.EARLY_WINTER, tempRange);
    }

    @Test
    public void testOutOfRangeLateSpringRange() {
        int temp = 46;
        Month month = Month.MAY;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.LATE_SPRING, tempRange);
    }

    @Test
    public void testOutOfRangeEarlySummerRange() {
        int temp = 79;
        Month month = Month.MAY;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.EARLY_SUMMER, tempRange);
    }

    @Test
    public void testExtremeDeepWinterRange() {
        int temp = -10;
        Month month = Month.DECEMBER;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.DEEP_WINTER, tempRange);
    }

    @Test
    public void testExtremePeakSummerRange() {
        int temp = 101;
        Month month = Month.JULY;
        TemperatureRange tempRange = TemperatureRange.fromWaterTemp(temp, month);
        assertEquals(TemperatureRange.PEAK_SUMMER, tempRange);
    }

}
