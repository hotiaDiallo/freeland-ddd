package tech.zerofiltre.freeland.collab.application.servicecontract;

import tech.zerofiltre.freeland.collab.domain.agency.AgencyProvider;
import tech.zerofiltre.freeland.collab.domain.agency.model.Agency;
import tech.zerofiltre.freeland.collab.domain.agency.model.AgencyId;
import tech.zerofiltre.freeland.collab.domain.freelancer.FreelancerProvider;
import tech.zerofiltre.freeland.collab.domain.freelancer.model.Freelancer;
import tech.zerofiltre.freeland.collab.domain.freelancer.model.FreelancerId;
import tech.zerofiltre.freeland.collab.domain.servicecontract.WagePortageAgreementProvider;
import tech.zerofiltre.freeland.collab.domain.servicecontract.model.WagePortageAgreement;

import java.util.Optional;

public class WagePortageAgreementStarter {

    private final WagePortageAgreementProvider wagePortageAgreementProvider;
    private final FreelancerProvider freelancerProvider;
    private final AgencyProvider agencyProvider;

    public WagePortageAgreementStarter(WagePortageAgreementProvider wagePortageAgreementProvider, FreelancerProvider freelancerProvider, AgencyProvider agencyProvider) {
        this.wagePortageAgreementProvider = wagePortageAgreementProvider;
        this.freelancerProvider = freelancerProvider;
        this.agencyProvider = agencyProvider;
    }

    public WagePortageAgreement start(AgencyId agencyId, FreelancerId freelancerId, String terms, float serviceFeesRate) throws StartWagePortageAgreementException{

        Optional<Agency> agency = Agency.builder()
                .agencyProvider(agencyProvider)
                .build()
                .of(agencyId);
        if(agency.isEmpty())
            throw new StartWagePortageAgreementException("There is no agency for: "+ agencyId);

        Optional<Freelancer> freelancer = Freelancer.builder()
                .freelancerProvider(freelancerProvider)
                .build()
                .of(freelancerId);
        if (freelancer.isEmpty())
            throw new StartWagePortageAgreementException("There is no freelancer for: "+ freelancerId);


        WagePortageAgreement wagePortageAgreement = WagePortageAgreement.builder()
                .wagePortageAgreementProvider(wagePortageAgreementProvider)
                .freelancerId(freelancerId)
                .agencyId(agencyId)
                .terms(terms)
                .serviceFeesRate(serviceFeesRate)
                .startDate(null)
                .endDate(null)
                .build();

        return wagePortageAgreement.register();

    }
}
