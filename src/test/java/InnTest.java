import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InnTest {

    @Test
    public void should_list_items() {
        Inn innTest = new Inn();
        assertThat(innTest.getItems()).extracting("name").containsExactly(
                "+5 Dexterity Vest",
                "Aged Brie",
                "Elixir of the Mongoose",
                "Sulfuras, Hand of Ragnaros",
                "Backstage passes to a TAFKAL80ETC concert",
                "Conjured Mana Cake");
        assertThat(innTest.getItems()).extracting("sellIn").containsExactly(10, 2, 5, 0, 15, 3);
        assertThat(innTest.getItems()).extracting("quality").containsExactly(20, 0, 7, 80, 20, 6);
    }

    @Test
    public void should_update_quality() {
        Inn innTest = new Inn();
        innTest.updateQuality();

        assertThat(innTest.getItems()).extracting("name").containsExactly(
                "+5 Dexterity Vest",
                "Aged Brie",
                "Elixir of the Mongoose",
                "Sulfuras, Hand of Ragnaros",
                "Backstage passes to a TAFKAL80ETC concert",
                "Conjured Mana Cake");
        assertThat(innTest.getItems()).extracting("sellIn").containsExactly(9, 1, 4, 0, 14, 2);
        assertThat(innTest.getItems()).extracting("quality").containsExactly(19, 1, 6, 80, 21, 5);
    }

    @Test
    public void should_update_quality_twice() {
        Inn innTest = new Inn();
        innTest.updateQuality();
        innTest.updateQuality();

        assertThat(innTest.getItems()).extracting("sellIn").containsExactly(8, 0, 3, 0, 13, 1);
        assertThat(innTest.getItems()).extracting("quality").containsExactly(18, 2, 5, 80, 22, 4);
    }

    @Test
    public void should_update_quality_100_times() {
        Inn innTest = new Inn();

        for (int day = 0; day < 100; day++) {
            innTest.updateQuality();
        }

        assertThat(innTest.getItems()).extracting("sellIn").containsExactly(-90, -98, -95, 0, -85, -97);
        assertThat(innTest.getItems()).extracting("quality").containsExactly(0, 50, 0, 80, 0, 0);
    }
}