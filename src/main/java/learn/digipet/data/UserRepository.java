package learn.digipet.data;

import learn.digipet.models.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(int userId_);

    User add(User user);

    boolean update(User user);

    boolean deleteById(int userId);
}
