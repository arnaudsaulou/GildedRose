import java.util.ArrayList;
import java.util.List;

public class Inn {
    private List<Item> items;

    public Inn() {
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
    }

    public List<Item> getItems() {
        return items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (itemIsNot_AgedBrie_Or_BackstagePasses(item)) {
                if (itemQualityIsUpperThan0(item)) {
                    if (itemIsNot_SulfurasHandOfRagnaros(item)) {
                        decrementByOneItemQuality(item);
                    }
                }
            } else {
                if (itemQualityIsLowerThan50(item)) {
                    incrementByOneItemQuality(item);

                    if (itemIsBackstagePasses(item)) {

                        if (consumptionDateIn10Days(item)) {
                            if (itemQualityIsLowerThan50(item)) {
                                incrementByOneItemQuality(item);
                            }
                        }

                        if (consumptionDateIn5Days(item)) {
                            if (itemQualityIsLowerThan50(item)) {
                                incrementByOneItemQuality(item);
                            }
                        }
                    }
                }
            }

            if (itemIsNot_SulfurasHandOfRagnaros(item)) {
                oneDayHasPassed(item);
            }

            if (consumptionDateNotPassedOrNotToday(item)) {
                if (itemIsNotAgedBrie(item)) {
                    if (!itemIsBackstagePasses(item)) {
                        if (itemQualityIsUpperThan0(item)) {
                            if (itemIsNot_SulfurasHandOfRagnaros(item)) {
                                decrementByOneItemQuality(item);
                            }
                        }
                    } else {
                        item.setQuality(0);
                    }
                } else {
                    if (itemQualityIsLowerThan50(item)) {
                        incrementByOneItemQuality(item);
                    }
                }
            }
        }

    }

    private boolean consumptionDateIn5Days(Item item) {
        return item.getSellIn() < 6;
    }

    private boolean consumptionDateIn10Days(Item item) {
        return item.getSellIn() < 11;
    }


    //Name identification

    private boolean itemIsNotAgedBrie(Item item) {
        return !item.getName().equals("Aged Brie");
    }

    private boolean itemIsBackstagePasses(Item item) {
        return item.getName().equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean itemIsNot_SulfurasHandOfRagnaros(Item item) {
        return !item.getName().equals("Sulfuras, Hand of Ragnaros");
    }

    //Item type's identification

    private boolean itemIsNot_AgedBrie_Or_BackstagePasses(Item item) {
        return itemIsNotAgedBrie(item) && !itemIsBackstagePasses(item);
    }

    //SellIn managment

    private void oneDayHasPassed(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private boolean consumptionDateNotPassedOrNotToday(Item item) {
        return item.getSellIn() < 0;
    }

    //Quality management

    private void incrementByOneItemQuality(Item item) {
        item.setQuality(item.getQuality() + 1);
    }

    private void decrementByOneItemQuality(Item item) {
        item.setQuality(item.getQuality() - 1);
    }

    private boolean itemQualityIsLowerThan50(Item item) {
        return item.getQuality() < 50;
    }

    private boolean itemQualityIsUpperThan0(Item item) {
        return item.getQuality() > 0;
    }

}