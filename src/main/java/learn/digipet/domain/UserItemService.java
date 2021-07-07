package learn.digipet.domain;

import learn.digipet.data.UserItemRepository;
import learn.digipet.models.UserItem;
import org.springframework.stereotype.Service;

@Service
public class UserItemService {

    private final UserItemRepository repository;

    public UserItemService(UserItemRepository repository) {
        this.repository = repository;
    }

    public UserItem findByIds(UserItem userItem) {
        return repository.findByIds(userItem);
    }

    public Result<Void> add (UserItem userItem) {
        Result<Void> result = validate(userItem);
        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.add(userItem)) {
            result.addMessage("Item not added to user", ResultType.INVALID);
        }
        return result;
    }

    public Result<Void> update(UserItem userItem) {
        Result<Void> result = validate(userItem);
        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.update(userItem)) {
            result.addMessage("Update to user item quantity failed", ResultType.INVALID);
        }

        return result;
    }

    private Result<Void> validate(UserItem userItem) {
        Result<Void> result = new Result<>();
        if (userItem == null) {
            result.addMessage("userItem can't be null", ResultType.INVALID);
        }

        if (userItem.getQuantity() < 0) {
            result.addMessage("Quantity can't be below 0", ResultType.INVALID);
        }
        return result;
    }
}
