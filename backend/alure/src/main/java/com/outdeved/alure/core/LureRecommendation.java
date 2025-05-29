package com.outdeved.alure.core;

import java.io.IOException;

import com.outdeved.alure.model.lure.Lure;
import com.outdeved.alure.util.ImageUtil;

public class LureRecommendation {

    private String name;
    private String description;
    private String condition;
    private int score;
    private String reason;
    private String image;

    public LureRecommendation(Lure lure, String condition, int score) {
        this.name = lure.name;
        this.description = lure.description;

        try {
            this.image = ImageUtil.getBase64Image(lure.imageFile);
        } catch (IOException e) {
            this.image = "";
            e.printStackTrace();
        }
        this.condition = condition;
        this.score = score;
        this.reason = lure.reason;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getCondition() {
        return condition;
    }

    public int getScore() {
        return score;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "LureRecommendation={ lureName=" + name + ", lureDescription= " + description + ", condition="
                + condition + ", score=" + score + ", reason=" + reason + " }";
    }

}
