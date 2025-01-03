package main.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private FileUtils() {
        // Classe utilitária, não deve ser instanciada
    }

    public static void writeToFile(String filePath, String content) {
        if (filePath == null || content == null) {
            throw new IllegalArgumentException("File path and content cannot be null.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + filePath, e);
        }
    }

    public static List<String> readFromFile(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null.");
        }

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + filePath, e);
        }
        return lines;
    }
}