package Scripts.Panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClassPanel {
    public JButton knightClassButton, heraldClassButton, sorcererClassButton, clericClassButton;
    private JPanel classPanel = new JPanel();
    private ArrayList<JButton> classesButtons = new ArrayList<JButton>();
    private String classChosen;

    public ClassPanel() {
        this.knightClassButton = new JButton("Knight");
        this.heraldClassButton = new JButton("Herald");
        this.sorcererClassButton = new JButton("Sorcerer");
        this.clericClassButton = new JButton("Cleric");

        this.classesButtons.add(knightClassButton);
        this.classesButtons.add(heraldClassButton);
        this.classesButtons.add(sorcererClassButton);
        this.classesButtons.add(clericClassButton);

        this.classPanel = new JPanel();
        this.classPanel.setBounds(400, 150, 300, 500);
        this.classPanel.setBackground(Color.BLUE);
        this.classPanel.setLayout(null);

        for (JButton jButton : this.classesButtons) {
            this.classPanel.add(jButton);
        }
        this.classPanel.setVisible(false);

        for (int i = 0; i < this.classesButtons.size(); i++) {
            this.classesButtons.get(i).setBounds(50, 200 + (i * 60), 200, 50);
            this.classesButtons.get(i).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    for (JButton jButton : classesButtons) {
                        if (e.getSource() == jButton) {
                            classChosen = jButton.getText();
                            classPanel.setVisible(false);
                            ChosenAttPanel.getPanel().setVisible(true);
                            // JOptionPane.showMessageDialog(jButton, classChosen);
                        }
                    }
                }

            });
        }

    }

    public JPanel getPanel() {
        return classPanel;
    }

    public ArrayList<JButton> getButtonsArray() {
        return classesButtons;
    }
}
