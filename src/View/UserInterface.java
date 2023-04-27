package View;

import Model.Elixir;
import Model.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UserInterface {
    GamePannel gp;
    Graphics2D g2;
    Font arial;
    BufferedImage keyImage;
    BufferedImage elixirImage;
    double playTime; //creating game timer

    DecimalFormat decimalFormat = new DecimalFormat("#0.00"); //format the game time (DISPLAY 2 PLACES OF DECIMALS)

    public UserInterface(GamePannel gp) {
        this.gp = gp;
        arial = new Font("Arial", Font.PLAIN, 40);
        Key key = new Key();
        keyImage = key.image; //preparing key image to display the count of the keys

        Elixir elixir = new Elixir();
        elixirImage = elixir.image;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial);
        g2.setColor(Color.white);
         if(GamePannel.gameState == GamePannel.playState){
             //do playstate staff
         }

         if(GamePannel.gameState == GamePannel.pauseState){
//             drawPauseScreen(g2);
         }

         g2.drawImage(keyImage, gp.playerSize / 2, gp.playerSize/2, gp.playerSize, gp.playerSize, null);
         g2.drawString("x " + gp.player.keyAmount, 74, 65);
        g2.drawImage(elixirImage, 24, 80, gp.playerSize, gp.playerSize, null);
        g2.drawString("x " + gp.player.elixirAmount, 74, 110);

         //TIME
        playTime += (double) 1/60;
        g2.drawString("Time :" + decimalFormat.format(playTime), gp.playerSize*11,65 );
    }
    }
//
//    public void drawPauseScreen(Graphics2D g2){
//        g2.setFont(arial);
//        String text = "PAUSED";
//        int x = getX(text);
//        int y = gp.screenHeight/2;
//        g2.drawString(text, x, y);
//
//    }
//
//    public int getX(String text){
//        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//        int x = gp.screenWidth/2 - length/2;
//        return x;
//    }
//
//}
