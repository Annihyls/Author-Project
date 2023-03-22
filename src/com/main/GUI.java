package com.main;

import com.gamesysteme.Personnage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class GUI extends JPanel{

    private static final long serialVersionUID = 83918983;
    private static final int LONGUEUR = 1000;
    private static final int LARGEUR = 700;

    public JFrame window;

    public JTextArea nameArea, messageArea;

    public JPanel titrePanel;
    public JLabel titreLabel;

    public static final int NBR_CHARA_POSITION_AND_LABEL = 2;
    public JPanel[] characterPanel = new JPanel[NBR_CHARA_POSITION_AND_LABEL];
    public JLabel[] characterLabel = new JLabel[NBR_CHARA_POSITION_AND_LABEL];

    public JPanel bgP;
    public JLabel bgL;

    public JPanel waitClickPnl;
    public JLabel waitClickLbl;

    public JButton newGame, resume, quit;

    public GUI(){
        createWindow();
        window.setVisible(true);
    }

    public void createWindow(){
        /*
            Crée une fenêtre de taille LONGUEUR et LARGEUR
            avec un fond d'écran noir
        */
        window = new JFrame("L'Auteur");
        window.setSize(LONGUEUR, LARGEUR);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.getContentPane().setBackground(Color.BLACK);
        window.getContentPane().setLayout(null);
        window.setLocationRelativeTo(null);
        window.getContentPane().addMouseListener(new ButtonMouseListener());
    }
    public void createMessageArea(){
        /*
            Crée une zone de texte dans la fenêtre
        */
        messageArea = new JTextArea();
        messageArea.setBounds(90, 450, 800, 200);
        messageArea.setBackground(Color.blue);
        messageArea.setForeground(Color.white);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("Helvetica", Font.PLAIN, 30));
        messageArea.addMouseListener(new ButtonMouseListener());

        window.add(messageArea);
    }
    public void removeMessageArea(){
        window.remove(messageArea);
    }
    public void createNameArea(){
        nameArea = new JTextArea();
        nameArea.setBounds(120,410,200,40);
        nameArea.setBackground(Color.MAGENTA);
        nameArea.setForeground(Color.white);
        nameArea.setEditable(false);
        nameArea.setLineWrap(true);
        nameArea.setWrapStyleWord(true);
        nameArea.setFont(new Font("Helvetica", Font.BOLD, 22));
        nameArea.addMouseListener(new ButtonMouseListener());

        window.add(nameArea);
    }
    public void removeNameArea(){
        window.remove(nameArea);
    }

    public void createMainMenu(){
        /* 
            Permet de créer le menu principal (avec trois boutons)
            qui permettent respectivement de créer une nouvelle
            partie (NE MARCHE PAS), continuer la partie et quitter
            le programme.
        */
        newGame = new JButton("Nouvelle Partie");
        newGame.setBounds(315, 400, 350, 40);
        newGame.setBackground(Color.MAGENTA);
        newGame.setForeground(Color.white);
        newGame.setFont(new Font("Helvetica", Font.BOLD, 20));
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // delegate to event handler method
                buttonActionPerformed(evt);
            }

            private void buttonActionPerformed(ActionEvent evt) {
                Auteur.setOptionMenu(0);
            }
        });

        resume = new JButton("Continuer");
        resume.setBounds(315, 470, 350, 40);
        resume.setBackground(Color.MAGENTA);
        resume.setForeground(Color.white);
        resume.setFont(new Font("Helvetica", Font.BOLD, 20));
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // delegate to event handler method
                buttonActionPerformed(evt);
            }

            private void buttonActionPerformed(ActionEvent evt) {
                Auteur.setOptionMenu(1);
            }
        });

        quit = new JButton("Quitter le jeu");
        quit.setBounds(315, 540, 350, 40);
        quit.setBackground(Color.MAGENTA);
        quit.setForeground(Color.white);
        quit.setFont(new Font("Helvetica", Font.BOLD, 20));
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // delegate to event handler method
                buttonActionPerformed(evt);
            }

            private void buttonActionPerformed(ActionEvent evt) {
                Auteur.setOptionMenu(2);
            }
        });


        titrePanel = new JPanel();
        titrePanel.setBounds(155, 80, 680, 200);
        titrePanel.setBackground(Color.RED);
        titrePanel.setLayout(null);

        titreLabel = new JLabel();
        titreLabel.setBounds(0, 0, 680, 200);
        ImageIcon titleIcon = new ImageIcon(Objects.requireNonNull(getClass()
                                .getClassLoader().getResource("res/img/mainMenu/title.png")));
        titreLabel.setIcon(titleIcon);
        titrePanel.add(titreLabel);

        window.add(newGame);
        window.add(resume);
        window.add(quit);
        window.add(titrePanel);
    }

    public void removeMainMenu(){
        window.remove(newGame);
        window.remove(resume);
        window.remove(quit);
        window.remove(titrePanel);
    }

    public void createTwoCharaField(Personnage p1, Personnage p2){
        /*
            Permet de créer deux champs dans la fenêtre pour afficher 
            deux personnages. 
        */
        characterPanel[0] = new JPanel();
        characterPanel[0].setBounds(650, 100, 300, 700);
        characterPanel[0].setBackground(Color.RED);
        characterPanel[0].setLayout(null);

        characterLabel[0] = new JLabel();
        characterLabel[0].setIcon(p1.getDialogViewBasicPose());
        characterLabel[0].setBounds(0,0,300,692);
        characterPanel[0].add(characterLabel[0]);

        characterPanel[1] = new JPanel();
        characterPanel[1].setBounds(50, 100, 300, 700);
        characterPanel[1].setBackground(Color.RED);
        characterPanel[1].setLayout(null);

        characterLabel[1] = new JLabel();
        characterLabel[1].setIcon(p2.getDialogViewBasicPose());
        characterLabel[1].setBounds(0,0,228,700);
        characterPanel[1].add(characterLabel[1]);

        /*commentez ci dessous pour voir les "hitbox" des personnages*/
        characterPanel[0].setOpaque(false);
        characterPanel[1].setOpaque(false);
        /*-----------------------------------------------------------*/
        p1.setDialogViewPositionLabel(0);
        p2.setDialogViewPositionLabel(1);

        window.add(characterPanel[0]);
        window.add(characterPanel[1]);
    }

        public void removeCharaField(){
        window.remove(characterPanel[0]);
        window.remove(characterPanel[1]);
    }


    public void createBackground(ImageIcon img){
        bgP = new JPanel();
        bgP.setBounds(0, 0, 1000, 600);
        bgP.setBackground(Color.YELLOW);
        bgP.setLayout(null);

        bgL = new JLabel();
        bgL.setIcon(img);
        bgL.setBounds(0,0,1000,600);
        bgP.add(bgL);

        window.add(bgP);
    }
    public void removeBackground(){
        window.remove(bgP);
    }

    public void createAnimationWaiting(){
        /*  
            Permet créer la zone où apparaîtra l'animation du livre
            avec les pages qui se tourne
        */
        waitClickPnl = new JPanel();
        waitClickPnl.setBounds(840,620,50,30);
        waitClickPnl.setBackground(Color.RED);
        waitClickPnl.setLayout(null);

        waitClickLbl = new JLabel();
        waitClickLbl.setBounds(0,0,50,30);
        waitClickPnl.add(waitClickLbl);
        waitClickPnl.setVisible(false);

        waitClickPnl.setOpaque(false);
        waitClickPnl.addMouseListener(new ButtonMouseListener());

        window.add(waitClickPnl);
    }
    public void removeAnimationWaiting(){
        window.remove(waitClickPnl);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paint(g);
    }

}
