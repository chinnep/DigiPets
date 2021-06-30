package learn.digipet.data;

import learn.digipet.models.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findByUsername(String username);

    boolean add(User user);

//    boolean update(User user);

}
