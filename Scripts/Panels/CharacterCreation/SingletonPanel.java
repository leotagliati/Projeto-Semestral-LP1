package Scripts.Panels.CharacterCreation;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Scripts.AudioHandler;
import Scripts.ImagesConversion.ImageCreate;
import Scripts.Model.GameCharacter;
import Scripts.Repository.CharacterRepository;

public class SingletonPanel extends JPanel {
    private static SingletonPanel instance;

    public JLabel nameInput, classInput;
    public String eyesInput, skinInput, physicInput, helmInput, chestInput, legsInput;
    private JLabel nameTitle, classTitle;
    private ArrayList<JLabel> textArray = new ArrayList<JLabel>();

    private JButton saveButton = new JButton("Salvar");

    private JPanel titlesPanel = new JPanel();
    private JPanel savePanel = new JPanel();
    private JPanel saveBackGNDPanel = new JPanel();

    private SingletonPanel() {

    }

    public static SingletonPanel getInstance() {
        if (instance == null) {
            instance = new SingletonPanel();
        }
        return instance;
    }

    public void initPanel(GameCharacter character) {

        CharacterRepository repo = new CharacterRepository();

        // Set propriedade do objeto
        this.setBounds(425, 50, 500, 700);
        this.setBackground(Color.BLUE);
        this.setOpaque(true);
        this.setLayout(null);
        this.setVisible(true);

        // Inicializa os Textos
        nameTitle = new JLabel("Nome: ");
        classTitle = new JLabel("Classe: ");

        // Inicialize os atributos
        nameInput = new JLabel(character.getName());
        classInput = new JLabel(character.getSkillClass());
        eyesInput = character.getEyeColor();
        skinInput = character.getSkinColor();
        physicInput = character.getPhysicType();
        // helmInput = character.charHelm;
        // chestInput = character.charChest;
        // legsInput =character. charLegs;

        // Add os textos ao array
        textArray.add(nameInput);
        textArray.add(classInput);
        textArray.add(nameTitle);
        textArray.add(classTitle);

        // Cria o design do botao
        ImageCreate buttonImage = new ImageCreate(100, 500, 300, 100);
        buttonImage.setAlignment(JLabel.CENTER, JLabel.CENTER);
        buttonImage.setIconFile("Images\\button.png");
        buttonImage.imageSetter();

        // Inicializa o botao SALVAR
        saveButton.setBounds(100, 500, 300, 100);
        saveButton.setFont(new Font("Adobe Garamond Pro", Font.PLAIN, 34));
        saveButton.setForeground(Color.WHITE);
        saveButton.setOpaque(true);
        saveButton.setContentAreaFilled(true);
        saveButton.setBorderPainted(false);
        saveButton.setFocusable(false);
        saveButton.setVisible(true);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("name:" + nameInput.getText());
                // System.out.println("class:" + classInput.getText());
                if (!nameInput.getText().equals("Entrada incompleta")) {
                    AudioHandler.audioPlay("Music\\charSaved.wav");
                    saveButton.setText("Salvo!");
                    buttonImage.setIconFile("Images\\charSavedButton.png");
                    buttonImage.imageSetter();
                    repo.addCharacter(new GameCharacter(nameInput.getText(), classInput.getText(), eyesInput,
                            skinInput, physicInput));
                } else {
                    AudioHandler.audioPlay("Music\\charNotSaved.wav");
                    saveButton.setText("Insira seu nome!");
                    buttonImage.setIconFile("Images\\charNotSavedButton.png");
                    buttonImage.imageSetter();

                }

            }
        });
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() == saveButton) {
                    buttonImage.setIcon(new ImageIcon("Images\\buttonClicked.png"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == saveButton) {
                    saveButton.setText("Salvar");
                    buttonImage.setIcon(new ImageIcon("Images\\button.png"));
                }
            }
        });

        GridLayout layout = new GridLayout();
        layout.setColumns(2);
        layout.setRows(2);
        layout.setVgap(20);

        for (int i = 0; i < textArray.size(); i++) {
            textArray.get(i).setFont(new Font("Adobe Garamond Pro", Font.PLAIN, 30));
            textArray.get(i).setOpaque(false);
            textArray.get(i).setForeground(Color.WHITE);
            textArray.get(i).setBorder(null);
            if (i == 1 || i == 4) {
                textArray.get(i).setHorizontalAlignment(JLabel.LEFT);
            }
            else textArray.get(i).setHorizontalAlignment(JLabel.RIGHT);
        }

        // Set propriedade do painel de fundo
        titlesPanel.setBounds(-50, 150, 500, 100);
        titlesPanel.setBackground(Color.GREEN);
        titlesPanel.setOpaque(false);
        titlesPanel.setLayout(layout);
        titlesPanel.setVisible(true);
        titlesPanel.add(nameTitle);
        titlesPanel.add(nameInput);
        titlesPanel.add(classTitle);
        titlesPanel.add(classInput);

        // Add componentes ao objeto
        this.add(titlesPanel);
        this.add(saveButton);
        this.add(buttonImage);
    }
    public void updatePanel()
    {
        
    }
}