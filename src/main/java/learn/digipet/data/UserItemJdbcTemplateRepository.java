package learn.digipet.data;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserItemJdbcTemplateRepository implements UserItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserItemJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(UserItem )

}
