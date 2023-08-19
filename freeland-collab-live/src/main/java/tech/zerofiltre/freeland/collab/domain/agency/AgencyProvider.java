package tech.zerofiltre.freeland.collab.domain.agency;

import tech.zerofiltre.freeland.collab.domain.agency.model.Agency;
import tech.zerofiltre.freeland.collab.domain.agency.model.AgencyId;

import java.util.Optional;

public interface AgencyProvider {
    Optional<Agency> agencyOfId(AgencyId agencyId);
    Agency register(Agency agency);
}
