package learn.digipet.domain;

import learn.digipet.data.UserRepository;
import learn.digipet.models.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) { this.repository = repository; }

    public List<User> findAll() { return repository.findAll(); }

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

        if (user.getUserId() == 0 ) {
            result.addMessage("User Id must be a valid number.", ResultType.INVALID);
        }

        for (int i = 0; i < repository.findAll().size(); i++) {
            if (user.getUserId() == repository.findById(i).getUserId()) {
                result.addMessage("User Id cannot be a duplicate Id.", ResultType.INVALID);
            }
        }

        if (user.getGold() < 0) {
            result.addMessage("User gold cannot be less than zero.", ResultType.INVALID);
        }

        //result.setPayload(repository.add(user)); // WAT?
        return result;
    }

    public Result<User> update(User user) {

        Result<User> result = new Result<>();

        if (user.getUserId() <= 0) {
            result.addMessage("User Id cannot be zero or less for 'update' operation.", ResultType.INVALID);
            return result;
        }

        result = validate(user);

        if (result.getMessages().size() > 0) {
            return result;
        }

        if (!repository.update(user)) {
            result.addMessage(String.format("User Id: %s was not found.", user.getUserId()), ResultType.NOT_FOUND);
        }

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

}
