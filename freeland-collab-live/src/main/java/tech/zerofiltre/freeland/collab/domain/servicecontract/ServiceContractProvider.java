package tech.zerofiltre.freeland.collab.domain.servicecontract;

import tech.zerofiltre.freeland.collab.domain.servicecontract.model.ServiceContract;
import tech.zerofiltre.freeland.collab.domain.servicecontract.model.ServiceContractId;

import java.util.Optional;

public interface ServiceContractProvider {

    Optional<ServiceContract> serviceContractOfId(ServiceContractId serviceContractId);

    ServiceContract registerContract(ServiceContract serviceContract);
}
