package learn.digipet.models;

import java.math.BigDecimal;

public class User {

    private int userId;
    private BigDecimal gold;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getGold() {
        return gold;
    }

    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }

}
