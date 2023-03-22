package com.loadRessource;

import java.io.*;
import java.util.LinkedList;
import com.gamesysteme.Personnage;
import com.main.Auteur;
import com.main.ButtonMouseListener;

import java.util.StringTokenizer;


public class SceneReader {
    private LinkedList<String> listString;
    private FileReader fr;
    private BufferedReader br;

    private String name;
    private String dialog;

    public static volatile boolean screenClicked = false;


    public SceneReader(File f) throws FileNotFoundException {
        fr = new FileReader(f);
        listString = new LinkedList<>();
        br = new BufferedReader(fr);
    }

    public LinkedList<String> listOfDialog() throws IOException {
        /* 
            Met dans une liste chaque lignes du fichier
        */
        int i = 0;
        String line;
        while ((line = br.readLine()) != null) {
            listString.add(i, line);
            i++;
        }
        br.close();
        fr.close();
        return listString;
    }

    public void loadNameAndDialog(LinkedList<String> listOfString) throws InterruptedException {
        /* 
            Permet de récupérer dans les attributs name et dialog le nom
            et le dialog du fichier text (le nom est crypté par l'index
            du personnage)
        */
        for (int i = 0; i < listOfString.size(); i++) {
            String line = listOfString.get(i);
            String lineTest = "";
            StringTokenizer st = new StringTokenizer(line, ":");
            Personnage p = Auteur.getCharaTab()[Integer.parseInt(st.nextToken())];
            name = p.getNom();
            dialog = "";
            line = st.nextToken();
            /*trouve le nom du perso a partir de l'index*/
            StringTokenizer st2 = new StringTokenizer(line, "£");
            while(st2.hasMoreTokens()){
                lineTest = st2.nextToken();
                try {
                    dialog = dialog + Auteur.getCharaTab()[Integer.parseInt(lineTest)].getNom();
                } catch (NumberFormatException e) {
                    dialog = dialog + lineTest;
                }
            }
            /*-----------------------------------------*/
            printScene(p);
        }
    }
    public void printScene(Personnage p) throws InterruptedException {
        /* 
            Affiche dans la fenêtre le texte lettre par lettre
        */
        char c;
        Auteur.getGUI().nameArea.setText(name);
        Auteur.getGUI().messageArea.setText("");

        Auteur.getGameLoop().p = p;
        Auteur.getGameLoop().labelPosition = p.getDialogViewPositionLabel();
        Auteur.getGameLoop().nombreMaxFrameAnimation = p.getMaxFrameAnimation();
        Auteur.getGameLoop().stopAnimationDialogView = false;

        for (int i = 0; i < dialog.length(); i++) {
            c = dialog.charAt(i);
            if(dialog.charAt(i) == '$'){
                Auteur.getGUI().messageArea.append(String.valueOf('\n'));
            } else {
               Auteur.getGUI().messageArea.append(String.valueOf(c));
            }
            Thread.sleep(30);
        }
        Auteur.getGameLoop().stopAnimationDialogView = true;


        Auteur.getGameLoop().stopAnimationWaiting = false;
        screenClicked = false;
        while (!screenClicked) {
            Thread.onSpinWait();
        }

        screenClicked = false;

        Auteur.getGameLoop().stopAnimationWaiting = true;

    }
}
