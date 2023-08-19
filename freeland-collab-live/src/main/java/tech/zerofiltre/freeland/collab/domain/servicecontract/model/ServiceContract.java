package tech.zerofiltre.freeland.collab.domain.servicecontract.model;

import tech.zerofiltre.freeland.collab.domain.Rate;
import tech.zerofiltre.freeland.collab.domain.client.model.ClientId;
import tech.zerofiltre.freeland.collab.domain.servicecontract.ServiceContractNotificationProvider;
import tech.zerofiltre.freeland.collab.domain.servicecontract.ServiceContractProvider;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;

public class ServiceContract {
    private ServiceContractProvider serviceContractProvider;
    private ServiceContractNotificationProvider serviceContractNotificationProvider;
    private ServiceContractId serviceContractId;
    private WagePortageAgreement wagePortageAgreement;
    private ClientId clientId;
    private Rate rate;
    private String terms;
    private Date startDate;
    private Date endDate;



    private ServiceContract(ServiceContractBuilder serviceContractBuilder) {
        this.serviceContractId = serviceContractBuilder.serviceContractId;
        this.clientId = serviceContractBuilder.clientId;
        this.rate = serviceContractBuilder.rate;
        this.terms = serviceContractBuilder.terms;
        this.startDate = serviceContractBuilder.startDate != null ? serviceContractBuilder.startDate : new Date();
        this.wagePortageAgreement = serviceContractBuilder.wagePortageAgreement;
        this.endDate = serviceContractBuilder.endDate != null ? serviceContractBuilder.endDate : new Date(startDate.getTime() + Duration.ofDays(180).getSeconds() * 1000);

        this.serviceContractProvider = serviceContractBuilder.serviceContractProvider;
        this.serviceContractNotificationProvider = serviceContractBuilder.serviceContractNotificationProvider;
    }

    public ServiceContractProvider getServiceContractProvider() {
        return serviceContractProvider;
    }

    public ServiceContractNotificationProvider getServiceContractNotificationProvider() {
        return serviceContractNotificationProvider;
    }

    public ServiceContractId getServiceContractId() {
        return serviceContractId;
    }

    public WagePortageAgreement getWagePortageAgreement() {
        return wagePortageAgreement;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public Rate getRate() {
        return rate;
    }

    public String getTerms() {
        return terms;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public static ServiceContractBuilder builder(){
        return new ServiceContractBuilder();
    }

    public ServiceContract start(){
        serviceContractId = serviceContractProvider.registerContract(this).getServiceContractId();
        return this;
    }

    public Optional<ServiceContract> of(ServiceContractId serviceContractId){
        Optional<ServiceContract> result = serviceContractProvider.serviceContractOfId(serviceContractId);
        result.ifPresent(serviceContract -> {
            serviceContract.serviceContractProvider = this.serviceContractProvider;
            serviceContract.serviceContractNotificationProvider = this.serviceContractNotificationProvider;
        });
        return result;
    }

    public void notifyServiceContractStarted(){
        ServiceContractStarted event = new ServiceContractStarted(
                serviceContractId.getContractNumber(),
                clientId.getName(),
                clientId.getSiren(),
                wagePortageAgreement.getFreelancerId().getName(),
                wagePortageAgreement.getFreelancerId().getSiren(),
                wagePortageAgreement.getAgencyId().getName(),
                wagePortageAgreement.getAgencyId().getSiren(),
                rate.getValue(),
                rate.getFrequency(),
                rate.getCurrency(),
                wagePortageAgreement.getServiceFeesRate(),
                this.getStartDate()
        );
        notify(event);
    }

    private void notify(ServiceContractEvent serviceContractEvent){
        serviceContractNotificationProvider.notify(serviceContractEvent);
    }

    public static class ServiceContractBuilder{
        private ServiceContractProvider serviceContractProvider;
        private ServiceContractNotificationProvider serviceContractNotificationProvider;
        private ServiceContractId serviceContractId;
        private WagePortageAgreement wagePortageAgreement;
        private ClientId clientId;
        private Rate rate;
        private String terms;
        private Date startDate;
        private Date endDate;

        public ServiceContractBuilder serviceContractProvider(ServiceContractProvider serviceContractProvider) {
            this.serviceContractProvider = serviceContractProvider;
            return this;
        }

        public ServiceContractBuilder serviceContractNotificationProvider(ServiceContractNotificationProvider serviceContractNotificationProvider) {
            this.serviceContractNotificationProvider = serviceContractNotificationProvider;
            return this;
        }

        public ServiceContractBuilder serviceContractId(ServiceContractId serviceContractId) {
            this.serviceContractId = serviceContractId;
            return this;
        }

        public ServiceContractBuilder wagePortageAgreement(WagePortageAgreement wagePortageAgreement) {
            this.wagePortageAgreement = wagePortageAgreement;
            return this;
        }
        public ServiceContractBuilder clientId(ClientId clientId) {
            this.clientId = clientId;
            return this;
        }

        public ServiceContractBuilder rate(Rate rate) {
            this.rate = rate;
            return this;
        }

        public ServiceContractBuilder terms(String terms) {
            this.terms = terms;
            return this;
        }

        public ServiceContractBuilder starDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public ServiceContractBuilder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public ServiceContract build() {
            return new ServiceContract(this);
        }


    }

}
