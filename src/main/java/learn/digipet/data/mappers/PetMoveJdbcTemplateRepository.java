package learn.digipet.data.mappers;

import learn.digipet.data.PetMoveRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class PetMoveJdbcTemplateRepository implements PetMoveRepository {

    private final JdbcTemplate jdbcTemplate;

    public PetMoveJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add (int petId, int moveId) {

        final String sql = "insert into pet_move (agency_id, agent_id, identifier, security_clearance_id, "
                + "activation_date, is_active) values "
                + "(?,?,?,?,?,?);";

        return jdbcTemplate.update(sql,
                agencyAgent.getAgencyId(),
                agencyAgent.getAgent().getAgentId(),
                agencyAgent.getIdentifier(),
                agencyAgent.getSecurityClearance().getSecurityClearanceId(),
                agencyAgent.getActivationDate(),
                agencyAgent.isActive()) > 0;
    }
}
