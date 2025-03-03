package org.morsaprogramando.notepad.model;


import java.io.File;

public class NotepadModel {

    private File currentFile;

    private boolean lastChangesSaved = true;

    public File getCurrentFile() {
        return currentFile;
    }

    public boolean getLastChangesSaved() {
        return lastChangesSaved;
    }

    public void setCurrentFile(File file) {
        currentFile = file;
    }

    public void setLastChangesSaved(boolean changed) {
        lastChangesSaved = changed;
    }
}
