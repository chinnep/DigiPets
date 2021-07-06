package learn.digipet.models;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Item {

    private int itemId;
    @NotBlank
    private String name;
    private String description;
    private boolean forBattle;
    private boolean forFood;
    private boolean forWater;
    private boolean forCare;
    private boolean forHealth;
    private int price;
    private String imgUrl;
    private int quantity;

    public Item() {
    }

    public Item(int itemId, String name, String description, boolean forBattle, boolean forFood, boolean forWater,
                boolean forCare, boolean forHealth, int price, String imgUrl) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.forBattle = forBattle;
        this.forFood = forFood;
        this.forWater = forWater;
        this.forCare = forCare;
        this.forHealth = forHealth;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public boolean isForFood() {
        return forFood;
    }

    public void setForFood(boolean forFood) {
        this.forFood = forFood;
    }

    public boolean isForWater() {
        return forWater;
    }

    public void setForWater(boolean forWater) {
        this.forWater = forWater;
    }

    public boolean isForCare() {
        return forCare;
    }

    public void setForCare(boolean forCare) {
        this.forCare = forCare;
    }

    public boolean isForHealth() {
        return forHealth;
    }

    public void setForHealth(boolean forHealth) {
        this.forHealth = forHealth;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
