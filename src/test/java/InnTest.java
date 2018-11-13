import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InnTest {

    private Inn innTest;
    private ArrayList<Item> items;

    @Before
    public void initializer() {
        this.innTest = new Inn();

        items = new ArrayList<>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
    }

    @Test
    public void innTest_should_not_be_empty() {
        assertTrue(this.innTest.getItems().size() != 0);
    }

    @Test
    public void getItems_should_return_right_item() {
        for (int i = 0; i < this.items.size(); i++) {
            assertEquals(this.innTest.getItems().get(i).getName(), this.items.get(i).getName());
            assertEquals(this.innTest.getItems().get(i).getSellIn(), this.items.get(i).getSellIn());
            assertEquals(this.innTest.getItems().get(i).getQuality(), this.items.get(i).getQuality());
        }
    }

    @Test
    public void sellIn_should_be_decremented() {
        int sellInOld;
        int sellInNow;

        for (int i = 0; i < this.innTest.getItems().size(); i++) {

            sellInOld = this.innTest.getItems().get(i).getSellIn();
            this.innTest.updateQuality();
            sellInNow = this.innTest.getItems().get(i).getSellIn();

            if (this.innTest.getItems().get(i).getName().equals("Sulfuras, Hand of Ragnaros")) {
                assertEquals(sellInNow, sellInOld);

            } else {
                assertEquals(sellInNow, sellInOld - 1);
            }
        }
    }

    @Test
    public void quality_should_not_be_negative() {
        for (int i = 0; i < this.innTest.getItems().size(); i++) {
            for (int qualityPoint = this.innTest.getItems().get(i).getQuality(); qualityPoint > -1; qualityPoint--) {
                this.innTest.updateQuality();
            }
            assertTrue(this.innTest.getItems().get(i).getQuality() >= 0);
        }
    }

    @Test
    public void quality_should_not_be_upper_than_50() {
        int qualityPoint;
        for (int i = 0; i < this.innTest.getItems().size(); i++) {
            qualityPoint = 50 - this.innTest.getItems().get(i).getQuality();

            for (int updatePoint = 0; updatePoint < qualityPoint + 1; updatePoint++) {
                this.innTest.updateQuality();
            }

            if (this.innTest.getItems().get(i).getName().equals("Sulfuras, Hand of Ragnaros")) {
                assertEquals(80, this.innTest.getItems().get(i).getQuality());

            } else {
                assertTrue(this.innTest.getItems().get(i).getQuality() <= 50);
            }
        }
    }
}