package learn.digipet.domain;

import learn.digipet.data.UserRepository;
import learn.digipet.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository repository;

    private PasswordEncoder passwordEncoder;
    private HashMap<String, User> users = new HashMap<>();

   public UserService(PasswordEncoder encoder, UserRepository repository) {
        passwordEncoder = encoder;
        this.repository = repository;
    }

   public User findByUsername(String username) { return repository.findByUsername(username); }

    public boolean add(User user) {

        if (user == null || user.getUsername() == null || user.getUsername().trim().length() == 0
                || user.getPassword() == null || user.getPassword().trim().length() == 0) {
            return false;
        }

        if (users.containsKey(user.getUsername())) {
            return false;
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));

        user.setPassword(null);

        users.put(user.getUsername(), user);

        return true;
    }


    public boolean authenticate(User user) {
        // bad data? says there is no hope to authenticate...
        if (user == null || user.getUsername() == null || user.getUsername().trim().length() == 0
                || user.getPassword() == null || user.getPassword().trim().length() == 0) {
            return false;
        }

        User existing = users.get(user.getUsername());
        if (existing == null) {
            return false;
        }

        return passwordEncoder.matches(user.getPassword(), existing.getPasswordHash());
    }

    // The old UserService (before the security / jwt stuff) is below. I don't believe we need it anymore, but just in case??
/*
    private final UserRepository repository;

    public UserService(UserRepository repository) { this.repository = repository; }

    public Result<User> add(User user) {

        Result<User> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if(!violations.isEmpty()) {
            for (ConstraintViolation<User> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
                return result;
            }
        }

        result = validate(user);
        if(result.getMessages().size() > 0) return result;

        if (result.getMessages().size() > 0) {
            return result;
        }

        if (user.getUsername() == null) {
            result.addMessage("Username cannot be null.", ResultType.INVALID);
        }

        for (int i = 0; i < repository.findAll().size(); i++) {
            if (user.getUsername().equals(repository.findByUsername(i))) {
                result.addMessage("Username already exists. Choose a different username.", ResultType.INVALID);
            }
        }

        if (user.getGold() < 0) {
            result.addMessage("User gold cannot be less than zero.", ResultType.INVALID);
        }

        //result.setPayload(repository.add(user)); // WAT? same result we've used in every project, looking for a user but this add method
                                                   // returns a boolean... Not sure how to adjust this.
        return result;
    }

    public Result<User> update(User user) {

        Result<User> result = new Result<>();

        if (user.getUsername() == null) {
            result.addMessage("Username cannot be 'null' for 'update' operation.", ResultType.INVALID);
            return result;
        }

        result = validate(user);

        if (result.getMessages().size() > 0) {
            return result;
        }

//        if (!repository.update(user)) {
//            result.addMessage(String.format("User Id: %s was not found.", user.getUsername()), ResultType.NOT_FOUND);
//        }

        return result;
    }

    private Result<User> validate(User user) {
        Result<User> result = new Result<>();

        if (user == null) {
            result.addMessage("User cannot be null.", ResultType.INVALID);
            return result;
        }
        return result;
    }
*/

}