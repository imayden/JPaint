package view.gui;

/* 
 * Campus Connect E-mail: ydeng24@depaul.edu
 * Github: https://github.com/imayden
*/

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;

public class ButtonPanel extends JPanel {
    
    public ButtonPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  // Set the layout to BoxLayout with vertical direction

        JLabel noteLabel = new JLabel("\u229B Press and drag your mouse in the drawing panel above to create your artworks \u229B");
        noteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Set labels to be centered horizontally

        this.add(noteLabel);

        JPanel buttonsPanel = new JPanel();  // Set up a new panel to hold the buttons
        JButton undoButton = new JButton("\u21A9 Undo");
        JButton redoButton = new JButton("\u21AA Redo");
        JButton clearButton = new JButton("\u2717 Clear");

        buttonsPanel.add(undoButton);
        buttonsPanel.add(redoButton);
        buttonsPanel.add(clearButton);

        this.add(buttonsPanel);  
    }
    
    public JButton getUndoButton() {
        return (JButton) ((JPanel) this.getComponent(1)).getComponent(0);  // Adjust the way to get the button
    }

    public JButton getRedoButton() {
        return (JButton) ((JPanel) this.getComponent(1)).getComponent(1);
    }

    public JButton getClearButton() {
        return (JButton) ((JPanel) this.getComponent(1)).getComponent(2);
    }
}
