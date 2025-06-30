package com.outdeved.alure.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.outdeved.alure.model.lure.Lure;

public class LureRecommendationSystemTest {

    @Test
    public void testGetLureRecommendation_Cold() {
        int waterTemp = 40;
        Month month = Month.NOVEMBER;
        List<LureRecommendation> recommendations = LureRecommendationSystem.getLureRecommendations(waterTemp, month);
        System.out.println("\nMonth: " + month + ", Temp:" + waterTemp);
        System.out.println(recommendations);
        assertNotNull(recommendations);
        assertEquals(5, recommendations.size());
        for (LureRecommendation recommendation : recommendations) {
            assertNotNull(recommendation);
        }
    }

    @Test
    public void testGetLureRecommendation_Warm() {
        int waterTemp = 70;
        Month month = Month.MAY;
        List<LureRecommendation> recommendations = LureRecommendationSystem.getLureRecommendations(waterTemp, month);
        System.out.println("\nMonth: " + month + ", Temp:" + waterTemp);
        System.out.println(recommendations);
        assertNotNull(recommendations);
        assertEquals(5, recommendations.size());
        for (LureRecommendation recommendation : recommendations) {
            assertNotNull(recommendation);
        }
    }

    @Test
    public void testGetLureRecommendation_Hot() {
        int waterTemp = 80;
        Month month = Month.JUNE;
        List<LureRecommendation> recommendations = LureRecommendationSystem.getLureRecommendations(waterTemp, month);
        System.out.println("\nMonth: " + month + ", Temp:" + waterTemp);
        System.out.println(recommendations);
        assertNotNull(recommendations);
        assertEquals(5, recommendations.size());
        for (LureRecommendation recommendation : recommendations) {
            assertNotNull(recommendation);
        }
    }

    @Test
    public void testGetLureRecommendation_ExtremelyCold() {
        int waterTemp = 10;
        Month month = Month.JANUARY;
        List<LureRecommendation> recommendations = LureRecommendationSystem.getLureRecommendations(waterTemp, month);
        System.out.println("\nMonth: " + month + ", Temp:" + waterTemp);
        System.out.println(recommendations);
        assertNotNull(recommendations);
        assertEquals(5, recommendations.size());
        for (LureRecommendation recommendation : recommendations) {
            assertNotNull(recommendation);
        }
    }

    @Test
    public void testGetLureRecommendation_ExtremelyHot() {
        int waterTemp = 95;
        Month month = Month.JULY;
        List<LureRecommendation> recommendations = LureRecommendationSystem.getLureRecommendations(waterTemp, month);
        System.out.println("\nMonth: " + month + ", Temp:" + waterTemp);
        System.out.println(recommendations);
        assertNotNull(recommendations);
        assertEquals(5, recommendations.size());
        for (LureRecommendation recommendation : recommendations) {
            assertNotNull(recommendation);
        }
    }

    @Test
    public void testGetLureRecommendation_Invalid() {
        int waterTemp = -20;
        Month month = Month.JANUARY;
        List<LureRecommendation> recommendations = LureRecommendationSystem.getLureRecommendations(waterTemp, month);
        System.out.println("\nMonth: " + month + ", Temp:" + waterTemp);
        System.out.println(recommendations);
        assertNotNull(recommendations);
        assertEquals(5, recommendations.size());
        for (LureRecommendation recommendation : recommendations) {
            assertNotNull(recommendation);
        }
    }

    @Test
    public void testGetLureCatalog() {
        List<LureRecommendation> recommendations = LureRecommendationSystem.getLureCatalog();
        assertNotNull(recommendations);
        assertEquals(Lure.values().length, recommendations.size());
        for (LureRecommendation recommendation : recommendations) {
            assertNotNull(recommendation);
        }
    }

}
