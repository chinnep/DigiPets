package learn.digipet.data;

import learn.digipet.data.mappers.PetTypeMapper;
import learn.digipet.models.PetType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetTypeJdbcTemplateRepository implements PetTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    public PetTypeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PetType> findAll() {
        final String sql = "select pet_type_id, pet_type_name, appetite, care, thirst, health, next_pet_type_id" +
                " from pet_type;";
        return jdbcTemplate.query(sql, new PetTypeMapper());
    }

    @Override
    public PetType findById(int petTypeId) {

        final String sql = "select pet_type_id, pet_type_name, appetite, care, thirst, health, next_pet_type_id" +
                " from pet_type" +
                " where pet_type_id = ?;";

        PetType petType = jdbcTemplate.query(sql, new PetTypeMapper(), petTypeId).stream()
                .findFirst().orElse(null);

        return petType;
    }
}
