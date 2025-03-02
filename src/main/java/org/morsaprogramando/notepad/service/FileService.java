package org.morsaprogramando.notepad.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileService {

    public String readFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (FileReader fr = new FileReader(file);
             BufferedReader reader = new BufferedReader(fr)) {

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }
}
