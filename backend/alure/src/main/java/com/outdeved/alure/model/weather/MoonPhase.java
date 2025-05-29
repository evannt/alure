package com.outdeved.alure.model.weather;

import java.util.HashMap;
import java.util.Map;

public enum MoonPhase {

    NEW_MOON("New Moon"),
    WAXING_CRESCENT("Waxing Crescent"),
    FIRST_QUARTER("First Quarter"),
    WAXING_GIBBOUS("Waxing Gibbous"),
    FULL_MOON("Full Moon"),
    WANING_GIBBOUS("Waning Gibbous"),
    LAST_QUARTER("Last Quarter"),
    WANING_CRESCENT("Waning Crescent");

    private static final Map<String, MoonPhase> BY_NAME = new HashMap<>();

    static {
        for (MoonPhase moonPhase : MoonPhase.values()) {
            BY_NAME.put(moonPhase.name, moonPhase);
        }
    }

    private String name;

    private MoonPhase(String name) {
        this.name = name;
    }

    public static MoonPhase fromName(String name) {
        return BY_NAME.get(name);
    }
}
