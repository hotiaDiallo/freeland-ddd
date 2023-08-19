package tech.zerofiltre.freeland.collab.application.servicecontract;

import tech.zerofiltre.freeland.collab.domain.Rate;
import tech.zerofiltre.freeland.collab.domain.client.ClientProvider;
import tech.zerofiltre.freeland.collab.domain.client.model.Client;
import tech.zerofiltre.freeland.collab.domain.client.model.ClientId;
import tech.zerofiltre.freeland.collab.domain.servicecontract.ServiceContractNotificationProvider;
import tech.zerofiltre.freeland.collab.domain.servicecontract.ServiceContractProvider;
import tech.zerofiltre.freeland.collab.domain.servicecontract.WagePortageAgreementProvider;
import tech.zerofiltre.freeland.collab.domain.servicecontract.model.ServiceContract;
import tech.zerofiltre.freeland.collab.domain.servicecontract.model.WagePortageAgreement;
import tech.zerofiltre.freeland.collab.domain.servicecontract.model.WagePortageAgreementId;

public class ServiceContractStarter {
    private final ServiceContractProvider serviceContractProvider;
    private final ServiceContractNotificationProvider serviceContractNotificationProvider;
    ServiceContract serviceContract;
    WagePortageAgreement wagePortageAgreement;
    Client client;


    public ServiceContractStarter(ServiceContractProvider serviceContractProvider, ClientProvider clientProvider, WagePortageAgreementProvider wagePortageAgreementProvider, ServiceContractNotificationProvider serviceContractNotificationProvider) {
        this.serviceContractProvider = serviceContractProvider;
        this.serviceContractNotificationProvider = serviceContractNotificationProvider;

        wagePortageAgreement = WagePortageAgreement.builder()
                .wagePortageAgreementProvider(wagePortageAgreementProvider)
                .build();
        client = Client.builder()
                .clientProvider(clientProvider)
                .build();
    }

    public ServiceContract start(WagePortageAgreementId wagePortageAgreementId, ClientId clientId,
                                 String terms, Rate rate) throws StartServiceContractException {
        wagePortageAgreement = wagePortageAgreement
                .of(wagePortageAgreementId)
                .orElseThrow(() -> new StartServiceContractException(
                        "There is no wage portage agreement available for"
                                + wagePortageAgreementId.getAgreementNumber()));

        if (client.of(clientId).isEmpty())
            throw new StartServiceContractException("There is no client available for" + clientId.getSiren());

        serviceContract = ServiceContract.builder()
                .serviceContractProvider(serviceContractProvider)
                .serviceContractNotificationProvider(serviceContractNotificationProvider)
                .wagePortageAgreement(wagePortageAgreement)
                .clientId(clientId)
                .terms(terms)
                .rate(rate)
                .starDate(null)
                .endDate(null)
                .build()
                .start();
        serviceContract.notifyServiceContractStarted();
        return serviceContract;
    }
}