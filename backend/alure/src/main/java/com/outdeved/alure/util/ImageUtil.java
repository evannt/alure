package com.outdeved.alure.util;

import java.io.IOException;
import java.util.Base64;

import org.springframework.core.io.ClassPathResource;

public class ImageUtil {

    private static final String PNG_DATA_URI = "data:image/png;base64,";

    public static String getBase64Image(String path) throws IOException {
        byte[] imageBytes = new ClassPathResource(path).getInputStream().readAllBytes();
        return PNG_DATA_URI + Base64.getEncoder().encodeToString(imageBytes);
    }

}
