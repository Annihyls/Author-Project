package com.main;

import com.gamesysteme.Personnage;
import com.loadRessource.LoadAnimation;
import com.loadRessource.SceneReader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Auteur {


    private static Personnage[] charaTab;
    private static final GUI ui = new GUI();
    private static final GameLoop gameLoop = new GameLoop();
    private static int optionMenu;


    public static GameLoop getGameLoop() { return gameLoop; }
    public static Personnage[] getCharaTab() {
        return charaTab;
    }
    public static GUI getGUI() {
        return ui;
    }
    public static void setOptionMenu(int i){ optionMenu = i; }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(gameLoop).start();
        initGame();
        ui.createMainMenu();
        while(true) { //boucle en attente d'un action Listenener
            if (optionMenu == 0) {
                //definir un new game
            } else if (optionMenu == 1) {
                resumeGame();
            } else if (optionMenu == 2) {
                System.exit(0);
            }
        }
    }

    public static void resumeGame() throws IOException, InterruptedException {
        ui.removeMainMenu();

        /*LOAD ANIMATIONS*/
        new LoadAnimation();

        ImageIcon img = new ImageIcon(Objects
                .requireNonNull(Auteur.class
                        .getClassLoader()
                        .getResource("res/img/background/Environnement7.jpg")));

        //  Ci-dessous les éléments nécessaire pour le "visual novel"
        ui.createAnimationWaiting();
        ui.createNameArea();
        ui.createMessageArea();
        ui.createTwoCharaField(charaTab[0], charaTab[1]);
        ui.createBackground(img);

        //  Le script se lit
        readScene(new File("build/res/scene/scene01.txt"));
        
        //une fois le script lu, on efface les éléments de la fenêtre
        ui.removeMessageArea();
        ui.removeNameArea();
        ui.removeCharaField();
        ui.removeBackground();

        //  On quitte le programme
        System.exit(0);
    }

    private static void readScene(File f) throws IOException, InterruptedException {
        /*
            Permet d'afficher à l'écran le texte écrit dans le 
            fichier texte passé en paramètre 
        */
        SceneReader sr = new SceneReader(f);
        LinkedList<String> listOfString = sr.listOfDialog();
        sr.loadNameAndDialog(listOfString);
    }

    public static void initGame(){
        /*initialisation des personnages*/
        charaTab = Personnage.initPersonnage();
    }
}
