package org.morsaprogramando.notepad.view;

import javax.swing.*;
import java.awt.*;

public class TextEditorView {
    private JTextArea textArea;
    private JScrollPane scrollPane;

    /**
     * Constructs the TextEditorView and initializes the JTextArea.
     */
    public TextEditorView() {
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
    }

    /**
     * Returns the JScrollPane containing the JTextArea.
     * This is useful for embedding into the main application view.
     *
     * @return JScrollPane with the text editor.
     */
    public JScrollPane getEditorComponent() {
        return scrollPane;
    }

    /**
     * Gets the text content from the editor.
     *
     * @return Current text in the editor.
     */
    public String getText() {
        return textArea.getText();
    }

    /**
     * Sets the text content in the editor.
     *
     * @param text The text to be displayed.
     */
    public void setText(String text) {
        textArea.setText(text);
    }

    /**
     * Clears the text in the editor.
     */
    public void clear() {
        textArea.setText("");
    }
}
