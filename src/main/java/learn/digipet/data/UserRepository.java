package learn.digipet.data;

import learn.digipet.models.User;

import java.util.HashSet;
import java.util.List;

public interface UserRepository {

    HashSet<String> findAll();

    User findByUsername(String username);

    boolean add(User user);

    boolean updateGold(User user);

}
