package org.morsaprogramando.notepad.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.function.Function;

public class NotepadView {
    private JFrame frame;
    private TextEditorView textEditorView;
    private MenuView menuView;

    public NotepadView(Function<File, String> fileStringFunction) {
        // Initialize the components
        frame = new JFrame("Notepad");
        textEditorView = new TextEditorView();

        menuView = new MenuView((file -> {
            String fileContent = fileStringFunction.apply(file);
            textEditorView.setText(fileContent);
        }));

        // Set the frame layout
        frame.setLayout(new BorderLayout());

        // Add the menu to the top
        frame.setJMenuBar(menuView.getMenuBar());

        // Add the text editor to the center (scrollPane is part of textEditorView)
        frame.add(textEditorView.getScrollPane(), BorderLayout.CENTER);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set window size
        frame.setSize(800, 600);
    }

    // Method to display the frame
    public void show() {
        frame.setVisible(true);
    }
}
