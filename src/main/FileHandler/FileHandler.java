package main.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private File file;

    public FileHandler(String filePath) {
        this.file = new File(filePath);
    }

    public void writeToFile(String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public List<String> readFromFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return lines;
    }

}

