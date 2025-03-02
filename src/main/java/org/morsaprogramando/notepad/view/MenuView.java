package org.morsaprogramando.notepad.view;

import javax.swing.*;
import java.io.File;
import java.util.function.Consumer;

public class MenuView {
    private JMenuBar menuBar;
    private final JMenuItem openItem;
    private final JMenuItem saveItem;
    private final JMenuItem saveAsItem;
    private final JMenuItem exitItem;

    private final Consumer<File> fileConsumer;
    private final Runnable saveRunnable;

    public MenuView(Consumer<File> fileConsumer, Runnable saveRunnable) {
        this.fileConsumer = fileConsumer;
        this.saveRunnable = saveRunnable;

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

    private void initMenuItems() {
        openItem.addActionListener(e -> {
            File file = openFile();

            if (file != null) {
                System.out.println("Selected file: " + file.getAbsolutePath());

                fileConsumer.accept(file);
            } else {
                System.out.println("No file selected");
            }
        });

        saveItem.addActionListener(e -> saveRunnable.run());

        exitItem.addActionListener(e -> System.exit(0));
    }
}
