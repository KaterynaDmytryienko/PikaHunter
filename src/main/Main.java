package main;

import Controller.KeyHandler;
import View.GamePannel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JHunter");

        KeyHandler keyHandler = new KeyHandler();
        
        window.addKeyListener(keyHandler);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        GamePannel gamepanel = new GamePannel(keyHandler);
        window.add(gamepanel);
        window.pack();
        gamepanel.startGameThread();
    }
}