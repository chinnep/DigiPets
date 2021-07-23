package learn.digipet.data.mappers;

import learn.digipet.models.Pet;
import learn.digipet.models.PetType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        pet.setTimeAtLastLogin(LocalDateTime.parse(resultSet.getString("time_at_last_login"),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        pet.setBirthday(LocalDateTime.parse(resultSet.getString("birthday"),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        pet.setDead(resultSet.getBoolean("is_dead"));
        pet.setTrophies(resultSet.getInt("trophies"));
        pet.setUsername(resultSet.getString("username"));
        pet.setPetType(new PetType(resultSet.getInt("pet_type_id")));

        return pet;
    }
}
