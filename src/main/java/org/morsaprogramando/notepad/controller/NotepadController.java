package org.morsaprogramando.notepad.controller;

import org.morsaprogramando.notepad.service.FileService;
import org.morsaprogramando.notepad.view.NotepadView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public enum NotepadController {
    INSTANCE;

    private File currentFile;
    private boolean lastChangesSaved = true;

    public void run() {
        FileService fileService = new FileService();

        Runnable newItemRunnable = () -> {
            currentFile = null;
        };

        Function<File, String> openFileConsumer = (file) -> {
            try {
                currentFile = file;

                return fileService.readFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Consumer<String> saveFileConsumer = (String content) -> {
            try {
                if (currentFile == null || content == null) return;

                fileService.saveFile(currentFile, content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        BiConsumer<File, String> saveAsFileConsumer = (file, content) -> {
            currentFile = file;

            try {
                fileService.saveFile(file, content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        SwingUtilities.invokeLater(() -> {
            NotepadView notepadView = new NotepadView(newItemRunnable, openFileConsumer,
                    saveFileConsumer, saveAsFileConsumer);
            notepadView.show();
        });
    }
}
