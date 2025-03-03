package org.morsaprogramando.notepad.controller;

import org.morsaprogramando.notepad.model.NotepadModel;
import org.morsaprogramando.notepad.service.FileService;
import org.morsaprogramando.notepad.view.NotepadView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Function;

public enum NotepadController {
    INSTANCE;

    private NotepadModel notepadModel = new NotepadModel();

    public void run() {
        Runnable newItemRunnable = () -> notepadModel = null;

        Function<File, String> openFileConsumer = (file) -> {
            try {
                notepadModel.setCurrentFile(file);
                notepadModel.setLastChangesSaved(true);

                return FileService.INSTANCE.readFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Function<String, Boolean> saveFileNotFound = (String content) -> {
            if (notepadModel.getCurrentFile() == null) {
                return true;
            }

            try {
                FileService.INSTANCE.saveFile(notepadModel.getCurrentFile(), content);
                return false;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        BiConsumer<File, String> saveAsFileConsumer = (file, content) -> {
            notepadModel.setCurrentFile(file);
            notepadModel.setLastChangesSaved(true);

            try {
                FileService.INSTANCE.saveFile(file, content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        SwingUtilities.invokeLater(() -> {
            NotepadView notepadView = new NotepadView(
                    notepadModel,
                    newItemRunnable, openFileConsumer,
                    saveFileNotFound, saveAsFileConsumer);
            notepadView.show();
        });
    }
}
