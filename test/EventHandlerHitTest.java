import Controller.EventHandler;
import Controller.KeyHandler;
import Model.Player;
import View.GamePannel;
import org.junit.jupiter.api.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class EventHandlerHitTest {
    EventHandlerHitTest eventHandlerHitTest;
    GamePannel gp;
    KeyHandler kh;
    EventHandler eventHandler;

    @BeforeEach
    public void setUp() throws IOException {
        gp = new GamePannel();
        kh = new KeyHandler(gp);
        eventHandler = new EventHandler(gp);
        eventHandlerHitTest = new EventHandlerHitTest();
        eventHandlerHitTest.gp = new GamePannel();
        eventHandlerHitTest.gp.player = new Player(gp, kh);
        eventHandlerHitTest.gp.player.solidArea = new Rectangle(10, 10);
        eventHandlerHitTest.gp.player.solidAreaDefaultX = 0;
        eventHandlerHitTest.gp.player.solidAreaDefaultY = 0;
        eventHandlerHitTest.gp.player.worldx = 0;
        eventHandlerHitTest.gp.player.worldy = 0;

        eventHandlerHitTest.eventHandler = new EventHandler(eventHandlerHitTest.gp);

        eventHandlerHitTest.eventHandler.eventRect = new Rectangle();
        eventHandlerHitTest.eventHandler.eventRectDefaultX = 0;
        eventHandlerHitTest.eventHandler.eventRectDefaultY = 0;
        eventHandlerHitTest.gp.playerSize = 10;
    }

    @Test
    public void hitTest() {
        // Arrange
        int eventCol = 1;
        int eventRow = 1;
        String requiredDirection = "Right";

        eventHandlerHitTest.gp.player.solidArea.setBounds(0, 0, 20, 20);
        eventHandlerHitTest.eventHandler.eventRect.setBounds(0, 0, 20, 20);

        // Act
        boolean hit = eventHandlerHitTest.eventHandler.hit(eventCol, eventRow, requiredDirection);

        // Assert
        assertTrue(hit, "The player should hit the eventRect");
    }
}