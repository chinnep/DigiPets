package learn.digipet.data.mappers;

import learn.digipet.models.PetType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetTypeMapper implements RowMapper<PetType> {

    @Override
    public PetType mapRow(ResultSet resultSet, int i) throws SQLException {
        PetType petType = new PetType();

        petType.setPetTypeId(resultSet.getInt("pet_type_id"));
        petType.setName(resultSet.getString("pet_type_name"));
        petType.setAppetite(resultSet.getInt("apetite")); // accurate DB label
        petType.setThirst(resultSet.getInt("thirst"));
        petType.setHealth(resultSet.getInt("health"));
        petType.setNextPetTypeId(resultSet.getInt("next_pet_type_id"));

        return petType;
    }
}