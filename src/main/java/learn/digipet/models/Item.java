package learn.digipet.models;

import java.math.BigDecimal;

public class Item {

    private int itemId;
    private String name;
    private String description;
<<<<<<< HEAD
    private boolean forBattle;
    private BigDecimal price;

    public int getItemId() {
        return itemId;
    }
=======
>>>>>>> caa1d5d37ca8f119735de3fa901b4b585dd75b0b

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

<<<<<<< HEAD
    public String getName() {
        return name;
    }

=======
>>>>>>> caa1d5d37ca8f119735de3fa901b4b585dd75b0b
    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
    public String getDescription() {
        return description;
    }

=======
>>>>>>> caa1d5d37ca8f119735de3fa901b4b585dd75b0b
    public void setDescription(String description) {
        this.description = description;
    }

<<<<<<< HEAD
    public boolean isForBattle() {
        return forBattle;
    }

    public void setForBattle(boolean forBattle) {
        this.forBattle = forBattle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

=======
    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
>>>>>>> caa1d5d37ca8f119735de3fa901b4b585dd75b0b
}
