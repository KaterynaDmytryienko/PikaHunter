package main;

import Controller.KeyHandler;
import View.GamePannel;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PikaHunter");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        GamePannel gamepanel = new GamePannel();
        window.addKeyListener(gamepanel.getKeyHandler());
        window.add(gamepanel);
        window.pack();
        gamepanel.setupGame();
        gamepanel.startGameThread();

    }
}