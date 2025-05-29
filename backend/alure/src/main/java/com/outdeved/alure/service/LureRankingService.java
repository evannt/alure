package com.outdeved.alure.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.outdeved.alure.core.LureRecommendation;
import com.outdeved.alure.core.LureRecommendationReport;
import com.outdeved.alure.core.LureRecommendationSystem;
import com.outdeved.alure.error.AlureRequestException;
import com.outdeved.alure.error.AlureRequestExceptionType;
import com.outdeved.alure.model.weather.ForecastDay;
import com.outdeved.alure.model.weather.Weather;
import com.outdeved.alure.util.GsonUtil;

@Service
public class LureRankingService {
    private static final int NUM_PREV_DAYS = 7;

    @Value("${alure.api.key}")
    private String apiKey;

    @Value("${alure.api.url}")
    private String apiUrl;

    private final HttpClient httpClient;

    public LureRankingService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<LureRecommendation> getLureCatalog() {
        return LureRecommendationSystem.getLureCatalog();
    }

    public LureRecommendationReport getLureRecommendations(String location) throws IOException, InterruptedException {
        Weather weather = fetchWeatherData(location);
        int hour = weather.getDateTime().getHour();
        LocalDateTime startDate = weather.getDateTime().minusDays(NUM_PREV_DAYS);
        LocalDateTime endDate = weather.getDateTime();
        List<ForecastDay> pastWeather = fetchPastWeatherData(location, hour, startDate, endDate);
        int estWaterTemp = getWaterTempEstimate(pastWeather);

        List<LureRecommendation> lures = LureRecommendationSystem.getLureRecommendations(estWaterTemp,
                weather.getMonth());
        return new LureRecommendationReport(weather, estWaterTemp, lures);
    }

    protected Weather fetchWeatherData(String location) throws IOException, InterruptedException {
        String uri = UriComponentsBuilder.fromUriString(apiUrl)
                .path("/forecast.json")
                .queryParam("key", apiKey)
                .queryParam("days", 1)
                .queryParam("q", location)
                .toUriString();
        HttpRequest weatherRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        HttpResponse<String> weatherResponse = httpClient.send(weatherRequest, HttpResponse.BodyHandlers.ofString());

        if (weatherResponse.statusCode() == HttpStatus.BAD_REQUEST.value()) {
            throw new AlureRequestException(AlureRequestExceptionType.NO_LOCATION_FOUND);
        }

        return GsonUtil.GSON.fromJson(weatherResponse.body(), Weather.class);
    }

    protected List<ForecastDay> fetchPastWeatherData(String location, int hour, LocalDateTime startDate,
            LocalDateTime endDate)
            throws IOException, InterruptedException {
        String uri = UriComponentsBuilder.fromUriString(apiUrl)
                .path("/history.json")
                .queryParam("key", apiKey)
                .queryParam("q", location)
                .queryParam("dt", startDate.toString())
                .queryParam("end_dt", endDate.toString())
                .queryParam("hour", hour)
                .toUriString();
        HttpRequest weatherRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        HttpResponse<String> weatherResponse = httpClient.send(weatherRequest, HttpResponse.BodyHandlers.ofString());

        if (weatherResponse.statusCode() == HttpStatus.BAD_REQUEST.value()) {
            throw new AlureRequestException(AlureRequestExceptionType.NO_LOCATION_FOUND);
        }

        JsonObject json = GsonUtil.GSON.fromJson(weatherResponse.body(), JsonObject.class);
        JsonArray forecastDays = json.get("forecast").getAsJsonObject().get("forecastday").getAsJsonArray();

        if (weatherResponse.statusCode() == HttpStatus.BAD_REQUEST.value()) {
            throw new AlureRequestException(AlureRequestExceptionType.NO_LOCATION_FOUND);
        }

        return GsonUtil.GSON.fromJson(forecastDays, new TypeToken<List<ForecastDay>>() {
        }.getType());
    }

    protected int getWaterTempEstimate(List<ForecastDay> forecastDays) {
        double weightDecayRate = 0.98;
        double weightedAvgAirTemps = 0;
        double totalWeight = 0;
        for (int i = 0; i < forecastDays.size(); i++) {
            double weight = Math.pow(weightDecayRate, i);
            weightedAvgAirTemps += (weight * forecastDays.get(i).getAvgTemp());
            totalWeight += weight;
        }
        double baseWaterTemp = (int) (0.98 * (weightedAvgAirTemps / totalWeight));
        double temperatureTrend = 0.25 * getTemperatureTrend(forecastDays);
        return (int) Math.round(baseWaterTemp + temperatureTrend);
    }

    private double getTemperatureTrend(List<ForecastDay> forecastDays) {
        if (forecastDays.size() < 3)
            return 0;

        return (forecastDays.get(0).getAvgTemp() - forecastDays.get(2).getAvgTemp()) / 2.0;
    }
}
