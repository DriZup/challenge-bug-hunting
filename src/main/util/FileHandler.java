package main.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private final File file;


    public FileHandler(String filepath) {
        this.file = new File(filepath);
        try {
            if (file.createNewFile()) {
                System.out.println("Arquivo criado: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }

    public void writeLine(String line){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
            bw.write(line);
            bw.newLine();
    }catch(IOException e){
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
}
public List<String>readAllLines(){
    List<String>lines = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
    }catch (IOException e){
        System.out.println("Erro ao ler arquivo: " + e.getMessage());
    }
    return lines;
    }
}