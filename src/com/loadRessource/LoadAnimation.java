package com.loadRessource;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class LoadAnimation {
    private static ArrayList<ImageIcon> waitingIconList;
    private static final int NBR_FRAME_WAITING_ANIMATION = 4;

    public LoadAnimation(){
        createWaitingIconFrame();
    }

    public void createWaitingIconFrame(){
        /* 
            Permet de mettre toutes les images de l'animation du livre
            avec les pages qui tourne dans une liste
        */
        waitingIconList = new ArrayList<>();
        ImageIcon img;
        for(int i=0; i<NBR_FRAME_WAITING_ANIMATION; i++){
            img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource("res/img/waitingAnimation/bookAnimation" + i + ".png")));

            waitingIconList.add(img);
        }
    }
    public static ArrayList<ImageIcon> getWaitingIconList(){
        return waitingIconList;
    }
    public static int getNbrFrameWaitingAnimation(){
        return NBR_FRAME_WAITING_ANIMATION;
    }
}
