package View;

import Model.Elixir;
import Model.Heart;
import Model.Item;
import Model.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UserInterface {
    GamePannel gp;
    Font arial;
    BufferedImage keyImage;
    BufferedImage elixirImage;

    BufferedImage heart_full, heart_half, heart_blank;
    double playTime; //creating game timer
    public int commandNum = 0;

    DecimalFormat decimalFormat = new DecimalFormat("#0.00"); //format the game time (DISPLAY 2 PLACES OF DECIMALS)

    public UserInterface(GamePannel gp) {
        this.gp = gp;
        arial = new Font("Arial", Font.PLAIN, 40);
        Key key = new Key();
        keyImage = key.image; //preparing key image to display the count of the keys

        Elixir elixir = new Elixir();
        elixirImage = elixir.image;

        //CREATE HEART OBJECT
        Item heart = new Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial);
        g2.setColor(Color.white);

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen(g2);
        }

         drawPlayerLife(g2);
         g2.drawImage(keyImage, gp.playerSize / 2, gp.playerSize/2, gp.playerSize, gp.playerSize, null);
         g2.drawString("x " + gp.player.keyAmount, 74, 65);
        g2.drawImage(elixirImage, 24, 80, gp.playerSize, gp.playerSize, null);
        g2.drawString("x " + gp.player.elixirAmount, 74, 110);

//         //TIME
//        playTime += (double) 1/60;
//        g2.drawString("Time :" + decimalFormat.format(playTime), gp.playerSize*11,65 );
    }

    public void drawTitleScreen(Graphics2D g2) {
        g2.setColor(new Color(70, 120, 80));

        //MENU BACKGROUND COLOR
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 86));
        String text = "PikaHunter";
        int x = 150;
        int y = gp.playerSize * 3;

        //SHADOW for 3d effect of the text
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);

        //TEXT COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //DISPLAYING PIKA IMAGE
        x = gp.screenWidth/2 - (gp.playerSize*2)/2;
        y += gp.playerSize*2;
        g2.drawImage(gp.player.left, x, y, gp.playerSize*2, gp.playerSize*2, null);

        //MENU BAR
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = 250;
        y += gp.playerSize * 3;
//        System.out.println("new game. x: " + x + ", y: " + y + ". num: " + this.commandNum);
        g2.drawString(text, x, y);
        if(this.commandNum == 0) {
            g2.drawString(">", x - gp.playerSize, y);
        }

        text = "LOAD GAME";
        x = 250;
        y += gp.playerSize;
//        System.out.println("load game. x: " + x + ", y: " + y + ". num: " + this.commandNum);
        g2.drawString(text, x, y);
        if(this.commandNum == 1){
            g2.drawString(">", x - gp.playerSize, y);
        }

        text = "QUIT GAME";
        x = 250;
        y += gp.playerSize;
//        System.out.println("quit game. x: " + x + ", y: " + y + ". num: " + this.commandNum);
        g2.drawString(text, x, y);
        if(this.commandNum == 2){
            g2.drawString(">", x - gp.playerSize, y);
        }


    }

    public void drawPlayerLife(Graphics2D g2){
        int x = gp.playerSize* 12;
        int y = gp.playerSize;

        int i = 0;

        //DRAW MAX LIFE
        while (i < gp.player.getMaxLife()/2){
            g2.drawImage(heart_blank, x, y, gp.playerSize, gp.playerSize, null);
            i++;
            x += gp.playerSize + 5;
        }
        //RESET
         x = gp.playerSize* 12;
         y = gp.playerSize;
         i = 0;

         //DRAW CURRENT LIFE
        while (i < gp.player.getLife()) {
            g2.drawImage(heart_half, x, y, gp.playerSize, gp.playerSize, null);
            i++;
            if (i < gp.player.getLife()) {
                g2.drawImage(heart_full, x, y, gp.playerSize, gp.playerSize, null);
            }
            i++;
            x += gp.playerSize + 5;
        }

    }

    public int getCommandNum() {
        return commandNum;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }
}
