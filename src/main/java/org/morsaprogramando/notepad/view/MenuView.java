package org.morsaprogramando.notepad.view;

import javax.swing.*;
import java.awt.event.ActionListener;
public class MenuView {
    /**
     * Represents the menu bar for the Notepad app.
     */
    private JMenuBar menuBar;
    private JMenuItem openItem, saveItem, saveAsItem, exitItem;

    /**
     * Constructs the MenuView and initializes the menu items.
     */
    public MenuView() {
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
    }

    /**
     * Returns the JMenuBar for integration into the main application frame.
     *
     * @return The menu bar component.
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Adds an action listener to the Open menu item.
     *
     * @param listener The action listener.
     */
    public void addOpenListener(ActionListener listener) {
        openItem.addActionListener(listener);
    }

    /**
     * Adds an action listener to the Save menu item.
     *
     * @param listener The action listener.
     */
    public void addSaveListener(ActionListener listener) {
        saveItem.addActionListener(listener);
    }

    /**
     * Adds an action listener to the Save As menu item.
     *
     * @param listener The action listener.
     */
    public void addSaveAsListener(ActionListener listener) {
        saveAsItem.addActionListener(listener);
    }

    /**
     * Adds an action listener to the Exit menu item.
     *
     * @param listener The action listener.
     */
    public void addExitListener(ActionListener listener) {
        exitItem.addActionListener(listener);
    }
}
