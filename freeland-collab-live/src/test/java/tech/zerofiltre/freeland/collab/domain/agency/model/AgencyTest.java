package tech.zerofiltre.freeland.collab.domain.agency.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.zerofiltre.freeland.collab.domain.agency.AgencyProvider;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AgencyTest {

    @Mock
    private AgencyProvider agencyProvider;

    private Agency agency;


    @BeforeEach
    void setUp() {
        agency = Agency.builder()
                .agencyProvider(agencyProvider)
                .build();
    }

    @Test
    @DisplayName("On register, call agencyProvider")
    void registerShouldCallAgencyProvider() {
        //given
        //when
        agency.register();
        //then
        verify(agencyProvider, times(1)).register(any());
    }

    @Test
    void ofShouldFeedAgencyProvider() {
        //given
        AgencyId agencyId = new AgencyId("siren", "name");
        when(agencyProvider.agencyOfId(any()))
                .thenReturn(Optional.of(Agency.builder().build()));
        //when
        Optional<Agency> result = agency.of(agencyId);
        //then
        assertThat(result).isNotEmpty();
        assertThat(result.get().getAgencyProvider()).isNotNull();
    }

    @Test
    void ofShouldCallAgencyProvider() {
        //given
        AgencyId agencyId = new AgencyId("siren", "name");
        //when
        agency.of(agencyId);
        //then
        verify(agencyProvider, times(1)).agencyOfId(any());
    }
}