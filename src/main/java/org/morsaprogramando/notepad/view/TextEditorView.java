package org.morsaprogramando.notepad.view;

import javax.swing.*;
import java.awt.*;

public class TextEditorView {
    private final JTextArea textArea;
    private final JScrollPane scrollPane;

    public TextEditorView() {
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
    }

    public JScrollPane getScrollPane() {
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

    public void clear() {
        textArea.setText("");
    }
}
