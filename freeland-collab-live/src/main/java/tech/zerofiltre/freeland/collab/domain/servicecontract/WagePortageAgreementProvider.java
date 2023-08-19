package tech.zerofiltre.freeland.collab.domain.servicecontract;

import tech.zerofiltre.freeland.collab.domain.servicecontract.model.WagePortageAgreement;
import tech.zerofiltre.freeland.collab.domain.servicecontract.model.WagePortageAgreementId;

import java.util.Optional;

public interface WagePortageAgreementProvider {

    Optional<WagePortageAgreement> wagePortageAgreementOfId(WagePortageAgreementId wagePortageAgreementId);

    WagePortageAgreement registerWagePortageAgreement(WagePortageAgreement wagePortageAgreement);

}
