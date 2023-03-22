package com.main;

import com.gamesysteme.Personnage;
import com.loadRessource.LoadAnimation;

public class GameLoop implements Runnable {

    private long nextStatTime;
    private int fps, ups;

    /*----------------------------------*/
    /*  paramètre pour les animations   */
    /*----------------------------------*/
    public boolean stopAnimationDialogView = true;
    public boolean stopAnimationWaiting = true;
    public Personnage p;
    public int labelPosition;
    public int frameActuelle, frameWaitingAnimation;
    public int nombreMaxFrameAnimation;



    @Override
    public void run() {
        boolean running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();

        while(running){

            currentTime = System.currentTimeMillis();
            double lastRenderTimeSecond = (currentTime - lastUpdate)/ 10000d;
            accumulator += lastRenderTimeSecond;
            lastUpdate = currentTime;

            double updateRate = 1.5d / 120.0d;
            while(accumulator > updateRate){
                update();

                animationDialogView();
                animationWaiting();

                accumulator -= updateRate;
            }

            Auteur.getGUI().window.repaint();

            render();
            //printStats();
        }
    }

    private void printStats(){
        if(System.currentTimeMillis() > nextStatTime){
            System.out.printf("FPS: %d, UPS: %d%n", fps, ups);
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void render() {fps++;}

    private void update() {ups++;}

    private void animationDialogView(){
        /*
            Permet de jouer une animation quand le personnage
            parle 
        */
        if(p != null) {
            if (!stopAnimationDialogView) {
                Auteur.getGUI().characterLabel[labelPosition].setIcon(p.getDialogView(frameActuelle));
                frameActuelle++;
                if (frameActuelle == nombreMaxFrameAnimation) {
                    frameActuelle = 0;
                }
            } else {
                Auteur.getGUI().characterLabel[labelPosition].setIcon(p.getDialogViewBasicPose());
            }
        }
    }

    private void animationWaiting(){
        /*  
            Permet de jouer l'animation du livre avec les pages
            qui se tourne en bas à droite de l'écran
        */
        if(Auteur.getGUI().waitClickPnl != null) {
            if (!stopAnimationWaiting) {
                Auteur.getGUI().waitClickPnl.setVisible(true);
                Auteur.getGUI().waitClickLbl.setIcon(LoadAnimation
                        .getWaitingIconList()
                        .get(frameWaitingAnimation));
                frameWaitingAnimation++;
                if (frameWaitingAnimation == LoadAnimation.getNbrFrameWaitingAnimation()) {
                    frameWaitingAnimation = 0;
                }
            } else{
                Auteur.getGUI().waitClickPnl.setVisible(false);
            }
        }
    }
}