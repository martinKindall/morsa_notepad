package org.morsaprogramando.notepad.service;

import java.io.*;
import java.util.Objects;

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

    public void saveFile(File file, String content) throws IOException {
        Objects.requireNonNull(file, "no file provided");
        Objects.requireNonNull(content, "no content provided");

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }
}
