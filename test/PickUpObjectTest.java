import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.util.ArrayList;

import Model.*;
import View.GamePannel;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    public void pickUpObjectTestKey() throws IOException {

        GamePannel gp = new GamePannel();
        Item keyItem = new Key();
        gp.item = new Item[]{keyItem};

        Player player = new Player(gp, null);
        player.inventory = new ArrayList<>();
        player.setKeyAmount(0);

        // Act
        player.pickUpObject(0);

        // Assert
        assertEquals(1, player.getKeyAmount());
        assertEquals(1, player.inventory.size());
        assertEquals(keyItem, player.inventory.get(0));
    }

    @Test
    public void pickUpObjectTestAxe() throws IOException {
        GamePannel gp = new GamePannel();
        Item axe = new Axe();
        gp.item = new Item[]{axe};

        Player player = new Player(gp, null);
        player.inventory = new ArrayList<>();

        // Act
        player.pickUpObject(0);

        // Assert
        assertEquals(1, player.inventory.size());
        assertEquals(axe, player.inventory.get(0));
        assertNull(gp.item[0]);
    }

    @Test
    public void pickUpObjectFullInventory() throws IOException {
        GamePannel gp = new GamePannel();
        Item key = new Key();
        gp.item = new Item[]{key};

        Player player = new Player(gp, null);
        player.inventory = new ArrayList<>();
        player.inventorySize = 1;
        player.inventory.add(new Axe());

        // Act
        player.pickUpObject(0);

        // Assert
        assertEquals(0, player.getKeyAmount());
        assertEquals(1, player.inventory.size());
    }

    @Test
    public void PickUpObjectTestIncreaseElixirAmountWhenOpenTreasureChest() throws IOException {
        GamePannel gp = new GamePannel();
        Item treasureChest = new TreasureChest();
        gp.item = new Item[]{treasureChest};

        Player player = new Player(gp, null);
        player.inventory = new ArrayList<>();
        player.setKeyAmount(1);
        player.setElixirAmount(0);

        player.pickUpObject(0);

        assertEquals(0, player.getKeyAmount());
        assertEquals(0, player.inventory.size());
        assertEquals(1, player.getElixirAmount());
    }


    @Test
    public void damageMonsterTest() throws IOException {
        // Arrange
        GamePannel gp = new GamePannel();
        Entity monster = new Enemy(gp);
        gp.monster = new Entity[]{monster};

        Player player = new Player(gp, null);
        player.getCurrentWeapon().setType(3);
        monster.setLife(3);

        // Act
        player.damageMonster(0);

        // Assert
        assertEquals(2, monster.getLife());
        assertTrue(monster.isInvincible());
    }
}