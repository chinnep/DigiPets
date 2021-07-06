package learn.digipet.domain;

import learn.digipet.data.UserRepository;
import learn.digipet.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    private final UserRepository repository;

    private PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder encoder, UserRepository repository) {
        passwordEncoder = encoder;
        this.repository = repository;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public boolean add(User user) {

        HashSet<String> users = repository.findAll();

        if (!validateUser(user)) return false;

        if (users.contains(user.getUsername())) {
            return false;
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        user.setPassword(null);

        if (!repository.add(user)) {
            return false;
        }
        return true;
    }

    public boolean authenticate(User user) {

        if (!validateUser(user)) return false;

        User existing = repository.findByUsername(user.getUsername());

        if (existing == null) return false;

        return passwordEncoder.matches(user.getPassword(), existing.getPasswordHash());
    }

    private boolean validateUser(User user) {
        if (user == null || user.getUsername() == null || user.getUsername().trim().length() == 0
                || user.getPassword() == null || user.getPassword().trim().length() == 0) {
            return false;
        }
        return true;
    }
}
