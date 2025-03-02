package org.morsaprogramando.notepad.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.function.Consumer;

public class MenuView {
    private JMenuBar menuBar;
    private final JMenuItem openItem;
    private final JMenuItem saveItem;
    private final JMenuItem saveAsItem;
    private final JMenuItem exitItem;

    private final Consumer<File> fileConsumer;

    public MenuView(Consumer<File> fileConsumer) {
        this.fileConsumer = fileConsumer;
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        saveAsItem = new JMenuItem("Save As");
        exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        initMenuItems();
    }

    /**
     * Returns the JMenuBar for integration into the main application frame.
     *
     * @return The menu bar component.
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void addOpenListener(ActionListener listener) {
        openItem.addActionListener(listener);
    }

    /**
     * @param listener The action listener.
     */
    public void addSaveListener(ActionListener listener) {
        saveItem.addActionListener(listener);
    }

    /**
     * @param listener The action listener.
     */
    public void addSaveAsListener(ActionListener listener) {
        saveAsItem.addActionListener(listener);
    }

    /**
     * @param listener The action listener.
     */
    public void addExitListener(ActionListener listener) {
        exitItem.addActionListener(listener);
    }

    // Method to open a file dialog and return the selected file
    private File openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files (*.txt)", "txt"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();  // Return the File object
        }
        return null;  // Return null if no file was selected
    }

    private void initMenuItems() {
        openItem.addActionListener(e -> {
            File file = openFile();

            // idea: here we could have a consumer passed from the parent component
            // which we will feed this file to the upper logic can read its content

            if (file != null) {
                System.out.println("Selected file: " + file.getAbsolutePath());

                fileConsumer.accept(file);
            } else {
                System.out.println("No file selected");
            }
        });
    }
}
