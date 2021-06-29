package learn.digipet.models;

import java.util.List;
import java.util.Objects;

public class User {

    private int userId;
    private int gold;
    private List<Pet> pets;
    private List<Item> items;

    public User(int userId, int gold) {
        this.userId = userId;
        this.gold = gold;
    }

    public User() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() && getGold() == user.getGold() && Objects.equals(getPets(), user.getPets()) && Objects.equals(getItems(), user.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getGold(), getPets(), getItems());
    }
}
