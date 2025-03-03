package org.morsaprogramando.notepad.view;

import org.morsaprogramando.notepad.model.NotepadModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class NotepadView {
    private final NotepadModel notepadModel;
    private final JFrame frame;
    private final TextEditorView textEditorView;
    private final MenuView menuView;

    public NotepadView(NotepadModel notepadModel, Runnable newItemRunnable, Function<File, String> fileStringFunction,
                       Function<String, Boolean> saveFileFunction,
                       BiConsumer<File, String> saveAsFileConsumer) {

        this.notepadModel = notepadModel;
        frame = new JFrame("Morsa's notepad");
        textEditorView = new TextEditorView();

        menuView = initMenuView(newItemRunnable, fileStringFunction, saveFileFunction, saveAsFileConsumer);

        frame.setLayout(new BorderLayout());

        frame.setJMenuBar(menuView.getMenuBar());

        frame.add(textEditorView.getScrollPane(), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);
    }

    public void show() {
        frame.setVisible(true);
    }


    private MenuView initMenuView(Runnable newItemRunnable, Function<File, String> getFileContent, Function<String, Boolean> saveFileFunction, BiConsumer<File, String> saveAsFileConsumer) {
        return new MenuView(() -> {
            newItemRunnable.run();
            textEditorView.clear();
        }, (file -> {

            if (!notepadModel.getLastChangesSaved()) {
                // TODO: open dialog to confirm if changes should be ignored
            }

            String fileContent = getFileContent.apply(file);
            textEditorView.setText(fileContent);
        }), () -> {
            String fileContent = textEditorView.getText();
            Boolean fileNotFound = saveFileFunction.apply(fileContent);

            if (fileNotFound) menuView.saveFileHandler();

        }, file -> saveAsFileConsumer.accept(file, textEditorView.getText()));
    }
}
