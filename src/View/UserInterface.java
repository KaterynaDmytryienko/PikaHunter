package View;

import Model.Elixir;
import Model.Heart;
import Model.Item;
import Model.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.logging.Logger;

public class UserInterface {
    GamePannel gp;
    Font arial;
    BufferedImage keyImage;
    BufferedImage elixirImage;

    BufferedImage heart_full, heart_half, heart_blank;
    public int commandNum = 0;

    public int slotCol = 0;
    public int slotRow = 0;
    private static final Logger logger = Logger.getLogger(UserInterface.class.getName());

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
        heart_blank = heart.image3;;

    }

    /**
     * Method draws screen depending on a game state.
     * @param g2
     */
    public void draw(Graphics2D g2) {
        g2.setFont(arial);
        g2.setColor(Color.white);
        drawPlayerLife(g2);

        if(gp.gameState == gp.playerSize){
            drawPlayerLife(g2);
        }
        //TITLE STATE
        if(gp.gameState == gp.getTitleState()){
            drawTitleScreen(g2);
            logger.info("Drawing title screen.");
        }

        //CHARACTER STATE
        if(gp.gameState == gp.getCharacterState()){
            drawCharacterScreen(g2);
            drawInventory(g2);
        }
        //GAME OVER STATE
        if(gp.gameState == gp.getGameOverState()){
            drawGameOverScreen(g2);
            logger.info("Drawing game over screen.");
        }

        //PAUSE STATE
        if(gp.gameState == gp.getPauseState()){
            drawPauseScreen(g2);
            logger.info("Drawing pause screen.");
        }

        //DIALOGUE STATE
        if(gp.gameState == gp.getDialogueState()){
            drawDialogueScreen(g2);
            logger.info("Drawing dialogue screen.");
        }

    }

    /**
     * Method draws screen with messages to a player during the game
     * @param g2
     */
    public void drawDialogueScreen(Graphics2D g2){
        //WINDOW
        int x = gp.playerSize*2;
        int y = gp.playerSize/2;
        int width = gp.screenWidth - (gp.playerSize*4);
        int height = gp.playerSize*5;
        drawWindow(x, y, width, height, g2);
        String text = "";
        text = "Your game is saved!";
        x = getXForCenteredText(text, g2);
        g2.setColor(Color.BLACK);
        y = gp.playerSize*4;
        g2.drawString(text, x, y);
        //MAIN TEXT
        g2.setColor(Color.WHITE);
        g2.drawString(text, x -4, y-1);

    }

    /**
     * Method draws title screen in the beginning of a game with different options for player to choose.
     * @param g2
     */
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
        g2.drawImage(gp.player.front, x, y, gp.playerSize*2, gp.playerSize*2, null);

        //MENU BAR
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = 250;
        y += gp.playerSize * 3;

        g2.drawString(text, x, y);
        if(this.commandNum == 0) {
            g2.drawString(">", x - gp.playerSize, y);
        }

        text = "LOAD GAME";
        x = 250;
        y += gp.playerSize;

        g2.drawString(text, x, y);
        if(this.commandNum == 1){
            g2.drawString(">", x - gp.playerSize, y);
        }

        text = "QUIT GAME";
        x = 250;
        y += gp.playerSize;

        g2.drawString(text, x, y);
        if(this.commandNum == 2){
            g2.drawString(">", x - gp.playerSize, y);
        }
    }

    /**
     * Method draws player`s life depending on his current state.
     * @param g2
     */
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

    /**
     * Method draws screen with current information of player`s states.
     * @param g2
     */
    public void drawCharacterScreen(Graphics2D g2){
        // CREATE A FRAME
        final int frameX = gp.playerSize*2;
        final int frameY = gp.playerSize;
        final int frameWidth = gp.playerSize*5;
        final int frameHeight = gp.playerSize*10;
        drawWindow(frameX, frameY, frameWidth, frameHeight, g2);

        //CREATE A TEXT
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.playerSize;
        final int lineHeight = 40;

        //NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;

        g2.drawString("Life", textX, textY);
        textY += lineHeight;

        g2.drawString("Strength", textX, textY);
        textY += lineHeight;

        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;

        g2.drawString("Attack", textX, textY);
        textY += lineHeight;

        g2.drawString("Defence", textX, textY);
        textY += lineHeight;

        g2.drawString("Weapon", textX, textY);
        textY += lineHeight;

        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth) - 30;
         //Reset textY
        textY = frameY + gp.playerSize;
        String value;

        //GET LEVEL
        value = String.valueOf(gp.player.getLevel());
        textX = getXForAlignToRightText(value, tailX, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        //GET LIFE
        value = String.valueOf(gp.player.getLife() + "/" + gp.player.getMaxLife());
        textX = getXForAlignToRightText(value, tailX, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        //GET STRENGTH
        value = String.valueOf(gp.player.getStrength());
        textX = getXForAlignToRightText(value, tailX, g2);
        g2.drawString(value, textX, textY);  textY += lineHeight;


        //GET DEXTERITY
        value = String.valueOf(gp.player.getDexterity());
        textX = getXForAlignToRightText(value, tailX, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        //GET ATTACK
        value = String.valueOf(gp.player.getAttack());
        textX = getXForAlignToRightText(value, tailX, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        //GET DEFENSE
        value = String.valueOf(gp.player.getDefenceValue());
        textX = getXForAlignToRightText(value, tailX, g2);
        g2.drawString(value, textX, textY);
        textY += lineHeight-10;

        //DISPLAY IMAGE
        g2.drawImage(gp.player.getCurrentWeapon().back, tailX-gp.playerSize, textY-15, gp.playerSize, gp.playerSize, null);
        textY += gp.playerSize;
        g2.drawImage(gp.player.getCurrentShield().back, textX-gp.playerSize, textY-15, gp.playerSize, gp.playerSize,  null);
    }

    /**
     * Method draws inventory screen.
     * @param g2
     */
    public void drawInventory(Graphics2D g2){
        //FRAME
        int frameX = gp.playerSize * 9;
        int frameY = gp.playerSize;
        int frameWidth = gp.playerSize * 6;
        int frameHeight = gp.playerSize * 5;
        drawWindow(frameX, frameY, frameWidth, frameHeight, g2);

        //SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;

        int slotX = slotXStart;
        int slotY = slotYStart;

        //DRAW ITEMS
        for(int i = 0; i < gp.player.inventory.size(); i++){

            //EQUIP CURSOR
            if(gp.player.inventory.get(i)==gp.player.getCurrentWeapon()||
            gp.player.inventory.get(i) == gp.player.getCurrentShield()){
                g2.setColor(new Color(240, 190, 90)); //highlight current equipment
                g2.fillRoundRect(slotX, slotY, gp.playerSize, gp.playerSize, 10, 10);
            }




            g2.drawImage(gp.player.inventory.get(i).back, slotX, slotY, gp.playerSize, gp.playerSize, null);
            slotX += gp.playerSize;
            if(i == 4 || i == 9 || i == 14){ // when we are on the end position of the raw, we need to move to next row
                slotX = slotXStart;
                slotY += gp.playerSize;
            }
        }

        //DRAW CURSOR
        int cursorX = slotXStart + (gp.playerSize * slotCol);
        int cursorY = slotYStart + (gp.playerSize * slotRow);
        int cursorWidth = gp.playerSize;
        int cursorHeight = gp.playerSize;

        //DRAW CURSOR
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3)); // setting border thickness of a cursor
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        //DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.playerSize * 3;


        //DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.playerSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        //GET ITEM INDEX
        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            drawWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight, g2);
            for(String line : gp.player.inventory.get(itemIndex).getDescription().split("\n")){
                g2.drawString(line, textX, textY); //drawing description for current item
                textY += 32;
            }
        }
    }

    /**
     * Method draws game over screen.
     * @param g2
     */
    public void drawGameOverScreen(Graphics2D g2){
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        text = "GAME OVER";

        //SHADOW
        g2.setColor(Color.BLACK);
        x = getXForCenteredText(text, g2);
        y = gp.playerSize*4;
        g2.drawString(text, x, y);
        //MAIN TEXT
        g2.setColor(Color.WHITE);
        g2.drawString(text, x -4, y-4);

        //RETRY
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenteredText(text, g2);
        y += gp.playerSize * 4;
        g2.drawString(text, x, y);

        if(getCommandNum() == 0){
           g2.drawString(">", x-40, y);
        }

        //BACK TO THE TITLE SCREEN
        text = "Quit";
        x = getXForCenteredText(text, g2);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum ==1 ){
            g2.drawString(">", x-40, y);
        }
    }

    /**
     * Method draws pause screen.
     * @param g2
     */
    public void drawPauseScreen(Graphics2D g2){
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 110f));
        text = "PAUSED";

        //SHADOW
        g2.setColor(Color.BLACK);
        x = getXForCenteredText(text, g2);
        y =  gp.playerSize*6;
        g2.drawString(text, x, y);
        //MAIN TEXT
        g2.setColor(Color.WHITE);
        g2.drawString(text, x -4, y-4);
    }

    /**
     * Method gets current item index in the inventory.
     * @return int
     */
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }

    /**
     * Gets current number of a command.
     * @return
     */
    public int getCommandNum() {
        return commandNum;
    }

    /**
     * Sets current number of command.
     * @param commandNum
     */
    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }

    /**
     * Method draws window during the game.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param g2
     */
    public void drawWindow(int x, int y, int width, int height, Graphics2D g2){
        Color c = new Color(0, 0, 0, 170);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    /**
     * Method gets X position to align text to the right on a window.
     * @param text
     * @param tailX
     * @param g2
     * @return int
     */
    public int getXForAlignToRightText(String text, int tailX, Graphics2D g2){
          int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
          int x = tailX - lenght; //starting point for drawing the text
        return x;
    }

    /**
     * Method gets X position to center text on the window.
     * @param text
     * @param g2
     * @return
     */
    public int getXForCenteredText(String text, Graphics2D g2){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

}

