package gui.main;

import javax.swing.*;

public class MainMenuBar extends JMenuBar {

    public MainMenuBar() {
        initMenuBar();
    }

    private void initMenuBar() {
        var fileMenu = new JMenu("File");
        var editMenu = new JMenu("Edit");
        var helpMenu = new JMenu("Help");
        var menuItemOne = new JMenuItem("Item 1");
        var menuItemTwo = new JMenuItem("Item 2");
        var menuItemThree = new JMenuItem("Item 3");

        fileMenu.add(menuItemOne);
        fileMenu.add(menuItemTwo);
        fileMenu.add(menuItemThree);

        this.add(fileMenu);
        this.add(editMenu);
        this.add(helpMenu);
    }
}
