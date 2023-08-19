package tech.zerofiltre.freeland.collab.domain.freelancer.model;

import tech.zerofiltre.freeland.collab.domain.Address;
import tech.zerofiltre.freeland.collab.domain.freelancer.FreelancerProvider;
import tech.zerofiltre.freeland.collab.domain.servicecontract.model.WagePortageAgreementId;

import java.util.List;
import java.util.Optional;

public class Freelancer {

    private FreelancerId freelancerId;
    private String description;
    private String phoneNumber;
    private Address address;
    private WagePortageAgreementId wagePortageAgreementId;
    private List<Skill> skills;

    private FreelancerProvider freelancerProvider;

    private Freelancer(FreelancerBuilder freelancerBuilder) {
        this.freelancerId = freelancerBuilder.freelancerId;
        this.description = freelancerBuilder.description;
        this.phoneNumber = freelancerBuilder.phoneNumber;
        this.address = freelancerBuilder.address;
        this.wagePortageAgreementId = freelancerBuilder.wagePortageAgreementId;
        this.skills = freelancerBuilder.skills;
        this.freelancerProvider = freelancerBuilder.freelancerProvider;
    }

    public static FreelancerBuilder builder() {
        return new FreelancerBuilder();
    }

    public FreelancerId getFreelancerId() {
        return freelancerId;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public WagePortageAgreementId getAgreementId() {
        return wagePortageAgreementId;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public Optional<Freelancer> of(FreelancerId freelancerId) {
        Optional<Freelancer> result = freelancerProvider.freelancerOfId(freelancerId);
        result.ifPresent(freelancer -> freelancer.freelancerProvider = this.freelancerProvider);
        return result;
    }

    public static class FreelancerBuilder {
        private FreelancerId freelancerId;
        private String description;
        private String phoneNumber;
        private Address address;
        private WagePortageAgreementId wagePortageAgreementId;
        private List<Skill> skills;

        private FreelancerProvider freelancerProvider;


        public FreelancerBuilder freelancerId(FreelancerId freelancerId) {
            this.freelancerId = freelancerId;
            return this;
        }

        public FreelancerBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FreelancerBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public FreelancerBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public FreelancerBuilder wagePortageAgreementId(WagePortageAgreementId wagePortageAgreementId) {
            this.wagePortageAgreementId = wagePortageAgreementId;
            return this;
        }

        public FreelancerBuilder skills(List<Skill> skills) {
            this.skills = skills;
            return this;
        }


        public FreelancerBuilder freelancerProvider(FreelancerProvider freelancerProvider) {
            this.freelancerProvider = freelancerProvider;
            return this;
        }

        public Freelancer build() {
            return new Freelancer(this);
        }
    }

}
