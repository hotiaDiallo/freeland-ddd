package tech.zerofiltre.freeland.collab.domain.servicecontract;

import tech.zerofiltre.freeland.collab.domain.servicecontract.model.ServiceContractEvent;

public interface ServiceContractNotificationProvider {

    void notify(ServiceContractEvent serviceContractEvent);

}
