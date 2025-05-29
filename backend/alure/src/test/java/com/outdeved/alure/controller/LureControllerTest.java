package com.outdeved.alure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.outdeved.alure.core.LureRecommendation;
import com.outdeved.alure.core.LureRecommendationReport;
import com.outdeved.alure.core.LureRecommendationSystem;
import com.outdeved.alure.error.AlureRequestException;
import com.outdeved.alure.error.AlureRequestExceptionType;
import com.outdeved.alure.error.ErrorResponse;
import com.outdeved.alure.model.lure.Lure;
import com.outdeved.alure.model.weather.Weather;
import com.outdeved.alure.service.LureRankingService;
import com.outdeved.alure.util.GsonUtil;
import com.outdeved.alure.util.TestUtil;

@WebMvcTest(LureController.class)
public class LureControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private LureRankingService lureRankingService;

    private static Weather mockWeather;
    private static List<LureRecommendation> mockLures;

    @BeforeEach
    public void setup() {
        mockWeather = TestUtil.loadWeatherJson("weather/weather.json");
        List<LureRecommendation> catalog = LureRecommendationSystem.getLureCatalog();
        Collections.shuffle(catalog);
        mockLures = catalog.subList(0, 3);
    }

    @Test
    public void testGetLureCatalog_FullCatalog() throws Exception {
        when(lureRankingService.getLureCatalog()).thenReturn(LureRecommendationSystem.getLureCatalog());
        MvcResult result = mvc.perform(get("/lures/catalog"))
                .andExpect(status().isOk())
                .andReturn();
        verify(lureRankingService, times(1)).getLureCatalog();
        String jsonString = result.getResponse().getContentAsString();
        List<LureRecommendation> lures = GsonUtil.GSON.fromJson(jsonString,
                new TypeToken<List<LureRecommendation>>() {
                }.getType());
        assertEquals(Lure.values().length, lures.size());
    }

    @Test
    public void testGetLureRecommendations_OnlyCity_FullRecommendations() throws Exception {
        String location = "Long Beach";

        LureRecommendationReport mockReport = new LureRecommendationReport(mockWeather, 46, mockLures);
        when(lureRankingService.getLureRecommendations(location)).thenReturn(mockReport);
        MvcResult result = mvc.perform(get("/lures/suggest").param("location", location))
                .andExpect(status().isOk())
                .andReturn();
        verify(lureRankingService, times(1)).getLureRecommendations(location);

        String jsonString = result.getResponse().getContentAsString();
        LureRecommendationReport report = GsonUtil.GSON.fromJson(jsonString, LureRecommendationReport.class);
        System.out.println(report);
    }

    @Test
    public void testGetLureRecommendations_OnlyState_FullRecommendations() throws Exception {
        String location = "Virginia";
        LureRecommendationReport mockReport = new LureRecommendationReport(mockWeather, 46, mockLures);
        when(lureRankingService.getLureRecommendations(location)).thenReturn(mockReport);
        MvcResult result = mvc.perform(get("/lures/suggest").param("location", location))
                .andExpect(status().isOk())
                .andReturn();
        verify(lureRankingService, times(1)).getLureRecommendations(location);

        String jsonString = result.getResponse().getContentAsString();
        LureRecommendationReport report = GsonUtil.GSON.fromJson(jsonString, LureRecommendationReport.class);
        System.out.println(report);
    }

    @Test
    public void testGetLureRecommendations_InvalidLocation() throws Exception {
        String location = "Ocean";
        when(lureRankingService.getLureRecommendations(location))
                .thenThrow(new AlureRequestException(AlureRequestExceptionType.NO_LOCATION_FOUND));
        MvcResult result = mvc.perform(get("/lures/suggest").param("location",
                location))
                .andExpect(status().isBadRequest())
                .andReturn();

        verify(lureRankingService, times(1)).getLureRecommendations(location);

        String jsonString = result.getResponse().getContentAsString();
        ErrorResponse errorResponse = GsonUtil.GSON.fromJson(jsonString, ErrorResponse.class);
        assertEquals(AlureRequestExceptionType.NO_LOCATION_FOUND.errorMessage, errorResponse.message);
    }

}
