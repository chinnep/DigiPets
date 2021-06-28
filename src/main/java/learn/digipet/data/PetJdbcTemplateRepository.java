package learn.digipet.data;

import learn.digipet.data.mappers.PetMapper;
import learn.digipet.models.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetJdbcTemplateRepository implements PetRepository {

    private final JdbcTemplate jdbcTemplate;

    public PetJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pet> findAll() {
        final String sql = "select pet_id, pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl" +
                "time_to_zero, is_dead, trophies, user_id from pet;";
        return jdbcTemplate.query(sql, new PetMapper());
    }

    @Override
    public Pet findById(int petId) {
        final String sql = "select pet_id, pet_name, hunger_lvl, care_lvl, thirst_lvl, health_lvl" +
                "time_to_zero, is_dead, trophies, user_id "
                + "from pet;"
                + "where pet_id = ?;";

        Pet result = jdbcTemplate.query(sql, new PetMapper(), petId).stream()
                .findAny().orElse(null);

        if (result != null) {
            addPetType(result);
            addMoves(result);
        }

        return result;
    }

    @Override
    public boolean deleteById(int petId) {
        return false;
    }

    private void addPetType(Pet pet) {

        final String sql = "select location_id, name, address, city, region, "
                + "country_code, postal_code, agency_id "
                + "from location "
                + "where agency_id = ?";

        var locations = jdbcTemplate.query(sql, new LocationMapper(), agency.getAgencyId());
        agency.setLocations(locations);
    }

    private void addMoves(Pet pet) {

        final String sql = "select move_id, move_name, damage "
                + "from move m "
                + "inner join agent a on aa.agent_id = a.agent_id "
                + "inner join security_clearance sc on aa.security_clearance_id = sc.security_clearance_id "
                + "where aa.agency_id = ?";

        var agencyAgents = jdbcTemplate.query(sql, new AgencyAgentMapper(), agency.getAgencyId());
        agency.setAgents(agencyAgents);
    }
}
