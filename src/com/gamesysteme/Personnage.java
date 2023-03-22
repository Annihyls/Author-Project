package com.gamesysteme;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Personnage {
    private static final int DIALOG_VIEW_BASIC_POSE = 0;

    private String nom;
    private int age;
    private boolean vivant;
    private int dialogViewPositionLabel;
    private static final int TAILLE_CHARATAB = 20;
    private ArrayList<ImageIcon> dialogView;

    public Personnage(String nom, int age, boolean vivant){
        this.nom = nom;
        this.age = age;
        this.vivant = vivant;
        dialogView = new ArrayList<>();
    }

    public String getNom() {return nom;}
    public int getAge() {return age;}
    public boolean getVivant() {return vivant;}
    public ImageIcon getDialogViewBasicPose() {return dialogView.get(DIALOG_VIEW_BASIC_POSE);}

    public ImageIcon getDialogView(int i){
        return dialogView.get(i);
    }
    public int getMaxFrameAnimation(){
        return dialogView.size();
    }
    public int getDialogViewPositionLabel(){return this.dialogViewPositionLabel;}
    public void setDialogViewPositionLabel(int i){this.dialogViewPositionLabel = i;}

    public static Personnage[] initPersonnage(){
        /*
            Initialise le tableau de tous les personnages
            du jeu 
        */
        Personnage[] charaTab = new Personnage[TAILLE_CHARATAB];
        charaTab[0] = new Personnage("L'Auteur", -1, true);
        charaTab[0].dialogView.add(new ImageIcon(Objects.requireNonNull(Personnage
                        .class.getClassLoader()
                        .getResource("res/img/character/Auteur02.png"))));
        charaTab[0].dialogView.add(new ImageIcon(Objects.requireNonNull(Personnage
                .class.getClassLoader()
                .getResource("res/img/character/Auteur01.png"))));

        charaTab[1] = new Personnage("Ludivine", 17, true);
        charaTab[1].dialogView.add(new ImageIcon(Objects.requireNonNull(Personnage
                .class.getClassLoader()
                .getResource("res/img/character/Ludivine.png"))));
        charaTab[1].dialogView.add(new ImageIcon(Objects.requireNonNull(Personnage
                .class.getClassLoader()
                .getResource("res/img/character/Ludivine02.png"))));


        return charaTab;
    }

}
