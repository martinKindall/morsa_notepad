package org.morsaprogramando.notepad.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class MenuView {
    private JMenuBar menuBar;
    private final JMenuItem newItem;
    private final JMenuItem openItem;
    private final JMenuItem saveItem;
    private final JMenuItem saveAsItem;
    private final JMenuItem exitItem;

    private final Runnable newItemRunnable;
    private final Consumer<File> openFileConsumer;
    private final Runnable saveRunnable;
    private final Consumer<File> saveAsFileConsumer;

    public MenuView(Runnable newItemRunnable, Consumer<File> openFileConsumer, Runnable saveRunnable, Consumer<File> saveAsFileConsumer) {
        this.newItemRunnable = newItemRunnable;
        this.openFileConsumer = openFileConsumer;
        this.saveRunnable = saveRunnable;
        this.saveAsFileConsumer = saveAsFileConsumer;


        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        saveAsItem = new JMenuItem("Save As");
        exitItem = new JMenuItem("Exit");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        initMenuItems();
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    private File openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files (*.txt)", "txt"));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    public File saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));

        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Ensure the file has a .txt extension
            if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            }

            try {
                if (selectedFile.createNewFile() || selectedFile.exists()) {
                    return selectedFile;
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error creating file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return null;
    }

    private void initMenuItems() {

        newItem.addActionListener(e -> newItemRunnable.run());

        openItem.addActionListener(e -> {
            File file = openFile();

            if (file != null) {
                System.out.println("Selected file: " + file.getAbsolutePath());

                openFileConsumer.accept(file);
            } else {
                System.out.println("No file selected");
            }
        });

        saveItem.addActionListener(e -> saveRunnable.run());

        saveAsItem.addActionListener(e -> {
            File file = saveFile();

            if (file != null) {
                System.out.println("Selected file for saving: " + file.getAbsolutePath());
                saveAsFileConsumer.accept(file);

            } else {
                System.out.println("No file saved");
            }
        });

        exitItem.addActionListener(e -> System.exit(0));
    }
}
