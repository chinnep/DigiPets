package learn.digipet.models;

public class Item {

    private int itemId;
    private String name;
    private String description;
    private boolean forBattle;
    private int price;

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

}