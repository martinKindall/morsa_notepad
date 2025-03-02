package org.morsaprogramando.notepad.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class NotepadView {
    private JFrame frame;
    private TextEditorView textEditorView;
    private MenuView menuView;

    public NotepadView() {
        // Initialize the components
        frame = new JFrame("Notepad");
        textEditorView = new TextEditorView();

        menuView = new MenuView((File file) -> {});

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

    public static void main(String[] args) {
        // Run the Notepad application
        SwingUtilities.invokeLater(() -> {
            NotepadView notepadView = new NotepadView();
            notepadView.show();
        });
    }
}
