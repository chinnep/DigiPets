package learn.digipet.models;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Item {

    private int itemId;
    @NotBlank
    private String name;
    private String description;
    private boolean forBattle;
    private int price;

    public Item() {
    }
    public Item(int itemId, String name, String description, boolean forBattle, int price) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.forBattle = forBattle;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isForBattle() {
        return forBattle;
    }

    public void setForBattle(boolean forBattle) {
        this.forBattle = forBattle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemId == item.itemId && forBattle == item.forBattle && price == item.price && name.equals(item.name) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, description, forBattle, price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", forBattle=" + forBattle +
                ", price=" + price +
                '}';
    }
}
