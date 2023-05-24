package main;

import Controller.KeyHandler;
import View.GamePannel;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean enableLogging = false;

        System.out.print("Enable logging? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("yes")) {
            enableLogging = true;
        }

        if (enableLogging) {
            configureLogging(Level.INFO);
        } else {
            configureLogging(Level.OFF);
        }
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

    private static void configureLogging(Level level) {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(level);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(level);

        rootLogger.addHandler(consoleHandler);
    }
}
