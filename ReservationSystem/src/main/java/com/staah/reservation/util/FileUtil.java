package com.staah.reservation.util;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class FileUtil {
    public static <T> List<T> readJsonFile(String filePath, Class<T[]> clazz) {

        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return List.of(objectMapper.readValue(new File("src/main/resources/"+filePath), clazz));
        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }
}
