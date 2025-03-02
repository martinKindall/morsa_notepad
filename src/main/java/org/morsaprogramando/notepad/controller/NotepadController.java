package org.morsaprogramando.notepad.controller;

import org.morsaprogramando.notepad.service.FileService;
import org.morsaprogramando.notepad.view.NotepadView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public enum NotepadController {
    INSTANCE;

    private File currentFile;
    private boolean lastChangesSaved = true;

    public void run() {
        FileService fileService = new FileService();

        Function<File, String> openFileConsumer = (file) -> {
            try {
                currentFile = file;

                return fileService.readFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        SwingUtilities.invokeLater(() -> {
            NotepadView notepadView = new NotepadView(openFileConsumer);
            notepadView.show();
        });
    }
}
