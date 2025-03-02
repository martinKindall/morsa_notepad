package org.morsaprogramando.notepad.controller;

import org.morsaprogramando.notepad.service.FileService;
import org.morsaprogramando.notepad.view.NotepadView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Function;

public enum NotepadController {
    INSTANCE;

    private File currentFile;

    public void run() {
        Runnable newItemRunnable = () -> currentFile = null;

        Function<File, String> openFileConsumer = (file) -> {
            try {
                currentFile = file;

                return FileService.INSTANCE.readFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Function<String, Boolean> saveFileNotFound = (String content) -> {
            if (currentFile == null) {
                return true;
            }

            try {
                FileService.INSTANCE.saveFile(currentFile, content);
                return false;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        BiConsumer<File, String> saveAsFileConsumer = (file, content) -> {
            currentFile = file;

            try {
                FileService.INSTANCE.saveFile(file, content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        SwingUtilities.invokeLater(() -> {
            NotepadView notepadView = new NotepadView(newItemRunnable, openFileConsumer,
                    saveFileNotFound, saveAsFileConsumer);
            notepadView.show();
        });
    }
}
