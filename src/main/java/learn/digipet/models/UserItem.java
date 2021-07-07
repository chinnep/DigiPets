package learn.digipet.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class UserItem {

    @NotBlank
    private String username;
    @PositiveOrZero
    private int itemId;
    @PositiveOrZero
    private int quantity;

    public UserItem(String username, int itemId, int quantity) {
        this.username = username;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public UserItem(String username, int itemId) {
        this.username = username;
        this.itemId = itemId;
    }

    public UserItem(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
