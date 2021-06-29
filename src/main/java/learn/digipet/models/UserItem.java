package learn.digipet.models;

import javax.validation.constraints.PositiveOrZero;

public class UserItem {

    @PositiveOrZero
    private int userId;
    @PositiveOrZero
    private int itemId;
    @PositiveOrZero
    private int quantity;

    public UserItem(int userId, int itemId, int quantity) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public UserItem(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
