package learn.digipet.data.mappers;

import learn.digipet.models.Pet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetMapper implements RowMapper<Pet> {

    @Override
    public Pet mapRow(ResultSet resultSet, int i) throws SQLException {
        Pet pet = new Pet();

        pet.setPetId(resultSet.getInt("pet_id"));
        pet.setName(resultSet.getString("pet_name"));
        pet.setHungerLevel(resultSet.getInt("hunger_lvl"));
        pet.setCareLevel(resultSet.getInt("care_lvl"));
        pet.setThirstLevel(resultSet.getInt("thirst_lvl"));
        pet.setHealthLevel(resultSet.getInt("health_lvl"));
        pet.setTimeToZero(resultSet.getTime("time_to_zero"));
        pet.setDead(resultSet.getBoolean("is_dead"));
        pet.setTrophies(resultSet.getInt("trophies"));
        pet.setUserId(resultSet.getInt("user_int"));

        return pet;
    }
}
